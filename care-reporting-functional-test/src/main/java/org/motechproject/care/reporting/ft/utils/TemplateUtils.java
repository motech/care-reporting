package org.motechproject.care.reporting.ft.utils;

import org.antlr.stringtemplate.StringTemplate;

import java.util.Map;

public class TemplateUtils {

    public static String apply(String template, Map<String, String> placeholderMap) {
        StringTemplate stringTemplate = new StringTemplate(template);
        for(String placeHolderKey: placeholderMap.keySet()) {
            stringTemplate.setAttribute(placeHolderKey, placeholderMap.get(placeHolderKey));
        }
        return stringTemplate.toString();
    }
}
