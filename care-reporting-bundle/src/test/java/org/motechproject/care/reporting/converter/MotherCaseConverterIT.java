package org.motechproject.care.reporting.converter;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MotherCaseConverterIT extends SpringIntegrationTest {
    @Autowired
    private MotherCaseConverter motherCaseConverter;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void shouldGetANewMotherIfDoesNotExists() throws Exception {
        Object convertedMotherCase = motherCaseConverter.convert(MotherCase.class, "123456789");
        boolean isPersisted = sessionFactory.getCurrentSession().contains(convertedMotherCase);
        assertFalse(isPersisted);
        assertEquals("123456789", ((MotherCase) convertedMotherCase).getCaseId());
    }

    @Test
    public void shouldGetPersistedObjectIfMotherCaseExists() throws Exception {
        MotherCase motherCase = new MotherCase();
        motherCase.setCaseId("123456789");
        template.save(motherCase);

        Object convertedObject = motherCaseConverter.convert(MotherCase.class, "123456789");
        boolean isPersisted = sessionFactory.getCurrentSession().contains(convertedObject);
        assertTrue(isPersisted);
    }
}
