package org.motechproject.care.reporting.parser;

import org.junit.Test;
import org.motechproject.care.reporting.utils.TestUtils;
import org.motechproject.commcare.provider.sync.response.Group;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class GroupParserTest {
    @Test
    public void shouldParseAGivenGroupWithAppropriateKeyConversions() {
        final Group group = group();

        Map<String, String> parsedGroupMap = new GroupParser().parse(group);

        assertEquals("3c5a80e4db53049dfc110c368a0d05d4", parsedGroupMap.get("groupId"));
        assertEquals("038", parsedGroupMap.get("awcCode"));
        assertEquals("groupName1", parsedGroupMap.get("name"));
    }

    private Group group() {
        Group group = new Group();
        TestUtils.setField(group, new HashMap<String, Object>() {{
            put("id", "3c5a80e4db53049dfc110c368a0d05d4");
            put("name", "groupName1");
            put("metaData", new HashMap<String, String>() {{
                put("awc-code", "038");
            }});
        }});
        return group;
    }
}
