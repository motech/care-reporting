
package org.motechproject.care.reporting.parser;

import org.motechproject.commcare.domain.CommcareForm;

import java.util.Map;

public class MetaInfoParser extends BaseInfoParser {
    public MetaInfoParser(InfoParser infoParser) {
        super(infoParser);
    }

    public Map<String, String> parse(CommcareForm commcareForm) {
        return (Map) infoParser.parse(commcareForm.getMetadata());
    }
}
