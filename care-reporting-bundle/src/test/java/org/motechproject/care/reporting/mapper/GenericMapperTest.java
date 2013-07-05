package org.motechproject.care.reporting.mapper;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class GenericMapperTest {

    private ProviderSyncMapper genericMapper;

    @Before
    public void setUp() throws Exception {
        genericMapper = ProviderSyncMapper.getInstance();
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

        validateIfDateFormatIsAccepted("29/11/2012", new DateTime(2012, 11, 29, 0, 0, 0).toDate());
        validateIfDateFormatIsAccepted("02/01/2012", new DateTime(2012, 1, 2, 0, 0, 0).toDate());
    }

    @Test
    public void shouldSetDateAsNullIfOfUnknownFormat() {
        validateIfDateFormatIsAccepted("01/13/2012", null);
    }

    private void validateIfDateFormatIsAccepted(final String input, Date expected) {
        DateContainer actualDate = genericMapper.map(new HashMap<String, String>() {{
            put("date", input);
        }}, DateContainer.class);

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
