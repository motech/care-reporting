package org.motechproject.care.reporting.migration.service;

import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class MigrationBatchProcessor {

    private final ExecutorServiceFactory executorServiceFactory;

    private static final Logger logger = LoggerFactory.getLogger(MigrationBatchProcessor.class);

    @Autowired
    public MigrationBatchProcessor(ExecutorServiceFactory executorServiceFactory) {
        this.executorServiceFactory = executorServiceFactory;
    }

    public Multimap<String, String> processInBatch(final MigrationTask migrationTask, List<String> idsToMigrate) {
        logger.info(String.format("Migrating %s ids for task %s", idsToMigrate.size(), migrationTask.getClass().getCanonicalName()));

        MigrationFailedJobsCollector migrationFailedJobsCollector = new MigrationFailedJobsCollector();

        ExecutorService executorService = executorServiceFactory.create();
        for (final String id : idsToMigrate) {
            MigrationJob job = new MigrationJob(migrationTask, id, migrationFailedJobsCollector);
            executorService.execute(job);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return migrationFailedJobsCollector.getFailedIds();
    }

}
