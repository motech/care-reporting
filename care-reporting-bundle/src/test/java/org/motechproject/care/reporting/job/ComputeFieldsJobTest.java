package org.motechproject.care.reporting.job;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.repository.Repository;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ComputeFieldsJobTest {

    @Mock
    private Repository repository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testExecute(){
        ComputeFieldsJob job = new ComputeFieldsJob(repository);
        job.run();

        verify(repository).execute("SELECT report.populate_computed_fields()");
    }


}
