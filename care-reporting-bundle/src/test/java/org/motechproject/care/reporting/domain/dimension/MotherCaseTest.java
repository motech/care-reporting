package org.motechproject.care.reporting.domain.dimension;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.care.reporting.builder.MotherCaseBuilder;
import org.motechproject.care.reporting.domain.measure.NewForm;

import java.util.Date;

import static junit.framework.Assert.*;

public class MotherCaseTest {
    @Test
    public void shouldUpdateUpdatableFields() throws Exception {
        Date jan01 = DateTime.parse("2012-01-01").toDate();
        Date dec01 = DateTime.parse("2012-12-01").toDate();
        MotherCase oldMother = new MotherCaseBuilder().caseId("01").caseName("durga").dateModified(jan01).alive(false).build();
        MotherCase updatedMother = new MotherCaseBuilder().caseId("01").caseName("devi").dateModified(dec01).alive(true).build();

        oldMother.updateToLatest(updatedMother);

        assertEquals("devi", oldMother.getCaseName());
        assertEquals(dec01, oldMother.getDateModified());
        assertTrue(oldMother.getMotherAlive());
    }

    @Test
    public void shouldNotUpdateFormAndIdFields() throws Exception {
        NewForm oldNewForm = new NewForm(0);
        MotherCase oldMother = new MotherCaseBuilder().caseId("01").newForm(oldNewForm).build();
        NewForm newForm = new NewForm(1);
        MotherCase updatedMother = new MotherCaseBuilder().caseId("01").newForm(newForm).build();

        oldMother.updateToLatest(updatedMother);

        assertEquals(1, oldMother.getNewForms().size());
        assertSame(oldNewForm, oldMother.getNewForms().iterator().next());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCaseIdMismatch() {
        new MotherCaseBuilder().caseId("01").build().updateToLatest(new MotherCaseBuilder().caseId("02").build());
    }

    @Test
    public void shouldNotUpdateIfDateModifiedOlderThanPresent() throws Exception {
        Date jan01 = DateTime.parse("2012-01-01").toDate();
        Date dec01 = DateTime.parse("2012-12-01").toDate();
        MotherCase oldMother = new MotherCaseBuilder().caseId("01").caseName("durga").dateModified(dec01).alive(false).build();
        MotherCase updatedMother = new MotherCaseBuilder().caseId("01").caseName("devi").dateModified(jan01).alive(true).build();

        oldMother.updateToLatest(updatedMother);

        assertEquals("durga", oldMother.getCaseName());
    }
}
