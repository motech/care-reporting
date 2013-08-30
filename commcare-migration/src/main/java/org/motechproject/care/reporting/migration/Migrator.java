package org.motechproject.care.reporting.migration;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.motechproject.care.reporting.migration.service.MigrationService;
import org.motechproject.care.reporting.migration.statistics.MigrationStatisticsCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Migrator {

    private MigrationService migrationService;
    private MigrationStatisticsCollector statisticsCollector;

    private static final Logger logger = LoggerFactory.getLogger(Migrator.class);

    @Autowired
    public Migrator(MigrationService migrationService, MigrationStatisticsCollector statisticsCollector) {
        this.migrationService = migrationService;
        this.statisticsCollector = statisticsCollector;
    }

    public static void main(String[] args) {
        DateTime startTime = DateTime.now();

        logger.info("Running migrator ...");
        boolean success = true;

        Exception exception = null;
        try {
            execute(args);
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
        System.exit(success ? 0 : 1);
    }

    private static void execute(final String[] args) {
        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationCareMigration.xml");

        final MigratorArguments migratorArguments = new MigratorArguments(args);

        final Migrator migrator = applicationContext.getBean(Migrator.class);
        migrator.migrate(migratorArguments);
        applicationContext.destroy();
    }



    public void migrate(MigratorArguments migratorArguments) {
        statisticsCollector.startTimer();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                statisticsCollector.stopTimer();
                statisticsCollector.publishResults();
            }
        });

        migrationService.migrate(migratorArguments);
    }
}