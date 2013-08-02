package org.motechproject.care.reporting.migration.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.motechproject.care.reporting.migration.common.PaginationOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class CommcareAPIHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(CommcareAPIHttpClient.class);

    private HttpClient httpClient;
    private final Properties commcareProperties;

    @Autowired
    public CommcareAPIHttpClient(@Qualifier("commcareHttpClient") HttpClient httpClient, @Qualifier("commcareProperties") Properties commcareProperties) {
        this.httpClient = httpClient;
        this.commcareProperties = commcareProperties;
        authenticate();
    }

    public String fetchForms(NameValuePair[] parameters, PaginationOption option) {
        List<NameValuePair> parameterList = new ArrayList<>(Arrays.asList(parameters));
        parameterList.add(new NameValuePair("limit", String.valueOf(option.getLimit())));
        parameterList.add(new NameValuePair("offset", String.valueOf(option.getOffset())));

        NameValuePair[] queryParams = parameterList.toArray(new NameValuePair[parameterList.size()]);

        return getRequest(commcareFormListUrl(), queryParams);
    }

    public String fetchCases(NameValuePair[] parameters, PaginationOption option) {
        List<NameValuePair> parameterList = new ArrayList<>(Arrays.asList(parameters));
        parameterList.add(new NameValuePair("limit", String.valueOf(option.getLimit())));
        parameterList.add(new NameValuePair("offset", String.valueOf(option.getOffset())));

        NameValuePair[] queryParams = parameterList.toArray(new NameValuePair[parameterList.size()]);

        return getRequest(commcareCaseListUrl(), queryParams);
    }


    private HttpMethod buildRequest(String url, NameValuePair[] queryParams) {
        HttpMethod requestMethod = new GetMethod(url);

        if (queryParams != null) {
            requestMethod.setQueryString(queryParams);
        }

        return requestMethod;
    }

    private String getRequest(String requestUrl, NameValuePair[] queryParams) {

        HttpMethod getMethod = buildRequest(requestUrl, queryParams);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new HttpMethodRetryHandler() {
            @Override
            public boolean retryMethod(HttpMethod method, IOException exception, int executionCount) {
                boolean retry = executionCount < getRetryCount();
                logger.error("Exception occurred while pulling data from commcare hq", exception);
                logger.error(String.format("Execution Count: %s, Retrying again: %s", executionCount, retry));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {

                }
                return retry;
            }
        });

        try {
            logger.info("Executing " + getMethod.getURI().getURI());
            int statusCode = httpClient.executeMethod(getMethod);
            String response = readResponse(getMethod);

            if (statusCode != HttpStatus.SC_OK) {
                BadResponseException e = new BadResponseException(requestUrl, statusCode, response);
                logger.error(e.getMessage(), e);
                throw e;
            }
            return response;

        } catch (IOException e) {
            logger.error("IOException while sending request to Commcare", e);
            throw new RuntimeException(e);
        }
    }

    private int getRetryCount() {
        return Integer.parseInt(commcareProperties.getProperty("retry.count"));
    }

    private String readResponse(HttpMethod getMethod) throws IOException {
        InputStream responseStream = getMethod.getResponseBodyAsStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private void authenticate() {
        httpClient.getParams().setAuthenticationPreemptive(true);

        httpClient.getState().setCredentials(
                new AuthScope(null, -1, null, null),
                new UsernamePasswordCredentials(getUsername(), getPassword()));
    }

    private String commcareCaseListUrl() {
        return String.format("%s/%s/api/%s/case", getCommcareBaseUrl(), getCommcareDomain(), getVersion());
    }

    private String commcareFormListUrl() {
        return String.format("%s/%s/api/%s/form", getCommcareBaseUrl(), getCommcareDomain(), getVersion());
    }

    private String getCommcareBaseUrl() {
        String commcareBaseUrl = getBaseUrl();

        if (commcareBaseUrl.endsWith("/")) {
            commcareBaseUrl = commcareBaseUrl.substring(0, commcareBaseUrl.length() - 1);
        }

        return commcareBaseUrl;
    }

    private String getBaseUrl() {
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
