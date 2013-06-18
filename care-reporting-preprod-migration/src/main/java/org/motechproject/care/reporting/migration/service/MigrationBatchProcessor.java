package org.motechproject.care.reporting.migration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class MigrationBatchProcessor {

    private final ExecutorServiceFactory executorServiceFactory;

    @Autowired
    public MigrationBatchProcessor(ExecutorServiceFactory executorServiceFactory) {
        this.executorServiceFactory = executorServiceFactory;
    }

    public List<String> processInBatch(final MigrationTask migrationTask, List<String> idsToMigrate) {
        MigrationFailedJobsCollector migrationFailedJobsCollector = new MigrationFailedJobsCollector();

        ExecutorService executorService = executorServiceFactory.create();
        for (final String id : idsToMigrate) {
            MigrationJob job = new MigrationJob(migrationTask, id, migrationFailedJobsCollector);
            executorService.execute(job);
        }
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return migrationFailedJobsCollector.getFailedIds();
    }

}
