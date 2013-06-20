package org.motechproject.care.reporting.migration.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CaseMigrationTaskTest {
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
    public void shouldPostCase() {
        CaseMigrationTask caseMigrationTask = new CaseMigrationTask(migrationBatchProcessor, commcareAPIHttpClient, motechAPIHttpClient);
        String caseId = "form Id";
        List<String> response = asList("case response");
        when(commcareAPIHttpClient.fetchCase(caseId)).thenReturn(response);

        caseMigrationTask.migrate(caseId);

        verify(motechAPIHttpClient).postCase("case response");
    }

    @Test
    public void shouldPostCloseCaseAfterCreateUpdate() {
        CaseMigrationTask caseMigrationTask = new CaseMigrationTask(migrationBatchProcessor, commcareAPIHttpClient, motechAPIHttpClient);
        String caseId = "form Id";
        List<String> response = asList("create update response", "close response");
        when(commcareAPIHttpClient.fetchCase(caseId)).thenReturn(response);

        caseMigrationTask.migrate(caseId);

        ArgumentCaptor<String> casePostCaptor = ArgumentCaptor.forClass(String.class);
        verify(motechAPIHttpClient, times(2)).postCase(casePostCaptor.capture());

        List<String> casePosts = casePostCaptor.getAllValues();
        assertEquals(2, casePosts.size());
        assertEquals("create update response", casePosts.get(0));
        assertEquals("close response", casePosts.get(1));
    }

    @Test
    public void shouldThrowExceptionIfFormFetchFails() {
        CaseMigrationTask caseMigrationTask = new CaseMigrationTask(migrationBatchProcessor, commcareAPIHttpClient, motechAPIHttpClient);
        String caseId = "caseId";
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("fetch failed");
        when(commcareAPIHttpClient.fetchCase(caseId)).thenThrow(new RuntimeException("fetch failed"));

        caseMigrationTask.migrate(caseId);
    }

    @Test
    public void shouldThrowExceptionIfFormPostFails() {
        CaseMigrationTask caseMigrationTask = new CaseMigrationTask(migrationBatchProcessor, commcareAPIHttpClient, motechAPIHttpClient);
        String caseId = "caseId";
        List<String> response = asList("case response");
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("post failed");
        when(commcareAPIHttpClient.fetchCase(caseId)).thenReturn(response);
        doThrow(new RuntimeException("post failed")).when(motechAPIHttpClient).postCase(response.get(0));

        caseMigrationTask.migrate(caseId);
    }
}
