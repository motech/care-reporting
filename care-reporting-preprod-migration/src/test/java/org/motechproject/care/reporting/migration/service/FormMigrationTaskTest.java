package org.motechproject.care.reporting.migration.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FormMigrationTaskTest {

    @Mock
    private MigrationBatchProcessor migrationBatchProcessor;
    @Mock
    private CommcareAPIHttpClient commcareAPIHttpClient;
    @Mock
    private MotechAPIHttpClient motechAPIHttpClient;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldPostForm() {
        FormMigrationTask formMigrationTask = new FormMigrationTask(migrationBatchProcessor, commcareAPIHttpClient, motechAPIHttpClient);
        String formId = "form Id";
        CommcareResponseWrapper response = new CommcareResponseWrapper("form response", getHeader());
        when(commcareAPIHttpClient.fetchForm(formId)).thenReturn(response);

        formMigrationTask.migrate(formId);

        verify(motechAPIHttpClient).postForm(response);
    }

    @Test
    public void shouldThrowExceptionIfFormFetchFails() {
        FormMigrationTask formMigrationTask = new FormMigrationTask(migrationBatchProcessor, commcareAPIHttpClient, motechAPIHttpClient);
        String form = "form";
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("fetch failed");
        when(commcareAPIHttpClient.fetchForm(form)).thenThrow(new RuntimeException("fetch failed"));

        formMigrationTask.migrate(form);
    }

    @Test
    public void shouldThrowExceptionIfFormPostFails() {
        FormMigrationTask formMigrationTask = new FormMigrationTask(migrationBatchProcessor, commcareAPIHttpClient, motechAPIHttpClient);
        String form = "form";
        CommcareResponseWrapper response = new CommcareResponseWrapper("response", getHeader());
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("post failed");
        when(commcareAPIHttpClient.fetchForm(form)).thenReturn(response);
        doThrow(new RuntimeException("post failed")).when(motechAPIHttpClient).postForm(response);

        formMigrationTask.migrate(form);
    }

    private Map<String, String> getHeader() {
        return new HashMap<String, String>(){{
            put("received_on", DateTime.now().toString());
        }};
    }
}
