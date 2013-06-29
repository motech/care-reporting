package org.motechproject.care.reporting.migration.service;

import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MigrationFailedJobsCollectorTest {

    @Test
    public void shouldCollectJobResultAndReturnFailedIdsList() {
        MigrationFailedJobsCollector resultCollector = new MigrationFailedJobsCollector();
        assertTrue(resultCollector.getFailedIds().isEmpty());

        resultCollector.recordFailedEntityId("failed1", "404");
        resultCollector.recordFailedEntityId("failed2", "error");
        resultCollector.recordFailedEntityId("failed3", "error");

        Multimap<String, String> actualFailedIds = resultCollector.getFailedIds();
        assertEquals(3, actualFailedIds.size());


        List<String> failedIds404 = new ArrayList<>(actualFailedIds.get("404"));
        assertEquals(Arrays.asList("failed1"), failedIds404);

        List<String> failedIdsErrors = new ArrayList<>(actualFailedIds.get("error"));
        assertEquals(Arrays.asList("failed2", "failed3"), failedIdsErrors);

    }
}
