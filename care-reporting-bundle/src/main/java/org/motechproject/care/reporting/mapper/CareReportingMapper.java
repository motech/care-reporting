package org.motechproject.care.reporting.mapper;


import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.motechproject.care.reporting.converter.ChildCaseConverter;
import org.motechproject.care.reporting.converter.FlwConverter;
import org.motechproject.care.reporting.converter.FlwGroupConverter;
import org.motechproject.care.reporting.converter.MotherCaseConverter;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.CareDateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Component
public class CareReportingMapper {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");
    private BeanUtilsBean beanUtilsBean;

    @Autowired
    public CareReportingMapper(FlwConverter flwConverter, FlwGroupConverter flwGroupConverter, MotherCaseConverter motherCaseConverter, ChildCaseConverter childCaseConverter) {
        beanUtilsBean = BeanUtilsBean.getInstance();
        ConvertUtilsBean convertUtils = beanUtilsBean.getConvertUtils();

        convertUtils.register(new CareDateConverter(), Date.class);
        convertUtils.register(new IntegerConverter(null), Integer.class);
        convertUtils.register(new ShortConverter(null),Short.class);
        convertUtils.register(new BooleanConverter(null),Boolean.class);
        convertUtils.register(new BigDecimalConverter(null),BigDecimal.class);

        convertUtils.register(flwConverter, Flw.class);
        convertUtils.register(flwGroupConverter, FlwGroup.class);
        convertUtils.register(motherCaseConverter, MotherCase.class);
        convertUtils.register(childCaseConverter, ChildCase.class);
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

    private  <T, U> T map(Map<String, U> keyStore, T typeInstance) {
        for (Map.Entry<String, U> field : keyStore.entrySet()) {
            String key = field.getKey();
            U value = field.getValue();

            set(typeInstance, key, value);
        }

        return typeInstance;
    }

    public void set(Object object, String fieldName, Object fieldValue) {
        try {
            beanUtilsBean.setProperty(object, fieldName, fieldValue);
        } catch (Exception ex) {
            logger.warn("Exception when setting " + fieldValue + " to " + fieldName + " Exception Details: " + ex);
        }
    }
}