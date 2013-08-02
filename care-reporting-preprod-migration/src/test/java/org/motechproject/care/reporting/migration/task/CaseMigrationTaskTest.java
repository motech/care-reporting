package org.motechproject.care.reporting.migration.task;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.httpclient.NameValuePair;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.MigratorArguments;
import org.motechproject.care.reporting.migration.common.CommcareResponseWrapper;
import org.motechproject.care.reporting.migration.common.PaginatedResult;
import org.motechproject.care.reporting.migration.common.PaginationOption;
import org.motechproject.care.reporting.migration.common.ResponseParser;
import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.motechproject.care.reporting.migration.common.Constants.*;

public class CaseMigrationTaskTest {
    @Mock
    private CommcareAPIHttpClient commcareAPIHttpClient;
    @Mock
    private MotechAPIHttpClient motechAPIHttpClient;
    @Mock
    private ResponseParser parser;
    @Mock
    private MigratorArguments migratorArguments;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldFetchCaseWithParameters() {
        DateTime now = DateTime.now();
        Map<String, Object> optionsMap = new HashMap<>();
        optionsMap.put(TYPE, "cc_bihar_pregnancy");
        optionsMap.put(VERSION, "version");
        optionsMap.put(START_DATE, now.toDate().toString());
        optionsMap.put(END_DATE, now.toDate().toString());
        MigrationTask caseMigrationTask = new CaseMigrationTask(commcareAPIHttpClient, motechAPIHttpClient, parser);
        when(migratorArguments.getMap()).thenReturn(optionsMap);

        NameValuePair[] expectedNameValuePairs = new NameValuePair[4];
        expectedNameValuePairs[0] = new NameValuePair(CASE_TYPE, "cc_bihar_pregnancy");
        expectedNameValuePairs[1] = new NameValuePair(CASE_VERSION, "version");
        expectedNameValuePairs[2] = new NameValuePair(CASE_START_DATE, now.toDate().toString());
        expectedNameValuePairs[3] = new NameValuePair(CASE_END_DATE, now.toDate().toString());

        caseMigrationTask.migrate(migratorArguments);

        ArgumentCaptor<NameValuePair[]> parameterCaptor = ArgumentCaptor.forClass(NameValuePair[].class);
        verify(commcareAPIHttpClient).fetchCases(parameterCaptor.capture(), any(PaginationOption.class));
        ReflectionAssert.assertLenientEquals(expectedNameValuePairs, parameterCaptor.getValue());
    }

    @Test
    public void shouldFetchPaginatedCasesWithParameters() {
        String caseResponse1 = "response1";
        JsonArray jsonResponse1 = getCaseJson("2013-10-30", 1);
        when(parser.parse(caseResponse1)).thenReturn(new PaginatedResult(jsonResponse1, new PaginationOption(100, 100)));
        String caseResponse2 = "response2";
        JsonArray jsonResponse2 = getCaseJson("2013-12-13", 1);
        when(parser.parse(caseResponse2)).thenReturn(new PaginatedResult(jsonResponse2, null));
        when(commcareAPIHttpClient.fetchCases(any(NameValuePair[].class), any(PaginationOption.class))).thenReturn(caseResponse1).thenReturn(caseResponse2).thenReturn(null);
        MigrationTask caseMigrationTask = new CaseMigrationTask(commcareAPIHttpClient, motechAPIHttpClient, parser);

        caseMigrationTask.migrate(migratorArguments);

        ArgumentCaptor<NameValuePair[]> parameterCaptor = ArgumentCaptor.forClass(NameValuePair[].class);
        ArgumentCaptor<PaginationOption> optionCaptor = ArgumentCaptor.forClass(PaginationOption.class);
        ArgumentCaptor<CommcareResponseWrapper> caseReponseCaptor = ArgumentCaptor.forClass(CommcareResponseWrapper.class);

        verify(commcareAPIHttpClient, times(2)).fetchCases(parameterCaptor.capture(), optionCaptor.capture());
        List<PaginationOption> actualOptions = optionCaptor.getAllValues();
        ReflectionAssert.assertReflectionEquals(new PaginationOption(100, 0), actualOptions.get(0));
        ReflectionAssert.assertReflectionEquals(new PaginationOption(100, 100), actualOptions.get(1));

        verify(motechAPIHttpClient, times(2)).postCase(caseReponseCaptor.capture());
        List<CommcareResponseWrapper> actualForms = caseReponseCaptor.getAllValues();
        assertEquals("2013-10-30", actualForms.get(0).getHeaders().get("server-modified-on"));
        assertEquals("2013-12-13", actualForms.get(1).getHeaders().get("server-modified-on"));

    }

    @Test
    public void shouldPostMultipleTimesForMultipleCases() {
        String caseResponse1 = "response1";
        JsonArray jsonResponse1 = getCaseJson("2013-10-30", 2);
        when(parser.parse(caseResponse1)).thenReturn(new PaginatedResult(jsonResponse1, null));
        when(commcareAPIHttpClient.fetchCases(any(NameValuePair[].class), any(PaginationOption.class))).thenReturn(caseResponse1).thenReturn(null);
        MigrationTask caseMigrationTask = new CaseMigrationTask(commcareAPIHttpClient, motechAPIHttpClient, parser);

        caseMigrationTask.migrate(migratorArguments);

        ArgumentCaptor<CommcareResponseWrapper> caseReponseCaptor = ArgumentCaptor.forClass(CommcareResponseWrapper.class);

        verify(commcareAPIHttpClient).fetchCases(any(NameValuePair[].class), any(PaginationOption.class));

        verify(motechAPIHttpClient, times(2)).postCase(caseReponseCaptor.capture());
        List<CommcareResponseWrapper> actualForms = caseReponseCaptor.getAllValues();
        assertEquals("2013-10-30", actualForms.get(0).getHeaders().get("server-modified-on"));
        assertEquals("2013-10-30", actualForms.get(1).getHeaders().get("server-modified-on"));
    }

    @Test
    public void shouldPostClosedCaseAlsoForClosed() {
        String caseResponse1 = "response1";
        JsonArray jsonResponse1 = getClosedCase();
        when(parser.parse(caseResponse1)).thenReturn(new PaginatedResult(jsonResponse1, null));
        when(commcareAPIHttpClient.fetchCases(any(NameValuePair[].class), any(PaginationOption.class))).thenReturn(caseResponse1).thenReturn(null);
        MigrationTask caseMigrationTask = new CaseMigrationTask(commcareAPIHttpClient, motechAPIHttpClient, parser);

        caseMigrationTask.migrate(migratorArguments);

        ArgumentCaptor<CommcareResponseWrapper> caseReponseCaptor = ArgumentCaptor.forClass(CommcareResponseWrapper.class);

        verify(commcareAPIHttpClient).fetchCases(any(NameValuePair[].class), any(PaginationOption.class));

        verify(motechAPIHttpClient, times(2)).postCase(caseReponseCaptor.capture());
        List<CommcareResponseWrapper> actualForms = caseReponseCaptor.getAllValues();
        assertEquals("2013-10-30", actualForms.get(0).getHeaders().get("server-modified-on"));
        assertEquals("2013-10-30", actualForms.get(1).getHeaders().get("server-modified-on"));
    }

    private JsonArray getCaseJson(String serverModifiedOn, int sizeOfArray) {
        String caseJson = getCaseJson(serverModifiedOn);
        JsonParser jsonParser = new JsonParser();
        JsonElement aCase = jsonParser.parse(caseJson);
        JsonArray caseJsons = new JsonArray();
        for (int i = 1; i <= sizeOfArray; i++) {
            caseJsons.add(aCase);
        }
        return caseJsons;
    }

    private JsonArray getClosedCase() {
        JsonParser jsonParser = new JsonParser();
        JsonElement aCase = jsonParser.parse(getClosedCaseJson());
        JsonArray jsonElements = new JsonArray();
        jsonElements.add(aCase);
        return jsonElements;
    }

    private String getClosedCaseJson() {
        return "{\n" +
                "            \"case_id\": \"757537e3-6406-410e-8206-5d92226fe6a3\",\n" +
                "            \"closed\": true,\n" +
                "            \"date_closed\": \"2012-12-31 22:14:27\",\n" +
                "            \"date_modified\": \"1980-12-31 22:14:27\",\n" +
                "            \"id\": \"757537e3-6406-410e-8206-5d92226fe6a3\",\n" +
                "            \"indices\": {},\n" +
                "            \"properties\": {\n" +
                "                \"case_name\": \"Rubi devi\",\n" +
                "                \"case_type\": \"cc_bihar_pregnancy\",\n" +
                "                \"date_last_visit\": \"1980-12-31\",\n" +
                "                \"date_next_reg\": \"1980-12-31\",\n" +
                "                \"date_opened\": \"1980-12-31T22:14:27\",\n" +
                "                \"external_id\": null,\n" +
                "                \"family_number\": \"5\",\n" +
                "                \"hh_number\": \"4\",\n" +
                "                \"husband_name\": \"Sobha pasban\",\n" +
                "                \"last_visit_type\": \"new\",\n" +
                "                \"mother_alive\": \"yes\",\n" +
                "                \"mother_dob\": \"\",\n" +
                "                \"mother_name\": \"Rubi devi\",\n" +
                "                \"owner_id\": \"demo_user_group_bihar\"\n" +
                "            },\n" +
                "            \"resource_uri\": \"\",\n" +
                "            \"server_date_modified\": \"2013-10-30\",\n" +
                "            \"server_date_opened\": null,\n" +
                "            \"user_id\": \"demo_user\",\n" +
                "            \"xform_ids\": [\n" +
                "                \"beacfceb-ef1e-4f46-a296-3b66e07f667f\"\n" +
                "            ]\n" +
                "        }";
    }

    private String getCaseJson(String serverModifiedOn) {
        return "{\n" +
                "            \"case_id\": \"757537e3-6406-410e-8206-5d92226fe6a3\",\n" +
                "            \"closed\": false,\n" +
                "            \"date_closed\": null,\n" +
                "            \"date_modified\": \"1980-12-31 22:14:27\",\n" +
                "            \"id\": \"757537e3-6406-410e-8206-5d92226fe6a3\",\n" +
                "            \"indices\": {},\n" +
                "            \"properties\": {\n" +
                "                \"case_name\": \"Rubi devi\",\n" +
                "                \"case_type\": \"cc_bihar_pregnancy\",\n" +
                "                \"date_last_visit\": \"1980-12-31\",\n" +
                "                \"date_next_reg\": \"1980-12-31\",\n" +
                "                \"date_opened\": \"1980-12-31T22:14:27\",\n" +
                "                \"external_id\": null,\n" +
                "                \"family_number\": \"5\",\n" +
                "                \"hh_number\": \"4\",\n" +
                "                \"husband_name\": \"Sobha pasban\",\n" +
                "                \"last_visit_type\": \"new\",\n" +
                "                \"mother_alive\": \"yes\",\n" +
                "                \"mother_dob\": \"\",\n" +
                "                \"mother_name\": \"Rubi devi\",\n" +
                "                \"owner_id\": \"demo_user_group_bihar\"\n" +
                "            },\n" +
                "            \"resource_uri\": \"\",\n" +
                "            \"server_date_modified\": \"" + serverModifiedOn + "\",\n" +
                "            \"server_date_opened\": null,\n" +
                "            \"user_id\": \"demo_user\",\n" +
                "            \"xform_ids\": [\n" +
                "                \"beacfceb-ef1e-4f46-a296-3b66e07f667f\"\n" +
                "            ]\n" +
                "        }";
    }
}
