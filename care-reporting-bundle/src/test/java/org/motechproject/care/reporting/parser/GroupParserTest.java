package org.motechproject.care.reporting.parser;

import org.codehaus.jackson.annotate.JsonProperty;
import org.junit.Test;
import org.motechproject.commcare.provider.sync.response.Group;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertTrue;

public class GroupParserTest {
    @Test
    public void shouldParseAGivenGroupWithAppropriateKeyConversions() throws InvocationTargetException, IllegalAccessException {
        final Group group = new Group() {
            @JsonProperty
            private String id = "groupId1";
            @JsonProperty
            private Map<String, String> metaData = new HashMap<String, String>() {{
                put("awc-code", "groupAwcCode1");
            }};
            @JsonProperty
            private String name = "groupName1";

            @Override
            public Map<String, String> getMetaData() {
                return metaData;
            }
        };

        Map<String, String> parsedGroupMap = new GroupParser().parse(group);

        assertTrue(parsedGroupMap.containsKey("groupId"));
        assertTrue(parsedGroupMap.containsValue("groupId1"));
        assertTrue(parsedGroupMap.containsKey("awcCode"));
        assertTrue(parsedGroupMap.containsValue("groupAwcCode1"));
        assertTrue(parsedGroupMap.containsKey("name"));
        assertTrue(parsedGroupMap.containsValue("groupName1"));
    }
}
