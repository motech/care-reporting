package org.motechproject.care.reporting.listener;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.motechproject.care.reporting.builder.GroupBuilder;
import org.motechproject.care.reporting.builder.ProviderBuilder;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.LocationDimension;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.motechproject.care.reporting.utils.TestUtils;
import org.motechproject.commcare.provider.sync.constants.EventConstants;
import org.motechproject.commcare.provider.sync.response.Group;
import org.motechproject.commcare.provider.sync.response.Provider;
import org.motechproject.event.MotechEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.motechproject.care.reporting.builder.ProviderBuilder.DEFAULT_DOB;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionContains;

public class CommcareProviderSyncIT extends SpringIntegrationTest {
    @Autowired
    private CommcareProviderSyncListener commcareProviderSyncListener;

    @Test
    public void shouldHandleGroupSyncAndSaveFlwGroups() {
        List<Group> groups = new ArrayList<Group>() {{
            add(group("3c5a80e4db53049dfc110c368a0d05d4"));
            add(group("3c5a80e4db53049dfc110c368a0d1de1"));
        }};

        commcareProviderSyncListener.handleGroupSyncEvent(motechGroupDetailsEvent(groups));

        List<FlwGroup> flwGroupsFromDb = template.loadAll(FlwGroup.class);
        assertEquals(2, flwGroupsFromDb.size());
        assertReflectionContains(flwGroup("3c5a80e4db53049dfc110c368a0d05d4"), flwGroupsFromDb, new String[]{"id", "creationTime", "lastModifiedTime"});
        assertReflectionContains(flwGroup("3c5a80e4db53049dfc110c368a0d1de1"), flwGroupsFromDb, new String[]{"id", "creationTime", "lastModifiedTime"});
        List<Flw> flwsFromDb = template.loadAll(Flw.class);
        assertTrue(flwsFromDb.isEmpty());
    }

    @Test
    public void shouldHandleProviderSyncAndSaveFlwAndAssociatedGroups() {
        List<Provider> providers = new ArrayList<Provider>() {{
            add(provider("b0645df855266f29849eb2515b5ed57c", "8294168471", "8294168471", "918294168471"));
            add(provider("b0645df855266f29849eb2515b5ed374", "8294168471", "8294168471", null));
            add(provider("b0645df855266f29849eb2515b5ed176", "8294168471", "8294168471", "8294168472"));
        }};
        template.save(flwGroup("89fda0284e008d2e0c980fb13fb72931"));

        commcareProviderSyncListener.handleProviderSyncEvent(motechProviderEvent(providers));

        final DetachedCriteria criteria = DetachedCriteria.forClass(LocationDimension.class);
        criteria.add(Restrictions.eq("state", "UNKNOWN")).add(Restrictions.eq("district", "UNKNOWN")).add(Restrictions.eq("block", "UNKNOWN"));
        LocationDimension location = (LocationDimension) template.findByCriteria(criteria).get(0);

        List<Flw> flwsFromDb = template.loadAll(Flw.class);
        assertEquals(3, flwsFromDb.size());

        assertReflectionContains(flw("b0645df855266f29849eb2515b5ed57c", "8294168471", "8294168471", "918294168471", location, "1092830192"), flwsFromDb, new String[]{"id", "flwGroups", "creationTime", "lastModifiedTime"});
        assertReflectionContains(flw("b0645df855266f29849eb2515b5ed374", "8294168471", "8294168471", null, location, "1092830192"), flwsFromDb, new String[]{"id", "flwGroups", "creationTime", "lastModifiedTime"});
        assertReflectionContains(flw("b0645df855266f29849eb2515b5ed176", "8294168471", "8294168471", "8294168472", location, "1092830192"), flwsFromDb, new String[]{"id", "flwGroups", "creationTime", "lastModifiedTime"});

        List<FlwGroup> flwGroupsFromDb = template.loadAll(FlwGroup.class);
        assertEquals(3, flwGroupsFromDb.size());

        assertReflectionContains(flwGroup("89fda0284e008d2e0c980fb13fb72931"), flwGroupsFromDb, new String[]{"id", "creationTime", "lastModifiedTime"});
        assertReflectionContains(blankFlwGroup("89fda0284e008d2e0c980fb13fb63886"), flwGroupsFromDb, new String[]{"id", "creationTime", "lastModifiedTime"});
        assertReflectionContains(blankFlwGroup("89fda0284e008d2e0c980fb13fb66a7b"), flwGroupsFromDb, new String[]{"id", "creationTime", "lastModifiedTime"});
    }

    private FlwGroup blankFlwGroup(String groupId) {
        FlwGroup flwGroup = new FlwGroup();
        TestUtils.setField(flwGroup, "groupId", groupId);
        return flwGroup;
    }

    private Flw flw(String providerId, String defaultPhoneNumber, String phoneNumber1, String phoneNumber2, LocationDimension locationDimension, String locationCode) {
        return new Flw(providerId, defaultPhoneNumber, "a@b.com", "Dr.Pramod", "Kumar Gautam", phoneNumber1, phoneNumber2,
                "P18", "001", "MOIC", "", "", "8294168471@care-bihar.commcarehq.org", null, null,
                "BIHAR", "", "Delhi", "Kapra", "Kopargoan", "Thiruppalai", null, null, null, DEFAULT_DOB, null, null, locationDimension,locationCode);
    }

    private FlwGroup flwGroup(String groupId) {
        return new FlwGroup(0, groupId, true, "care-bihar", "001", "danny team 1", true, null, null, new HashSet<Flw>());
    }

    private Group group(final String groupId) {
        return new GroupBuilder(groupId).build();
    }

    private MotechEvent motechProviderEvent(final List<Provider> providers) {
        return new MotechEvent(EventConstants.PROVIDER_DETAILS_EVENT, new HashMap<String, Object>() {{
            put(EventConstants.DETAILS_LIST, providers);
        }});
    }

    private MotechEvent motechGroupDetailsEvent(List<Group> groups) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(EventConstants.DETAILS_LIST, groups);
        return new MotechEvent(EventConstants.DETAILS_LIST, parameters);
    }

    private Provider provider(final String providerId, final String defaultPhoneNumber, final String phoneNumber1, final String phoneNumber2) {
        return new ProviderBuilder(providerId)
                .setDefaults()
                .setDefaultPhoneNumber(defaultPhoneNumber)
                .setPhoneNumbers(phoneNumber1, phoneNumber2)
                .build();
    }
}
