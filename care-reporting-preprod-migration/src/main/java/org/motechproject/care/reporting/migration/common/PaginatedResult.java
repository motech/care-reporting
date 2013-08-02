package org.motechproject.care.reporting.migration.common;

import com.google.gson.JsonArray;

public class PaginatedResult {
   private JsonArray response;
   private PaginationOption paginationOption;

    public PaginatedResult(JsonArray response, PaginationOption paginationOption) {
        this.response = response;
        this.paginationOption = paginationOption;
    }

    public JsonArray getResponse() {
        return response;
    }

    public PaginationOption getPaginationOption() {
        return paginationOption;
    }
}
