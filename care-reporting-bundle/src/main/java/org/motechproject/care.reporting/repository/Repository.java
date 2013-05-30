package org.motechproject.care.reporting.repository;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 30/05/13
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */

public interface Repository {
    <T> Integer save(T instance);

    <T> T get(int id, Class<T> entityClass);

    <T> T get(String fieldName, Object value, Class<T> entityClass);
}
