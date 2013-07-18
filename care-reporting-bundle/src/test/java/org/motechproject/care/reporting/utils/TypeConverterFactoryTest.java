package org.motechproject.care.reporting.utils;

import org.apache.commons.beanutils.converters.*;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertFalse;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class TypeConverterFactoryTest {
    @Test
    public void shouldReturnProperConverterWithoutDefaultValue() {
        assertConverter(TypeConverterFactory.getFor(Integer.class), IntegerConverter.class);
        assertConverter(TypeConverterFactory.getFor(Short.class), ShortConverter.class);
        assertConverter(TypeConverterFactory.getFor(Boolean.class), BooleanConverter.class);
        assertConverter(TypeConverterFactory.getFor(BigDecimal.class), BigDecimalConverter.class);
    }

    private void assertConverter(AbstractConverter actualConverter, Class expectedConverter) {
        assertThat(actualConverter, instanceOf(expectedConverter));
        assertFalse(actualConverter.isUseDefault());
    }
}
