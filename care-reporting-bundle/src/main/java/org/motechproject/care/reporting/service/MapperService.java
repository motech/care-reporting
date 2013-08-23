package org.motechproject.care.reporting.service;

import org.motechproject.care.reporting.enums.CaseType;
import org.motechproject.care.reporting.model.MappingEntity;
import org.motechproject.care.reporting.parser.GroupParser;
import org.motechproject.care.reporting.parser.InfoParser;
import org.motechproject.care.reporting.enums.*;
import org.motechproject.care.reporting.parser.InfoParserImpl;
import org.motechproject.care.reporting.parser.ProviderParser;
import org.motechproject.care.reporting.processors.BestMatchProcessor;
import org.motechproject.care.reporting.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapperService {
    private final BestMatchProcessor formBestMatchProcessor;
    private final BestMatchProcessor caseBestMatchProcessor;
    private List<String> exclusionAppversionList;

    @Autowired
    public MapperService(MapperSettingsService mapperSettingsService){
        this.formBestMatchProcessor = getProcessor(mapperSettingsService.getFormStreams());
        this.caseBestMatchProcessor = getProcessor(mapperSettingsService.getCaseStreams());
        this.exclusionAppversionList = getExclusionAppversionList(mapperSettingsService.getExclusionAppversionStreams());
    }

    public MapperService(BestMatchProcessor formBestMatchProcessor, BestMatchProcessor caseBestMatchProcessor, List<String> exclusionAppversionList) {
        this.formBestMatchProcessor = formBestMatchProcessor;
        this.caseBestMatchProcessor = caseBestMatchProcessor;
        this.exclusionAppversionList = exclusionAppversionList;
    }

    private BestMatchProcessor getProcessor(List<InputStream> inputStreams) {
        return new BestMatchProcessor(JsonUtils.parseStreams(inputStreams, MappingEntity[].class));
    }

    private List<String> getExclusionAppversionList(List<InputStream> inputStreams) {
        return JsonUtils.parseStreams(inputStreams, String[].class);
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

    public List<String> getExclusionAppversionList() {
        return exclusionAppversionList;
    }
}
