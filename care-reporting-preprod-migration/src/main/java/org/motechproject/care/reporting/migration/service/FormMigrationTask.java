package org.motechproject.care.reporting.migration.service;

import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormMigrationTask extends MigrationTask {

    private CommcareAPIHttpClient commcareAPIHttpClient;
    private MotechAPIHttpClient motechAPIHttpClient;
    private static final Logger logger = LoggerFactory.getLogger(FormMigrationTask.class);

    @Autowired
    public FormMigrationTask(MigrationBatchProcessor migrationBatchProcessor, CommcareAPIHttpClient commcareAPIHttpClient, MotechAPIHttpClient motechAPIHttpClient) {
        super(migrationBatchProcessor);
        this.commcareAPIHttpClient = commcareAPIHttpClient;
        this.motechAPIHttpClient = motechAPIHttpClient;
    }

    @Override
    public void migrate(String id) {
        logger.info(String.format("Migrating Form: %s", id));
        String form = commcareAPIHttpClient.fetchForm(id);
        logger.info(String.format("Posting Form %s to motech", id));
        motechAPIHttpClient.postForm(form);
        logger.info(String.format("Migrating completed for form: %s", id));
    }

}
