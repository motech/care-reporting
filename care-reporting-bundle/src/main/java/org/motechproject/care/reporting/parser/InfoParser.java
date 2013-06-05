package org.motechproject.care.reporting.parser;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.motechproject.care.reporting.utils.StringUtils;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InfoParser {

    private boolean convertToCamelCase = true;
    private Map<String, String> keyConversionMap = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();


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

    Map<String, String> parse(Object object) {
        Map<String, String> objectFieldsMap = objectMapper.convertValue(object, Map.class);
        return parse(objectFieldsMap);
    }

    Map<String, String> parse(Map<String, String> map) {

        HashMap<String, String> mapper = new HashMap<>();

        for (Map.Entry<String, String> pair : map.entrySet()) {
            String key = applyKeyConversionMap(pair.getKey());
            key = applyCamelConversion(key);

            mapper.put(key, pair.getValue());
        }
        return mapper;
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

    public void setKeyConversionMap(Map<String, String> keyConversionMap) {
        this.keyConversionMap = keyConversionMap;
    }

    public Map<String, String> getKeyConversionMap() {
        return keyConversionMap;
    }
}