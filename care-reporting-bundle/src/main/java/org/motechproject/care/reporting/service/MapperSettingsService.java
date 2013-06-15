package org.motechproject.care.reporting.service;

import org.apache.commons.lang.StringUtils;
import org.motechproject.server.config.SettingsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapperSettingsService {
    private final String FORM_MAPPING_PROPERTY_KEY = "form.mapping.file.names";
    private final String CASE_MAPPING_PROPERTY_KEY = "case.mapping.file.names";
    private final String FILE_NAME_DELIMITER = ",";

    SettingsFacade settings;

    @Autowired
    public MapperSettingsService(@Qualifier("settings") SettingsFacade settings) {
        this.settings = settings;
        registerRawConfigFiles(getFileNames(getFormMappingPropertyValues()));
        registerRawConfigFiles(getFileNames(getCaseMappingPropertyValues()));

    }

    private void registerRawConfigFiles(String[] mappingFileNames) {
        final List mappingFiles = new ArrayList<ClassPathResource>();
        for (String mappingFileName : mappingFileNames) {
            mappingFiles.add(new ClassPathResource(mappingFileName));
        }
        settings.setRawConfigFiles(mappingFiles);
    }

    public List<InputStream> getFormStreams() {
        return getStreams(getFileNames(getFormMappingPropertyValues()));
    }

    public List<InputStream> getCaseStreams() {
        return getStreams(getFileNames(getCaseMappingPropertyValues()));
    }

    private List<InputStream> getStreams(String[] configFiles) {
        final List<InputStream> inputStreams = new ArrayList<>();
        for (String configFile : configFiles) {
            inputStreams.add(settings.getRawConfig(configFile));
        }
        return inputStreams;
    }

    private String getCaseMappingPropertyValues() {
        return settings.getProperty(CASE_MAPPING_PROPERTY_KEY);
    }

    private String getFormMappingPropertyValues() {
        return settings.getProperty(FORM_MAPPING_PROPERTY_KEY);
    }

    private String[] getFileNames(String mappingPropertyValue) {
        String[] mappingFileNames = new String[]{};
        if (StringUtils.isNotEmpty(mappingPropertyValue)) {
            mappingFileNames = StringUtils.stripAll(mappingPropertyValue.split(FILE_NAME_DELIMITER));
        }
        return mappingFileNames;
    }
}
