package org.motechproject.care.reporting.domain.dimension;

import junit.framework.Assert;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.care.reporting.builder.ChildCaseBuilder;
import org.motechproject.care.reporting.builder.FlwBuilder;
import org.motechproject.care.reporting.builder.MotherCaseBuilder;

import java.util.Date;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.motechproject.care.reporting.utils.TestUtils.assertDateIgnoringSeconds;

public class ChildCaseTest {

    public static final Date JAN_09 = DateTime.parse("2012-01-09").toDate();
    public static final Date JAN_10 = DateTime.parse("2012-01-10").toDate();
    public static final Date JAN_11 = DateTime.parse("2012-01-11").toDate();

    @Test
    public void shouldUpdateOnlyRequiredFields() {
        ChildCase oldChild = new ChildCase(1);
        oldChild.setCaseId("656a96a1-af77-4dca-9dd0-579d933733da");
        oldChild.setName("old name");
        oldChild.setDateModified(JAN_09);

        ChildCase updatedChild = new ChildCase(2);
        updatedChild.setCaseId("656a96a1-af77-4dca-9dd0-579d933733da");
        updatedChild.setName("new name");
        updatedChild.setDateModified(JAN_10);

        oldChild.updateToLatest(updatedChild);

        assertEquals("new name", oldChild.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfCaseIdDontMatch() throws Exception {
        ChildCase childCase = new ChildCase();
        childCase.setCaseId("001");

        ChildCase newChildCase = new ChildCase();
        newChildCase.setCaseId("002");

        childCase.updateToLatest(newChildCase);
    }

    @Test
    public void shouldNotUpdateIfDateModifiedOlderThanPresent() throws Exception {
        ChildCase existingChildCase = childCaseWithDateModified(JAN_10, "Old Name");
        ChildCase newChildCase = childCaseWithDateModified(JAN_09, "New Name");

        existingChildCase.updateToLatest(newChildCase);

        assertEquals("Old Name", existingChildCase.getName());
    }

    @Test
    public void shouldUpdateIfDateModifiedSameAsPresent() throws Exception {
        ChildCase existingChildCase = childCaseWithDateModified(JAN_10, "Old Name");
        ChildCase newChildCase = childCaseWithDateModified(JAN_10, "New Name");

        existingChildCase.updateToLatest(newChildCase);

        assertEquals("New Name", existingChildCase.getName());
    }

    @Test
    public void shouldUpdateIfDateModifiedNewerThanPresent() throws Exception {
        ChildCase existingChildCase = childCaseWithDateModified(JAN_10, "Old Name");
        ChildCase newChildCase = childCaseWithDateModified(JAN_11, "New Name");

        existingChildCase.updateToLatest(newChildCase);

        assertEquals("New Name", existingChildCase.getName());
    }

    @Test
    public void shouldUpdateIfCurrentDateModifiedIsNull() throws Exception {
        ChildCase existingChildCase = childCaseWithDateModified(null, "Old Name");
        ChildCase newChildCase = childCaseWithDateModified(JAN_11, "New Name");

        existingChildCase.updateToLatest(newChildCase);

        assertEquals("New Name", existingChildCase.getName());
    }

    @Test
    public void shouldNotUpdateIfUpdatedDateModifiedIsNull() throws Exception {
        ChildCase existingChildCase = childCaseWithDateModified(JAN_09, "Old Name");
        ChildCase newChildCase = childCaseWithDateModified(null, "New Name");

        existingChildCase.updateToLatest(newChildCase);

        assertEquals("Old Name", existingChildCase.getName());
    }

    @Test
    public void shouldUpdateIfBothDateModifiedIsNull() throws Exception {
        ChildCase existingChildCase = childCaseWithDateModified(null, "Old Name");
        ChildCase newChildCase = childCaseWithDateModified(null, "New Name");

        existingChildCase.updateToLatest(newChildCase);

        assertEquals("New Name", existingChildCase.getName());
    }

    @Test
    public void shouldUpdateLastModifiedToCurrentTime(){
        ChildCase childCase = childCaseWithDateModified(null, null);

        childCase.updateToLatest(childCaseWithDateModified(null, null));

        assertDateIgnoringSeconds(new Date(), childCase.getLastModifiedTime());
    }

    @Test
    public void shouldNotUpdateCreationTimeOfChildCase(){
        ChildCase childCase = childCaseWithDateModified(null, null);
        ChildCase updatedChildCase = childCaseWithDateModified(null, null);
        updatedChildCase.setCreationTime(null);

        childCase.updateToLatest(updatedChildCase);

        assertNotNull(childCase.getCreationTime());
    }

    @Test
    public void shouldNotUpdateTheCloseCaseFields() {
        Flw oldFlw = new FlwBuilder().flwId("flw id1").build();
        Flw newFlw = new FlwBuilder().flwId("flw id2").build();
        ChildCase oldChild = new ChildCaseBuilder()
                .flw(oldFlw)
                .dateModified(JAN_09)
                .caseName("old Name").close().build();
        ChildCase newChild = new ChildCaseBuilder()
                .closedBy(newFlw)
                .dateModified(JAN_10)
                .closedDate(JAN_10)
                .closed(false)
                .caseName("new Name").build();

        oldChild.updateToLatest(newChild);

        assertEquals("new Name",oldChild.getCaseName());
        assertEquals(JAN_09, oldChild.getClosedOn());
        assertEquals(oldFlw, oldChild.getClosedBy());
        assertTrue(oldChild.getClosed());
    }

    private ChildCase childCaseWithDateModified(Date date, String name) {
        ChildCase childCase = new ChildCase();
        childCase.setCaseId("001");
        childCase.setDateModified(date);
        childCase.setName(name);
        return childCase;
    }
}
