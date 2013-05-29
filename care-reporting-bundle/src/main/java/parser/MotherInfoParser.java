package parser;

import org.apache.commons.collections.CollectionUtils;
import org.motechproject.care.reporting.utils.StringUtils;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MotherInfoParser {

    public Map<String, String> parse(CommcareForm commcareForm) {
        Map<String, String> motherInfo = new HashMap<>();
        Map<String, Collection<FormValueElement>> subElementsMap = commcareForm.getForm().getSubElements().asMap();
        for (Map.Entry<String, Collection<FormValueElement>> subElement : subElementsMap.entrySet()) {
            String key = StringUtils.toCamelCase(subElement.getKey());
            Collection<FormValueElement> subElementValue = subElement.getValue();
            FormValueElement fieldValue = (FormValueElement) CollectionUtils.get(subElementValue, 0);
            motherInfo.put(key, fieldValue.getValue());
        }

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

