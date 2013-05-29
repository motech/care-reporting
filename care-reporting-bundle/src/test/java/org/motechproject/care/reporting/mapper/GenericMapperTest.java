package org.motechproject.care.reporting.mapper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.motechproject.care.reporting.domain.measure.NewForm;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class GenericMapperTest {

    @Test
    public void testMapsFromKeyValueToFormObject() throws Exception {

        HashMap<String, String> newFormValues = new HashMap<String, String>() {{
            put("formId", "1234");
            put("ageCalc", "1");
            put("dateModified", "2012-07-21T12:02:59.923+05:30");
        }};

        NewForm newForm = new GenericMapper().map(newFormValues, NewForm.class);

        assertEquals("1234", newForm.getFormId());
        assertEquals((short) 1, (short) newForm.getAgeCalc());
        assertEquals(new DateTime(2012, 7, 21, 12, 2, 59, 923, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate(), newForm.getDateModified());
    }
}
