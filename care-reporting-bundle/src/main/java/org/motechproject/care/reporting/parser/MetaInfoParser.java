
package org.motechproject.care.reporting.parser;

import org.motechproject.commcare.domain.CommcareForm;

import java.util.HashMap;
import java.util.Map;

public class MetaInfoParser extends BaseInfoParser {
    public static final HashMap<String, String> keyConversionMap = new HashMap<String, String>() {{
        put("instanceID", "instanceId");
    }};

    public MetaInfoParser(InfoParser infoParser) {
        super(infoParser);
        infoParser.setKeyConversionMap(keyConversionMap);
    }

    public Map<String, String> parse(CommcareForm commcareForm) {
        return (Map) infoParser.parse(commcareForm.getMetadata());
    }
}
