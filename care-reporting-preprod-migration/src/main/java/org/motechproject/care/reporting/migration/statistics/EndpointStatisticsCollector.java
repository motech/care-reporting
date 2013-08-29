package org.motechproject.care.reporting.migration.statistics;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndpointStatisticsCollector {

    private long totalElapsedTime;
    private int totalRequests;
    private int totalRetries;
    private int retiredRequests;

    private static final Logger logger = LoggerFactory.getLogger("migration-statistics-logger");

    public EndpointStatisticsCollector() {
    }

    public static String formatDuration(long mills) {
        return DurationFormatUtils.formatDuration(mills, "ss 'seconds' SS 'mills'", true);
    }

    public RequestTimer newRequest() {
        return new RequestTimer(this);
    }

    public void publishResults() {
        logger.info(String.format("Total successful Requests: %s (Total Retries: %s, Requests Retried: %s)", totalRequests, totalRetries, retiredRequests));
        logger.info(String.format("Avg time taken by requests: %s", formatDuration(getAverageRequestTime())));
    }

    public long getAverageRequestTime() {
        if(totalRequests + totalRetries == 0) {
            return 0;
        }
        return totalElapsedTime / (totalRequests + totalRetries);
    }

    public void record(long elapsedTime, int retries, boolean successful) {
        if(!successful) {
            return;
        }
        totalRequests++;
        totalElapsedTime += elapsedTime;
        if(retries > 0) {
            totalRetries += retries;
            retiredRequests++;
        }
    }

    public long getTotalElapsedTime() {
        return totalElapsedTime;
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public int getTotalRetries() {
        return totalRetries;
    }

    public int getRetiredRequests() {
        return retiredRequests;
    }
}
