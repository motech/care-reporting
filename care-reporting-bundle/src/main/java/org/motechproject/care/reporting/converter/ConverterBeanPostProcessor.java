package org.motechproject.care.reporting.converter;

import org.apache.commons.beanutils.ConvertUtils;
import org.motechproject.care.reporting.domain.dimension.Flw;
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
            ConvertUtils.register((FlwConverter) bean, Flw.class);
        if (MotherCaseConverter.class.equals(bean.getClass()))
            ConvertUtils.register((MotherCaseConverter) bean, MotherCase.class);
        return bean;
    }
}
