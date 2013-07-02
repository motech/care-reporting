package org.motechproject.care.reporting.service;

import junit.framework.Assert;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.builder.FlwBuilder;
import org.motechproject.care.reporting.builder.FlwGroupBuilder;
import org.motechproject.care.reporting.builder.MotherCaseBuilder;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.domain.measure.NewForm;
import org.motechproject.care.reporting.domain.measure.RegistrationChildForm;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.motechproject.care.reporting.utils.TestUtils.*;

public class CareServiceIT extends SpringIntegrationTest {
    @Autowired
    private Service careService;

    @Before
    @After
    public void setUp() {
        template.deleteAll(template.loadAll(FlwGroup.class));
    }

    @Test
    public void shouldSearchForExistingGroupBeforeUpdating() throws Exception {
        FlwGroup toBeUpdatedGroup = new FlwGroupBuilder().groupId("5ba9a0928dde95d187544babf6c0ad24")
                .name("afrisis team 1")
                .domain("care-bihar")
                .awcCode("001")
                .caseSharing(true)
                .reporting(true)
                .build();
        FlwGroup notToBeUpdatedGroup = flwGroupWithNameAndId("5ba9a0928dde95d187544babf6c0af36", "ashok team 1");

        template.saveOrUpdateAll(Arrays.asList(toBeUpdatedGroup, notToBeUpdatedGroup));

        FlwGroup updatedGroup = updatedGroup();

        careService.saveOrUpdateAllByExternalPrimaryKey(FlwGroup.class, Arrays.asList(updatedGroup));

        FlwGroup loadedFlwGroup = template.load(FlwGroup.class, toBeUpdatedGroup.getId());
        FlwGroup unchangedFlwGroup = template.load(FlwGroup.class, notToBeUpdatedGroup.getId());
        assertReflectionEqualsWithIgnore(updatedGroup(), loadedFlwGroup, new String[]{"id", "creationTime", "lastModifiedTime"});
        assertDateIgnoringSeconds(new Date(), loadedFlwGroup.getCreationTime());
        assertDateIgnoringSeconds(new Date(), loadedFlwGroup.getLastModifiedTime());
        assertEquals("ashok team 1", unchangedFlwGroup.getName());
    }

    @Test
    public void shouldSaveNewGroup() throws Exception {
        FlwGroup newGroup = new FlwGroupBuilder().groupId("5ba9a0928dde95d187544babf6c0ad24")
                .name("amir team 1")
                .domain("care-mp")
                .awcCode("007")
                .caseSharing(true)
                .reporting(true)
                .build();

        careService.saveOrUpdateAllByExternalPrimaryKey(FlwGroup.class, Arrays.asList(newGroup));

        List<FlwGroup> flwGroupsFromDb = template.loadAll(FlwGroup.class);
        assertEquals(1, flwGroupsFromDb.size());
        assertReflectionContains(newGroup, flwGroupsFromDb, new String[]{"id"});
    }

    @Test
    public void shouldSearchExistingFlwsAndUpdateFlw() {
        Flw toBeUpdatedFlw = flw("5ba9a0928dde95d187544babf6c0ad24", "FirstName1", flwGroupWithNameAndId("64a9a0928dde95d187544babf6c0ad38", "oldGroupName"));
        Flw notToBeUpdateFlw = flw("5ba9a0928dde95d187544babf6c0ad25", "FirstName2", new FlwGroup());
        template.saveOrUpdateAll(Arrays.asList(toBeUpdatedFlw, notToBeUpdateFlw));

        Flw newFlw = flw("5ba9a0928dde95d187544babf6c0ad24", "FirstName3", flwGroupWithNameAndId("38a9a0928dde95d187544babf6c0ad64", "newGroupName"));
        ArrayList<Flw> flwsToUpdate = new ArrayList<>();
        flwsToUpdate.add(newFlw);

        careService.saveOrUpdateAllByExternalPrimaryKey(Flw.class, flwsToUpdate);

        Flw updatedFlw = template.load(Flw.class, toBeUpdatedFlw.getId());
        assertReflectionEqualsWithIgnore(newFlw, updatedFlw, new String[]{"id", "creationTime", "lastModifiedTime"});
        assertDateIgnoringSeconds(new Date(), updatedFlw.getCreationTime());
        assertDateIgnoringSeconds(new Date(), updatedFlw.getLastModifiedTime());

        Flw unchangedFlw = template.load(Flw.class, notToBeUpdateFlw.getId());
        assertEquals("FirstName2", unchangedFlw.getFirstName());
    }

    @Test
    public void shouldCreateNewFlw() {
        Flw newFlw = flw("5ba9a0928dde95d187544babf6c0ad24", "FirstName1", flwGroupWithNameAndId("64a9a0928dde95d187544babf6c0ad38", "oldGroupName"));

        careService.saveOrUpdateAllByExternalPrimaryKey(Flw.class, Arrays.asList(newFlw));

        List<Flw> flwsFromDb = template.loadAll(Flw.class);
        assertEquals(1, flwsFromDb.size());
        assertReflectionContains(newFlw, flwsFromDb, new String[]{"id"});
    }

    @Test
    public void shouldInsertAnEntityByExternalPrimaryKeyWhenDoesNotExists() throws Exception {
        Flw newFlw = flw("5ba9a0928dde95d187544babf6c0ad24", "FirstName1", flwGroupWithNameAndId("64a9a0928dde95d187544babf6c0ad38", "oldGroupName"));
        careService.saveOrUpdateByExternalPrimaryKey(newFlw);

        List<Flw> flws = template.loadAll(Flw.class);

        assertEquals(1, flws.size());
        assertReflectionContains(newFlw, flws);
    }

    @Test
    public void shouldUpdateEntityByExternalPrimaryKeyWhenExists() {
        String caseId = "case Id";
        ChildCase existingChild = new ChildCase();
        existingChild.setCaseId(caseId);
        existingChild.setName("old Child name");
        template.save(existingChild);

        ChildCase newChild = new ChildCase();
        newChild.setCaseId(caseId);
        String newChildName = "new child name";
        newChild.setName(newChildName);

        careService.saveOrUpdateByExternalPrimaryKey(newChild);

        List<ChildCase> childCases = template.loadAll(ChildCase.class);
        Assert.assertEquals(1, childCases.size());
        Assert.assertEquals(newChildName, childCases.get(0).getName());
    }

    @Test
    public void shouldNotSaveMotherOrChildFormsIfNullAndEmpty() {
        careService.processAndSaveForms(null, new ArrayList<Map<String, String>>());

        List<MotherCase> mothers = template.loadAll(MotherCase.class);
        assertTrue(mothers.isEmpty());

        List<ChildCase> children = template.loadAll(ChildCase.class);
        assertTrue(children.isEmpty());
    }

    @Test
    public void shouldSaveMotherFormForExistingCase() {
        MotherCase motherCase = new MotherCase();
        motherCase.setCaseId("94d5374f-290e-409f-bc57-86c2e4bcc43f");
        template.save(motherCase);
        Flw flw = new Flw();
        flw.setFlwId("89fda0284e008d2e0c980fb13fa0e5bb");
        template.save(flw);
        Map<String, String> motherForm = motherFormValues(motherCase.getCaseId(), flw.getFlwId());

        careService.processAndSaveForms(motherForm, new ArrayList<Map<String, String>>());

        List<MotherCase> persistedMotherCases = template.loadAll(MotherCase.class);
        assertEquals(1, persistedMotherCases.size());
        List<NewForm> newForms = template.loadAll(NewForm.class);
        assertEquals(1, newForms.size());
        NewForm expectedForm = expectedForm(motherCase, flw);
        assertReflectionEqualsWithIgnore(expectedForm, newForms.get(0), new String[]{"id", "creationTime"});
    }

    @Test
    public void shouldSaveMotherFormAndCreateNewCase() {
        Map<String, String> motherForm = motherFormValues("94d5374f-290e-409f-bc57-86c2e4bcc43f", "89fda0284e008d2e0c980fb13fa0e5bb");

        careService.processAndSaveForms(motherForm, new ArrayList<Map<String, String>>());

        List<MotherCase> persistedMotherCases = template.loadAll(MotherCase.class);
        assertEquals(1, persistedMotherCases.size());
        List<NewForm> newForms = template.loadAll(NewForm.class);
        assertEquals(1, newForms.size());
        NewForm expectedForm = expectedForm(new MotherCaseBuilder().caseId("94d5374f-290e-409f-bc57-86c2e4bcc43f").build(), new FlwBuilder().flwId("89fda0284e008d2e0c980fb13fa0e5bb").build());
        assertReflectionEqualsWithIgnore(expectedForm, newForms.get(0), new String[]{"id", "creationTime", "flw", "motherCase"});
        assertEquals("94d5374f-290e-409f-bc57-86c2e4bcc43f", newForms.get(0).getMotherCase().getCaseId());
        assertEquals("89fda0284e008d2e0c980fb13fa0e5bb", newForms.get(0).getFlw().getFlwId());
    }

    @Test
    public void shouldSaveChildForm() {
        ArrayList<Map<String, String>> childFormValues = new ArrayList<>();
        HashMap<String, String> childFormValue = new HashMap<>();
        childFormValue.put("caseId", "94d5374f-290e-409f-bc57-86c2e4bcc43f");
        childFormValue.put("xmlns", "http://bihar.commcarehq.org/pregnancy/registration");
        childFormValue.put("childName", "suraj");
        childFormValue.put("birthStatus", "healthy");
        childFormValue.put("instanceId", "ff2eb090-03a9-4f23-afed-cf6012784c55");
        childFormValue.put("flw", "89fda0284e008d2e0c980fb13fa0e5bb");
        childFormValue.put("timeStart","2013-03-03T10:31:51.045+05:30");
        childFormValue.put("timeEnd","2013-03-03T10:38:52.804+05:30");
        childFormValue.put("dateModified","2013-03-03T10:38:52.804+05:30");

        childFormValues.add(childFormValue);

        careService.processAndSaveForms(null, childFormValues);

        List<RegistrationChildForm> registrationChildForms = template.loadAll(RegistrationChildForm.class);
        assertEquals(1,registrationChildForms.size());

        RegistrationChildForm expectedForm = getExpectedForm("94d5374f-290e-409f-bc57-86c2e4bcc43f");

        assertReflectionEqualsWithIgnore(expectedForm, registrationChildForms.get(0), new String[]{"id", "flw", "childCase", "creationTime"});

        List<Flw> flws = template.loadAll(Flw.class);
        assertEquals(1, flws.size());
        assertEquals("89fda0284e008d2e0c980fb13fa0e5bb", flws.get(0).getFlwId());
    }

    @Test
    public void shouldIgnoreMotherFormsWithoutXmlns() {
        Map<String, String> motherFormValuesWithoutXmlns = motherFormValues("94d5374f-290e-409f-bc57-86c2e4bcc43f", "89fda0284e008d2e0c980fb13fa0e5bb");
        motherFormValuesWithoutXmlns.remove("xmlns");

        try {
            careService.processAndSaveForms(motherFormValuesWithoutXmlns, new ArrayList<Map<String, String>>());
        } catch (Exception e) {
            fail("The exception should not have been thrown: " + e.getMessage());
        }
    }

    @Test
    public void shouldNotThrowExceptionIfXmlnsNotRecognized() {
        Map<String, String> motherFormValuesWithoutXmlns = motherFormValues("94d5374f-290e-409f-bc57-86c2e4bcc43f", "89fda0284e008d2e0c980fb13fa0e5bb");
        motherFormValuesWithoutXmlns.put("xmlns", "randomurl");

        try {
            careService.processAndSaveForms(motherFormValuesWithoutXmlns, new ArrayList<Map<String, String>>());
        } catch (Exception e) {
            fail("The exception should not have been thrown: " + e.getMessage());
        }
    }

    private RegistrationChildForm getExpectedForm(String caseId) {
        ChildCase expectedChildCase = new ChildCase();
        expectedChildCase.setCaseId(caseId);

        Flw expectedFlw = new Flw();
        expectedFlw.setFlwId("89fda0284e008d2e0c980fb13fa0e5bb");

        RegistrationChildForm expectedChildForm = new RegistrationChildForm();
        expectedChildForm.setChildCase(expectedChildCase);
        expectedChildForm.setFlw(expectedFlw);
        expectedChildForm.setDateModified(new DateTime(2013, 3, 3, 10, 38, 52, 804, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());
        expectedChildForm.setChildName("suraj");
        expectedChildForm.setBirthStatus("healthy");
        expectedChildForm.setInstanceId("ff2eb090-03a9-4f23-afed-cf6012784c55");
        expectedChildForm.setTimeStart(new DateTime(2013, 3, 3, 10, 31, 51, 45, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());
        expectedChildForm.setTimeEnd(new DateTime(2013, 3, 3, 10, 38, 52, 804, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate());

        return expectedChildForm;
    }

    private Map<String, String> motherFormValues(String caseId, String flwId) {
        Map<String, String> motherForm = new HashMap<>();
        motherForm.put("xmlns", "http://bihar.commcarehq.org/pregnancy/new");
        motherForm.put("dateModified", "2012-07-21T12:02:59.923+05:30");
        motherForm.put("fullName", "&#2327;&#2366;&#2351;&#2340;&#2381;&#2352;&#2368; &#2342;&#2375;&#2357;&#2368;");
        motherForm.put("husbandName", "&#2342;&#2367;&#2344;&#2375;&#2358; &#2350;&#2369;&#2326;&#2367;&#2351;&#2366;");
        motherForm.put("hhNumber", "165");
        motherForm.put("familyNumber", "5");
        motherForm.put("dobKnown", "no");
        motherForm.put("caste", "other");
        motherForm.put("ageCalc", null);
        motherForm.put("instanceId", "e34707f8-80c8-4198-bf99-c11c90ba5c98");
        motherForm.put("motherCase", caseId);
        motherForm.put("flw", flwId);
        motherForm.put("timeStart", "2012-07-21T11:59:31.076+05:30");
        motherForm.put("timeEnd", "2012-07-21T12:02:59.923+05:30");
        return motherForm;
    }

    private NewForm expectedForm(MotherCase motherCase, Flw flw) {
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
        return expectedForm;
    }

    private Flw flw(String flwId, String firstName, final FlwGroup flwGroup) {
        return new FlwBuilder()
                .flwId(flwId)
                .firstName(firstName)
                .flwGroups(new HashSet<FlwGroup>() {{
                    add(flwGroup);
                }})
                .build();
    }

    private FlwGroup updatedGroup() {
        return new FlwGroupBuilder().groupId("5ba9a0928dde95d187544babf6c0ad24")
                .name("danny team 1")
                .domain("care-orrisa")
                .awcCode("002")
                .caseSharing(false)
                .reporting(false)
                .build();
    }

    private FlwGroup flwGroupWithNameAndId(String groupId, String name) {
        return new FlwGroupBuilder().groupId(groupId).name(name).build();
    }
}
