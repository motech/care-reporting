package org.motechproject.care.reporting.mapper;


import org.motechproject.care.reporting.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class GenericMapper {
    protected Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    public <T> T map(Map<String, String> keyStore, Class<T> type) {
        T newInstance;
        try {
            newInstance = type.newInstance();
        } catch (Exception ex) {
            logger.warn("Exception thrown when creating new instance" + ex);
            return null;
        }

        return map(keyStore, newInstance);
    }

    public <T> T map(Map<String, String> keyStore, T typeInstance) {
        for (Map.Entry<String, String> field : keyStore.entrySet()) {
            String key = field.getKey();
            String value = field.getValue();

            logger.info("Setting " + value + " to " + key);
            ObjectUtils.set(typeInstance, key, value);
        }

        return typeInstance;
    }
}


