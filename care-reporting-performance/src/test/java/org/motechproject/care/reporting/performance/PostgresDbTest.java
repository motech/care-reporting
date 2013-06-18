package org.motechproject.care.reporting.performance;


import org.databene.benerator.main.Benerator;
import org.junit.Test;

import java.io.IOException;

public class PostgresDbTest {

    @Test
    public void populateDb() throws IOException {
        long startTime = System.currentTimeMillis();
        Benerator.main(new String[]{"postgres_generation.ben.xml"});
        long endTime = System.currentTimeMillis();
        System.out.println("Timetaken: " + (endTime - startTime)/1000 + " seconds");
    }
}


