package org.motechproject.care.reporting.processors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.parser.GroupParser;
import org.motechproject.care.reporting.service.CareService;
import org.motechproject.commcare.provider.sync.response.Group;

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
    CareService careService;
    @Captor
    ArgumentCaptor<List<FlwGroup>> flwGroupArgumentCaptor;

    ProviderSyncProcessor providerSyncProcessor;

    @Before
    public void setUp() {
        providerSyncProcessor = new ProviderSyncProcessor(groupParser, careService);
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
}
