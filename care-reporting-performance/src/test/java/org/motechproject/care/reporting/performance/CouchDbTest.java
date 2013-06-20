package org.motechproject.care.reporting.performance;


import org.databene.benerator.main.Benerator;
import org.junit.Test;

import java.io.IOException;

public class CouchDbTest {

    @Test
    public void populateDb() throws IOException {
        long startTime = System.currentTimeMillis();
        Benerator.main(new String[]{"couch_generation.ben.xml"});
        long endTime = System.currentTimeMillis();
        System.out.println("Timetaken: " + (endTime - startTime)/1000 + " seconds");
    }
}


