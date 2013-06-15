package org.motechproject.care.reporting.service;

import org.apache.commons.lang.StringUtils;
import org.motechproject.care.reporting.enums.CaseType;
import org.motechproject.care.reporting.model.MappingEntity;
import org.motechproject.care.reporting.parser.GroupParser;
import org.motechproject.care.reporting.parser.InfoParser;
import org.motechproject.care.reporting.enums.*;
import org.motechproject.care.reporting.parser.InfoParserImpl;
import org.motechproject.care.reporting.parser.ProviderParser;
import org.motechproject.care.reporting.processors.BestMatchProcessor;
import org.motechproject.care.reporting.utils.JsonUtils;
import org.motechproject.server.config.SettingsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapperService {
    private final BestMatchProcessor formBestMatchProcessor;
    private final BestMatchProcessor caseBestMatchProcessor;

    @Autowired
    public MapperService(MapperSettingsService mapperSettingsService){
        this.formBestMatchProcessor = getProcessor(mapperSettingsService.getFormStreams());
        this.caseBestMatchProcessor = getProcessor(mapperSettingsService.getCaseStreams());
    }

    public MapperService(BestMatchProcessor formBestMatchProcessor, BestMatchProcessor caseBestMatchProcessor) {
        this.formBestMatchProcessor = formBestMatchProcessor;
        this.caseBestMatchProcessor = caseBestMatchProcessor;
    }

    private BestMatchProcessor getProcessor(List<InputStream> inputStreams) {
        return new BestMatchProcessor(JsonUtils.parseStreams(inputStreams));
    }

    public InfoParser getFormInfoParser(String namespace, String version, FormSegment formSegment) {
        return formBestMatchProcessor.getBestMatch(namespace, version, formSegment.name()).getInfoParser();
    }

    public InfoParser getCaseInfoParser(CaseType caseType, String version) {
        return caseBestMatchProcessor.getBestMatch(caseType.name(), version).getInfoParser();
    }

    public GroupParser getGroupInfoParser() {
        return new GroupParser(new InfoParserImpl());
    }

    public ProviderParser getProviderInfoParser() {
        return new ProviderParser(new InfoParserImpl());
    }
}
