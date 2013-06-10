package org.motechproject.care.reporting.builder;

import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;

import java.util.HashSet;

public class FlwBuilder {
    Flw flw;

    public FlwBuilder() {
        flw = new Flw();
    }

    public FlwBuilder flwId(String flwId) {
        flw.setFlwId(flwId);
        return this;
    }

    public FlwBuilder firstName(String firstName) {
        flw.setFirstName(firstName);
        return this;
    }

    public FlwBuilder flwGroups(HashSet<FlwGroup> flwGroups) {
        flw.setFlwGroups(flwGroups);
        return this;
    }

    public Flw build() {
        return flw;
    }

    public static Flw buildDefault() {
        return new FlwBuilder()
                .flwId("5ba9a0928dde95d187544babf6c0ad24")
                .firstName("FirstName1")
                .flwGroups(FlwGroupBuilder.buildDefaultSet())
                .build();
    }
}
