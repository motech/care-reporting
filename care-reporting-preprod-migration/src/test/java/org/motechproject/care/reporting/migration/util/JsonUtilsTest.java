package org.motechproject.care.reporting.migration.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonUtilsTest {

    @Test
    public void shouldConvertJsonToXml() {
        String json = "{\n" +
                "    \"form\": {\n" +
                "        \"@attr\": \"attrValue\",\n" +
                "        \"#value\": \"formValue\",\n" +
                "        \"element1\": \"element1Value\",\n" +
                "        \"booleanElement\": false,\n" +
                "        \"intElement\": 42,\n" +
                "        \"childElement\": [{\n" +
                "                \"@attr\": \"childElement1AttrValue\",\n" +
                "                \"#someValue\": \"childElement1Value\",\n" +
                "                \"childChild\": {\n" +
                "                    \"@attr\": \"childChildAttrValue\"\n" +
                "                }\n" +
                "            }, {\n" +
                "                \"@attr\": \"childElement2AttrValue\",\n" +
                "                \"#someValue\": \"childElement2Value\"\n" +
                "            },\n" +
                "            \"childElement3Value\",\n" +
                "            true,\n" +
                "            5\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        String expectedXml = "<data attr=\"attrValue\">formValue<element1>element1Value</element1>%n" +
                "<booleanElement>false</booleanElement>%n" +
                "<intElement>42</intElement>%n" +
                "<childElement attr=\"childElement1AttrValue\">childElement1Value<childChild attr=\"childChildAttrValue\"/>%n" +
                "</childElement>%n" +
                "<childElement attr=\"childElement2AttrValue\">childElement2Value</childElement>%n" +
                "<childElement>childElement3Value</childElement>%n" +
                "<childElement>true</childElement>%n" +
                "<childElement>5</childElement>%n" +
                "</data>%n";

        String actualXml = JsonUtils.toFormXml(json);
        assertEquals(String.format(expectedXml), actualXml);
    }
}