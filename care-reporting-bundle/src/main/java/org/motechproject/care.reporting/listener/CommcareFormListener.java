package org.motechproject.care.reporting.listener;

import org.motechproject.care.reporting.processors.FormProcessor;
import org.motechproject.commcare.builder.CommcareFormBuilder;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.events.constants.EventSubjects;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommcareFormListener {

    @Autowired
    FormProcessor formProcessor;

    @MotechListener(subjects = EventSubjects.FORMS_EVENT)
    public void handleEvent(MotechEvent event) {
        CommcareForm form = new CommcareFormBuilder().buildFrom(event);
        formProcessor.process(form);
    }
}