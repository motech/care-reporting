package org.motechproject.care.reporting.converter;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionEqualsWithIgnore;

public class FlwConverterIT extends SpringIntegrationTest {
    @Autowired
    private FlwConverter flwConverter;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void shouldGetANewFlwIfDoesNotExist() throws Exception {
        Object convertedObject = flwConverter.convert(Flw.class, "89fda0284e008d2e0c980fb13f96c45a");
        boolean isPersisted = sessionFactory.getCurrentSession().contains(convertedObject);
        assertFalse(isPersisted);
        assertEquals("89fda0284e008d2e0c980fb13f96c45a", ((Flw) convertedObject).getFlwId());
    }

    @Test
    public void shouldGetPersistedObjectIfFlwExists() throws Exception {
        Flw flw = new Flw();
        flw.setFlwId("89fda0284e008d2e0c980fb13f96c45a");
        template.save(flw);

        Object convertedObject = flwConverter.convert(Flw.class, "89fda0284e008d2e0c980fb13f96c45a");
        boolean isPersisted = sessionFactory.getCurrentSession().contains(convertedObject);
        assertTrue(isPersisted);
    }
}
