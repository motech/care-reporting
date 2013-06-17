package org.motechproject.care.reporting.ft;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.ft.couch.domain.Patient;
import org.motechproject.care.reporting.ft.pages.FormPage;
import org.motechproject.care.reporting.ft.utils.PropertyFile;
import org.motechproject.care.reporting.ft.utils.TimedRunnerBreakCondition;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.motechproject.care.reporting.ft.utils.AssertionUtils.assertContainsAll;
import static org.motechproject.care.reporting.ft.utils.ReflectionUtils.reflectionSerialize;

public class MotherLifeCycleTest extends FormTestCase {

    private String flwId;
    private String groupId;
    private String caseId;

    @Before
    public void setUp() {
        caseId = UUID.randomUUID().toString();
        flwId = UUID.randomUUID().toString().replaceAll("-", "");
        groupId = UUID.randomUUID().toString().replaceAll("-", "");
    }

    @After
    public void tearDown() throws InterruptedException {
        reportingDatabase().deleteMother(caseId);
        reportingDatabase().deleteFLW(flwId);
        reportingDatabase().deleteGroup(groupId);

        mrsDatabase().patients().detete(caseId, true);
    }


    @Test
    public void createNewAndRegisterMother() throws Exception {
        String newFormInstanceId = postNewFormAndAssert();
        postRegistrationFormAndAssert(newFormInstanceId);
    }

    private String postNewFormAndAssert() {
        Map<String, String> placeholderMap = new HashMap<String, String>();
        placeholderMap.put("caseId", caseId);
        placeholderMap.put("ownerId", groupId);
        placeholderMap.put("userId", flwId);

        String instanceId = UUID.randomUUID().toString();
        placeholderMap.put("instanceId", instanceId);

        FormPage newFormPage = new FormPage();
        int response = newFormPage.post(constructRequestTemplateUrl("new_form"), placeholderMap);
        assertEquals(200, response);

        Map<String, Object> actualForm = reportingDatabase().newForm.waitAndGet(instanceId);
        PropertyFile expectedFormValues = new PropertyFile(constructExpectedUrl("reporting/new_form"), placeholderMap);
        assertContainsAll(expectedFormValues.asPrimitiveMap(), actualForm);

        Map<String, Object> actualMotherCase = reportingDatabase().motherCase.waitAndGet(caseId);
        PropertyFile expectedCaseValues = new PropertyFile(constructExpectedUrl("reporting/mother_case"), placeholderMap);
        assertContainsAll(expectedCaseValues.asPrimitiveMap(), actualMotherCase);

        Map<String, Object> actualFlw = reportingDatabase().flw.waitAndGet(flwId);
        PropertyFile expectedFLWValues = new PropertyFile(constructExpectedUrl("reporting/flw"), placeholderMap);
        assertContainsAll(expectedFLWValues.asPrimitiveMap(), actualFlw);


        assertNull(reportingDatabase().flwGroup.find(groupId));
        assertNull(reportingDatabase().flwGroupMap.find(actualFlw.get("id")));

        Patient patient = mrsDatabase().patients().waitAndFindByMotechId(caseId);
        PropertyFile expectedCouchValues = new PropertyFile(constructExpectedUrl("couch/mother_after_new"), placeholderMap);
        PropertyFile actualCouchValues = PropertyFile.fromString(reflectionSerialize(patient, "patient"));
        assertContainsAll(expectedCouchValues.properties(), actualCouchValues.properties());
        return instanceId;
    }

    private void postRegistrationFormAndAssert(String newFormInstanceId) {
        Map<String, String> placeholderMap = new HashMap<String, String>();
        placeholderMap.put("caseId", caseId);
        placeholderMap.put("ownerId", groupId);
        placeholderMap.put("userId", flwId);

        String instanceId = UUID.randomUUID().toString();
        placeholderMap.put("instanceId", instanceId);
        placeholderMap.put("newFormInstanceId", newFormInstanceId);

        FormPage newFormPage = new FormPage();
        int response = newFormPage.post(constructRequestTemplateUrl("registration_form"), placeholderMap);
        assertEquals(200, response);

        Map<String, Object> actualForm = reportingDatabase().registrationMotherForm.waitAndGet(instanceId);
        PropertyFile expectedFormValues = new PropertyFile(constructExpectedUrl("reporting/registration_form"), placeholderMap);
        assertContainsAll(expectedFormValues.asPrimitiveMap(), actualForm);

        Map<String, Object> actualMotherCase = reportingDatabase().motherCase.waitAndGet(caseId);
        PropertyFile expectedCaseValues = new PropertyFile(constructExpectedUrl("reporting/mother_case"), placeholderMap);
        assertContainsAll(expectedCaseValues.asPrimitiveMap(), actualMotherCase);

        Map<String, Object> actualFlw = reportingDatabase().flw.waitAndGet(flwId);
        PropertyFile expectedFLWValues = new PropertyFile(constructExpectedUrl("reporting/flw"), placeholderMap);
        assertContainsAll(expectedFLWValues.asPrimitiveMap(), actualFlw);

        assertNull(reportingDatabase().flwGroup.find(groupId));
        assertNull(reportingDatabase().flwGroupMap.find(actualFlw.get("id")));

        Patient patient = mrsDatabase().patients().waitAndFindByMotechId(caseId, new TimedRunnerBreakCondition() {
            @Override
            public boolean test(Object obj) {
                return obj != null && ((Patient) obj).getEncounters().size() == 2;
            }
        });

        PropertyFile expectedCouchValues = new PropertyFile(constructExpectedUrl("couch/mother_after_registration"), placeholderMap);
        PropertyFile actualCouchValues = PropertyFile.fromString(reflectionSerialize(patient, "patient"));
        assertContainsAll(expectedCouchValues.properties(), actualCouchValues.properties());

    }

    @Override
    protected String getTestIdentifier() {
        return "createNewAndRegisterMother";
    }
}
