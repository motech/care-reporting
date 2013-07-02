package org.motechproject.care.reporting.processors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.junit.Test;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.builder.FormValueElementBuilder;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.measure.DeathChildForm;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChildFormProcessorIT extends SpringIntegrationTest {
    @Autowired
    private ChildFormProcessor childFormProcessor;

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
                .addSubElement("eating", "no")
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

        List<Map<String, String>> childFieldValues = childFormProcessor.parseChildForms(ebfForm);
        assertEquals(2, childFieldValues.size());

        Map<String, String> childValues1 = findWithCaseId("3e8998ce-b19f-4fa7-b1a1-721b6951e3cf", childFieldValues);

        assertEquals("3e8998ce-b19f-4fa7-b1a1-721b6951e3cf", childValues1.get("caseId"));
        assertEquals("2013-03-03T10:38:52.804+05:30", childValues1.get("dateModified"));
        assertEquals("89fda0284e008d2e0c980fb13fa0e5bb", childValues1.get("flw"));
        assertEquals("no", childValues1.get("nameUpdate"));
        assertEquals(null, childValues1.get("childName"));
        assertEquals("yes", childValues1.get("eating"));

        Map<String, String> childValues2 = findWithCaseId("59ab28e0-2d2d-4bc7-933f-09dcacf70d61", childFieldValues);
        assertEquals("59ab28e0-2d2d-4bc7-933f-09dcacf70d61", childValues2.get("caseId"));
        assertEquals("2013-03-03T10:38:52.804+05:30", childValues2.get("dateModified"));
        assertEquals("89fda0284e008d2e0c980fb13fa0e5bb", childValues2.get("flw"));
        assertEquals("no", childValues2.get("nameUpdate"));
        assertEquals(null, childValues2.get("childName"));
        assertEquals("no", childValues2.get("eating"));
    }

    @Test
    public void shouldReturnEmptyListIfChildInfoNotExistsInForm() {
        CommcareForm form = new CommcareFormBuilder().build();

        List<Map<String, String>> serializables = childFormProcessor.parseChildForms(form);

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
        Map<String, String> output = childFormProcessor.parseChildForms(ebfForm).get(0);

        assertEquals("true", output.get("close"));
    }

    @Test
    public void shouldNotSaveChildFormIfFormWithSameInstanceIdAndCaseIdExistsAlready() {
        String instanceId = "e34707f8-80c8-4198-bf99-c11c90ba5c98";
        String caseId = "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf";

        ChildCase persistedChildCase = new ChildCase();
        persistedChildCase.setCaseId(caseId);

        DeathChildForm persistedForm = new DeathChildForm();
        persistedForm.setInstanceId(instanceId);
        persistedForm.setChildCase(persistedChildCase);
        template.save(persistedForm);

        FormValueElement childCase1Data = new FormValueElementBuilder()
                .addAttribute("case_id", caseId)
                .addAttribute("date_modified", "2013-03-03T10:38:52.804+05:30")
                .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                .build();

        FormValueElement childCase1Details = new FormValueElementBuilder()
                .addSubElement("cid", "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf")
                .addSubElement("index", "0")
                .addSubElement("case", childCase1Data)
                .addSubElement("close", new FormValueElement())
                .build();

        CommcareForm deathForm = new CommcareFormBuilder().addMetadata("deviceID", "IUFN6IXAIV7Z1OKJBIWV7WY3C")
                .addMetadata("userID", "89fda0284e008d2e0c980fb13fa0e5bb")
                .addMetadata("instance_id", instanceId)
                .addAttribute("uiVersion", "1")
                .addAttribute("version", "1")
                .addAttribute("name", "EBF")
                .addAttribute("xmlns", "http://bihar.commcarehq.org/pregnancy/death")
                .addSubElement("num_children", "1")
                .addSubElement("child_info", childCase1Details)
                .build();

        childFormProcessor.parseChildForms(deathForm);

        List<DeathChildForm> deathChildForms = template.loadAll(DeathChildForm.class);
        assertEquals(1, deathChildForms.size());
        assertEquals(persistedForm, deathChildForms.get(0));
    }

    private Map<String, String> findWithCaseId(final String caseId, List<Map<String, String>> childFieldValues) {
        return (Map<String, String>) CollectionUtils.find(childFieldValues, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                return caseId.equals(((Map<String, String>) object).get("caseId"));
            }
        });
    }

}
