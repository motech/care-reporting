package org.motechproject.care.reporting.migration.util;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.service.CommcareResponseWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MotechAPIHttpClientTest {
    @Mock
    private HttpClient httpClient;
    @Mock
    private Properties platformProperties;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldThrowExceptionIfStatusIsNotSuccess() throws IOException {
        CommcareResponseWrapper aCase = new CommcareResponseWrapper("aCase", MapUtils.EMPTY_MAP);
        PostMethod postMethod = mock(PostMethod.class);
        MotechAPIHttpClient motechAPIHttpClient = new MotechAPIHttpClient(httpClient, platformProperties);
        when(postMethod.getStatusCode()).thenReturn(500);
        when(postMethod.getResponseBodyAsStream()).thenReturn(IOUtils.toInputStream(""));
        when(postMethod.getURI()).thenReturn(new URI("motech/cases", true));

        expectedException.expect(BadResponseException.class);
        expectedException.expectMessage("Request to url motech/cases failed with status code 500 and response ");

        motechAPIHttpClient.postContet(aCase, postMethod);

        verify(httpClient).executeMethod(postMethod);
    }

    @Test
    public void shouldPostFormWithHeaders() throws IOException {
        PostMethod postMethod = mock(PostMethod.class);
        HashMap<String, String> headers = new HashMap<>();
        String headerKey = "received-on";
        String headerValue = DateTime.now().toString();
        headers.put(headerKey, headerValue);
        CommcareResponseWrapper response = new CommcareResponseWrapper("response", headers);
        MotechAPIHttpClient motechAPIHttpClient = new MotechAPIHttpClient(httpClient, platformProperties);
        when(postMethod.getStatusCode()).thenReturn(200);
        when(postMethod.getResponseBodyAsStream()).thenReturn(IOUtils.toInputStream(""));

        motechAPIHttpClient.postContet(response, postMethod);

        verify(postMethod).addRequestHeader(headerKey, headerValue);
        ArgumentCaptor<StringRequestEntity> requestEntityCaptor = ArgumentCaptor.forClass(StringRequestEntity.class);
        verify(postMethod).setRequestEntity(requestEntityCaptor.capture());
        assertEquals(response.getResponseBody(), requestEntityCaptor.getValue().getContent());

        verify(httpClient).executeMethod(postMethod);
    }

    @Test
    public void shouldThrowExceptionWhenPostFails() throws IOException {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("post failed");

        doThrow(new RuntimeException("post failed")).when(httpClient).executeMethod(any(PostMethod.class));

        MotechAPIHttpClient motechAPIHttpClient = new MotechAPIHttpClient(httpClient, platformProperties);

        motechAPIHttpClient.postForm(new CommcareResponseWrapper("response", MapUtils.EMPTY_MAP));
    }
}
