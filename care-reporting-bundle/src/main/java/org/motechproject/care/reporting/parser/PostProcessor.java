package org.motechproject.care.reporting.parser;

import java.util.Map;

public interface PostProcessor {
    public static PostProcessor COPY_CASE_ID_AS_MOTHER_CASE_POST_PROCESSOR = new PostProcessor() {
        @Override
        public void transform(Map<String, String> values) {
            if (values.containsKey("caseId")) values.put("motherCase", values.get("caseId"));
        }
    };

    public static PostProcessor COPY_CASE_ID_AS_CHILD_CASE_POST_PROCESSOR = new PostProcessor() {
        @Override
        public void transform(Map<String, String> values) {
            if (values.containsKey("caseId")) values.put("childCase", values.get("caseId"));
        }
    };

    public static PostProcessor COPY_USER_ID_AS_FLW_ID_POST_PROCESSOR = new PostProcessor() {
        @Override
        public void transform(Map<String, String> values) {
            if (values.containsKey("userID")) values.put("flw", values.get("userID"));
        }
    };

    void transform(Map<String, String> values);
}
