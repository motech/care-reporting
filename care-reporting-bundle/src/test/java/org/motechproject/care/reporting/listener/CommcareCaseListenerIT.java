package org.motechproject.care.reporting.listener;

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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionContains;

public class CommcareCaseListenerIT extends SpringIntegrationTest {
    @Autowired
    private CommcareCaseListener commcareCaseListener;

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
}
