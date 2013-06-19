package org.motechproject.care.reporting.migration;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.service.MigrationService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class MigratorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    private MigrationService migrationService;
    @Mock
    private MigratorArguments migratorArguments;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldValidateAndMigrate() {
        when(migrationService.migrate(migratorArguments)).thenReturn(true);

        boolean success = new Migrator(migrationService).migrate(migratorArguments);

        assertTrue(success);
        verify(migrationService).migrate(migratorArguments);
    }

    @Test
    public void shouldThrowExeptionWhenValidationFails() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Some exception");
        doThrow(new IllegalArgumentException("Some exception")).when(migrationService).migrate(migratorArguments);

        new Migrator(migrationService).migrate(migratorArguments);
    }

    @Test
    public void shouldReturnFalseIfMigrationServiceIsUnsuccessful() {
        when(migrationService.migrate(migratorArguments)).thenReturn(false);

        boolean success = new Migrator(migrationService).migrate(migratorArguments);

        assertFalse(success);
        verify(migrationService).migrate(migratorArguments);
    }
}
