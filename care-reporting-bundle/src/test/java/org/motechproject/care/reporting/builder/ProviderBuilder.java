package org.motechproject.care.reporting.builder;

import org.joda.time.LocalDate;
import org.motechproject.care.reporting.utils.TestUtils;
import org.motechproject.commcare.provider.sync.response.Provider;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ProviderBuilder {
    public static final Date DEFAULT_DOB = new LocalDate(1980, 1, 1).toDate();
    private Provider provider;


    public ProviderBuilder(String providerId) {
        this.provider = new Provider();
        setField("id", providerId);
    }

    public ProviderBuilder setDefaults(){
        setField("defaultPhoneNumber", "8294168471");
        setField("email", "a@b.com");
        setField("firstName", "Dr.Pramod");
        setField("groups", Arrays.asList("89fda0284e008d2e0c980fb13fb63886", "89fda0284e008d2e0c980fb13fb66a7b", "89fda0284e008d2e0c980fb13fb72931"));
        setField("lastName", "Kumar Gautam");
        setField("phoneNumbers", Arrays.asList("8294168471", "918294168471"));
        setField("resourceURI", "");
        setField("userData", new HashMap<String, String>() {{
            put("awc-code", "001");
            put("asset-id", "P18");
            put("block", "Delhi");
            put("state", "BIHAR");
            put("district", "");
            put("location-code", "");
            put("panchayat", "Kapra");
            put("role", "MOIC");
            put("subcentre", "");
            put("user_type", "");
            put("village", "Kopargoan");
            put("ward", "Thiruppalai");
            put("dob", "1980-01-01");
            put("location-code", "1092830192");
        }});
        setField("username", "8294168471@care-bihar.commcarehq.org");
        return this;
    }

    public Provider build() {
        return provider;
    }

    public ProviderBuilder setDefaultPhoneNumber(String defaultPhoneNumber) {
        TestUtils.setField(provider, "defaultPhoneNumber", defaultPhoneNumber);
        return this;
    }

    public ProviderBuilder setField(String fieldName, Object value) {
        TestUtils.setField(provider, fieldName, value);
        return this;
    }

    public ProviderBuilder setPhoneNumbers(String phoneNumber1, String phoneNumber2) {
        final List<String> phoneNumbers = phoneNumber2 == null ? Arrays.asList(phoneNumber1) : Arrays.asList(phoneNumber1, phoneNumber2);
        setField("phoneNumbers", phoneNumbers);
        return this;
    }

    public ProviderBuilder setPhoneNumbers(String... phoneNumbers) {
        setField("phoneNumbers", Arrays.asList(phoneNumbers));
        return this;
    }

    public ProviderBuilder setDob(String dob) {
        provider.getUserData().put("dob", dob);
        return this;
    }

    public ProviderBuilder setState(String state) {
        provider.getUserData().put("state", state);
        return this;
    }

    public ProviderBuilder removeState() {
        provider.getUserData().remove("state");
        return this;
    }
}
