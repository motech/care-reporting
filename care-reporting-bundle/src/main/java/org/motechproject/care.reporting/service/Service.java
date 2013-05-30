package org.motechproject.care.reporting.service;

import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;

public interface Service {
    MotherCase getMotherCase(String caseId);

    Flw getFlw(String flwId);

    <T> Integer save(T instance);
}
