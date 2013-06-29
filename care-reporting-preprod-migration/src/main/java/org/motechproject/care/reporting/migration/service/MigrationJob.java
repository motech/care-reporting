package org.motechproject.care.reporting.migration.service;

import org.motechproject.care.reporting.migration.util.BadResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MigrationJob implements Runnable {

    private final MigrationTask migrationTask;
    private final String entityId;
    private final MigrationFailedJobsCollector migrationFailedJobsCollector;

    private static final Logger logger = LoggerFactory.getLogger(MigrationJob.class);

    public MigrationJob(MigrationTask migrationTask, String entityId, MigrationFailedJobsCollector migrationFailedJobsCollector) {
        this.migrationTask = migrationTask;
        this.entityId = entityId;
        this.migrationFailedJobsCollector = migrationFailedJobsCollector;
    }

    @Override
    public void run() {
        try {
            migrationTask.migrate(entityId);
        } catch (RuntimeException ex) {
            logger.error(String.format("Failed to execute migration for id:%s; migrationType: %s", entityId, migrationTask.getClass().getCanonicalName()), ex);
            String errorType = ex instanceof  BadResponseException ? Integer.toString(((BadResponseException)ex).getStatusCode()) : "error";
            migrationFailedJobsCollector.recordFailedEntityId(entityId, errorType);
        }
    }

    public MigrationTask getMigrationTask() {
        return migrationTask;
    }

    public String getEntityId() {
        return entityId;
    }
}
