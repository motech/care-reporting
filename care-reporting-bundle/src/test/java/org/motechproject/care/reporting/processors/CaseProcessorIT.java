package org.motechproject.care.reporting.processors;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.builder.CaseEventBuilder;
import org.motechproject.care.reporting.builder.FlwBuilder;
import org.motechproject.care.reporting.builder.FlwGroupBuilder;
import org.motechproject.care.reporting.builder.MotherCaseBuilder;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.repository.SpringIntegrationTest;
import org.motechproject.commcare.events.CaseEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionEqualsWithIgnore;

public class CaseProcessorIT extends SpringIntegrationTest {

    public static final Date JAN_01 = DateTime.parse("2013-01-01").toDate();
    private final String caseId = "97e56523-5820-414a-83c2-bfcb6dcf4db3";
    private final String userId = "89fda0284e008d2e0c980fb13f989136";
    private final String ownerId = "89fda0284e008d2e0c980fb13fbb49e6";
    @Autowired
    private MotherCaseProcessor motherCaseProcessor;
    private FlwGroup flwGroup;
    private Flw flw;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        flwGroup = new FlwGroup();
        flwGroup.setGroupId(ownerId);
        flw = new Flw();
        flw.setFlwId(userId);
        flwGroup.getFlws().add(flw);
        template.save(flwGroup);
    }

    @Test
    public void shouldParseMotherCase() {
        CaseEvent caseEvent = new CaseEventBuilder(caseId)
                .withUserId(userId)
                .withCaseType("cc_bihar_pregnancy")
                .withOwnerId(ownerId)
                .withDateModified("2013-01-01T12:00:23.923Z")
                .build();

        motherCaseProcessor.process(caseEvent);

        List<MotherCase> motherCases = template.loadAll(MotherCase.class);
        assertEquals(1, motherCases.size());
        MotherCase expectedMotherCase = new MotherCaseBuilder()
                .clear()
                .caseId(caseId)
                .flw(new FlwBuilder().flwId(userId).build())
                .flwGroup(new FlwGroupBuilder().groupId(ownerId).build())
                .dateModified(DateTime.parse("2013-01-01T12:00:23.923Z").toDate())
                .build();

        assertReflectionEqualsWithIgnore(expectedMotherCase, motherCases.get(0), "id", "flw", "flwGroup", "creationTime", "lastModifiedTime");
        assertReflectionEqualsWithIgnore(flw, motherCases.get(0).getFlw(), "id", "flwGroup", "creationTime", "lastModifiedTime");
        assertReflectionEqualsWithIgnore(flwGroup, motherCases.get(0).getFlwGroup(), "id", "flws", "creationTime", "lastModifiedTime");
    }

    @Test
    public void shouldNotUpdateCloseFieldsOnSubsequentUpdates() {
        MotherCase motherCase = new MotherCaseBuilder()
                .caseId(caseId)
                .caseName("User 1")
                .flw(userId)
                .dateModified(JAN_01)
                .close()
                .build();
        template.save(motherCase);

        CaseEvent updatedCase = new CaseEventBuilder(caseId)
                .withCaseName("User 2")
                .withCaseType("cc_bihar_pregnancy")
                .withDateModified("2013-01-01T12:00:23.923Z")
                .build();

        motherCaseProcessor.process(updatedCase);

        List<MotherCase> motherCases = template.loadAll(MotherCase.class);
        assertEquals(1, motherCases.size());
        MotherCase updatedMotherCase = motherCases.get(0);
        assertTrue(updatedMotherCase.getClosed());
        assertEquals(userId, updatedMotherCase.getClosedBy().getFlwId());
        assertEquals(JAN_01, updatedMotherCase.getClosedOn());
        assertEquals("User 2", updatedCase.getCaseName());
    }
}