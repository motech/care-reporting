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
    private String child1caseId;

    @Autowired
    private Asserter asserter;

    Map<String, String> placeholderMap = new HashMap<>();

    @Before
    public void setUp() {
        caseId = UUID.randomUUID().toString();
        child1caseId = UUID.randomUUID().toString();
        flwId = UUID.randomUUID().toString().replaceAll("-", "");
        groupId = UUID.randomUUID().toString().replaceAll("-", "");
        placeholderMap.put("caseId", caseId);
        placeholderMap.put("child1caseId", child1caseId);
        placeholderMap.put("ownerId", groupId);
        placeholderMap.put("userId", flwId);
        asserter.setPlaceholder(placeholderMap);
    }

    @After
    public void tearDown() throws InterruptedException {
        reportingDatabase().deleteMother(caseId);
        reportingDatabase().deleteChild(child1caseId);
        reportingDatabase().deleteFLW(flwId);
        reportingDatabase().deleteGroup(groupId);

        mrsDatabase().patients().delete(caseId, true);
        mrsDatabase().patients().delete(child1caseId, true);
    }

    @Test
    public void createNewForm() throws Exception {
        String instanceId = postForm("new_form");
        assertReportingDatabaseWithMotherAndFlw(TableName.new_form, instanceId, "new_form");
        assertCouchDatabaseForMother("mother_after_new");

    }

    @Test
    public void createRegistrationMotherForm() throws Exception {
        String instanceId = postForm("registration_mother_form");
        assertReportingDatabaseWithMotherAndFlw(TableName.registration_mother_form, instanceId, "registration_mother_form");
        assertCouchDatabaseForMother("mother_after_registration");
    }

    @Test
    public void createBPForm() throws Exception {
        String instanceId = postForm("bp_form");
        assertReportingDatabaseWithMotherAndFlw(TableName.bp_form, instanceId, "bp_form");
        assertCouchDatabaseForMother("mother_after_bp");
    }

    @Test
    public void createCFForm() throws Exception {
        String instanceId = postForm("cf_form");
        assertReportingDatabaseWithMotherAndFlw(TableName.cf_mother_form, instanceId, "cf_mother_form");
        assertReportingDatabaseWithChild(TableName.cf_child_form, instanceId, "cf_child_form");
        assertCouchDatabaseForMother("mother_after_cf");
        assertCouchDatabaseForChild("child_after_cf");
    }

    @Test
    public void createCloseForm() throws Exception {
        String instanceId = postForm("close_form");
        assertReportingDatabaseWithMotherAndFlw(TableName.close_mother_form, instanceId, "close_mother_form");
        assertReportingDatabaseWithChild(TableName.close_child_form, instanceId, "close_child_form");
        assertCouchDatabaseForMother("mother_after_close");
        assertCouchDatabaseForChild("child_after_close");
    }

    private String postForm(String formName){
        final String formUrl = constructRequestTemplateUrl(formName);

        String instanceId = UUID.randomUUID().toString();
        placeholderMap.put("instanceId", instanceId);

        asserter.postForm(formUrl);
        return instanceId;
    }

    private void assertReportingDatabaseWithMotherAndFlw(String tableName, String instanceId, String expectedFormUrl){
        asserter.verifyTable(tableName, instanceId, constructExpectedUrl("reporting/"+expectedFormUrl));
        asserter.verifyTable(TableName.mother_case, caseId, constructExpectedUrl("reporting/mother_case"));
        asserter.verifyFlwWithoutGroup(flwId, constructExpectedUrl("reporting/flw"), groupId);
    }

    private void assertReportingDatabaseWithChild(String tableName, String instanceId, String expectedFormUrl){
        asserter.verifyTable(tableName, instanceId, constructExpectedUrl("reporting/"+expectedFormUrl));
        asserter.verifyTable(TableName.child_case, child1caseId, constructExpectedUrl("reporting/child_case"));
    }

    private void assertCouchDatabaseForMother(String expectedCouchUrl){
        assertCouchDatabase(caseId, expectedCouchUrl);
    }

    private void assertCouchDatabaseForChild(String expectedCouchUrl){
        assertCouchDatabase(child1caseId, expectedCouchUrl);
    }

    private void assertCouchDatabase(String id, String expectedCouchUrl){
        final String expectedPatientUrl = constructExpectedUrl("couch/"+expectedCouchUrl);
        asserter.verifyCouchPatient(id, expectedPatientUrl);
    }

    @Override
    protected String getTestIdentifier() {
        return "allForms";
    }
}
