package org.motechproject.care.reporting.migration.service;

import org.apache.commons.collections.MapUtils;
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
        List<CommcareResponseWrapper> response = asList(new CommcareResponseWrapper("case response", MapUtils.EMPTY_MAP));
        when(commcareAPIHttpClient.fetchCase(caseId)).thenReturn(response);

        caseMigrationTask.migrate(caseId);

        verify(motechAPIHttpClient).postCase(response.get(0));
        verifyNoMoreInteractions(motechAPIHttpClient);
    }

    @Test
    public void shouldPostCloseCaseAfterCreateUpdate() {
        CaseMigrationTask caseMigrationTask = new CaseMigrationTask(migrationBatchProcessor, commcareAPIHttpClient, motechAPIHttpClient);
        String caseId = "form Id";
        CommcareResponseWrapper caseResponse = new CommcareResponseWrapper("create update response", MapUtils.EMPTY_MAP);
        CommcareResponseWrapper closeCaseResponse = new CommcareResponseWrapper("close response", MapUtils.EMPTY_MAP);
        List<CommcareResponseWrapper> response = asList(caseResponse, closeCaseResponse);
        when(commcareAPIHttpClient.fetchCase(caseId)).thenReturn(response);

        caseMigrationTask.migrate(caseId);

        ArgumentCaptor<CommcareResponseWrapper> casePostCaptor = ArgumentCaptor.forClass(CommcareResponseWrapper.class);
        verify(motechAPIHttpClient, times(2)).postCase(casePostCaptor.capture());

        List<CommcareResponseWrapper> casePosts = casePostCaptor.getAllValues();
        assertEquals(2, casePosts.size());
        assertEquals(caseResponse, casePosts.get(0));
        assertEquals(closeCaseResponse, casePosts.get(1));
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
    public void shouldThrowExceptionIfCasePostFails() {
        CaseMigrationTask caseMigrationTask = new CaseMigrationTask(migrationBatchProcessor, commcareAPIHttpClient, motechAPIHttpClient);
        String caseId = "caseId";
        List<CommcareResponseWrapper> response = asList(new CommcareResponseWrapper("case response", MapUtils.EMPTY_MAP));
        when(commcareAPIHttpClient.fetchCase(caseId)).thenReturn(response);

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("post failed");

        doThrow(new RuntimeException("post failed")).when(motechAPIHttpClient).postCase(response.get(0));
        caseMigrationTask.migrate(caseId);
    }
}
