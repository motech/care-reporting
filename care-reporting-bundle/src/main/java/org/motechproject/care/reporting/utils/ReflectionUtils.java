package org.motechproject.care.reporting.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {
    public static void updateValue(String fieldName, Object source, Object target) {
        try {
            Field field = source.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object updatedValue = field.get(source);
            field.set(target, updatedValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
