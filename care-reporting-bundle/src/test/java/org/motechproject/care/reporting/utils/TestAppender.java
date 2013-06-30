package org.motechproject.care.reporting.utils;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.hamcrest.Matcher;
import org.mockito.ArgumentMatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestAppender extends AppenderSkeleton {

    private static List<LoggingEvent> events = Collections.synchronizedList(new ArrayList<LoggingEvent>());

    @Override
    protected void append(LoggingEvent event) {
        events.add(event);
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    public static LoggingEvent findMatching(final Matcher<Level> levelMatcher, final Matcher<String> messageMatcher) {
        return findMatching(new ArgumentMatcher<LoggingEvent>() {
            @Override
            public boolean matches(Object object) {
                LoggingEvent event = (LoggingEvent) object;
                return levelMatcher.matches(event.getLevel()) && messageMatcher.matches(event.getMessage());
            }
        });
    }

    public static LoggingEvent findMatching(final Matcher<LoggingEvent> matcher) {
        return (LoggingEvent) CollectionUtils.find(events, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                LoggingEvent event = (LoggingEvent) object;
                return matcher.matches(event);
            }
        });
    }

    public static void clear() {
        events.clear();
    }
}
