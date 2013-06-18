package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.service.MapperService;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.domain.CommcareForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericFormProcessor implements FormProcessor {
    private Service service;
    private MapperService mapperService;

    @Autowired
    public GenericFormProcessor(Service service, MapperService mapperService) {
        this.service = service;
        this.mapperService = mapperService;
    }

    @Override
    public void process(CommcareForm commcareForm) {
        new GenericFormProcessorWorker(service, mapperService).process(commcareForm);
    }
}
