package org.motechproject.care.reporting.migration;

import org.joda.time.DateTime;
import org.motechproject.care.reporting.migration.common.Constants;
import org.motechproject.care.reporting.migration.common.MigrationType;

import java.util.HashMap;
import java.util.Map;

public class MigratorArguments {

    private final String[] arguments;
    HashMap<String, Object> optionsMap = new HashMap<>();
    private MigrationType migrationType;
    private String appVersion;
    private String startDate;
    private String endDate;
    private String type;

    public MigratorArguments(String[] arguments) {
        this.arguments = arguments;
        validateArgumentsLength();
        populateArguments();
    }

    public static String usage() {
        return "Migrator migration-type -t <form-type> -v <app-version> -s <start-date> -e <end-date>\n" +
                "Eg:\n" +
                "Migrator form -t http://bihar.commcarehq.org/pregnancy/new\n" +
                "Migrator form -t http://bihar.commcarehq.org/pregnancy/new -v \"v2.0.0alpha (2b6e13-e6e3c5-unvers-2.1.0-Nokia/S40-native-input) #40 b:2012-Jul-17 r:2012-Jul-25\" -s 2013-07-01 -e 2013-07-31";
    }

    public Map<String, Object> getMap() {
        return optionsMap;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public MigrationType getMigrationType() {
        return this.migrationType;
    }


    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getType() {
        return type;
    }

    private void populateArguments() {
        populateMigrationType();
        populateType();
        populateAppVersion();
        populateStartDate();
        populateEndDate();
    }

    private void populateType() {
        this.type = getOptionValueFor(MigrationOption.TYPE);
        if (this.type != null)
            optionsMap.put(Constants.TYPE, this.type);
    }

    private void populateAppVersion() {
        this.appVersion = getOptionValueFor(MigrationOption.VERSION);
        if (this.appVersion != null)
            optionsMap.put(Constants.VERSION, this.appVersion);
    }

    private void populateStartDate() {
        String date = getOptionValueFor(MigrationOption.STARTDATE);

        if (date != null) {
            this.startDate = getFormattedDate(date);
            optionsMap.put(Constants.START_DATE, this.startDate);
        }
    }

    private void populateEndDate() {
        String date = getOptionValueFor(MigrationOption.ENDDATE);

        if (date != null) {
            this.endDate = getFormattedDate(date);
            optionsMap.put(Constants.END_DATE, this.endDate);
        }

    }

    private String getFormattedDate(String date) {
        return DateTime.parse(date).toString("yyyy-MM-dd");
    }

    private String getOptionValueFor(MigrationOption parameter) {
        int foundIndex = -1;
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i].startsWith(parameter.option)) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1) return null;

        if (arguments.length > (foundIndex + 1))
            return arguments[foundIndex + 1];

        throw new IllegalArgumentException(String.format("Invalid %s", parameter.name));
    }

    private void populateMigrationType() {
        MigrationType type = MigrationType.getFor(arguments[0]);
        if (type == null) {
            throw new IllegalArgumentException(String.format("Invalid migration type %s", arguments[0]));
        }

        this.migrationType = type;
        optionsMap.put(Constants.MIGRATION_TYPE, this.migrationType);

    }

    private void validateArgumentsLength() {
        if (arguments.length == 0 || arguments.length > 9) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
    }

    private enum MigrationOption {
        TYPE("-t", "type"), VERSION("-v", "version"), STARTDATE("-s", "start date"), ENDDATE("-e", "end date");
        private final String option;
        private final String name;

        MigrationOption(String option, String name) {
            this.option = option;
            this.name = name;
        }
    }
}
