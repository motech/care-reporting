package org.motechproject.care.reporting.job;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.repository.Repository;
import org.motechproject.care.reporting.service.Service;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ComputeFieldsJobTest {

    @Mock
    private Service service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testExecute(){
        ComputeFieldsJob job = new ComputeFieldsJob(service);
        job.run();

        verify(service).execute("SELECT report.populate_computed_fields()");
    }


}
