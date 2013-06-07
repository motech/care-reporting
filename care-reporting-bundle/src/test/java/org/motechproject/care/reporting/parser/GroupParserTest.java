package org.motechproject.care.reporting.parser;

import org.junit.Test;
import org.motechproject.care.reporting.builder.GroupBuilder;
import org.motechproject.commcare.provider.sync.response.Group;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class GroupParserTest {
    @Test
    public void shouldParseAGivenGroupWithAppropriateKeyConversions() {
        final Group group = new GroupBuilder("3c5a80e4db53049dfc110c368a0d05d4").build();

        Map<String, Object> parsedGroupMap = new GroupParser().parse(group);

        assertEquals("3c5a80e4db53049dfc110c368a0d05d4", parsedGroupMap.get("groupId"));
        assertEquals("001", parsedGroupMap.get("awcCode"));
        assertEquals("danny team 1", parsedGroupMap.get("name"));
    }
}
