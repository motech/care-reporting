package org.motechproject.care.reporting.migration.executor;

import org.motechproject.care.reporting.migration.service.MigrationJob;

import java.util.concurrent.ExecutorService;

public class MigrationJobExecutor  {

    private final ExecutorService executorService;

    public MigrationJobExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void execute(MigrationJob migrationBatchJob) {
        executorService.execute(migrationBatchJob);
    }
}
