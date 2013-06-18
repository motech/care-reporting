package org.motechproject.care.reporting.migration.service;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MigrationBatchProcessorTest {

    @Test
    public void shouldProcessTheIdList() {
        ExecutorServiceFactory executorServiceFactory = mock(ExecutorServiceFactory.class);
        MigrationTask migrationTask = mock(FormMigrationTask.class);
        ExecutorService executorService = mock(ExecutorService.class);
        ArgumentCaptor<MigrationJob> jobCaptor = ArgumentCaptor.forClass(MigrationJob.class);

        List<String> idsToMigrate = new ArrayList<String>();
        idsToMigrate.add("1");
        idsToMigrate.add("2");
        idsToMigrate.add("3");
        when(executorServiceFactory.create()).thenReturn(executorService);
        MigrationBatchProcessor migrationBatchProcessor = new MigrationBatchProcessor(executorServiceFactory);

        migrationBatchProcessor.processInBatch(migrationTask, idsToMigrate);

        verify(executorService, times(3)).execute(jobCaptor.capture());
        List<MigrationJob> jobs = jobCaptor.getAllValues();
        assertEquals(3, jobs.size());
        assertEquals("1", jobs.get(0).getEntityId());
        assertEquals(migrationTask, jobs.get(0).getMigrationTask());
        assertEquals("2", jobs.get(1).getEntityId());
        assertEquals(migrationTask, jobs.get(1).getMigrationTask());
        assertEquals("3", jobs.get(2).getEntityId());
        assertEquals(migrationTask, jobs.get(2).getMigrationTask());
    }

}
