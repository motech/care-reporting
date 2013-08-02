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

public class FormMigrationTaskTest {

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
    public void shouldFetchWithParameters() {
        DateTime now = DateTime.now();
        Map<String, Object> optionsMap = new HashMap<>();
        optionsMap.put(TYPE, "namespace");
        optionsMap.put(VERSION, "version");
        optionsMap.put(START_DATE, now.toDate().toString());
        optionsMap.put(END_DATE, now.toDate().toString());
        MigrationTask formMigrationTask = new FormMigrationTask(commcareAPIHttpClient, motechAPIHttpClient, parser);
        when(migratorArguments.getMap()).thenReturn(optionsMap);

        NameValuePair[] expectedNameValuePairs = new NameValuePair[4];
        expectedNameValuePairs[0] = new NameValuePair(FORM_NAMESPACE, "namespace");
        expectedNameValuePairs[1] = new NameValuePair(FORM_VERSION, "version");
        expectedNameValuePairs[2] = new NameValuePair(FORM_START_DATE, now.toDate().toString());
        expectedNameValuePairs[3] = new NameValuePair(FORM_END_DATE, now.toDate().toString());

        formMigrationTask.migrate(migratorArguments);

        ArgumentCaptor<NameValuePair[]> parameterCaptor = ArgumentCaptor.forClass(NameValuePair[].class);
        verify(commcareAPIHttpClient).fetchForms(parameterCaptor.capture(), any(PaginationOption.class));
        ReflectionAssert.assertLenientEquals(expectedNameValuePairs, parameterCaptor.getValue());
    }

    @Test
    public void shouldFetchFormsWithParameters() {
        String formResponse1 = "response1";
        JsonArray jsonResponse1 = getJsonFormArray("2013-10-30", 1);
        when(parser.parse(formResponse1)).thenReturn(new PaginatedResult(jsonResponse1, new PaginationOption(100, 100)));
        String formResponse2 = "response2";
        JsonArray jsonResponse2 = getJsonFormArray("2013-12-13", 1);
        when(parser.parse(formResponse2)).thenReturn(new PaginatedResult(jsonResponse2, null));
        when(commcareAPIHttpClient.fetchForms(any(NameValuePair[].class), any(PaginationOption.class))).thenReturn(formResponse1).thenReturn(formResponse2).thenReturn(null);
        MigrationTask formMigrationTask = new FormMigrationTask(commcareAPIHttpClient, motechAPIHttpClient, parser);

        formMigrationTask.migrate(migratorArguments);

        ArgumentCaptor<NameValuePair[]> parameterCaptor = ArgumentCaptor.forClass(NameValuePair[].class);
        ArgumentCaptor<PaginationOption> optionCaptor = ArgumentCaptor.forClass(PaginationOption.class);
        ArgumentCaptor<CommcareResponseWrapper> formReponseCaptor = ArgumentCaptor.forClass(CommcareResponseWrapper.class);

        verify(commcareAPIHttpClient, times(2)).fetchForms(parameterCaptor.capture(), optionCaptor.capture());
        List<PaginationOption> actualOptions = optionCaptor.getAllValues();
        ReflectionAssert.assertReflectionEquals(new PaginationOption(100, 0), actualOptions.get(0));
        ReflectionAssert.assertReflectionEquals(new PaginationOption(100, 100), actualOptions.get(1));

        verify(motechAPIHttpClient, times(2)).postForm(formReponseCaptor.capture());
        List<CommcareResponseWrapper> actualForms = formReponseCaptor.getAllValues();
        assertEquals("2013-10-30", actualForms.get(0).getHeaders().get("received-on"));
        assertEquals("2013-12-13", actualForms.get(1).getHeaders().get("received-on"));

    }

    @Test
    public void shouldPostMultipleTimesForMultipleForms() {
        String formResponse1 = "response1";
        JsonArray jsonResponse1 = getJsonFormArray("2013-10-30", 2);
        when(parser.parse(formResponse1)).thenReturn(new PaginatedResult(jsonResponse1, null));
        when(commcareAPIHttpClient.fetchForms(any(NameValuePair[].class), any(PaginationOption.class))).thenReturn(formResponse1).thenReturn(null);
        MigrationTask formMigrationTask = new FormMigrationTask(commcareAPIHttpClient, motechAPIHttpClient, parser);
        formMigrationTask.migrate(migratorArguments);

        ArgumentCaptor<CommcareResponseWrapper> formReponseCaptor = ArgumentCaptor.forClass(CommcareResponseWrapper.class);
        verify(motechAPIHttpClient, times(2)).postForm(formReponseCaptor.capture());
    }

    private JsonArray getJsonFormArray(String receivedOn, int sizeOfArray) {
        JsonArray forms = new JsonArray();
        JsonElement element = new JsonParser().parse(getFormJson(receivedOn));
        for (int i = 1; i <= sizeOfArray; i++) {
            forms.add(element);
        }
        return forms;
    }

    private String getFormJson(final String receivedOn) {
        return "{\n" +
                "    \"archived\": false,\n" +
                "    \"form\": {\n" +
                "        \"#type\": \"data\",\n" +
                "        \"@name\": \"New Beneficiary\"\n" +
                "    },\n" +
                "    \"id\": \"d0e54970-0ec7-45d4-87ef-21eaa504cf91\",\n" +
                "    \"md5\": \"OBSOLETED\",\n" +
                "    \"metadata\": {\n" +
                "        \"@xmlns\": \"http://openrosa.org/jr/xforms\",\n" +
                "        \"appVersion\": \"@xmlns:http://commcarehq.org/xforms, #text:v2.0.0alpha (990ba3-e6e3c5-unvers-2.1.0-Nokia/S40-native-input) #48 b:2012-Jul-26 r:2012-Jul-28\",\n" +
                "        \"deprecatedID\": null,\n" +
                "        \"deviceID\": \"BHN2E3BXVDP274W2ZJ74ASH31\",\n" +
                "        \"instanceID\": \"d0e54970-0ec7-45d4-87ef-21eaa504cf91\",\n" +
                "        \"timeEnd\": \"2013-02-11T15:42:58\",\n" +
                "        \"timeStart\": \"2013-02-11T15:41:09\",\n" +
                "        \"userID\": \"89fda0284e008d2e0c980fb13f98a6d5\",\n" +
                "        \"username\": \"8969815368\"\n" +
                "    },\n" +
                "    \"received_on\": \"" + receivedOn + "\",\n" +
                "    \"resource_uri\": \"\",\n" +
                "    \"type\": \"data\",\n" +
                "    \"uiversion\": \"1\",\n" +
                "    \"version\": \"1\"\n" +
                "}\n";

    }

}
