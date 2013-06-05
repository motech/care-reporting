package org.motechproject.care.reporting.parser;

import org.junit.Test;
import org.motechproject.commcare.provider.sync.response.Provider;
import org.unitils.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class ProviderParserTest {

    @Test
    public void shouldParseProviderWithAppropriateKeyConversions() {
        ProviderParser providerParser = new ProviderParser();

        Map<String, String> parsedValues = providerParser.parse(provider());

        assertEquals("Sonia", parsedValues.get("firstName"));
        assertEquals("001", parsedValues.get("assetId"));
        assertEquals("somevalue1", parsedValues.get("awcCode"));
        assertEquals("somevalue2", parsedValues.get("imeiNo"));
        assertEquals("delhi", parsedValues.get("block"));
        assertEquals("9004195698,919004195698", parsedValues.get("phoneNumbers"));
    }

    private Provider provider() {
        Provider provider = new Provider();
        setField(provider, "id", "5ba9a0928dde95d187544babf6c0ad24");
        setField(provider, "firstName", "Sonia");
        setField(provider, "phoneNumbers", new ArrayList<String>() {{
            add("9004195698");
            add("919004195698");
        }});
        setField(provider, "userData", new HashMap<String, String>() {{
            put("asset-id", "001");
            put("awc-code", "somevalue1");
            put("imei-no", "somevalue2");
            put("block", "delhi");
        }});
        return provider;
    }

    private void setField(Provider provider, String fieldName, Object value) {
        try {
            Field field = Provider.class.getDeclaredField(fieldName);
            ReflectionUtils.setFieldValue(provider, field, value);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
