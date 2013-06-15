package org.motechproject.care.reporting.utils;

import org.junit.Test;
import org.motechproject.care.reporting.model.MappingEntity;
import org.motechproject.care.reporting.parser.InfoParser;
import org.motechproject.care.reporting.parser.InfoParserImpl;
import org.unitils.reflectionassert.ReflectionAssert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class JsonUtilsTest {

    @Test
    public void shouldParseGivenInputStreamsToMappingEntities() {
        InputStream stream = new ByteArrayInputStream(getSampleJson().getBytes());
        InfoParser expectedInfoParser = new InfoParserImpl();
        expectedInfoParser.setConvertToCamelCase(false);
        expectedInfoParser.setRestrictedElements(asList("case", "child_info"));
        final HashMap<String, String> keyConversionMap = new HashMap<String, String>() {{
            put("instance_ID", "instanceID");
        }};
        expectedInfoParser.setKeyConversionMap(keyConversionMap);

        final List<MappingEntity> mappingEntities = JsonUtils.parseStreams(asList(stream));

        assertEquals(1, mappingEntities.size());
        MappingEntity entity = mappingEntities.get(0);
        assertEquals("myId", entity.getIdentifier());
        assertEquals("myVersion", entity.getVersion());
        assertEquals("mySegment", entity.getSegment());
        assertNotNull(entity.getInfoParser());
        ReflectionAssert.assertReflectionEquals(expectedInfoParser, entity.getInfoParser());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionForNullStreamOrForIOErrors() {
        InputStream inputStream = null;

        JsonUtils.parseStreams(asList(inputStream));
    }

    private String getSampleJson(){
        return "["
                +"{\""
                +   "identifier\": \"myId\",\""
                +       "version\": \"myVersion\",\""
                +       "segment\": \"mySegment\",\""
                +       "parser\": {"
                +   "\"convertToCamelCase\": false,"
                +           "\"restrictedElements\": [\"case\", \"child_info\"],"
                +   "\"keyConversionMap\": {"
                +       "\"instance_ID\": \"instanceID\""
                +   "}"
                +"}"
                +"}"
                +"]";
    }
}
