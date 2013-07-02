package org.motechproject.care.reporting.parser;

import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class ChildInfoParser extends FormInfoParser {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    public ChildInfoParser(InfoParser infoParser) {
        super(infoParser, FormSegment.CHILD);
    }

    public List<Map<String, String>> parse(CommcareForm commcareForm) {
        List<Map<String, String>> childrenMap = new ArrayList<>();

        List<FormValueElement> childInfoElements = commcareForm.getForm().getAllElements("child_info");

        for (FormValueElement childInfoElement : childInfoElements) {
            Map<String, String> childMap = parse(childInfoElement, commcareForm);
            if (childMap == null) {
                logger.warn(format("Skipping processing of child form with instance id %s", commcareForm.getId()));
                continue;
            }
            childrenMap.add(childMap);
        }
        return childrenMap;
    }
}
