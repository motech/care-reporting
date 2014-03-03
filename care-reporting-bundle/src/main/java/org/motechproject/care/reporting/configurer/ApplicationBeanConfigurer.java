package org.motechproject.care.reporting.configurer;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.motechproject.server.config.SettingsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
public class ApplicationBeanConfigurer {

    @Autowired
    private SettingsFacade settingsFacade;

    @Bean
    public DataSource dataSource() throws Exception {
        List<Resource> resources = new ArrayList<>();
        resources.add(new ClassPathResource("care_reporting.properties"));
        resources.add(new ClassPathResource("hibernate.properties"));
        resources.add(new ClassPathResource("postgres.properties"));
        settingsFacade.setConfigFiles(resources);

        Properties dataSourceProperties = new Properties();
        dataSourceProperties.put("driverClassName", settingsFacade.getProperty("jdbc.driverClassName"));
        dataSourceProperties.put("url", settingsFacade.getProperty("jdbc.url"));
        dataSourceProperties.put("username", settingsFacade.getProperty("jdbc.username"));
        dataSourceProperties.put("password", settingsFacade.getProperty("jdbc.password"));

        return BasicDataSourceFactory.createDataSource(dataSourceProperties);
    }
}
