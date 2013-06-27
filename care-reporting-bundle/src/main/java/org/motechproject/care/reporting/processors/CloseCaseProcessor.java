package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.mapper.CareReportingMapper;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.events.CaseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CloseCaseProcessor {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    private Service service;
    private CareReportingMapper careReportingMapper;

    @Autowired
    public CloseCaseProcessor(CareReportingMapper careReportingMapper, Service service) {
        this.careReportingMapper = careReportingMapper;
        this.service = service;
    }

    public void process(CaseEvent caseEvent) {
        String caseId = caseEvent.getCaseId();
        MotherCase motherCase = service.getMotherCase(caseId);
        if (motherCase == null) {
            ChildCase childCase = service.getChildCase(caseId);
            if (childCase == null) {
                logger.warn(String.format("Cannot find case %s to close", caseId));
                throw new CaseNotFoundException(caseId);
            }
            childCase.setClosed(true);
            childCase.updateLastModifiedTime();
            careReportingMapper.set(childCase, "closedOn", caseEvent.getDateModified());
            careReportingMapper.set(childCase, "closedBy", caseEvent.getUserId());
            careReportingMapper.set(childCase, "flw", caseEvent.getUserId());
            service.update(childCase);
        } else {
            motherCase.setClosed(true);
            motherCase.updateLastModifiedTime();
            careReportingMapper.set(motherCase, "closedOn", caseEvent.getDateModified());
            careReportingMapper.set(motherCase, "closedBy", caseEvent.getUserId());
            careReportingMapper.set(motherCase, "flw", caseEvent.getUserId());
            service.update(motherCase);
        }
    }
}
