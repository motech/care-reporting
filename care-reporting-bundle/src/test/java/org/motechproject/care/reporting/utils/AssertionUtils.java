package org.motechproject.care.reporting.utils;

import junit.framework.ComparisonFailure;

import java.util.LinkedHashMap;
import java.util.Map;

public class AssertionUtils {
    public static void assertContainsAll(Map<String, String> expected, Map<String, String> actual) {
        LinkedHashMap<String, Object[]> differences = new LinkedHashMap<String, Object[]>();
        for (String key : expected.keySet()) {
            Object value = expected.get(key);
            if (!actual.containsKey(key)) differences.put(key, new Object[]{value, null});
            else if (value == null && actual.get(key) == null) continue;
            else if (value == null && actual.get(key) != null)
                differences.put(key, new Object[]{value, actual.get(key)});
            else {
                Object actualValue = actual.get(key);
                if (!value.equals(actualValue))
                    differences.put(key, new Object[]{value, actualValue});
            }
        }
        if (!differences.isEmpty())
            processDifferenceComparison(expected, actual);
    }

    private static void processDifferenceComparison(Map<String, ?> expected, Map<String, ?> actual) {
        StringBuilder expectedValue = new StringBuilder();
        StringBuilder actualValue = new StringBuilder();
        for (String expectedKey : expected.keySet()) {
            expectedValue.append(String.format("%s=%s\n", expectedKey, expected.get(expectedKey)));
            actualValue.append(String.format("%s%s\n", expectedKey, actual.containsKey(expectedKey) ? "=" + actual.get(expectedKey) : " <IS MISSING>"));
        }
        throw new ComparisonFailure("Expected and actual values are not same", expectedValue.toString(), actualValue.toString());
    }
}
