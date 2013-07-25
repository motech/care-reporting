package org.motechproject.care.reporting.ft.asserters;

import org.motechproject.care.reporting.ft.couch.MRSDatabase;
import org.motechproject.care.reporting.ft.couch.domain.Patient;
import org.motechproject.care.reporting.ft.pages.MotechEndpoint;
import org.motechproject.care.reporting.ft.reporting.ReportingDatabase;
import org.motechproject.care.reporting.ft.reporting.Table;
import org.motechproject.care.reporting.ft.reporting.TableName;
import org.motechproject.care.reporting.ft.utils.PropertyFile;
import org.motechproject.care.reporting.ft.utils.ReflectionUtils;
import org.motechproject.care.reporting.ft.utils.TimedRunnerBreakCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.motechproject.care.reporting.ft.utils.AssertionUtils.assertContainsAll;
import static org.motechproject.care.reporting.ft.utils.ReflectionUtils.reflectionSerialize;

@Component
public class Asserter {

    private Map<String, String> placeholderMap;
    private Map<String, String> header;

    @Autowired
    private ReportingDatabase reportingDatabase;

    @Autowired
    private MRSDatabase mrsDatabase;

    public ReportingDatabase reportingDatabase() {
        return reportingDatabase;
    }

    public MRSDatabase mrsDatabase() {
        return mrsDatabase;
    }

    public void postForm(String requestUrl) {
        MotechEndpoint motechEndpoint = new MotechEndpoint();
        int response = motechEndpoint.postForm(requestUrl, placeholderMap, header);
        assertEquals(200, response);

    }

    public Map<String, Object> verifyTable(String tableName, String instanceId, String expectedResourceUrl) {

        Table table = reportingDatabase().getTable(tableName);

        Map<String, Object> actualForm = table.waitAndGet(instanceId);

        PropertyFile expectedFormValues = new PropertyFile(expectedResourceUrl, placeholderMap);
        assertContainsAll(expectedFormValues.properties(), ReflectionUtils.serializeMap(actualForm));
        return actualForm;
    }

    public void verifyFlwWithoutGroup(String flwId, String expectedFlwUrl, String groupId) {
        Map<String, Object> actualFlw = verifyTable(TableName.flw, flwId, expectedFlwUrl);

        assertNull(reportingDatabase().flwGroup.find(groupId));
        assertNull(reportingDatabase().flwGroupMap.find(actualFlw.get("id")));
    }

    public void verifyCouchPatient(String caseId, String expectedUrl) {
        verifyCouchPatient(caseId, expectedUrl, 1);
    }

    public void verifyCouchPatient(String caseId, String expectedUrl, final int encounterCount) {
        Patient patient = mrsDatabase().patients().waitAndFindByMotechId(caseId, new TimedRunnerBreakCondition() {
            @Override
            public boolean test(Object obj) {
                return obj != null && ((Patient) obj).getEncounters().size() == encounterCount;
            }
        });
        PropertyFile expectedCouchValues = new PropertyFile(expectedUrl, placeholderMap);
        PropertyFile actualCouchValues = PropertyFile.fromString(reflectionSerialize(patient, "patient"));
        for (Map.Entry<String, String> actual : actualCouchValues.properties().entrySet()) {
            System.out.println(actual.getKey() + "=" +actual.getValue());
        }
        assertContainsAll(expectedCouchValues.properties(), actualCouchValues.properties());
    }

    public void setPlaceholder(Map<String, String> placeholder) {
        this.placeholderMap = placeholder;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }
}
