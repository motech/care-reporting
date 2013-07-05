package org.motechproject.care.reporting.utils;

import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

public final class CareDateConverter extends AbstractConverter {
    private DateTimeConverter dateTimeConverter = new DateConverter();

    public CareDateConverter(String[] allowedDatePatterns) {
        super(null);
        dateTimeConverter.setPatterns(allowedDatePatterns);
    }

    @Override
    protected Object convertToType(Class type, Object o) {
        if(!Date.class.equals(type))
            throw new IllegalArgumentException(format("Cannot convert to type %s", type.getName()));
        if(!String.class.equals(o.getClass()))
            throw new IllegalArgumentException(format("Cannot convert value of type %s", o.getClass().getName()));
        String value = (String) o;

        if(value.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{3}){0,1}[\\\\+|-]\\d{2}$"))
            value = value + "00";
        return dateTimeConverter.convert(type, value);
    }

    public static String toString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(date);
    }

    @Override
    protected Class getDefaultType() {
        return Date.class;
    }
}
