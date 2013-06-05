package org.motechproject.care.reporting.parser;

import org.junit.Test;
import org.motechproject.care.reporting.utils.TestUtils;
import org.motechproject.commcare.provider.sync.response.Provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class ProviderParserTest {

    @Test
    public void shouldParseProviderWithAppropriateKeyConversions() {
        ProviderParser providerParser = new ProviderParser();

        Map<String, Object> parsedValues = providerParser.parse(provider());

        assertEquals("Sonia", parsedValues.get("firstName"));
        assertEquals("001", parsedValues.get("assetId"));
        assertEquals("somevalue1", parsedValues.get("awcCode"));
        assertEquals("somevalue2", parsedValues.get("imeiNo"));
        assertEquals("delhi", parsedValues.get("block"));
        assertEquals("9004195698,919004195698", parsedValues.get("phoneNumbers"));
    }

    private Provider provider() {
        Provider provider = new Provider();
        HashMap<String, Object> fieldMap = new HashMap<String, Object>() {{
            put("id", "5ba9a0928dde95d187544babf6c0ad24");
            put("firstName", "Sonia");
            put("phoneNumbers", new ArrayList<String>() {{
                add("9004195698");
                add("919004195698");
            }});
            put("userData", new HashMap<String, String>() {{
                put("asset-id", "001");
                put("awc-code", "somevalue1");
                put("imei-no", "somevalue2");
                put("block", "delhi");
            }});
        }};
        TestUtils.setField(provider, fieldMap);
        return provider;
    }
}
