package org.motechproject.care.reporting.parser;

import junit.framework.Assert;
import org.codehaus.jackson.annotate.JsonProperty;
import org.junit.Test;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.builder.FormValueElementBuilder;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.motechproject.care.reporting.builder.FormValueElementBuilder.getFVE;

public class InfoParserImplTest {

    @Test
    public void shouldSearchForCaseElementWithinStartElement() {
        String caseElementPath = "caseElementPath";

        InfoParserImpl infoParser = new InfoParserImpl();
        infoParser.setCaseElementPath(caseElementPath);

        FormValueElement formValueElement = mock(FormValueElement.class);
        FormValueElement caseFormValueElement = mock(FormValueElement.class);

        when(formValueElement.searchFirst(caseElementPath)).thenReturn(caseFormValueElement);

        assertEquals(caseFormValueElement, infoParser.getCaseElement(formValueElement));
    }

    @Test
    public void testPopulatesTheMapWithoutCamelCaseConversion() {

        CommcareForm commcareForm = new CommcareFormBuilder()
                .addSubElement("hh_number", "165")
                .addSubElement("family_number", "5")
                .build();

        InfoParserImpl infoParser = new InfoParserImpl();
        infoParser.setConvertToCamelCase(false);
        Map<String, String> info = infoParser.parse(commcareForm.getForm());

        Assert.assertEquals(2, info.size());

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

        Map<String, String> info = new InfoParserImpl().parse(commcareForm.getForm());

        Assert.assertEquals(2, info.size());

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

        InfoParserImpl infoParser = new InfoParserImpl(keyMap);

        Map<String, String> info = infoParser.parse(commcareForm.getForm());

        Assert.assertEquals(2, info.size());

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("modifiedHhNumber", "165");
            put("familyNumber", "5");
        }};

        ReflectionAssert.assertReflectionEquals(expected, info);
    }

    @Test
    public void testExcludesRestrictedElementsOnConvertingFVEToMap() {

        CommcareForm commcareForm = new CommcareFormBuilder()
                .addSubElement("hh_number", "165")
                .addSubElement("family_number", "5")
                .build();

        List<String> restrictedElements = asList("hh_number");
        InfoParserImpl infoParser = new InfoParserImpl();
        infoParser.setRestrictedElements(restrictedElements);

        Map<String, String> info = infoParser.parse(commcareForm.getForm());

        Assert.assertEquals(1, info.size());

        HashMap<String, String> expected = new HashMap<String, String>() {{
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

        HashMap<String, Object> expected = new HashMap<String, Object>() {{
            put("hhNumber", "165");
            put("familyNumber", "5");
        }};

        Map<String, Object> actual = new InfoParserImpl().parse(input);

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

        InfoParserImpl infoParser = new InfoParserImpl(keyMap);
        Map<String, Object> actual = infoParser.parse(input);

        ReflectionAssert.assertReflectionEquals(expected, actual);
    }


    @Test
    public void testExcludesRestrictedElementsOnConvertingMap() {
        HashMap<String, String> input = new HashMap<String, String>() {{
            put("hh_number", "165");
            put("family_number", "5");
        }};

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("familyNumber", "5");
        }};

        List<String> restrictedElements = asList("hh_number");

        InfoParserImpl infoParser = new InfoParserImpl();
        infoParser.setRestrictedElements(restrictedElements);

        Map<String, Object> actual = infoParser.parse(input);

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

        Map<String, Object> parsedFlwMap = new InfoParserImpl().parse(instance);

        ReflectionAssert.assertReflectionEquals(expectedFlwMap, parsedFlwMap);
    }

    @Test
    public void shouldGetTheFirstValueIfAFieldHasListOfValues() {
        InfoParserImpl infoParser = new InfoParserImpl();

        Map<String, Object> parsedFieldValueMap = infoParser.parse(new HashMap<String, Object>() {{
            put("field1", "value1");
            put("field2", asList("value2", "value3"));
            put("field3", new ArrayList<>());
            put("field4", null);
            put("field5", 5);
        }});

        Assert.assertEquals("value1", parsedFieldValueMap.get("field1"));
        Assert.assertEquals("value2", parsedFieldValueMap.get("field2"));
        assertNull(parsedFieldValueMap.get("field3"));
        assertNull(parsedFieldValueMap.get("field4"));
        Assert.assertEquals(5, parsedFieldValueMap.get("field5"));
    }

    @Test
    public void shouldRecursivelyParseSubElements() throws Exception {
        InfoParserImpl infoParser = new InfoParserImpl();
        FormValueElement root = new FormValueElementBuilder().addSubElement("case", getFVE("update", getFVE("age", "1")))
                .build();

        Map<String, String> parsedFieldValueMap = infoParser.parse(root, true);

        Assert.assertEquals("1", parsedFieldValueMap.get("age"));
    }

    @Test
    public void shouldRecursivelyParseWithRestrictedElements() throws Exception{
        InfoParserImpl infoParser = new InfoParserImpl();
        infoParser.setRestrictedElements(asList("restricted"));
        FormValueElement root = new FormValueElementBuilder()
                .addSubElement("case", getFVE("update", getFVE("age", "1")))
                .addSubElement("restricted",getFVE("age","2"))
                .build();

        Map<String, String> parsedFieldValueMap = infoParser.parse(root, true);

        Assert.assertEquals("1", parsedFieldValueMap.get("age"));
    }
}
