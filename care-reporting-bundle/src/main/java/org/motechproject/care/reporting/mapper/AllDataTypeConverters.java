package org.motechproject.care.reporting.mapper;

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
import org.motechproject.care.reporting.service.Service;
import org.motechproject.care.reporting.utils.CareDateConverter;

import java.math.BigDecimal;
import java.util.Date;

public class AllDataTypeConverters {

    public void registerBaseConverters(ConvertUtilsBean convertUtilsBean, String[] allowedDateFormats) {
        convertUtilsBean.register(new CareDateConverter(allowedDateFormats), Date.class);
        convertUtilsBean.register(new IntegerConverter(null), Integer.class);
        convertUtilsBean.register(new ShortConverter(null), Short.class);
        convertUtilsBean.register(new BooleanConverter(null), Boolean.class);
        convertUtilsBean.register(new BigDecimalConverter(null), BigDecimal.class);
    }

    public void registerDomainConverters(ConvertUtilsBean convertUtils, Service careService) {
        convertUtils.register(new FlwConverter(careService), Flw.class);
        convertUtils.register(new FlwGroupConverter(careService), FlwGroup.class);
        convertUtils.register(new MotherCaseConverter(careService), MotherCase.class);
        convertUtils.register(new ChildCaseConverter(careService), ChildCase.class);
    }

}
