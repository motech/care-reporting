package org.motechproject.care.reporting.migration;

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
        try {
            migrator.migrate(migratorArguments);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Usage: " + migratorArguments.usage());
        } finally {
            applicationContext.destroy();
        }
    }

    public void migrate(MigratorArguments migratorArguments) {
        migrationService.migrate(migratorArguments);
    }
}