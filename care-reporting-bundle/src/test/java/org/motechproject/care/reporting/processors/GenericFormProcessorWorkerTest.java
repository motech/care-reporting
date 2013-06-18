package org.motechproject.care.reporting.processors;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.builder.FormValueElementBuilder;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.domain.measure.EbfChildForm;
import org.motechproject.care.reporting.domain.measure.NewForm;
import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.care.reporting.parser.InfoParserImpl;
import org.motechproject.care.reporting.service.MapperService;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.motechproject.care.reporting.utils.TestUtils.assertDateIgnoringSeconds;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionEqualsWithIgnore;

public class GenericFormProcessorWorkerTest {
    @Mock
    Service service;

    @Mock
    MapperService mapperService;

    private GenericFormProcessorWorker processor;

    @Before
    public void setUp(){
        initMocks(this);
        processor = new GenericFormProcessorWorker(service, mapperService);

        when(mapperService.getFormInfoParser(anyString(), anyString(), isA(FormSegment.class))).thenReturn(new InfoParserImpl());
    }

    @Test
    public void shouldParseMotherNewForm() {

        FormValueElement motherCase = new FormValueElementBuilder()
                .addAttribute("case_id", "94d5374f-290e-409f-bc57-86c2e4bcc43f")
                .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                .build();

        CommcareForm newForm = new CommcareFormBuilder().addMetadata("deviceID", "IUFN6IXAIV7Z1OKJBIWV7WY3C")
                                .addMetadata("time_start", "2012-07-21T11:59:31.076+05:30")
                                .addMetadata("time_end", "2012-07-21T12:02:59.923+05:30")
                                .addMetadata("username", "username")
                                .addMetadata("userID", "89fda0284e008d2e0c980fb13fa0e5bb")
                                .addMetadata("instanceId", "e34707f8-80c8-4198-bf99-c11c90ba5c98")
                                
                                .addAttribute("uiVersion", "1")
                                .addAttribute("version", "1")
                                .addAttribute("name", "New Beneficiary")
                                .addAttribute("xmlns", "http://bihar.commcarehq.org/pregnancy/new")
                                
                                .addSubElement("full_name", "&#2327;&#2366;&#2351;&#2340;&#2381;&#2352;&#2368; &#2342;&#2375;&#2357;&#2368;")
                                .addSubElement("husband_name", "&#2342;&#2367;&#2344;&#2375;&#2358; &#2350;&#2369;&#2326;&#2367;&#2351;&#2366;")
                                .addSubElement("hh_number", "165")
                                .addSubElement("family_number", "5")
                                .addSubElement("dob_known", "no")
                                .addSubElement("caste", "other")
                                .addSubElement("success", "OK")
                                .addSubElement("age_calc", (String) null)
                                .addSubElement("case", motherCase)
                                
                                .build();

        MotherCase expectedMotherCase = new MotherCase() {{
            setCaseId("94d5374f-290e-409f-bc57-86c2e4bcc43f");
        }};

        Flw expectedFlw = new Flw() {{
            setFlwId("89fda0284e008d2e0c980fb13fa0e5bb");
        }};

        NewForm expectedForm = new NewForm();
        expectedForm.setDateModified(new DateTime(2012, 7, 21, 12, 2, 59, 923, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());
        expectedForm.setFullName("&#2327;&#2366;&#2351;&#2340;&#2381;&#2352;&#2368; &#2342;&#2375;&#2357;&#2368;");
        expectedForm.setHusbandName("&#2342;&#2367;&#2344;&#2375;&#2358; &#2350;&#2369;&#2326;&#2367;&#2351;&#2366;");
        expectedForm.setHhNumber(165);
        expectedForm.setFamilyNumber(5);
        expectedForm.setDobKnown(false);
        expectedForm.setCaste("other");
        expectedForm.setAgeCalc((short) 0);
        expectedForm.setInstanceId("e34707f8-80c8-4198-bf99-c11c90ba5c98");
        expectedForm.setMotherCase(expectedMotherCase);
        expectedForm.setFlw(expectedFlw);
        expectedForm.setTimeStart(new DateTime(2012, 7, 21, 11, 59, 31, 76, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());
        expectedForm.setTimeEnd(new DateTime(2012, 7, 21, 12, 2, 59, 923, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());

        when(service.getMotherCase("94d5374f-290e-409f-bc57-86c2e4bcc43f")).thenReturn(expectedMotherCase);
        when(service.getFlw("89fda0284e008d2e0c980fb13fa0e5bb")).thenReturn(expectedFlw);

        processor.initialize(newForm);
        NewForm motherForm = (NewForm) processor.parseMotherForm();

        assertReflectionEqualsWithIgnore(expectedForm, motherForm, new String[]{"creationTime"});
        assertDateIgnoringSeconds(expectedForm.getCreationTime(), motherForm.getCreationTime());
    }

    @Test
    public void shouldReturnEmptyListIfChildInfoNotExistsInForm(){
        CommcareForm form = new CommcareFormBuilder().build();

        processor.initialize(form);
        List<Serializable> serializables = processor.parseChildForms();

        assertEquals(0, serializables.size());
    }


    @Test
    public void shouldParseChildEbfForm() {

        FormValueElement motherCase = new FormValueElementBuilder()
                .addAttribute("case_id", "94d5374f-290e-409f-bc57-86c2e4bcc43f")
                .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                .build();

        FormValueElement childCase1 = new FormValueElementBuilder()
                .addAttribute("case_id", "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf")
                .addAttribute("date_modified", "2013-03-03T10:38:52.804+05:30")
                .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                .build();

        FormValueElement childCase1Details = new FormValueElementBuilder()
                .addSubElement("cid", "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf")
                .addSubElement("index","0")
                .addSubElement("case",childCase1)
                .addSubElement("display_child", "OK")
                .addSubElement("name_update", "no")
                .addSubElement("child_name", (String) null)
                .addSubElement("breastfeeding", "yes")
                .addSubElement("water_or_milk", "no")
                .addSubElement("tea_other", "no")
                .addSubElement("eating", "yes")
                .addSubElement("emptying", "yes")
                .build();


        FormValueElement childCase2 = new FormValueElementBuilder()
                .addAttribute("case_id", "59ab28e0-2d2d-4bc7-933f-09dcacf70d61")
                .addAttribute("date_modified", "2013-03-03T10:38:52.804+05:30")
                .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                .build();

        FormValueElement childCase2Details = new FormValueElementBuilder()
                .addSubElement("cid","59ab28e0-2d2d-4bc7-933f-09dcacf70d61")
                .addSubElement("index","1")
                .addSubElement("case",childCase2)
                .addSubElement("display_child", "OK")
                .addSubElement("name_update", "no")
                .addSubElement("child_name", (String) null)
                .addSubElement("breastfeeding", "yes")
                .addSubElement("water_or_milk", "no")
                .addSubElement("tea_other", "no")
                .addSubElement("eating", "yes")
                .addSubElement("emptying", "yes")
                .build();

        CommcareForm ebfForm = new CommcareFormBuilder().addMetadata("deviceID", "IUFN6IXAIV7Z1OKJBIWV7WY3C")
                .addMetadata("time_start", "2013-03-03T10:31:51.045+05:30")
                .addMetadata("time_end", "2013-03-03T10:38:52.804+05:30")
                .addMetadata("username", "username")
                .addMetadata("userID", "89fda0284e008d2e0c980fb13fa0e5bb")
                .addMetadata("instance_id", "ff2eb090-03a9-4f23-afed-cf6012784c55")

                .addAttribute("uiVersion", "1")
                .addAttribute("version", "1")
                .addAttribute("name", "EBF")
                .addAttribute("xmlns", "http://bihar.commcarehq.org/pregnancy/ebf")

                .addSubElement("eb_visit_num", "1")
                .addSubElement("num_children", "2")
                .addSubElement("why_no_ppffp", "refused")
                .addSubElement("ppfp_interest", "1")
                .addSubElement("nextvisit", "OK")
                .addSubElement("nextvisittype", "ebf")
                .addSubElement("case", motherCase)
                .addSubElement("child_info", childCase1Details)
                .addSubElement("child_info", childCase2Details)

                .build();

        MotherCase expectedMotherCase = new MotherCase();
        expectedMotherCase.setCaseId("94d5374f-290e-409f-bc57-86c2e4bcc43f");

        Flw expectedFlw = new Flw();
        expectedFlw.setFlwId("89fda0284e008d2e0c980fb13fa0e5bb");

        ChildCase expectedChildCase1 = new ChildCase();
        expectedChildCase1.setCaseId("3e8998ce-b19f-4fa7-b1a1-721b6951e3cf");

        ChildCase expectedChildCase2 = new ChildCase();
        expectedChildCase2.setCaseId("59ab28e0-2d2d-4bc7-933f-09dcacf70d61");


        when(service.getChildCase("3e8998ce-b19f-4fa7-b1a1-721b6951e3cf")).thenReturn(expectedChildCase1);
        when(service.getChildCase("59ab28e0-2d2d-4bc7-933f-09dcacf70d61")).thenReturn(expectedChildCase2);
        when(service.getMotherCase("94d5374f-290e-409f-bc57-86c2e4bcc43f")).thenReturn(expectedMotherCase);
        when(service.getFlw("89fda0284e008d2e0c980fb13fa0e5bb")).thenReturn(expectedFlw);

        processor.initialize(ebfForm);
        List<Serializable> serializables = processor.parseChildForms();
        assertEquals(2, serializables.size());

        final EbfChildForm expectedForm1 = getExpectedForm(((EbfChildForm)serializables.get(0)).getChildCase().getCaseId());
        final EbfChildForm expectedForm2 = getExpectedForm(((EbfChildForm)serializables.get(1)).getChildCase().getCaseId());

        assertChildEbfForm(expectedForm1, (EbfChildForm) serializables.get(0));
        assertChildEbfForm(expectedForm2, (EbfChildForm) serializables.get(1));
    }

    private void assertChildEbfForm(EbfChildForm expectedForm, EbfChildForm actualForm) {
        assertReflectionEqualsWithIgnore(expectedForm, actualForm, new String[]{"creationTime", "flw", "childCase"});
        assertReflectionEqualsWithIgnore(expectedForm.getFlw(), actualForm.getFlw(), new String[]{"creationTime"});
        assertReflectionEqualsWithIgnore(expectedForm.getChildCase(), actualForm.getChildCase(), new String[]{"creationTime"});
        assertDateIgnoringSeconds(expectedForm.getCreationTime(), actualForm.getCreationTime());
    }

    private EbfChildForm getExpectedForm(String caseId){

        ChildCase expectedChildCase = new ChildCase();
        expectedChildCase.setCaseId(caseId);

        Flw expectedFlw = new Flw();
        expectedFlw.setFlwId("89fda0284e008d2e0c980fb13fa0e5bb");

        int index = caseId.equals("59ab28e0-2d2d-4bc7-933f-09dcacf70d61") ? 1 : 0;

        EbfChildForm expectedChildForm = new EbfChildForm();

        expectedChildForm.setChildCase(expectedChildCase);
        expectedChildForm.setFlw(expectedFlw);
        expectedChildForm.setDateModified(new DateTime(2013, 3, 3, 10, 38, 52, 804, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());
        expectedChildForm.setNameUpdate(false);
        expectedChildForm.setChildName(null);
        expectedChildForm.setBreastfeeding(true);
        expectedChildForm.setWaterOrMilk(false);
        expectedChildForm.setTeaOther(false);
        expectedChildForm.setEating(true);
        expectedChildForm.setEmptying(true);
        expectedChildForm.setInstanceId("ff2eb090-03a9-4f23-afed-cf6012784c55");
        expectedChildForm.setTimeStart(new DateTime(2013, 3, 3, 10, 31, 51, 45, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());
        expectedChildForm.setTimeEnd(new DateTime(2013, 3, 3, 10, 38, 52, 804, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());

        return expectedChildForm;
    }

    @Test
    public void shouldSaveForm(){
        Serializable newFormObject = new NewForm();
        GenericFormProcessorWorker processor = new GenericFormProcessorWorker(service, mapperService);
        when(service.findByExternalPrimaryKey(newFormObject)).thenReturn(null);

        processor.saveForm(newFormObject, NewForm.class);

        verify(service).save(any(NewForm.class));
    }

    @Test
    public void shouldSaveMultipleForms(){
        List<Serializable> serializables = new ArrayList<>();
        serializables.add(new EbfChildForm());
        serializables.add(new EbfChildForm());
        serializables.add(new EbfChildForm());

        GenericFormProcessorWorker processor = new GenericFormProcessorWorker(service, mapperService);

        processor.saveForm(serializables, EbfChildForm.class);

        verify(service, times(3)).save(any(EbfChildForm.class));

    }

    @Test
    public void shouldNotSaveFormWithAnExistingInstanceId(){
        NewForm newForm = new NewForm();
        when(service.findByExternalPrimaryKey(newForm)).thenReturn(new NewForm());

        new GenericFormProcessorWorker(service, mapperService).saveForm(newForm, NewForm.class);

        verify(service, never()).save(anyObject());
    }
}


