package org.motechproject.care.reporting.parser;

import org.codehaus.jackson.annotate.JsonProperty;
import org.junit.Test;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.commcare.domain.CommcareForm;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class InfoParserTest {
    @Test
    public void testPopulatesTheMapWithoutCamelCaseConversion() {

        CommcareForm commcareForm = new CommcareFormBuilder()
                .addSubElement("hh_number", "165")
                .addSubElement("family_number", "5")
                .build();

        InfoParser infoParser = new InfoParser();
        infoParser.setConvertToCamelCase(false);
        Map<String, String> info = infoParser.parse(commcareForm.getForm());

        assertEquals(2, info.size());

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("hh_number", "165");
            put("family_number", "5");
        }};

        ReflectionAssert.assertReflectionEquals(expected, info);
    }

    @Test
    public void testConvertsFVEToCamelCaseAndPopulatesTheMap() {

        CommcareForm commcareForm = new CommcareFormBuilder()
                .addSubElement("hh_number", "165")
                .addSubElement("family_number", "5")
                .build();

        Map<String, String> info = new InfoParser().parse(commcareForm.getForm());

        assertEquals(2, info.size());

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("hhNumber", "165");
            put("familyNumber", "5");
        }};

        ReflectionAssert.assertReflectionEquals(expected, info);
    }

    @Test
    public void testConvertsFVEToCamelCaseWithKeyConversionAndPopulatesTheMap() {

        CommcareForm commcareForm = new CommcareFormBuilder()
                .addSubElement("hh_number", "165")
                .addSubElement("family_number", "5")
                .build();


        Map<String, String> keyMap = new HashMap<String, String>() {{
            put("hh_number", "modifiedHhNumber");
        }};

        InfoParser infoParser = new InfoParser(keyMap);

        Map<String, String> info = infoParser.parse(commcareForm.getForm());

        assertEquals(2, info.size());

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("modifiedHhNumber", "165");
            put("familyNumber", "5");
        }};

        ReflectionAssert.assertReflectionEquals(expected, info);
    }

    @Test
    public void testConvertsMapToCamelCase() {
        HashMap<String, String> input = new HashMap<String, String>() {{
            put("hh_number", "165");
            put("family_number", "5");
        }};

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("hhNumber", "165");
            put("familyNumber", "5");
        }};

        Map<String, String> actual = new InfoParser().parse(input);

        ReflectionAssert.assertReflectionEquals(expected, actual);
    }

    @Test
    public void testConvertsMapToCamelCaseWithKeyConversion() {
        HashMap<String, String> input = new HashMap<String, String>() {{
            put("hh_number", "165");
            put("family_number", "5");
        }};

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("modifiedHhNumber", "165");
            put("familyNumber", "5");
        }};

        Map<String, String> keyMap = new HashMap<String, String>() {{
            put("hh_number", "modifiedHhNumber");
        }};

        InfoParser infoParser = new InfoParser(keyMap);
        Map<String, String> actual = infoParser.parse(input);

        ReflectionAssert.assertReflectionEquals(expected, actual);
    }

    @Test
    public void shouldParseGivenObject() {
        Object instance = new Object() {
            @JsonProperty("field_name")
            String fieldName = "fieldValue";
        };
        HashMap<String, String> expectedFlwMap = new HashMap<String, String>() {{
            put("fieldName", "fieldValue");
        }};

        Map<String, String> parsedFlwMap = new InfoParser().parse(instance);

        ReflectionAssert.assertReflectionEquals(expectedFlwMap, parsedFlwMap);
    }
}
