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
import org.motechproject.care.reporting.domain.dimension.LocationDimension;
import org.motechproject.care.reporting.mapper.CareReportingMapper;
import org.motechproject.care.reporting.parser.GroupParser;
import org.motechproject.care.reporting.parser.ProviderParser;
import org.motechproject.care.reporting.service.CareService;
import org.motechproject.commcare.provider.sync.response.Group;
import org.motechproject.commcare.provider.sync.response.Provider;
import org.unitils.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

import static junit.framework.Assert.*;
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

    @Mock
    private CareReportingMapper genericMapper;

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
        when(groupParser.parse(group1)).thenReturn(new HashMap<String, Object>() {{
            put("name", "group1");
        }});
        when(groupParser.parse(group2)).thenReturn(new HashMap<String, Object>() {{
            put("name", "group2");
        }});

        providerSyncProcessor.processGroupSync(groups);

        verify(groupParser).parse(group1);
        verify(groupParser).parse(group2);
        verify(groupParser, times(2)).parse(any(Group.class));
        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(FlwGroup.class), flwGroupArgumentCaptor.capture());
        List<FlwGroup> actualFlwGroups = flwGroupArgumentCaptor.getValue();
        assertEquals(2, actualFlwGroups.size());
        assertEquals("group1", actualFlwGroups.get(0).getName());
        assertEquals("group2", actualFlwGroups.get(1).getName());
    }

    @Test
    public void shouldParseProvidersAndMapToFlwDbEntityAndSaveAll() {
        final Provider provider1 = provider("id", "1");
        final Provider provider2 = provider("id", "2");
        final ArrayList<Provider> providers = new ArrayList<Provider>() {{
            add(provider1);
            add(provider2);
        }};
        when(providerParser.parse(provider1)).thenReturn(new HashMap<String, Object>() {{
            put("firstName", "provider1");
        }});
        when(providerParser.parse(provider2)).thenReturn(new HashMap<String, Object>() {{
            put("firstName", "provider2");
        }});


        providerSyncProcessor.processProviderSync(providers);

        verify(providerParser).parse(provider1);
        verify(providerParser).parse(provider2);
        verify(providerParser, times(2)).parse(any(Provider.class));
        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(Flw.class), flwArgumentCaptor.capture());
        List<Flw> mappedFlws = flwArgumentCaptor.getValue();
        assertEquals(2, mappedFlws.size());
        assertEquals("provider1", mappedFlws.get(0).getFirstName());
        assertEquals("provider2", mappedFlws.get(1).getFirstName());
    }

    @Test
    public void shouldGetFlwGroupsAndAssociateItWithFlw() {
        final String groupId1 = "groupId1";
        final String groupId2 = "groupId2";
        final Provider provider = provider("groups", new ArrayList<String>() {{
            add(groupId1);
            add(groupId2);
        }});
        FlwGroup expectedFlwGroup1 = new FlwGroup();
        FlwGroup expectedFlwGroup2 = new FlwGroup();
        when(providerParser.parse(provider)).thenReturn(new HashMap<String, Object>());
        when(careService.getOrCreateGroup(groupId1)).thenReturn(expectedFlwGroup1);
        when(careService.getOrCreateGroup(groupId2)).thenReturn(expectedFlwGroup2);

        providerSyncProcessor.processProviderSync(new ArrayList<Provider>() {{
            add(provider);
        }});

        verify(careService).getOrCreateGroup(groupId1);
        verify(careService).getOrCreateGroup(groupId2);
        verify(careService, times(2)).getOrCreateGroup(anyString());
        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(Flw.class), flwArgumentCaptor.capture());
        Set<FlwGroup> actualFlwGroups = flwArgumentCaptor.getValue().get(0).getFlwGroups();
        assertEquals(2, actualFlwGroups.size());
        assertTrue(actualFlwGroups.contains(expectedFlwGroup1));
        assertTrue(actualFlwGroups.contains(expectedFlwGroup2));
    }

    @Test
    public void shouldGetLocationDimensionsAndAssociateItWithFlw() {

        final Provider provider = new Provider();

        final String state = "BIHAR";
        final String district = "ARARIA";
        final String block = "BHARGAMA";

        when(providerParser.parse(provider)).thenReturn(getLocationMap(state, district, block));
        when(careService.getLocation(state, district, block)).thenReturn(new LocationDimension(state, district, block));

        providerSyncProcessor.processProviderSync(new ArrayList<Provider>() {{
            add(provider);
        }});

        verify(careService).getLocation(state, district, block);
        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(Flw.class), flwArgumentCaptor.capture());
        final LocationDimension locationDimension = flwArgumentCaptor.getValue().get(0).getLocationDimension();
        assertEquals(state, locationDimension.getState());
        assertEquals(district, locationDimension.getDistrict());
        assertEquals(block, locationDimension.getBlock());
    }

    private Map<String,Object> getLocationMap(final String state, final String district, final String block){
        return new HashMap<String, Object>() {{
            put("state", state);
            put("district", district);
            put("block", block);
        }};
    }

    @Test
    public void shouldNotGetNewReferenceAgainToSameFlwGroup() {
        final String groupId1 = "groupId1";
        final String groupId2 = "groupId2";
        final Provider provider1 = provider("groups", new ArrayList<String>() {{
            add(groupId1);
            add(groupId2);
        }});
        final Provider provider2 = provider("groups", new ArrayList<String>() {{
            add(groupId1);
        }});
        FlwGroup expectedFlwGroup1 = new FlwGroup();
        FlwGroup expectedFlwGroup2 = new FlwGroup();
        when(providerParser.parse(provider1)).thenReturn(new HashMap<String, Object>());
        when(careService.getOrCreateGroup(groupId1)).thenReturn(expectedFlwGroup1);
        when(careService.getOrCreateGroup(groupId2)).thenReturn(expectedFlwGroup2);

        providerSyncProcessor.processProviderSync(new ArrayList<Provider>() {{
            add(provider1);
            add(provider2);
        }});

        verify(careService).getOrCreateGroup(groupId1);
        verify(careService).getOrCreateGroup(groupId2);
        verify(careService, times(2)).getOrCreateGroup(anyString());

        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(Flw.class), flwArgumentCaptor.capture());
        Set<FlwGroup> actualFlwGroupsForProvider1 = flwArgumentCaptor.getValue().get(0).getFlwGroups();
        assertEquals(2, actualFlwGroupsForProvider1.size());
        assertTrue(actualFlwGroupsForProvider1.contains(expectedFlwGroup1));
        assertTrue(actualFlwGroupsForProvider1.contains(expectedFlwGroup2));
        Set<FlwGroup> actualFlwGroupsForProvider2 = flwArgumentCaptor.getValue().get(1).getFlwGroups();
        assertEquals(1, actualFlwGroupsForProvider2.size());
        assertSame(expectedFlwGroup1, actualFlwGroupsForProvider2.iterator().next());
    }

    private Provider provider(String fieldName, Object value) {
        Provider provider = new Provider();
        try {
            Field id = Provider.class.getDeclaredField(fieldName);
            ReflectionUtils.setFieldValue(provider, id, value);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return provider;
    }
}
