package org.motechproject.care.reporting.parser;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.builder.FormValueElementBuilder;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.motechproject.commcare.parser.FullFormParser;
import org.unitils.reflectionassert.ReflectionAssert;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.motechproject.care.reporting.builder.FormValueElementBuilder.getFVE;

public class MotherInfoParserTest {

    @Test
    public void shouldMapOnlyMotherCaseInfo() throws Exception {

        FormValueElement motherCase = new FormValueElementBuilder()
                                            .addAttribute("case_id", "94d5374f-290e-409f-bc57-86c2e4bcc43f")
                                            .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                                            .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                                            .build();
        FormValueElement childCase = new FormValueElementBuilder()
                                            .addSubElement("hh_number", "555")
                                            .addSubElement("age", "1")
                                            .build();

        CommcareForm commcareForm = new CommcareFormBuilder()
                                            .addSubElement("hh_number", "165")
                                            .addSubElement("family_number", "5")
                                            .addSubElement("case", motherCase)
                                            .addSubElement("child_info", childCase)
                                            .build();

        Map<String,String> motherInfo = new MotherInfoParser().parse(commcareForm);

        assertEquals(4, motherInfo.size());

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("caseId", "94d5374f-290e-409f-bc57-86c2e4bcc43f");
            put("dateModified", "2012-07-21T12:02:59.923+05:30");
            put("hhNumber", "165");
            put("familyNumber", "5");
        }};

        ReflectionAssert.assertReflectionEquals(expected, motherInfo);
    }

    @Test
    public void shouldCopyCaseValues() throws Exception {
        FormValueElement caseElement = new FormValueElementBuilder().addSubElement("case", getFVE("update", getFVE("age", "1")))
                .build();
        CommcareForm form = new CommcareFormBuilder().setRootElement(caseElement);

        Map<String, String> mappedValues = new MotherInfoParser().parse(form);

        assertEquals("1", mappedValues.get("age"));
    }

    @Test
    public void shouldCopyNestedValues() throws Exception {

        FormValueElement caseElement = new FormValueElementBuilder().addSubElement("case", getFVE("update", getFVE("age", "1")))
                .addSubElement("nestedField", getFVE("age", "2"))
                .build();
        CommcareForm form = new CommcareFormBuilder().setRootElement(caseElement);

        Map<String, String> mappedValues = new MotherInfoParser().parse(form);

        assertEquals("2", mappedValues.get("age"));
    }

}

