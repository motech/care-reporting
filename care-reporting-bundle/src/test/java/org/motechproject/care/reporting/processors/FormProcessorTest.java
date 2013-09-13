package org.motechproject.care.reporting.processors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.model.AppVersionListEntity;
import org.motechproject.care.reporting.service.MapperService;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.domain.CommcareForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.never;
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
    @Mock
    private MapperService mapperService;

    private FormProcessor formProcessor;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        formProcessor = new FormProcessor(motherFormProcessor, childFormProcessor, service, mapperService);
    }

    @Test
    public void shouldParseMotherAndChildDetailsInForm() {
        CommcareForm form = new CommcareFormBuilder()
                                    .withId("case id")
                                    .addAttribute("name", "form name")
                                    .addAttribute("xmlns", "namespace")
                                    .addMetadata("appVersion", "validVersion")
                                    .build();

        when(mapperService.isAppversionExcluded("validVersion")).thenReturn(false);
        HashMap<String, String> motherFormValues = new HashMap<>();
        when(motherFormProcessor.parseMotherForm(form)).thenReturn(motherFormValues);
        ArrayList<Map<String, String>> childFormValues = new ArrayList<>();
        when(childFormProcessor.parseChildForms(form)).thenReturn(childFormValues);


        formProcessor.process(form);

        verify(mapperService).isAppversionExcluded("validVersion");
        verify(motherFormProcessor).parseMotherForm(form);
        verify(childFormProcessor).parseChildForms(form);
        verify(service).processAndSaveForms(motherFormValues, childFormValues);
    }


    @Test
    public void shouldNotParseMotherAndChildDetailsInFormWhenVersionIsExcluded() {
        CommcareForm form = new CommcareFormBuilder()
                .withId("case id")
                .addAttribute("name", "form name")
                .addAttribute("xmlns", "namespace")
                .addMetadata("appVersion", "blacklistVersion")
                .build();

        when(mapperService.isAppversionExcluded("blacklistVersion")).thenReturn(true);
        HashMap<String, String> motherFormValues = new HashMap<>();
        when(motherFormProcessor.parseMotherForm(form)).thenReturn(motherFormValues);
        ArrayList<Map<String, String>> childFormValues = new ArrayList<>();
        when(childFormProcessor.parseChildForms(form)).thenReturn(childFormValues);


        formProcessor.process(form);

        verify(mapperService).isAppversionExcluded("blacklistVersion");
        verify(motherFormProcessor, never()).parseMotherForm(form);
        verify(childFormProcessor, never()).parseChildForms(form);
        verify(service, never()).processAndSaveForms(motherFormValues, childFormValues);
    }

    @Test
    public void shouldNotParseMotherAndChildDetailsInFormWhenVersionDoesNotExist() {
        CommcareForm form = new CommcareFormBuilder()
                .withId("case id")
                .addAttribute("name", "form name")
                .addAttribute("xmlns", "namespace")
                .build();

        HashMap<String, String> motherFormValues = new HashMap<>();
        when(motherFormProcessor.parseMotherForm(form)).thenReturn(motherFormValues);
        ArrayList<Map<String, String>> childFormValues = new ArrayList<>();
        when(childFormProcessor.parseChildForms(form)).thenReturn(childFormValues);


        formProcessor.process(form);

        verify(mapperService, never()).isAppversionExcluded("");
        verify(motherFormProcessor, never()).parseMotherForm(form);
        verify(childFormProcessor, never()).parseChildForms(form);
        verify(service, never()).processAndSaveForms(motherFormValues, childFormValues);
    }

}
