package org.motechproject.care.reporting.migration;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.motechproject.care.reporting.migration.common.MigrationType;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.motechproject.care.reporting.migration.common.Constants.*;


public class MigratorArgumentsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private String[] args;

    @Before
    public void setUp() {
        args = new String[]{"form", "-t", "namespace", "-v", "version", "-start", "2012-10-20", "-enddate", "2012-10-20", "-o", "2000", "-l", "100"};
    }

    @Test
    public void shouldGetMap() throws Exception {
        Map<String, Object> expectedMap = new HashMap<String, Object>() {{
            put(TYPE, "namespace");
            put(VERSION, "version");
            put(START_DATE, "2012-10-20");
            put(END_DATE, "2012-10-20");
            put(OFFSET, "2000");
            put(LIMIT, "100");
        }};

        MigratorArguments arguments = new MigratorArguments(args);
        Map<String, Object> actualMap = arguments.getMap();
        ReflectionAssert.assertReflectionEquals(expectedMap, actualMap);
        assertEquals(MigrationType.FORM, arguments.getMigrationType());
    }

    @Test
    public void shouldUsage() throws Exception {
        assertNotNull(MigratorArguments.usage());
    }

    @Test
    public void shouldGetNameSpace() throws Exception {
        MigratorArguments arguments = new MigratorArguments(args);
        assertEquals("namespace", arguments.getType());
    }

    @Test
    public void shouldGetAppVersion() throws Exception {
        MigratorArguments arguments = new MigratorArguments(args);
        assertEquals("version", arguments.getAppVersion());
    }

    @Test
    public void shouldGetMigrationType() throws Exception {
        MigratorArguments arguments = new MigratorArguments(args);
        assertEquals(MigrationType.FORM, arguments.getMigrationType());
    }

    @Test
    public void shouldGetStartDate() throws Exception {
        MigratorArguments arguments = new MigratorArguments(args);
        assertEquals("2012-10-20", arguments.getStartDate());
    }

    @Test
    public void shouldGetEndDate() throws Exception {
        MigratorArguments arguments = new MigratorArguments(args);
        assertEquals("2012-10-20", arguments.getEndDate());
    }

    @Test
    public void shouldGetOffset() {
        MigratorArguments migratorArguments = new MigratorArguments(args);
        assertEquals("2000", migratorArguments.getOffset());
    }

    @Test
    public void shouldGetLimit() {
        MigratorArguments migratorArguments = new MigratorArguments(args);
        assertEquals("100", migratorArguments.getLimit());
    }

    @Test
    public void shouldThrowExceptionForInvalidArgumentLength_less() {
        String[] invalidArgs = new String[]{};
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid number of arguments");

        new MigratorArguments(invalidArgs);
    }

    @Test
    public void shouldThrowExceptionForInvalidArgumentLength_more() {
        String[] invalidArgs = args = new String[]{"form", "-t", "namespace", "-v", "version", "-start", "2012-10-20", "-enddate", "2012-10-20", "-o", "2000", "-l", "100", "-x"};
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid number of arguments");

        new MigratorArguments(invalidArgs);
    }

    @Test
    public void shouldThrowExceptionForInvalidArgument() {
        String[] invalidArgs = new String[]{"form", "namespace", "-v"};
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid version");

        new MigratorArguments(invalidArgs);
    }

    @Test
    public void shouldThrowExceptionForInvalidMigrationType() {
        String[] invalidArgs = new String[]{"invalidType", "namespace", "-v", "someversion"};
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid migration type invalidType");

        new MigratorArguments(invalidArgs);
    }

    @Test
    public void shouldThrowExceptionForInvalidDate() {
        String[] args = new String[]{"form", "namespace", "-v", "version", "-start", "screwed", "-enddate", "2012-10-20"};

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid format: \"screwed\"");

        new MigratorArguments(args);
    }

}
