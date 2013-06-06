package org.motechproject.care.reporting.listener;

import org.junit.Test;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.domain.measure.NewForm;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.motechproject.care.reporting.utils.TestUtils;
import org.motechproject.commcare.provider.sync.constants.EventConstants;
import org.motechproject.commcare.provider.sync.response.Group;
import org.motechproject.commcare.provider.sync.response.Provider;
import org.motechproject.event.MotechEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionContains;

public class CommcareProviderSyncIT extends SpringIntegrationTest {
    @Autowired
    CommcareProviderSyncListener commcareProviderSyncListener;

    @Test
    public void shouldHandleGroupSyncAndSaveFlwGroups() {
        ArrayList<Group> groups = new ArrayList<Group>() {{
            add(group("3c5a80e4db53049dfc110c368a0d05d4"));
            add(group("3c5a80e4db53049dfc110c368a0d1de1"));
        }};
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(EventConstants.GROUP_DETAILS, groups);
        MotechEvent event = new MotechEvent(EventConstants.GROUP_DETAILS, parameters);

        commcareProviderSyncListener.handleGroupSyncEvent(event);

        List<FlwGroup> flwGroupsFromDb = template.loadAll(FlwGroup.class);
        assertEquals(2, flwGroupsFromDb.size());
        assertReflectionContains(flwGroup("3c5a80e4db53049dfc110c368a0d05d4"), flwGroupsFromDb, new String[]{"id"});
        assertReflectionContains(flwGroup("3c5a80e4db53049dfc110c368a0d1de1"), flwGroupsFromDb, new String[]{"id"});
        List<Flw> flwsFromDb = template.loadAll(Flw.class);
        assertTrue(flwsFromDb.isEmpty());
    }

    @Test
    public void shouldHandleProviderSyncAndSaveFlwAndAssociatedGroups() {
        ArrayList<Provider> providers = new ArrayList<Provider>() {{
            add(provider("b0645df855266f29849eb2515b5ed57c"));
            add(provider("b0645df855266f29849eb2515b5ed374"));
        }};
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(EventConstants.PROVIDER_DETAILS, providers);
        MotechEvent event = new MotechEvent(EventConstants.PROVIDER_DETAILS_EVENT, parameters);
        template.save(flwGroup("89fda0284e008d2e0c980fb13fb72931"));

        commcareProviderSyncListener.handleProviderSyncEvent(event);

        List<Flw> flwsFromDb = template.loadAll(Flw.class);
        assertEquals(2, flwsFromDb.size());
        assertReflectionContains(flw("b0645df855266f29849eb2515b5ed57c"), flwsFromDb, new String[]{"id", "flwGroups"});
        assertReflectionContains(flw("b0645df855266f29849eb2515b5ed374"), flwsFromDb, new String[]{"id", "flwGroups"});

        List<FlwGroup> flwGroupsFromDb = template.loadAll(FlwGroup.class);
        assertEquals(3, flwGroupsFromDb.size());

        assertReflectionContains(flwGroup("89fda0284e008d2e0c980fb13fb72931"), flwGroupsFromDb, new String[]{"id"});
        assertReflectionContains(blankFlwGroup("89fda0284e008d2e0c980fb13fb63886"), flwGroupsFromDb, new String[]{"id"});
        assertReflectionContains(blankFlwGroup("89fda0284e008d2e0c980fb13fb66a7b"), flwGroupsFromDb, new String[]{"id"});
    }

    private FlwGroup blankFlwGroup(String groupId) {
        FlwGroup flwGroup = new FlwGroup();
        TestUtils.setField(flwGroup, "groupId", groupId);
        return flwGroup;
    }

    private Flw flw(String providerId) {
        return new Flw(providerId, "8294168471", "a@b.com", "Dr.Pramod", "Kumar Gautam", "8294168471,918294168471", "P18", "001", "351971057712199", "MOIC", "", "", "8294168471@care-bihar.commcarehq.org", null, null, "", "Delhi", "Kapra", "Kopargoan", null);
    }

    private FlwGroup flwGroup(String groupId) {
        return new FlwGroup(0, groupId, true, "care-bihar", "001", "danny team 1", true, new HashSet<Flw>(), new HashSet<ChildCase>(), new HashSet<MotherCase>(), new HashSet<NewForm>());
    }

    private Group group(final String groupId) {
        Group group = new Group();
        TestUtils.setFields(group, new HashMap<String, Object>() {{
            put("caseSharing", true);
            put("domain", "care-bihar");
            put("id", groupId);
            put("path", new ArrayList<>());
            put("name", "danny team 1");
            put("reporting", true);
            put("resourceUri", "");
            put("metaData", new HashMap<String, String>() {{
                put("awc-code", "001");
            }});
            put("users", Arrays.asList("67bffa913b38e7901851d863eded0809", "67bffa913b38e7901851d863edecfb4a"));
        }});
        return group;
    }

    private Provider provider(final String providerId) {
        final Provider provider = new Provider();
        TestUtils.setFields(provider, new HashMap<String, Object>() {{
            put("defaultPhoneNumber", "8294168471");
            put("email", "a@b.com");
            put("firstName", "Dr.Pramod");
            put("groups", Arrays.asList("89fda0284e008d2e0c980fb13fb63886", "89fda0284e008d2e0c980fb13fb66a7b", "89fda0284e008d2e0c980fb13fb72931"));
            put("lastName", "Kumar Gautam");
            put("id", providerId);
            put("phoneNumbers", Arrays.asList("8294168471", "918294168471"));
            put("resourceURI", "");
            put("userData", new HashMap<String, String>() {{
                put("awc-code", "001");
                put("asset-id", "P18");
                put("block", "Delhi");
                put("district", "");
                put("imei-no", "351971057712199");
                put("location-code", "");
                put("panchayat", "Kapra");
                put("role", "MOIC");
                put("subcentre", "");
                put("user_type", "");
                put("village", "Kopargoan");
            }});
            put("username", "8294168471@care-bihar.commcarehq.org");
        }});
        return provider;
    }
}
