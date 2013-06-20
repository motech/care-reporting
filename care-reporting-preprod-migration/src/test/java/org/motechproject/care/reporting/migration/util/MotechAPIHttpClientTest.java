package org.motechproject.care.reporting.migration.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
    public void shouldPostFormToMotechFormEndpoint() throws IOException {
        String form = "form";
        when(platformProperties.getProperty("app.url")).thenReturn("localhost");
        when(platformProperties.getProperty("app.form.endpoint")).thenReturn("motech/forms");
        MotechAPIHttpClient motechAPIHttpClient = new MotechAPIHttpClient(httpClient, platformProperties);

        motechAPIHttpClient.postForm(form);

        ArgumentCaptor<PostMethod> methodCaptor = ArgumentCaptor.forClass(PostMethod.class);
        verify(httpClient).executeMethod(methodCaptor.capture());
        PostMethod postmethod = methodCaptor.getValue();
        assertEquals("localhost/motech/forms", postmethod.getPath());
        assertThat(postmethod.getRequestEntity(), instanceOf(StringRequestEntity.class));
        assertEquals(form, ((StringRequestEntity) postmethod.getRequestEntity()).getContent());
        assertEquals("text/xml; charset=UTF-8", postmethod.getRequestEntity().getContentType());
        assertEquals("UTF-8", postmethod.getRequestCharSet());
    }

    @Test
    public void shouldPostCaseToMotechCaseEndpoint() throws IOException {
        String aCase = "case";
        when(platformProperties.getProperty("app.url")).thenReturn("localhost");
        when(platformProperties.getProperty("app.case.endpoint")).thenReturn("motech/cases");
        MotechAPIHttpClient motechAPIHttpClient = new MotechAPIHttpClient(httpClient, platformProperties);

        motechAPIHttpClient.postCase(aCase);

        ArgumentCaptor<PostMethod> methodCaptor = ArgumentCaptor.forClass(PostMethod.class);
        verify(httpClient).executeMethod(methodCaptor.capture());
        PostMethod postmethod = methodCaptor.getValue();
        assertEquals("localhost/motech/cases", postmethod.getPath());
        assertThat(postmethod.getRequestEntity(), instanceOf(StringRequestEntity.class));
        assertEquals(aCase, ((StringRequestEntity) postmethod.getRequestEntity()).getContent());
        assertEquals("text/xml; charset=UTF-8", postmethod.getRequestEntity().getContentType());
        assertEquals("UTF-8", postmethod.getRequestCharSet());
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
