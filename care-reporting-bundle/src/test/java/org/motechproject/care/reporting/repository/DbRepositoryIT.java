package org.motechproject.care.reporting.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.measure.NewForm;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

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

        NewForm savedForm = repository.get(savedFormId, NewForm.class);
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

        NewForm newForm = repository.get("caseName", "mother", NewForm.class);

        assertEquals("abcd", newForm.getInstanceId());
        assertEquals("mother", newForm.getCaseName());
    }

    @Test
    public void shouldGetByNonMatchingCriteria(){

        NewForm newForm = repository.get("caseName", "father", NewForm.class);

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
}
