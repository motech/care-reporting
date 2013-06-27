package org.motechproject.care.reporting.ft.pages;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.motechproject.care.reporting.ft.utils.FileUtils;
import org.motechproject.care.reporting.ft.utils.TemplateUtils;
import org.motechproject.care.reporting.ft.utils.TestEnvironment;

import java.io.IOException;
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
        return post(environment.getFormUpdateEnpoint(), xmlFileName, placeholderMap);
    }

    public int postCase(String xmlFileName) {
        return postCase(xmlFileName, null);
    }

    public int postCase(String xmlFileName, Map<String, String> placeholderMap) {
        return post(environment.getCaseUpdateEnpoint(), xmlFileName, placeholderMap);
    }

    private int post(String url, String xmlFileName, Map<String, String> placeholderMap) {
        String requestBody = getRequestBody(xmlFileName, placeholderMap);
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        try {
            postMethod.setRequestEntity(new StringRequestEntity(requestBody, "text/xml", "UTF-8"));
            return httpClient.executeMethod(postMethod);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRequestBody(String xmlFileName, Map<String, String> placeHolderMap) {
        String formXml = FileUtils.readFromClasspath(xmlFileName);
        if(placeHolderMap == null || placeHolderMap.size() == 0) {
            return formXml;
        }
        return TemplateUtils.apply(formXml, placeHolderMap);
    }
}
