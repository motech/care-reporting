package org.motechproject.care.reporting.mapper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.domain.measure.NewForm;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.motechproject.care.reporting.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionEqualsWithIgnore;

public class CareReportingMapperIT extends SpringIntegrationTest {
    @Autowired
    private Service service;

    private CareReportingMapper careReportingMapper;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        careReportingMapper = CareReportingMapper.getInstance(service);
    }

    @Test
    public void testMapsFromKeyValueToFormObject() throws Exception {

        HashMap<String, String> newFormValues = new HashMap<String, String>() {{
            put("instanceId", "1234");
            put("ageCalc", "1");
            put("dateModified", "2012-07-21T12:02:59.923+05:30");
        }};
        NewForm newForm = careReportingMapper.map(NewForm.class, newFormValues);

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

        TestData output = careReportingMapper.map(TestData.class, asMap("integerObject", null));
        assertNull(output.integerObject);

        output = careReportingMapper.map(TestData.class, asMap("shortObject", null));
        assertNull(output.shortObject);

        output = careReportingMapper.map(TestData.class, asMap("booleanObject", null));
        assertNull(output.booleanObject);

        output = careReportingMapper.map(TestData.class, asMap("decimalObject", null));
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

        ConverterDemoObject target = careReportingMapper.map(ConverterDemoObject.class, values());

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

    @Test
    public void shouldConvertKnownDateFormats() {
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59.923+06:30", new DateTime(2012, 7, 21, 11, 2, 59, 923).toDate());
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59.923+0630", new DateTime(2012, 7, 21, 11, 2, 59, 923).toDate());
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59.923+05", new DateTime(2012, 7, 21, 12, 32, 59, 923).toDate());
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59.923Z", new DateTime(2012, 7, 21, 17, 32, 59, 923).toDate());

        validateIfDateFormatIsAccepted("2012-07-21T12:02:59+06:30", new DateTime(2012, 7, 21, 11, 2, 59).toDate());
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59+0630", new DateTime(2012, 7, 21, 11, 2, 59).toDate());
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59+05", new DateTime(2012, 7, 21, 12, 32, 59).toDate());
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59Z", new DateTime(2012, 7, 21, 17, 32, 59).toDate());

        validateIfDateFormatIsAccepted("2012-07-21T12:02:59", new DateTime(2012, 7, 21, 12, 2, 59).toDate());

        validateIfDateFormatIsAccepted("2012-11-29", new DateTime(2012, 11, 29, 0, 0, 0).toDate());
        validateIfDateFormatIsAccepted("2012-01-02", new DateTime(2012, 1, 2, 0, 0, 0).toDate());
    }

    @Test
    public void shouldSetDateAsNullIfOfUnknownFormat() {
        validateIfDateFormatIsAccepted("01/01/2012", null);
    }

    private void validateIfDateFormatIsAccepted(final String input, Date expected) {
        DateContainer actualDate = careReportingMapper.map(DateContainer.class, new HashMap<String, String>() {{
            put("date", input);
        }});

        assertEquals(expected, actualDate.getDate());
    }

    public static class DateContainer {
        private Date date;

        public void setDate(Date date) {
            this.date = date;
        }

        public Date getDate() {
            return date;
        }
    }

}
