package org.motechproject.care.reporting.service;

import org.motechproject.server.config.SettingsFacade;

import java.util.Properties;

public class ConfigsFactory {

    private static final String POSTGRES_PROPERTIES_FILE_NAME = "postgres.properties";
    private static final String HIBERNATE_PROPERTIES_FILE_NAME = "hibernate.properties";

    private SettingsFacade settings;

    public ConfigsFactory(SettingsFacade settings) {
        this.settings = settings;
    }

    public Properties getPostgresConfigs() {
        return settings.getProperties(POSTGRES_PROPERTIES_FILE_NAME);
    }

    public Properties getHibernateConfigs() {
        return settings.getProperties(HIBERNATE_PROPERTIES_FILE_NAME);
    }

    public Properties getAllConfigs() {
        return settings.asProperties();
    }
}
