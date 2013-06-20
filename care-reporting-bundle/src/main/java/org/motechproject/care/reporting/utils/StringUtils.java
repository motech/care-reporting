package org.motechproject.care.reporting.utils;

import static org.apache.commons.lang.StringUtils.capitalize;
import static org.apache.commons.lang.StringUtils.uncapitalize;

public class StringUtils {

    public static String toCamelCase(String underscoreSeparatedString) {
        return toCamelCase(underscoreSeparatedString, "_");
    }

    private static String toCamelCase(String inputString, String delimiter) {
        if (org.apache.commons.lang.StringUtils.isEmpty(inputString))
            return inputString;

        StringBuilder buffer = new StringBuilder();

        String[] tokens = inputString.split(delimiter);

        buffer.append(uncapitalize(convertAllCaps(tokens[0])));

        for (int i = 1; i < tokens.length; i++) {
            String fieldName = convertAllCaps(tokens[i]);
            if(fieldName.matches("\\d+\\w*"))
                buffer.append(delimiter).append(fieldName);
            else
                buffer.append(capitalize(fieldName));
        }
        return buffer.toString();
    }

    private static String convertAllCaps(String token) {
        if(token.matches("^[A-Z0-9]+$"))
            return token.toLowerCase();
        return token;
    }
}
