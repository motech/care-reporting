package org.motechproject.care.reporting.migration.factory;

import org.motechproject.care.reporting.migration.service.CaseMigrationTask;
import org.motechproject.care.reporting.migration.service.FormMigrationTask;
import org.motechproject.care.reporting.migration.service.MigrationTask;
import org.motechproject.care.reporting.migration.service.MigrationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MigrationTaskFactory {

    private final FormMigrationTask formMigrationTask;
    private final CaseMigrationTask caseMigrationTask;

    @Autowired
    public MigrationTaskFactory(FormMigrationTask formMigrationTask, CaseMigrationTask caseMigrationTask) {
        this.formMigrationTask = formMigrationTask;
        this.caseMigrationTask = caseMigrationTask;
    }

    public MigrationTask getFor(MigrationType migrationType) {
        if(migrationType == MigrationType.CASE) {
            return caseMigrationTask;
        }
        if(migrationType == MigrationType.FORM) {
            return formMigrationTask;
        }
        return null;
    }
}
