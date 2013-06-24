package org.motechproject.care.reporting.converter;


import org.apache.commons.beanutils.converters.AbstractConverter;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChildCaseConverter extends AbstractConverter {
    private Service careService;

    @Autowired
    public ChildCaseConverter(Service careService) {
        super(null);
        this.careService = careService;
    }


    @Override
    protected Object convertToType(Class type, Object value) throws Throwable {
        return careService.getChildCase((String) value);
    }

    @Override
    protected Class getDefaultType() {
        return ChildCase.class;
    }
}
