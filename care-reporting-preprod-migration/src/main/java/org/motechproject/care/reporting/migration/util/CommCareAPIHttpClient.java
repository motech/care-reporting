package org.motechproject.care.reporting.migration.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class CommCareAPIHttpClient {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private HttpClient commonsHttpClient;
    private final Properties commcareProperties;

    @Autowired
    public CommCareAPIHttpClient(final HttpClient commonsHttpClient, @Qualifier("commcareProperties") Properties commcareProperties) {
        this.commonsHttpClient = commonsHttpClient;
        this.commcareProperties = commcareProperties;
    }

    public String usersRequest() {
        return this.getRequest(commcareUserUrl(), null);
    }

    public String formRequest(String formId) {
        return this.getRequest(commcareFormUrl(formId), null);
    }

    public String casesRequest(NameValuePair[] queryParams) {
        return this.getRequest(commcaseCasesUrl(), queryParams);
    }

    public String singleCaseRequest(String caseId) {
        return this.getRequest(commcareCaseUrl(caseId), null);
    }

    private HttpMethod buildRequest(String url, NameValuePair[] queryParams) {
        HttpMethod requestMethod = new GetMethod(url);

        authenticate();

        if (queryParams != null) {
            requestMethod.setQueryString(queryParams);
        }

        return requestMethod;
    }

    private String getRequest(String requestUrl, NameValuePair[] queryParams) {

        HttpMethod getMethod = buildRequest(requestUrl, queryParams);

        String response = null;

        try {
            commonsHttpClient.executeMethod(getMethod);
            response = getMethod.getResponseBodyAsString();
        } catch (HttpException e) {
            logger.warn("HttpException while sending request to CommCare: " + e.getMessage());
            return null;
        } catch (IOException e) {
            logger.warn("IOException while sending request to CommCare: " + e.getMessage());
            return null;
        }

        return response;
    }

    private void authenticate() {
        commonsHttpClient.getParams().setAuthenticationPreemptive(true);

        commonsHttpClient.getState().setCredentials(
                new AuthScope(null, -1, null, null),
                new UsernamePasswordCredentials(getUsername(), getPassword()));
    }


    private String commcareUserUrl() {
        return String.format("%s/%s/api/v0.3/user/?format=json", getCommcareBaseUrl(), getCommcareDomain());
    }

    private String commcareFormUrl(String formId) {
        return String.format("%s/%s/api/v0.3/form/%s/?format=xml", getCommcareBaseUrl(), getCommcareDomain(), formId);
    }

    private String commcaseCasesUrl() {
        return String.format("%s/%s/api/%s/case/", getCommcareBaseUrl(), getVersion(), getCommcareDomain());
    }

    private String commcareCaseUrl(String caseId) {
        return String.format("%s/%s/api/%s/case/%s/", getCommcareBaseUrl(), getCommcareDomain(), getVersion(), caseId);
    }

    private String getCommcareBaseUrl() {
        String commcareBaseUrl = getBaseUrl();

        if (commcareBaseUrl.endsWith("/")) {
            commcareBaseUrl = commcareBaseUrl.substring(0, commcareBaseUrl.length() - 1);
        }

        return commcareBaseUrl;
    }

    private String getBaseUrl(){
        return commcareProperties.getProperty("commcareBaseUrl");
    }

    private String getCommcareDomain() {
        return commcareProperties.getProperty("commcareDomain");
    }

    private String getUsername() {
        return commcareProperties.getProperty("username");
    }

    private String getPassword() {
        return commcareProperties.getProperty("password");
    }

    private String getVersion() {
        return commcareProperties.getProperty("apiVersion");
    }
}
