package org.motechproject.care.reporting.parser;

import org.motechproject.commcare.events.CaseEvent;

import java.util.HashMap;
import java.util.Map;

public class CaseInfoParser{

    InfoParser parser = new InfoParser();

    public Map<String, String> parse(CaseEvent caseEvent){
        final HashMap<String, String> map = new HashMap<>();

        if(null == caseEvent) {
            return map;
        }

        map.put("caseId",caseEvent.getCaseId());
        map.put("userId",caseEvent.getUserId());
        map.put("apiKey",caseEvent.getApiKey());
        map.put("dateModified",caseEvent.getDateModified());
        map.put("action",caseEvent.getAction());
        map.put("caseType",caseEvent.getCaseType());
        map.put("caseName", caseEvent.getCaseName());
        map.put("ownerId",caseEvent.getOwnerId());

        Map<String, String> fieldValues = caseEvent.getFieldValues();

        if(null == fieldValues)
            return map;

        Map<String, String> fieldValuesMap = (Map) parser.parse(fieldValues);
        map.putAll(fieldValuesMap);

        return map;
    }
}
