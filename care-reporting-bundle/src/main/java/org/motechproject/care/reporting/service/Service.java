package org.motechproject.care.reporting.service;

import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;

import java.util.List;

public interface Service {
    MotherCase getMotherCase(String caseId);

    Flw getFlw(String flwId);

    <T> Integer save(T instance);

    ChildCase getChildCase(String caseId);

    <T> void saveOrUpdateAll(List<T> instances);
}
