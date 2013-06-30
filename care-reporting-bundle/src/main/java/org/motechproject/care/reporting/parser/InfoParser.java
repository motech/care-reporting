package org.motechproject.care.reporting.parser;

import org.motechproject.commcare.domain.FormValueElement;

import java.util.Map;

public interface InfoParser {
    Map<String, String> parse(FormValueElement element);

    Map<String, String> parse(FormValueElement element, boolean isRecursive);

    Map<String, Object> parse(Object object);

    Map<String, Object> parse(Map map);

    void setKeyConversionMap(Map<String, String> keyConversionMap);

    FormValueElement getCaseElement(FormValueElement startElement);


    boolean shouldReportMissingCaseElement();
}
