package org.motechproject.care.reporting.migration.service;

import org.apache.commons.httpclient.NameValuePair;
import org.motechproject.care.reporting.migration.common.PaginationOption;

public interface PaginationScheme {
    public String nextPage(NameValuePair[] parameters, PaginationOption paginationOption);
}
