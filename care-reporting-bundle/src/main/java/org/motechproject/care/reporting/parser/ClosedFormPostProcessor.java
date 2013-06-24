package org.motechproject.care.reporting.parser;

import java.util.Map;

public class ClosedFormPostProcessor implements PostProcessor {
    @Override
    public void transform(Map<String, String> map) {
        if(map.containsKey("close")) {
            map.put("close", "true");
        }
    }
}
