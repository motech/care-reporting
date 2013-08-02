package org.motechproject.care.reporting.migration.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ResponseParser {

    public PaginatedResult parse(String result) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonResponse = (JsonObject) jsonParser.parse(result);
        JsonObject meta = (JsonObject) jsonResponse.get("meta");
        PaginationOption paginationOption = extractPaginationOption(meta);
        JsonElement objects = jsonResponse.get("objects");
        JsonArray responseEntity = objects.getAsJsonArray();
        return new PaginatedResult(responseEntity, paginationOption);
    }

    private PaginationOption extractPaginationOption(JsonObject meta) {
        JsonElement next = meta.get("next");
        if (next.isJsonNull())
            return null;
        Pattern pattern = Pattern.compile("\\?limit=(\\d+)&offset=(\\d+)");
        Matcher matcher = pattern.matcher(next.getAsString());
        boolean found = matcher.find();
        if (!found)
            throw new RuntimeException(String.format("Invalid next option %s. Pattern should be ?limit=<limit_value>&offset=<offset_value>", next.getAsString()));

        int limit = Integer.parseInt(matcher.group(1));
        int offset = Integer.parseInt(matcher.group(2));
        return new PaginationOption(limit, offset);
    }
}
