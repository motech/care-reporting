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

        for (int i = 0; i < tokens.length; i++) {
            String fieldName = convertAllCaps(tokens[i]);
            fieldName = i == 0 ? uncapitalize(fieldName) : capitalize(fieldName);

            buffer.append(fieldName);
            if (isNotLastToken(tokens, i) && tokens[i].matches("[a-z]+\\d+$"))
                buffer.append("_");
        }

        return buffer.toString();
    }

    private static boolean isNotLastToken(String[] tokens, int i) {
        return i != tokens.length - 1;
    }

    private static String convertAllCaps(String token) {
        if (token.matches("^[A-Z0-9]+$"))
            return token.toLowerCase();
        return token;
    }
}
