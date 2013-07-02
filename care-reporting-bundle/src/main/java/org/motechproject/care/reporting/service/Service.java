package org.motechproject.care.reporting.service;

import org.motechproject.care.reporting.domain.SelfUpdatable;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;

import java.util.List;
import java.util.Map;

public interface Service {
    MotherCase getOrCreateMotherCase(String caseId);

    MotherCase getMotherCase(String caseId);

    ChildCase getOrCreateChildCase(String caseId);

    ChildCase getChildCase(String caseId);

    Flw getOrCreateFlw(String flwId);

    FlwGroup getOrCreateGroup(String groupId);

    <T> Integer save(T instance);

    <T extends SelfUpdatable<T>> void saveOrUpdateByExternalPrimaryKey(T entity);

    <T extends SelfUpdatable<T>> void saveOrUpdateAllByExternalPrimaryKey(Class clazz, List<T> instances);

    <T> T getOrCreateNew(Class<T> type, String fieldName, String value);

    <T> void update(T entity);

    <T> T get(Class<T> type, String fieldName, Object value);

    <T> T get(Class<T> type, Map<String, Object> fieldMap, Map<String, String> aliasMapping);

    void processAndSaveForms(Map<String, String> motherForm, List<Map<String,String>> childForms);

    <T extends SelfUpdatable<T>> T saveByExternalPrimaryKey(Class<T> entityClass, Map<String, String> values);

    void closeCase(Map<String, String> closeFields);
}
