package org.motechproject.care.reporting.migration.util;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MotechAPIHttpClient {

    private MigratorHttpClient httpClient;

    private final Properties platformProperties;

    @Autowired
    public MotechAPIHttpClient(@Qualifier("motechHttpClient") HttpClient httpClient, @Qualifier("platformProperties") Properties platformProperties) {
        this.httpClient = new MigratorHttpClient(httpClient);
        this.platformProperties = platformProperties;
    }

    public void postForm(String form) {
        httpClient.postContent(form, new HttpPost(getFormUpdateUrl()));
    }

    public void postCase(String aCase) {
        httpClient.postContent(aCase, new HttpPost(getCaseUpdateUrl()));
    }

    String getFormUpdateUrl() {
        return String.format("%s/%s", platformProperties.getProperty("app.url"), platformProperties.getProperty("app.form.endpoint"));
    }

    String getCaseUpdateUrl() {
        return String.format("%s/%s", platformProperties.getProperty("app.url"), platformProperties.getProperty("app.case.endpoint"));
    }

}
