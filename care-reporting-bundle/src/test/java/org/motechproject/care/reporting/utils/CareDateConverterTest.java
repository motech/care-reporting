package org.motechproject.care.reporting.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.joda.time.DateTime.parse;

public class CareDateConverterTest {

    private CareDateConverter careDateConverter;

    @Before
    public void setUp() throws Exception {
        careDateConverter = new CareDateConverter();
    }

    @Test
    public void shouldSetValueAsNullIfCannotConvertToGivenType() throws Exception {
        Object convertedValue = careDateConverter.convert(Integer.class, "1");
        assertNull(convertedValue);
    }

    @Test
    public void shouldSetDefaultValueAsNull() throws Exception {
        Object convertedValue = careDateConverter.convert(Date.class, null);
        assertNull(convertedValue);
    }

    @Test
    public void shouldConvertDateInUTC() throws Exception {
        Object convertedValue = careDateConverter.convert(Date.class, "2013-01-01T12:00:00Z");
        assertEquals(parse("2013-01-01T17:30:00+0530").toDate(), convertedValue);
    }

    @Test
    public void shouldParseDateWithIncompleteTimezoneInformation() throws Exception {
        Object convertedDate = careDateConverter.convert(Date.class, "2012-09-25T20:17:19.189+05");
        assertEquals(parse("2012-09-25T20:17:19.189+0500").toDate(), convertedDate);
    }
}
