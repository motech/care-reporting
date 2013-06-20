package org.motechproject.care.reporting.migration.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;

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
        String response = "form response";
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
        String response = "response";
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("post failed");
        when(commcareAPIHttpClient.fetchForm(form)).thenReturn(response);
        doThrow(new RuntimeException("post failed")).when(motechAPIHttpClient).postForm(response);

        formMigrationTask.migrate(form);
    }
}
