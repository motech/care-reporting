package org.motechproject.care.reporting.migration.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MigrationTypeTest {
    @Test
    public void shouldGetMigrationTypeFromString() {
        assertEquals(org.motechproject.care.reporting.migration.common.MigrationType.FORM, org.motechproject.care.reporting.migration.common.MigrationType.getFor("form"));
        assertEquals(org.motechproject.care.reporting.migration.common.MigrationType.CASE, org.motechproject.care.reporting.migration.common.MigrationType.getFor("case"));
    }

    @Test
    public void shouldReturnNullIfTypeNotFound() {
        assertNull(org.motechproject.care.reporting.migration.common.MigrationType.getFor("someType"));
    }
}
