package org.motechproject.care.reporting.enums;

public enum CaseType {
    MOTHER("cc_bihar_pregnancy", true),
    CHILD("cc_bihar_newborn", true),
    TASK("task", false);

    private final String type;
    private boolean shouldProcess;

    CaseType(String type, boolean shouldProcess) {
        this.type = type;
        this.shouldProcess = shouldProcess;
    }

    public static CaseType getType(String type){
          for(CaseType caseType: CaseType.values()){
            if(caseType.type.equals(type))
                return caseType;
        }
        throw new IllegalArgumentException(String.format("Cannot find CaseType for value: %s", type));
    }

    public boolean shouldProcess() {
       return shouldProcess;
    }
}
