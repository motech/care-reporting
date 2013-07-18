package org.motechproject.care.reporting.utils;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.AbstractConverter;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.isEmpty;

public class CareTypeConverter extends AbstractConverter {
    protected Converter converter;
    private Class typeToConvert;

    public CareTypeConverter(Class typeToConvert) {
        this.typeToConvert = typeToConvert;
        this.converter = TypeConverterFactory.getFor(typeToConvert);
    }

    @Override
    protected Object handleMissing(Class type) {
        return null;
    }

    @Override
    protected Object convertToType(Class type, Object value) {
        return isEmpty((String) value) ? null : converter.convert(type, value);
    }

    @Override
    protected Class getDefaultType() {
        return typeToConvert;
    }
}
