package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.care.reporting.parser.*;
import org.motechproject.care.reporting.service.MapperService;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.domain.CommcareForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.motechproject.care.reporting.parser.PostProcessor.COPY_CASE_ID_AS_CHILD_CASE_POST_PROCESSOR;
import static org.motechproject.care.reporting.parser.PostProcessor.FORM_COPY_USER_ID_AS_FLW;
import static org.motechproject.care.reporting.parser.PostProcessor.Utils.applyPostProcessors;
import static org.motechproject.care.reporting.processors.ComputedFieldsProcessor.Utils.applyComputedFields;

@Component
public class ChildFormProcessor {

    public static final List<PostProcessor> CHILD_CASE_POST_PROCESSORS = new ArrayList<PostProcessor>() {{
        add(new ClosedFormPostProcessor());
        add(COPY_CASE_ID_AS_CHILD_CASE_POST_PROCESSOR);
        add(FORM_COPY_USER_ID_AS_FLW);
    }};

    private List<ComputedFieldsProcessor> CHILD_FORM_COMPUTED_FIELDS = new ArrayList<>();

    private MapperService mapperService;

    @Autowired
    public ChildFormProcessor(Service service, MapperService mapperService) {
        this.mapperService = mapperService;

        CHILD_FORM_COMPUTED_FIELDS.add(new ComputeDeliveryOffsetForChild(service));
    }

    List<Map<String, String>> parseChildForms(CommcareForm commcareForm) {
        InfoParser infoParser = mapperService.getFormInfoParser(namespace(commcareForm), appVersion(commcareForm), FormSegment.CHILD);
        InfoParser metaDataInfoParser = mapperService.getFormInfoParser(
                namespace(commcareForm), appVersion(commcareForm), FormSegment.METADATA);
        final Map<String, String> metadata = new MetaInfoParser(metaDataInfoParser).parse(commcareForm);

        List<Map<String, String>> childDetails = new ChildCaseParser(infoParser).parse(commcareForm);
        for (final Map<String, String> childDetail : childDetails) {
            childDetail.putAll(metadata);

            applyPostProcessors(CHILD_CASE_POST_PROCESSORS, childDetail);
            applyComputedFields(CHILD_FORM_COMPUTED_FIELDS, childDetail);
        }
        return childDetails;
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
