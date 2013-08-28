package org.motechproject.care.reporting.migration.common;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ResponseParserTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldParseResponse() {
        String response = getMessage("\"?offset=30&case_type=bihar_pregnancy&limit=10\"", "\"?offset=0&case_type=bihar_pregnancy&limit=10\"");

        PaginatedResponse result = new ResponseParser().parse(response);

        assertEquals(1, result.getRecords().size());

        PaginatedResponseMeta meta = result.getMeta();

        assertEquals(486839, meta.getTotalCount());

        assertEquals(10, meta.getNextPage().getLimit());
        assertEquals(30, meta.getNextPage().getOffset());

        assertEquals(10, meta.getPreviousPage().getLimit());
        assertEquals(0, meta.getPreviousPage().getOffset());

        assertEquals(10, meta.getCurrentPage().getLimit());
        assertEquals(20, meta.getCurrentPage().getOffset());
    }

    @Test
    public void shouldParseResponseWithNullNextField() {
        String response = getMessage(null, null);

        PaginatedResponse result = new ResponseParser().parse(response);

        assertNull(result.getMeta().getPreviousPage());
        assertNull(result.getMeta().getNextPage());

        assertEquals(1, result.getRecords().size());
    }

    @Test
    public void shouldThrowExceptionForInvalidNextField() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Invalid limit option, li=10&of");

        String response = getMessage("\"li=10&of\"", null);

        new ResponseParser().parse(response);
    }

    private String getMessage(String nextPageParameters, String previousPageParameters) {
        return "{\n" +
                "    \"meta\": {\n" +
                "        \"limit\": 10,\n" +
                "        \"next\": " + nextPageParameters + ",\n" +
                "        \"previous\": " + previousPageParameters + ",\n" +
                "        \"offset\": 20,\n" +
                "        \"total_count\": 486839\n" +
                "    },\n" +
                "    \"objects\": [\n" +
                "        {\n" +
                "            \"archived\": false,\n" +
                "            \"form\": {\n" +
                "                \"#type\": \"data\",\n" +
                "                \"@name\": \"New Beneficiary\"\n" +
                "            },\n" +
                "            \"id\": \"d0e54970-0ec7-45d4-87ef-21eaa504cf91\",\n" +
                "            \"md5\": \"OBSOLETED\",\n" +
                "            \"metadata\": {\n" +
                "                \"@xmlns\": \"http://openrosa.org/jr/xforms\",\n" +
                "                \"appVersion\": \"@xmlns:http://commcarehq.org/xforms, #text:v2.0.0alpha (990ba3-e6e3c5-unvers-2.1.0-Nokia/S40-native-input) #48 b:2012-Jul-26 r:2012-Jul-28\",\n" +
                "                \"deprecatedID\": null,\n" +
                "                \"deviceID\": \"BHN2E3BXVDP274W2ZJ74ASH31\",\n" +
                "                \"instanceID\": \"d0e54970-0ec7-45d4-87ef-21eaa504cf91\",\n" +
                "                \"timeEnd\": \"2013-02-11T15:42:58\",\n" +
                "                \"timeStart\": \"2013-02-11T15:41:09\",\n" +
                "                \"userID\": \"89fda0284e008d2e0c980fb13f98a6d5\",\n" +
                "                \"username\": \"8969815368\"\n" +
                "            },\n" +
                "            \"received_on\": \"2013-02-11T10:05:11\",\n" +
                "            \"resource_uri\": \"\",\n" +
                "            \"type\": \"data\",\n" +
                "            \"uiversion\": \"1\",\n" +
                "            \"version\": \"1\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

    }
}
