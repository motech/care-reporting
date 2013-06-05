package org.motechproject.care.reporting.parser;

import org.motechproject.commcare.provider.sync.response.Group;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GroupParser {

    public Map<String, Object> parse(Group group) {
        Map<String, Object> groupMap = new HashMap<>();
        Map<String, Object> groupFieldMap = parseGroupInfo(group, new HashMap<String, String>() {{
            put("id", "groupId");
        }});
        groupMap.putAll(groupFieldMap);
        Map<String, Object> groupMetaInfo = parseGroupInfo(group.getMetaData(), new HashMap<String, String>() {{
            put("awc-code", "awcCode");
        }});
        groupMap.putAll(groupMetaInfo);
        return groupMap;
    }

    private Map<String, Object> parseGroupInfo(Object groupInfo, Map<String, String> keyConversionMap) {
        InfoParser infoParser = new InfoParser(keyConversionMap);
        return infoParser.parse(groupInfo);
    }
}
