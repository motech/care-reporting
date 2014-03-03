package org.motechproject.care.reporting.parser;

import org.apache.commons.lang.StringUtils;
import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormInfoParser extends BaseInfoParser {

    private static final Map<String, FormCaseType> namespaceToCaseType = new HashMap<String, FormCaseType>() {{
        // AWW Forms
        put("http://bihar.commcarehq.org/pregnancy/aww_reg_child", FormCaseType.AWW_MOTHER_AND_CHILD);
        put("http://bihar.commcarehq.org/pregnancy/aww_child_edit", FormCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_preschool_activities", FormCaseType.CHILD_MANY_TO_MANY);
        put("http://bihar.commcarehq.org/pregnancy/aww_migrate_out", FormCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_migrate_in", FormCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_growth_monitoring_1", FormCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_growth_monitoring_2", FormCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_child_thr", FormCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_update_vaccinations", FormCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_close", FormCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_mother_thr", FormCaseType.MOTHER_ONLY);
        // CCS Forms
        put("http://bihar.commcarehq.org/pregnancy/growth_monitoring", FormCaseType.CHILD_ONLY);
    }};

    protected static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");
    protected static final String NAMESPACE_ATTRIBUTE_NAME = "xmlns";

    private FormSegment formSegment;

    public FormInfoParser(InfoParser infoParser, FormSegment formSegment) {
        super(infoParser);
        this.formSegment = formSegment;
    }

    protected Map<String, String> parse(FormValueElement startElement, CommcareForm commcareForm) {
        String namespace = commcareForm.getForm().getAttributes().get(NAMESPACE_ATTRIBUTE_NAME);
        FormCaseType caseType = getCaseTypeFromNamespace(namespace);
        FormValueElement caseElement = startElement;
        if (!startElement.getElementName().equals("case") && caseType != FormCaseType.CHILD_MANY_TO_MANY) {
            caseElement = infoParser.getCaseElement(startElement);
        }

        if (caseElement == null) {
            logCaseNotFoundEvent(commcareForm);
            return null;
        }
        Map<String, String> infoMap = parseCaseInfo(caseType, caseElement, commcareForm);
        infoMap.putAll(extractHeaders(commcareForm));
        infoMap.putAll(infoParser.parse(startElement, true));
        return infoMap;
    }

    private void logCaseNotFoundEvent(CommcareForm commcareForm) {
        String missingElementMessage = String.format("%s case element not found for form(%s). Ignoring this form.", formSegment, commcareForm.getId());
        if (infoParser.shouldReportMissingCaseElement()) {
            logger.error(missingElementMessage);
            return;
        }
        logger.info(missingElementMessage);
    }

    protected Map<String, String> parseCaseInfo(FormCaseType caseType, FormValueElement caseElement,
                                                CommcareForm commcareForm) {
        Map<String, String> caseInfo = new HashMap<>();
        final String caseId = caseElement.getAttributes().get("case_id");

        if (caseType != FormCaseType.CHILD_MANY_TO_MANY) {
            if (StringUtils.isEmpty(caseId)) {
                throw new RuntimeException(String.format("Empty case id found in form(%s)", commcareForm.getId()));
            } else {
                caseInfo.put("caseId", caseId);
            }
        }

        final String dateModified = caseElement.getAttributes().get("date_modified");
        caseInfo.put("dateModified", dateModified);
        caseInfo.putAll(infoParser.parse(caseElement, true));

        return caseInfo;
    }

    public Map<String, String> extractHeaders(final CommcareForm commcareForm) {
        return new HashMap<String,String>(){{
            put("serverDateModified",commcareForm.getReceivedOn());
        }};
    }

    public static FormCaseType getCaseTypeFromNamespace(String namespace) {
        if (namespaceToCaseType.containsKey(namespace)) {
            return namespaceToCaseType.get(namespace);
        }

        return null;
    }
}

