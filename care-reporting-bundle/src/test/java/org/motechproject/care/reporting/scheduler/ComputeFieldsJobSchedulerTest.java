package org.motechproject.care.reporting.scheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.care.reporting.constants.EventConstants;
import org.motechproject.care.reporting.constants.PropertyConstants;
import org.motechproject.event.MotechEvent;
import org.motechproject.scheduler.MotechSchedulerService;
import org.motechproject.scheduler.domain.CronSchedulableJob;
import org.motechproject.server.config.SettingsFacade;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ComputeFieldsJobSchedulerTest {

    @Mock
    private SettingsFacade settings;
    @Mock
    private MotechSchedulerService motechSchedulerService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldScheduleComputeFieldsCronJob() {
        String cronExpression = "0 6 * * 0";

        when(settings.getProperty(PropertyConstants.COMPUTE_FIELDS_JOB_CRON_EXPRESSION)).thenReturn(cronExpression);

        new ComputeFieldsJobScheduler(settings, motechSchedulerService);

        ArgumentCaptor<CronSchedulableJob> cronJobCaptor = ArgumentCaptor.forClass(CronSchedulableJob.class);
        verify(motechSchedulerService).scheduleJob(cronJobCaptor.capture());
        CronSchedulableJob actualScheduledCronJob = cronJobCaptor.getValue();
        assertEquals(cronExpression, actualScheduledCronJob.getCronExpression());
        assertEquals(new MotechEvent(EventConstants.COMPUTE_FIELDS), actualScheduledCronJob.getMotechEvent());
    }
}
