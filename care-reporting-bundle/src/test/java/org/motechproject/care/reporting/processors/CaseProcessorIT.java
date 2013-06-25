package org.motechproject.care.reporting.processors;

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

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.motechproject.care.reporting.utils.TestUtils.assertReflectionEqualsWithIgnore;

public class CaseProcessorIT extends SpringIntegrationTest {

    @Autowired
    private CaseProcessor caseProcessor;
    private FlwGroup flwGroup;
    private Flw flw;


    @Before
    @Override
    public void setUp() {
        super.setUp();
        flwGroup = new FlwGroup();
        flwGroup.setGroupId("89fda0284e008d2e0c980fb13fbb49e6");
        flw = new Flw();
        flw.setFlwId("89fda0284e008d2e0c980fb13f989136");
        flwGroup.getFlws().add(flw);
        template.save(flwGroup);
    }

    @Test
    public void shouldParseMotherCase() {
        CaseEvent caseEvent = new CaseEventBuilder("97e56523-5820-414a-83c2-bfcb6dcf4db3")
                .withUserId("89fda0284e008d2e0c980fb13f989136")
                .withCaseType("cc_bihar_pregnancy")
                .withOwnerId("89fda0284e008d2e0c980fb13fbb49e6")
                .build();

        caseProcessor.process(caseEvent);

        List<MotherCase> motherCases = template.loadAll(MotherCase.class);
        assertEquals(1, motherCases.size());
        MotherCase expectedMotherCase = new MotherCaseBuilder()
                .clear()
                .caseId("97e56523-5820-414a-83c2-bfcb6dcf4db3")
                .flw(new FlwBuilder().flwId("89fda0284e008d2e0c980fb13f989136").build())
                .flwGroup(new FlwGroupBuilder().groupId("89fda0284e008d2e0c980fb13fbb49e6").build())
                .build();

        assertReflectionEqualsWithIgnore(expectedMotherCase, motherCases.get(0), new String[]{"id", "flw", "flwGroup", "creationTime"});
        assertReflectionEqualsWithIgnore(flw, motherCases.get(0).getFlw(), new String[]{"id", "flwGroup", "creationTime"});
        assertReflectionEqualsWithIgnore(flwGroup, motherCases.get(0).getFlwGroup(), new String[]{"id", "flws", "creationTime"});
    }

    @Test
    public void shouldParseChildCase() {
        CaseEvent caseEvent = new CaseEventBuilder("97e56523-5820-414a-83c2-bfcb6dcf4db3")
                .withUserId("89fda0284e008d2e0c980fb13f989136")
                .withCaseType("cc_bihar_newborn")
                .withOwnerId("89fda0284e008d2e0c980fb13fbb49e6")
                .build();

        caseProcessor.process(caseEvent);

        List<ChildCase> childCases = template.loadAll(ChildCase.class);
        assertEquals(1, childCases.size());
        ChildCase expectedChildCase = new ChildCaseBuilder()
                .clear()
                .caseId("97e56523-5820-414a-83c2-bfcb6dcf4db3")
                .flw(new FlwBuilder().flwId("89fda0284e008d2e0c980fb13f989136").build())
                .flwGroup(new FlwGroupBuilder().groupId("89fda0284e008d2e0c980fb13fbb49e6").build())
                .build();

        assertReflectionEqualsWithIgnore(expectedChildCase, childCases.get(0), new String[]{"id", "flw", "flwGroup", "creationTime"});
        assertReflectionEqualsWithIgnore(flw, childCases.get(0).getFlw(), new String[]{"id", "flwGroup", "creationTime"});
        assertReflectionEqualsWithIgnore(flwGroup, childCases.get(0).getFlwGroup(), new String[]{"id", "flws", "creationTime"});
    }
}