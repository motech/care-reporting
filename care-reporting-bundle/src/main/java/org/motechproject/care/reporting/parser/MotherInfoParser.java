package org.motechproject.care.reporting.parser;

import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.commcare.domain.CommcareForm;

import java.util.Map;

public class MotherInfoParser extends FormInfoParser {

    public MotherInfoParser(InfoParser infoParser) {
        super(infoParser, FormSegment.MOTHER);
    }

    public Map<String, String> parse(CommcareForm commcareForm) {
        return parse(commcareForm.getForm(), commcareForm);
    }
}

