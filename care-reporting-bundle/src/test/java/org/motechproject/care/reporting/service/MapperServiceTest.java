package org.motechproject.care.reporting.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.care.reporting.enums.CaseType;
import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.care.reporting.model.MappingEntity;
import org.motechproject.care.reporting.parser.GroupParser;
import org.motechproject.care.reporting.parser.InfoParser;
import org.motechproject.care.reporting.parser.InfoParserImpl;
import org.motechproject.care.reporting.parser.ProviderParser;
import org.motechproject.care.reporting.processors.BestMatchProcessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MapperServiceTest {
    @Mock
    private MapperSettingsService mapperSettingsService;
    @Mock
    private BestMatchProcessor bestMatchProcessor1;
    @Mock
    private BestMatchProcessor bestMatchProcessor2;

    private MapperService mapperService;
    private String namespace = "http://bihar.commcarehq.org/pregnancy/";

    @Before
    public void setUp() {
        mapperService = new MapperService(bestMatchProcessor1, bestMatchProcessor2);
    }

    @Test
    public void shouldGetFormAndCaseStreamsToGetBestMatchProcessor() {
        mapperService = new MapperService(mapperSettingsService);

        verify(mapperSettingsService).getFormStreams();
        verify(mapperSettingsService).getCaseStreams();
    }

    @Test
    public void shouldGetMappingEntityForFormAndGetInfoParser() throws Exception {
        final String identifier = namespace + "new";
        final String version = "1";
        final FormSegment metadata = FormSegment.METADATA;
        final InfoParserImpl expectedInfoParser = new InfoParserImpl();
        final MappingEntity mappingEntity = mock(MappingEntity.class);
        when(bestMatchProcessor1.getBestMatch(identifier, version, metadata.name())).thenReturn(mappingEntity);
        when(mappingEntity.getInfoParser()).thenReturn(expectedInfoParser);

        InfoParser infoParser = mapperService.getFormInfoParser(identifier, version, metadata);

        assertEquals(expectedInfoParser, infoParser);
    }

    @Test
    public void shouldGetMappingEntityForCaseAndGetInfoParser() throws Exception {
        final CaseType caseType = CaseType.MOTHER;
        final String version = "1";
        final InfoParserImpl expectedInfoParser = new InfoParserImpl();
        final MappingEntity mappingEntity = mock(MappingEntity.class);
        when(bestMatchProcessor2.getBestMatch(caseType.name(), version)).thenReturn(mappingEntity);
        when(mappingEntity.getInfoParser()).thenReturn(expectedInfoParser);

        InfoParser infoParser = mapperService.getCaseInfoParser(caseType, version);

        assertEquals(expectedInfoParser, infoParser);
    }

    @Test
    public void testGetGroupInfoParser() throws Exception {
        GroupParser groupInfoParser = mapperService.getGroupInfoParser();
        assertNotNull(groupInfoParser);
    }

    @Test
    public void testGetProviderInfoParser() {
        ProviderParser providerInfoParser = mapperService.getProviderInfoParser();
        assertNotNull(providerInfoParser);
    }
}
