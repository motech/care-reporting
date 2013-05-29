package org.motechproject.care.reporting.utils;

import org.junit.Test;
import org.motechproject.care.reporting.enums.CaseType;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;

public class PairTest {

    @Test
    public void testStringPair(){
        Pair<String, String> stringPair = new Pair<>("Hello", "World");
        Pair<String, String> expectedStringPair = new Pair<>("Hello", "World");
        assertEquals(expectedStringPair, stringPair);
        assertEquals("Hello", stringPair.getFirst());
        assertEquals("World", stringPair.getSecond());
    }

    @Test
    public void testStringEnumPair(){
        Pair<String, CaseType> stringPair = new Pair<>("namespace", CaseType.Mother);
        Pair<String, CaseType> expectedStringEnumPair = new Pair<>("namespace", CaseType.Mother);
        assertEquals(expectedStringEnumPair, stringPair);
        assertEquals("namespace", stringPair.getFirst());
        assertEquals("Mother", stringPair.getSecond().name());
    }

}
