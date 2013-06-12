package org.motechproject.care.reporting.parser;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.builder.FormValueElementBuilder;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.motechproject.commcare.exception.FullFormParserException;
import org.motechproject.commcare.parser.FullFormParser;
import org.unitils.reflectionassert.ReflectionAssert;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

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

    @Test
    public void shouldCopyCaseValues() throws Exception {
        FormValueElement root = new FullFormParser(caseXml("bp.xml")).parse();
        CommcareForm form = new CommcareFormBuilder().setRootElement(root);

        Map<String, String> mappedValues = new MotherInfoParser().parse(form);

        assertEquals("2012-09-12", mappedValues.get("ifaTablets100"));
        assertEquals("yes", mappedValues.get("vehicle"));
        assertEquals("institutional", mappedValues.get("deliveryType"));
        assertEquals("yes", mappedValues.get("institutional"));
        assertEquals("25", mappedValues.get("age"));
    }

    private String caseXml(String fileName) throws IOException {
        InputStream xmlStream = this.getClass().getResourceAsStream("/"+fileName);
        StringWriter writer = new StringWriter();
        IOUtils.copy(xmlStream, writer, "UTF-8");
        return writer.toString();
    }
}

