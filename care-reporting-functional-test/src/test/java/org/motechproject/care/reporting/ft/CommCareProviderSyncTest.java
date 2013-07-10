package org.motechproject.care.reporting.ft;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.*;
import org.motechproject.care.reporting.ft.pages.MotechEndpoint;
import org.motechproject.care.reporting.ft.utils.PropertyFile;
import org.motechproject.care.reporting.ft.utils.ReflectionUtils;
import org.motechproject.care.reporting.ft.utils.TemplateUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.motechproject.care.reporting.ft.utils.AssertionUtils.assertContainsAll;

public class CommCareProviderSyncTest extends BaseTestCase {
    private String groupId1, groupId2, groupId3, providerId1, providerId2, providerId3;
    private MotechEndpoint motechEndpoint = new MotechEndpoint();

    private Map<String, String> placeholders = new HashMap<String, String>() {{
        put("groupId1", groupId1);
        put("groupId2", groupId2);
        put("groupId3", groupId3);
        put("providerId1", providerId1);
        put("providerId2", providerId2);
        put("providerId3", providerId3);
    }};

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(1111); // No-args constructor defaults to port 8080

    @Before
    public void setUp() {
        groupId1 = UUID.randomUUID().toString().replaceAll("-", "");
        groupId2 = UUID.randomUUID().toString().replaceAll("-", "");
        groupId3 = UUID.randomUUID().toString().replaceAll("-", "");
        providerId1 = UUID.randomUUID().toString().replaceAll("-", "");
        providerId2 = UUID.randomUUID().toString().replaceAll("-", "");
        providerId3 = UUID.randomUUID().toString().replaceAll("-", "");
    }

    @After
    public void tearDown() {
        reportingDatabase().deleteFLW(providerId1);
        reportingDatabase().deleteFLW(providerId2);
        reportingDatabase().deleteFLW(providerId3);
        reportingDatabase().deleteGroup(groupId1);
        reportingDatabase().deleteGroup(groupId2);
        reportingDatabase().deleteGroup(groupId3);
    }

    @Test
    @Ignore
    public void groupSyncTest() {
        final String group_sync = TemplateUtils.apply(constructRequestTemplateUrl("group_sync"), placeholders);

        stubFor(get(urlEqualTo("/group"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/json")
                        .withBody(group_sync)));

        DateTime groupSyncTime = new DateTime().withDayOfWeek(DateTimeConstants.SUNDAY).withHourOfDay(6).withMinuteOfHour(0).withSecondOfMinute(1);
        motechEndpoint.postFakeTimeRequest(groupSyncTime);

        Map<String, Object> actualGroup1 = reportingDatabase().flwGroup.waitAndGet(groupId1);
        assertDetails(actualGroup1, "group1");

        Map<String, Object> actualGroup2 = reportingDatabase().flwGroup.waitAndGet(groupId2);
        assertDetails(actualGroup2, "group2");

        Map<String, Object> actualGroup3 = reportingDatabase().flwGroup.waitAndGet(groupId3);
        assertDetails(actualGroup3, "group3");
    }

    @Test
    @Ignore
    public void providerSyncTest() {
        final String provider_sync = TemplateUtils.apply(constructRequestTemplateUrl("provider_sync"), placeholders);

        stubFor(get(urlEqualTo("/user"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/json")
                        .withBody(provider_sync)));

        DateTime providerSyncTime = new DateTime().withDayOfWeek(DateTimeConstants.SUNDAY).withHourOfDay(6).withMinuteOfHour(30).withSecondOfMinute(0);
        motechEndpoint.postFakeTimeRequest(providerSyncTime);

        Map<String, Object> actualProvider1 = reportingDatabase().flw.waitAndGet(providerId1);
        assertDetails(actualProvider1, "provider1");

        Map<String, Object> actualProvider2 = reportingDatabase().flw.waitAndGet(providerId2);
        assertDetails(actualProvider2, "provider2");

        Map<String, Object> actualProvider3 = reportingDatabase().flw.waitAndGet(providerId3);
        assertDetails(actualProvider3, "provider3");
    }

    private void assertDetails(Map<String, Object> actualDetails, final String detailName) {
        PropertyFile expectedGroupValues = new PropertyFile(constructExpectedUrl("reporting/" + detailName), placeholders);
        assertContainsAll(expectedGroupValues.properties(), ReflectionUtils.serializeMap(actualDetails));
    }

    @Override
    protected String getTestIdentifier() {
        return "commCareSync";
    }
}
