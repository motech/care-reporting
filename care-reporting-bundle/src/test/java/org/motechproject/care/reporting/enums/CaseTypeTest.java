package org.motechproject.care.reporting.enums;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertEquals;

public class CaseTypeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnCaseType() throws Exception {
        assertEquals(CaseType.MOTHER, CaseType.getType("cc_bihar_pregnancy"));
        assertEquals(CaseType.CHILD, CaseType.getType("cc_bihar_newborn"));
        assertEquals(CaseType.TASK, CaseType.getType("task"));
    }

    @Test
    public void shouldThrowExceptionIfCaseTypeStringIsNotExactMatch() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Cannot find CaseType for value: cc_Bihar_pregnancy");

        CaseType.getType("cc_Bihar_pregnancy");

        expectedException.expectMessage("Cannot find CaseType for value:  cc_bihar_pregnancy");

        CaseType.getType(" cc_bihar_pregnancy");
    }

    @Test
    public void shouldThrowExceptionIfCaseTypeIsNotFound() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Cannot find CaseType for value: unknown");

        CaseType.getType("unknown");

        expectedException.expectMessage("Cannot find CaseType for value: null");

        CaseType.getType(null);

        expectedException.expectMessage("Cannot find CaseType for value: ");

        CaseType.getType("");

        expectedException.expectMessage("Cannot find CaseType for value:   ");

        CaseType.getType("  ");
    }

}
