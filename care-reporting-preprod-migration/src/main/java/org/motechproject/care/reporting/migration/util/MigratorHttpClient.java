package org.motechproject.care.reporting.migration.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

public class MigratorHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(MigratorHttpClient.class);

    private HttpClient httpClient;

    protected MigratorHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected String postContent(String form, HttpPost httpPost) {
        URI uri = httpPost.getURI();
        try {
            httpPost.setEntity(new StringEntity(form, ContentType.APPLICATION_XML));
            HttpResponse response = httpClient.execute(httpPost);

            String responseContent = readResponse(response);
            int statusCode = response.getStatusLine().getStatusCode();

            if (!isStatusOk(statusCode)) {
                RuntimeException e = new RuntimeException(String.format("Request to %s failed with status code %s and response %s", uri, statusCode, responseContent));
                logger.error("Request to motech failed", e);
                throw e;
            }

            return responseContent;

        } catch (IOException e) {
            logger.error(String.format("IO exception while sending request to %s", uri), e);
            throw new RuntimeException(e);
        }
    }

    protected String readResponse(HttpResponse response) {
        try {
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
            String content = "";
            String line;
            while ((line = reader.readLine()) != null) {
                content += line;
            }
            return content;
        } catch (IOException ex) {
            logger.warn("Could not read response");
            return null;
        }
    }

    private boolean isStatusOk(int stausCode) {
        return stausCode >= 200 && stausCode <= 299;
    }
}
