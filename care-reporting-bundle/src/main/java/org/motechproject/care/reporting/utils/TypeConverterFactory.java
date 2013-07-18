package org.motechproject.care.reporting.utils;

import org.apache.commons.beanutils.converters.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TypeConverterFactory {

    private static Map<Class, AbstractConverter> typeConverters = new HashMap<Class, AbstractConverter>() {{
        put(Integer.class, new IntegerConverter());
        put(Short.class, new ShortConverter());
        put(Boolean.class, new BooleanConverter());
        put(BigDecimal.class, new BigDecimalConverter());
        put(Date.class, new DateConverter());
    }};

    public static AbstractConverter getFor(Class type) {
        return typeConverters.get(type);
    }
}
