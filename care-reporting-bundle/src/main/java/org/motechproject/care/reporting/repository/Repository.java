package org.motechproject.care.reporting.repository;

import org.motechproject.care.reporting.domain.dimension.ChildCase;

import java.io.Serializable;
import java.util.List;

public interface Repository {
    <T> Integer save(T instance);

    <T> T get(Class<T> entityClass, int id);

    <T> T get(Class<T> entityClass, String fieldName, Object value);

    <T> void saveOrUpdateAll(List<T> instances);

    <T> List<T> findAllByField(Class<T> clazz, List<String> values, String fieldName);

    <T> T findByExternalPrimaryKey(Class<T> clazz, Object value);

    <T> void saveOrUpdate(T instance, String entityName);
}
