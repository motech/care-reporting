package org.motechproject.care.reporting.parser;

import org.apache.commons.collections.CollectionUtils;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class MotherInfoParser {

    private final InfoParser infoParser = new InfoParser();

    public Map<String, String> parse(CommcareForm commcareForm) {
        FormValueElement form = commcareForm.getForm();

        Map<String, String> caseMap = parseCaseInfo(commcareForm);
        Map<String, String> motherInfo = new HashMap<>(caseMap);

        infoParser.setRestrictedElements(asList("case", "child_info"));
        Map<String, String> motherMap = infoParser.parse(form, true);

        motherInfo.putAll(motherMap);
        return motherInfo;
    }

    private Map<String, String> parseCaseInfo(CommcareForm commcareForm) {
        Map<String, String> caseInfo = new HashMap<>();

        FormValueElement motherCase = (FormValueElement) CollectionUtils.get(commcareForm.getForm().getSubElements().get("case"), 0);
        final String caseId = motherCase.getAttributes().get("case_id");
        final String dateModified = motherCase.getAttributes().get("date_modified");
        caseInfo.put("caseId", caseId);
        caseInfo.put("dateModified", dateModified);
        caseInfo.putAll(infoParser.parse(motherCase, true));

        return caseInfo;
    }
}

