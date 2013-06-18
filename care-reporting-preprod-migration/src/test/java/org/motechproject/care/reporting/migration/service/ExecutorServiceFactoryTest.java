package org.motechproject.care.reporting.migration.service;

import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ExecutorServiceFactoryTest {


    @Test
    public void shouldCreateExecutorWithThreadPoolSizeFromProperties() {
        Properties migrationProperties = new Properties();
        migrationProperties.put("migration.threadpool.size", "4");

        ExecutorService executorService = new ExecutorServiceFactory(migrationProperties).create();

        assertThat(executorService, is(ThreadPoolExecutor.class));
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        assertEquals(4, threadPoolExecutor.getMaximumPoolSize());
    }

    @Test
    public void shouldShutdownExecutorOnPreDestroy() {
        Properties migrationProperties = new Properties();
        migrationProperties.put("migration.threadpool.size", "4");

        ExecutorServiceFactory executorServiceFactory = new ExecutorServiceFactory(migrationProperties);
        ExecutorService executorService = executorServiceFactory.create();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                }
            }
        });

        assertFalse(executorService.isShutdown());
        executorServiceFactory.destroy();
        assertTrue(executorService.isShutdown());
    }
}
