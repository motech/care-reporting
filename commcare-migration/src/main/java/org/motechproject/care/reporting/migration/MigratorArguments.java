package org.motechproject.care.reporting.migration;

import org.joda.time.DateTime;
import org.motechproject.care.reporting.migration.common.Constants;
import org.motechproject.care.reporting.migration.common.MigrationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MigratorArguments {
    private static final Logger logger = LoggerFactory.getLogger(MigratorArguments.class);

    private final String[] arguments;
    private HashMap<String, Object> optionsMap = new HashMap<>();
    private MigrationType migrationType;

    public MigratorArguments(String[] arguments) {
        this.arguments = arguments;
        validateArgumentsLength();
        populateArguments();
        logArguments();
    }

    private void logArguments() {
        logger.info("Arguments:");
        logger.info(String.format("Migration Type: %s", migrationType));
        for (Map.Entry<String, Object> optionEntry : optionsMap.entrySet()) {
            logger.info(String.format("%s: %s", optionEntry.getKey(), optionEntry.getValue()));
        }
    }

    public static String usage() {
        return "Migrator migration-type -t <form-type> -v <app-version> -s <start-date> -e <end-date> -o <offset> -l <limit>\n" +
                "Eg:\n" +
                "Migrator form -t http://bihar.commcarehq.org/pregnancy/new\n" +
                "Migrator form -t http://bihar.commcarehq.org/pregnancy/new -v \"v2.0.0alpha (2b6e13-e6e3c5-unvers-2.1.0-Nokia/S40-native-input) #40 b:2012-Jul-17 r:2012-Jul-25\" -s 2013-07-01 -e 2013-07-31 -o 2000 -l 100";
    }

    public Map<String, Object> getOptions() {
        return optionsMap;
    }

    public MigrationType getMigrationType() {
        return this.migrationType;
    }

    private void populateArguments() {
        populateMigrationType();
        populateStringOption(NamedArgument.TYPE, Constants.TYPE);
        populateStringOption(NamedArgument.VERSION, Constants.VERSION);
        populateStringOption(NamedArgument.LIMIT, Constants.LIMIT);
        populateStringOption(NamedArgument.INITIAL_OFFSET, Constants.OFFSET);
        populateDateOption(NamedArgument.START_DATE, Constants.START_DATE);
        populateDateOption(NamedArgument.END_DATE, Constants.END_DATE);
    }

    private void populateStringOption(NamedArgument namedArgument, String optionName) {
        String value = getOptionValueFor(namedArgument);
        if(value != null) {
            optionsMap.put(optionName, value);
        }
    }
    private void populateDateOption(NamedArgument argumentName, String optionName) {
        String value = getOptionValueFor(argumentName);
        if(value != null) {
            optionsMap.put(optionName, getFormattedDate(value));
        }
    }

    private String getFormattedDate(String date) {
        return DateTime.parse(date).toString("yyyy-MM-dd");
    }

    private String getOptionValueFor(NamedArgument parameter) {
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

        throw new IllegalArgumentException(String.format("Invalid %s. Provide value as %s option.", parameter.name, parameter.option));
    }

    private void populateMigrationType() {
        MigrationType type = MigrationType.getFor(arguments[0]);
        if (type == null) {
            throw new IllegalArgumentException(String.format("Invalid migration type %s", arguments[0]));
        }

        this.migrationType = type;
    }

    private void validateArgumentsLength() {
        if (arguments.length == 0 || arguments.length > 13) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
    }

    private enum NamedArgument {
        TYPE("-t", "type"), VERSION("-v", "version"), START_DATE("-s", "start date"), END_DATE("-e", "end date"), INITIAL_OFFSET("-o", "initial offset"), LIMIT("-l", "limit");
        private final String option;
        private final String name;

        NamedArgument(String option, String name) {
            this.option = option;
            this.name = name;
        }
    }
}
