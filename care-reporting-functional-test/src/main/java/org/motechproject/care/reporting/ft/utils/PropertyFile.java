package org.motechproject.care.reporting.ft.utils;

import org.antlr.stringtemplate.StringTemplate;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import static org.motechproject.care.reporting.ft.utils.FileUtils.readFromClasspath;

public class PropertyFile {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private final Map<String,Object> properties;

    private PropertyFile(Reader reader)  {
        try {
            properties = loadPropertyFile(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PropertyFile(String fileName, Map<String, String> placeHolderMap) {
        this(new StringReader(readFromClasspath(fileName, placeHolderMap)));
    }

    public PropertyFile(String fileName) {
        this(new StringReader(readFromClasspath(fileName)));
    }

    public static PropertyFile fromString(String str) {
        return new PropertyFile(new StringReader(str));
    }

    public Map<String, Object> asPrimitiveMap() {
        Map<String, Object> result = new LinkedHashMap<String, Object>(this.properties);

        for (String key : result.keySet()) {
            String value = (String) result.get(key);

            if (value.matches("null")) result.put(key, null);
            else if (value.matches("true|false")) result.put(key, new Boolean(value));
            else if (value.matches("\\d+")) result.put(key, Integer.parseInt(value));
            else if (value.matches("\\d{4}-\\d{2}-\\d{2}"))
                result.put(key, new Date(DateTime.parse(value).getMillis()));
            else if (value.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{3}"))
                result.put(key, new Timestamp(DATE_TIME_FORMATTER.parseDateTime(value).getMillis()));
        }
        return result;
    }

    public Map<String, Object> properties() {
        return Collections.unmodifiableMap(properties);
    }

    private Map<String, Object> loadPropertyFile(Reader reader) throws IOException {
        BufferedReader values = new BufferedReader(reader);
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        String line;
        while ((line = values.readLine()) != null) {
            if (StringUtils.isBlank(line)) continue;
            String[] keyValue = line.split("=");
            if (keyValue[0].startsWith("#")) continue; // commented line

            result.put(keyValue[0], safeAccess(keyValue, 1));
        }
        return result;
    }

    private static String safeAccess(String[] value, int i) {
        return i < value.length ? value[i] : "null";
    }
}
