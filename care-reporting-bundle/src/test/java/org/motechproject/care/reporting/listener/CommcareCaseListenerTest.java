package org.motechproject.care.reporting.listener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.processors.ChildCaseProcessor;
import org.motechproject.care.reporting.processors.CloseCaseProcessor;
import org.motechproject.care.reporting.processors.MotherCaseProcessor;
import org.motechproject.commcare.events.CaseEvent;
import org.motechproject.event.MotechEvent;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommcareCaseListenerTest {

    @Mock
    private ChildCaseProcessor childCaseProcessor;
    @Mock
    private CloseCaseProcessor closeCaseProcessor;
    @Mock
    private MotherCaseProcessor motherCaseProcessor;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldHandleCloseEvent() {
        CommcareCaseListener commcareCaseListener = new CommcareCaseListener(motherCaseProcessor, childCaseProcessor, closeCaseProcessor);

        MotechEvent event = new MotechEvent();
        event.getParameters().put("caseAction", "CLOSE");
        commcareCaseListener.handleEvent(event);

        verify(closeCaseProcessor).process(any(CaseEvent.class));
        verifyZeroInteractions(motherCaseProcessor);
        verifyZeroInteractions(childCaseProcessor);
    }


    @Test
    public void shouldHandleMotherCaseEvent() {
        CommcareCaseListener commcareCaseListener = new CommcareCaseListener(motherCaseProcessor, childCaseProcessor, closeCaseProcessor);

        MotechEvent event = new MotechEvent();
        event.getParameters().put("caseType", "cc_bihar_pregnancy");
        commcareCaseListener.handleEvent(event);

        verify(motherCaseProcessor).process(any(CaseEvent.class));
        verifyNoMoreInteractions(closeCaseProcessor);
        verifyZeroInteractions(childCaseProcessor);
    }


    @Test
    public void shouldHandleChildCaseEvent() {
        CommcareCaseListener commcareCaseListener = new CommcareCaseListener(motherCaseProcessor, childCaseProcessor, closeCaseProcessor);

        MotechEvent event = new MotechEvent();
        event.getParameters().put("caseType", "cc_bihar_newborn");
        commcareCaseListener.handleEvent(event);

        verify(childCaseProcessor).process(any(CaseEvent.class));
        verifyNoMoreInteractions(closeCaseProcessor);
        verifyZeroInteractions(motherCaseProcessor);
    }

}