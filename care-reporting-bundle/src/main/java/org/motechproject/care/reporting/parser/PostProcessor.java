package org.motechproject.care.reporting.parser;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

public interface PostProcessor {
    public static PostProcessor COPY_CASE_ID_AS_MOTHER_CASE_POST_PROCESSOR = new PostProcessor() {
        @Override
        public void transform(Map<String, String> values) {
            if (values.containsKey("caseId")) values.put("motherCase", values.get("caseId"));
        }
    };

    public static PostProcessor COPY_MOTHER_ID_AS_MOTHER_CASE = new PostProcessor() {
        @Override
        public void transform(Map<String, String> values) {
            if (values.containsKey("motherId")) values.put("motherCase", values.get("motherId"));
        }
    };

    public static PostProcessor COPY_CASE_ID_AS_CHILD_CASE_POST_PROCESSOR = new PostProcessor() {
        @Override
        public void transform(Map<String, String> values) {
            if (values.containsKey("caseId")) values.put("childCase", values.get("caseId"));
        }
    };

    public static PostProcessor FORM_COPY_USER_ID_AS_FLW = new PostProcessor() {
        @Override
        public void transform(Map<String, String> values) {
            if (values.containsKey("userID")) values.put("flw", values.get("userID"));
        }
    };

    public static PostProcessor CASE_COPY_USER_ID_AS_FLW = new PostProcessor() {
        @Override
        public void transform(Map<String, String> values) {
            if (values.containsKey("userId")) values.put("flw", values.get("userId"));
        }
    };

    public static PostProcessor COPY_OWNER_ID_AS_FLW_GROUP = new PostProcessor() {
        @Override
        public void transform(Map<String, String> values) {
            if (values.containsKey("ownerId")) values.put("flwGroup", values.get("ownerId"));
        }
    };

    void transform(Map<String, String> values);

    public static class Utils {

        public static void applyPostProcessors(List<PostProcessor> processors, final Map<String, String> map) {
            CollectionUtils.forAllDo(processors, new Closure() {
                @Override
                public void execute(Object input) {
                    ((PostProcessor) input).transform(map);
                }
            });
        }
    }
}
