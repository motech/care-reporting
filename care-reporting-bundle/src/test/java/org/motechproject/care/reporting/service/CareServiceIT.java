package org.motechproject.care.reporting.service;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.builder.FlwBuilder;
import org.motechproject.care.reporting.builder.FlwGroupBuilder;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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

        careService.saveOrUpdateAllByExternalPrimaryKey(FlwGroup.class, Arrays.asList(updatedGroup));

        FlwGroup loadedFlwGroup = template.load(FlwGroup.class, toBeUpdatedGroup.getId());
        FlwGroup unchangedFlwGroup = template.load(FlwGroup.class, notToBeUpdatedGroup.getId());
        assertReflectionEqualsWithIgnore(updatedGroup(), loadedFlwGroup, new String[]{"id", "creationTime"});
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

        careService.saveOrUpdateAllByExternalPrimaryKey(FlwGroup.class, Arrays.asList(newGroup));

        List<FlwGroup> flwGroupsFromDb = template.loadAll(FlwGroup.class);
        assertEquals(1, flwGroupsFromDb.size());
        assertReflectionContains(newGroup, flwGroupsFromDb, new String[]{"id"});
    }

    @Test
    public void shouldSearchExistingFlwsAndUpdateFlw() {
        Flw toBeUpdatedFlw = flw("5ba9a0928dde95d187544babf6c0ad24", "FirstName1", flwGroupWithNameAndId("64a9a0928dde95d187544babf6c0ad38", "oldGroupName"));
        Flw notToBeUpdateFlw = flw("5ba9a0928dde95d187544babf6c0ad25", "FirstName2", new FlwGroup());
        template.saveOrUpdateAll(Arrays.asList(toBeUpdatedFlw, notToBeUpdateFlw));

        Flw newFlw = flw("5ba9a0928dde95d187544babf6c0ad24", "FirstName3", flwGroupWithNameAndId("38a9a0928dde95d187544babf6c0ad64", "newGroupName"));
        ArrayList<Flw> flwsToUpdate = new ArrayList<>();
        flwsToUpdate.add(newFlw);

        careService.saveOrUpdateAllByExternalPrimaryKey(Flw.class, flwsToUpdate);

        Flw updatedFlw = template.load(Flw.class, toBeUpdatedFlw.getId());
        assertReflectionEqualsWithIgnore(newFlw, updatedFlw, new String[]{"id", "creationTime"});
        Flw unchangedFlw = template.load(Flw.class, notToBeUpdateFlw.getId());
        assertEquals("FirstName2", unchangedFlw.getFirstName());
    }

    @Test
    public void shouldCreateNewFlw() {
        Flw newFlw = flw("5ba9a0928dde95d187544babf6c0ad24", "FirstName1", flwGroupWithNameAndId("64a9a0928dde95d187544babf6c0ad38", "oldGroupName"));

        careService.saveOrUpdateAllByExternalPrimaryKey(Flw.class, Arrays.asList(newFlw));

        List<Flw> flwsFromDb = template.loadAll(Flw.class);
        assertEquals(1, flwsFromDb.size());
        assertReflectionContains(newFlw, flwsFromDb, new String[]{"id"});
    }

    @Test
    public void shouldInsertAnEntityByExternalPrimaryKeyWhenDoesNotExists() throws Exception {
        Flw newFlw = flw("5ba9a0928dde95d187544babf6c0ad24", "FirstName1", flwGroupWithNameAndId("64a9a0928dde95d187544babf6c0ad38", "oldGroupName"));
        careService.saveOrUpdateByExternalPrimaryKey(newFlw);

        List<Flw> flws = template.loadAll(Flw.class);

        assertEquals(1, flws.size());
        assertReflectionContains(newFlw, flws);
    }

    @Test
    public void shouldUpdateEntityByExternalPrimaryKeyWhenExists() {
        String caseId = "case Id";
        ChildCase existingChild = new ChildCase();
        existingChild.setCaseId(caseId);
        existingChild.setName("old Child name");
        template.save(existingChild);

        ChildCase newChild = new ChildCase();
        newChild.setCaseId(caseId);
        String newChildName = "new child name";
        newChild.setName(newChildName);

        careService.saveOrUpdateByExternalPrimaryKey(newChild);

        List<ChildCase> childCases = template.loadAll(ChildCase.class);
        Assert.assertEquals(1, childCases.size());
        Assert.assertEquals(newChildName, childCases.get(0).getName());
    }

    private Flw flw(String flwId, String firstName, final FlwGroup flwGroup) {
        return new FlwBuilder()
                .flwId(flwId)
                .firstName(firstName)
                .flwGroups(new HashSet<FlwGroup>() {{
                    add(flwGroup);
                }})
                .build();
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
