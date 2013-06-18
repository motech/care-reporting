package org.motechproject.care.reporting.migration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaseMigrationTask extends MigrationTask {

    @Autowired
    public CaseMigrationTask(MigrationBatchProcessor migrationBatchProcessor) {
        super(migrationBatchProcessor);
    }

    @Override
    public void migrate(String id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
