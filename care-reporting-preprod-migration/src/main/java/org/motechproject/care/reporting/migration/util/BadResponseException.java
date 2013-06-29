package org.motechproject.care.reporting.migration.util;

public class BadResponseException extends RuntimeException {

    private final int statusCode;

    public BadResponseException(String url, int statusCode, String response) {
        super(String.format("Request to url %s failed with status code %s and response %s", url, statusCode, response));
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
