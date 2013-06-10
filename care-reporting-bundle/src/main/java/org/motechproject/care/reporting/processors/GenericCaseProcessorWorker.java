package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.factory.CaseFactory;
import org.motechproject.care.reporting.mapper.GenericMapper;
import org.motechproject.care.reporting.parser.CaseInfoParser;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.events.CaseEvent;

import java.util.Map;

public class GenericCaseProcessorWorker extends ProcessorWorker {

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
        service.saveOrUpdateByExternalPrimaryKey(((MotherCase) mother));
    }

    private void processChild(Object child, Map<String, String> caseMap) {
        setMotherCase(caseMap.get("motherId"), child);
        setFlw(caseMap.get("userId"), child);
        setFlwGroup(caseMap.get("ownerId"), child);
        service.save(child);
    }
}
