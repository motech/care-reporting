package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.factory.CaseFactory;
import org.motechproject.care.reporting.mapper.CareReportingMapper;
import org.motechproject.care.reporting.parser.CaseInfoParser;
import org.motechproject.care.reporting.parser.InfoParser;
import org.motechproject.care.reporting.parser.PostProcessor;
import org.motechproject.care.reporting.service.MapperService;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.events.CaseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.motechproject.care.reporting.parser.PostProcessor.Utils.applyPostProcessors;

@Component
public class CaseProcessor {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");
    private static List<PostProcessor> MOTHER_CASE_POSTPROCESSOR = new ArrayList<PostProcessor>() {{
        add(PostProcessor.CASE_COPY_USER_ID_AS_FLW);
        add(PostProcessor.COPY_OWNER_ID_AS_FLW_GROUP);
    }};

    private static List<PostProcessor> CHILD_CASE_POSTPROCESSOR = new ArrayList<PostProcessor>() {{
        add(PostProcessor.CASE_COPY_USER_ID_AS_FLW);
        add(PostProcessor.COPY_OWNER_ID_AS_FLW_GROUP);
        add(PostProcessor.COPY_MOTHER_ID_AS_MOTHER_CASE);
    }};

    private Service service;
    private MapperService mapperService;
    private CareReportingMapper careReportingMapper;

    @Autowired
    public CaseProcessor(CareReportingMapper careReportingMapper, Service service, MapperService mapperService) {
        this.careReportingMapper = careReportingMapper;
        this.service = service;
        this.mapperService = mapperService;
    }

    public void process(CaseEvent caseEvent) {
        final String caseType = caseEvent.getCaseType();
        final Class<?> caseTypeClass = CaseFactory.getCase(caseType);
        final InfoParser infoParser = mapperService.getCaseInfoParser(CaseFactory.getCaseType(caseType), null);
        final Map<String, String> caseMap = new CaseInfoParser(infoParser).parse(caseEvent);
        if (caseTypeClass.equals(MotherCase.class)) {
            processMother(caseMap);
        } else if (caseTypeClass.equals(ChildCase.class)) {
            processChild(caseMap);
        }
    }

    private void processMother(Map<String, String> caseMap) {
        applyPostProcessors(MOTHER_CASE_POSTPROCESSOR, caseMap);

        MotherCase motherCase = careReportingMapper.map(caseMap, MotherCase.class);
        logger.info(String.format("Started processing mother case with case ID %s", motherCase.getCaseId()));
        service.saveOrUpdateByExternalPrimaryKey(motherCase);
        logger.info(String.format("Finished processing mother case with case ID %s", motherCase.getCaseId()));
    }

    private void processChild(Map<String, String> caseMap) {
        applyPostProcessors(CHILD_CASE_POSTPROCESSOR, caseMap);

        ChildCase childCase = careReportingMapper.map(caseMap, ChildCase.class);
        logger.info(String.format("Started processing child case with case ID %s", childCase.getCaseId()));
        service.saveOrUpdateByExternalPrimaryKey(childCase);
        logger.info(String.format("Finished processing child case with case ID %s", childCase.getCaseId()));
    }
}