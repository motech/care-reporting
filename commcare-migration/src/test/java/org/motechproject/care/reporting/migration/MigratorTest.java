package org.motechproject.care.reporting.migration;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.service.MigrationService;
import org.motechproject.care.reporting.migration.statistics.MigrationStatisticsCollector;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


public class MigratorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    private MigrationService migrationService;
    @Mock
    private MigratorArguments migratorArguments;
    @Mock
    private MigrationStatisticsCollector statisticsCollector;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldValidateAndMigrate() {

        new Migrator(migrationService, statisticsCollector).migrate(migratorArguments);

        verify(statisticsCollector).startTimer();
        verify(migrationService).migrate(migratorArguments);

    }
}
