package org.motechproject.care.reporting.parser;

import org.apache.commons.collections.CollectionUtils;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.HashMap;
import java.util.Map;

public class MotherInfoParser {

    private final InfoParser infoParser = new InfoParser();

    public Map<String, String> parse(CommcareForm commcareForm) {
        FormValueElement form = commcareForm.getForm();

        Map<String, String> motherInfo = infoParser.parse(form);

        motherInfo.putAll(parseCaseInfo(commcareForm));
        return motherInfo;

    }

    private Map<String, String> parseCaseInfo(CommcareForm commcareForm) {
        Map<String, String> caseInfo = new HashMap<>();

        FormValueElement motherCase = (FormValueElement) CollectionUtils.get(commcareForm.getForm().getSubElements().get("case"), 0);
        final String caseId = motherCase.getAttributes().get("case_id");
        final String dateModified = motherCase.getAttributes().get("date_modified");
        caseInfo.put("caseId", caseId);
        caseInfo.put("dateModified", dateModified);

        return caseInfo;
    }
}

