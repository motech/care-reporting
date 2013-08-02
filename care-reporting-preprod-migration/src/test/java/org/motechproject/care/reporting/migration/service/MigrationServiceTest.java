package org.motechproject.care.reporting.migration.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.MigratorArguments;
import org.motechproject.care.reporting.migration.common.MigrationType;
import org.motechproject.care.reporting.migration.factory.MigrationTaskFactory;
import org.motechproject.care.reporting.migration.task.MigrationTask;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MigrationServiceTest {

    @Mock
    private MigrationTaskFactory migrationTaskFactory;
    @Mock
    private MigratorArguments migratorArguments;
    @Mock
    private MigrationTask migrationTask;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldGetMigrationTaskAndMigrate() {
        when(migratorArguments.getMigrationType()).thenReturn(MigrationType.FORM);
        when(migrationTaskFactory.getFor(MigrationType.FORM)).thenReturn(migrationTask);
        MigrationService migrationService = new MigrationService(migrationTaskFactory);

        migrationService.migrate(migratorArguments);

        verify(migrationTask).migrate(migratorArguments);
    }
}
