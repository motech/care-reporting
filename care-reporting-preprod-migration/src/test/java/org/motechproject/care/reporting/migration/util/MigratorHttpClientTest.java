package org.motechproject.care.reporting.migration.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.net.URI;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MigratorHttpClientTest {

    @Mock
    private HttpClient httpClient;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldPostContent() throws Exception {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        StatusLine statusLine = Mockito.mock(StatusLine.class);
        HttpPost httpPost = mock(HttpPost.class);
        HttpEntity entity = mock(HttpEntity.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(statusLine.getReasonPhrase()).thenReturn("created");
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpPost.getURI()).thenReturn(new URI("localhost/motech"));
        when(entity.getContent()).thenReturn(IOUtils.toInputStream("response"));
        when(httpResponse.getEntity()).thenReturn(entity);
        when(httpClient.execute(httpPost)).thenReturn(httpResponse);

        MigratorHttpClient migratorHttpClient = new MigratorHttpClient(httpClient);

        String response = migratorHttpClient.postContent("form", httpPost);

        assertEquals("response", response);
    }

    @Test
    public void shouldThrowExceptionIfPostFails() throws Exception {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Request to localhost/motech failed with status code 500 and response null");

        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        StatusLine statusLine = Mockito.mock(StatusLine.class);
        HttpPost httpPost = mock(HttpPost.class);
        when(statusLine.getStatusCode()).thenReturn(500);
        when(statusLine.getReasonPhrase()).thenReturn("created");
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpPost.getURI()).thenReturn(new URI("localhost/motech"));
        when(httpClient.execute(httpPost)).thenReturn(httpResponse);

        MigratorHttpClient migratorHttpClient = new MigratorHttpClient(httpClient);

        migratorHttpClient.postContent("form", httpPost);
    }
}
