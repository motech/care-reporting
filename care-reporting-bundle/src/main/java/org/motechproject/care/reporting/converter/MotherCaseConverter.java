package org.motechproject.care.reporting.converter;

import org.apache.commons.beanutils.converters.AbstractConverter;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MotherCaseConverter extends AbstractConverter {
    private Service careService;

    @Autowired
    public MotherCaseConverter(Service careService) {
        super(null);
        this.careService = careService;
    }

    @Override
    protected Object convertToType(Class type, Object value) throws Throwable {
        return careService.getOrCreateMotherCase((String) value);
    }

    @Override
    protected Class getDefaultType() {
        return MotherCase.class;
    }
}
