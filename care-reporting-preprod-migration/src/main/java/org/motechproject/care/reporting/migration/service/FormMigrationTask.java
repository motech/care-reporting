package org.motechproject.care.reporting.migration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormMigrationTask extends MigrationTask {

    @Autowired
    public FormMigrationTask(MigrationBatchProcessor migrationBatchProcessor) {
        super(migrationBatchProcessor);
    }

    @Override
    public void migrate(String id) {
        //fetch id
        //post
    }

}
