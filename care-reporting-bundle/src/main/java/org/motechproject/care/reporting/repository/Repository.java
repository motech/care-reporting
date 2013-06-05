package org.motechproject.care.reporting.repository;

import java.util.List;

public interface Repository {
    <T> Integer save(T instance);

    <T> T get(Class<T> entityClass, int id);

    <T> T get(Class<T> entityClass, String fieldName, Object value);

    <T> void saveOrUpdateAll(List<T> instances);
}
