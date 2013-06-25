package org.motechproject.care.reporting.mapper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.domain.measure.NewForm;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionEqualsWithIgnore;

public class CareReportingMapperIT extends SpringIntegrationTest {

    @Autowired
    private CareReportingMapper careReportingMapper;

    @Test
    public void testMapsFromKeyValueToFormObject() throws Exception {

        HashMap<String, String> newFormValues = new HashMap<String, String>() {{
            put("instanceId", "1234");
            put("ageCalc", "1");
            put("dateModified", "2012-07-21T12:02:59.923+05:30");
        }};

        NewForm newForm = careReportingMapper.map(newFormValues, NewForm.class);

        assertEquals("1234", newForm.getInstanceId());
        assertEquals((short) 1, (short) newForm.getAgeCalc());
        assertEquals(new DateTime(2012, 7, 21, 12, 2, 59, 923, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate(), newForm.getDateModified());
    }

    @Test
    public void shouldSetPrimitiveValueObjectsAsNull(){
        TestData testData = new TestData();
        testData.integerObject = new Integer(1);
        testData.shortObject = new Short("1");
        testData.booleanObject = Boolean.TRUE;
        testData.decimalObject = new BigDecimal("1.1");

        TestData output = careReportingMapper.map(asMap("integerObject", null), TestData.class);
        assertNull(output.integerObject);

        output = careReportingMapper.map(asMap("shortObject", null), TestData.class);
        assertNull(output.shortObject);

        output = careReportingMapper.map(asMap("booleanObject", null), TestData.class);
        assertNull(output.booleanObject);

        output = careReportingMapper.map(asMap("decimalObject", null), TestData.class);
        assertNull(output.decimalObject);
    }

    @Test
    public void shouldAutoConvertSupportedObject() throws Exception {
        Flw flw = new Flw();
        flw.setFlwId("89fda0284e008d2e0c980fb13f96c45a");
        template.save(flw);

        FlwGroup flwGroup = new FlwGroup();
        flwGroup.setGroupId("89fda0284e008d2e0c980fb13f96c45a");
        template.save(flwGroup);

        MotherCase motherCase = new MotherCase();
        motherCase.setCaseId("94d5374f-290e-409f-bc57-86c2e4bcc43f");
        template.save(motherCase);

        ChildCase childCase = new ChildCase();
        childCase.setCaseId("94d5374f-290e-409f-bc57-86c2e4bcc43f");
        template.save(childCase);

        ConverterDemoObject target = careReportingMapper.map(values(), ConverterDemoObject.class);

        assertReflectionEqualsWithIgnore(flw, target.flw);
        assertReflectionEqualsWithIgnore(flwGroup, target.flwGroup);
        assertReflectionEqualsWithIgnore(motherCase, target.motherCase);
        assertReflectionEqualsWithIgnore(childCase, target.childCase);
    }


    private HashMap<String, Object> asMap(final String key, final Object value) {
        return new HashMap<String, Object>(){{
            put(key, value);
        }};
    }

    private Map<String, Object> values() {
        return new HashMap<String, Object>() {{
            put("flw", "89fda0284e008d2e0c980fb13f96c45a");
            put("flwGroup", "89fda0284e008d2e0c980fb13f96c45a");
            put("motherCase", "94d5374f-290e-409f-bc57-86c2e4bcc43f");
            put("childCase", "94d5374f-290e-409f-bc57-86c2e4bcc43f");
        }};
    }
}
