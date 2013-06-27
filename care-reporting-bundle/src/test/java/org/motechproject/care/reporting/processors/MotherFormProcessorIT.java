package org.motechproject.care.reporting.processors;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.builder.FormValueElementBuilder;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.domain.measure.NewForm;
import org.motechproject.care.reporting.domain.measure.RegistrationMotherForm;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.motechproject.care.reporting.utils.TestUtils.assertDateIgnoringSeconds;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionEqualsWithIgnore;

public class MotherFormProcessorIT extends SpringIntegrationTest {
    @Autowired
    private MotherFormProcessor motherFormProcessor;

    @Test
    public void shouldParseMotherNewForm() {
        MotherCase motherCase = new MotherCase();
        motherCase.setCaseId("94d5374f-290e-409f-bc57-86c2e4bcc43f");
        template.save(motherCase);

        Flw flw = new Flw();
        flw.setFlwId("89fda0284e008d2e0c980fb13fa0e5bb");
        template.save(flw);

        FormValueElement motherCaseData = new FormValueElementBuilder()
                .addAttribute("case_id", "94d5374f-290e-409f-bc57-86c2e4bcc43f")
                .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                .build();

        CommcareForm newFormData = new CommcareFormBuilder()
                .addMetadata("deviceID", "IUFN6IXAIV7Z1OKJBIWV7WY3C")
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
                .addSubElement("case", motherCaseData)
                .build();

        NewForm expectedForm = new NewForm();
        expectedForm.setDateModified(new DateTime(2012, 7, 21, 12, 2, 59, 923, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());
        expectedForm.setFullName("&#2327;&#2366;&#2351;&#2340;&#2381;&#2352;&#2368; &#2342;&#2375;&#2357;&#2368;");
        expectedForm.setHusbandName("&#2342;&#2367;&#2344;&#2375;&#2358; &#2350;&#2369;&#2326;&#2367;&#2351;&#2366;");
        expectedForm.setHhNumber(165);
        expectedForm.setFamilyNumber(5);
        expectedForm.setDobKnown(false);
        expectedForm.setCaste("other");
        expectedForm.setAgeCalc(null);
        expectedForm.setInstanceId("e34707f8-80c8-4198-bf99-c11c90ba5c98");
        expectedForm.setMotherCase(motherCase);
        expectedForm.setFlw(flw);
        expectedForm.setTimeStart(new DateTime(2012, 7, 21, 11, 59, 31, 76, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());
        expectedForm.setTimeEnd(new DateTime(2012, 7, 21, 12, 2, 59, 923, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());

        NewForm savedForm = (NewForm) motherFormProcessor.parseMotherForm(newFormData);

        assertReflectionEqualsWithIgnore(expectedForm, savedForm, new String[]{"id", "creationTime"});
        assertDateIgnoringSeconds(expectedForm.getCreationTime(), savedForm.getCreationTime());
    }

    @Test
    public void shouldNotSaveIfMotherFormWithSameInstanceIdExist() {
        NewForm persistedForm = new NewForm();
        persistedForm.setInstanceId("e34707f8-80c8-4198-bf99-c11c90ba5c98");
        template.save(persistedForm);

        FormValueElement motherCaseData = new FormValueElementBuilder()
                .addAttribute("case_id", "94d5374f-290e-409f-bc57-86c2e4bcc43f")
                .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                .build();
        CommcareForm newFormData = new CommcareFormBuilder()
                .addMetadata("userID", "89fda0284e008d2e0c980fb13fa0e5bb")
                .addMetadata("instanceId", "e34707f8-80c8-4198-bf99-c11c90ba5c98")
                .addSubElement("case", motherCaseData)
                .addAttribute("xmlns", "http://bihar.commcarehq.org/pregnancy/new")
                .build();

        motherFormProcessor.parseMotherForm(newFormData);

        List<NewForm> newFormsFromDb = template.loadAll(NewForm.class);
        assertEquals(1, newFormsFromDb.size());
        assertEquals(persistedForm, newFormsFromDb.get(0));
    }

    @Test
    public void shouldMarkClosedForm() throws Exception {
        FormValueElement motherCaseData = new FormValueElementBuilder()
                .addAttribute("case_id", "94d5374f-290e-409f-bc57-86c2e4bcc43f")
                .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                .addSubElement("close", new FormValueElement())
                .build();

        CommcareForm commcareForm = new CommcareFormBuilder()
                .addAttribute("xmlns", "http://bihar.commcarehq.org/pregnancy/registration")
                .addSubElement("meta", new FormValueElementBuilder()
                        .addSubElement("userID", "89fda0284e008d2e0c980fb13f96c45a")
                        .build())
                .addSubElement("case", motherCaseData)
                .build();
        RegistrationMotherForm output = (RegistrationMotherForm) motherFormProcessor.parseMotherForm(commcareForm);

        assertTrue(output.getClose());
    }
}
