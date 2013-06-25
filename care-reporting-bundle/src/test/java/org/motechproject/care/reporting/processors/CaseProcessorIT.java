package org.motechproject.care.reporting.processors;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.builder.*;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
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
    private final String dateModified = "2013-06-13";
    @Autowired
    private CaseProcessor caseProcessor;
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
                .build();

        caseProcessor.process(caseEvent);

        List<MotherCase> motherCases = template.loadAll(MotherCase.class);
        assertEquals(1, motherCases.size());
        MotherCase expectedMotherCase = new MotherCaseBuilder()
                .clear()
                .caseId(caseId)
                .flw(new FlwBuilder().flwId(userId).build())
                .flwGroup(new FlwGroupBuilder().groupId(ownerId).build())
                .build();

        assertReflectionEqualsWithIgnore(expectedMotherCase, motherCases.get(0), "id", "flw", "flwGroup", "creationTime");
        assertReflectionEqualsWithIgnore(flw, motherCases.get(0).getFlw(), "id", "flwGroup", "creationTime");
        assertReflectionEqualsWithIgnore(flwGroup, motherCases.get(0).getFlwGroup(), "id", "flws", "creationTime");
    }

    @Test
    public void shouldParseChildCase() {
        CaseEvent caseEvent = new CaseEventBuilder(caseId)
                .withUserId(userId)
                .withCaseType("cc_bihar_newborn")
                .withOwnerId(ownerId)
                .build();

        caseProcessor.process(caseEvent);

        List<ChildCase> childCases = template.loadAll(ChildCase.class);
        assertEquals(1, childCases.size());
        ChildCase expectedChildCase = new ChildCaseBuilder()
                .clear()
                .caseId(caseId)
                .flw(new FlwBuilder().flwId(userId).build())
                .flwGroup(new FlwGroupBuilder().groupId(ownerId).build())
                .build();

        assertReflectionEqualsWithIgnore(expectedChildCase, childCases.get(0), "id", "flw", "flwGroup", "creationTime");
        assertReflectionEqualsWithIgnore(flw, childCases.get(0).getFlw(), "id", "flwGroup", "creationTime");
        assertReflectionEqualsWithIgnore(flwGroup, childCases.get(0).getFlwGroup(), "id", "flws", "creationTime");
    }

    @Test
    public void shouldUpdateCloseCaseWhenMotherCaseAlreadyExists() {
        MotherCase mother = new MotherCaseBuilder().caseId(caseId).build();
        template.save(mother);
        Flw flw = new FlwBuilder().flwId(userId).build();
        template.save(flw);

        CaseEvent closedCase = new CaseEventBuilder(caseId)
                .withAction("CLOSE")
                .withUserId(userId)
                .withDateModified(dateModified)
                .build();

        caseProcessor.process(closedCase);

        List<MotherCase> motherCases = template.loadAll(MotherCase.class);
        assertEquals(1, motherCases.size());

        MotherCase actualMother = motherCases.get(0);
        assertEquals(Boolean.TRUE, actualMother.getClosed());
        assertEquals((new DateTime(2013, 6, 13, 0, 0, 0)).toDate(), actualMother.getClosedOn());
        assertReflectionEqualsWithIgnore(flw, actualMother.getClosedBy());

    }

    @Test
    public void shouldUpdateCloseCaseWhenChildCaseAlreadyExists() {
        Flw flw = new FlwBuilder().flwId(userId).build();
        template.save(flw);

        ChildCase childCase = new ChildCaseBuilder().caseId(caseId).build();
        template.save(childCase);

        CaseEvent closedCase = new CaseEventBuilder(caseId)
                .withAction("CLOSE")
                .withUserId(userId)
                .withDateModified(dateModified)
                .build();

        caseProcessor.process(closedCase);

        List<ChildCase> childCases = template.loadAll(ChildCase.class);
        assertEquals(1, childCases.size());

        ChildCase actualChild = childCases.get(0);
        assertEquals(Boolean.TRUE, actualChild.getClosed());
        assertEquals((new DateTime(2013, 6, 13, 0, 0, 0)).toDate(), actualChild.getClosedOn());
        assertReflectionEqualsWithIgnore(flw, actualChild.getClosedBy());

    }

    @Test(expected = CaseNotFoundException.class)
    public void shouldThrowExceptionWhenTryingToClosedCaseThatDoesNotExists() {
        caseProcessor.process(new CaseEventBuilder(caseId).withAction("CLOSE").build());
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
                .build();

        caseProcessor.process(updatedCase);

        List<MotherCase> motherCases = template.loadAll(MotherCase.class);
        assertEquals(1, motherCases.size());
        MotherCase updatedMotherCase = motherCases.get(0);
        assertTrue(updatedMotherCase.getClosed());
        assertEquals(userId, updatedMotherCase.getClosedBy().getFlwId());
        assertEquals(JAN_01, updatedMotherCase.getClosedOn());
        assertEquals("User 2", updatedCase.getCaseName());
    }
}