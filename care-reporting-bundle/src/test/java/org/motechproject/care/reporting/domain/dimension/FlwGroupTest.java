package org.motechproject.care.reporting.domain.dimension;

import org.junit.Test;
import org.motechproject.care.reporting.builder.FlwGroupBuilder;
import org.motechproject.care.reporting.utils.TestUtils;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class FlwGroupTest {
    @Test
    public void shouldUpdateLastModifiedTimeAndNotCreationTime() {
        FlwGroup flwGroup = new FlwGroupBuilder().groupId("groupId").build();

        flwGroup.updateToLatest(new FlwGroupBuilder().groupId("groupId").creationTime(null).build());

        TestUtils.assertDateIgnoringSeconds(new Date(), flwGroup.getLastModifiedTime());
        assertNotNull(flwGroup.getCreationTime());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfGroupIdDontMatch() throws Exception {
        new FlwGroupBuilder().groupId("1").build().updateToLatest(new FlwGroupBuilder().groupId("2").build());
    }
}
