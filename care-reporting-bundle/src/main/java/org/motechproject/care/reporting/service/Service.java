package org.motechproject.care.reporting.service;

import org.motechproject.care.reporting.domain.SelfUpdatable;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;

import java.util.List;

public interface Service {
    MotherCase getOrCreateMotherCase(String caseId);

    MotherCase getMotherCase(String caseId);

    ChildCase getOrCreateChildCase(String caseId);

    ChildCase getChildCase(String caseId);

    Flw getOrCreateFlw(String flwId);

    FlwGroup getOrCreateGroup(String groupId);

    <T> Integer save(T instance);

    <T> Integer save(T instance, boolean ignoreUniqueConstraint);

    <T extends SelfUpdatable<T>> void saveOrUpdateByExternalPrimaryKey(T entity);

    <T extends SelfUpdatable<T>> void saveOrUpdateAllByExternalPrimaryKey(Class clazz, List<T> instances);

    <T> T getOrCreateNew(Class<T> type, String fieldName, String value);

    <T> T get(Class<T> type, String fieldName, String value);

    <T> void update(T entity);
}
