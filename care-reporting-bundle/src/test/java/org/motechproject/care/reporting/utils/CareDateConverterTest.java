package org.motechproject.care.reporting.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class CareDateConverterTest {

    @Test
    public void shouldSetValueAsNullIfCannotConvertToGivenType() throws Exception {
        CareDateConverter careDateConverter = new CareDateConverter(new String[]{});
        assertNull(careDateConverter.convert(Integer.class, "1"));

    }

    @Test
    public void shouldSupportFormats() {
        CareDateConverter careDateConverter = new CareDateConverter(new String[] {
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "yyyy-MM-dd'T'HH:mm:ssXXX",
                "yyyy-MM-dd'T'HH:mm:ssZ",
                "yyyy-MM-dd'T'HH:mm:ss",
                "yyyy-MM-dd",
                "dd/MM/yyyy"
        });

        Object convertedValue = careDateConverter.convert(Date.class, "2012-07-21T12:02:59.923+06:30");
        assertEquals(new DateTime(2012, 7, 21, 11, 2, 59, 923).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "2012-07-21T12:02:59.923+0630");
        assertEquals(new DateTime(2012, 7, 21, 11, 2, 59, 923).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "2013-01-01T12:00:23.923Z");
        assertEquals((new DateTime(2013, 1, 1, 17, 30, 23, 923)).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "2013-02-15T15:45:23+06:30");
        assertEquals((new DateTime(2013, 2, 15, 14, 45, 23)).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "2013-02-15T15:45:23+0630");
        assertEquals((new DateTime(2013, 2, 15, 14, 45, 23)).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "2013-01-01T12:00:23Z");
        assertEquals((new DateTime(2013, 1, 1, 17, 30, 23)).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "2013-02-15T15:45:23");
        assertEquals((new DateTime(2013, 2, 15, 15, 45, 23)).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "2012-07-15");
        assertEquals((new DateTime(2012, 7, 15, 0, 0, 0)).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "29/11/2011");
        assertEquals((new DateTime(2011, 11, 29, 0, 0, 0)).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "01/02/2011");
        assertEquals((new DateTime(2011, 2, 1, 0, 0, 0)).toDate(), convertedValue);
    }

    @Test
    public void shouldNotThrowExceptionIfInputFormatIsNotSupported() {
        CareDateConverter careDateConverter = new CareDateConverter(new String[] {
                "yyyy-MM-dd",
                "dd/MM/yyyy"
        });

        assertNull(careDateConverter.convert(Date.class, "01/31/2013"));
    }

    @Test
    public void shouldReturnNullIfNullOrEmptyProvided() throws Exception {
        CareDateConverter careDateConverter = new CareDateConverter(new String[] {
                "yyyy-MM-dd",
                "dd/MM/yyyy"
        });

        assertNull(careDateConverter.convert(Date.class, null));
        assertNull(careDateConverter.convert(Date.class, ""));
        assertNull(careDateConverter.convert(Date.class, "  "));
    }

    @Test
    public void shouldParseDateWithIncompleteTimezoneInformation() throws Exception {
        CareDateConverter careDateConverter = new CareDateConverter(new String[]{
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "yyyy-MM-dd'T'HH:mm:ssZ",
        });

        Object convertedValue = careDateConverter.convert(Date.class, "2013-02-15T15:45:23.123+05");
        assertEquals((new DateTime(2013, 2, 15, 16, 15, 23, 123)).toDate(), convertedValue);

        convertedValue = careDateConverter.convert(Date.class, "2013-02-15T15:45:23+05");
        assertEquals((new DateTime(2013, 2, 15, 16, 15, 23)).toDate(), convertedValue);
    }

    @Test
    public void shouldGetStringValue() {
        String dateTimeString = CareDateConverter.toString(new DateTime(2012, 7, 21, 12, 2, 59, 923, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());

        assertEquals("2012-07-21T12:02:59.923+05:30",dateTimeString);
    }
}
