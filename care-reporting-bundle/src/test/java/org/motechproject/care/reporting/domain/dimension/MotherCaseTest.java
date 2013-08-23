package org.motechproject.care.reporting.domain.dimension;

import junit.framework.Assert;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.care.reporting.builder.FlwBuilder;
import org.motechproject.care.reporting.builder.MotherCaseBuilder;
import org.motechproject.care.reporting.utils.TestUtils;

import java.util.Date;

import static junit.framework.Assert.*;

public class MotherCaseTest {

    public static final Date JAN_01 = DateTime.parse("2012-01-01").toDate();
    public static final Date DEC_01 = DateTime.parse("2012-12-01").toDate();

    @Test
    public void shouldUpdateUpdatableFields() throws Exception {
        Date jan01 = DateTime.parse("2012-01-01").toDate();
        Date dec01 = DateTime.parse("2012-12-01").toDate();
        MotherCase oldMother = new MotherCaseBuilder().caseId("01").caseName("durga").dateModified(jan01).alive("no").build();
        MotherCase updatedMother = new MotherCaseBuilder().caseId("01").caseName("devi").dateModified(dec01).alive("yes").build();

        oldMother.updateToLatest(updatedMother);

        assertEquals("devi", oldMother.getCaseName());
        assertEquals(dec01, oldMother.getDateModified());
        assertEquals("yes", oldMother.getMotherAlive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCaseIdMismatch() {
        new MotherCaseBuilder().caseId("01").build().updateToLatest(new MotherCaseBuilder().caseId("02").build());
    }

    @Test
    public void shouldNotUpdateIfServerDateModifiedOlderThanPresent() throws Exception {
        MotherCase oldMother = new MotherCaseBuilder().caseId("01").caseName("durga").serverDateModified(DEC_01).alive("no").build();
        MotherCase updatedMother = new MotherCaseBuilder().caseId("01").caseName("devi").serverDateModified(JAN_01).alive("yes").build();

        oldMother.updateToLatest(updatedMother);

        assertEquals("durga", oldMother.getCaseName());
    }

    @Test
    public void shouldUpdateTheLastModifiedTimeToCurrentTime() {
        MotherCase motherCase = new MotherCaseBuilder().caseId("01").build();

        motherCase.updateToLatest(new MotherCaseBuilder().caseId("01").build());

        TestUtils.assertDateIgnoringSeconds(new Date(), motherCase.getLastModifiedTime());
    }

    @Test
    public void shouldNotUpdateTheCreationTime() {
        MotherCase motherCase = new MotherCaseBuilder().caseId("01").build();

        motherCase.updateToLatest(new MotherCaseBuilder().caseId("01").creationTime(null).build());

        assertNotNull(motherCase.getCreationTime());
    }

    @Test
    public void shouldNotUpdateTheCloseCaseFields() {
        Flw oldFlw = new FlwBuilder().flwId("flw id1").build();
        Flw newFlw = new FlwBuilder().flwId("flw id2").build();
        MotherCase motherCase = new MotherCaseBuilder()
                .flw(oldFlw)
                .serverDateModified(JAN_01)
                .caseName("old Name").close().build();
        MotherCase newMother = new MotherCaseBuilder()
                .closedBy(newFlw)
                .serverDateModified(DEC_01)
                .closedDate(DEC_01)
                .closed(false)
                .caseName("new Name").build();

        motherCase.updateToLatest(newMother);

        assertEquals("new Name",motherCase.getCaseName());
        assertEquals(JAN_01, motherCase.getClosedOn());
        assertEquals(oldFlw, motherCase.getClosedBy());
        assertTrue(motherCase.getClosed());
    }

    @Test
    public void shouldInitializeWithCreationAndLastModifiedTime() {
        DateTime now = new DateTime();

        MotherCase motherCase = new MotherCase();

        Date creationTime = motherCase.getCreationTime();
        Date lastModifiedTime = motherCase.getLastModifiedTime();
        Assert.assertEquals(creationTime, lastModifiedTime);
        assertTrue(!now.isAfter(new DateTime(lastModifiedTime)));
    }
}
