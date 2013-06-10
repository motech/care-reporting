package org.motechproject.care.reporting.builder;

import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;

import java.util.HashSet;

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

    public static HashSet<FlwGroup> buildDefaultSet() {
        return new HashSet<FlwGroup>() {{
            add(buildDefault());
        }};
    }

    public static FlwGroup buildDefault() {
        return new FlwGroupBuilder()
                .groupId("5ba9a0928dde95d187544babf6c0ad24")
                .name("afrisis team 1")
                .domain("care-bihar")
                .awcCode("001")
                .caseSharing(true)
                .reporting(true)
                .build();
    }

    public FlwGroupBuilder addFlw(Flw flw) {
        flwGroup.getFlws().add(flw);
        return this;
    }
}
