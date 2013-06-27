package org.motechproject.care.reporting.ft.utils;

import org.apache.commons.lang.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.motechproject.care.reporting.ft.utils.FileUtils.readFromClasspath;

public class PropertyFile {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private final Map<String, String> properties;

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

    public Map<String, String> properties() {
        return Collections.unmodifiableMap(properties);
    }

    private Map<String, String> loadPropertyFile(Reader reader) throws IOException {
        BufferedReader values = new BufferedReader(reader);
        Map<String, String> result = new LinkedHashMap<String, String>();
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
        return i < value.length ? value[i] : "";
    }
}
