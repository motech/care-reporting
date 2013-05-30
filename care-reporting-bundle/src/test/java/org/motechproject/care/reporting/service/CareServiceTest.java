package org.motechproject.care.reporting.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.repository.Repository;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

public class CareServiceTest {

    @Mock
    Repository dbRepository;

    CareService service;

    @Before
    public void setUp(){
        initMocks(this);
        service = new CareService(dbRepository);
    }

    @Test
    public void shouldReturnMotherIfExistsInRepository(){
        MotherCase expectedMotherCase = new MotherCase();
        expectedMotherCase.setCaseId("1");

        when(dbRepository.get("caseId", "1", MotherCase.class)).thenReturn(expectedMotherCase);

        MotherCase actualMotherCase = service.getMotherCase("1");

        assertEquals(expectedMotherCase, actualMotherCase);
    }

    @Test
    public void shouldReturnNewMotherIfNotExistsInRepository(){
        MotherCase expectedMotherCase = new MotherCase();
        expectedMotherCase.setCaseId("1");

        when(dbRepository.get("caseId", "1", MotherCase.class)).thenReturn(null);

        MotherCase actualMotherCase = service.getMotherCase("1");

        assertReflectionEquals(expectedMotherCase, actualMotherCase);
    }

    @Test
    public void shouldReturnFlwIfExistsInRepository(){
        Flw expectedFlw = new Flw();
        expectedFlw.setName("1");

        when(dbRepository.get("name", "1", Flw.class)).thenReturn(expectedFlw);

        Flw actualFlw = service.getFlw("1");

        assertEquals(expectedFlw, actualFlw);
    }

    @Test
    public void shouldReturnNewFlwIfNotExistsInRepository(){
        Flw expectedFlw = new Flw();
        expectedFlw.setName("1");

        when(dbRepository.get("name", "1", Flw.class)).thenReturn(null);

        Flw actualFlw = service.getFlw("1");

        assertReflectionEquals(expectedFlw, actualFlw);
    }
}
