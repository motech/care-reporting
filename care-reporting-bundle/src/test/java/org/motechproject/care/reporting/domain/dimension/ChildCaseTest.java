package org.motechproject.care.reporting.domain.dimension;

import junit.framework.Assert;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.care.reporting.domain.measure.CfChildForm;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

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

        HashSet<CfChildForm> cfChildForms = new HashSet<>();
        oldChild.setCfChildForms(cfChildForms);

        ChildCase updatedChild = new ChildCase(2);
        updatedChild.setCaseId("656a96a1-af77-4dca-9dd0-579d933733da");
        updatedChild.setName("new name");
        updatedChild.setDateModified(JAN_10);

        oldChild.updateToLatest(updatedChild);

        assertSame(cfChildForms, oldChild.getCfChildForms());
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

    private ChildCase childCaseWithDateModified(Date date, String name) {
        ChildCase childCase = new ChildCase();
        childCase.setCaseId("001");
        childCase.setDateModified(date);
        childCase.setName(name);
        return childCase;
    }
}
