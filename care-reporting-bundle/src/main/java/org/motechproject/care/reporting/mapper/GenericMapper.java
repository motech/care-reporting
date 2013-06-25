package org.motechproject.care.reporting.mapper;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class GenericMapper {
    private static Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    public <T, U> T map(Map<String, U> keyStore, Class<T> type) {
        T newInstance;
        try {
            newInstance = type.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return map(keyStore, newInstance);
    }

    private  <T, U> T map(Map<String, U> keyStore, T typeInstance) {
        for (Map.Entry<String, U> field : keyStore.entrySet()) {
            String key = field.getKey();
            U value = field.getValue();

            set(typeInstance, key, value);
        }

        return typeInstance;
    }

    private boolean set(Object object, String fieldName, Object fieldValue) {
        try {
            BeanUtils.setProperty(object, fieldName, fieldValue);
            return true;
        } catch (Exception ex) {
            logger.warn("Exception when setting " + fieldValue + " to " + fieldName + " Exception Details: " + ex);
            return false;
        }
    }
}
