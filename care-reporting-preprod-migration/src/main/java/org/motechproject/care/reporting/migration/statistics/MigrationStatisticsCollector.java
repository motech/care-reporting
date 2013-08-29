package org.motechproject.care.reporting.migration.statistics;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MigrationStatisticsCollector {

    private int recordsDownloaded;
    private int recordsUploaded;

    private static final Logger logger = LoggerFactory.getLogger("migration-statistics-logger");

    private StopWatch stopWatch;
    private EndpointStatisticsCollector commcareEndpoint;
    private EndpointStatisticsCollector motechEndpoint;

    public MigrationStatisticsCollector() {
        stopWatch = new StopWatch();
        commcareEndpoint = new EndpointStatisticsCollector();
        motechEndpoint = new EndpointStatisticsCollector();
    }

    private String formatDuration(long mills) {
        return DurationFormatUtils.formatDuration(mills, "HH 'hours' mm 'minutes' ss 'seconds' SS 'mills'", true);
    }

    public void startTimer() {
        stopWatch.start();
    }

    public void stopTimer() {
        stopWatch.stop();
    }

    public void addRecordsDownloaded(int records) {
        this.recordsDownloaded += records;
    }

    public void addRecordsUploaded(int records) {
        this.recordsUploaded += records;
    }

    public EndpointStatisticsCollector commcareEndpoint() {
        return commcareEndpoint;
    }

    public EndpointStatisticsCollector motechEndpoint() {
        return motechEndpoint;
    }

    public void publishResults() {
        logger.info(String.format("Migration Statistics::"));
        logger.info(String.format("Total time: %s", formatDuration(getTotalTimeElapsed())));
        logger.info(String.format("Total Records Downloaded: %s", recordsDownloaded));
        logger.info(String.format("Total Records Uploaded: %s", recordsUploaded));
        logger.info("Commcare Statistics:");
        commcareEndpoint.publishResults();
        logger.info("Motech Statistics");
        motechEndpoint.publishResults();
    }

    public int getRecordsUploaded() {
        return recordsUploaded;
    }

    public int getRecordsDownloaded() {
        return recordsDownloaded;
    }

    public long getTotalTimeElapsed() {
        return stopWatch.getTime();
    }
}
