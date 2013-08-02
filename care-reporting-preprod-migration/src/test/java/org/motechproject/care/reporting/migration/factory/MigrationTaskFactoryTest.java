package org.motechproject.care.reporting.migration.factory;

import org.junit.Test;
import org.motechproject.care.reporting.migration.common.MigrationType;
import org.motechproject.care.reporting.migration.common.ResponseParser;
import org.motechproject.care.reporting.migration.task.CaseMigrationTask;
import org.motechproject.care.reporting.migration.task.FormMigrationTask;
import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class MigrationTaskFactoryTest {


    @Test
    public void shouldGetMigrationTaskFromMigrationType() {
        CommcareAPIHttpClient httpClient = mock(CommcareAPIHttpClient.class);
        MotechAPIHttpClient motechAPIHttpClient = mock(MotechAPIHttpClient.class);
        ResponseParser parser = mock(ResponseParser.class);

        FormMigrationTask formMigrationTask = new FormMigrationTask(httpClient, motechAPIHttpClient, parser);
        CaseMigrationTask caseMigrationTask = new CaseMigrationTask(httpClient, motechAPIHttpClient, parser);

        org.motechproject.care.reporting.migration.factory.MigrationTaskFactory migrationTaskFactory = new MigrationTaskFactory(formMigrationTask, caseMigrationTask);

        assertEquals(formMigrationTask, migrationTaskFactory.getFor(MigrationType.FORM));
        assertEquals(caseMigrationTask, migrationTaskFactory.getFor(MigrationType.CASE));
        assertNull(migrationTaskFactory.getFor(null));
    }
}
