package org.motechproject.care.reporting.ft.pages;

import org.antlr.stringtemplate.StringTemplate;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.motechproject.care.reporting.ft.utils.FileUtils;
import org.motechproject.care.reporting.ft.utils.TemplateUtils;
import org.motechproject.care.reporting.ft.utils.TestEnvironment;

import java.io.IOException;
import java.util.Map;

public class FormPage {

    private final TestEnvironment environment;

    public FormPage() {
        environment = new TestEnvironment();
    }

    public int post(String xmlFileName) {
        return post(xmlFileName, null);
    }

    public int post(String xmlFileName, Map<String, String> placeHolderMap) {
        String requestBody = getRequestBody(xmlFileName, placeHolderMap);
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(environment.getFormUpdateEnpoint());
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
