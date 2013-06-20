package org.motechproject.care.reporting.migration.factory;

import org.junit.Test;
import org.motechproject.care.reporting.migration.service.CaseMigrationTask;
import org.motechproject.care.reporting.migration.service.FormMigrationTask;
import org.motechproject.care.reporting.migration.service.MigrationBatchProcessor;
import org.motechproject.care.reporting.migration.service.MigrationType;
import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class MigrationTaskFactoryTest {


    @Test
    public void shouldGetMigrationTaskFromMigrationType() {
        MigrationBatchProcessor migrationBatchProcessor = mock(MigrationBatchProcessor.class);
        CommcareAPIHttpClient httpClient = mock(CommcareAPIHttpClient.class);
        MotechAPIHttpClient motechAPIHttpClient = mock(MotechAPIHttpClient.class);

        FormMigrationTask formMigrationTask = new FormMigrationTask(migrationBatchProcessor, httpClient, motechAPIHttpClient);
        CaseMigrationTask caseMigrationTask = new CaseMigrationTask(migrationBatchProcessor,httpClient,motechAPIHttpClient);

        MigrationTaskFactory migrationTaskFactory = new MigrationTaskFactory(formMigrationTask, caseMigrationTask);

        assertEquals(formMigrationTask, migrationTaskFactory.getFor(MigrationType.FORM));
        assertEquals(caseMigrationTask, migrationTaskFactory.getFor(MigrationType.CASE));
        assertNull(migrationTaskFactory.getFor(null));
    }
}
