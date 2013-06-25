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
        InfoParser metaDataInfoParser = mapperService.getFormInfoParser(namespace(commcareForm), version(commcareForm), FormSegment.METADATA);
        Map<String, String> metadata = new MetaInfoParser(metaDataInfoParser).parse(commcareForm);

        Map<String, String> motherInfo = new HashMap<>(metadata);
        InfoParser motherInfoParser = mapperService.getFormInfoParser(namespace(commcareForm), version(commcareForm), FormSegment.MOTHER);
        motherInfo.putAll(new MotherInfoParser(motherInfoParser).parse(commcareForm));

        applyPostProcessors(MOTHER_FORM_POST_PROCESSORS, motherInfo);

        Class<?> motherForm = FormFactory.getForm(namespace(commcareForm), CaseType.MOTHER);
        Object formObject = careReportingMapper.map(motherInfo, motherForm);

        saveForm((Serializable) formObject, motherForm);
        return (Serializable) formObject;
    }

    private void saveForm(Serializable form, Class<?> type) {
        logger.info(String.format("Started processing form %s", form));
        service.save(type.cast(form), true);
        logger.info(String.format("Finished processing form %s", form));
    }

    private String namespace(CommcareForm commcareForm) {
        return attribute(commcareForm, "xmlns");
    }

    private String version(CommcareForm commcareForm) {
        return commcareForm.getVersion();
    }

    private String attribute(CommcareForm commcareForm, String name) {
        return commcareForm.getForm().getAttributes().get(name);
    }
}
