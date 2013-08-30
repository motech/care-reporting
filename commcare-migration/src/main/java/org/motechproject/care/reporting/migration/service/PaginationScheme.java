package org.motechproject.care.reporting.migration.service;

import org.motechproject.care.reporting.migration.common.Page;

import java.util.Map;

public interface PaginationScheme {
    public String nextPage(Map<String, String> parameters, Page paginationOption);
}
