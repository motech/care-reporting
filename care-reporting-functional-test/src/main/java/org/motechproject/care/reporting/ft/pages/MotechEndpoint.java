package org.motechproject.care.reporting.ft.pages;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.motechproject.care.reporting.ft.utils.FileUtils;
import org.motechproject.care.reporting.ft.utils.TemplateUtils;
import org.motechproject.care.reporting.ft.utils.TestEnvironment;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MotechEndpoint {

    private final TestEnvironment environment;

    public MotechEndpoint() {
        environment = new TestEnvironment();
    }

    public int postForm(String xmlFileName) {
        return postForm(xmlFileName, null);
    }

    public int postForm(String xmlFileName, Map<String, String> placeholderMap) {
        return post(environment.getFormUpdateEnpoint(), getBody(xmlFileName, placeholderMap), Collections.EMPTY_MAP);
    }

    public int postCase(String xmlFileName, Map<String, String> placeholderMap) {
        return post(environment.getCaseUpdateEnpoint(), getBody(xmlFileName, placeholderMap), Collections.EMPTY_MAP);
    }

    public int postFakeTimeRequest(final DateTime futureTimeToMove) {
        Map<String, String> postParameters = new HashMap<String, String>() {{
            put("newDateTime", futureTimeToMove.toString("dd/MM/yyyy HH:mm"));

        }};
        return post(environment.updateFakeTimeEndPoint(), null, postParameters);
    }

    public DateTime getCurrentFakeTime() {
        try {
            GetMethod method = new GetMethod(environment.getFakeTimeEndPoint());
            new HttpClient().executeMethod(method);

            String currentTimeAsString = StringUtils.trim(method.getResponseBodyAsString());
            return DateTimeFormat.forPattern("dd/MM/yyyy HH:mm").parseDateTime(currentTimeAsString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int post(String url, String requestBody, Map<String, String> postParameters) {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        for (Map.Entry<String, String> postParameter : postParameters.entrySet()) {
            postMethod.addParameter(postParameter.getKey(), postParameter.getValue());
        }

        try {
            if (requestBody != null)
                postMethod.setRequestEntity(new StringRequestEntity(requestBody, "text/xml", "UTF-8"));
            return httpClient.executeMethod(postMethod);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBody(String fileName, Map<String, String> placeHolderMap) {
        String content = FileUtils.readFromClasspath(fileName);
        if (placeHolderMap == null || placeHolderMap.size() == 0) {
            return content;
        }
        return TemplateUtils.apply(content, placeHolderMap);
    }
}
