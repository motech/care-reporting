package org.motechproject.care.reporting.model;


import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class AppVersionListEntityTest {

    AppVersionListEntity entity;
    @Before
    public void setup(){
        entity = new AppVersionListEntity(true, new String[]{"alphaVersion", "devVersion"});

    }

    @Test
    public void testContains() throws Exception {
        assertTrue(entity.contains("alphaVersion"));
        assertFalse(entity.contains("releaseVersion"));
    }

    @Test
    public void testExclusion() throws Exception {
        assertTrue(entity.isExclusion());
    }

    @Test
    public void testInclusion() throws Exception {
        assertFalse(entity.isInclusion());
    }
}
