package org.motechproject.care.reporting.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.mapper.TestData;

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

    @Test
    public void shouldSupportFormats() {
        Object convertedValue = careDateConverter.convert(Date.class, "05/22/2013");
        assertEquals((new DateTime(2013, 5, 22, 0, 0, 0)).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "2012-07-21T12:02:59.923+05:30");
        assertEquals(new DateTime(2012, 7, 21, 12, 2, 59, 923, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "2012-07-15");
        assertEquals((new DateTime(2012, 7, 15, 0, 0, 0)).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "2013-02-15T15:45:23");
        assertEquals((new DateTime(2013, 2, 15, 15, 45, 23)).toDate(), convertedValue);
    }

    @Test
    public void shouldGetStringValue() {
        String dateTimeString = CareDateConverter.toString(new DateTime(2012, 7, 21, 12, 2, 59, 923, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());

        assertEquals("2012-07-21T12:02:59.923+05:30",dateTimeString);
    }
}
