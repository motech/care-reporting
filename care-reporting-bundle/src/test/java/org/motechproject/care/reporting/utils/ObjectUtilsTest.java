package org.motechproject.care.reporting.utils;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ObjectUtilsTest {
    @Test
    public void testSet() throws Exception {

        TestData testData = new TestData();
        ObjectUtils.set(testData, "anInt", "21");
        ObjectUtils.set(testData, "aString", "hello");
        ObjectUtils.set(testData, "aBoolean", "yEs"); // Boolean accepts true, yes, y, on, 1 (case insensitive).
        ObjectUtils.set(testData, "aDateFormat1", "05/22/2013");
        ObjectUtils.set(testData, "aDateFormat2", "2012-07-21T12:02:59.923+05:30");
        ObjectUtils.set(testData, "aDateFormat3", "2012-07-15");
        ObjectUtils.set(testData, "aDateFormat4", "2013-02-15T15:45:23");

        assertEquals(21, testData.getAnInt());
        assertEquals("hello", testData.getaString());
        assertEquals(true, testData.isaBoolean());
        assertEquals((new DateTime(2013, 5, 22, 0, 0, 0)).toDate(), testData.getaDateFormat1());
        assertEquals(new DateTime(2012, 7, 21, 12, 2, 59, 923, DateTimeZone.forOffsetHoursMinutes(5, 30)).toDate(), testData.aDateFormat2);
        assertEquals((new DateTime(2012, 7, 15, 0, 0, 0)).toDate(), testData.getaDateFormat3());
        assertEquals((new DateTime(2013, 2, 15, 15, 45, 23)).toDate(), testData.getaDateFormat4());
    }

    @Test
    public void testGet() throws Exception {
        TestData testData = new TestData();
        testData.setAnInt(25);
        testData.setaString("world");
        testData.setaBoolean(true);
        java.util.Date aDate = (new DateTime(2013, 5, 22, 0, 0, 0)).toDate();
        testData.setaDateFormat1(aDate);

        assertEquals(25, ObjectUtils.get(testData, "anInt"));
        assertEquals("world", ObjectUtils.get(testData, "aString"));
        assertEquals(aDate, ObjectUtils.get(testData, "aDateFormat1"));
        assertEquals(true, ObjectUtils.get(testData, "aBoolean"));
    }

    @Test
    public void shouldSetPrimitiveValueObjectsAsNull(){
        TestData testData = new TestData();
        testData.integerObject = new Integer(1);
        testData.shortObject = new Short("1");
        testData.booleanObject = Boolean.TRUE;
        testData.decimalObject = new BigDecimal("1.1");

        ObjectUtils.set(testData, "integerObject", null);
        ObjectUtils.set(testData, "shortObject", null);
        ObjectUtils.set(testData, "booleanObject", null);
        ObjectUtils.set(testData, "decimalObject", null);

        assertNull(testData.integerObject);
        assertNull(testData.shortObject);
        assertNull(testData.booleanObject);
        assertNull(testData.decimalObject);
    }

    @Test
    public void shouldParseDatesWithIncompleteTimezoneInformation() throws Exception {
        TestData testData = new TestData();
        ObjectUtils.set(testData, "aDateFormat1", "2012-09-25T20:17:19.189+05");

        assertEquals(DateTime.parse("2012-09-25T20:17:19.189+0500").toDate(), testData.aDateFormat1);
    }
}
