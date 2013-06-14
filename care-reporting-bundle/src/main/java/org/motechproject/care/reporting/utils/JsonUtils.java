package org.motechproject.care.reporting.utils;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.motechproject.care.reporting.model.MappingEntity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class JsonUtils {

    public static List<MappingEntity> parseJson(String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return asList(objectMapper.readValue(json, MappingEntity[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFile(String filePath){
        try {
            return FileUtils.readFileToString(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<MappingEntity> parseFile(String filePath){
        final String json = readFile(filePath);
        return parseJson(json);
    }
}
