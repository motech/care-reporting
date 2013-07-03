package org.motechproject.care.reporting.domain.dimension;

import junit.framework.Assert;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.care.reporting.builder.FlwGroupBuilder;
import org.motechproject.care.reporting.utils.TestUtils;

import java.util.Date;

import static junit.framework.Assert.assertTrue;
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


    @Test
    public void shouldInitializeWithCreationAndLastModifiedTime() {
        DateTime now = new DateTime();

        FlwGroup flwGroup = new FlwGroup();

        Date creationTime = flwGroup.getCreationTime();
        Date lastModifiedTime = flwGroup.getLastModifiedTime();
        Assert.assertEquals(creationTime, lastModifiedTime);
        assertTrue(!now.isAfter(new DateTime(lastModifiedTime)));
    }


    @Test
    public void shouldInitializeWithEmptyFLWSet() {
        FlwGroup flwGroup = new FlwGroup();

        assertTrue(flwGroup.getFlws().isEmpty());
    }
}
