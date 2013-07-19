package org.motechproject.care.reporting.builder;

import org.motechproject.commcare.events.CaseEvent;

import java.util.HashMap;
import java.util.Map;

public class CaseEventBuilder{
    CaseEvent caseEvent;

    public CaseEventBuilder(String caseId){
        caseEvent = new CaseEvent(caseId);
        caseEvent.setFieldValues(new HashMap<String,String>());
    }

    public CaseEventBuilder with(String fieldName, String fieldValue){
        caseEvent.getFieldValues().put(fieldName, fieldValue);
        return this;
    }

    public CaseEventBuilder with(Map<String, String> fieldMap){
        caseEvent.getFieldValues().putAll(fieldMap);
        return this;
    }

    public CaseEventBuilder withUserId(String userId){
        caseEvent.setUserId(userId);
        return this;
    }

    public CaseEventBuilder withAction(String action){
        caseEvent.setAction(action);
        return this;
    }

    public CaseEventBuilder withDateModified(String dateModified){
        caseEvent.setDateModified(dateModified);
        return this;
    }

    public CaseEventBuilder withCaseType(String caseType){
        caseEvent.setCaseType(caseType);
        return this;
    }

    public CaseEventBuilder withCaseName(String caseName){
        caseEvent.setCaseName(caseName);
        return this;
    }


    public CaseEventBuilder withOwnerId(String ownerId){
        caseEvent.setOwnerId(ownerId);
        return this;
    }

    public CaseEventBuilder withApiKey(String key) {
        caseEvent.setApiKey(key);
        return this;
    }

    public CaseEventBuilder withHusbandName(String husbandName) {
        caseEvent.getFieldValues().put("husband_name", husbandName);
        return this;
    }

    public CaseEvent build(){
        return caseEvent;
    }

    public CaseEventBuilder withServerModifiedOn(String serverModifiedOn) {
        caseEvent.setServerModifiedOn(serverModifiedOn);
        return this;
    }
}
