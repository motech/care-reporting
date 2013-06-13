package org.motechproject.care.reporting.parser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.motechproject.care.reporting.builder.GroupBuilder;
import org.motechproject.commcare.provider.sync.response.Group;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GroupParserTest {


    @Mock
    InfoParser infoParser;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void shouldParseGroupWithKeyConversion(){
        final Group group = new GroupBuilder("3c5a80e4db53049dfc110c368a0d05d4").build();
        final Object metaData = group.getMetaData();

        final HashMap<String, Object> groupMap = new HashMap<String, Object>() {{
            put("groupName", "mygroup");
        }};

        final HashMap<String, Object> metaMap = new HashMap<String, Object>() {{
            put("metaName", "mymeta");
        }};
        when(infoParser.parse(group)).thenReturn(groupMap);
        when(infoParser.parse(metaData)).thenReturn(metaMap);

        final Map<String, Object> actualParsedMap = new GroupParser(infoParser).parse(group);

        Map<String, Object> expectedParsedMap = new HashMap<>();
        expectedParsedMap.putAll(groupMap);
        expectedParsedMap.putAll(metaMap);

        Map<String, String> groupFieldMap = new HashMap<String, String>() {{
            put("id", "groupId");
        }};

        Map<String, String> groupMetaFieldMap = new HashMap<String, String>() {{
            put("awc-code", "awcCode");
        }};

        ReflectionAssert.assertReflectionEquals(expectedParsedMap, actualParsedMap);
        verify(infoParser).setKeyConversionMap(groupMetaFieldMap);
        verify(infoParser).parse(metaData);
        verify(infoParser).setKeyConversionMap(groupFieldMap);
        verify(infoParser).parse(group);

    }
}
