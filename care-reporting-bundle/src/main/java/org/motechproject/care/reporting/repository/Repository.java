package org.motechproject.care.reporting.repository;

import java.util.List;

public interface Repository {
    <T> Integer save(T instance);

    <T> T get(int id, Class<T> entityClass);

    <T> T get(String fieldName, Object value, Class<T> entityClass);

    <T> void saveOrUpdateAll(List<T> instances);
}
