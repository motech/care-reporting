package org.motechproject.care.reporting.migration.task;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.motechproject.care.reporting.migration.common.CommcareResponseWrapper;
import org.motechproject.care.reporting.migration.common.MigrationType;
import org.motechproject.care.reporting.migration.common.Page;
import org.motechproject.care.reporting.migration.common.ResponseParser;
import org.motechproject.care.reporting.migration.statistics.MigrationStatisticsCollector;
import org.motechproject.care.reporting.migration.util.CaseXmlPair;
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

    private static final Logger logger = LoggerFactory.getLogger(CaseMigrationTask.class);

    private Map<String, String> optionsToUrlMapper = new HashMap<String, String>() {{
        put(VERSION, CASE_VERSION);
        put(TYPE, CASE_TYPE);
        put(START_DATE, CASE_START_DATE);
        put(END_DATE, CASE_END_DATE);
    }};
    private CommcareDataUtil commcareDataUtil;

    @Autowired
    public CaseMigrationTask(CommcareAPIHttpClient commcareAPIHttpClient, MotechAPIHttpClient motechAPIHttpClient, ResponseParser responseParser,
                             MigrationStatisticsCollector statisticsCollector,
                             CommcareDataUtil commcareDataUtil) {
        super(commcareAPIHttpClient, motechAPIHttpClient, responseParser, MigrationType.CASE, statisticsCollector);
        this.commcareDataUtil = commcareDataUtil;
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
        List<CommcareResponseWrapper> closedActionList = new ArrayList<>();
        List<CommcareResponseWrapper>  createUpdateActionList = new ArrayList<>();
        List<CommcareResponseWrapper> temporaryList = new ArrayList<>();

        for (JsonElement aCase : request) {

            if(isTask(aCase)) {
                String caseId = getCaseId(aCase);
                logger.warn(String.format("Task Case %s Ignored", caseId));
                continue;
            }

            CaseXmlPair caseXmls = commcareDataUtil.toCaseXml((JsonObject) aCase);
            Map<String, String> headers = commcareDataUtil.extractAsMap((JsonObject) aCase, "server_date_modified", "server-modified-on");

            if(caseXmls.hasClosedAction()) {
                createUpdateActionList.add(new CommcareResponseWrapper(caseXmls.getCreateUpdateAction(), headers));
                closedActionList.add(new CommcareResponseWrapper(caseXmls.getCloseAction(), headers));
            } else {
                temporaryList.add(new CommcareResponseWrapper(caseXmls.getCreateUpdateAction(), headers));
            }
        }

        createUpdateActionList.addAll(temporaryList);
        createUpdateActionList.addAll(closedActionList);

        return createUpdateActionList;
    }

    private boolean isTask(JsonElement aCase) {
        JsonObject jsonObject = (JsonObject) aCase;
        if(jsonObject == null)
            return false;
        JsonElement properties = jsonObject.get("properties");
        if(properties == null)
            return false;
        final JsonElement caseType = properties.getAsJsonObject().get("case_type");
        return (caseType != null) && "TASK".equalsIgnoreCase(caseType.getAsString());
    }

    private String getCaseId(JsonElement aCase) {
        JsonObject jsonObject = (JsonObject) aCase;
        if(jsonObject == null)
            return null;
        final JsonElement caseId = jsonObject.get("case_id");
        return caseId == null ? null : caseId.getAsString();
    }

    @Override
    protected String fetchCommcareRecords(Map<String, String> parameters, Page paginationOption) {
        return commcareAPIHttpClient.fetchCases(parameters, paginationOption);
    }

}
