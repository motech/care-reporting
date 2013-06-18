package org.motechproject.care.reporting.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {
    public static void updateValue(String fieldName, Object source, Object target) {
        try {
            Field field = getField(source, fieldName);
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers()))
                return;
            field.setAccessible(true);
            Object updatedValue = field.get(source);
            field.set(target, updatedValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getValue(Object object, String fieldName) {
        try {
            Field field = getField(object, fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Field getField(Object object, String fieldName) throws NoSuchFieldException {
        Field field;
        try {
            field = object.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = object.getClass().getSuperclass().getDeclaredField(fieldName);
        }
        return field;
    }
}
