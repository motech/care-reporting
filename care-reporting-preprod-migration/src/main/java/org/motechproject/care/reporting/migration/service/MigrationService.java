package org.motechproject.care.reporting.migration.service;

import org.motechproject.care.reporting.migration.MigratorArguments;
import org.motechproject.care.reporting.migration.factory.MigrationTaskFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MigrationService {

    private MigrationTaskFactory migrationTaskFactory;

    @Autowired
    public MigrationService(MigrationTaskFactory migrationTaskFactory) {
        this.migrationTaskFactory = migrationTaskFactory;
    }

    public void migrate(MigratorArguments migratorArguments) {
        migratorArguments.validate();
        MigrationTask migrationTask = migrationTaskFactory.getFor(migratorArguments.getMigrationType());
        migrationTask.migrate(migratorArguments.getIdFile());
    }

}
