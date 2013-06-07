package org.motechproject.care.reporting.builder;

import org.motechproject.care.reporting.utils.TestUtils;
import org.motechproject.commcare.provider.sync.response.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GroupBuilder {
    private final Group group;

    public GroupBuilder(String groupId) {
        group = new Group();
        setField("id", groupId);
        setField("caseSharing", true);
        setField("domain", "care-bihar");
        setField("id", groupId);
        setField("path", new ArrayList<>());
        setField("name", "danny team 1");
        setField("reporting", true);
        setField("resourceUri", "");
        setField("metaData", new HashMap<String, String>() {{
            put("awc-code", "001");
        }});
        setField("users", Arrays.asList("67bffa913b38e7901851d863eded0809", "67bffa913b38e7901851d863edecfb4a"));
    }

    private void setField(String fieldName, Object value) {
        TestUtils.setField(group, fieldName, value);
    }

    public Group build() {
        return group;
    }
}
