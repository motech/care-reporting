package org.motechproject.care.reporting.parser;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DelFupFixPostProcessorTest {

    @Test
    public void shouldConvertElapsedDaysToDate() {
        HashMap<String, String> values = new HashMap<>();
        values.put("delFup", "12");

        new DelFupFixPostProcessor().transform(values);

        assertEquals("1970-01-13", values.get("delFup"));
    }

    @Test
    public void shouldIgnoreIfDelFupIsNotPresent() {
        HashMap<String, String> values = new HashMap<>();

        new DelFupFixPostProcessor().transform(values);

        assertFalse(values.containsKey("delFup"));
    }

    @Test
    public void shouldIgnoreIfDelFupIsNull() {
        HashMap<String, String> values = new HashMap<>();
        values.put("delFup", null);

        new DelFupFixPostProcessor().transform(values);

        assertNull(values.get("delFup"));
        assertTrue(values.containsKey("delFup"));
    }


    @Test
    public void shouldIgnoreIfDelFupIsNonNumeric() {
        HashMap<String, String> values1 = new HashMap<>();
        HashMap<String, String> values2 = new HashMap<>();
        HashMap<String, String> values3 = new HashMap<>();
        HashMap<String, String> values4 = new HashMap<>();

        values1.put("delFup", "");
        values2.put("delFup", " ");
        values3.put("delFup", "abc");
        values4.put("delFup", "12.45");

        new DelFupFixPostProcessor().transform(values1);
        new DelFupFixPostProcessor().transform(values2);
        new DelFupFixPostProcessor().transform(values3);
        new DelFupFixPostProcessor().transform(values4);

        assertEquals("", values1.get("delFup"));
        assertEquals(" ", values2.get("delFup"));
        assertEquals("abc", values3.get("delFup"));
        assertEquals("12.45", values4.get("delFup"));
    }
}
