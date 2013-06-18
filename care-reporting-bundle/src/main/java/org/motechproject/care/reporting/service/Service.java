package org.motechproject.care.reporting.service;

import org.motechproject.care.reporting.domain.SelfUpdatable;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;

import java.util.List;

public interface Service {
    MotherCase getMotherCase(String caseId);

    Flw getFlw(String flwId);

    <T> Integer save(T instance);

    ChildCase getChildCase(String caseId);

    <T extends SelfUpdatable<T>> void saveOrUpdateByExternalPrimaryKey(T entity);

    <T extends SelfUpdatable<T>> void saveOrUpdateAllByExternalPrimaryKey(Class clazz, List<T> instances);

    FlwGroup getGroup(String groupId);

    <T> T get(Class<T> type, String fieldName, String value);
}
