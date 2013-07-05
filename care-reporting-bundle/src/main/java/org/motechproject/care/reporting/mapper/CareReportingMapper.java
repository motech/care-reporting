package org.motechproject.care.reporting.mapper;


import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.motechproject.care.reporting.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CareReportingMapper {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");
    private BeanUtilsBean beanUtils;

    private CareReportingMapper(Service careService) {
        beanUtils = new BeanUtilsBean();

        ConvertUtilsBean convertUtils = beanUtils.getConvertUtils();

        AllDataTypeConverters allDataTypeConverters = new AllDataTypeConverters();

        allDataTypeConverters.registerBaseConverters(convertUtils, new String[]{
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "yyyy-MM-dd'T'HH:mm:ssXXX",
                "yyyy-MM-dd'T'HH:mm:ssZ",
                "yyyy-MM-dd'T'HH:mm:ss",
                "yyyy-MM-dd",
        });

        allDataTypeConverters.registerDomainConverters(convertUtils, careService);
    }


    private static CareReportingMapper _instance;

    public static CareReportingMapper getInstance(Service careService) {
        if(_instance != null) {
            return _instance;
        }
        return createInstance(careService);
    }

    private synchronized static CareReportingMapper createInstance(Service careService) {
        if(_instance != null) {
            return _instance;
        }
        _instance = new CareReportingMapper(careService);
        return _instance;
    }

    public <T, U> T map(Class<T> type, Map<String, U> keyStore) {
        T newInstance;
        try {
            newInstance = type.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return map(newInstance, keyStore);
    }

    public <T, U> T map(T typeInstance, Map<String, U> keyStore) {
        for (Map.Entry<String, U> field : keyStore.entrySet()) {
            String key = field.getKey();
            U value = field.getValue();

            set(typeInstance, key, value);
        }

        return typeInstance;
    }

    private void set(Object object, String fieldName, Object fieldValue) {
        try {
            beanUtils.setProperty(object, fieldName, fieldValue);
        } catch (Exception ex) {
            logger.warn("Exception when setting " + fieldValue + " to " + fieldName + " Exception Details: " + ex);
        }
    }
}