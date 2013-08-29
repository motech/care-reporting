package org.motechproject.care.reporting.migration.task;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.motechproject.care.reporting.migration.common.CommcareResponseWrapper;
import org.motechproject.care.reporting.migration.common.MigrationType;
import org.motechproject.care.reporting.migration.common.Page;
import org.motechproject.care.reporting.migration.common.ResponseParser;
import org.motechproject.care.reporting.migration.statistics.MigrationStatisticsCollector;
import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.CommcareDataUtil;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.motechproject.care.reporting.migration.common.Constants.CASE_END_DATE;
import static org.motechproject.care.reporting.migration.common.Constants.CASE_START_DATE;
import static org.motechproject.care.reporting.migration.common.Constants.CASE_TYPE;
import static org.motechproject.care.reporting.migration.common.Constants.CASE_VERSION;
import static org.motechproject.care.reporting.migration.common.Constants.END_DATE;
import static org.motechproject.care.reporting.migration.common.Constants.START_DATE;
import static org.motechproject.care.reporting.migration.common.Constants.TYPE;
import static org.motechproject.care.reporting.migration.common.Constants.VERSION;

@Component
public class CaseMigrationTask extends MigrationTask {


    private Map<String, String> optionsToUrlMapper = new HashMap<String, String>() {{
        put(VERSION, CASE_VERSION);
        put(TYPE, CASE_TYPE);
        put(START_DATE, CASE_START_DATE);
        put(END_DATE, CASE_END_DATE);
    }};

    @Autowired
    public CaseMigrationTask(CommcareAPIHttpClient commcareAPIHttpClient, MotechAPIHttpClient motechAPIHttpClient, ResponseParser responseParser,
                             MigrationStatisticsCollector statisticsCollector) {
        super(commcareAPIHttpClient, motechAPIHttpClient, responseParser, MigrationType.CASE, statisticsCollector);
    }


    @Override
    protected Map<String, String> getOptionsToUrlMapper() {
        return optionsToUrlMapper;
    }

    @Override
    protected void postToMotech(CommcareResponseWrapper commcareResponseWrapper) {
        motechAPIHttpClient.postCase(commcareResponseWrapper);
    }

    @Override
    protected List<CommcareResponseWrapper> convertToEntity(JsonArray request) {
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

    @Override
    protected String fetchCommcareRecords(Map<String, String> parameters, Page paginationOption) {
        return commcareAPIHttpClient.fetchCases(parameters, paginationOption);
    }

}
