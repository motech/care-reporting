package org.motechproject.care.reporting.utils;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.motechproject.care.reporting.model.MappingEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class JsonUtils {

    public static List<MappingEntity> parseStreams(List<InputStream> streams) {
        List<MappingEntity> mappingEntities = new ArrayList<>();

        for (InputStream stream : streams) {
            mappingEntities.addAll(parseStream(stream));
        }

        return mappingEntities;
    }

    private static List<MappingEntity> parseStream(InputStream stream) {
        final String jsonContent;
        try {
            jsonContent = IOUtils.toString(stream);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing mapping file", e);
        }

        return parseJson(jsonContent);
    }

    private static List<MappingEntity> parseJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return asList(objectMapper.readValue(json, MappingEntity[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
