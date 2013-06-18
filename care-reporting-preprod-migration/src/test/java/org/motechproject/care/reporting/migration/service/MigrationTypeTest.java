package org.motechproject.care.reporting.migration.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MigrationTypeTest {
    @Test
    public void shouldGetMigrationTypeFromString() {
        assertEquals(MigrationType.FORM,MigrationType.getFor("form"));
        assertEquals(MigrationType.CASE,MigrationType.getFor("case"));
    }

    @Test
    public void shouldReturnNullIfTypeNotFound() {
        assertNull(MigrationType.getFor("someType"));
    }
}
