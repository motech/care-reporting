package org.motechproject.care.reporting.migration.task;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.motechproject.care.reporting.migration.common.CommcareResponseWrapper;
import org.motechproject.care.reporting.migration.common.MigrationType;
import org.motechproject.care.reporting.migration.common.Page;
import org.motechproject.care.reporting.migration.common.ResponseParser;
import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.CommcareDataUtil;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.motechproject.care.reporting.migration.common.Constants.END_DATE;
import static org.motechproject.care.reporting.migration.common.Constants.FORM_END_DATE;
import static org.motechproject.care.reporting.migration.common.Constants.FORM_NAMESPACE;
import static org.motechproject.care.reporting.migration.common.Constants.FORM_START_DATE;
import static org.motechproject.care.reporting.migration.common.Constants.FORM_VERSION;
import static org.motechproject.care.reporting.migration.common.Constants.START_DATE;
import static org.motechproject.care.reporting.migration.common.Constants.TYPE;
import static org.motechproject.care.reporting.migration.common.Constants.VERSION;

@Component
public class FormMigrationTask extends MigrationTask {

    private Map<String, String> optionsToUrlMapper = new HashMap<String, String>() {{
        put(VERSION, FORM_VERSION);
        put(TYPE, FORM_NAMESPACE);
        put(START_DATE, FORM_START_DATE);
        put(END_DATE, FORM_END_DATE);
    }};

    @Autowired
    public FormMigrationTask(CommcareAPIHttpClient commcareAPIHttpClient, MotechAPIHttpClient motechAPIHttpClient, ResponseParser responseParser) {
        super(commcareAPIHttpClient, motechAPIHttpClient, responseParser, MigrationType.FORM);
    }

    @Override
    protected Map<String, String> getOptionsToUrlMapper() {
        return optionsToUrlMapper;
    }

    protected List<CommcareResponseWrapper> convertToEntity(JsonArray request) {
        List<CommcareResponseWrapper> formsWithHeader = new ArrayList<>();

        for (JsonElement form : request) {
            String formXml = CommcareDataUtil.toFormXml((JsonObject) form);
            Map<String, String> headers = CommcareDataUtil.extractAsMap((JsonObject) form, "received_on", "received-on");
            formsWithHeader.add(new CommcareResponseWrapper(formXml, headers));
        }
        return formsWithHeader;
    }

    @Override
    protected void postToMotech(CommcareResponseWrapper commcareResponseWrapper) {
        motechAPIHttpClient.postForm(commcareResponseWrapper);
    }

    @Override
    protected String fetchCommcareRecords(Map<String, String> parameters, Page paginationOption) {
        return commcareAPIHttpClient.fetchForms(parameters, paginationOption);
    }
}
