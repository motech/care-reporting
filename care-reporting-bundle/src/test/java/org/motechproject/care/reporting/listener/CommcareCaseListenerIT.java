package org.motechproject.care.reporting.listener;

import org.joda.time.DateTime;
import org.junit.After;
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
import org.motechproject.event.MotechEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionContains;

public class CommcareCaseListenerIT extends SpringIntegrationTest {
    @Autowired
    private CommcareCaseListener commcareCaseListener;
    public static final Date JAN_01 = DateTime.parse("2013-01-01").toDate();
    public static final Date JAN_20 = DateTime.parse("2013-01-20").toDate();

    @Before
    @After
    public void setUp() {
        template.deleteAll(template.loadAll(MotherCase.class));
        template.deleteAll(template.loadAll(FlwGroup.class));
    }

    @Test
    public void shouldNotInsertDuplicateMotherCase() throws Exception {
        MotherCase motherCase = new MotherCaseBuilder()
                .caseId("999")
                .build();
        template.save(motherCase);
        MotechEvent motechEvent = new CaseEventBuilder("999")
                .withCaseName("VA")
                .withCaseType("cc_bihar_pregnancy")
                .build()
                .toMotechEventWithData();

        commcareCaseListener.handleEvent(motechEvent);

        List<MotherCase> motherCases = template.loadAll(MotherCase.class);

        assertEquals(1, motherCases.size());
        assertReflectionContains(motherCase, motherCases);
    }

    @Test
    public void shouldInsertMotherCaseWithNewFlwAndFlwGroup() throws Exception {
        CaseEvent caseEvent = new CaseEventBuilder("999")
                .withCaseType("cc_bihar_pregnancy")
                .withUserId("5ba9a0928dde95d187544babf6c0ad24")
                .withOwnerId("6ba9a0928dde95d187544babf6c0ad36")
                .build();
        commcareCaseListener.handleEvent(caseEvent.toMotechEventWithData());

        List<FlwGroup> flwGroups = template.loadAll(FlwGroup.class);
        assertEquals(1, flwGroups.size());
        assertEquals("6ba9a0928dde95d187544babf6c0ad36", flwGroups.get(0).getGroupId());

        List<Flw> flws = template.loadAll(Flw.class);
        assertEquals(1, flwGroups.size());
        assertEquals("5ba9a0928dde95d187544babf6c0ad24", flws.get(0).getFlwId());
    }

    @Test
    public void shouldSaveFlwGroupFromFlwAndMotherCase() throws Exception {
        template.save(new FlwGroupBuilder()
                .groupId("6ba9a0928dde95d187544babf6c0ad36")
                .addFlw(new FlwBuilder().flwId("6ba9a0928dde95d187544babf6c0ad24").build())
                .build());
        template.save(new FlwGroupBuilder()
                .groupId("5ba9a0928dde95d187544babf6c0ad36")
                .addFlw(new FlwBuilder().flwId("5ba9a0928dde95d187544babf6c0ad24").build())
                .build());

        CaseEvent caseEvent = new CaseEventBuilder("999")
                .withCaseType("cc_bihar_pregnancy")
                .withUserId("5ba9a0928dde95d187544babf6c0ad24")
                .withOwnerId("6ba9a0928dde95d187544babf6c0ad36")
                .build();

        commcareCaseListener.handleEvent(caseEvent.toMotechEventWithData());
    }

    @Test
    public void shouldCloseCaseEvenIfDateModifiedIsLate() {
        MotherCase motherCase = new MotherCaseBuilder()
                .caseId("97e56523-5820-414a-83c2-bfcb6dcf4db3")
                .caseName("devi")
                .dateModified(JAN_20)
                .build();
        template.save(motherCase);

        CaseEvent caseEvent = new CaseEventBuilder("97e56523-5820-414a-83c2-bfcb6dcf4db3")
                .withAction("CLOSE")
                .withDateModified("2013-01-01")
                .withUserId("5ba9a0928dde95d187544babf6c0ad24")
                .build();

        commcareCaseListener.handleEvent(caseEvent.toMotechEventWithData());

        List<MotherCase> motherCases = template.loadAll(MotherCase.class);
        assertEquals(1, motherCases.size());
        MotherCase actualMother = motherCases.get(0);
        assertTrue(actualMother.getClosed());
        assertEquals(JAN_20, actualMother.getDateModified());
        assertEquals(JAN_01, actualMother.getClosedOn());
        assertEquals("5ba9a0928dde95d187544babf6c0ad24", actualMother.getClosedBy().getFlwId());

    }

    @Test
    public void shouldUpdateClosedCaseButShouldNotUpdateClosedFields() {
        Flw flw = new FlwBuilder().flwId("5ba9a0928dde95d187544babf6c0ad21").build();
        MotherCase motherCase = new MotherCaseBuilder()
                .caseId("97e56523-5820-414a-83c2-bfcb6dcf4db3")
                .caseName("old name")
                .dateModified(JAN_01)
                .closedDate(JAN_01)
                .closed(true)
                .closedBy(flw)
                .build();
        template.save(motherCase);

        HashMap<String, String> fieldMap = new HashMap<>();
        fieldMap.put("closed","false");
        fieldMap.put("closedOn","2013-01-05");
        CaseEvent caseEvent = new CaseEventBuilder("97e56523-5820-414a-83c2-bfcb6dcf4db3")
                .withCaseType("cc_bihar_pregnancy")
                .withDateModified("2013-01-20")
                .with(fieldMap)
                .withCaseName("new name")
                .build();


        commcareCaseListener.handleEvent(caseEvent.toMotechEventWithData());

        List<MotherCase> motherCases = template.loadAll(MotherCase.class);
        assertEquals(1, motherCases.size());
        MotherCase actualMother = motherCases.get(0);
        assertTrue(actualMother.getClosed());
        assertEquals(JAN_20, actualMother.getDateModified());
        assertEquals(JAN_01, actualMother.getClosedOn());
        assertEquals("new name",actualMother.getCaseName());
    }
}
