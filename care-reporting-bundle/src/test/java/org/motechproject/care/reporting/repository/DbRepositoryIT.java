package org.motechproject.care.reporting.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.motechproject.care.reporting.domain.measure.NewForm;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.*;

public class DbRepositoryIT extends SpringIntegrationTest {

    @Autowired
    private DbRepository repository;

    @Test
    @Ignore
    public void shouldSaveNewForm() {
        NewForm form = new NewForm();
        form.setCaseName("mother");
        form.setFormId("abcd");

        int savedFormId = repository.save(form);

        NewForm otherForm = new NewForm();
        otherForm.setCaseName("other mother");
        otherForm.setFormId("abcd");
        Integer otherFormId = repository.save(otherForm);

        System.out.println(savedFormId);
        System.out.println(otherFormId);

        NewForm savedForm = repository.get(savedFormId, NewForm.class);
        assertEquals("mother", savedForm.getCaseName());
        assertEquals("abcd", savedForm.getFormId());
        assertEquals(savedFormId, savedForm.getId());
    }

    @Test
    public void shouldGetByMatchingCriteria(){

        NewForm form = new NewForm();
        form.setCaseName("mother");
        form.setFormId("abcd");

        repository.save(form);

        NewForm newForm = repository.get("caseName", "mother", NewForm.class);

        assertEquals("abcd", newForm.getFormId());
        assertEquals("mother", newForm.getCaseName());
    }

    @Test
    public void shouldGetByNonMatchingCriteria(){

        NewForm newForm = repository.get("caseName", "father", NewForm.class);

        assertNull(newForm);
    }
}
