package org.motechproject.care.reporting.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;


public class ObjectUtils {

    static {
        ConvertUtils.register(new CareDateConverter(), Date.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new ShortConverter(null),Short.class);
        ConvertUtils.register(new BooleanConverter(null),Boolean.class);
        ConvertUtils.register(new BigDecimalConverter(null),BigDecimal.class);
    }

    private static Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    public static boolean set(Object object, String fieldName, Object fieldValue) {
        try {
            BeanUtils.setProperty(object, fieldName, fieldValue);
            return true;
        } catch (Exception ex) {
            logger.warn("Exception when setting " + fieldValue + " to " + fieldName + " Exception Details: " + ex);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static <E> E get(Object object, String fieldName) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return (E) field.get(object);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
