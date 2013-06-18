package org.motechproject.care.reporting.migration.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MigrationFailedJobsCollector {

    private List<String> failedIds = Collections.synchronizedList(new ArrayList<String>());

    public void recordFailedEntityId(String enityId) {
        failedIds.add(enityId);
    }

    public List<String> getFailedIds() {
        return failedIds;
    }
}
