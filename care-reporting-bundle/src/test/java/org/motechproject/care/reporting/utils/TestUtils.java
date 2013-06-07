package org.motechproject.care.reporting.utils;


import junit.framework.AssertionFailedError;
import org.apache.commons.lang.ArrayUtils;
import org.unitils.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class TestUtils {
    public static void setFields(Object object, Map<String, Object> fieldMap) {
        for (String fieldName : fieldMap.keySet()) {
            setField(object, fieldName, fieldMap.get(fieldName));
        }
    }

    public static void setField(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            ReflectionUtils.setFieldValue(object, field, value);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getField(Object object, Field field) {
        field.setAccessible(true);
        return ReflectionUtils.getFieldValue(object, field);
    }

    public static void assertReflectionEqualsWithIgnore(Object lhs, Object rhs, String[] ignoredFields) {
        if (isReferenceSame(lhs, rhs)) {
            return;
        }
        if (!isOfSameClass(lhs, rhs)) {
            throw new AssertionFailedError("Classes of the two objects are different.");
        }
        Difference difference = compareFields(lhs, rhs, ignoredFields);
        if (!difference.isNull()) throw new AssertionFailedError(difference.message());
    }

    private static boolean isOfSameClass(Object lhs, Object rhs) {
        return lhs.getClass().isAssignableFrom(rhs.getClass()) || rhs.getClass().isAssignableFrom(lhs.getClass());
    }

    private static Difference compareFields(Object lhs, Object rhs, String[] ignoredFields) {
        Field[] fields = lhs.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (ArrayUtils.contains(ignoredFields, field.getName())) continue;
            Object value1 = getField(lhs, field);
            Object value2 = getField(rhs, field);
            if (value1 == value2) continue;
            if (value1 == null || value2 == null || !value1.equals(value2))
                return new Difference(field, value1, value2);
        }
        return Difference.NULL;
    }

    private static boolean isReferenceSame(Object lhs, Object rhs) {
        return lhs == rhs || lhs == null || rhs == null;
    }

    public static <T> void assertReflectionContains(T object, Collection<T> collection, String... ignoredFields) {
        for (T t : collection) {
            if (compareFields(object, t, ignoredFields).isNull()) return;
        }
        throw new AssertionFailedError("Object does not exist in collection.");
    }

    public static <T> void assertReflectionDoesNotContains(T object, Collection<T> collection, String... ignoredFields) {
        for (T t : collection) {
            if (compareFields(object, t, ignoredFields).isNull()) throw new AssertionFailedError("Object exists in collection.");
        }
        return;
    }

    private static class Difference {
        public static final Difference NULL = new Difference(null, null, null) {
            @Override
            public boolean isNull() {
                return true;
            }
        };
        private final Field field;
        private final Object value1;
        private final Object value2;

        public Difference(Field field, Object value1, Object value2) {
            this.field = field;
            this.value1 = value1;
            this.value2 = value2;
        }

        public boolean isNull() {
            return false;
        }

        public String message() {
            return String.format("Values for field %s don't match.\nExpected: %s\nActual: %s", field.getName(), value1, value2);
        }
    }


}
