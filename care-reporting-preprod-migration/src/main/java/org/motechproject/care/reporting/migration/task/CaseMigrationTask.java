package org.motechproject.care.reporting.migration.task;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.httpclient.NameValuePair;
import org.motechproject.care.reporting.migration.common.CommcareResponseWrapper;
import org.motechproject.care.reporting.migration.common.PaginationOption;
import org.motechproject.care.reporting.migration.common.ResponseParser;
import org.motechproject.care.reporting.migration.service.PaginationScheme;
import org.motechproject.care.reporting.migration.service.Paginator;
import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.CommcareDataUtil;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.motechproject.care.reporting.migration.common.Constants.*;

@Component
public class CaseMigrationTask extends MigrationTask {

    private final ResponseParser parser;
    private Map<String, String> optionsToUrlMapper = new HashMap<String, String>() {{
        put(VERSION, CASE_VERSION);
        put(TYPE, CASE_TYPE);
        put(START_DATE, CASE_START_DATE);
        put(END_DATE, CASE_END_DATE);
    }};

    private static final Logger logger = LoggerFactory.getLogger(CaseMigrationTask.class);

    @Autowired
    public CaseMigrationTask(CommcareAPIHttpClient commcareAPIHttpClient, MotechAPIHttpClient motechAPIHttpClient, ResponseParser parser) {
        super(commcareAPIHttpClient, motechAPIHttpClient);
        this.parser = parser;
    }


    @Override
    protected Map<String, String> getOptionsToUrlMapper() {
        return optionsToUrlMapper;
    }

    @Override
    protected Paginator getPaginator(NameValuePair[] pairs) {
        PaginationScheme paginationScheme = new PaginationScheme() {
            @Override
            public String nextPage(NameValuePair[] parameters, PaginationOption paginationOption) {
                return commcareAPIHttpClient.fetchCases(parameters, paginationOption);
            }
        };

        return new Paginator(pairs, paginationScheme, parser);
    }

    @Override
    protected void postToMotech(JsonArray request) {
        List<CommcareResponseWrapper> commcareResponseWrappers = convertToEntity(request);
        for (CommcareResponseWrapper commcareResponseWrapper : commcareResponseWrappers) {
            motechAPIHttpClient.postCase(commcareResponseWrapper);
        }
    }

    private List<CommcareResponseWrapper> convertToEntity(JsonArray request) {
        List<CommcareResponseWrapper> casesWithHeader = new ArrayList<>();
        for (JsonElement aCase : request) {
            List<String> caseXmls = CommcareDataUtil.toCaseXml((JsonObject) aCase);
            Map<String, String> headers = CommcareDataUtil.extractAsMap((JsonObject) aCase, "server_date_modified", "server-modified-on");
            for (String caseXml : caseXmls) {
                CommcareResponseWrapper commcareResponseWrapper = new CommcareResponseWrapper(caseXml, headers);
                casesWithHeader.add(commcareResponseWrapper);
            }
        }
        return casesWithHeader;
    }

}
