package org.motechproject.care.reporting.parser;

import java.util.Map;

public interface PostProcessor {
    void transform(Map<String, String> values);
}
