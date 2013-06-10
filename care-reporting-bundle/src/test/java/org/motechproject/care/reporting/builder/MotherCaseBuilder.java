package org.motechproject.care.reporting.builder;

import org.joda.time.DateTime;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.domain.measure.NewForm;

import java.util.Date;

public class MotherCaseBuilder {

    private final MotherCase motherCase;

    public MotherCaseBuilder() {
        motherCase = new MotherCase();
        motherCase.setFlw(FlwBuilder.buildDefault());
        motherCase.setFlwGroup(FlwGroupBuilder.buildDefault());
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

    public MotherCaseBuilder alive(boolean isAlive) {
        motherCase.setMotherAlive(isAlive);
        return this;
    }

    public MotherCaseBuilder newForm(NewForm newForm) {
        motherCase.getNewForms().add(newForm);
        return this;
    }
}
