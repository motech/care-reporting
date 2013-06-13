package org.motechproject.care.reporting.service;

import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.enums.CaseType;
import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.care.reporting.parser.GroupParser;
import org.motechproject.care.reporting.parser.InfoParser;
import org.motechproject.care.reporting.parser.ProviderParser;

import static org.junit.Assert.assertNotNull;

public class MapperServiceTest {

    MapperService mapperService;
    String namespace = "http://bihar.commcarehq.org/pregnancy/";

    @Before
    public void setUp(){
        mapperService = new MapperService();
    }

    @Test
    public void testGetFormInfoParser() throws Exception {
        InfoParser infoParser = mapperService.getFormInfoParser(namespace + "new", FormSegment.Metadata, "1");
        assertNotNull(infoParser);
    }

    @Test
    public void testGetCaseInfoParser() throws Exception {
        InfoParser infoParser = mapperService.getCaseInfoParser(CaseType.Mother, "1");
        assertNotNull(infoParser);
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
