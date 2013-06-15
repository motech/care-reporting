package org.motechproject.care.reporting.utils;

import org.junit.Test;
import org.motechproject.care.reporting.enums.CaseType;

import static junit.framework.Assert.assertEquals;

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
        Pair<String, CaseType> stringPair = new Pair<>("namespace", CaseType.MOTHER);
        Pair<String, CaseType> expectedStringEnumPair = new Pair<>("namespace", CaseType.MOTHER);
        assertEquals(expectedStringEnumPair, stringPair);
        assertEquals("namespace", stringPair.getFirst());
        assertEquals("MOTHER", stringPair.getSecond().name());
    }

}
