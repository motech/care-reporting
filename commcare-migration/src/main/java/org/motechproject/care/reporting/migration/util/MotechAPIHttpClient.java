package org.motechproject.care.reporting.migration.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.motechproject.care.reporting.migration.common.CommcareResponseWrapper;
import org.motechproject.care.reporting.migration.statistics.EndpointStatisticsCollector;
import org.motechproject.care.reporting.migration.statistics.MigrationStatisticsCollector;
import org.motechproject.care.reporting.migration.statistics.RequestTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

@Component
public class MotechAPIHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(MotechAPIHttpClient.class);

    private HttpClient httpClient;
    private final Properties platformProperties;
    private EndpointStatisticsCollector statisticsCollector;

    @Autowired
    public MotechAPIHttpClient(@Qualifier("motechHttpClient") HttpClient httpClient, @Qualifier("platformProperties") Properties platformProperties, MigrationStatisticsCollector migrationStatisticsCollector) {
        this.httpClient = httpClient;
        this.platformProperties = platformProperties;
        this.statisticsCollector = migrationStatisticsCollector.motechEndpoint();
        logConfig();
    }

    public void postForm(CommcareResponseWrapper form) {
        postContent(form, new PostMethod(getFormUpdateUrl()));
    }

    public void postCase(CommcareResponseWrapper aCase) {
        postContent(aCase, new PostMethod(getCaseUpdateUrl()));
    }

    void postContent(CommcareResponseWrapper responseWrapper, PostMethod postMethod) {
        final RequestTimer requestTimer = statisticsCollector.newRequest();
        boolean success = false;
        try {
            addHeader(postMethod, responseWrapper.getHeaders());

            postMethod.setRequestEntity(new StringRequestEntity(responseWrapper.getResponseBody(), "text/xml; charset=UTF-8", "UTF-8"));

            final int maxRetries = getMaxRetries();
            final int sleepTime = getSleepTimeBeforeRetries();
            postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new HttpMethodRetryHandler() {
                @Override
                public boolean retryMethod(HttpMethod method, IOException exception, int executionCount) {
                    requestTimer.retried();
                    boolean retry = executionCount <= maxRetries;

                    logger.error("Exception occurred while posting data to motech", exception);
                    logger.error(String.format("Execution Count: %s, Retrying again: %s", executionCount, retry));

                    if(!retry) {
                        return false;
                    }

                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ignored) {
                    }
                    return true;
                }
            });

            requestTimer.start();
            httpClient.executeMethod(postMethod);

            int statusCode = postMethod.getStatusCode();

            String response = readResponse(postMethod);

            if (statusCode != HttpStatus.SC_OK) {
                BadResponseException e = new BadResponseException(postMethod.getURI().toString(), statusCode, response);
                logger.error(e.getMessage(), e);
                throw e;
            }
            success = true;
        } catch (IOException e) {
            logger.error("IO exception while sending request to motech", e);
            throw new RuntimeException(e);
        } finally {
            if(success) {
                requestTimer.successful();
            } else {
                requestTimer.failed();
            }
        }
    }

    private void addHeader(PostMethod postMethod, Map<String, String> headers) {
        for (Map.Entry<String, String> header : headers.entrySet()) {
            postMethod.addRequestHeader(header.getKey(), header.getValue());
        }
    }

    private String readResponse(PostMethod postMethod) throws IOException {
        InputStream responseStream = postMethod.getResponseBodyAsStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private String getFormUpdateUrl() {
        return String.format("%s/%s", platformProperties.getProperty("app.url"), platformProperties.getProperty("app.form.endpoint"));
    }

    private String getCaseUpdateUrl() {
        return String.format("%s/%s", platformProperties.getProperty("app.url"), platformProperties.getProperty("app.case.endpoint"));
    }


    private int getSleepTimeBeforeRetries() {
        return Integer.parseInt(platformProperties.getProperty("retry.sleep.time.in.ms"));
    }

    private int getMaxRetries() {
        return Integer.parseInt(platformProperties.getProperty("retry.count"));
    }

    private void logConfig() {
        logger.info(String.format("Motech case update endpoint: %s", getCaseUpdateUrl()));
        logger.info(String.format("Motech form update Endpoint: %s", getFormUpdateUrl()));
        logger.info(String.format("Motech maximumm retries: %s; with sleep time: %s", getMaxRetries(), getSleepTimeBeforeRetries()));
    }
}
