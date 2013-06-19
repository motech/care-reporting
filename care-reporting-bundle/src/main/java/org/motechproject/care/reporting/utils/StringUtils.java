package org.motechproject.care.reporting.utils;

public class StringUtils {

    public static String toCamelCase(String underscoreSeparatedString) {
        return toCamelCase(underscoreSeparatedString, "_");
    }

    private static String toCamelCase(String inputString, String delimiter) {
        if (org.apache.commons.lang.StringUtils.isEmpty(inputString))
            return inputString;

        StringBuilder buffer = new StringBuilder();

        String[] tokens = inputString.split(delimiter);

        buffer.append(org.apache.commons.lang.StringUtils.uncapitalize(tokens[0]));
        for (int i = 1; i < tokens.length; i++) {
            String fieldName = tokens[i];
            if(fieldName.matches("\\d+\\w*"))
                buffer.append(delimiter).append(fieldName);
            else
                buffer.append(org.apache.commons.lang.StringUtils.capitalize(fieldName));
        }
        return buffer.toString();
    }
}
