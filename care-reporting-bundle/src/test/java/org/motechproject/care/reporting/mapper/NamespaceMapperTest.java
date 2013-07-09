package org.motechproject.care.reporting.mapper;


import org.junit.Test;
import org.motechproject.care.reporting.enums.FormType;

import static junit.framework.Assert.assertEquals;

public class NamespaceMapperTest {

    @Test
    public void testNamespaces() {
        assertEquals(FormType.New, getFormType("new"));
        assertEquals(FormType.Registration, getFormType("registration"));
        assertEquals(FormType.Bp, getFormType("bp"));
        assertEquals(FormType.Ebf, getFormType("ebf"));
        assertEquals(FormType.Cf, getFormType("cf"));
        assertEquals(FormType.Pnc, getFormType("pnc"));
        assertEquals(FormType.Refer, getFormType("refer"));
        assertEquals(FormType.Death, getFormType("death"));
        assertEquals(FormType.Delivery, getFormType("del"));
        assertEquals(FormType.Close, getFormType("close"));
        assertEquals(FormType.Mo, getFormType("migrate_out"));
        assertEquals(FormType.Mi, getFormType("migrate_in"));
        assertEquals(FormType.Abort, getFormType("mtp_abort"));
        assertEquals(FormType.Ui, getFormType("update_vaccinations"));
    }

    private FormType getFormType(String formNs) {
        String prefix = "http://bihar.commcarehq.org/pregnancy/";
        return NamespaceMapper.getFormType(prefix + formNs);
    }

    @Test
    public void shouldGetFormTypeForToolsNamespace() {
        assertEquals(FormType.MoveBeneficiary, NamespaceMapper.getFormType("http://bihar.commcarehq.org/tools/move_beneficiary"));
    }
}
