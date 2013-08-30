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

    public PaginatedResponse parse(String result) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonResponse = (JsonObject) jsonParser.parse(result);
        JsonObject meta = (JsonObject) jsonResponse.get("meta");
        PaginatedResponseMeta resultMeta = constructMeta(meta);
        JsonElement objects = jsonResponse.get("objects");
        JsonArray responseEntity = objects.getAsJsonArray();
        return new PaginatedResponse(responseEntity, resultMeta);
    }

    private PaginatedResponseMeta constructMeta(JsonObject meta) {
        Page nextPage = constructPage(meta.get("next"));
        Page previousPage= constructPage(meta.get("previous"));
        Page currentPage = new Page(getChildElementAsInteger(meta, "offset"), getChildElementAsInteger(meta, "limit"));
        int totalCount = getChildElementAsInteger(meta, "total_count");
        return new PaginatedResponseMeta(currentPage, nextPage, previousPage, totalCount);
    }

    private int getChildElementAsInteger(JsonObject parent, String childName) {
        return Integer.parseInt(parent.get(childName).getAsString());
    }

    private Page constructPage(JsonElement paginationOption) {
        if (paginationOption.isJsonNull())
            return null;

        String url = paginationOption.getAsString();
        int limit = Integer.parseInt(getOptionFromUrl(url, "limit"));
        int offset = Integer.parseInt(getOptionFromUrl(url, "offset"));
        return new Page(offset, limit);
    }

    private String getOptionFromUrl(String url, String option) {
        String regex = ".*" + option + "=(\\d+).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        boolean found = matcher.find();
        if (!found)
            throw new RuntimeException(String.format("Invalid %s option, %s", option, url));
        return matcher.group(1);
    }
}
