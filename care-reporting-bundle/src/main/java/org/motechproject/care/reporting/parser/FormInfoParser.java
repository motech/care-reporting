package org.motechproject.care.reporting.parser;

import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class FormInfoParser extends CaseInfoParser{

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
        Map<String, String> infoMap = parseCaseInfo(caseElement);
        infoMap.putAll(infoParser.parse(startElement, true));
        return infoMap;
    }

    protected void logCaseNotFoundEvent(CommcareForm commcareForm) {
        String ignoreMessage = String.format("Motech id is empty for %s form(%s). Ignoring this form.", formSegment, commcareForm.getId());
        if(infoParser.isSkipMappingIfCaseNotFound()) {
            logger.info(ignoreMessage);
            return;
        }
        logger.error(ignoreMessage);
    }

    protected Map<String, String> parseCaseInfo(FormValueElement caseElement) {
        Map<String, String> caseInfo = new HashMap<>();

        final String caseId = caseElement.getAttributes().get("case_id");
        final String dateModified = caseElement.getAttributes().get("date_modified");
        caseInfo.put("caseId", caseId);
        caseInfo.put("dateModified", dateModified);
        caseInfo.putAll(infoParser.parse(caseElement, true));

        return caseInfo;
    }
}

