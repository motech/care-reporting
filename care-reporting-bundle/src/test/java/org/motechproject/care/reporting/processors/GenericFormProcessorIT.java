package org.motechproject.care.reporting.processors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.builder.FormValueElementBuilder;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.domain.measure.EbfChildForm;
import org.motechproject.care.reporting.domain.measure.EbfMotherForm;
import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.care.reporting.parser.InfoParserImpl;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.motechproject.care.reporting.service.MapperService;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GenericFormProcessorIT extends SpringIntegrationTest {
    @Autowired
    Service service;
    @Mock
    MapperService mapperService;

    GenericFormProcessor genericFormProcessor;

    @Before
    public void setUp() {
        initMocks(this);
        genericFormProcessor = new GenericFormProcessor(service, mapperService);
    }

    @After
    public void tearDown() {
        template.deleteAll(template.loadAll(EbfChildForm.class));
        template.deleteAll(template.loadAll(EbfMotherForm.class));
        template.deleteAll(template.loadAll(MotherCase.class));
        template.deleteAll(template.loadAll(ChildCase.class));
    }

    @Test
    public void shouldSaveNewForm() {
        String instanceId = "202dfec6-55a6-45ce-8857-085b4b91a70e";
        CommcareForm ebfFormToSave = setUpForm(instanceId);

        genericFormProcessor.process(ebfFormToSave);

        List<EbfMotherForm> savedEbfMotherForms = template.loadAll(EbfMotherForm.class);
        assertEquals(1, savedEbfMotherForms.size());
        assertEquals(instanceId, savedEbfMotherForms.get(0).getInstanceId());
        List<EbfChildForm> savedEbfChildForms = template.loadAll(EbfChildForm.class);
        assertEquals(1, savedEbfChildForms.size());
        assertEquals(instanceId, savedEbfChildForms.get(0).getInstanceId());
    }

    private CommcareForm setUpForm(String instanceId) {
        String namespace = "http://bihar.commcarehq.org/pregnancy/ebf";
        String version = "v2.0.0alpha";
        when(mapperService.getFormInfoParser(namespace, version, FormSegment.METADATA)).thenReturn(new InfoParserImpl(new HashMap<String, String>(){{
            put("instanceID", "instanceId");
        }}));
        when(mapperService.getFormInfoParser(namespace, version, FormSegment.MOTHER)).thenReturn(new InfoParserImpl());
        when(mapperService.getFormInfoParser(namespace, version, FormSegment.CHILD)).thenReturn(new InfoParserImpl());

        FormValueElement mother = new FormValueElementBuilder()
                .addAttribute("date_modified", "2013-01-21T17:29:06.657+05:30")
                .addAttribute("case_id", "c5e5e5c1-c1a4-4cf1-b208-faf1c170b2de")
                .build();
        FormValueElement child = new FormValueElementBuilder()
                .addAttribute("caseId", "9a1b538f-42f3-421f-9fbd-b3937c7e1bf7")
                .build();
        FormValueElement childInfo = new FormValueElementBuilder()
                .addSubElement("case", child)
                .build();
        FormValueElementBuilder formValueElementBuilder = new FormValueElementBuilder()
                .addAttribute("xmlns", namespace)
                .addSubElement("case", mother)
                .addSubElement("child_info", childInfo)
                .addSubElement("why_no_ppffp", "refused")
                .addSubElement("add_vaccinations", "true")
                .addSubElement("nextvisittype", "ebf");

        return new CommcareFormBuilder()
                .addMetadata("instanceID", instanceId)
                .addVersion("version", "v2.0.0alpha")
                .withFormValueElementBuilder(formValueElementBuilder).build();
    }
}
