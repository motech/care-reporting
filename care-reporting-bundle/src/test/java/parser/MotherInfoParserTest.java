package parser;

import com.google.common.collect.HashMultimap;
import org.junit.Test;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.builder.FormValueElementBuilder;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class MotherInfoParserTest {

    @Test
    public void testConvertsToCamelCaseAndPopulatesTheMapWithCaseInformation() throws Exception {

        FormValueElement motherCase = new FormValueElementBuilder()
                                            .addAttribute("case_id", "94d5374f-290e-409f-bc57-86c2e4bcc43f")
                                            .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                                            .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                                            .build();

        CommcareForm commcareForm = new CommcareFormBuilder()
                                            .addSubElement("hh_number", "165")
                                            .addSubElement("family_number", "5")
                                            .addSubElement("case", motherCase)
                                            .build();

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
}

