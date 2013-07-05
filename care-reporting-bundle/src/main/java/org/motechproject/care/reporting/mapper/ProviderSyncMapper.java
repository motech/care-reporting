package org.motechproject.care.reporting.mapper;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ProviderSyncMapper {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");
    private final BeanUtilsBean beanUtils;

    private ProviderSyncMapper() {
        beanUtils = new BeanUtilsBean();

        ConvertUtilsBean convertUtils = beanUtils.getConvertUtils();

        AllDataTypeConverters allDataTypeConverters = new AllDataTypeConverters();

        allDataTypeConverters.registerBaseConverters(convertUtils, new String[] {
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "yyyy-MM-dd'T'HH:mm:ssXXX",
                "yyyy-MM-dd'T'HH:mm:ssZ",
                "yyyy-MM-dd'T'HH:mm:ss",
                "dd/MM/yyyy",
                "yyyy-MM-dd",
        });
    }

    private static ProviderSyncMapper _instance;

    public static ProviderSyncMapper getInstance() {
        if(_instance != null) {
            return _instance;
        }
        return createInstance();
    }

    private synchronized static ProviderSyncMapper createInstance() {
        if(_instance != null) {
            return _instance;
        }
        _instance = new ProviderSyncMapper();
        return _instance;
    }

    public <T, U> T map(Map<String, U> keyStore, Class<T> type) {
        T newInstance;
        try {
            newInstance = type.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return map(keyStore, newInstance);
    }

    private <T, U> T map(Map<String, U> keyStore, T typeInstance) {
        for (Map.Entry<String, U> field : keyStore.entrySet()) {
            String key = field.getKey();
            U value = field.getValue();

            set(typeInstance, key, value);
        }

        return typeInstance;
    }

    private boolean set(Object object, String fieldName, Object fieldValue) {
        try {
            beanUtils.setProperty(object, fieldName, fieldValue);
            return true;
        } catch (Exception ex) {
            logger.warn("Exception when setting " + fieldValue + " to " + fieldName + " Exception Details: " + ex);
            return false;
        }
    }
}
