package org.motechproject.care.reporting.migration.service;

import org.motechproject.care.reporting.migration.common.Constants;
import org.motechproject.care.reporting.migration.common.PaginatedResult;
import org.motechproject.care.reporting.migration.common.PaginationOption;
import org.motechproject.care.reporting.migration.common.ResponseParser;

import java.util.Map;

public class Paginator {

    private final Map<String, String> parameters;
    private final PaginationScheme paginationScheme;
    private final ResponseParser parser;
    private PaginatedResult previousPage;

    public Paginator(Map<String, String> parameters, PaginationScheme paginationScheme, ResponseParser parser) {
        this.parameters = parameters;
        this.paginationScheme = paginationScheme;
        this.parser = parser;
    }

    public PaginatedResult nextPage() {
        PaginationOption currentPaginationOption;
        currentPaginationOption = previousPage != null ? previousPage.getPaginationOption() : new PaginationOption(getLimit(), getOffset());
        if (currentPaginationOption == null)
            return null;

        String result = paginationScheme.nextPage(parameters, currentPaginationOption);
        previousPage = parser.parse(result);
        return previousPage;
    }

    private int getOffset() {
        String offset = parameters.get(Constants.OFFSET);
        if (offset != null)
            return Integer.parseInt(offset);
        return PaginationOption.getDefaultOffset();
    }

    private int getLimit() {
        String offset = parameters.get(Constants.LIMIT);
        if (offset != null)
            return Integer.parseInt(offset);
        return PaginationOption.getDefaultLimit();
    }

}
