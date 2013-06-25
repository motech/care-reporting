package org.motechproject.care.reporting.converter;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ConverterBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (FlwConverter.class.equals(bean.getClass()))
            BeanUtilsBean.getInstance().getConvertUtils().register((FlwConverter) bean, Flw.class);
        if (FlwGroupConverter.class.equals(bean.getClass()))
            ConvertUtils.register((FlwGroupConverter) bean, FlwGroup.class);
        if (MotherCaseConverter.class.equals(bean.getClass()))
            ConvertUtils.register((MotherCaseConverter) bean, MotherCase.class);
        if (ChildCaseConverter.class.equals(bean.getClass()))
            ConvertUtils.register((ChildCaseConverter) bean, ChildCase.class);
        return bean;
    }
}
