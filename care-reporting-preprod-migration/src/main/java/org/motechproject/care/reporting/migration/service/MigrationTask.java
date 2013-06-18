package org.motechproject.care.reporting.migration.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class MigrationTask {
    protected MigrationBatchProcessor migrationBatchProcessor;

    public MigrationTask(MigrationBatchProcessor migrationBatchProcessor) {
        this.migrationBatchProcessor = migrationBatchProcessor;
    }

    public void migrate(File idFile) {
        List<String> instanceIds = readAllId(idFile);
        migrationBatchProcessor.processInBatch(this, instanceIds);
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
