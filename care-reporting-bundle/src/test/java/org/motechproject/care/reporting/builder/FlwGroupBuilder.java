package org.motechproject.care.reporting.builder;

import org.motechproject.care.reporting.domain.dimension.FlwGroup;

public class FlwGroupBuilder {

    private final FlwGroup flwGroup;

    public FlwGroupBuilder() {
        flwGroup = new FlwGroup();
    }

    public FlwGroupBuilder groupId(String groupId) {
        flwGroup.setGroupId(groupId);
        return this;
    }

    public FlwGroup build() {
        return flwGroup;
    }

    public FlwGroupBuilder name(String name) {
        flwGroup.setName(name);
        return this;
    }

    public FlwGroupBuilder domain(String domain) {
        flwGroup.setDomain(domain);
        return this;
    }

    public FlwGroupBuilder awcCode(String awcCode) {
        flwGroup.setAwcCode(awcCode);
        return this;
    }

    public FlwGroupBuilder caseSharing(boolean caseSharing) {
        flwGroup.setCaseSharing(caseSharing);
        return this;
    }

    public FlwGroupBuilder reporting(boolean reporting) {
        flwGroup.setReporting(reporting);
        return this;
    }
}
