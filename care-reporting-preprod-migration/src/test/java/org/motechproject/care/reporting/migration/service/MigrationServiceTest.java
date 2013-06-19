package org.motechproject.care.reporting.migration.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.MigratorArguments;
import org.motechproject.care.reporting.migration.factory.MigrationTaskFactory;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MigrationServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private MigratorArguments migratorArguments;

    @Mock
    private MigrationTaskFactory migrationTaskFactory;

    private MigrationService migrationService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        migrationService = new MigrationService(migrationTaskFactory);
    }

    @Test
    public void shouldValidateBeforeMigration() {
        MigrationTask migrationTask = mock(MigrationTask.class);
        File idFile = new File("/temp");

        when(migratorArguments.getIdFile()).thenReturn(idFile);
        when(migratorArguments.getMigrationType()).thenReturn(MigrationType.FORM);
        when(migrationTaskFactory.getFor(MigrationType.FORM)).thenReturn(migrationTask);
        when(migrationTask.migrate(idFile)).thenReturn(true);

        boolean success = migrationService.migrate(migratorArguments);

        assertTrue(success);
        verify(migratorArguments).validate();
        verify(migrationTask).migrate(idFile);
    }

    @Test
    public void shouldThrowExceptionIfValidationFails() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Some exception");

        doThrow(new IllegalArgumentException("Some exception")).when(migratorArguments).validate();

        migrationService.migrate(migratorArguments);
    }

    @Test
    public void shouldReturnFalseIfMigrationIsUnsuccessfulForAnyId() {

        MigrationTask migrationTask = mock(MigrationTask.class);
        File idFile = new File("/temp");

        when(migratorArguments.getIdFile()).thenReturn(idFile);
        when(migratorArguments.getMigrationType()).thenReturn(MigrationType.FORM);
        when(migrationTaskFactory.getFor(MigrationType.FORM)).thenReturn(migrationTask);
        when(migrationTask.migrate(idFile)).thenReturn(false);

        boolean success = migrationService.migrate(migratorArguments);

        assertFalse(success);
        verify(migratorArguments).validate();
        verify(migrationTask).migrate(idFile);
    }
}
