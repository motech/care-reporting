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
        String actual = toCamelCase("anc_1_date");
        assertEquals("anc_1Date", actual);
    }

    @Test
    public void shouldConvertToCamelCaseWhereNumberIsTheFirstLetterAfterUnderscore(){
        String actual = toCamelCase("anc_1date");
        assertEquals("anc_1date", actual);
    }
    @Test
    public void shouldConvertToCamelCaseWithNumberInName(){
        String actual = toCamelCase("anc1_date");
        assertEquals("anc1Date", actual);
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
