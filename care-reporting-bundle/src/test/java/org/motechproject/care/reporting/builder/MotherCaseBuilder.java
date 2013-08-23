package org.motechproject.care.reporting.builder;

import org.joda.time.DateTime;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;

import java.util.Date;

public class MotherCaseBuilder {

    private final MotherCase motherCase;

    public MotherCaseBuilder() {
        motherCase = new MotherCase();
        motherCase.setFlw(FlwBuilder.buildDefault());
        motherCase.setFlwGroup(new FlwGroupBuilder()
                .groupId("5ba9a0928dde95d187544babf6c0ad48")
                .build());
        motherCase.setCaseId("001");
        motherCase.setCaseName("NEERAJ");
        motherCase.setCaseType("cc_bihar_pregnancy");
        motherCase.setDateLastVisit(new DateTime(2012, 4, 3, 0, 0).toDate());
        motherCase.setHhNumber(3);
        motherCase.setHusbandName("DA");
    }

    public MotherCaseBuilder caseId(String caseId) {
        motherCase.setCaseId(caseId);
        return this;
    }

    public MotherCase build() {
        return motherCase;
    }

    public MotherCaseBuilder caseName(String name) {
        motherCase.setCaseName(name);
        return this;
    }

    public MotherCaseBuilder dateModified(Date date) {
        motherCase.setDateModified(date);
        return this;
    }

    public MotherCaseBuilder alive(String isAlive) {
        motherCase.setMotherAlive(isAlive);
        return this;
    }

    public MotherCaseBuilder creationTime(Date creationTime) {
        motherCase.setCreationTime(creationTime);
        return this;
    }

    public MotherCaseBuilder clear() {
        motherCase.setFlw(null);
        motherCase.setFlwGroup(null);
        motherCase.setCaseName(null);
        motherCase.setDateLastVisit(null);
        motherCase.setHhNumber(null);
        motherCase.setHusbandName(null);
        return this;
    }

    public MotherCaseBuilder flw(Flw flw) {
        motherCase.setFlw(flw);
        return this;
    }

    public MotherCaseBuilder flw(String flwId) {
        motherCase.setFlw(new FlwBuilder().flwId(flwId).build());
        return this;
    }

    public MotherCaseBuilder flwGroup(FlwGroup flwGroup) {
        motherCase.setFlwGroup(flwGroup);
        return this;
    }

    public MotherCaseBuilder close() {
        motherCase.setClosed(true);
        motherCase.setClosedBy(motherCase.getFlw());
        motherCase.setClosedOn(motherCase.getServerDateModified());
        return this;
    }

    public MotherCaseBuilder closedBy(Flw flw) {
        motherCase.setClosedBy(flw);
        return this;
    }

    public MotherCaseBuilder closedDate(Date closedOn) {
        motherCase.setClosedOn(closedOn);
        return this;
    }

    public MotherCaseBuilder closed(boolean isClosed) {
        motherCase.setClosed(isClosed);
        return this;
    }

    public MotherCaseBuilder serverDateModified(Date serverModifiedOn) {
        motherCase.setServerDateModified(serverModifiedOn);
        return this;
    }
}
