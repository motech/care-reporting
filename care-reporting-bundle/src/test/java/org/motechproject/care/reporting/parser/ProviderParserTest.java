package org.motechproject.care.reporting.parser;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.builder.ProviderBuilder;
import org.motechproject.commcare.provider.sync.response.Provider;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProviderParserTest {

    private ProviderParser providerParser;

    @Mock
    private InfoParser infoParser;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        providerParser = new ProviderParser(infoParser);
    }

    @Test
    public void shouldParseProviderWithKeyConversionMap() {

        final HashMap<String, String> userDataMap = new HashMap<String, String>() {{
            put("awc-code", "001");
        }};

        final Provider provider = new ProviderBuilder("5ba9a0928dde95d187544babf6c0ad24").setField("userData", userDataMap).build();

        final Object userData = provider.getUserData();

        final HashMap<String, String> providerKeyConversionMap = new HashMap<String, String>() {{
            put("id", "flwId");
        }};
        final HashMap<String, String> userDataKeyConversionMap = new HashMap<String, String>() {{
            put("asset-id", "assetId");
            put("awc-code", "awcCode");
            put("location-code", "locationCode");
        }};

        final HashMap<String, Object> providerParsedMap = new HashMap<String, Object>() {{
            put("name", "myprovider");
        }};

        final HashMap<String, Object> userDataParsedMap = new HashMap<String, Object>() {{
            put("userName", "myprovider");
            put("dob", null);
            put("phoneNumber1", null);
            put("phoneNumber2", null);
            put("state", "BIHAR");
        }};

        when(infoParser.parse(provider)).thenReturn(providerParsedMap);
        when(infoParser.parse(userData)).thenReturn(userDataParsedMap);

        Map<String, Object> actualParsedMap = providerParser.parse(provider);

        verify(infoParser).setKeyConversionMap(providerKeyConversionMap);
        verify(infoParser).parse(provider);
        verify(infoParser).setKeyConversionMap(userDataKeyConversionMap);
        verify(infoParser).parse(userData);

        Map<String, Object> expectedParsedMap = new HashMap<>();
        expectedParsedMap.putAll(providerParsedMap);
        expectedParsedMap.putAll(userDataParsedMap);

        ReflectionAssert.assertReflectionEquals(expectedParsedMap, actualParsedMap);
    }


    @Test
    public void shouldParseIfPhoneNumbersNotProvided() throws Exception {
        Provider providerWithNoPhoneNumber = customizedProvider(null);
        Map<String, Object> output = providerParser.parse(providerWithNoPhoneNumber);

        assertTrue(output.containsKey("phoneNumber1"));
        assertTrue(output.containsKey("phoneNumber2"));
        assertNull(output.get("defaultPhoneNumber"));
        assertNull(output.get("phoneNumber1"));
        assertNull(output.get("phoneNumber2"));
    }

    @Test
    public void shouldParseDob(){
        Provider provider = builder().setDob("21/12/2013").build();
        Map<String, Object> output = providerParser.parse(provider);
        assertEquals(output.get("dob").toString(), "21/12/2013");
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
        assertEquals("", output.get("dob"));
    }

    @Test
    public void shouldParseState(){

        Provider provider = builder().build();

        final Object userData = provider.getUserData();
        final HashMap<String, Object> userDataParsedMap = new HashMap<String, Object>() {{
            put("state", "ORISSA");
        }};

        when(infoParser.parse(userData)).thenReturn(userDataParsedMap);

        Map<String, Object> actualParsedMap = providerParser.parse(provider);
        verify(infoParser).parse(userData);
        assertEquals("ORISSA", actualParsedMap.get("state"));
    }

    @Test
    public void shouldParseStateIfItIsNull(){
        Provider provider = builder().build();

        final Object userData = provider.getUserData();
        final HashMap<String, Object> userDataParsedMap = new HashMap<String, Object>() {{
            put("state", null);
        }};

        when(infoParser.parse(userData)).thenReturn(userDataParsedMap);

        Map<String, Object> actualParsedMap = providerParser.parse(provider);
        verify(infoParser).parse(userData);
        assertNull(actualParsedMap.get("state"));
    }

    @Test
    public void shouldParseStateIfItIsBlank(){
        Provider provider = builder().build();

        final Object userData = provider.getUserData();
        final HashMap<String, Object> userDataParsedMap = new HashMap<String, Object>() {{
            put("state", StringUtils.EMPTY);
        }};

        when(infoParser.parse(userData)).thenReturn(userDataParsedMap);

        Map<String, Object> actualParsedMap = providerParser.parse(provider);
        verify(infoParser).parse(userData);
        assertEquals(StringUtils.EMPTY, actualParsedMap.get("state"));
    }

    @Test
    public void shouldParseDefaultStateIfStateDoesnotExistInProvider(){
        Provider provider = builder().build();

        final Object userData = provider.getUserData();
        final HashMap<String, Object> userDataParsedMap = new HashMap<>();

        when(infoParser.parse(userData)).thenReturn(userDataParsedMap);

        Map<String, Object> actualParsedMap = providerParser.parse(provider);
        verify(infoParser).parse(userData);
        assertEquals("BIHAR", actualParsedMap.get("state"));
    }

    private Provider customizedProvider(final String defaultPhoneNumber, final String... phoneNumbers) {
        return builder().setDefaultPhoneNumber(defaultPhoneNumber).setPhoneNumbers(phoneNumbers).build();
    }

    private ProviderBuilder builder() {
        return new ProviderBuilder("5ba9a0928dde95d187544babf6c0ad24").setDefaults();
    }
}
