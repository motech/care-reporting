package org.motechproject.care.reporting.parser;

import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.utils.TestUtils;
import org.motechproject.commcare.provider.sync.response.Provider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.*;

public class ProviderParserTest {

    private ProviderParser providerParser;

    @Before
    public void setUp() throws Exception {
        providerParser = new ProviderParser();
    }

    @Test
    public void shouldParseProviderWithAppropriateKeyConversions() {

        Map<String, Object> parsedValues = providerParser.parse(provider());

        assertEquals("Sonia", parsedValues.get("firstName"));
        assertEquals("001", parsedValues.get("assetId"));
        assertEquals("somevalue1", parsedValues.get("awcCode"));
        assertEquals("delhi", parsedValues.get("block"));
        assertEquals("919004195698", parsedValues.get("defaultPhoneNumber"));
        assertEquals("9004195698", parsedValues.get("phoneNumber1"));
        assertEquals("919004195698", parsedValues.get("phoneNumber2"));
    }

    @Test
    public void shouldParseIfPhoneNumbersNotProvided() throws Exception {
        Provider providerWithNoPhoneNumber = customizedProvider(null);
        Map<String, Object> output = providerParser.parse(providerWithNoPhoneNumber);

        assertTrue(output.containsKey("defaultPhoneNumber"));
        assertTrue(output.containsKey("phoneNumber1"));
        assertTrue(output.containsKey("phoneNumber2"));
        assertNull(output.get("defaultPhoneNumber"));
        assertNull(output.get("phoneNumber1"));
        assertNull(output.get("phoneNumber2"));
    }

    private Provider provider() {
        return customizedProvider("919004195698", "9004195698", "919004195698");
    }

    private Provider customizedProvider(final String defaultPhoneNumber, final String... phoneNumbers) {
        Provider provider = new Provider();
        HashMap<String, Object> fieldMap = new HashMap<String, Object>() {{
            put("id", "5ba9a0928dde95d187544babf6c0ad24");
            put("firstName", "Sonia");
            put("defaultPhoneNumber", defaultPhoneNumber);
            put("phoneNumbers", Arrays.asList(phoneNumbers));
            put("userData", new HashMap<String, String>() {{
                put("asset-id", "001");
                put("awc-code", "somevalue1");
                put("block", "delhi");
            }});
        }};
        TestUtils.setFields(provider, fieldMap);
        return provider;
    }
}
