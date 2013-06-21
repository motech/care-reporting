package org.motechproject.care.reporting.utils;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class CareDateConverterTest {

    private CareDateConverter careDateConverter;

    @Before
    public void setUp() throws Exception {
        careDateConverter = new CareDateConverter();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentIfTryingToConvertTypeOtherThanDate() throws Exception {
        careDateConverter.convertToType(String.class, null);
    }


    @Test
    public void shouldSetDefaultValueAsNull() throws Exception {
        Object convertedValue = careDateConverter.convert(Date.class, null);
        assertNull(convertedValue);
    }

    @Test
    public void shouldParseDateWithIncompleteTimezoneInformation() throws Exception {
        Object convertedDate = careDateConverter.convertToType(Date.class, "2012-09-25T20:17:19.189+05");
        assertEquals(DateTime.parse("2012-09-25T20:17:19.189+0500").toDate(), convertedDate);
    }
}
