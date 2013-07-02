
package org.motechproject.care.reporting.parser;

import org.motechproject.commcare.domain.CommcareForm;

import java.util.HashMap;
import java.util.Map;

public class MetaInfoParser extends BaseInfoParser {
    public MetaInfoParser(InfoParser infoParser) {
        super(infoParser);
    }

    public Map<String, String> parse(CommcareForm commcareForm) {
        HashMap<String, String> metaInfoMap = new HashMap<>();
        metaInfoMap.put("xmlns", commcareForm.getForm().getAttributes().get("xmlns"));
        metaInfoMap.putAll((Map) infoParser.parse(commcareForm.getMetadata()));
        return metaInfoMap;
    }
}
