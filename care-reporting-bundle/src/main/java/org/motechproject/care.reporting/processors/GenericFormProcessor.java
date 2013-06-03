package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.domain.CommcareForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericFormProcessor implements FormProcessor {
    @Autowired
    private Service service;

    @Override
    public void process(CommcareForm commcareForm) {
        new GenericFormProcessorWorker(service).process(commcareForm);
    }
}
