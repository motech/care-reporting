package org.motechproject.care.reporting.utils;

import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.util.Date;

import static java.lang.String.format;

public final class CareDateConverter extends AbstractConverter {
    private static final DateTimeConverter DEFAULT_CONVERTER = new DateConverter();
    static {
        DEFAULT_CONVERTER.setPatterns(new String[]{
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "yyyy-MM-dd'T'HH:mm:ss",
                "MM/dd/yyyy",
                "yyyy-MM-dd"});
    }

    public CareDateConverter() {
        super(null);
    }

    @Override
    protected Object convertToType(Class type, Object o) {
        if(!Date.class.equals(type))
            throw new IllegalArgumentException(format("Cannot convert to type %s", type.getName()));
        if(!String.class.equals(o.getClass()))
            throw new IllegalArgumentException(format("Cannot convert value of type %s", o.getClass().getName()));
        String value = (String) o;

        if(value.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3,6}[\\\\+|-]\\d{2}$"))
            value = value + "00";
        return DEFAULT_CONVERTER.convert(type, value);
    }

    @Override
    protected Class getDefaultType() {
        return Date.class;
    }
}
