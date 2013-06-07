package org.motechproject.care.reporting.parser;

import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.builder.ProviderBuilder;
import org.motechproject.commcare.provider.sync.response.Provider;

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
        Map<String, Object> parsedValues = providerParser.parse(builder().build());

        assertEquals("Dr.Pramod", parsedValues.get("firstName"));
        assertEquals("P18", parsedValues.get("assetId"));
        assertEquals("001", parsedValues.get("awcCode"));
        assertEquals("Delhi", parsedValues.get("block"));
        assertEquals("8294168471", parsedValues.get("defaultPhoneNumber"));
        assertEquals("8294168471", parsedValues.get("phoneNumber1"));
        assertEquals("918294168471", parsedValues.get("phoneNumber2"));
        assertEquals(ProviderBuilder.DEFAULT_DOB, parsedValues.get("dob"));
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

    @Test
    public void shouldParseDobIfItIsNull(){
        Provider provider = builder().setDob(null).build();
        Map<String, Object> output = providerParser.parse(provider);
        assertNull(output.get("dob"));
    }

    @Test
    public void shouldParseDobIfIsBlank(){
        Provider provider = builder().setDob("").build();
        Map<String, Object> output = providerParser.parse(provider);
        assertNull(output.get("dob"));
    }

    private Provider customizedProvider(final String defaultPhoneNumber, final String... phoneNumbers) {
        return builder().setDefaultPhoneNumber(defaultPhoneNumber).setPhoneNumbers(phoneNumbers).build();
    }

    private ProviderBuilder builder() {
        return new ProviderBuilder("5ba9a0928dde95d187544babf6c0ad24");
    }
}
