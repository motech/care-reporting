package org.motechproject.care.reporting.utils;

import org.motechproject.commcare.provider.sync.diagnostics.DiagnosticsLogger;
import org.motechproject.commcare.provider.sync.diagnostics.DiagnosticsStatus;
import org.motechproject.commcare.provider.sync.diagnostics.scheduler.SchedulerDiagnosticsService;
import org.quartz.SchedulerException;

import java.util.List;

public class MockSchedulerDiagnosticsService implements SchedulerDiagnosticsService {

    @Override
    public DiagnosticsStatus diagnoseSchedules(List<String> strings, DiagnosticsLogger diagnosticsLogger) throws SchedulerException {
        return null;
    }
}
