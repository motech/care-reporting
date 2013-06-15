package org.motechproject.care.reporting.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.server.config.SettingsFacade;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MapperSettingsServiceTest {

    @Mock
    private SettingsFacade settingsFacade;
    @Captor
    private ArgumentCaptor<List<Resource>> resourceArgumentCaptor;

    MapperSettingsService mapperSettingsService;

    @Before
    public void setUp() {
        when(settingsFacade.getProperty("form.mapping.file.names")).thenReturn("formFileName1, formFileName2");
        when(settingsFacade.getProperty("case.mapping.file.names")).thenReturn("caseFileName1, caseFileName2");
        mapperSettingsService = new MapperSettingsService(settingsFacade);
    }

    @Test
    public void shouldRegisterConfigFilesAtConstruction() {
        verify(settingsFacade, times(2)).setRawConfigFiles(resourceArgumentCaptor.capture());
        List<List<Resource>> allRegisteredFiles = resourceArgumentCaptor.getAllValues();
        assertEquals(new ClassPathResource("formFileName1"), allRegisteredFiles.get(0).get(0));
        assertEquals(new ClassPathResource("formFileName2"), allRegisteredFiles.get(0).get(1));
        assertEquals(new ClassPathResource("caseFileName1"), allRegisteredFiles.get(1).get(0));
        assertEquals(new ClassPathResource("caseFileName2"), allRegisteredFiles.get(1).get(1));
    }

    @Test
    public void shouldGetInputStreamsForFormMappingFiles() throws Exception {
        List<InputStream> expectedInputStreams = Arrays.asList((InputStream) new ByteArrayInputStream(bytes()), new ByteArrayInputStream(bytes()));
        when(settingsFacade.getRawConfig("formFileName1")).thenReturn(expectedInputStreams.get(0));
        when(settingsFacade.getRawConfig("formFileName2")).thenReturn(expectedInputStreams.get(1));

        final List<InputStream> actualFormInputStreams = mapperSettingsService.getFormStreams();

        assertEquals(expectedInputStreams, actualFormInputStreams);
    }

    @Test
    public void shouldGetInputStreamsForCaseMappingFiles() throws Exception {
        List<InputStream> expectedInputStreams = Arrays.asList((InputStream) new ByteArrayInputStream(bytes()), new ByteArrayInputStream(bytes()));
        when(settingsFacade.getRawConfig("caseFileName1")).thenReturn(expectedInputStreams.get(0));
        when(settingsFacade.getRawConfig("caseFileName2")).thenReturn(expectedInputStreams.get(1));

        final List<InputStream> actualCaseInputStreams = mapperSettingsService.getCaseStreams();

        assertEquals(expectedInputStreams, actualCaseInputStreams);
    }

    private byte[] bytes(){
        return "dummy".getBytes();
    }
}
