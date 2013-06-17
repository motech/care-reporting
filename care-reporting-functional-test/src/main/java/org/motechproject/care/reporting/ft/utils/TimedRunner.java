package org.motechproject.care.reporting.ft.utils;

import org.motechproject.care.reporting.ft.couch.service.SaveOperationStillInProgressException;

public abstract class TimedRunner<T> {

    private int tries;
    private int intervalSleep;
    private final TimedRunnerBreakCondition<T> breakCondition;

    public TimedRunner(int tries, int intervalSleep) {
        this(tries, intervalSleep, new TimedRunnerBreakCondition<T>() {
            @Override
            public boolean test(T obj) {
                return obj != null;
            }
        });
    }

    public TimedRunner(int tries, int intervalSleep, TimedRunnerBreakCondition<T> breakCondition) {
        this.tries = tries;
        this.intervalSleep = intervalSleep;
        this.breakCondition = breakCondition;
    }

    /*
     * Function to run within the timeout. It returns a boolean. If the value is true, the code
     * will break out of the loop immediately else try again within the timeout period.
     */
    protected abstract T run() throws Exception;

    public T executeWithTimeout() throws Exception {
        T result;
        for (int i = 0; i < tries; i++) {
            result = null;
            try {
                result = run();
            } catch (SaveOperationStillInProgressException ex) {
                //Catch it so that we can continue retrying.
            }
            if (breakCondition.test(result)) return result;

            try {
                Thread.sleep(intervalSleep);
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread was interrupted.", e);
            }
        }
        return null;
    }

}
