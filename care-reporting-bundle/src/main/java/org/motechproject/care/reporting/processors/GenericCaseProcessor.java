package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.service.MapperService;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.events.CaseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericCaseProcessor implements CaseProcessor {
    @Autowired
    private Service service;

    @Autowired
    private MapperService mapperService;

    @Override
    public void process(CaseEvent caseEvent) {
        new GenericCaseProcessorWorker(service, mapperService).process(caseEvent);
    }
}


