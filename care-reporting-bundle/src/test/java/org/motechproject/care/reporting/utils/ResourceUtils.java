package org.motechproject.care.reporting.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class ResourceUtils {

    public String formXml(String fileName) throws IOException {
        InputStream xmlStream = this.getClass().getResourceAsStream("/"+fileName);
        StringWriter writer = new StringWriter();
        IOUtils.copy(xmlStream, writer, "UTF-8");
        return writer.toString();
    }
}
