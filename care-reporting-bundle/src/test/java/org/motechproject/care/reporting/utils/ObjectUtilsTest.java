package org.motechproject.care.reporting.utils;


import org.junit.Test;
import org.motechproject.care.reporting.mapper.TestData;

import static junit.framework.Assert.assertEquals;

public class ObjectUtilsTest {
    @Test
    public void testSet() throws Exception {
        TestData testData = new TestData();
        ObjectUtils.set(testData, "anInt", "21");
        ObjectUtils.set(testData, "aString", "hello");
        ObjectUtils.set(testData, "aBoolean", "yEs"); // Boolean accepts true, yes, y, on, 1 (case insensitive).

        assertEquals(21, testData.getAnInt());
        assertEquals("hello", testData.getaString());
        assertEquals(true, testData.isaBoolean());
    }
}
