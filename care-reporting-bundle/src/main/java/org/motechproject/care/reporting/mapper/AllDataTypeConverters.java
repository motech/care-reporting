package org.motechproject.care.reporting.mapper;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.motechproject.care.reporting.converter.ChildCaseConverter;
import org.motechproject.care.reporting.converter.FlwConverter;
import org.motechproject.care.reporting.converter.FlwGroupConverter;
import org.motechproject.care.reporting.converter.MotherCaseConverter;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.care.reporting.utils.CareDateConverter;
import org.motechproject.care.reporting.utils.CareTypeConverter;

import java.math.BigDecimal;
import java.util.Date;

public class AllDataTypeConverters {

    public void registerBaseConverters(ConvertUtilsBean convertUtilsBean, String[] allowedDateFormats) {
        convertUtilsBean.register(new CareDateConverter(allowedDateFormats), Date.class);
        registerPrimitive(convertUtilsBean, Integer.class);
        registerPrimitive(convertUtilsBean, Short.class);
        registerPrimitive(convertUtilsBean, Boolean.class);
        registerPrimitive(convertUtilsBean, BigDecimal.class);
    }

    private void registerPrimitive(ConvertUtilsBean convertUtilsBean, Class typeToConvert) {
        convertUtilsBean.register(new CareTypeConverter(typeToConvert), typeToConvert);
    }

    public void registerDomainConverters(ConvertUtilsBean convertUtils, Service careService) {
        convertUtils.register(new FlwConverter(careService), Flw.class);
        convertUtils.register(new FlwGroupConverter(careService), FlwGroup.class);
        convertUtils.register(new MotherCaseConverter(careService), MotherCase.class);
        convertUtils.register(new ChildCaseConverter(careService), ChildCase.class);
    }
}
