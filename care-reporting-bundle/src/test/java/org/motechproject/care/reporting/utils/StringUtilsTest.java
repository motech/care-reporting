package org.motechproject.care.reporting.utils;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StringUtilsTest {
    @Test
    public void shouldConvertToCamelCaseWhereNumberIsSeparatedByUnderscores(){
        String actual = StringUtils.toCamelCase("anc_1_date");
        assertEquals("anc_1Date", actual);
    }

    @Test
    public void shouldConvertToCamelCaseWhereNumberIsTheFirstLetterAfterUnderscore(){
        String actual = StringUtils.toCamelCase("anc_1date");
        assertEquals("anc_1date", actual);
    }
    @Test
    public void shouldConvertToCamelCaseWithNumberInName(){
        String actual = StringUtils.toCamelCase("anc1_date");
        assertEquals("anc1Date", actual);
    }

    @Test
    public void testToCamelCase() throws Exception {
        String expected = "helloWorld";
        String actual = StringUtils.toCamelCase("hello_world");
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
        String actual = StringUtils.toCamelCase(null);
        assertEquals(expected, actual);
    }

}
