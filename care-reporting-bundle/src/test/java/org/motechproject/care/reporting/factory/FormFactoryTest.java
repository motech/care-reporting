package org.motechproject.care.reporting.factory;

import org.junit.Test;
import org.motechproject.care.reporting.domain.measure.DeliveryChildForm;
import org.motechproject.care.reporting.domain.measure.DeliveryMotherForm;
import org.motechproject.care.reporting.enums.CaseType;

import static junit.framework.Assert.assertEquals;

public class FormFactoryTest{

    @Test
    public void testGetDeliveryForm() throws Exception {
        assertEquals(DeliveryMotherForm.class, FormFactory.getForm("http://bihar.commcarehq.org/pregnancy/del", CaseType.MOTHER));
        assertEquals(DeliveryChildForm.class, FormFactory.getForm("http://bihar.commcarehq.org/pregnancy/del", CaseType.CHILD));
    }
}
