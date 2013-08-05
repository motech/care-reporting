package org.motechproject.care.reporting.migration.task;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
public class FormMigrationTask extends MigrationTask {

    private final ResponseParser parser;
    private Map<String, String> optionsToUrlMapper = new HashMap<String, String>() {{
        put(VERSION, FORM_VERSION);
        put(TYPE, FORM_NAMESPACE);
        put(START_DATE, FORM_START_DATE);
        put(END_DATE, FORM_END_DATE);
    }};

    private static final Logger logger = LoggerFactory.getLogger(FormMigrationTask.class);

    @Autowired
    public FormMigrationTask(CommcareAPIHttpClient commcareAPIHttpClient, MotechAPIHttpClient motechAPIHttpClient, ResponseParser parser) {
        super(commcareAPIHttpClient, motechAPIHttpClient);
        this.parser = parser;
    }

    @Override
    protected Map<String, String> getOptionsToUrlMapper() {
        return optionsToUrlMapper;
    }

    @Override
    protected Paginator getPaginator(Map<String, String> pairs) {
        PaginationScheme paginationScheme = new PaginationScheme() {
            @Override
            public String nextPage(Map<String, String> parameters, PaginationOption paginationOption) {
                return commcareAPIHttpClient.fetchForms(parameters, paginationOption);
            }
        };
        return new Paginator(pairs, paginationScheme, parser);
    }

    @Override
    protected void postToMotech(JsonArray request) {
        List<CommcareResponseWrapper> commcareResponseWrappers = convertToEntity(request);
        for (CommcareResponseWrapper commcareResponseWrapper : commcareResponseWrappers) {
            motechAPIHttpClient.postForm(commcareResponseWrapper);
        }
    }

    private List<CommcareResponseWrapper> convertToEntity(JsonArray request) {
        List<CommcareResponseWrapper> formsWithHeader = new ArrayList<>();

        for (JsonElement form : request) {
            String formXml = CommcareDataUtil.toFormXml((JsonObject) form);
            Map<String, String> headers = CommcareDataUtil.extractAsMap((JsonObject) form, "received_on", "received-on");
            formsWithHeader.add(new CommcareResponseWrapper(formXml, headers));
        }
        return formsWithHeader;
    }
}
