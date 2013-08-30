package org.motechproject.care.reporting.migration.statistics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MigrationStatisticsCollectorTest {

    @Test
    public void shouldUpdateRecordsDownloaded() {
        MigrationStatisticsCollector statisticsCollector = new MigrationStatisticsCollector();
        statisticsCollector.addRecordsDownloaded(1);
        statisticsCollector.addRecordsDownloaded(15);

        assertEquals(16, statisticsCollector.getRecordsDownloaded());
    }

    @Test
    public void shouldUpdateRecordsUploaded() {
        MigrationStatisticsCollector statisticsCollector = new MigrationStatisticsCollector();
        statisticsCollector.addRecordsUploaded(10);
        statisticsCollector.addRecordsUploaded(12);

        assertEquals(22, statisticsCollector.getRecordsUploaded());
    }

    @Test
    public void shouldCalculateTotalTimeElapsed() throws InterruptedException {
        MigrationStatisticsCollector statisticsCollector = new MigrationStatisticsCollector();
        long startTime = System.currentTimeMillis();
        statisticsCollector.startTimer();
        Thread.sleep(1);
        statisticsCollector.stopTimer();
        long expectedTotalTimeElapsed = System.currentTimeMillis() - startTime;

        long actualTotalTimeElapsed = statisticsCollector.getTotalTimeElapsed();

        assertTrue(actualTotalTimeElapsed > 0);
        assertTrue(actualTotalTimeElapsed <= expectedTotalTimeElapsed);
    }
}
