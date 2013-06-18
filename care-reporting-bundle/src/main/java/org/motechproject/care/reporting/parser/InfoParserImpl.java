package org.motechproject.care.reporting.parser;

import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.motechproject.care.reporting.utils.ListUtils;
import org.motechproject.care.reporting.utils.StringUtils;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.*;

public class InfoParserImpl implements InfoParser {

    @JsonProperty("convertToCamelCase")
    private boolean convertToCamelCase = true;
    @JsonProperty("keyConversionMap")
    private Map<String, String> keyConversionMap;
    private ObjectMapper objectMapper = new ObjectMapper();

    @JsonProperty("restrictedElements")
    private List<String> restrictedElements = new ArrayList<>();

    public InfoParserImpl() {
        this(new HashMap<String, String>());
    }

    public InfoParserImpl(Map<String, String> keyConversionMap) {
        this.keyConversionMap = keyConversionMap;
    }

    @Override
    public Map<String, String> parse(FormValueElement element) {
        return parse(element, false);
    }

    @Override
    public Map<String, String> parse(FormValueElement element, boolean isRecursive) {
        Map<String, String> mapper = new HashMap<>();
        Multimap<String, FormValueElement> subElements = element.getSubElements();

        if (empty(subElements))
            return mapper;

        Map<String, Collection<FormValueElement>> subElementsMap = subElements.asMap();
        for (Map.Entry<String, Collection<FormValueElement>> subElement : subElementsMap.entrySet()) {

            String key = subElement.getKey();
            if (isRestricted(key))
                continue;

            if (isRecursive) {
                for (FormValueElement formValueElement : subElement.getValue()) {
                    mapper.putAll(parse(formValueElement, isRecursive));
                }
            }

            key = applyConversions(key);
            mapper.put(key, getFormValueElementValue(subElement));
        }
        return mapper;
    }

    @Override
    public Map<String, Object> parse(Object object) {
        Map<String, Object> objectFieldsMap = objectMapper.convertValue(object, Map.class);
        return parse(objectFieldsMap);
    }

    @Override
    public Map<String, Object> parse(Map map) {
        HashMap<String, Object> mapper = new HashMap<>();
        for (Object mapKey : map.keySet()) {
            String key = (String) mapKey;
            if (isRestricted(key))
                continue;

            key = applyConversions(key);

            mapper.put(key, getSingleValue(map.get(mapKey)));
        }
        return mapper;
    }

    @Override
    public void setConvertToCamelCase(boolean convertToCamelCase) {
        this.convertToCamelCase = convertToCamelCase;
    }

    @Override
    public void setRestrictedElements(List<String> restrictedElements) {
        this.restrictedElements = restrictedElements;
    }

    @Override
    public void setKeyConversionMap(Map<String, String> keyConversionMap){
        this.keyConversionMap = keyConversionMap;
    }

    private boolean empty(Multimap<?, ?> subElements) {
        return null == subElements || subElements.size() == 0;
    }

    private String getFormValueElementValue(Map.Entry<String, Collection<FormValueElement>> subElement) {
        FormValueElement fieldValue = getFormValueElement(subElement);
        return fieldValue.getValue();
    }

    private FormValueElement getFormValueElement(Map.Entry<String, Collection<FormValueElement>> subElement) {
        Collection<FormValueElement> subElementValue = subElement.getValue();
        return (FormValueElement) CollectionUtils.get(subElementValue, 0);
    }

    private Object getSingleValue(Object value) {
        if (value instanceof List) {
            return ListUtils.safeGet((List) value, 0);
        }
        return value;
    }

    private String applyConversions(String key) {
        key = applyKeyConversionMap(key);
        key = applyCamelConversion(key);
        return key;
    }

    private boolean isRestricted(String key) {
        return null != restrictedElements && restrictedElements.contains(key);
    }

    private String applyCamelConversion(String input) {
        return convertToCamelCase ? StringUtils.toCamelCase(input) : input;
    }

    private String applyKeyConversionMap(String keyValue) {
        return keyConversionMap.containsKey(keyValue) ? keyConversionMap.get(keyValue) : keyValue;
    }
}