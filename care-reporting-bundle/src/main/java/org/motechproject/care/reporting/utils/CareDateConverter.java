package org.motechproject.care.reporting.utils;

import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.apache.commons.lang.StringUtils.isEmpty;

public final class CareDateConverter extends CareTypeConverter {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    private DateTimeConverter dateTimeConverter;

    public CareDateConverter(String[] allowedDatePatterns) {
        super(Date.class);
        dateTimeConverter = (DateTimeConverter) converter;
        dateTimeConverter.setPatterns(allowedDatePatterns);
    }

    @Override
    protected Object convertToType(Class type, Object o) {
        String value = (String) o;
        if (isEmpty(value))
            return null;

        if (value.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{3}){0,1}[\\\\+|-]\\d{2}$"))
            value = value + "00";
        return dateTimeConverter.convert(type, value);
    }

    public static String toString(Date date) {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
    }

    public static String toString(Date date, TimeZone timeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        simpleDateFormat.setTimeZone(timeZone);

        return simpleDateFormat.format(date);
    }
}
