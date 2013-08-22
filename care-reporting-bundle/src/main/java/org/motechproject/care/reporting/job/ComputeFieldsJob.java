package org.motechproject.care.reporting.job;

import org.motechproject.care.reporting.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ComputeFieldsJob {

    Repository repository;

    @Autowired
    public ComputeFieldsJob(Repository repository){
        this.repository = repository;
    }

    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    public void run() {
        logger.info("Starting Computed Fields Population");
        repository.execute("SELECT report.populate_computed_fields()");
    }
}
