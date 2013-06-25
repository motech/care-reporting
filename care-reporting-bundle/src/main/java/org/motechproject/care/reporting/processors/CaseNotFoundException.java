package org.motechproject.care.reporting.processors;

public class CaseNotFoundException extends RuntimeException {
    public CaseNotFoundException(String caseId) {
        super(String.format("Cannot find case %s to close", caseId));
    }
}
