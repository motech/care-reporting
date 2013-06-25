package org.motechproject.care.reporting.converter;

import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;

public class ConverterDemoObject {
    Flw flw;
    FlwGroup flwGroup;
    MotherCase motherCase;
    ChildCase childCase;

    public Flw getFlw() {
        return flw;
    }

    public void setFlw(Flw flw) {
        this.flw = flw;
    }

    public FlwGroup getFlwGroup() {
        return flwGroup;
    }

    public void setFlwGroup(FlwGroup flwGroup) {
        this.flwGroup = flwGroup;
    }

    public MotherCase getMotherCase() {
        return motherCase;
    }

    public void setMotherCase(MotherCase motherCase) {
        this.motherCase = motherCase;
    }

    public ChildCase getChildCase() {
        return childCase;
    }

    public void setChildCase(ChildCase childCase) {
        this.childCase = childCase;
    }
}
