package org.motechproject.care.reporting.utils;

public class StringUtils {

    public static String toCamelCase(String underscoreSeparatedString) {
        return toCamelCase(underscoreSeparatedString, "_");
    }

    public static String toCamelCase(String inputString, String delimiter) {
        if (org.apache.commons.lang.StringUtils.isEmpty(inputString))
            return inputString;

        StringBuilder buffer = new StringBuilder();

        String[] tokens = inputString.split(delimiter);

        buffer.append(org.apache.commons.lang.StringUtils.uncapitalize(tokens[0]));
        for (int i = 1; i < tokens.length; i++) {
            buffer.append(org.apache.commons.lang.StringUtils.capitalize(tokens[i]));
        }
        return buffer.toString();
    }
}
