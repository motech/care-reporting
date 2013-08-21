package org.motechproject.care.reporting.processors;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

public interface ComputedFieldsProcessor {
    void compute(Map<String, String> values);

    public static class Utils {

        public static void applyComputedFields(List<ComputedFieldsProcessor> processors, final Map<String, String> map) {
            CollectionUtils.forAllDo(processors, new Closure() {
                @Override
                public void execute(Object input) {
                    ((ComputedFieldsProcessor) input).compute(map);
                }
            });
        }
    }
}

