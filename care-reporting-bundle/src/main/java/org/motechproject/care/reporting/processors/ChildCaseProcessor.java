package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.domain.dimension.ChildCase;
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
public class ChildCaseProcessor {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");
    private static List<PostProcessor> CHILD_CASE_POSTPROCESSOR = new ArrayList<PostProcessor>() {{
        add(PostProcessor.CASE_COPY_USER_ID_AS_FLW);
        add(PostProcessor.COPY_OWNER_ID_AS_FLW_GROUP);
        add(PostProcessor.COPY_MOTHER_ID_AS_MOTHER_CASE);
    }};
    private Service service;
    private MapperService mapperService;
    private CareReportingMapper careReportingMapper;

    @Autowired
    public ChildCaseProcessor(Service service, MapperService mapperService, CareReportingMapper careReportingMapper) {
        this.service = service;
        this.mapperService = mapperService;
        this.careReportingMapper = careReportingMapper;
    }

    public void process(CaseEvent caseEvent) {
        InfoParser infoParser = mapperService.getCaseInfoParser(CaseFactory.getCaseType(caseEvent.getCaseType()), null);
        Map<String, String> caseMap = new CaseInfoParser(infoParser).parse(caseEvent);

        applyPostProcessors(CHILD_CASE_POSTPROCESSOR, caseMap);

        ChildCase childCase = careReportingMapper.map(caseMap, ChildCase.class);
        logger.info(String.format("Started processing child case with case ID %s", childCase.getCaseId()));
        service.saveOrUpdateByExternalPrimaryKey(childCase);
        logger.info(String.format("Finished processing child case with case ID %s", childCase.getCaseId()));
    }
}
