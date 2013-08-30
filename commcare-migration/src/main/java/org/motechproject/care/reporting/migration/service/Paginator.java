package org.motechproject.care.reporting.migration.service;

import org.motechproject.care.reporting.migration.common.Constants;
import org.motechproject.care.reporting.migration.common.Page;
import org.motechproject.care.reporting.migration.common.PaginatedResponse;
import org.motechproject.care.reporting.migration.common.ResponseParser;

import java.util.Map;

public class Paginator {

    private final Map<String, String> parameters;
    private final PaginationScheme paginationScheme;
    private final ResponseParser parser;
    private Page nextPaginationOption;

    public Paginator(Map<String, String> parameters, PaginationScheme paginationScheme, ResponseParser parser) {
        this.parameters = parameters;
        this.paginationScheme = paginationScheme;
        this.parser = parser;
        nextPaginationOption = new Page(getDefaultInitialPageOffset(), getDefaultLimit());
    }

    public PaginatedResponse nextPage() {
        if (nextPaginationOption == null) {
            return null;
        }

        String result = paginationScheme.nextPage(parameters, nextPaginationOption);
        PaginatedResponse paginatedResult = parser.parse(result);
        nextPaginationOption = paginatedResult.getMeta().getNextPage();
        return paginatedResult;
    }

    private int getDefaultInitialPageOffset() {
        String offset = parameters.get(Constants.OFFSET);
        if (offset != null)
            return Integer.parseInt(offset);
        return Constants.DEFAULT_INITIAL_PAGE_OFFSET;
    }

    private int getDefaultLimit() {
        String offset = parameters.get(Constants.LIMIT);
        if (offset != null)
            return Integer.parseInt(offset);
        return Constants.DEFAULT_PAGE_LIMIT;
    }

}
