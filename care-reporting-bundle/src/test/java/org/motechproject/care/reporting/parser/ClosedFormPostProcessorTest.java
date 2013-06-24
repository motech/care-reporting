package org.motechproject.care.reporting.parser;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ClosedFormPostProcessorTest {

    private ClosedFormPostProcessor closedFormPostProcessor;

    @Before
    public void setUp() throws Exception {
        closedFormPostProcessor = new ClosedFormPostProcessor();
    }

    @Test
    public void shouldAddCloseFields() throws Exception {
        Map<String, String> input = new HashMap<String, String>() {{
            put("close", null);
            put("userID", "myuserid");
            put("dateModified", "mydatemodified");
        }};

        closedFormPostProcessor.transform(input);

        assertEquals("true", input.get("close"));
        assertEquals("myuserid",input.get("closedBy"));
        assertEquals("mydatemodified",input.get("closedOn"));
    }

    @Test
    public void shouldNotAddClosedFieldsIfCloseNodeNotPresent() throws Exception {
        Map<String, String> input = new HashMap<String, String>() {{
            put("userID", "myuserid");
            put("dateModified", "mydatemodified");
        }};

        closedFormPostProcessor.transform(input);

        assertFalse(input.containsKey("close"));
        assertFalse(input.containsKey("closedBy"));
        assertFalse(input.containsKey("closedOn"));
    }

}
