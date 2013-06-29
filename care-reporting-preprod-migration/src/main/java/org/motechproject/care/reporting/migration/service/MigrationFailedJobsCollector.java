package org.motechproject.care.reporting.migration.service;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class MigrationFailedJobsCollector {

    Multimap<String, String> failedIds = Multimaps.synchronizedMultimap(LinkedHashMultimap.<String, String>create());

    public void recordFailedEntityId(String enityId, String errorType) {
        failedIds.put(errorType, enityId);
    }

    public Multimap<String, String> getFailedIds() {
        return failedIds;
    }
}
