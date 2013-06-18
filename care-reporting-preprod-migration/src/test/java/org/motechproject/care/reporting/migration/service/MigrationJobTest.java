package org.motechproject.care.reporting.migration.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MigrationJobTest {

    @Mock
    private MigrationTask migrationTask;
    @Mock
    private MigrationFailedJobsCollector resultCollector;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldRunTaskAndNotRecordFailedResultIfTaskIsSuccessful() {
        String entityId = "myEntityId";

        new MigrationJob(migrationTask, entityId, resultCollector).run();

        verify(migrationTask).migrate(entityId);
        verifyZeroInteractions(resultCollector);
    }

    @Test
    public void shouldRunTaskAndRecordFailedResultIfTaskIsHasFailed() {
        String entityId = "myEntityId";
        doThrow(new RuntimeException("someException")).when(migrationTask).migrate(entityId);

        new MigrationJob(migrationTask, entityId, resultCollector).run();

        verify(migrationTask).migrate(entityId);
        verify(resultCollector).recordFailedEntityId(entityId);
    }
}
