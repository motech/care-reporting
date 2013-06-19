package org.motechproject.care.reporting.migration.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class MigrationTask {
    protected MigrationBatchProcessor migrationBatchProcessor;

    private static final Logger logger = LoggerFactory.getLogger(MigrationTask.class);

    public MigrationTask(MigrationBatchProcessor migrationBatchProcessor) {
        this.migrationBatchProcessor = migrationBatchProcessor;
    }

    public boolean migrate(File idFile) {
        List<String> instanceIds = readAllId(idFile);
        List<String> failedRecords = migrationBatchProcessor.processInBatch(this, instanceIds);
        if(failedRecords.size() == 0) {
            return true;
        }
        writeErrorFile(idFile, failedRecords);
        return false;
    }

    private String getErrorFilePath(String inputFilePath) {
        return inputFilePath + "_error";
    }

    private void writeErrorFile(File idFile, List<String> failedRecords) {
        String errorFilePath = getErrorFilePath(idFile.getAbsolutePath());
        logger.error(String.format("Writing failed ids in file %s", errorFilePath));

        StringBuffer errorFileContent = new StringBuffer();
        for (String failedId : failedRecords) {
            errorFileContent.append(String.format("%s%n", failedId));
        }
        try {
            FileUtils.write(new File(errorFilePath), errorFileContent.toString(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void migrate(String id);

    private List<String> readAllId(File idFile) {
        List<String> lines;
        try {
            lines = FileUtils.readLines(idFile);
            CollectionUtils.filter(lines, new Predicate() {
                @Override
                public boolean evaluate(Object object) {
                    return !StringUtils.isBlank((String) object);
                }
            });
            CollectionUtils.transform(lines, new Transformer() {
                @Override
                public Object transform(Object input) {
                    return StringUtils.trim((String) input);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
