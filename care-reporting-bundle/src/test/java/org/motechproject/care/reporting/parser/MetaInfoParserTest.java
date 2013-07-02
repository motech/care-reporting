package org.motechproject.care.reporting.parser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.commcare.domain.CommcareForm;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MetaInfoParserTest {

    @Mock
    InfoParser infoParser;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldParseMetaInfo() throws Exception {
        CommcareForm commcareForm = new CommcareFormBuilder().addAttribute("xmlns", "http://bihar.commcarehq.org/pregnancy/ebf")
                .addMetadata("user_id", "89fda0284e008d2e0c980fb13fa0e5bb").build();

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("user_id", "89fda0284e008d2e0c980fb13fa0e5bb");
            put("xmlns", "http://bihar.commcarehq.org/pregnancy/ebf");
        }};

        HashMap<String, Object> meta = new HashMap<>();
        meta.put("user_id", "89fda0284e008d2e0c980fb13fa0e5bb");
        when(infoParser.parse(commcareForm.getMetadata())).thenReturn(meta);

        Map<String, String> metaInfo = new MetaInfoParser(infoParser).parse(commcareForm);

        assertEquals(expected, metaInfo);
    }
}
