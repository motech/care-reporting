package org.motechproject.care.reporting.mapper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import static junit.framework.Assert.assertEquals;

public class ProviderSyncMapperTest {

    private ProviderSyncMapper providerSyncMapper;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        providerSyncMapper = new ProviderSyncMapper();
    }

    @Test
    public void shouldConvertKnownDateFormats() {
        DateTimeZone dateTimeZone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("IST"));

        validateIfDateFormatIsAccepted("2012-07-21T12:02:59.923+06:30", new DateTime(2012, 7, 21, 11, 2, 59, 923,
                dateTimeZone).toDate());
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59.923+0630", new DateTime(2012, 7, 21, 11, 2, 59, 923,
                dateTimeZone).toDate());
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59.923+05", new DateTime(2012, 7, 21, 12, 32, 59, 923,
                dateTimeZone).toDate());
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59.923Z", new DateTime(2012, 7, 21, 17, 32, 59, 923,
                dateTimeZone).toDate());

        validateIfDateFormatIsAccepted("2012-07-21T12:02:59+06:30", new DateTime(2012, 7, 21, 11, 2, 59,
                dateTimeZone).toDate());
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59+0630", new DateTime(2012, 7, 21, 11, 2, 59,
                dateTimeZone).toDate());
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59+05", new DateTime(2012, 7, 21, 12, 32, 59,
                dateTimeZone).toDate());
        validateIfDateFormatIsAccepted("2012-07-21T12:02:59Z", new DateTime(2012, 7, 21, 17, 32, 59,
                dateTimeZone).toDate());

        validateIfDateFormatIsAccepted("2012-07-21T12:02:59", new DateTime(2012, 7, 21, 12, 2, 59).toDate());

        validateIfDateFormatIsAccepted("2012-11-29", new DateTime(2012, 11, 29, 0, 0, 0).toDate());
        validateIfDateFormatIsAccepted("2012-01-02", new DateTime(2012, 1, 2, 0, 0, 0).toDate());

        validateIfDateFormatIsAccepted("29/11/2012", new DateTime(2012, 11, 29, 0, 0, 0).toDate());
        validateIfDateFormatIsAccepted("02/01/2012", new DateTime(2012, 1, 2, 0, 0, 0).toDate());
    }

    @Test
    public void shouldThrowExceptionIfDateFormat() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(String.format("Exception when setting 01/13/2012 to date"));

        validateIfDateFormatIsAccepted("01/13/2012", null);
    }

    private void validateIfDateFormatIsAccepted(final String input, Date expected) {
        DateContainer actualDate = providerSyncMapper.map(DateContainer.class, new HashMap<String, String>() {{
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
