package org.motechproject.care.reporting.migration;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.motechproject.care.reporting.migration.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Migrator {

    private MigrationService migrationService;

    @Autowired
    public Migrator(MigrationService migrationService) {
        this.migrationService = migrationService;
    }

    public static void main(String[] args) {
        MigratorArguments migratorArguments = new MigratorArguments(args);
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-migration.xml");
        Migrator migrator = applicationContext.getBean(Migrator.class);
        DateTime startTime = DateTime.now();
        boolean success = false;
        try {
            success = migrator.migrate(migratorArguments);
            if(!success) {
                System.out.println("Migration failed for few records. Check the generated error file.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Usage: " + migratorArguments.usage());
        } finally {
            System.out.printf("Total time taken for migration: %d mins %n", new Duration(startTime, DateTime.now()).getStandardMinutes());
            applicationContext.destroy();
        }

        if(!success) {
            System.exit(1);
        }
    }

    public boolean migrate(MigratorArguments migratorArguments) {
       return migrationService.migrate(migratorArguments);
    }
}