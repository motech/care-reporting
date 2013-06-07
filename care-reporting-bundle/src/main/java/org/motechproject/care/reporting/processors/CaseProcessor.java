package org.motechproject.care.reporting.processors;

import org.motechproject.commcare.events.CaseEvent;

public interface CaseProcessor {
    void process(CaseEvent caseEvent);
}
