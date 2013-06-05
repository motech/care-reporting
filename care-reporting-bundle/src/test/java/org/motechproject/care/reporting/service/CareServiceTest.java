package org.motechproject.care.reporting.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.domain.measure.NewForm;
import org.motechproject.care.reporting.repository.Repository;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
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
    public void shouldReturnChildIfExistsInRepository(){
        ChildCase expectedChildCase = new ChildCase();
        expectedChildCase.setCaseId("1");

        when(dbRepository.get("caseId", "1", ChildCase.class)).thenReturn(expectedChildCase);

        ChildCase actualChildCase = service.getChildCase("1");

        assertEquals(expectedChildCase, actualChildCase);
    }

    @Test
    public void shouldReturnNewChildIfNotExistsInRepository(){
        ChildCase expectedChildCase = new ChildCase();
        expectedChildCase.setCaseId("1");

        when(dbRepository.get("caseId", "1", MotherCase.class)).thenReturn(null);

        ChildCase actualChildCase = service.getChildCase("1");

        assertReflectionEquals(expectedChildCase, actualChildCase);
    }

    @Test
    public void shouldReturnFlwIfExistsInRepository(){
        Flw expectedFlw = new Flw();
        expectedFlw.setFlwId("1");

        when(dbRepository.get("flwId", "1", Flw.class)).thenReturn(expectedFlw);

        Flw actualFlw = service.getFlw("1");

        assertEquals(expectedFlw, actualFlw);
    }

    @Test
    public void shouldReturnNewFlwIfNotExistsInRepository(){
        Flw expectedFlw = new Flw();
        expectedFlw.setFlwId("1");

        when(dbRepository.get("flwId", "1", Flw.class)).thenReturn(null);

        Flw actualFlw = service.getFlw("1");

        assertReflectionEquals(expectedFlw, actualFlw);
    }

    @Test
    public void shouldSaveInstance(){
        NewForm newForm = new NewForm();
        newForm.setFullName("fullName");

        service.save(newForm);

        verify(dbRepository).save(newForm);

    }

    @Test
    public void shouldSaveOrUpdateAll(){
        ArrayList<Flw> flws = new ArrayList<>();
        flws.add(new Flw());

        service.saveOrUpdateAll(flws);

        verify(dbRepository).saveOrUpdateAll(flws);
    }
}
