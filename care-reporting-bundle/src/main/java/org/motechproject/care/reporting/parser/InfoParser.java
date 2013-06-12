package org.motechproject.care.reporting.parser;

import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.motechproject.care.reporting.utils.ListUtils;
import org.motechproject.care.reporting.utils.StringUtils;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.*;

public class InfoParser {

    private boolean convertToCamelCase = true;
    private final Map<String, String> keyConversionMap;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<String> restrictedElements = new ArrayList<>();

    public InfoParser() {
        this(new HashMap<String, String>());
    }

    public InfoParser(Map<String, String> keyConversionMap) {
        this.keyConversionMap = keyConversionMap;
    }

    Map<String, String> parse(FormValueElement form) {
        Map<String, String> mapper = new HashMap<>();
        Map<String, Collection<FormValueElement>> subElementsMap = form.getSubElements().asMap();
        for (Map.Entry<String, Collection<FormValueElement>> subElement : subElementsMap.entrySet()) {

            String key = subElement.getKey();

            if (isRestricted(key))
                continue;

            key = applyConversions(key);

            Collection<FormValueElement> subElementValue = subElement.getValue();
            FormValueElement fieldValue = (FormValueElement) CollectionUtils.get(subElementValue, 0);
            mapper.put(key, fieldValue.getValue());
        }
        return mapper;
    }

    Map<String, Object> parse(Object object) {
        Map<String, Object> objectFieldsMap = objectMapper.convertValue(object, Map.class);
        return parse(objectFieldsMap);
    }

    Map<String, Object> parse(Map map) {
        HashMap<String, Object> mapper = new HashMap<>();
        for(Object mapKey : map.keySet()) {
            String key = (String) mapKey;
            if (isRestricted(key))
                continue;

            key = applyConversions(key);

            mapper.put(key, getSingleValue(map.get(mapKey)));
        }
        return mapper;
    }

    private Object getSingleValue(Object value) {
        if(value instanceof List) {
            return ListUtils.safeGet((List) value, 0);
        }
        return value;
    }

    private String applyConversions(String key){
        key = applyKeyConversionMap(key);
        key = applyCamelConversion(key);
        return key;
    }

    private boolean isRestricted(String key){
        return null != restrictedElements && restrictedElements.contains(key);
    }

    private String applyCamelConversion(String input) {
        return convertToCamelCase ? StringUtils.toCamelCase(input) : input;
    }

    private String applyKeyConversionMap(String keyValue) {
        return keyConversionMap.containsKey(keyValue) ? keyConversionMap.get(keyValue) : keyValue;
    }

    public void setConvertToCamelCase(boolean convertToCamelCase) {
        this.convertToCamelCase = convertToCamelCase;
    }

    public void setRestrictedElements(List<String> restrictedElements) {
        this.restrictedElements = restrictedElements;
    }
}