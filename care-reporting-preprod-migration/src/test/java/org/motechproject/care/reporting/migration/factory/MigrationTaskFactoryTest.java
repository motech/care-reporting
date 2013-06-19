package org.motechproject.care.reporting.migration.factory;

import org.junit.Test;
import org.motechproject.care.reporting.migration.service.CaseMigrationTask;
import org.motechproject.care.reporting.migration.service.FormMigrationTask;
import org.motechproject.care.reporting.migration.service.MigrationBatchProcessor;
import org.motechproject.care.reporting.migration.service.MigrationType;
import org.motechproject.care.reporting.migration.util.CommCareAPIHttpClient;

import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class MigrationTaskFactoryTest {


    @Test
    public void shouldGetMigrationTaskFromMigrationType() {
        MigrationBatchProcessor migrationBatchProcessor = mock(MigrationBatchProcessor.class);
        CommCareAPIHttpClient httpClient = mock(CommCareAPIHttpClient.class);
        Properties platformProperties = mock(Properties.class);

        FormMigrationTask formMigrationTask = new FormMigrationTask(migrationBatchProcessor, httpClient, platformProperties);
        CaseMigrationTask caseMigrationTask = new CaseMigrationTask(migrationBatchProcessor);

        MigrationTaskFactory migrationTaskFactory = new MigrationTaskFactory(formMigrationTask, caseMigrationTask);

        assertEquals(formMigrationTask, migrationTaskFactory.getFor(MigrationType.FORM));
        assertEquals(caseMigrationTask, migrationTaskFactory.getFor(MigrationType.CASE));
        assertNull(migrationTaskFactory.getFor(null));
    }
}
