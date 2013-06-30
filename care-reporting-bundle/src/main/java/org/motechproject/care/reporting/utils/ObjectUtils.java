package org.motechproject.care.reporting.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ObjectUtils {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    public static boolean set(Object object, String fieldName, Object fieldValue) {
        try {
            BeanUtils.setProperty(object, fieldName, fieldValue);
            return true;
        } catch (Exception ex) {
            logger.warn("Exception when setting " + fieldValue + " to " + fieldName + " Exception Details: " + ex);
            return false;
        }
    }
}
