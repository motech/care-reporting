package org.motechproject.care.reporting.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.builder.FlwBuilder;
import org.motechproject.care.reporting.builder.FlwGroupBuilder;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.domain.measure.NewForm;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static junit.framework.Assert.*;
import static org.motechproject.care.reporting.utils.TestUtils.*;

public class DbRepositoryIT extends SpringIntegrationTest {

    @Autowired
    private Repository repository;

    @Before
    @After
    public void setUp() {
        template.deleteAll(template.loadAll(NewForm.class));
        template.deleteAll(template.loadAll(ChildCase.class));
        template.deleteAll(template.loadAll(MotherCase.class));
        template.deleteAll(template.loadAll(Flw.class));
        template.deleteAll(template.loadAll(FlwGroup.class));
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

        List<FlwGroup> groupsFromDb = repository.findAllByField(FlwGroup.class, Arrays.asList("5ba9a0928dde95d187544babf6c0ad24","5ba9a0928dde95d187544babf6c0af36"), "groupId");

        assertEquals(2, groupsFromDb.size());
        assertReflectionContains(flwGroup1, groupsFromDb);
        assertReflectionContains(flwGroup2, groupsFromDb);
        assertReflectionDoesNotContains(flwGroup3, groupsFromDb);
    }

    private FlwGroup flwGroupWithId(String groupId) {
        return new FlwGroupBuilder().groupId(groupId).build();
    }

    @Test
    public void shouldSaveCase() {
        String flwId = "flwId";
        String groupId = "groupId";
        String caseId = "caseId";
        Flw flw = new Flw();
        flw.setFlwId(flwId);
        FlwGroup flwGroup = new FlwGroup();
        flwGroup.setGroupId(groupId);
        MotherCase expectedMother = new MotherCase();
        expectedMother.setCaseId(caseId);
        expectedMother.setFlw(flw);
        expectedMother.setFlwGroup(flwGroup);

        repository.save(expectedMother);

        List<MotherCase> motherCases = template.loadAll(MotherCase.class);
        assertEquals(1, motherCases.size());
        MotherCase actualMother = motherCases.get(0);
        assertEquals(caseId, actualMother.getCaseId());
        assertEquals(flwId, actualMother.getFlw().getFlwId());
        assertEquals(groupId, actualMother.getFlwGroup().getGroupId());
    }

    @Test
    public void shouldFindByExternalPrimaryKey() throws Exception {
        Flw flw = new FlwBuilder().flwId("5ba9a0928dde95d187544babf6c0ad24").build();
        template.save(flw);

        Flw flwFromDb = repository.findByExternalPrimaryKey(Flw.class, "5ba9a0928dde95d187544babf6c0ad24");

        assertReflectionEqualsWithIgnore(flw, flwFromDb);
    }

    @Test
    public void shouldReturnNullIfCannotFindByExternalPrimaryKey() throws Exception {
        assertNull(repository.findByExternalPrimaryKey(Flw.class, "5ba9a0928dde95d187544babf6c0ad00"));
    }

    @Test
    public void shouldFetchFromDbGivenBasedOnMultipleNestedFields(){
        final String caseId = "myCaseId";
        final String instanceId = "myInstanceId";
        NewForm form = new NewForm();
        MotherCase motherCase = new MotherCase();
        motherCase.setCaseId(caseId);
        form.setMotherCase(motherCase);
        form.setInstanceId(instanceId);
        template.save(form);
        Map<String, Object> fieldMap = new HashMap<String, Object>() {{
            put("mc.caseId", caseId);
            put("instanceId", instanceId);
        }};
        Map<String, String> aliasMapping = new HashMap<String, String>() {{
            put("motherCase", "mc");
        }};

        NewForm actualFormFromDb = repository.get(NewForm.class, fieldMap, aliasMapping);

        assertEquals(instanceId, actualFormFromDb.getInstanceId());
        assertEquals(caseId, actualFormFromDb.getMotherCase().getCaseId());
    }

    @Test
    public void shouldDeleteForm(){
        NewForm form = new NewForm();
        form.setCaseName("mother");
        form.setInstanceId("abcd");
        repository.save(form);

        NewForm newFormBeforeDelete = repository.get(NewForm.class, "caseName", "mother");
        assertNotNull(newFormBeforeDelete);

        repository.delete(form);

        NewForm newFormAfterDelete = repository.get(NewForm.class, "caseName", "mother");
        assertNull(newFormAfterDelete);
    }

}
