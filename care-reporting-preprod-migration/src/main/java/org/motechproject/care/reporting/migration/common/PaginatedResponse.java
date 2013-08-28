package org.motechproject.care.reporting.migration.common;

import com.google.gson.JsonArray;

public class PaginatedResponse {
   private JsonArray records;
   private PaginatedResponseMeta meta;

    public PaginatedResponse(JsonArray records, PaginatedResponseMeta meta) {
        this.records = records;
        this.meta = meta;
    }

    public JsonArray getRecords() {
        return records;
    }

    public PaginatedResponseMeta getMeta() {
        return meta;
    }
}
