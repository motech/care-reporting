package org.motechproject.care.reporting.factory;

import org.junit.Test;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.MotherCase;

import static junit.framework.Assert.assertEquals;

public class CaseFactoryTest {
    @Test
    public void testGetMotherCase() throws Exception {
        assertEquals(MotherCase.class, CaseFactory.getCase("cc_bihar_pregnancy"));
    }

    @Test
    public void testGetChildCase() throws Exception {
        assertEquals(ChildCase.class, CaseFactory.getCase("cc_bihar_newborn"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfTryingToGetInvalidCase() throws Exception {
        CaseFactory.getCase("cc_unknown");
    }

}
