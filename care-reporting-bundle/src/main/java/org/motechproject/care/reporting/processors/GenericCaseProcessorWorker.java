package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.factory.CaseFactory;
import org.motechproject.care.reporting.mapper.GenericMapper;
import org.motechproject.care.reporting.parser.CaseInfoParser;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.events.CaseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class GenericCaseProcessorWorker extends ProcessorWorker {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    public GenericCaseProcessorWorker(Service service) {
        super(service);
    }

    public void process(CaseEvent caseEvent) {
        final Class<?> caseType = CaseFactory.getCase(caseEvent.getCaseType());
        final Map<String, String> caseMap = new CaseInfoParser().parse(caseEvent);
        Object patientCase = new GenericMapper().map(caseMap, caseType);
        processCase(patientCase, caseMap);
    }

    private void processCase(Object patient, Map<String, String> caseMap) {
        Class<?> clazz = patient.getClass();
        if (clazz.equals(MotherCase.class)) {
            processMother(patient, caseMap);
        } else if (clazz.equals(ChildCase.class)) {
            processChild(patient, caseMap);
        }
    }

    private void processMother(Object mother, Map<String, String> caseMap) {
        setFlw(caseMap.get("userId"), mother);
        setFlwGroup(caseMap.get("ownerId"), mother);

        MotherCase motherCase = (MotherCase) mother;
        logger.info(String.format("Started processing mother case with case ID %s", motherCase.getCaseId()));
        service.saveOrUpdateByExternalPrimaryKey(motherCase);
        logger.info(String.format("Finished processing mother case with case ID %s", motherCase.getCaseId()));
    }

    private void processChild(Object child, Map<String, String> caseMap) {
        setMotherCase(caseMap.get("motherId"), child);
        setFlw(caseMap.get("userId"), child);
        setFlwGroup(caseMap.get("ownerId"), child);

        ChildCase childCase = (ChildCase) child;
        logger.info(String.format("Started processing child case with case ID %s", childCase.getCaseId()));
        service.saveOrUpdateByExternalPrimaryKey(((ChildCase) child));
        logger.info(String.format("Finished processing child case with case ID %s", childCase.getCaseId()));
    }
}
