package org.motechproject.care.reporting.parser;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.builder.CaseEventBuilder;
import org.motechproject.commcare.events.CaseEvent;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CaseInfoParserTest {


    @Mock
    private InfoParser infoParser;

    private CaseInfoParser caseInfoParser;

    @Before
    public void setUp() {
        initMocks(this);
        caseInfoParser = new CaseInfoParser(infoParser);
    }

    @Test
    public void testNullCaseEvent() {
        final HashMap<String, String> expectedMap = new HashMap<>();

        final Map<String, String> actualMap = caseInfoParser.parse(null);

        ReflectionAssert.assertReflectionEquals(expectedMap, actualMap);
    }

    @Test
    public void testWithoutFieldValues() {
        String serverModifiedOn = DateTime.now().toString();
        String dateModified = "2012-04-03";
        final HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("caseId", "id");
        expectedMap.put("userId", "userId");
        expectedMap.put("apiKey", "key");
        expectedMap.put("dateModified", dateModified);
        expectedMap.put("serverDateModified", serverModifiedOn);
        expectedMap.put("action", "CREATE");
        expectedMap.put("caseType", "cc_bihar_newborn");
        expectedMap.put("caseName", "lad");
        expectedMap.put("ownerId", "d823ea3d392a06f8b991e9e49394ce45");

        final CaseEvent caseEvent = new CaseEventBuilder("id").withUserId("userId")
                .withApiKey("key")
                .withDateModified(dateModified).withAction("CREATE").withCaseType("cc_bihar_newborn")
                .withCaseName("lad").withOwnerId("d823ea3d392a06f8b991e9e49394ce45")
                .withServerModifiedOn(serverModifiedOn)
                .build();
        caseEvent.setFieldValues(null);

        final Map<String, String> actualMap = caseInfoParser.parse(caseEvent);

        ReflectionAssert.assertReflectionEquals(expectedMap, actualMap);
    }

    @Test
    public void testWithFieldValues() {
        String dateModified = "2012-04-03";
        String serverModifiedOn = DateTime.now().toString();
        final Map<String, String> caseEventFieldValues = new HashMap<String, String>() {{
            put("field_name1", "value1");
            put("field_name2", "value2");
        }};
        final CaseEvent caseEvent = new CaseEventBuilder("id").withUserId("userId")
                .withApiKey("key")
                .withDateModified(dateModified).withAction("CREATE").withCaseType("cc_bihar_newborn")
                .withCaseName("lad").withOwnerId("d823ea3d392a06f8b991e9e49394ce45")
                .with(caseEventFieldValues)
                .withServerModifiedOn(serverModifiedOn)
                .build();
        final Map<String, Object> expectedFieldValues = new HashMap<String, Object>() {{
            put("fieldName1", "value1");
            put("fieldName2", "value2");
        }};
        final Map<String, Object> caseEventFields = new HashMap<String, Object>() {{
            put("field_name1", "value1");
            put("field_name2", "value2");
        }};
        final HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("caseId", "id");
        expectedMap.put("userId", "userId");
        expectedMap.put("apiKey", "key");
        expectedMap.put("dateModified", dateModified);
        expectedMap.put("serverDateModified", serverModifiedOn);
        expectedMap.put("action", "CREATE");
        expectedMap.put("caseType", "cc_bihar_newborn");
        expectedMap.put("caseName", "lad");
        expectedMap.put("ownerId", "d823ea3d392a06f8b991e9e49394ce45");
        expectedMap.put("fieldName1", "value1");
        expectedMap.put("fieldName2", "value2");
        when(infoParser.parse(caseEventFields)).thenReturn(expectedFieldValues);

        final Map<String, String> actualMap = caseInfoParser.parse(caseEvent);

        ReflectionAssert.assertReflectionEquals(expectedMap, actualMap);
    }
}

