package org.motechproject.care.reporting.migration.statistics;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class RequestTimerTest {

    @Mock
    private EndpointStatisticsCollector statisticsCollector;
    private RequestTimer requestTimer;

    @Before
    public void setUp() {
        initMocks(this);
        requestTimer = new RequestTimer(statisticsCollector);
    }

    @Test
    public void shouldSendTimeAndRetryInformationForSuccessful() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        requestTimer.start();
        Thread.sleep(1);
        requestTimer.retried();
        requestTimer.retried();
        requestTimer.successful();

        long expectedTimeElapsed = System.currentTimeMillis() - startTime;

        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(statisticsCollector).record(longArgumentCaptor.capture(), eq(2), eq(true));

        Long actualTimeElapsed = longArgumentCaptor.getValue();

        assertTrue(actualTimeElapsed > 0);
        assertTrue(actualTimeElapsed <= expectedTimeElapsed);
    }


    @Test
    public void shouldSendTimeAndRetryInformationForFailedRequests() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        requestTimer.start();
        Thread.sleep(1);
        requestTimer.retried();
        requestTimer.failed();

        long expectedTimeElapsed = System.currentTimeMillis() - startTime;

        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(statisticsCollector).record(longArgumentCaptor.capture(), eq(1), eq(false));

        Long actualTimeElapsed = longArgumentCaptor.getValue();

        assertTrue(actualTimeElapsed > 0);
        assertTrue(actualTimeElapsed <= expectedTimeElapsed);
    }

    @Test
    public void shouldNotSendTimeAndRetryInformationForSuccessIfNotStarted() {
        requestTimer.retried();
        requestTimer.failed();

        verify(statisticsCollector, never()).record(anyLong(), anyInt(), anyBoolean());
    }

    @Test
    public void shouldNotSendTimeAndRetryInformationForFailureIfNotStarted() {
        requestTimer.retried();
        requestTimer.successful();

        verify(statisticsCollector, never()).record(anyLong(), anyInt(), anyBoolean());
    }

    @Test
    public void shouldNotReSendTimeAndRetryOnceSent() {
        requestTimer.start();
        requestTimer.retried();
        requestTimer.successful();
        requestTimer.retried();
        requestTimer.successful();
        requestTimer.failed();

        verify(statisticsCollector).record(anyLong(), eq(1), eq(true));
        verifyNoMoreInteractions(statisticsCollector);
    }
}
