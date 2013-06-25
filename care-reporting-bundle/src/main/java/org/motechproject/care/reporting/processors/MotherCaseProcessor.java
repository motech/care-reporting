package org.motechproject.care.reporting.processors;

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
public class MotherCaseProcessor {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");
    private static List<PostProcessor> MOTHER_CASE_POSTPROCESSOR = new ArrayList<PostProcessor>() {{
        add(PostProcessor.CASE_COPY_USER_ID_AS_FLW);
        add(PostProcessor.COPY_OWNER_ID_AS_FLW_GROUP);
    }};

    private Service service;
    private MapperService mapperService;
    private CareReportingMapper careReportingMapper;

    @Autowired
    public MotherCaseProcessor(CareReportingMapper careReportingMapper, Service service, MapperService mapperService) {
        this.careReportingMapper = careReportingMapper;
        this.service = service;
        this.mapperService = mapperService;
    }

    public void process(CaseEvent caseEvent) {
        InfoParser infoParser = mapperService.getCaseInfoParser(CaseFactory.getCaseType(caseEvent.getCaseType()), null);
        Map<String, String> caseMap = new CaseInfoParser(infoParser).parse(caseEvent);

        applyPostProcessors(MOTHER_CASE_POSTPROCESSOR, caseMap);

        MotherCase motherCase = careReportingMapper.map(caseMap, MotherCase.class);
        logger.info(String.format("Started processing mother case with case ID %s", motherCase.getCaseId()));
        service.saveOrUpdateByExternalPrimaryKey(motherCase);
        logger.info(String.format("Finished processing mother case with case ID %s", motherCase.getCaseId()));
    }
}