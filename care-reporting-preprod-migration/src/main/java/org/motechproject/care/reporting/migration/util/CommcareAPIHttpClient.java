package org.motechproject.care.reporting.migration.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.auth.CredentialsNotAvailableException;
import org.apache.commons.httpclient.auth.CredentialsProvider;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.motechproject.care.reporting.migration.common.Constants;
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
import java.util.List;
import java.util.Map;
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

    public String fetchForms(Map<String, String> parameters, PaginationOption option) {
        NameValuePair[] queryParams = populateParams(parameters, option);
        return getRequest(commcareFormListUrl(), queryParams);
    }

    public String fetchCases(Map<String, String> parameters, PaginationOption option) {
        NameValuePair[] queryParams = populateParams(parameters, option);
        return getRequest(commcareCaseListUrl(), queryParams);
    }

    private NameValuePair[] populateParams(Map<String, String> parameters, PaginationOption option) {
        parameters.put(Constants.LIMIT, String.valueOf(option.getLimit()));
        parameters.put(Constants.OFFSET, String.valueOf(option.getOffset()));
        return toArray(parameters);
    }

    private NameValuePair[] toArray(Map<String, String> parameters) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> parameterEntry : parameters.entrySet()) {
            nameValuePairs.add(new NameValuePair(parameterEntry.getKey(), parameterEntry.getValue()));
        }
        return nameValuePairs.toArray(new NameValuePair[nameValuePairs.size()]);
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
        getMethod.getParams().setParameter(CredentialsProvider.PROVIDER, new CredentialsProvider() {
            @Override
            public Credentials getCredentials(AuthScheme scheme, String host, int port, boolean proxy) throws CredentialsNotAvailableException {
                return new UsernamePasswordCredentials(getUsername(), getPassword());
            }
        });

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
            logger.info("Fetching from: " + getMethod.getURI().getURI());
            int statusCode = httpClient.executeMethod(getMethod);

            String response = readResponse(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                BadResponseException e = new BadResponseException(requestUrl, statusCode, response);
                logger.error(e.getMessage(), e);
                throw e;
            }
            logger.info("Successfully Fetched: " + getMethod.getURI().getURI());
            return response;

        } catch (IOException e) {
            getMethod.releaseConnection();
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
        httpClient.getParams().setAuthenticationPreemptive(false);

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
