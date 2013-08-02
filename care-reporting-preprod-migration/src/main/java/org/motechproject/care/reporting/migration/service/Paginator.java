package org.motechproject.care.reporting.migration.service;

import org.apache.commons.httpclient.NameValuePair;
import org.motechproject.care.reporting.migration.common.PaginatedResult;
import org.motechproject.care.reporting.migration.common.PaginationOption;
import org.motechproject.care.reporting.migration.common.ResponseParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Paginator {

    private final NameValuePair[] parameters;
    private final PaginationScheme paginationScheme;
    private final ResponseParser parser;
    private PaginatedResult previousPage;

    private static final Logger logger = LoggerFactory.getLogger(Paginator.class);

    public Paginator(NameValuePair[] parameters, PaginationScheme paginationScheme, ResponseParser parser) {
        this.parameters = parameters;
        this.paginationScheme = paginationScheme;
        this.parser = parser;
    }

    public PaginatedResult nextPage() {
        PaginationOption currentPaginationOption;
        currentPaginationOption = previousPage != null ? previousPage.getPaginationOption() : new PaginationOption();
        if (currentPaginationOption == null)
            return null;

        String result = paginationScheme.nextPage(parameters, currentPaginationOption);
        previousPage = parser.parse(result);
        return previousPage;
    }


}
