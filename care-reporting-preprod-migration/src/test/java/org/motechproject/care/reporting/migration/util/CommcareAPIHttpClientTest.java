package org.motechproject.care.reporting.migration.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.motechproject.care.reporting.migration.service.CommcareResponseWrapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommcareAPIHttpClientTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private HttpClient httpClient;

    private Properties commcareProperties;
    private CommcareAPIHttpClient commcareAPIHttpClient;

    @Before
    public void setUp() {
        commcareProperties = new Properties();
        commcareProperties.putAll(new HashMap<Object, Object>() {{
            put("commcareBaseUrl", "https://india.commcarehq.org/a");
            put("commcareDomain", "care-bihar");
            put("username", "");
            put("password", "");
            put("apiVersion", "v0.4");
        }});
        when(httpClient.getParams()).thenReturn(new HttpClientParams());
        when(httpClient.getState()).thenReturn(new HttpState());
        commcareAPIHttpClient = new CommcareAPIHttpClient(httpClient, commcareProperties);
    }

    @Test
    public void shouldReturnReceivedOnHeaderForForm() throws IOException {
        String receivedOn = DateTime.now().toString();
        when(httpClient.executeMethod(any(GetMethod.class))).thenAnswer(new CommcareRequestAnswer(getFormResponseWithHeaderField("received_on", receivedOn)));

        CommcareResponseWrapper commcareResponseWrapper = commcareAPIHttpClient.fetchForm("formId");

        assertEquals(receivedOn, commcareResponseWrapper.getHeaders().get("received-on"));
    }

    @Test
    public void shouldThrowExceptionIfFieldIsNotPresent() throws IOException {
        when(httpClient.executeMethod(any(GetMethod.class))).thenAnswer(new CommcareRequestAnswer(getFormResponseWithHeaderField(null, null)));

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("received_on field not present in commcare response");

        commcareAPIHttpClient.fetchForm("formId");
    }

    @Test
    public void shouldReturnServerModifiedOnHeaderForCase() throws IOException {
        String serverModifiedOn = DateTime.now().toString();
        when(httpClient.executeMethod(any(GetMethod.class))).thenAnswer(new CommcareRequestAnswer(getCaseResponseWithHeaderField("server_date_modified", serverModifiedOn)));

        List<CommcareResponseWrapper> cases = commcareAPIHttpClient.fetchCase("caseId");

        assertEquals(2, cases.size());
        assertEquals(serverModifiedOn, cases.get(0).getHeaders().get("server-modified-on"));
        assertEquals(serverModifiedOn, cases.get(1).getHeaders().get("server-modified-on"));
    }

    private class CommcareRequestAnswer implements Answer {
        private String response;

        private CommcareRequestAnswer(String response) {
            this.response = response;
        }

        private void setResponse(HttpMethod method) {
            try {
                Field privateResponseStream = HttpMethodBase.class.getDeclaredField("responseStream");
                privateResponseStream.setAccessible(true);
                privateResponseStream.set(method, IOUtils.toInputStream(response));
            } catch (Exception e) {
                throw new RuntimeException("Exception Setting response stream", e);
            }
        }

        @Override
        public Object answer(InvocationOnMock invocation) throws Throwable {
            GetMethod method = (GetMethod) invocation.getArguments()[0];
            setResponse(method);
            return HttpStatus.SC_OK;
        }
    }

    private String getFormResponseWithHeaderField(String headerField, String headerValue) {

        String field = "";
        if (headerField != null)
            field = String.format("\"%s\": \"%s\",\n", headerField, headerValue);
        return "{\n" +
                "\"archived\": false,\n" +
                "\"form\": {},\n" +
                "\"id\": \"c24d1bc8-ed4d-4c04-ab67-1fb394f2db58\",\n" +
                "\"md5\": \"OBSOLETED\",\n" +
                "\"metadata\": {},\n" +
                field +
                "\"resource_uri\": \"\",\n" +
                "\"type\": \"data\",\n" +
                "\"uiversion\": \"1\",\n" +
                "\"version\": \"1\"\n" +
                "}";
    }

    private String getCaseResponseWithHeaderField(String headerField, String headerValue) {
        String field = "";
        if (headerField != null)
            field = String.format("\"%s\": \"%s\",\n", headerField, headerValue);

        return  "{\n" +
                "\"case_id\": \"361816d9-a98e-41d9-9d67-af1e18a26ea7\",\n" +
                "\"closed\": true,\n" +
                "\"date_closed\": null,\n" +
                "\"date_modified\": \"1900-01-01\",\n" +
                "\"id\": \"361816d9-a98e-41d9-9d67-af1e18a26ea7\",\n" +
                "\"indices\": {\n" +
                "    \"mother_id\": {\n" +
                "        \"case_id\": \"dafadd3f-5f76-44ee-8734-36e49cda189a\",\n" +
                "        \"case_type\": \"cc_bihar_pregnancy\"\n" +
                "    }\n" +
                "},\n" +
                "\"properties\": {\n" +
                "        \"case_name\": \"प्रमोद मुखिया\",\n" +
                "        \"case_type\": \"cc_bihar_newborn\",\n" +
                "        \"owner_id\": \"89fda0284e008d2e0c980fb13fc0e87f\"\n" +
                "},\n" +
                "\"resource_uri\": \"\",\n" +
                "\"%s\": \"%s\",\n" +
                field +
                "\"user_id\": \"89fda0284e008d2e0c980fb13f9e0967\"\n" +
                "}";
    }
}
