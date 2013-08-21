package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.care.reporting.parser.*;
import org.motechproject.care.reporting.service.MapperService;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.domain.CommcareForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.motechproject.care.reporting.parser.PostProcessor.COPY_CASE_ID_AS_MOTHER_CASE_POST_PROCESSOR;
import static org.motechproject.care.reporting.parser.PostProcessor.FORM_COPY_USER_ID_AS_FLW;
import static org.motechproject.care.reporting.parser.PostProcessor.Utils.applyPostProcessors;
import static org.motechproject.care.reporting.processors.ComputedFieldsProcessor.Utils.applyComputedFields;

@Component
public class MotherFormProcessor {

    private static List<PostProcessor> MOTHER_FORM_POST_PROCESSORS = new ArrayList<PostProcessor>() {{
        add(new ClosedFormPostProcessor());
        add(COPY_CASE_ID_AS_MOTHER_CASE_POST_PROCESSOR);
        add(FORM_COPY_USER_ID_AS_FLW);
    }};

    private List<ComputedFieldsProcessor> MOTHER_FORM_COMPUTED_FIELDS = new ArrayList<>();

    private MapperService mapperService;

    @Autowired
    public MotherFormProcessor(Service service, MapperService mapperService) {
        this.mapperService = mapperService;

        MOTHER_FORM_COMPUTED_FIELDS.add(new ComputeDeliveryOffsetForMother(service));
    }

    public Map<String, String> parseMotherForm(CommcareForm commcareForm) {
        InfoParser metaDataInfoParser = mapperService.getFormInfoParser(namespace(commcareForm), appVersion(commcareForm), FormSegment.METADATA);
        Map<String, String> metadata = new MetaInfoParser(metaDataInfoParser).parse(commcareForm);

        Map<String, String> motherInfo = new HashMap<>();
        motherInfo.putAll(metadata);
        InfoParser motherInfoParser = mapperService.getFormInfoParser(namespace(commcareForm), appVersion(commcareForm), FormSegment.MOTHER);
        Map<String, String> formFields = new MotherInfoParser(motherInfoParser).parse(commcareForm);
        if (formFields == null) {
            return null;
        }

        motherInfo.putAll(formFields);

        applyPostProcessors(MOTHER_FORM_POST_PROCESSORS, motherInfo);

        applyComputedFields(MOTHER_FORM_COMPUTED_FIELDS, motherInfo);

        return motherInfo;
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
