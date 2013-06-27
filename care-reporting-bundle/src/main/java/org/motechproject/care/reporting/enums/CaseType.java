package org.motechproject.care.reporting.enums;

public enum CaseType {
    MOTHER("cc_bihar_pregnancy"),
    CHILD("cc_bihar_newborn");

    private final String type;

    CaseType(String type) {
        this.type = type;
    }

    public static CaseType getType(String type){
          for(CaseType caseType: CaseType.values()){
            if(caseType.type.equals(type))
                return caseType;
        }
        throw new IllegalArgumentException(String.format("Cannot find CaseType for value: %s", type));
    }
}
