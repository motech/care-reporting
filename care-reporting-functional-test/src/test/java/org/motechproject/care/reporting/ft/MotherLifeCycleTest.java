package org.motechproject.care.reporting.ft;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.ft.asserters.Asserter;
import org.motechproject.care.reporting.ft.reporting.TableName;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MotherLifeCycleTest extends BaseTestCase {

    private String flwId;
    private String groupId;
    private String caseId;
    private Asserter asserter;
    Map<String, String> placeholderMap = new HashMap<>();

    @Before
    public void setUp() {
        caseId = UUID.randomUUID().toString();
        flwId = UUID.randomUUID().toString().replaceAll("-", "");
        groupId = UUID.randomUUID().toString().replaceAll("-", "");
        placeholderMap.put("caseId", caseId);
        placeholderMap.put("ownerId", groupId);
        placeholderMap.put("userId", flwId);
        asserter = new Asserter(placeholderMap);
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
        final String new_form = constructRequestTemplateUrl("new_form");
        final String expectedNewFormUrl = constructExpectedUrl("reporting/new_form");
        final String expectedMotherCaseUrl = constructExpectedUrl("reporting/mother_case");
        final String expectedFlwUrl = constructExpectedUrl("reporting/flw");
        final String expectedPatientUrl = constructExpectedUrl("couch/mother_after_new");

        String instanceId = UUID.randomUUID().toString();
        placeholderMap.put("instanceId", instanceId);

        asserter.postForm(new_form);

        asserter.verifyTable(TableName.new_form, instanceId, expectedNewFormUrl);
        asserter.verifyTable(TableName.mother_case, caseId, expectedMotherCaseUrl);
        asserter.verifyFlwWithoutGroup(flwId, expectedFlwUrl, groupId);

        asserter.verifyCouchPatient(caseId, expectedPatientUrl);

        return instanceId;
    }

    private void postRegistrationFormAndAssert(String newFormInstanceId) {
        final String registration_form = constructRequestTemplateUrl("registration_form");
        final String expectedRegistrationFormUrl = constructExpectedUrl("reporting/registration_form");
        final String expectedMotherCaseUrl = constructExpectedUrl("reporting/mother_case");
        final String expectedFlwUrl = constructExpectedUrl("reporting/flw");
        final String expectedPatientUrl = constructExpectedUrl("couch/mother_after_registration");

        String instanceId = UUID.randomUUID().toString();
        placeholderMap.put("instanceId", instanceId);
        placeholderMap.put("newFormInstanceId", newFormInstanceId);

        asserter.postForm(registration_form);

        asserter.verifyTable(TableName.registration_mother_form, instanceId, expectedRegistrationFormUrl);
        asserter.verifyTable(TableName.mother_case, caseId, expectedMotherCaseUrl);
        asserter.verifyFlwWithoutGroup(flwId, expectedFlwUrl, groupId);

        asserter.verifyCouchPatient(caseId, expectedPatientUrl, 2);
    }

    @Override
    protected String getTestIdentifier() {
        return "createNewAndRegisterMother";
    }
}
