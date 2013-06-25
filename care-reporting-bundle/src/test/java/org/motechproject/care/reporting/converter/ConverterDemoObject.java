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

    void setFlw(Flw flw) {
        this.flw = flw;
    }

    void setFlwGroup(FlwGroup flwGroup) {
        this.flwGroup = flwGroup;
    }

    void setMotherCase(MotherCase motherCase) {
        this.motherCase = motherCase;
    }

    void setChildCase(ChildCase childCase) {
        this.childCase = childCase;
    }

    Flw getFlw() {
        return flw;
    }

    FlwGroup getFlwGroup() {
        return flwGroup;
    }

    MotherCase getMotherCase() {
        return motherCase;
    }

    ChildCase getChildCase() {
        return childCase;
    }
}
