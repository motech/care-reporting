package org.motechproject.care.reporting.ft.utils;

import java.sql.Timestamp;
import java.util.Map;

public class RecordUpdatedCondition implements TimedRunnerBreakCondition<Map<String, Object>> {
    private Timestamp updatedOnOrAfter;

    public RecordUpdatedCondition() {
        this(System.currentTimeMillis());
    }

    public RecordUpdatedCondition(long timestamp) {
        this.updatedOnOrAfter = new Timestamp(timestamp);
    }

    @Override
    public boolean test(Map<String, Object> obj) {
        if(obj == null) {
            return false;
        }

        Timestamp creationTime = (Timestamp) obj.get("creation_time");
        Timestamp lastModifiedTime = (Timestamp) obj.get("last_modified_time");
        Timestamp compareTo = lastModifiedTime == null ? creationTime : lastModifiedTime;

        if(compareTo == null) {
            throw new RuntimeException("both creation time and last modified time are null on a record found");
        }

        return compareTo.compareTo(updatedOnOrAfter) >= 0;
    }
}
