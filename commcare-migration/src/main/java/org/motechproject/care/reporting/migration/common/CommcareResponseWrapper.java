package org.motechproject.care.reporting.migration.common;

import java.util.Map;

public class CommcareResponseWrapper {
    private String responseBody;
    private Map<String, String> headers;

    public CommcareResponseWrapper(String responseBody, Map<String, String> headers) {
        this.responseBody = responseBody;
        this.headers = headers;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getResponseBody() {
        return responseBody;
    }

}
