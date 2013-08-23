package org.motechproject.care.reporting.parser;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Map;

public class DelFupFixPostProcessor implements PostProcessor {

    private static final DateTime EPOCH_LOCAL = new DateTime(1970, 1, 1, 0, 0, 0);
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public void transform(Map<String, String> values) {
        String delFup = values.get("delFup");
        if (StringUtils.isEmpty(delFup) || !StringUtils.isNumeric(delFup)) {
            return;
        }
        DateTime date = EPOCH_LOCAL.plusDays(Integer.parseInt(delFup));
        String formattedDate = new SimpleDateFormat(DATE_FORMAT).format(date.toDate());
        values.put("delFup", formattedDate);
    }
}
