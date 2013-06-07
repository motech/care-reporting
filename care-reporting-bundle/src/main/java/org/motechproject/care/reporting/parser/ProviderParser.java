package org.motechproject.care.reporting.parser;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.motechproject.commcare.provider.sync.response.Provider;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.motechproject.care.reporting.utils.ListUtils.safeGet;

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
        }});
        parsedProviderMap.putAll(providerData);
        parsedProviderMap.putAll(userData);

        parsedProviderMap.put("phoneNumber1", safeGet(provider.getPhoneNumbers(), 0));
        parsedProviderMap.put("phoneNumber2", safeGet(provider.getPhoneNumbers(), 1));
        parsedProviderMap.put("dob", transformDob(provider.getUserData().get("dob")));
        return parsedProviderMap;
    }

    private Date transformDob(String dob) {
        return StringUtils.isEmpty(dob) ? null : DateTime.parse(dob).toDate();
    }

    private Map<String, Object> parseProviderInfo(Object providerInfo, HashMap<String, String> keyConversionMap) {
        InfoParser infoParser = new InfoParser(keyConversionMap);
        return infoParser.parse(providerInfo);
    }
}
