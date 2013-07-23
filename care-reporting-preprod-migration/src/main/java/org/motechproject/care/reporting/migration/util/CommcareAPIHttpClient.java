package org.motechproject.care.reporting.migration.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.motechproject.care.reporting.migration.service.CommcareResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

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

    public CommcareResponseWrapper fetchForm(String formId) {
        String jsonResponse = getRequest(commcareFormUrl(formId), null);

        JsonObject jsonObject = parseJson(jsonResponse);
        String responseBody = CommcareDataConverter.toFormXml(jsonObject);
        Map<String, String> header = extractHeaders(jsonObject, "received_on", "received-on");

        return new CommcareResponseWrapper(responseBody, header);
    }

    public List<CommcareResponseWrapper> fetchCase(String caseId) {
        String jsonResponse = getRequest(commcareCaseUrl(caseId), null);

        JsonObject jsonObject = parseJson(jsonResponse);
        List<String> responses = CommcareDataConverter.toCaseXml(jsonObject);
        Map<String, String> headers = extractHeaders(jsonObject, "server_date_modified", "server-modified-on");

        ArrayList<CommcareResponseWrapper> responseWrappers = new ArrayList<>();
        for (String response : responses) {
            responseWrappers.add(new CommcareResponseWrapper(response, headers));
        }
        return responseWrappers;
    }

    private JsonObject parseJson(String jsonResponse) {
        JsonParser parser = new JsonParser();
        return (JsonObject) parser.parse(jsonResponse);
    }

    private Map<String, String> extractHeaders(JsonObject jsonResponse, String fieldToExtract, String requestHeader) {
        Map<String, String> header = new HashMap<>();
        JsonElement extractedValue = jsonResponse.get(fieldToExtract);
        if (extractedValue == null)
            throw new RuntimeException(String.format("%s field not present in commcare response", fieldToExtract));
        header.put(requestHeader, extractedValue.getAsString());
        return header;
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

    private String commcareFormUrl(String formId) {
        return String.format("%s/%s/api/%s/form/%s/?format=json", getCommcareBaseUrl(), getCommcareDomain(), getVersion(), formId);
    }

    private String commcareCaseUrl(String caseId) {
        return String.format("%s/%s/api/%s/case/%s/?format=json", getCommcareBaseUrl(), getCommcareDomain(), getVersion(), caseId);
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
