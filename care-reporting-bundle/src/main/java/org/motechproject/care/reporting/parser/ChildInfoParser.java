package org.motechproject.care.reporting.parser;

import org.apache.commons.collections.CollectionUtils;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class ChildInfoParser extends BaseInfoParser{

    public ChildInfoParser(InfoParser infoParser) {
        super(infoParser);
        infoParser.setRestrictedElements(asList("case"));
    }

    public List<Map<String, String>> parse(CommcareForm commcareForm){

        List<Map<String, String>> childInfoList = new ArrayList<>();
        List<FormValueElement> child_infos = commcareForm.getForm().getAllElements("child_info");


        for(FormValueElement child_info : child_infos){
            Map<String, String> caseInfo = parseCaseInfo(child_info);

            Map<String, String> childInfoMap = infoParser.parse(child_info, true);
            Map<String, String> childInfo = new HashMap<>();

            childInfo.putAll(caseInfo);
            childInfo.putAll(childInfoMap);

            childInfoList.add(childInfo);
        }

        return childInfoList;
    }

    private Map<String, String> parseCaseInfo(FormValueElement formValueElement) {
        Map<String, String> caseInfo = new HashMap<>();

        FormValueElement childCase = (FormValueElement) CollectionUtils.get(formValueElement.getSubElements().get("case"), 0);
        final String caseId = childCase.getAttributes().get("case_id");
        final String dateModified = childCase.getAttributes().get("date_modified");
        caseInfo.put("caseId", caseId);
        caseInfo.put("dateModified", dateModified);
        caseInfo.putAll(infoParser.parse(childCase, true));

        return caseInfo;
    }
}
