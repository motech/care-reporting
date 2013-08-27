package org.motechproject.care.reporting.migration;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.motechproject.care.reporting.migration.service.MigrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Migrator {

    private MigrationService migrationService;

    private static final Logger logger = LoggerFactory.getLogger(Migrator.class);

    @Autowired
    public Migrator(MigrationService migrationService) {
        this.migrationService = migrationService;
    }

    public static void main(String[] args) {
        logger.info("Running migrator ...");
        boolean success = true;
        DateTime startTime = DateTime.now();
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationCareMigration.xml");

        Exception exception = null;
        try {
            Migrator migrator = applicationContext.getBean(Migrator.class);
            MigratorArguments migratorArguments = new MigratorArguments(args);

            migrator.migrate(migratorArguments);

        } catch (IllegalArgumentException e) {
            exception = e;
            System.out.println("Error: " + e.getMessage());
            System.out.println("Usage: " + MigratorArguments.usage());
            success = false;
        } catch (RuntimeException e) {
            exception = e;
            System.out.print("Error: " + e.getMessage());
            System.out.println("Migration Failed");
            success = false;
        }

        if(success) {
            logger.info("Migration ended.");
        } else {
            logger.error("Exception occurred while running migrator", exception);
            logger.error("Migration failed.");
        }

        System.out.printf("Total time taken for migration: %d mins %n", new Duration(startTime, DateTime.now()).getStandardMinutes());
        applicationContext.destroy();
        System.exit(success ? 0 : 1);
    }

    public void migrate(MigratorArguments migratorArguments) {
        migrationService.migrate(migratorArguments);
    }
}