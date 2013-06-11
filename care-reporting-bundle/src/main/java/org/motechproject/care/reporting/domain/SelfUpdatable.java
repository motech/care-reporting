package org.motechproject.care.reporting.domain;

public interface SelfUpdatable<T> {
    public void updateToLatest(T object);
}
