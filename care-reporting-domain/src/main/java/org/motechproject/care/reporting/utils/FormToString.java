package org.motechproject.care.reporting.utils;

public class FormToString {
    public static String toString(Object object) {
        Object instanceId = ReflectionUtils.getValue(object, "instanceId");
        return String.format("%s{instanceId=%s}", object.getClass().getSimpleName(), instanceId);
    }
}
