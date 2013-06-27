package org.motechproject.care.reporting.ft.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;

public class FileUtils {
    public static String readFromClasspath(String fileName) {
        InputStream xmlStream = FileUtils.class.getResourceAsStream("/" + fileName);
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(xmlStream, writer, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    public static String readFromClasspath(String fileName, Map<String, String> placeholderMap) {
        return TemplateUtils.apply(readFromClasspath(fileName), placeholderMap);
    }
}
