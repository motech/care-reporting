package org.motechproject.care.reporting.parser;

import org.apache.commons.lang.StringUtils;
import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class FormInfoParser extends BaseInfoParser {

    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    private FormSegment formSegment;

    public FormInfoParser(InfoParser infoParser, FormSegment formSegment){
        super(infoParser);
        this.formSegment = formSegment;
    }

    protected Map<String, String> parse(FormValueElement startElement, CommcareForm commcareForm) {
        FormValueElement caseElement = infoParser.getCaseElement(startElement);
        if(caseElement == null) {
            logCaseNotFoundEvent(commcareForm);
             return null;
        }
        Map<String, String> infoMap = parseCaseInfo(caseElement, commcareForm);
        infoMap.putAll(infoParser.parse(startElement, true));
        return infoMap;
    }

    private void logCaseNotFoundEvent(CommcareForm commcareForm) {
        String missingElementMessage = String.format("%s case element not found for form(%s). Ignoring this form.", formSegment, commcareForm.getId());
        if(infoParser.shouldReportMissingCaseElement()) {
            logger.error(missingElementMessage);
            return;
        }
        logger.info(missingElementMessage);
    }

    private Map<String, String> parseCaseInfo(FormValueElement caseElement, CommcareForm commcareForm) {
        Map<String, String> caseInfo = new HashMap<>();

        final String caseId = caseElement.getAttributes().get("case_id");

        if(StringUtils.isEmpty(caseId)) {
            throw new RuntimeException(String.format("Empty case id found in form(%s)", commcareForm.getId()));
        }

        final String dateModified = commcareForm.getReceivedOn();
        caseInfo.put("caseId", caseId);
        caseInfo.put("dateModified", dateModified);
        caseInfo.putAll(infoParser.parse(caseElement, true));

        return caseInfo;
    }
}

