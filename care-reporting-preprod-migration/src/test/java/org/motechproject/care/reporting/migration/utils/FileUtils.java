package org.motechproject.care.reporting.migration.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtils {

    public static File createTempFileWithContent(String fileName, String content) {
        File tempDirectory = org.apache.commons.io.FileUtils.getTempDirectory();
        File file = new File(tempDirectory, fileName);
        try {
            file.createNewFile();
            file.deleteOnExit();
            if(content != null) {
                org.apache.commons.io.FileUtils.write(file, content);
            }
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File createTempFile(String fileName) {
        return createTempFileWithContent(fileName, null);
    }

    public static File createTempFile() {
        String fileName = UUID.randomUUID().toString();
        return createTempFileWithContent(fileName, null);
    }

    public static File createTempFileWithContent(String content) {
        String fileName = UUID.randomUUID().toString();
        return createTempFileWithContent(fileName, content);
    }
}
