package org.motechproject.care.reporting.processors;

import org.motechproject.commcare.domain.CommcareForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormProcessor {

    private MotherFormProcessorWorker motherFormProcessorWorker;
    private ChildFormProcessorWorker childFormProcessorWorker;

    @Autowired
    public FormProcessor(MotherFormProcessorWorker motherFormProcessorWorker, ChildFormProcessorWorker childFormProcessorWorker) {
        this.motherFormProcessorWorker = motherFormProcessorWorker;
        this.childFormProcessorWorker = childFormProcessorWorker;
    }

    public void process(CommcareForm commcareForm) {
        motherFormProcessorWorker.parseMotherForm(commcareForm);
        childFormProcessorWorker.parseChildForms(commcareForm);
    }
}
