package org.motechproject.care.reporting.enums;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CaseTypeTest {

    @Test
    public void shouldGetMotherCaseType(){
        assertEquals(CaseType.Mother, CaseType.getType("cc_bihar_pregnancy"));
    }

    @Test
    public void shouldGetChildCaseType(){
        assertEquals(CaseType.Child, CaseType.getType("cc_bihar_newborn"));
    }

    @Test
    public void shouldReturnNullIfCaseTypeDoesNotExist(){
        assertEquals(null, CaseType.getType("cc_unknown"));
    }

}
