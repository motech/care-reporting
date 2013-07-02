package org.motechproject.care.reporting.processors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.domain.CommcareForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FormProcessorTest {

    @Mock
    private Service service;
    @Mock
    private ChildFormProcessor childFormProcessor;
    @Mock
    private MotherFormProcessor motherFormProcessor;
    private FormProcessor formProcessor;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        formProcessor = new FormProcessor(motherFormProcessor, childFormProcessor, service);
    }

    @Test
    public void shouldParseMotherAndChildDetailsInForm() {
        CommcareForm form = new CommcareFormBuilder().withId("case id").addAttribute("name", "form name").addAttribute("xmlns", "namespace").build();
        HashMap<String, String> motherFormValues = new HashMap<>();
        when(motherFormProcessor.parseMotherForm(form)).thenReturn(motherFormValues);
        ArrayList<Map<String, String>> childFormValues = new ArrayList<>();
        when(childFormProcessor.parseChildForms(form)).thenReturn(childFormValues);

        formProcessor.process(form);

        verify(motherFormProcessor).parseMotherForm(form);
        verify(childFormProcessor).parseChildForms(form);
        verify(service).processAndSaveForms(motherFormValues, childFormValues);
    }
}
