package org.motechproject.care.reporting.parser;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PostProcessorTest {
    @Test
    public void shouldCopyCaseIdAsMotherCasePostProcessor() throws Exception {
        HashMap<String, String> input = new HashMap<String, String>();
        input.put("caseId", "94d5374f-290e-409f-bc57-86c2e4bcc43f");
        PostProcessor.COPY_CASE_ID_AS_MOTHER_CASE_POST_PROCESSOR.transform(input);
        assertEquals("94d5374f-290e-409f-bc57-86c2e4bcc43f", input.get("motherCase"));
    }

    @Test
    public void shouldCopyUserIDAsFlwIdPostProcessor() throws Exception {
        HashMap<String, String> input = new HashMap<String, String>();
        input.put("userID", "89fda0284e008d2e0c980fb13fa0e5bb");
        PostProcessor.COPY_USER_ID_AS_FLW_ID_POST_PROCESSOR.transform(input);
        assertEquals("89fda0284e008d2e0c980fb13fa0e5bb", input.get("flwId"));
    }
}
