package org.motechproject.care.reporting.migration.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CaseXmlPairTest {

    @Test
    public void shouldReturnTrueIfHasClosedAction() {
        CaseXmlPair xmlPair = new CaseXmlPair();

        assertFalse(xmlPair.hasClosedAction());

        xmlPair.setCreateUpdateAction("xml");

        assertFalse(xmlPair.hasClosedAction());

        xmlPair.setClosedAction("xml");

        assertTrue(xmlPair.hasClosedAction());
    }
}
