package org.motechproject.care.reporting.listener;

import org.motechproject.care.reporting.constants.EventConstants;
import org.motechproject.care.reporting.job.ComputeFieldsJob;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComputeFieldsEventListener {

    private ComputeFieldsJob computeFieldsJob;

    @Autowired
    public ComputeFieldsEventListener(ComputeFieldsJob computeFieldsJob) {
        this.computeFieldsJob = computeFieldsJob;
    }

    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    @MotechListener(subjects = {EventConstants.COMPUTE_FIELDS})
    @SuppressWarnings("unused - motechEvent expected as parameter by cron invoker")
    public synchronized void handleComputeFieldsEvent(MotechEvent motechEvent) {
        logger.info("Handling compute fields event");
        computeFieldsJob.run();
    }

}
