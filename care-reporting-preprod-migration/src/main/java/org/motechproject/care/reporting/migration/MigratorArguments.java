package org.motechproject.care.reporting.migration;

import org.motechproject.care.reporting.migration.service.MigrationType;

import java.io.File;

public class MigratorArguments {

    private final String[] arguments;
    private File file;
    private MigrationType migrationType;

    public MigratorArguments(String[] arguments) {

        this.arguments = arguments;
    }

    public String usage() {
        return "Migrator <migration-type> <file-name>\n" +
                "Eg:\n" +
                "Migrator form /tmp/form_ids\n" +
                "Migrator case /tmp/case_ids";
    }

    public void validate() {
        validateArgumentsLength();
        validateMigrationType();
        validateFile();
    }

    private void validateFile() {
        File file = new File(arguments[1]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("File %s does not exist", arguments[1]));
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException(String.format("%s is not a valid file", arguments[1]));
        }
        this.file = file;
    }

    private void validateMigrationType() {
        MigrationType type = MigrationType.getFor(arguments[0]);
        if (type == null) {
            throw new IllegalArgumentException(String.format("Invalid migration type %s", arguments[0]));
        } else {
            this.migrationType = type;
        }
    }

    private void validateArgumentsLength() {
        if (arguments.length != 2) {
            throw new IllegalArgumentException("Only two arguments are allowed");
        }
    }

    public File getIdFile() {
        return this.file;
    }

    public MigrationType getMigrationType() {
        return this.migrationType;
    }
}
