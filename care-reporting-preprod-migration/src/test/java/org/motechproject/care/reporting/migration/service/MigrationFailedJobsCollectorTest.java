package org.motechproject.care.reporting.migration.service;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MigrationFailedJobsCollectorTest {

    @Test
    public void shouldCollectJobResultAndReturnFailedIdsList() {
        MigrationFailedJobsCollector resultCollector = new MigrationFailedJobsCollector();
        assertTrue(resultCollector.getFailedIds().isEmpty());

        resultCollector.recordFailedEntityId("failed1");
        resultCollector.recordFailedEntityId("failed2");
        resultCollector.recordFailedEntityId("failed2");

        List<String> actualFailedIds = resultCollector.getFailedIds();
        assertEquals(3, actualFailedIds.size());
        assertEquals("failed1", actualFailedIds.get(0));
        assertEquals("failed2", actualFailedIds.get(1));
        assertEquals("failed2", actualFailedIds.get(2));
    }
}
