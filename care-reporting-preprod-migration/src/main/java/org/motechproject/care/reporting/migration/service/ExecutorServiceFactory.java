package org.motechproject.care.reporting.migration.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ExecutorServiceFactory {

    private static final String MIGRATION_THREADPOOL_SIZE = "migration.threadpool.size";

    private ExecutorService executorService;

    public ExecutorServiceFactory(@Qualifier("migrationProperties") Properties migrationProperties) {
        int threadPoolSize = Integer.parseInt(migrationProperties.getProperty(MIGRATION_THREADPOOL_SIZE));
        executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public ExecutorService create() {
        return executorService;
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdownNow();
    }
}
