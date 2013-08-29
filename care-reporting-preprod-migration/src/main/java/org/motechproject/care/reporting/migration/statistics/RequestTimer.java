package org.motechproject.care.reporting.migration.statistics;

import org.apache.commons.lang.time.StopWatch;

public class RequestTimer {

    private int retries;
    private StopWatch stopWatch;
    private EndpointStatisticsCollector endpointStatisticsCollector;
    private boolean started;

    public RequestTimer(EndpointStatisticsCollector endpointStatisticsCollector) {
        this.endpointStatisticsCollector = endpointStatisticsCollector;
        stopWatch = new StopWatch();
    }
    public void retried() {
        retries++;
    }
    public void start() {
        started = true;
        stopWatch.start();
    }
    public void successful() {
        if(!started) {
            return;
        }
        started = false;
        stopWatch.stop();
        endpointStatisticsCollector.record(stopWatch.getTime(), retries, true);
    }
    public void failed() {
        if(!started) {
            return;
        }
        stopWatch.stop();
        started = false;
        endpointStatisticsCollector.record(stopWatch.getTime(), retries, false);
    }
}
