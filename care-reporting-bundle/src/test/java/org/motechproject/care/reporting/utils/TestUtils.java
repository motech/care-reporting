package org.motechproject.care.reporting.utils;

import org.unitils.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

public class TestUtils {
    public static void setField(Object object, Map<String, Object> fieldMap) {
        for (String fieldName : fieldMap.keySet()) {
            try {
                Field field = object.getClass().getDeclaredField(fieldName);
                ReflectionUtils.setFieldValue(object, field, fieldMap.get(fieldName));
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
