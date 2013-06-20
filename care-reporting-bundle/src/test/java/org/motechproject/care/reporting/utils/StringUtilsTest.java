package org.motechproject.care.reporting.utils;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.motechproject.care.reporting.utils.StringUtils.toCamelCase;

public class StringUtilsTest {
    @Test
    public void shouldConvertAllCapsToSmallCase(){
        assertEquals("sba", toCamelCase("SBA"));
        assertEquals("sbaPhone", toCamelCase("SBA_Phone"));
        assertEquals("sbAPhone", toCamelCase("SbA_Phone"));
        assertEquals("sbAPhOne", toCamelCase("SbA_PhOne"));
    }

    @Test
    public void shouldConvertToCamelCaseWhereNumberIsSeparatedByUnderscores(){
        assertEquals("anc1Date", toCamelCase("anc_1_date"));
        assertEquals("ifaTablets100", toCamelCase("ifa_tablets_100"));
        assertEquals("anc1_Date", toCamelCase("anc1_date"));
        assertEquals("motherAnc1_Date", toCamelCase("mother_anc1_date"));
        assertEquals("field1", toCamelCase("field1"));
    }

    @Test
    public void testToCamelCase() throws Exception {
        String expected = "helloWorld";
        String actual = toCamelCase("hello_world");
        assertEquals(expected, actual);
    }

    @Test
    public void testToCamelCaseWithRetainingMixedCase() throws Exception {
        String expected = "heLloWoRld";
        String actual = StringUtils.toCamelCase("heLlo_woRld");
        assertEquals(expected, actual);
    }

    @Test
    public void testToCamelCaseWithoutUnderscore() throws Exception {
        String expected = "heLloWorld";
        String actual = StringUtils.toCamelCase("heLloWorld");
        assertEquals(expected, actual);
    }

    @Test
    public void testToCamelCaseWithNull() throws Exception {
        String expected = null;
        String actual = toCamelCase(null);
        assertEquals(expected, actual);
    }
}
