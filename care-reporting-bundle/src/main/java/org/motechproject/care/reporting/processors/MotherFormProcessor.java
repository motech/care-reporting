package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.enums.CaseType;
import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.care.reporting.factory.FormFactory;
import org.motechproject.care.reporting.mapper.CareReportingMapper;
import org.motechproject.care.reporting.parser.*;
import org.motechproject.care.reporting.service.MapperService;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.domain.CommcareForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.motechproject.care.reporting.parser.PostProcessor.COPY_CASE_ID_AS_MOTHER_CASE_POST_PROCESSOR;
import static org.motechproject.care.reporting.parser.PostProcessor.FORM_COPY_USER_ID_AS_FLW;
import static org.motechproject.care.reporting.parser.PostProcessor.Utils.applyPostProcessors;

@Component
public class MotherFormProcessor {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");
    private static List<PostProcessor> MOTHER_FORM_POST_PROCESSORS = new ArrayList<PostProcessor>() {{
        add(new ClosedFormPostProcessor());
        add(COPY_CASE_ID_AS_MOTHER_CASE_POST_PROCESSOR);
        add(FORM_COPY_USER_ID_AS_FLW);
    }};

    private Service service;
    private MapperService mapperService;
    private CareReportingMapper careReportingMapper;

    @Autowired
    public MotherFormProcessor(Service service, MapperService mapperService, CareReportingMapper careReportingMapper) {
        this.service = service;
        this.mapperService = mapperService;
        this.careReportingMapper = careReportingMapper;
    }

    public Serializable parseMotherForm(CommcareForm commcareForm) {
        Class<?> motherForm = FormFactory.getForm(namespace(commcareForm), CaseType.MOTHER);
        logger.info(String.format("Processing Form %s", motherForm));

        Map<String, String> motherInfo = new HashMap<>();

        InfoParser metaDataInfoParser = mapperService.getFormInfoParser(namespace(commcareForm), appVersion(commcareForm), FormSegment.METADATA);
        Map<String, String> metadata = new MetaInfoParser(metaDataInfoParser).parse(commcareForm);
        if (formExists(motherForm, metadata.get("instanceId"))) {
            return null;
        }
        motherInfo.putAll(metadata);

        InfoParser motherInfoParser = mapperService.getFormInfoParser(namespace(commcareForm), appVersion(commcareForm), FormSegment.MOTHER);
        Map<String, String> formFields = new MotherInfoParser(motherInfoParser).parse(commcareForm);
        if(formFields == null) {
            return null;
        }

        motherInfo.putAll(formFields);

        applyPostProcessors(MOTHER_FORM_POST_PROCESSORS, motherInfo);

        Object formObject = careReportingMapper.map(motherInfo, motherForm);

        saveForm((Serializable) formObject, motherForm);
        return (Serializable) formObject;
    }

    private boolean formExists(Class<?> type, String instanceId) {
        Object existingForm = service.get(type, "instanceId", instanceId);
        if (existingForm != null) {
            logger.warn(String.format("Cannot save Form: %s. Form with same instanceId (%s) already exists: %s", type, instanceId, existingForm));
            return true;
        }
        return false;
    }

    private void saveForm(Serializable form, Class<?> type) {
        service.save(type.cast(form));
    }

    private String namespace(CommcareForm commcareForm) {
        return attribute(commcareForm, "xmlns");
    }

    private String appVersion(CommcareForm commcareForm) {
        return commcareForm.getMetadata().get("appVersion");
    }

    private String attribute(CommcareForm commcareForm, String name) {
        return commcareForm.getForm().getAttributes().get(name);
    }
}
