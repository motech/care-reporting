package parser;

import com.google.common.collect.HashMultimap;
import org.junit.Test;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class MotherInfoParserTest {

    @Test
    public void testConvertsToCamelCaseAndPopulatesTheMapWithCaseInformation() throws Exception {
        CommcareForm commcareForm = new CommcareForm();
        FormValueElement form = new FormValueElement();
        HashMultimap<String, FormValueElement> subElements = new HashMultimap<>();
        FormValueElement motherCase = new FormValueElement();
        motherCase.setAttributes(new HashMap<String, String>() {{
            put("case_id", "94d5374f-290e-409f-bc57-86c2e4bcc43f");
            put("date_modified", "2012-07-21T12:02:59.923+05:30");
            put("user_id", "89fda0284e008d2e0c980fb13fa0e5bb");
        }});

        subElements.put("case", motherCase);
        subElements.put("hh_number", formWithSingleValue("165"));
        subElements.put("family_number", formWithSingleValue("5"));

        form.setSubElements(subElements);

        commcareForm.setForm(form);

        Map<String,String> motherInfo = new MotherInfoParser().parse(commcareForm);

        assertEquals(5, motherInfo.size());

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("caseId", "94d5374f-290e-409f-bc57-86c2e4bcc43f");
            put("dateModified", "2012-07-21T12:02:59.923+05:30");
            put("case", null);
            put("hhNumber", "165");
            put("familyNumber", "5");
        }};

        ReflectionAssert.assertReflectionEquals(expected, motherInfo);
    }

    private FormValueElement formWithSingleValue(final String value) {
        return new FormValueElement() {{
            setValue(value);
        }};
    }
}

