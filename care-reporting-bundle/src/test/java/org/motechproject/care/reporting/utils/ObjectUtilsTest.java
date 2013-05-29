package org.motechproject.care.reporting.utils;


import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.DateTime;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;

import static junit.framework.Assert.assertEquals;

public class ObjectUtilsTest {
    @Test
    public void testSet() throws Exception {

        TestData testData = new TestData();
        ObjectUtils.set(testData, "anInt", "21");
        ObjectUtils.set(testData, "aString", "hello");
        ObjectUtils.set(testData, "aDate", "05/22/2013");


        assertEquals(21, testData.getAnInt());
        assertEquals("hello", testData.getaString());
        java.util.Date aDate = (new DateTime(2013, 5, 22, 0, 0, 0)).toDate();
        assertEquals(aDate, testData.getaDate());

    }

    @Test
    public void testGet() throws Exception {
        TestData testData = new TestData();
        testData.setAnInt(25);
        testData.setaString("world");
        java.util.Date aDate = (new DateTime(2013, 5, 22, 0, 0, 0)).toDate();
        testData.setaDate(aDate);

        assertEquals(25, ObjectUtils.get(testData, "anInt"));
        assertEquals("world", ObjectUtils.get(testData, "aString"));
        assertEquals(aDate, ObjectUtils.get(testData, "aDate"));
    }
}
