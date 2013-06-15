package org.motechproject.care.reporting.enums;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CaseTypeTest {

    @Test
    public void shouldGetMotherCaseType(){
        assertEquals(CaseType.MOTHER, CaseType.getType("cc_bihar_pregnancy"));
    }

    @Test
    public void shouldGetChildCaseType(){
        assertEquals(CaseType.CHILD, CaseType.getType("cc_bihar_newborn"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfCaseTypeInvalid(){
        assertEquals(null, CaseType.getType("cc_unknown"));
    }

}
