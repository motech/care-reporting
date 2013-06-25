package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.enums.CaseType;
import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.care.reporting.factory.FormFactory;
import org.motechproject.care.reporting.mapper.GenericMapper;
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
    protected Service service;
    protected MapperService mapperService;

    @Autowired
    public ChildFormProcessor(Service service, MapperService mapperService) {
        this.service = service;
        this.mapperService = mapperService;
    }

    List<Serializable> parseChildForms(CommcareForm commcareForm) {
        Class<?> childForm = FormFactory.getForm(namespace(commcareForm), CaseType.CHILD);
        if (null == childForm)
            return new ArrayList<>();

        List<Serializable> childForms = new ArrayList<>();
        InfoParser infoParser = mapperService.getFormInfoParser(namespace(commcareForm), version(commcareForm), FormSegment.CHILD);
        List<Map<String, String>> childDetails = new ChildInfoParser(infoParser).parse(commcareForm);

        InfoParser metaDataInfoParser = mapperService.getFormInfoParser(namespace(commcareForm), version(commcareForm), FormSegment.METADATA);
        Map<String, String> metadata = new MetaInfoParser(metaDataInfoParser).parse(commcareForm);

        for (Map<String, String> childDetail : childDetails) {

            Map<String, String> childInfo = new HashMap<>(metadata);
            childInfo.putAll(childDetail);

            applyPostProcessors(CHILD_CASE_POST_PROCESSORS, childInfo);

            Serializable formObject = (Serializable) new GenericMapper().map(childInfo, childForm);
            childForms.add(formObject);
        }
        saveForm(childForms, childForm);
        return childForms;
    }

    private void saveForm(List<Serializable> forms, Class<?> type) {
        for (Serializable form : forms) {
            saveForm(form, type);
        }
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
