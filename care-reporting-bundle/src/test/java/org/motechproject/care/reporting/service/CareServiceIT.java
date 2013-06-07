package org.motechproject.care.reporting.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.builder.FlwGroupBuilder;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionContains;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionEqualsWithIgnore;

public class CareServiceIT extends SpringIntegrationTest {
    @Autowired
    private Service careService;

    @Before
    @After
    public void setUp() {
        template.deleteAll(template.loadAll(FlwGroup.class));
    }

    @Test
    public void shouldSearchForExistingGroupBeforeUpdating() throws Exception {
        FlwGroup toBeUpdatedGroup = new FlwGroupBuilder().groupId("5ba9a0928dde95d187544babf6c0ad24")
                .name("afrisis team 1")
                .domain("care-bihar")
                .awcCode("001")
                .caseSharing(true)
                .reporting(true)
                .build();
        FlwGroup notToBeUpdatedGroup = flwGroupWithNameAndId("5ba9a0928dde95d187544babf6c0af36", "ashok team 1");

        template.saveOrUpdateAll(Arrays.asList(toBeUpdatedGroup, notToBeUpdatedGroup));

        FlwGroup updatedGroup = updatedGroup();

        careService.saveOrUpdateGroups(Arrays.asList(updatedGroup));

        FlwGroup loadedFlwGroup = template.load(FlwGroup.class, toBeUpdatedGroup.getId());
        FlwGroup unchangedFlwGroup = template.load(FlwGroup.class, notToBeUpdatedGroup.getId());
        assertReflectionEqualsWithIgnore(updatedGroup(), loadedFlwGroup, new String[]{"id"});
        assertEquals("ashok team 1", unchangedFlwGroup.getName());
    }

    @Test
    public void shouldSaveNewGroup() throws Exception {
        FlwGroup newGroup = new FlwGroupBuilder().groupId("5ba9a0928dde95d187544babf6c0ad24")
                .name("amir team 1")
                .domain("care-mp")
                .awcCode("007")
                .caseSharing(true)
                .reporting(true)
                .build();

        careService.saveOrUpdateGroups(Arrays.asList(newGroup));

        List<FlwGroup> flwGroupsFromDb = template.loadAll(FlwGroup.class);
        assertEquals(1, flwGroupsFromDb.size());
        assertReflectionContains(newGroup, flwGroupsFromDb, new String[]{"id"});
    }

    private FlwGroup updatedGroup() {
        return new FlwGroupBuilder().groupId("5ba9a0928dde95d187544babf6c0ad24")
                .name("danny team 1")
                .domain("care-orrisa")
                .awcCode("002")
                .caseSharing(false)
                .reporting(false)
                .build();
    }

    private FlwGroup flwGroupWithNameAndId(String groupId, String name) {
        return new FlwGroupBuilder().groupId(groupId).name(name).build();
    }
}
