package org.motechproject.care.reporting.processors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.care.reporting.builder.CaseEventBuilder;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.events.CaseEvent;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class GenericCaseProcessorWorkerTest {


    @Mock
    private Service service;
    private GenericCaseProcessorWorker caseWorker;

    @Before
    public void setUp() {
        initMocks(this);
        caseWorker = new GenericCaseProcessorWorker(service);
    }

    @Test
    public void shouldParseMotherCase() throws Exception {
        String flwId = "user id";
        String groupId = "group id";
        Flw expectedFlw = new Flw(1);
        FlwGroup expectedGroup = new FlwGroup(2);
        String caseName = "case name";
        CaseEvent caseEvent = new CaseEventBuilder("case id")
                .withCaseName(caseName)
                .withCaseType("cc_bihar_pregnancy")
                .withUserId(flwId)
                .with("owner_id", groupId)
                .build();
        when(service.getFlw(flwId)).thenReturn(expectedFlw);
        when(service.getGroup(groupId)).thenReturn(expectedGroup);

        caseWorker.process(caseEvent);

        verify(service).getFlw(flwId);
        verify(service).getGroup(groupId);
        verify(service, never()).getMotherCase(anyString());
        ArgumentCaptor<MotherCase> motherCaptor = ArgumentCaptor.forClass(MotherCase.class);
        verify(service).save(motherCaptor.capture());
        MotherCase actualMotherCase = motherCaptor.getValue();
        assertEquals(expectedFlw, actualMotherCase.getFlw());
        assertEquals(expectedGroup, actualMotherCase.getFlwGroup());
        assertEquals(caseName, actualMotherCase.getCaseName());
    }

    @Test
    public void shouldParseChildCase() throws Exception {
        String flwId = "user id";
        String groupId = "group id";
        String motherId = "mother id";
        Flw expectedFlw = new Flw(1);
        FlwGroup expectedGroup = new FlwGroup(2);
        MotherCase expectedMother = new MotherCase(3);
        CaseEvent caseEvent = new CaseEventBuilder("case id")
                .withCaseName("case name")
                .withCaseType("cc_bihar_newborn")
                .withUserId(flwId)
                .with("owner_id", groupId)
                .with("mother_id", motherId)
                .build();
        when(service.getFlw(flwId)).thenReturn(expectedFlw);
        when(service.getGroup(groupId)).thenReturn(expectedGroup);
        when(service.getMotherCase(motherId)).thenReturn(expectedMother);

        caseWorker.process(caseEvent);

        verify(service).getFlw(flwId);
        verify(service).getGroup(groupId);
        verify(service).getMotherCase(motherId);
        ArgumentCaptor<ChildCase> childCaptor = ArgumentCaptor.forClass(ChildCase.class);
        verify(service).save(childCaptor.capture());
        ChildCase actualChildCase = childCaptor.getValue();
        assertEquals(expectedFlw, actualChildCase.getFlw());
        assertEquals(expectedGroup, actualChildCase.getFlwGroup());
        assertEquals(expectedMother, actualChildCase.getMotherCase());
    }
}
