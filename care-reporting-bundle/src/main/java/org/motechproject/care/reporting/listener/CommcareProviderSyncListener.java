package org.motechproject.care.reporting.listener;

import org.motechproject.care.reporting.processors.ProviderSyncProcessor;
import org.motechproject.commcare.provider.sync.constants.EventConstants;
import org.motechproject.commcare.provider.sync.response.Group;
import org.motechproject.commcare.provider.sync.response.Provider;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommcareProviderSyncListener {

    ProviderSyncProcessor providerSyncProcessor;

    @Autowired
    public CommcareProviderSyncListener(ProviderSyncProcessor providerSyncProcessor) {
        this.providerSyncProcessor = providerSyncProcessor;
    }

    @MotechListener(subjects = EventConstants.GROUP_DETAILS_EVENT)
    public void handleGroupSyncEvent(MotechEvent event) {
        List<Group> groups = (List<Group>) event.getParameters().get(EventConstants.GROUP_DETAILS);
        providerSyncProcessor.processGroupSync(groups);
    }

    @MotechListener(subjects = EventConstants.PROVIDER_DETAILS_EVENT)
    public void handleProviderSyncEvent(MotechEvent event) {
        List<Provider> providers = (List<Provider>) event.getParameters().get(EventConstants.PROVIDER_DETAILS);
        providerSyncProcessor.processProviderSync(providers);
    }
}