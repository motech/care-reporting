package org.motechproject.care.reporting.migration.util;

import org.apache.http.client.HttpClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.util.Properties;

import static junit.framework.Assert.assertEquals;
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
    public void shouldPostToFormUrl() {
        when(platformProperties.getProperty("app.url")).thenReturn("localhost");
        when(platformProperties.getProperty("app.form.endpoint")).thenReturn("forms");

        MotechAPIHttpClient motechAPIHttpClient = new MotechAPIHttpClient(httpClient, platformProperties);

        String formUpdateUrl = motechAPIHttpClient.getFormUpdateUrl();
        assertEquals("localhost/forms", formUpdateUrl);
    }

    @Test
    public void shouldPostToCaseUrl() {

        when(platformProperties.getProperty("app.url")).thenReturn("localhost");
        when(platformProperties.getProperty("app.case.endpoint")).thenReturn("cases");

        MotechAPIHttpClient motechAPIHttpClient = new MotechAPIHttpClient(httpClient, platformProperties);

        String caseUpdateUrl = motechAPIHttpClient.getCaseUpdateUrl();
        assertEquals("localhost/cases", caseUpdateUrl);
    }
}
