
package parser;

import org.motechproject.commcare.domain.CommcareForm;

import java.util.HashMap;
import java.util.Map;

public class MetaInfoParser {
    public static final HashMap<String, String> keyConversionMap = new HashMap<String, String>() {{
        put("instanceID", "formId");
    }};

    private final InfoParser infoParser = new InfoParser();

    public Map<String, String> parse(CommcareForm commcareForm) {

        infoParser.setKeyConversionMap(keyConversionMap);
        return infoParser.parse(commcareForm.getMetadata());
    }
}
