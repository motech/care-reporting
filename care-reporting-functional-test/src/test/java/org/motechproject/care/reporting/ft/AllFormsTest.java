package org.motechproject.care.reporting.ft;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
    private String instanceId;
    private String child1caseId;
    private String receivedOn, receivedOnIST;

    @Autowired
    private Asserter asserter;

    private Map<String, String> placeholderMap = new HashMap<>();
    private Map<String, String> headerMap = new HashMap<>();

    @Before
    public void setUp() {
        caseId = UUID.randomUUID().toString();
        child1caseId = UUID.randomUUID().toString();
        flwId = UUID.randomUUID().toString().replaceAll("-", "");
        groupId = UUID.randomUUID().toString().replaceAll("-", "");
        receivedOn = "2012-07-21T10:10:20.000Z";
        receivedOnIST = "2012-07-21 15:40:20.0";
        placeholderMap.put("caseId", caseId);
        placeholderMap.put("child1caseId", child1caseId);
        placeholderMap.put("ownerId", groupId);
        placeholderMap.put("userId", flwId);
        placeholderMap.put("receivedOn", receivedOn);
        placeholderMap.put("receivedOnIST", receivedOnIST);

        asserter.setPlaceholder(placeholderMap);

        headerMap.put("received-on",receivedOn);
        asserter.setHeader(headerMap);
    }

    @After
    public void tearDown() throws InterruptedException {
        reportingDatabase().deleteMother(caseId);
        reportingDatabase().deleteChild(child1caseId);
        reportingDatabase().deleteForm(instanceId);
        reportingDatabase().deleteFLW(flwId);
        reportingDatabase().deleteGroup(groupId);
    }

    @Test
    public void createNewForm() throws Exception {
        testForm("new");
    }

    @Test
    public void createRegistrationForm() throws Exception {
        testFormWithChild("registration");
    }

    @Test
    public void createBPForm() throws Exception {
        testForm("bp");
    }

    @Test
    public void createCFForm() throws Exception {
        testFormWithChild("cf");
    }

    @Test
    public void createCloseForm() throws Exception {
        testFormWithChild("close");
    }

    @Test
    public void createDeathForm() throws Exception {
        testFormWithChild("death");
    }

    @Test
    public void createDeliveryForm() throws Exception {
        testFormWithChild("delivery");
    }

    @Test
    public void createEbfForm() throws Exception {
        testFormWithChild("ebf");
    }

    @Test
    public void createPncForm() throws Exception {
        testFormWithChild("pnc");
    }

    @Test
    public void createReferForm() throws Exception {
        testFormWithChild("refer");
    }

    @Test
    public void createUiForm() throws Exception {
        testFormWithChild("ui");
    }

    @Test
    public void createAbortForm() throws Exception {
        testForm("abort");
    }

    @Test
    public void createMiForm() throws Exception {
        testForm("mi");
    }

    @Test
    public void createMoForm() throws Exception {
        testForm("mo");
    }

    @Test
    public void createMotherEditForm() throws Exception {
        testForm("mother_edit");
    }

    @Test
    public void createMoveBeneficiaryForm() throws Exception {
        testForm("move_beneficiary");
    }

    @Test
    public void createGrowthMonitoringChildForm() throws Exception {
        testFormWithChildOnly("growth_monitoring");
    }

    @Test
    public void createAwwRegForm() throws Exception {
        testFormWithChild("aww_reg");
    }

    @Test
    public void createGrowthMonitoring1ChildForm() throws Exception {
        testFormWithChildOnly("aww_growth_monitoring_1");
    }

    @Test
    public void createGrowthMonitoring2ChildForm() throws Exception {
        testFormWithChildOnly("aww_growth_monitoring_2");
    }

    @Test
    public void createAwwThrChildForm() throws Exception {
        testFormWithChildOnly("aww_thr");
    }

    @Test
    public void createAwwThrMotherForm() throws Exception {
        testForm("aww_thr_mother");
    }

    @Test
    public void createAwwCloseChildForm() throws Exception {
        testFormWithChildOnly("aww_close");
    }

    @Test
    public void createAwwEditChildForm() throws Exception {
        testFormWithChildOnly("aww_edit");
    }

    @Test
    public void createAwwUpdateVaccinationsChildForm() throws Exception {
        testFormWithChildOnly("aww_update_vaccinations");
    }

    @Test
    public void createAwwPreschoolActivitiesForm() throws Exception {
        testFormWithNoCaseAndMultipleChildren("aww_preschool_activities");
    }

    private void testFormWithChild(String formName) throws Exception {
        instanceId = postForm(formName + "_form");
        assertReportingDatabaseWithMotherAndFlw(formName + "_mother_form", instanceId, formName + "_mother_form");
        assertReportingDatabaseWithChild(formName + "_child_form", instanceId, formName + "_child_form");
    }

    private void testForm(String formName) throws Exception {
        instanceId = postForm(formName + "_form");
        assertReportingDatabaseWithMotherAndFlw(formName + "_form", instanceId, formName + "_form");
    }

    private void testFormWithChildOnly(String formName) throws Exception {
        instanceId = postForm(formName + "_child_form");
        assertReportingDatabaseWithChildAndFlw(formName + "_child_form", instanceId, formName + "_child_form");
    }

    private void testFormWithNoCaseAndMultipleChildren(String formName) throws Exception {
        instanceId = postForm(formName + "_form");
        assertReportingDatabaseWithNoCaseMultipleChildrenAndFlw(formName, instanceId, formName);
    }

    private String postForm(String formName){
        final String formUrl = constructRequestTemplateUrl(formName);

        instanceId = UUID.randomUUID().toString();
        placeholderMap.put("instanceId", instanceId);

        asserter.postForm(formUrl);
        return instanceId;
    }

    private void assertReportingDatabaseWithNoCaseMultipleChildrenAndFlw(String tableName, String instanceId,
                                                                         String expectedFormUrl) {
        asserter.verifyTable(tableName + "_form", instanceId,
                constructExpectedUrl("reporting/" + expectedFormUrl + "_form"));
        asserter.verifyTable(tableName + "_child_form", instanceId,
                constructExpectedUrl("reporting/" + expectedFormUrl + "_child_form"));
        asserter.verifyFlwWithoutGroup(flwId, constructExpectedUrl("reporting/flw"), groupId);
    }

    private void assertReportingDatabaseWithMotherAndFlw(String tableName, String instanceId, String expectedFormUrl){
        asserter.verifyTable(tableName, instanceId, constructExpectedUrl("reporting/"+expectedFormUrl));
        asserter.verifyTable(TableName.mother_case, caseId, constructExpectedUrl("reporting/mother_case"));
        asserter.verifyFlwWithoutGroup(flwId, constructExpectedUrl("reporting/flw"), groupId);
    }

    private void assertReportingDatabaseWithChildAndFlw(String tableName, String instanceId, String expectedFormUrl){
        asserter.verifyTable(tableName, instanceId, constructExpectedUrl("reporting/"+expectedFormUrl));
        asserter.verifyTable(TableName.child_case, child1caseId, constructExpectedUrl("reporting/child_case"));
        asserter.verifyFlwWithoutGroup(flwId, constructExpectedUrl("reporting/flw"), groupId);
    }

    private void assertReportingDatabaseWithChild(String tableName, String instanceId, String expectedFormUrl){
        asserter.verifyTable(tableName, instanceId, constructExpectedUrl("reporting/"+expectedFormUrl));
        asserter.verifyTable(TableName.child_case, child1caseId, constructExpectedUrl("reporting/child_case"));
    }

    @Override
    protected String getTestIdentifier() {
        return "allForms";
    }
}
