package org.motechproject.care.reporting.processors;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.builder.FormValueElementBuilder;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.domain.measure.*;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serializable;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.motechproject.care.reporting.utils.TestUtils.assertDateIgnoringSeconds;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionEqualsWithIgnore;

public class ChildFormProcessorWorkerIT extends SpringIntegrationTest {

    @Autowired
    private ChildFormProcessorWorker childFormProcessorWorker;

    @Test
    public void shouldParseChildEbfForm() {

        FormValueElement motherCaseData = new FormValueElementBuilder()
                .addAttribute("case_id", "94d5374f-290e-409f-bc57-86c2e4bcc43f")
                .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                .build();

        FormValueElement childCase1Data = new FormValueElementBuilder()
                .addAttribute("case_id", "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf")
                .addAttribute("date_modified", "2013-03-03T10:38:52.804+05:30")
                .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                .build();

        FormValueElement childCase1Details = new FormValueElementBuilder()
                .addSubElement("cid", "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf")
                .addSubElement("index", "0")
                .addSubElement("case", childCase1Data)
                .addSubElement("display_child", "OK")
                .addSubElement("name_update", "no")
                .addSubElement("child_name", (String) null)
                .addSubElement("breastfeeding", "yes")
                .addSubElement("water_or_milk", "no")
                .addSubElement("tea_other", "no")
                .addSubElement("eating", "yes")
                .addSubElement("emptying", "yes")
                .build();


        FormValueElement childCase2Data = new FormValueElementBuilder()
                .addAttribute("case_id", "59ab28e0-2d2d-4bc7-933f-09dcacf70d61")
                .addAttribute("date_modified", "2013-03-03T10:38:52.804+05:30")
                .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                .build();

        FormValueElement childCase2Details = new FormValueElementBuilder()
                .addSubElement("cid", "59ab28e0-2d2d-4bc7-933f-09dcacf70d61")
                .addSubElement("index", "1")
                .addSubElement("case", childCase2Data)
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
                .addSubElement("case", motherCaseData)
                .addSubElement("child_info", childCase1Details)
                .addSubElement("child_info", childCase2Details)
                .build();

        MotherCase motherCase = new MotherCase();
        motherCase.setCaseId("94d5374f-290e-409f-bc57-86c2e4bcc43f");
        template.save(motherCase);

        Flw expectedFlw = new Flw();
        expectedFlw.setFlwId("89fda0284e008d2e0c980fb13fa0e5bb");
        template.save(expectedFlw);

        ChildCase persistedChildCase1 = new ChildCase();
        persistedChildCase1.setCaseId("3e8998ce-b19f-4fa7-b1a1-721b6951e3cf");
        template.save(persistedChildCase1);


        ChildCase persistedChildCase2 = new ChildCase();
        persistedChildCase2.setCaseId("59ab28e0-2d2d-4bc7-933f-09dcacf70d61");
        template.save(persistedChildCase2);

        List<Serializable> serializables = childFormProcessorWorker.parseChildForms(ebfForm);
        assertEquals(2, serializables.size());

        final EbfChildForm expectedForm1 = getExpectedForm(((EbfChildForm) serializables.get(0)).getChildCase().getCaseId());
        final EbfChildForm expectedForm2 = getExpectedForm(((EbfChildForm) serializables.get(1)).getChildCase().getCaseId());

        assertChildEbfForm(expectedForm1, (EbfChildForm) serializables.get(0));
        assertChildEbfForm(expectedForm2, (EbfChildForm) serializables.get(1));
    }

    @Test
    public void shouldReturnEmptyListIfChildInfoNotExistsInForm(){
        CommcareForm form = new CommcareFormBuilder().build();

        List<Serializable> serializables = childFormProcessorWorker.parseChildForms(form);

        assertEquals(0, serializables.size());
    }

    @Test
    public void shouldMarkClosedForm() throws Exception {

        FormValueElement childCase1Data = new FormValueElementBuilder()
                .addAttribute("case_id", "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf")
                .addAttribute("date_modified", "2013-03-03T10:38:52.804+05:30")
                .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                .build();

        FormValueElement childCase1Details = new FormValueElementBuilder()
                .addSubElement("cid", "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf")
                .addSubElement("index", "0")
                .addSubElement("case", childCase1Data)
                .addSubElement("close", new FormValueElement())
                .build();

        CommcareForm ebfForm = new CommcareFormBuilder().addMetadata("deviceID", "IUFN6IXAIV7Z1OKJBIWV7WY3C")
                .addMetadata("userID", "89fda0284e008d2e0c980fb13fa0e5bb")
                .addMetadata("instance_id", "ff2eb090-03a9-4f23-afed-cf6012784c55")
                .addAttribute("uiVersion", "1")
                .addAttribute("version", "1")
                .addAttribute("name", "EBF")
                .addAttribute("xmlns", "http://bihar.commcarehq.org/pregnancy/death")
                .addSubElement("num_children", "1")
                .addSubElement("child_info", childCase1Details)
                .build();
        List<Serializable> output = childFormProcessorWorker.parseChildForms(ebfForm);

        assertTrue(((DeathChildForm) output.get(0)).getClose());
    }

    private void assertChildEbfForm(EbfChildForm expectedForm, EbfChildForm actualForm) {
        assertReflectionEqualsWithIgnore(expectedForm, actualForm, new String[]{"id","creationTime", "flw", "childCase"});
        assertReflectionEqualsWithIgnore(expectedForm.getFlw(), actualForm.getFlw(), new String[]{"id", "creationTime"});
        assertReflectionEqualsWithIgnore(expectedForm.getChildCase(), actualForm.getChildCase(), new String[]{"id", "creationTime"});
        assertDateIgnoringSeconds(expectedForm.getCreationTime(), actualForm.getCreationTime());
    }

    private EbfChildForm getExpectedForm(String caseId) {
        ChildCase expectedChildCase = new ChildCase();
        expectedChildCase.setCaseId(caseId);

        Flw expectedFlw = new Flw();
        expectedFlw.setFlwId("89fda0284e008d2e0c980fb13fa0e5bb");

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
}
