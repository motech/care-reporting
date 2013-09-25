package org.motechproject.care.reporting.service;

import org.junit.Test;
import org.motechproject.server.config.SettingsFacade;

import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConfigsFactoryTest {

    @Test
    public void shouldReturnPostgresConfigs() {
        Properties expectedProperties = new Properties();
        expectedProperties.setProperty("myKey", "myValue");

        SettingsFacade settings = mock(SettingsFacade.class);
        when(settings.getProperties("postgres.properties")).thenReturn(expectedProperties);

        Properties actualProperties = new ConfigsFactory(settings).getPostgresConfigs();

        assertEquals(expectedProperties, actualProperties);
    }

    @Test
    public void shouldReturnHibernateConfigs() {
        Properties expectedProperties = new Properties();
        expectedProperties.setProperty("myKey", "myValue");

        SettingsFacade settings = mock(SettingsFacade.class);
        when(settings.getProperties("hibernate.properties")).thenReturn(expectedProperties);

        Properties actualProperties = new ConfigsFactory(settings).getHibernateConfigs();

        assertEquals(expectedProperties, actualProperties);
    }

    @Test
    public void shouldReturnAllConfigs() {
        Properties expectedProperties = new Properties();
        expectedProperties.setProperty("myKey", "myValue");

        SettingsFacade settings = mock(SettingsFacade.class);
        when(settings.asProperties()).thenReturn(expectedProperties);

        Properties actualProperties = new ConfigsFactory(settings).getAllConfigs();

        assertEquals(expectedProperties, actualProperties);
    }
}
