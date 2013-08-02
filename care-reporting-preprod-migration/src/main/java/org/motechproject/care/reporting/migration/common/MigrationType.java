package org.motechproject.care.reporting.migration.common;

public enum MigrationType {
    FORM("form"), CASE("case");
    private final String type;

    MigrationType(String type) {
        this.type = type;
    }

    public static MigrationType getFor(String type){
        for (MigrationType migrationType : MigrationType.values()) {
            if (migrationType.type.equals(type))
                return migrationType;
        }
        return null;
    }
}
