package org.motechproject.care.reporting.migration.statistics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EndpointStatisticsCollectorTest {

    @Test
    public void shouldRecordSuccessfulRequestStatistics() {
        EndpointStatisticsCollector statisticsCollector = new EndpointStatisticsCollector();
        statisticsCollector.record(100, 0, true);
        statisticsCollector.record(59, 1, true);
        statisticsCollector.record(2, 5, true);

        assertEquals(2, statisticsCollector.getRetiredRequests());
        assertEquals(3, statisticsCollector.getTotalRequests());
        assertEquals(161, statisticsCollector.getTotalElapsedTime());
        assertEquals(6, statisticsCollector.getTotalRetries());
    }

    @Test
    public void shouldNotRecordUnsuccessfulRequestStatistics() {
        EndpointStatisticsCollector statisticsCollector = new EndpointStatisticsCollector();
        statisticsCollector.record(100, 0, true);
        statisticsCollector.record(2, 5, false);
        statisticsCollector.record(59, 1, true);
        statisticsCollector.record(2, 5, true);
        statisticsCollector.record(2, 0, false);

        assertEquals(2, statisticsCollector.getRetiredRequests());
        assertEquals(3, statisticsCollector.getTotalRequests());
        assertEquals(161, statisticsCollector.getTotalElapsedTime());
        assertEquals(6, statisticsCollector.getTotalRetries());
    }

    @Test
    public void shouldCalculateAverageRequestTime() {
        EndpointStatisticsCollector statisticsCollector = new EndpointStatisticsCollector();
        statisticsCollector.record(100, 0, true);
        statisticsCollector.record(200, 5, true);
        statisticsCollector.record(59, 1, false);

        assertEquals(42, statisticsCollector.getAverageRequestTime());
    }

    @Test
    public void shouldCalculateAverageRequestTimeIfNoRequestsWereFired() {
        EndpointStatisticsCollector statisticsCollector = new EndpointStatisticsCollector();
        assertEquals(0, statisticsCollector.getAverageRequestTime());
    }
}
