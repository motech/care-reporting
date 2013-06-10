package org.motechproject.care.reporting.domain.dimension;

import org.junit.Test;
import org.motechproject.care.reporting.domain.measure.CfChildForm;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ChildCaseTest {

    @Test
    public void shouldUpdateOnlyRequiredFields() {
        ChildCase oldChild = new ChildCase(1);
        oldChild.setCaseId("656a96a1-af77-4dca-9dd0-579d933733da");
        oldChild.setName("old name");
        HashSet<CfChildForm> cfChildForms = new HashSet<>();
        oldChild.setCfChildForms(cfChildForms);

        ChildCase updatedChild = new ChildCase(2);
        updatedChild.setCaseId("656a96a1-af77-4dca-9dd0-579d933733da");
        updatedChild.setName("new name");

        oldChild.updateFrom(updatedChild);

        assertSame(cfChildForms, oldChild.getCfChildForms());
        assertEquals("new name", oldChild.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfCaseIdDontMatch() throws Exception {
        ChildCase childCase = new ChildCase();
        childCase.setCaseId("001");

        ChildCase newChildCase = new ChildCase();
        newChildCase.setCaseId("002");

       childCase.updateFrom(newChildCase);
    }
}
