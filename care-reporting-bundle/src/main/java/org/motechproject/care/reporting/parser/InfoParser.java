package org.motechproject.care.reporting.parser;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.motechproject.care.reporting.utils.ListUtils;
import org.motechproject.care.reporting.utils.StringUtils;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoParser {

    private boolean convertToCamelCase = true;
    private final Map<String, String> keyConversionMap;
    private ObjectMapper objectMapper = new ObjectMapper();

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

            String key = applyKeyConversionMap(subElement.getKey());
            key = applyCamelConversion(key);

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
            String key = applyKeyConversionMap((String) mapKey);
            key = applyCamelConversion(key);
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

    private String applyCamelConversion(String input) {
        return convertToCamelCase ? StringUtils.toCamelCase(input) : input;
    }

    private String applyKeyConversionMap(String keyValue) {
        return keyConversionMap.containsKey(keyValue) ? keyConversionMap.get(keyValue) : keyValue;
    }

    public void setConvertToCamelCase(boolean convertToCamelCase) {
        this.convertToCamelCase = convertToCamelCase;
    }
}