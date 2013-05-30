package parser;

import org.junit.Test;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.commcare.domain.CommcareForm;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class MetaInfoParserTest {
    @Test
    public void shouldConvertToCamelCase() throws Exception {
        CommcareForm commcareForm = new CommcareFormBuilder()
                .addMetadata("user_id", "89fda0284e008d2e0c980fb13fa0e5bb").build();

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("userId", "89fda0284e008d2e0c980fb13fa0e5bb");
        }};

        Map<String,String> actualMetadata = new MetaInfoParser().parse(commcareForm);

        assertEquals(expected, actualMetadata);
    }

    @Test
    public void shouldConvertKeyName() throws Exception {

        CommcareForm commcareForm = new CommcareFormBuilder()
                .addMetadata("instanceID", "e34707f8-80c8-4198-bf99-c11c90ba5c98").build();

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("formId", "e34707f8-80c8-4198-bf99-c11c90ba5c98");
        }};

        Map<String,String> actualMetadata = new MetaInfoParser().parse(commcareForm);

        assertEquals(expected, actualMetadata);
    }
}
