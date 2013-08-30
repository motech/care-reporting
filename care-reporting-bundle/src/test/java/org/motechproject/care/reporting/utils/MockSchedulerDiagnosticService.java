package org.motechproject.care.reporting.utils;

import org.motechproject.commcare.provider.sync.diagnostics.DiagnosticsResult;
import org.motechproject.commcare.provider.sync.diagnostics.SchedulerDiagnosticService;
import org.quartz.SchedulerException;

import java.util.List;

public class MockSchedulerDiagnosticService implements SchedulerDiagnosticService {
    @Override
    public DiagnosticsResult diagnoseSchedules(List<String> strings) throws SchedulerException {
        return null;
    }
}
