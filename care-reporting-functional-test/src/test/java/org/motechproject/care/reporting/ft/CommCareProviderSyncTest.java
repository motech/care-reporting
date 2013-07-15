package org.motechproject.care.reporting.ft;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.motechproject.care.reporting.ft.couch.domain.Provider;
import org.motechproject.care.reporting.ft.pages.MotechEndpoint;
import org.motechproject.care.reporting.ft.utils.PropertyFile;
import org.motechproject.care.reporting.ft.utils.ReflectionUtils;
import org.motechproject.care.reporting.ft.utils.TimedRunnerBreakCondition;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.motechproject.care.reporting.ft.utils.AssertionUtils.assertContainsAll;
import static org.motechproject.care.reporting.ft.utils.ReflectionUtils.reflectionSerialize;

public class CommCareProviderSyncTest extends BaseTestCase {
    private String groupId1;
    private String groupId2;
    private String providerId1;
    private String providerId2;
    int retries = 100;
    int intervalSleep = 1000;
    private MotechEndpoint motechEndpoint = new MotechEndpoint();
    private Map<String, String> placeholders;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(1111); // No-args constructor defaults to port 8080

    @Before
    public void setUp() {
        groupId1 = UUID.randomUUID().toString().replaceAll("-", "");
        groupId2 = UUID.randomUUID().toString().replaceAll("-", "");
        providerId1 = UUID.randomUUID().toString().replaceAll("-", "");
        providerId2 = UUID.randomUUID().toString().replaceAll("-", "");
        placeholders = new HashMap<String, String>() {{
            put("groupId1", groupId1);
            put("groupId2", groupId2);
            put("providerId1", providerId1);
            put("providerId2", providerId2);
        }};
    }

    @After
    public void tearDown() {
        reportingDatabase().deleteFLW(providerId1);
        reportingDatabase().deleteFLW(providerId2);
        reportingDatabase().deleteGroup(groupId1);
        reportingDatabase().deleteGroup(groupId2);

        mrsDatabase().providers().delete(providerId1);
        mrsDatabase().providers().delete(providerId2);
    }

    @Test
    public void groupSyncTest() {
        final String groupSyncResponse = motechEndpoint.getBody(constructRequestTemplateUrl("group_sync"), placeholders);
        stubFor(get(urlMatching("/group.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(groupSyncResponse)));

        DateTime groupSyncTime = new DateTime().withDayOfWeek(DateTimeConstants.SUNDAY).withHourOfDay(6).withMinuteOfHour(0).withSecondOfMinute(1);
        motechEndpoint.postFakeTimeRequest(groupSyncTime);

        assertReportingGroupDetails(groupId1, "group1");
        assertReportingGroupDetails(groupId2, "group2");
    }

    @Test
    public void providerSyncTest() {
        final String providerSyncResponse = motechEndpoint.getBody(constructRequestTemplateUrl("provider_sync"), placeholders);
        stubFor(get(urlMatching("/user.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(providerSyncResponse)));

        DateTime providerSyncTime = new DateTime().withDayOfWeek(DateTimeConstants.SUNDAY).withHourOfDay(6).withMinuteOfHour(30).withSecondOfMinute(0);
        motechEndpoint.postFakeTimeRequest(providerSyncTime);

        assertReportingFlwDetails(providerId1, "provider1");
        assertReportingFlwDetails(providerId2, "provider2");

        assertMrsProviderDetails(providerId1, "provider1");
        assertMrsProviderDetails(providerId2, "provider2");
    }

    private void assertMrsProviderDetails(String businessId, String expectedProperty) {
        Provider actualProvider = mrsDatabase().providers().waitAndFindByProviderId(businessId, retries, intervalSleep, breakCondition());
        PropertyFile expectedCouchValues = new PropertyFile(constructExpectedUrl("couch/" + expectedProperty), placeholders);
        PropertyFile actualCouchValues = PropertyFile.fromString(reflectionSerialize(actualProvider, "provider"));
        assertContainsAll(expectedCouchValues.properties(), actualCouchValues.properties());
    }

    private void assertReportingGroupDetails(String businessId, String expectedDetailsProperty) {
        Map<String, Object> actualGroup = reportingDatabase().flwGroup.waitAndGet(businessId, retries, intervalSleep, breakCondition());
        assertDetails(actualGroup, expectedDetailsProperty);
    }

    private void assertReportingFlwDetails(String businessId, String expectedDetailsProperty) {
        Map<String, Object> actualFlw = reportingDatabase().flw.waitAndGet(businessId, retries, intervalSleep, breakCondition());
        assertDetails(actualFlw, expectedDetailsProperty);
    }

    private void assertDetails(Map<String, Object> actualDetails, final String detailName) {
        PropertyFile expectedGroupValues = new PropertyFile(constructExpectedUrl("reporting/" + detailName), placeholders);
        assertContainsAll(expectedGroupValues.properties(), ReflectionUtils.serializeMap(actualDetails));
    }

    private TimedRunnerBreakCondition breakCondition() {
        return new TimedRunnerBreakCondition() {
            @Override
            public boolean test(Object obj) {
                return obj != null;
            }
        };
    }

    @Override
    protected String getTestIdentifier() {
        return "commCareSync";
    }
}
