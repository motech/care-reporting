package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.care.reporting.parser.ChildInfoParser;
import org.motechproject.care.reporting.parser.ClosedFormPostProcessor;
import org.motechproject.care.reporting.parser.InfoParser;
import org.motechproject.care.reporting.parser.MetaInfoParser;
import org.motechproject.care.reporting.parser.PostProcessor;
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

    public static final List<PostProcessor> CHILD_CASE_MANY_TO_MANY_POST_PROCESSORS = new ArrayList<PostProcessor>() {{
        add(new ClosedFormPostProcessor());
        add(FORM_COPY_USER_ID_AS_FLW);
    }};

    private List<ComputedFieldsProcessor> CHILD_FORM_COMPUTED_FIELDS = new ArrayList<>();

    private Service service;
    private MapperService mapperService;

    @Autowired
    public ChildFormProcessor(Service service, MapperService mapperService) {
        this.service = service;
        this.mapperService = mapperService;

        CHILD_FORM_COMPUTED_FIELDS.add(new ComputeDeliveryOffsetForChild(this.service));
    }

    List<Map<String, String>> parseChildForms(CommcareForm commcareForm) {
        InfoParser infoParser = mapperService.getFormInfoParser(
                namespace(commcareForm), appVersion(commcareForm), FormSegment.CHILD);
        final Map<String, String> metadata = getMetadata(commcareForm);

        List<Map<String, String>> childDetails = new ChildInfoParser(infoParser).parse(commcareForm);
        for (final Map<String, String> childDetail : childDetails) {
            childDetail.putAll(metadata);

            applyPostProcessors(CHILD_CASE_POST_PROCESSORS, childDetail);
            applyComputedFields(CHILD_FORM_COMPUTED_FIELDS, childDetail);
        }
        return childDetails;
    }

    List<Map<String, String>> parseChildManyToManyForms(CommcareForm commcareForm) {
        InfoParser infoParser = mapperService.getFormInfoParser(
                namespace(commcareForm), appVersion(commcareForm), FormSegment.CHILD);
        final Map<String, String> metadata = getMetadata(commcareForm);

        List<Map<String, String>> childDetails = new ChildInfoParser(infoParser).parse(commcareForm);
        for (final Map<String, String> childDetail : childDetails) {
            childDetail.putAll(metadata);

            applyPostProcessors(CHILD_CASE_MANY_TO_MANY_POST_PROCESSORS, childDetail);
            parseAndAssignChildCaseId(childDetail);

            applyComputedFields(CHILD_FORM_COMPUTED_FIELDS, childDetail);
        }
        return childDetails;
    }

    private void parseAndAssignChildCaseId(Map<String, String> childDetail) {
        if (childDetail.containsKey("caseid")) {
            final ChildCase childCase = service.getOrCreateChildCase(childDetail.get("caseid"));
            service.saveOrUpdateAllByExternalPrimaryKey(
                    ChildCase.class, new ArrayList<ChildCase>() {{ add(childCase); }});
            childDetail.put("caseId", childDetail.get("caseid"));
            childDetail.put("childCase", Integer.toString(childCase.getId()));
        }
    }

    private Map<String, String> getMetadata(CommcareForm commcareForm) {
        InfoParser metaDataInfoParser = mapperService.getFormInfoParser(
                namespace(commcareForm), appVersion(commcareForm), FormSegment.METADATA);

        return new MetaInfoParser(metaDataInfoParser).parse(commcareForm);
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
