package org.motechproject.care.reporting.utils;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ListUtilsTest {
    @Test
    public void shouldReturnNullIfTheListIsNull() {
        assertNull(ListUtils.safeGet(null, 0));
    }

    @Test
    public void shouldCheckForIndexWhileGettingAnElementFromList() {
        assertNull(ListUtils.safeGet(Arrays.asList("element1"), 1));
    }

    @Test
    public void shouldReturnTheIndexedElementFromTheListGivenAnIndex() {
        assertEquals("element1", ListUtils.safeGet(Arrays.asList("element0", "element1"), 1));
    }
}
