package org.motechproject.care.reporting.processors;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.builder.FormValueElementBuilder;
import org.motechproject.care.reporting.domain.measure.MoveBeneficiaryForm;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class FormProcessorIT extends SpringIntegrationTest {

    @Autowired
    private FormProcessor formProcessor;

    @Test
    public void shouldSaveMotherForMoveBeneficiaryForm() {
        String motherCaseId = "11d5374f-290e-409f-bc57-86c2e4bcc43f";
        String flwId = "12fda0284e008d2e0c980fb13fa0e5bb";

        FormValueElement motherCaseData = new FormValueElementBuilder()
                .addAttribute("case_id", motherCaseId)
                .addAttribute("date_modified", "2012-07-21T12:04:00.923+05:30")
                .addAttribute("user_id", flwId)
                .build();

        String instanceId = "194707f8-80c8-4198-bf99-c11c90ba5c98";
        CommcareForm moveBeneficiaryForm = new CommcareFormBuilder()
                .addMetadata("deviceID", "AUFN6IXAIV7Z1OKJBIWV7WY3C")
                .addMetadata("time_start", "2012-07-21T11:59:31.076+05:30")
                .addMetadata("time_end", "2012-07-21T12:02:59.923+05:30")
                .addMetadata("username", "username")
                .addMetadata("userID", flwId)
                .addMetadata("instanceId", instanceId)

                .addAttribute("uiVersion", "1")
                .addAttribute("version", "1")
                .addAttribute("name", "Move Beneficiary")
                .addAttribute("xmlns", "http://bihar.commcarehq.org/tools/move_beneficiary")

                .addSubElement("confirm_move", "yes")
                .addSubElement("new_ward", "123")
                .addSubElement("new_awcc", "1000")
                .addSubElement("confirm_again", "no")
                .addSubElement("case", motherCaseData)
                .build();

        formProcessor.process(moveBeneficiaryForm);

        List<MoveBeneficiaryForm> forms = template.loadAll(MoveBeneficiaryForm.class);
        assertEquals(1, forms.size());
        MoveBeneficiaryForm moveForm = forms.get(0);

        assertTrue(moveForm.getConfirmMove());
        assertEquals(new Integer(123), moveForm.getNewWard());
        assertEquals(new Integer(1000), moveForm.getNewAwcc());
        assertFalse(moveForm.getConfirmAgain());
        assertEquals(motherCaseId, moveForm.getMotherCase().getCaseId());
        assertEquals(instanceId, moveForm.getInstanceId());
        assertEquals(flwId, moveForm.getFlw().getFlwId());
        assertEquals(DateTime.parse("2012-07-21T11:59:31.076+05:30").toDate(), moveForm.getTimeStart());
        assertEquals(DateTime.parse("2012-07-21T12:02:59.923+05:30").toDate(), moveForm.getTimeEnd());
        assertEquals(DateTime.parse("2012-07-21T12:04:00.923+05:30").toDate(), moveForm.getDateModified());
        assertNotNull(moveForm.getCreationTime());

    }
}
