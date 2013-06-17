package org.motechproject.care.reporting.ft.utils;

public interface TimedRunnerBreakCondition<T> {
    public boolean test(T obj);
}
