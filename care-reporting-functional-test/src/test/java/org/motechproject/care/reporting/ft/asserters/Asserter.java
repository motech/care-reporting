package org.motechproject.care.reporting.ft.asserters;

import org.motechproject.care.reporting.ft.pages.MotechEndpoint;
import org.motechproject.care.reporting.ft.reporting.ReportingDatabase;
import org.motechproject.care.reporting.ft.reporting.Table;
import org.motechproject.care.reporting.ft.reporting.TableName;
import org.motechproject.care.reporting.ft.utils.PropertyFile;
import org.motechproject.care.reporting.ft.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.motechproject.care.reporting.ft.utils.AssertionUtils.assertContainsAll;

@Component
public class Asserter {

    private Map<String, String> placeholderMap;
    private Map<String, String> header;

    @Autowired
    private ReportingDatabase reportingDatabase;

    public ReportingDatabase reportingDatabase() {
        return reportingDatabase;
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

    public void setPlaceholder(Map<String, String> placeholder) {
        this.placeholderMap = placeholder;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }
}
