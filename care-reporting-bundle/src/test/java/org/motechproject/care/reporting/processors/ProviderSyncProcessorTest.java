package org.motechproject.care.reporting.processors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.parser.GroupParser;
import org.motechproject.care.reporting.parser.ProviderParser;
import org.motechproject.care.reporting.service.CareService;
import org.motechproject.commcare.provider.sync.response.Group;
import org.motechproject.commcare.provider.sync.response.Provider;
import org.unitils.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProviderSyncProcessorTest {
    @Mock
    GroupParser groupParser;
    @Mock
    private ProviderParser providerParser;
    @Mock
    CareService careService;
    @Captor
    ArgumentCaptor<List<FlwGroup>> flwGroupArgumentCaptor;
    @Captor
    private ArgumentCaptor<List<Flw>> flwArgumentCaptor;

    ProviderSyncProcessor providerSyncProcessor;

    @Before
    public void setUp() {
        providerSyncProcessor = new ProviderSyncProcessor(groupParser, providerParser, careService);
    }

    @Test
    public void shouldParseGivenGroupsAndMapToFlwGroupEntityAndSaveAll() {
        final Group group1 = new Group();
        final Group group2 = new Group();
        ArrayList<Group> groups = new ArrayList<Group>() {{
            add(group1);
            add(group2);
        }};
        when(groupParser.parse(group1)).thenReturn(new HashMap<String, String>() {{
            put("name", "group1");
        }});
        when(groupParser.parse(group2)).thenReturn(new HashMap<String, String>() {{
            put("name", "group2");
        }});

        providerSyncProcessor.processGroupSync(groups);

        verify(groupParser).parse(group1);
        verify(groupParser).parse(group2);
        verify(groupParser, times(2)).parse(any(Group.class));
        verify(careService).saveOrUpdateAll(flwGroupArgumentCaptor.capture());
        List<FlwGroup> actualFlwGroups = flwGroupArgumentCaptor.getValue();
        assertEquals(2, actualFlwGroups.size());
        assertEquals("group1", actualFlwGroups.get(0).getName());
        assertEquals("group2", actualFlwGroups.get(1).getName());
    }

    @Test
    public void shouldParseProvidersAndMapToFlwDbEntityAndSaveAll() {
        final Provider provider1 = provider("1");
        final Provider provider2 = provider("2");
        final ArrayList<Provider> providers = new ArrayList<Provider>() {{
            add(provider1);
            add(provider2);
        }};
        when(providerParser.parse(provider1)).thenReturn(new HashMap<String, String>() {{
            put("firstName", "provider1");
        }});
        when(providerParser.parse(provider2)).thenReturn(new HashMap<String, String>() {{
            put("firstName", "provider2");
        }});

        providerSyncProcessor.processProviderSync(providers);

        verify(providerParser).parse(provider1);
        verify(providerParser).parse(provider2);
        verify(providerParser, times(2)).parse(any(Provider.class));
        verify(careService).saveOrUpdateAll(flwArgumentCaptor.capture());
        List<Flw> mappedFlws = flwArgumentCaptor.getValue();
        assertEquals(2, mappedFlws.size());
        assertEquals("provider1", mappedFlws.get(0).getFirstName());
        assertEquals("provider2", mappedFlws.get(1).getFirstName());
    }

    private Provider provider(String value) {
        Provider provider = new Provider();
        try {
            Field id = Provider.class.getDeclaredField("id");
            ReflectionUtils.setFieldValue(provider, id, value);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return provider;
    }
}
