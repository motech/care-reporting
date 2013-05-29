
package parser;

import org.motechproject.care.reporting.utils.StringUtils;
import org.motechproject.commcare.domain.CommcareForm;

import java.util.HashMap;
import java.util.Map;

public class MetaInfoParser {
    public static final HashMap<String, String> keyConversionMap = new HashMap<String, String>() {{
        put("instanceID", "formId");
    }};

    public Map<String, String> parse(CommcareForm commcareForm) {
        HashMap<String, String> metaData = new HashMap<>();

        for (Map.Entry<String, String> pair : commcareForm.getMetadata().entrySet()) {
            String keyValue = StringUtils.toCamelCase(pair.getKey());
            keyValue = applyConversionMap(keyValue);
            metaData.put(keyValue, pair.getValue());
        }
        return metaData;
    }

    private String applyConversionMap(String keyValue) {
        return keyConversionMap.containsKey(keyValue) ? keyConversionMap.get(keyValue) : keyValue;
    }
}
