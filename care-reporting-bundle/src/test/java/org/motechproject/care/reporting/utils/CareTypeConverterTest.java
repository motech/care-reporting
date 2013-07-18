package org.motechproject.care.reporting.utils;

import org.junit.Test;

import static junit.framework.Assert.assertNull;

public class CareTypeConverterTest {
    @Test
    public void shouldReturnNullIfValueIsEmpty() {
        CareTypeConverter careTypeConverter = new CareTypeConverter(Integer.class);

        assertNull(careTypeConverter.convertToType(Integer.class, ""));
        assertNull(careTypeConverter.convertToType(Integer.class, null));
    }
}
