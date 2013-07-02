package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.domain.CommcareForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FormProcessor {

    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    private MotherFormProcessor motherFormProcessor;
    private ChildFormProcessor childFormProcessor;
    private Service service;

    private static final String FORM_NAME_ATTRIBUTE = "name";
    private static final String FORM_XMLNS_ATTRIBUTE = "xmlns";

    @Autowired
    public FormProcessor(MotherFormProcessor motherFormProcessor, ChildFormProcessor childFormProcessor, Service service) {
        this.motherFormProcessor = motherFormProcessor;
        this.childFormProcessor = childFormProcessor;
        this.service = service;
    }

    public void process(CommcareForm commcareForm) {
        String formName = commcareForm.getForm().getAttributes().get(FORM_NAME_ATTRIBUTE);
        String xmlns = commcareForm.getForm().getAttributes().get(FORM_XMLNS_ATTRIBUTE);
        logger.info(String.format("Received form. id: %s, type: %s; xmlns: %s;", commcareForm.getId(), formName, xmlns));

        Map<String, String> motherForm = motherFormProcessor.parseMotherForm(commcareForm);
        List<Map<String, String>> childForms = childFormProcessor.parseChildForms(commcareForm);
        service.processAndSaveForms(motherForm, childForms);
        logger.info(String.format("Finished processing form. id: %s, type: %s;", commcareForm.getId(), formName));
    }
}
