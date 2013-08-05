package org.motechproject.care.reporting.migration.service;

import org.apache.commons.httpclient.NameValuePair;
import org.motechproject.care.reporting.migration.common.PaginationOption;

import java.util.Map;

public interface PaginationScheme {
    public String nextPage(Map<String, String> parameters, PaginationOption paginationOption);
}
