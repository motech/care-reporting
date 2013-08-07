package org.motechproject.care.reporting.parser;

import org.motechproject.commcare.provider.sync.response.Provider;

import java.util.HashMap;
import java.util.Map;

import static org.motechproject.care.reporting.utils.ListUtils.safeGet;

public class ProviderParser extends BaseInfoParser {

    private final String DEFAULT_STATE = "BIHAR";
    public ProviderParser(InfoParser infoParser) {
        super(infoParser);
    }

    public Map<String, Object> parse(Provider provider) {
        HashMap<String, Object> parsedProviderMap = new HashMap<>();
        Map<String, Object> providerData = parseProviderInfo(provider, new HashMap<String, String>() {{
            put("id", "flwId");
        }});
        Map<String, Object> userData = parseProviderInfo(provider.getUserData(), new HashMap<String, String>() {{
            put("asset-id", "assetId");
            put("awc-code", "awcCode");
        }});
        parsedProviderMap.putAll(providerData);
        parsedProviderMap.putAll(userData);

        parsedProviderMap.put("phoneNumber1", safeGet(provider.getPhoneNumbers(), 0));
        parsedProviderMap.put("phoneNumber2", safeGet(provider.getPhoneNumbers(), 1));
        parsedProviderMap.put("dob", provider.getUserData().get("dob"));

        if(!parsedProviderMap.containsKey("state"))
            parsedProviderMap.put("state", DEFAULT_STATE);

        return parsedProviderMap;
    }

    private Map<String, Object> parseProviderInfo(Object providerInfo, HashMap<String, String> keyConversionMap) {
        infoParser.setKeyConversionMap(keyConversionMap);
        return infoParser.parse(providerInfo);
    }
}
