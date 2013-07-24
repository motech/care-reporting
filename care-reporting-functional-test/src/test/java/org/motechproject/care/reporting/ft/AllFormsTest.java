package org.motechproject.care.reporting.ft;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.ft.asserters.Asserter;
import org.motechproject.care.reporting.ft.reporting.TableName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AllFormsTest extends BaseTestCase {

    private String flwId;
    private String groupId;
    private String caseId;

    @Autowired
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
        asserter.setPlaceholder(placeholderMap);
    }

    @After
    public void tearDown() throws InterruptedException {
        reportingDatabase().deleteMother(caseId);
        reportingDatabase().deleteFLW(flwId);
        reportingDatabase().deleteGroup(groupId);

        mrsDatabase().patients().delete(caseId, true);
    }

    @Test
    public void createNewForm() throws Exception {
        String instanceId = postForm("new_form");
        assertReportingDatabase(TableName.new_form, instanceId, "new_form");
        assertCouchDatabase("mother_after_new");

    }

    @Test
    public void createRegistrationMotherForm() throws Exception {
        String instanceId = postForm("registration_mother_form");
        assertReportingDatabase(TableName.registration_mother_form, instanceId, "registration_mother_form");
        assertCouchDatabase("mother_after_registration");
    }

    @Test
    public void createBPForm() throws Exception {
        String instanceId = postForm("bp_form");
        assertReportingDatabase(TableName.bp_form, instanceId, "bp_form");
        assertCouchDatabase("mother_after_bp");
    }

    private String postForm(String formName){
        final String formUrl = constructRequestTemplateUrl(formName);

        String instanceId = UUID.randomUUID().toString();
        placeholderMap.put("instanceId", instanceId);

        asserter.postForm(formUrl);
        return instanceId;
    }

    private void assertReportingDatabase(String tableName, String instanceId, String expectedFormUrl){
        asserter.verifyTable(tableName, instanceId, constructExpectedUrl("reporting/"+expectedFormUrl));
        asserter.verifyTable(TableName.mother_case, caseId, constructExpectedUrl("reporting/mother_case"));
        asserter.verifyFlwWithoutGroup(flwId, constructExpectedUrl("reporting/flw"), groupId);
    }

    private void assertCouchDatabase(String expectedCouchUrl){
        final String expectedPatientUrl = constructExpectedUrl("couch/"+expectedCouchUrl);
        asserter.verifyCouchPatient(caseId, expectedPatientUrl);
    }

    @Override
    protected String getTestIdentifier() {
        return "allForms";
    }
}
