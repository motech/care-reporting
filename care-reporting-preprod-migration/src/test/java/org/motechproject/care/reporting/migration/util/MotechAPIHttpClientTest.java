package org.motechproject.care.reporting.migration.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
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
    public void shouldPostCaseToMotechCaseEndpoint() throws IOException {
        String aCase = "case";
        PostMethod postMethod = mock(PostMethod.class);
        when(platformProperties.getProperty("app.url")).thenReturn("localhost");
        when(platformProperties.getProperty("app.case.endpoint")).thenReturn("motech/cases");
        MotechAPIHttpClient motechAPIHttpClient = new MotechAPIHttpClient(httpClient, platformProperties);
        when(postMethod.getStatusCode()).thenReturn(200);
        when(postMethod.getResponseBodyAsStream()).thenReturn(IOUtils.toInputStream("response"));
        motechAPIHttpClient.postContet(aCase, postMethod);

        verify(httpClient).executeMethod(postMethod);
    }

    @Test
    public void shouldThrowExceptionIfStatusIsNotSuccess() throws IOException {
        expectedException.expect(BadResponseException.class);
        expectedException.expectMessage("Request to url motech/cases failed with status code 500 and response ");
        String aCase = "case";
        PostMethod postMethod = mock(PostMethod.class);
        when(platformProperties.getProperty("app.url")).thenReturn("localhost");
        when(platformProperties.getProperty("app.case.endpoint")).thenReturn("motech/cases");
        MotechAPIHttpClient motechAPIHttpClient = new MotechAPIHttpClient(httpClient, platformProperties);
        when(postMethod.getStatusCode()).thenReturn(500);
        when(postMethod.getResponseBodyAsStream()).thenReturn(IOUtils.toInputStream(""));
        when(postMethod.getURI()).thenReturn(new URI("motech/cases",true));
        motechAPIHttpClient.postContet(aCase, postMethod);

        verify(httpClient).executeMethod(postMethod);
    }

    @Test
    public void shouldThrowExceptionWhenPostFails() throws IOException {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("post failed");

        doThrow(new RuntimeException("post failed")).when(httpClient).executeMethod(any(PostMethod.class));

        MotechAPIHttpClient motechAPIHttpClient = new MotechAPIHttpClient(httpClient, platformProperties);

        motechAPIHttpClient.postForm("form");
    }

}
