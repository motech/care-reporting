package org.motechproject.care.reporting.repository;

import org.motechproject.care.reporting.domain.dimension.FlwGroup;

import java.util.List;

public interface Repository {
    <T> Integer save(T instance);

    <T> T get(Class<T> entityClass, int id);

    <T> T get(Class<T> entityClass, String fieldName, Object value);

    <T> void saveOrUpdateAll(List<T> instances);

    List<FlwGroup> findAllByGroupId(List<String> groupIds);
}
