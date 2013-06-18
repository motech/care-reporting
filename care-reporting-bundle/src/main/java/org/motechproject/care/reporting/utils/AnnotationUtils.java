package org.motechproject.care.reporting.utils;

import org.apache.commons.lang.ArrayUtils;
import org.motechproject.care.reporting.domain.annotations.ExternalPrimaryKey;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationUtils {
    public static Object getExternalPrimaryKeyValue(Object object) {
        Field externalPrimaryKeyField = getExternalPrimaryKeyField(object.getClass());
        try {
            externalPrimaryKeyField.setAccessible(true);
            return externalPrimaryKeyField.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Field getExternalPrimaryKeyField(Class clazz) {
        return findFieldWithAnnotation(clazz, ExternalPrimaryKey.class);
    }

    private static Field findFieldWithAnnotation(Class clazz, Class<? extends Annotation> annotationClass) {
        Field[] allFields = getAllFields(clazz);

        for (Field declaredField : allFields) {
            if (declaredField.isAnnotationPresent(annotationClass))
                return declaredField;
        }
        return null;
    }

    private static Field[] getAllFields(Class clazz) {
        Field[] classFields = clazz.getDeclaredFields();
        Field[] superClassFields = clazz.getSuperclass().getDeclaredFields();
        return (Field[]) ArrayUtils.addAll(classFields, superClassFields);
    }
}
