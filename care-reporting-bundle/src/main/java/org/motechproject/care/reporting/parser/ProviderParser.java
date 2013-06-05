package org.motechproject.care.reporting.parser;

import org.apache.commons.lang.StringUtils;
import org.motechproject.commcare.provider.sync.response.Provider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProviderParser {

    public Map<String, Object> parse(Provider provider) {
        HashMap<String, Object> parsedProviderMap = new HashMap<>();
        Map<String, Object> providerData = parseProviderInfo(provider, new HashMap<String, String>() {{
            put("id", "flwId");
        }});
        Map<String, Object> userData = parseProviderInfo(provider.getUserData(), new HashMap<String, String>() {{
            put("asset-id", "assetId");
            put("awc-code", "awcCode");
            put("imei-no", "imeiNo");
        }});
        parsedProviderMap.putAll(providerData);
        parsedProviderMap.putAll(userData);
        parsedProviderMap.put("phoneNumbers", StringUtils.join(provider.getPhoneNumbers(), ","));
        return parsedProviderMap;
    }

    private Map<String, Object> parseProviderInfo(Object providerInfo, HashMap<String, String> keyConversionMap) {
        InfoParser infoParser = new InfoParser(keyConversionMap);
        return infoParser.parse(providerInfo);
    }
}
