package org.motechproject.care.reporting.mapper;


import org.motechproject.care.reporting.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class GenericMapper {

    public <T, U> T map(Map<String, U> keyStore, Class<T> type) {
        T newInstance;
        try {
            newInstance = type.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return map(keyStore, newInstance);
    }

    public <T, U> T map(Map<String, U> keyStore, T typeInstance) {
        for (Map.Entry<String, U> field : keyStore.entrySet()) {
            String key = field.getKey();
            U value = field.getValue();

            ObjectUtils.set(typeInstance, key, value);
        }

        return typeInstance;
    }
}


