package org.motechproject.care.reporting.utils;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class JsonUtils {

    public static <S> List<S>  parseStreams(List<InputStream> streams, Class<S[]> clazz){
        List<S> mappingEntities = new ArrayList<>();

        for (InputStream stream : streams) {
            mappingEntities.addAll(parseStream(stream, clazz));
        }

        return mappingEntities;

    }

    private static <S> List<S> parseStream(InputStream stream, Class<S[]> clazz) {
        final String jsonContent;
        try {
            jsonContent = IOUtils.toString(stream);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing mapping file", e);
        }

        return parseJson(jsonContent, clazz);
    }

    private static <S> List<S> parseJson(String json, Class<S[]> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();


        try {
            return asList(objectMapper.readValue(json, clazz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
