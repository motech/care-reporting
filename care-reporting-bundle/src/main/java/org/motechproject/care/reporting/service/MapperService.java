package org.motechproject.care.reporting.service;

import org.motechproject.care.reporting.enums.CaseType;
import org.motechproject.care.reporting.parser.GroupParser;
import org.motechproject.care.reporting.parser.InfoParser;
import org.motechproject.care.reporting.enums.*;
import org.motechproject.care.reporting.parser.InfoParserImpl;
import org.motechproject.care.reporting.parser.ProviderParser;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    public InfoParser getFormInfoParser(String namespace, FormSegment formSegment, String version) {
        return new InfoParserImpl();
    }

    public InfoParser getCaseInfoParser(CaseType caseType, String version) {
        return new InfoParserImpl();
    }

    public GroupParser getGroupInfoParser() {
        return new GroupParser(new InfoParserImpl());
    }

    public ProviderParser getProviderInfoParser() {
        return new ProviderParser(new InfoParserImpl());
    }
}
