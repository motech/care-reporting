package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.service.Service;
import org.motechproject.care.reporting.utils.CareDateConverter;
import org.motechproject.commcare.events.CaseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

@Component
public class CloseCaseProcessor {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    private Service service;

    @Autowired
    public CloseCaseProcessor(Service service) {
        this.service = service;
    }

    public void process(CaseEvent caseEvent) {
        String caseId = caseEvent.getCaseId();
        logger.info(format("Closing case for caseId %s", caseId));
        service.closeCase(caseId, getClosedFields(caseEvent));
    }

    private Map<String, String> getClosedFields(CaseEvent caseEvent) {
        Map<String, String> closeFieldValues = new HashMap<>();
        closeFieldValues.put("closed", "true");
        closeFieldValues.put("lastModifiedTime", CareDateConverter.toString(new Date()));
        closeFieldValues.put("closedOn", caseEvent.getDateModified());
        closeFieldValues.put("closedBy", caseEvent.getUserId());
        closeFieldValues.put("flw", caseEvent.getUserId());
        return closeFieldValues;
    }
}
