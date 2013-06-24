package org.motechproject.care.reporting.processors;

import org.motechproject.commcare.domain.CommcareForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormProcessor {

    private MotherFormProcessor motherFormProcessor;
    private ChildFormProcessor childFormProcessor;

    @Autowired
    public FormProcessor(MotherFormProcessor motherFormProcessor, ChildFormProcessor childFormProcessor) {
        this.motherFormProcessor = motherFormProcessor;
        this.childFormProcessor = childFormProcessor;
    }

    public void process(CommcareForm commcareForm) {
        motherFormProcessor.parseMotherForm(commcareForm);
        childFormProcessor.parseChildForms(commcareForm);
    }
}
