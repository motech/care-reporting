package org.motechproject.care.reporting.processors;

import org.apache.commons.lang.StringUtils;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.factory.CaseFactory;
import org.motechproject.care.reporting.mapper.GenericMapper;
import org.motechproject.care.reporting.parser.CaseInfoParser;
import org.motechproject.care.reporting.parser.InfoParser;
import org.motechproject.care.reporting.service.MapperService;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.care.reporting.utils.ObjectUtils;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.events.CaseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
public class CaseProcessor {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    private Service service;
    private MapperService mapperService;

    @Autowired
    public CaseProcessor(Service service, MapperService mapperService) {
        this.service = service;
        this.mapperService = mapperService;
    }

    public void process(CaseEvent caseEvent) {
        final String caseType = caseEvent.getCaseType();
        final Class<?> caseTypeClass = CaseFactory.getCase(caseType);
        final InfoParser infoParser = mapperService.getCaseInfoParser(CaseFactory.getCaseType(caseType), null);
        final Map<String, String> caseMap = new CaseInfoParser(infoParser).parse(caseEvent);
        Object patientCase = new GenericMapper().map(caseMap, caseTypeClass);
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

    protected void setFlwGroup(String ownerId, Object object) {
        if (StringUtils.isEmpty(ownerId)) {
            return;
        }

        FlwGroup motherCase = service.getGroup(ownerId);
        ObjectUtils.set(object, "flwGroup", motherCase);
    }

    protected void setMotherCase(String caseId, Object object) {
        if (StringUtils.isEmpty(caseId)) {
            return;
        }

        MotherCase motherCase = service.getMotherCase(caseId);
        ObjectUtils.set(object, "motherCase", motherCase);
    }

    protected void setChildCase(String caseId, Object object) {
        if (StringUtils.isEmpty(caseId)) {
            return;
        }

        ChildCase childCase = service.getChildCase(caseId);
        ObjectUtils.set(object, "childCase", childCase);
    }

    protected void setFlw(String flwId, Object object) {
        if (StringUtils.isEmpty(flwId)) {
            return;
        }

        Flw flw = service.getFlw(flwId);
        ObjectUtils.set(object, "flw", flw);
    }
}