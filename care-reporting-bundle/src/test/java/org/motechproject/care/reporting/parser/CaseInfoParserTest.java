package org.motechproject.care.reporting.parser;

import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.builder.CaseEventBuilder;
import org.motechproject.commcare.events.CaseEvent;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.HashMap;
import java.util.Map;

public class CaseInfoParserTest {

    private CaseInfoParser infoParser;

    @Before
    public void setUp(){
        infoParser = new CaseInfoParser();
    }

    @Test
    public void testNullCaseEvent(){
        final HashMap<String, String> expectedMap = new HashMap<>();

        final Map<String, String> actualMap = infoParser.parse(null);

        ReflectionAssert.assertReflectionEquals(expectedMap, actualMap);
    }

    @Test
    public void testWithoutFieldValues(){

        final HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("caseId", "id");
        expectedMap.put("userId", "userId");
        expectedMap.put("apiKey", "key");
        expectedMap.put("dateModified", "2012-04-03");
        expectedMap.put("action", "CREATE");
        expectedMap.put("caseType", "cc_bihar_newborn");
        expectedMap.put("caseName", "lad");
        expectedMap.put("ownerId", "d823ea3d392a06f8b991e9e49394ce45");

        final CaseEvent caseEvent = new CaseEventBuilder("id").withUserId("userId")
                .withApiKey("key")
                .withDateModified("2012-04-03").withAction("CREATE").withCaseType("cc_bihar_newborn")
                .withCaseName("lad").withOwnerId("d823ea3d392a06f8b991e9e49394ce45").build();
        caseEvent.setFieldValues(null);

        final Map<String, String> actualMap = infoParser.parse(caseEvent);

        ReflectionAssert.assertReflectionEquals(expectedMap, actualMap);
    }


    @Test
    public void testWithFieldValues(){

        final HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("caseId", "id");
        expectedMap.put("userId", "userId");
        expectedMap.put("apiKey", "key");
        expectedMap.put("dateModified", "2012-04-03");
        expectedMap.put("action", "CREATE");
        expectedMap.put("caseType", "cc_bihar_newborn");
        expectedMap.put("caseName", "lad");
        expectedMap.put("ownerId", "d823ea3d392a06f8b991e9e49394ce45");
        expectedMap.put("fieldName1", "value1");
        expectedMap.put("fieldName2", "value2");


        final CaseEvent caseEvent = new CaseEventBuilder("id").withUserId("userId")
                .withApiKey("key")
                .withDateModified("2012-04-03").withAction("CREATE").withCaseType("cc_bihar_newborn")
                .withCaseName("lad").withOwnerId("d823ea3d392a06f8b991e9e49394ce45")
                .with("field_name1", "value1").with("field_name2", "value2")
                .build();


        final Map<String, String> actualMap = infoParser.parse(caseEvent);

        ReflectionAssert.assertReflectionEquals(expectedMap, actualMap);
    }
}

