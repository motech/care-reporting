package org.motechproject.care.reporting.domain.measure;

import org.junit.Test;
import org.motechproject.care.reporting.utils.FormToString;

import static junit.framework.Assert.assertEquals;

public class FormToStringTest {
    @Test
    public void shouldGenerateToStringForNwForm() throws Exception {
        NewForm newForm = new NewForm();
        newForm.setInstanceId("foobar");
        String toString = FormToString.toString(newForm);
        assertEquals("NewForm{instanceId=foobar}", toString);
    }
}
