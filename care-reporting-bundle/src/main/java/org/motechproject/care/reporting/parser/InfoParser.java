package org.motechproject.care.reporting.parser;

import org.motechproject.commcare.domain.FormValueElement;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface InfoParser {
    Map<String, String> parse(FormValueElement element);

    Map<String, String> parse(FormValueElement element, boolean isRecursive);

    Map<String, Object> parse(Object object);

    Map<String, Object> parse(Map map);

    void setConvertToCamelCase(boolean convertToCamelCase);

    void setRestrictedElements(List<String> restrictedElements);

    void setKeyConversionMap(Map<String, String> keyConversionMap);

    boolean isCamelCaseConversionOn();

    Collection<String> getRestrictedElements();

    Map<String, String> getKeyConversionMap();
}
