package org.motechproject.care.reporting.builder;

import org.joda.time.DateTime;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;

import java.util.Date;

public class ChildCaseBuilder {

    private final ChildCase childCase;

    public ChildCaseBuilder() {
        childCase = new ChildCase();
        childCase.setFlw(FlwBuilder.buildDefault());
        childCase.setFlwGroup(new FlwGroupBuilder()
                .groupId("5ba9a0928dde95d187544babf6c0ad48")
                .build());
        childCase.setCaseId("001");
        childCase.setCaseName("NEERAJ");
        childCase.setCaseType("cc_bihar_newborn");
    }

    public ChildCaseBuilder caseId(String caseId) {
        childCase.setCaseId(caseId);
        return this;
    }

    public ChildCase build() {
        return childCase;
    }

    public ChildCaseBuilder caseName(String name) {
        childCase.setCaseName(name);
        return this;
    }

    public ChildCaseBuilder dateModified(Date date) {
        childCase.setDateModified(date);
        return this;
    }

    public ChildCaseBuilder creationTime(Date creationTime) {
        childCase.setCreationTime(creationTime);
        return this;
    }

    public ChildCaseBuilder clear() {
        childCase.setFlw(null);
        childCase.setFlwGroup(null);
        childCase.setCaseName(null);
        return this;
    }

    public ChildCaseBuilder flw(Flw flw) {
        childCase.setFlw(flw);
        return this;
    }

    public ChildCaseBuilder flwGroup(FlwGroup flwGroup) {
        childCase.setFlwGroup(flwGroup);
        return this;
    }

    public ChildCaseBuilder close() {
        childCase.setClosed(true);
        childCase.setClosedBy(childCase.getFlw());
        childCase.setClosedOn(childCase.getDateModified());
        return this;
    }

    public ChildCaseBuilder closedBy(Flw flw) {
        childCase.setClosedBy(flw);
        return this;
    }

    public ChildCaseBuilder closedDate(Date closedOn) {
        childCase.setClosedOn(closedOn);
        return this;
    }

    public ChildCaseBuilder closed(boolean isClosed) {
        childCase.setClosed(isClosed);
        return this;
    }

    public ChildCaseBuilder serverDateModified(Date serverDateModified) {
        childCase.setServerDateModified(serverDateModified);
        return this;
    }
}
