package org.motechproject.care.reporting.migration.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.motechproject.care.reporting.migration.util.CommCareAPIHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class FormMigrationTask extends MigrationTask {

    private final CommCareAPIHttpClient commCareAPIHttpClient;
    private final Properties platformProperties;

    @Autowired
    public FormMigrationTask(MigrationBatchProcessor migrationBatchProcessor, CommCareAPIHttpClient commCareAPIHttpClient, @Qualifier("platformProperties")Properties platformProperties) {
        super(migrationBatchProcessor);
        this.commCareAPIHttpClient = commCareAPIHttpClient;
        this.platformProperties = platformProperties;
    }

    @Override
    public void migrate(String id) {
        String form = commCareAPIHttpClient.formRequest(id);
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(getFormUpdateUrl());
        try {
            postMethod.setRequestEntity(new StringRequestEntity(form, "text/xml", "UTF-8"));
            httpClient.executeMethod(postMethod);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFormUpdateUrl() {
        return String.format("%s/%s", platformProperties.getProperty("app.url"), platformProperties.getProperty("app.form.endpoint"));
    }

}
