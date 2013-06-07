package org.motechproject.care.reporting.listener;

import org.motechproject.care.reporting.processors.CaseProcessor;
import org.motechproject.care.reporting.processors.FormProcessor;
import org.motechproject.commcare.builder.CommcareFormBuilder;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.events.CaseEvent;
import org.motechproject.commcare.events.constants.EventSubjects;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommcareCaseListener {

    @Autowired
    CaseProcessor caseProcessor;

    @MotechListener(subjects = EventSubjects.CASE_EVENT)
    public void handleEvent(MotechEvent event) {
        CaseEvent caseEvent = new CaseEvent(event);
        caseProcessor.process(caseEvent);
    }
}