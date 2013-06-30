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

import static org.motechproject.care.reporting.parser.PostProcessor.COPY_CASE_ID_AS_CHILD_CASE_POST_PROCESSOR;
import static org.motechproject.care.reporting.parser.PostProcessor.FORM_COPY_USER_ID_AS_FLW;
import static org.motechproject.care.reporting.parser.PostProcessor.Utils.applyPostProcessors;

@Component
public class ChildFormProcessor {

    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");
    public static final List<PostProcessor> CHILD_CASE_POST_PROCESSORS = new ArrayList<PostProcessor>() {{
        add(new ClosedFormPostProcessor());
        add(COPY_CASE_ID_AS_CHILD_CASE_POST_PROCESSOR);
        add(FORM_COPY_USER_ID_AS_FLW);
    }};

    private Service service;
    private MapperService mapperService;
    private CareReportingMapper careReportingMapper;

    @Autowired
    public ChildFormProcessor(Service service, MapperService mapperService, CareReportingMapper careReportingMapper) {
        this.service = service;
        this.mapperService = mapperService;
        this.careReportingMapper = careReportingMapper;
    }

    List<Serializable> parseChildForms(CommcareForm commcareForm) {
        Class<?> childForm = FormFactory.getForm(namespace(commcareForm), CaseType.CHILD);
        if (null == childForm) {
            return new ArrayList<>();
        }

        logger.info(String.format("Processing form %s", childForm));

        List<Serializable> childForms = new ArrayList<>();
        InfoParser infoParser = mapperService.getFormInfoParser(namespace(commcareForm), appVersion(commcareForm), FormSegment.CHILD);
        List<Map<String, String>> childDetails = new ChildInfoParser(infoParser).parse(commcareForm);

        InfoParser metaDataInfoParser = mapperService.getFormInfoParser(namespace(commcareForm), appVersion(commcareForm), FormSegment.METADATA);
        final Map<String, String> metadata = new MetaInfoParser(metaDataInfoParser).parse(commcareForm);
        String instanceId = metadata.get("instanceId");

        for (final Map<String, String> childDetail : childDetails) {
            if (formExists(childForm, instanceId, childDetail.get("caseId")))
                continue;

            Map<String, String> childInfo = new HashMap<>(metadata);
            childInfo.putAll(childDetail);

            applyPostProcessors(CHILD_CASE_POST_PROCESSORS, childInfo);

            Serializable formObject = (Serializable) careReportingMapper.map(childInfo, childForm);
            childForms.add(formObject);
        }
        saveForm(childForms, childForm);
        return childForms;
    }

    private boolean formExists(Class<?> type, String instanceId, String caseId) {
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("instanceId", instanceId);
        fieldMap.put("cc.caseId", caseId);

        Map<String, String> aliasMap = new HashMap<>();
        aliasMap.put("childCase", "cc");

        Object existingForm = service.get(type, fieldMap, aliasMap);
        if (existingForm != null) {
            logger.warn(String.format("Cannot save Form: %s. Form with same instanceId (%s) and caseId %s already exists: %s", type, instanceId, caseId, existingForm));
            return true;
        }
        return false;
    }

    private void saveForm(List<Serializable> forms, Class<?> type) {
        for (Serializable form : forms) {
            saveForm(form, type);
        }
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
