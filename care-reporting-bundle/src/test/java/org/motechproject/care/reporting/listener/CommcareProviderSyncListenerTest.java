package org.motechproject.care.reporting.listener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.care.reporting.processors.ProviderSyncProcessor;
import org.motechproject.commcare.provider.sync.constants.EventConstants;
import org.motechproject.commcare.provider.sync.response.Group;
import org.motechproject.commcare.provider.sync.response.Provider;
import org.motechproject.event.MotechEvent;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommcareProviderSyncListenerTest {

    @Mock
    ProviderSyncProcessor providerSyncProcessor;
    CommcareProviderSyncListener commcareProviderSyncListener;

    @Before
    public void setUp(){
        commcareProviderSyncListener = new CommcareProviderSyncListener(providerSyncProcessor);
    }

    @Test
    public void shouldHandleGroupSyncEvent(){
        HashMap<String, Object> parameters = new HashMap<>();
        ArrayList<Group> groups = new ArrayList<>();
        parameters.put(EventConstants.GROUP_DETAILS, groups);
        MotechEvent event = new MotechEvent(EventConstants.GROUP_DETAILS, parameters);

        commcareProviderSyncListener.handleGroupSyncEvent(event);

        verify(providerSyncProcessor).processGroupSync(groups);
    }

    @Test
    public void shouldHandleProviderSyncEvent(){
        HashMap<String, Object> parameters = new HashMap<>();
        ArrayList<Provider> providers = new ArrayList<>();
        parameters.put(EventConstants.PROVIDER_DETAILS, providers);
        MotechEvent event = new MotechEvent(EventConstants.PROVIDER_DETAILS_EVENT, parameters);

        commcareProviderSyncListener.handleProviderSyncEvent(event);

        verify(providerSyncProcessor).processProviderSync(providers);
    }
}
