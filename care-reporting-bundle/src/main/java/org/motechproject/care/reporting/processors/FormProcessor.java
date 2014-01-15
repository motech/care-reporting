package org.motechproject.care.reporting.processors;

import org.apache.commons.lang.StringUtils;
import org.motechproject.care.reporting.parser.FormCaseType;
import org.motechproject.care.reporting.parser.FormInfoParser;
import org.motechproject.care.reporting.service.MapperService;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.care.reporting.utils.FormFieldSplitter;
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
    private MapperService mapperService;


    private static final String FORM_NAME_ATTRIBUTE = "name";
    private static final String FORM_XMLNS_ATTRIBUTE = "xmlns";
    private static final String FORM_VERSION_ATTRIBUTE = "appVersion";

    @Autowired
    public FormProcessor(MotherFormProcessor motherFormProcessor, ChildFormProcessor childFormProcessor, Service service, MapperService mapperService) {
        this.motherFormProcessor = motherFormProcessor;
        this.childFormProcessor = childFormProcessor;
        this.service = service;
        this.mapperService = mapperService;
    }

    public void process(CommcareForm commcareForm) {

        final Map<String, String> formAttributes = commcareForm.getForm().getAttributes();
        String formName = formAttributes.get(FORM_NAME_ATTRIBUTE);
        String xmlns = formAttributes.get(FORM_XMLNS_ATTRIBUTE);
        logger.info(String.format("Received form. id: %s, type: %s; xmlns: %s;", commcareForm.getId(), formName, xmlns));

        String appVersion = commcareForm.getMetadata().get(FORM_VERSION_ATTRIBUTE);
        if(StringUtils.isEmpty(appVersion) || mapperService.getExclusionAppversionList().contains(appVersion)) {
            logger.info(String.format("[Excluded App version] Ignoring the form, id: %s with appversion %s", commcareForm.getId(), appVersion));
            return;
        }

        Map<String, String> motherForm = motherFormProcessor.parseMotherForm(commcareForm);
        List<Map<String, String>> childForms = childFormProcessor.parseChildForms(commcareForm);

        if (FormFieldSplitter.isNamespaceSupported(xmlns)) {
            Map<String, List<Map<String, String>>> allFields = FormFieldSplitter.splitMotherAndChildrenFields(
                    xmlns, motherForm, childForms);

            motherForm = allFields.get("mother").get(0);
            childForms = allFields.get("child");
        }

        service.processAndSaveForms(motherForm, childForms);
        logger.info(String.format("Finished processing form. id: %s, type: %s;", commcareForm.getId(), formName));
    }
}
