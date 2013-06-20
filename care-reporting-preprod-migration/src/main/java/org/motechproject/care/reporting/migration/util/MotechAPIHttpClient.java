package org.motechproject.care.reporting.migration.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class MotechAPIHttpClient {

    private HttpClient httpClient;
    private final Properties platformProperties;

    @Autowired
    public MotechAPIHttpClient(@Qualifier("motechHttpClient") HttpClient httpClient, @Qualifier("platformProperties") Properties platformProperties) {
        this.httpClient = httpClient;
        this.platformProperties = platformProperties;
    }

    public void postForm(String form) {
        postContet(form, new PostMethod(getFormUpdateUrl()));
    }

    public void postCase(String aCase) {
        postContet(aCase, new PostMethod(getCaseUpdateUrl()));
    }

    private void postContet(String form, PostMethod formPostMethod) {
        try {
            formPostMethod.setRequestEntity(new StringRequestEntity(form, "text/xml; charset=UTF-8", "UTF-8"));
            httpClient.executeMethod(formPostMethod);
            formPostMethod.releaseConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFormUpdateUrl() {
        return String.format("%s/%s", platformProperties.getProperty("app.url"), platformProperties.getProperty("app.form.endpoint"));
    }

    private String getCaseUpdateUrl() {
        return String.format("%s/%s", platformProperties.getProperty("app.url"), platformProperties.getProperty("app.case.endpoint"));
    }
}
