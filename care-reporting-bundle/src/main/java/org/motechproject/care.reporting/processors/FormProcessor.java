package org.motechproject.care.reporting.processors;

import org.motechproject.commcare.domain.CommcareForm;

public interface FormProcessor {
    void process(CommcareForm commcareForm);
}
