package org.motechproject.care.reporting.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.motechproject.care.reporting.builder.FlwGroupBuilder;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.measure.NewForm;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.*;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionContains;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionDoesNotContains;

public class DbRepositoryIT extends SpringIntegrationTest {

    @Autowired
    private Repository repository;

    @Before
    @After
    public void setUp() {
        template.deleteAll(template.loadAll(Flw.class));
        template.deleteAll(template.loadAll(FlwGroup.class));
    }

    @Test
    @Ignore
    public void shouldSaveNewForm() {
        NewForm form = new NewForm();
        form.setCaseName("mother");
        form.setInstanceId("abcd");

        int savedFormId = repository.save(form);

        NewForm otherForm = new NewForm();
        otherForm.setCaseName("other mother");
        otherForm.setInstanceId("abcd");
        Integer otherFormId = repository.save(otherForm);

        System.out.println(savedFormId);
        System.out.println(otherFormId);

        NewForm savedForm = repository.get(NewForm.class, savedFormId);
        assertEquals("mother", savedForm.getCaseName());
        assertEquals("abcd", savedForm.getInstanceId());
        assertEquals(savedFormId, savedForm.getId());
    }

    @Test
    public void shouldGetByMatchingCriteria() {

        NewForm form = new NewForm();
        form.setCaseName("mother");
        form.setInstanceId("abcd");

        repository.save(form);

        NewForm newForm = repository.get(NewForm.class, "caseName", "mother");

        assertEquals("abcd", newForm.getInstanceId());
        assertEquals("mother", newForm.getCaseName());
    }

    @Test
    public void shouldGetByNonMatchingCriteria(){

        NewForm newForm = repository.get(NewForm.class, "caseName", "father");

        assertNull(newForm);
    }

    @Test
    public void shouldPerformCascadeSaveOnFlw(){
        Flw flw = new Flw();
        HashSet<FlwGroup> flwGroups = new HashSet<>();
        flwGroups.add(new FlwGroup());
        flwGroups.add(new FlwGroup());
        flw.setFlwGroups(flwGroups);

        template.save(flw);

        List<Flw> savedFlws = template.loadAll(Flw.class);
        List<FlwGroup> savedFlwGroups = template.loadAll(FlwGroup.class);
        assertEquals(1, savedFlws.size());
        assertEquals(2, savedFlwGroups.size());
        assertEquals(flw, savedFlws.get(0));
        assertEquals(flwGroups, savedFlws.get(0).getFlwGroups());
    }

    @Test
    public void shouldSaveOrUpdateAll(){
        final FlwGroup existingFlwGroup = new FlwGroup();
        existingFlwGroup.setName("group1");
        final FlwGroup newFlwGroup = new FlwGroup();
        newFlwGroup.setName("group2");
        List<FlwGroup> flwGroups = new ArrayList<FlwGroup>() {{
            add(existingFlwGroup);
            add(newFlwGroup);
        }};
        template.save(existingFlwGroup);
        existingFlwGroup.setName("changedGroupName");

        repository.saveOrUpdateAll(flwGroups);

        List<FlwGroup> flwGroupsFromDb = template.loadAll(FlwGroup.class);
        assertEquals(2, flwGroupsFromDb.size());
        assertTrue(flwGroupsFromDb.contains(existingFlwGroup));
        assertTrue(flwGroupsFromDb.contains(newFlwGroup));
    }

    @Test
    public void shouldFindAllGroupsByGroupId(){
        FlwGroup flwGroup1 = flwGroupWithId("5ba9a0928dde95d187544babf6c0ad24");
        FlwGroup flwGroup2 = flwGroupWithId("5ba9a0928dde95d187544babf6c0af36");
        FlwGroup flwGroup3 = flwGroupWithId("5ba9a0928dde95d187544babf6c0ag48");
        template.saveOrUpdateAll(Arrays.asList(
                flwGroup1,
                flwGroup2,
                flwGroup3));

        List<FlwGroup> groupsFromDb = repository.findAllByGroupId(Arrays.asList("5ba9a0928dde95d187544babf6c0ad24","5ba9a0928dde95d187544babf6c0af36"));

        assertEquals(2, groupsFromDb.size());
        assertReflectionContains(flwGroup1, groupsFromDb);
        assertReflectionContains(flwGroup2, groupsFromDb);
        assertReflectionDoesNotContains(flwGroup3, groupsFromDb);
    }

    private FlwGroup flwGroupWithId(String groupId) {
        return new FlwGroupBuilder().groupId(groupId).build();
    }
}
