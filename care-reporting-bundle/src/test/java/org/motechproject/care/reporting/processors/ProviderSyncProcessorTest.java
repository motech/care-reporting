package org.motechproject.care.reporting.processors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.LocationDimension;
import org.motechproject.care.reporting.mapper.ProviderSyncMapper;
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
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProviderSyncProcessorTest {
    @Mock
    private GroupParser groupParser;
    @Mock
    private ProviderParser providerParser;
    @Mock
    private CareService careService;
    @Mock
    private ProviderSyncMapper providerSyncMapper;
    @Captor
    private ArgumentCaptor<List<FlwGroup>> flwGroupArgumentCaptor;
    @Captor
    private ArgumentCaptor<List<Flw>> flwArgumentCaptor;

    private ProviderSyncProcessor providerSyncProcessor;

    @Before
    public void setUp() {
        initMocks(this);
        providerSyncProcessor = new ProviderSyncProcessor(groupParser, providerParser, careService, providerSyncMapper);
    }

    @Test
    public void shouldParseGivenGroupsAndMapToFlwGroupEntityAndSaveAll() {
        FlwGroup expectedGroup1 = createFlwGroup(1, "myGroup1");
        FlwGroup expectedGroup2 = createFlwGroup(2, "myGroup2");

        final Group group1 = new Group();
        final Group group2 = new Group();
        ArrayList<Group> groups = new ArrayList<Group>() {{
            add(group1);
            add(group2);
        }};

        Map<String, Object> parsedMap1 = new HashMap<String, Object>() {{
            put("name", "myGroup1");
        }};
        Map<String, Object> parsedMap2 = new HashMap<String, Object>() {{
            put("name", "myGroup2");
        }};

        when(groupParser.parse(group1)).thenReturn(parsedMap1);
        when(groupParser.parse(group2)).thenReturn(parsedMap2);
        when(providerSyncMapper.map(FlwGroup.class, parsedMap1)).thenReturn(expectedGroup1);
        when(providerSyncMapper.map(FlwGroup.class, parsedMap2)).thenReturn(expectedGroup2);

        providerSyncProcessor.processGroupSync(groups);

        verify(groupParser).parse(group1);
        verify(groupParser).parse(group2);
        verify(providerSyncMapper).map(FlwGroup.class, parsedMap1);
        verify(providerSyncMapper).map(FlwGroup.class, parsedMap2);

        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(FlwGroup.class), flwGroupArgumentCaptor.capture());
        List<FlwGroup> actualFlwGroups = flwGroupArgumentCaptor.getValue();
        assertEquals(2, actualFlwGroups.size());
        assertEquals(expectedGroup1, actualFlwGroups.get(0));
        assertEquals(expectedGroup2, actualFlwGroups.get(1));
    }

    @Test
    public void shouldContinueWithOtherGroupsIfOneGroupParsingFails() {
        FlwGroup expectedGroup2 = createFlwGroup(2, "myGroup2");

        final Group group1 = new Group();
        final Group group2 = new Group();
        ArrayList<Group> groups = new ArrayList<Group>() {{
            add(group1);
            add(group2);
        }};

        HashMap<String, Object> parsedMap2 = new HashMap<String, Object>() {{
            put("name", "group2");
        }};

        when(groupParser.parse(group1)).thenThrow(new RuntimeException("some error"));
        when(groupParser.parse(group2)).thenReturn(parsedMap2);
        when(providerSyncMapper.map(FlwGroup.class, parsedMap2)).thenReturn(expectedGroup2);

        providerSyncProcessor.processGroupSync(groups);

        verify(groupParser).parse(group1);
        verify(groupParser).parse(group2);
        verify(providerSyncMapper).map(FlwGroup.class, parsedMap2);
        verifyNoMoreInteractions(providerSyncMapper);

        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(FlwGroup.class), flwGroupArgumentCaptor.capture());
        List<FlwGroup> actualFlwGroups = flwGroupArgumentCaptor.getValue();
        assertEquals(1, actualFlwGroups.size());
        assertEquals(expectedGroup2, actualFlwGroups.get(0));
    }

    @Test
    public void shouldContinueWithOtherGroupsIfOneGroupMappingFails() {
        FlwGroup expectedGroup2 = createFlwGroup(2, "myGroup2");

        final Group group1 = new Group();
        final Group group2 = new Group();
        ArrayList<Group> groups = new ArrayList<Group>() {{
            add(group1);
            add(group2);
        }};

        Map<String, Object> parsedMap1 = new HashMap<String, Object>() {{
            put("name", "myGroup1");
        }};
        Map<String, Object> parsedMap2 = new HashMap<String, Object>() {{
            put("name", "myGroup2");
        }};

        when(groupParser.parse(group1)).thenReturn(parsedMap1);
        when(groupParser.parse(group2)).thenReturn(parsedMap2);
        when(providerSyncMapper.map(FlwGroup.class, parsedMap1)).thenThrow(new RuntimeException("some exception"));
        when(providerSyncMapper.map(FlwGroup.class, parsedMap2)).thenReturn(expectedGroup2);

        providerSyncProcessor.processGroupSync(groups);

        verify(groupParser).parse(group1);
        verify(groupParser).parse(group2);
        verify(providerSyncMapper).map(FlwGroup.class, parsedMap1);
        verify(providerSyncMapper).map(FlwGroup.class, parsedMap2);

        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(FlwGroup.class), flwGroupArgumentCaptor.capture());
        List<FlwGroup> actualFlwGroups = flwGroupArgumentCaptor.getValue();
        assertEquals(1, actualFlwGroups.size());
        assertEquals(expectedGroup2, actualFlwGroups.get(0));
    }

    @Test
    public void shouldParseProvidersAndMapToFlwDbEntityAndSaveAll() {
        Flw expectedFlw1 = createFlw(1, "firstName1", "lastName1");
        Flw expectedFlw2 = createFlw(2, "firstName2", "lastName2");

        final Provider provider1 = provider("id", "1");
        final Provider provider2 = provider("id", "2");
        final ArrayList<Provider> providers = new ArrayList<Provider>() {{
            add(provider1);
            add(provider2);
        }};

        Map<String, Object> parsedMap1 = new HashMap<String, Object>() {{
            put("firstName", "firstName1");
            put("lastName", "lastName1");
        }};
        Map<String, Object> parsedMap2 = new HashMap<String, Object>() {{
            put("firstName", "firstName2");
            put("lastName", "lastName2");
        }};

        when(providerParser.parse(provider1)).thenReturn(parsedMap1);
        when(providerParser.parse(provider2)).thenReturn(parsedMap2);
        when(providerSyncMapper.map(Flw.class, parsedMap1)).thenReturn(expectedFlw1);
        when(providerSyncMapper.map(Flw.class, parsedMap2)).thenReturn(expectedFlw2);

        providerSyncProcessor.processProviderSync(providers);

        verify(providerParser).parse(provider1);
        verify(providerParser).parse(provider2);
        verify(providerSyncMapper).map(Flw.class, parsedMap1);
        verify(providerSyncMapper).map(Flw.class, parsedMap2);


        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(Flw.class), flwArgumentCaptor.capture());
        List<Flw> mappedFlws = flwArgumentCaptor.getValue();
        assertEquals(2, mappedFlws.size());
        assertEquals(expectedFlw1, mappedFlws.get(0));
        assertEquals(expectedFlw2, mappedFlws.get(1));
    }

    @Test
    public void shouldContinueIfFlwParsingFails() {
        Flw expectedFlw2 = createFlw(2, "firstName2", "lastName2");

        final Provider provider1 = provider("id", "1");
        final Provider provider2 = provider("id", "2");
        final ArrayList<Provider> providers = new ArrayList<Provider>() {{
            add(provider1);
            add(provider2);
        }};

        Map<String, Object> parsedMap2 = new HashMap<String, Object>() {{
            put("firstName", "firstName2");
            put("lastName", "lastName2");
        }};

        when(providerParser.parse(provider1)).thenThrow(new RuntimeException("some exception"));
        when(providerParser.parse(provider2)).thenReturn(parsedMap2);
        when(providerSyncMapper.map(Flw.class, parsedMap2)).thenReturn(expectedFlw2);

        providerSyncProcessor.processProviderSync(providers);

        verify(providerParser).parse(provider1);
        verify(providerParser).parse(provider2);
        verify(providerSyncMapper).map(Flw.class, parsedMap2);
        verifyNoMoreInteractions(providerSyncMapper);

        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(Flw.class), flwArgumentCaptor.capture());
        List<Flw> mappedFlws = flwArgumentCaptor.getValue();
        assertEquals(1, mappedFlws.size());
        assertEquals(expectedFlw2, mappedFlws.get(0));
    }

    @Test
    public void shouldContinueIfFlwMappingFails() {
        Flw expectedFlw2 = createFlw(2, "firstName2", "lastName2");

        final Provider provider1 = provider("id", "1");
        final Provider provider2 = provider("id", "2");
        final ArrayList<Provider> providers = new ArrayList<Provider>() {{
            add(provider1);
            add(provider2);
        }};

        Map<String, Object> parsedMap1 = new HashMap<String, Object>() {{
            put("firstName", "firstName1");
            put("lastName", "lastName1");
        }};
        Map<String, Object> parsedMap2 = new HashMap<String, Object>() {{
            put("firstName", "firstName2");
            put("lastName", "lastName2");
        }};

        when(providerParser.parse(provider1)).thenReturn(parsedMap1);
        when(providerParser.parse(provider2)).thenReturn(parsedMap2);
        when(providerSyncMapper.map(Flw.class, parsedMap1)).thenThrow(new RuntimeException("some exception"));
        when(providerSyncMapper.map(Flw.class, parsedMap2)).thenReturn(expectedFlw2);

        providerSyncProcessor.processProviderSync(providers);

        verify(providerParser).parse(provider1);
        verify(providerParser).parse(provider2);
        verify(providerSyncMapper).map(Flw.class, parsedMap1);
        verify(providerSyncMapper).map(Flw.class, parsedMap2);

        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(Flw.class), flwArgumentCaptor.capture());
        List<Flw> mappedFlws = flwArgumentCaptor.getValue();
        assertEquals(1, mappedFlws.size());
        assertEquals(expectedFlw2, mappedFlws.get(0));
    }

    @Test
    public void shouldGetFlwGroupsAndAssociateItWithFlw() {
        Flw expectedFlw = createFlw(1, "firstName1", "lastName1");

        final String groupId1 = "groupId1";
        final String groupId2 = "groupId2";
        final Provider provider = provider("groups", new ArrayList<String>() {{
            add(groupId1);
            add(groupId2);
        }});
        FlwGroup expectedFlwGroup1 = new FlwGroup();
        FlwGroup expectedFlwGroup2 = new FlwGroup();
        HashMap<String, Object> parsedMap = new HashMap<>();
        when(providerParser.parse(provider)).thenReturn(parsedMap);
        when(providerSyncMapper.map(Flw.class, parsedMap)).thenReturn(expectedFlw);
        when(careService.getOrCreateGroup(groupId1)).thenReturn(expectedFlwGroup1);
        when(careService.getOrCreateGroup(groupId2)).thenReturn(expectedFlwGroup2);

        providerSyncProcessor.processProviderSync(new ArrayList<Provider>() {{
            add(provider);
        }});

        verify(providerSyncMapper).map(Flw.class, parsedMap);

        verify(careService).getOrCreateGroup(groupId1);
        verify(careService).getOrCreateGroup(groupId2);
        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(Flw.class), flwArgumentCaptor.capture());
        Flw actualFlw = flwArgumentCaptor.getValue().get(0);
        assertEquals(expectedFlw, actualFlw);
        Set<FlwGroup> actualFlwGroups = actualFlw.getFlwGroups();
        assertEquals(2, actualFlwGroups.size());
        assertTrue(actualFlwGroups.contains(expectedFlwGroup1));
        assertTrue(actualFlwGroups.contains(expectedFlwGroup2));
    }

    @Test
    public void shouldGetLocationDimensionsAndAssociateItWithFlw() {
        Flw expectedFlw = createFlw(1, "firstName1", "lastName1");

        final Provider provider = new Provider();

        final String state = "BIHAR";
        final String district = "ARARIA";
        final String block = "BHARGAMA";

        Map<String, Object> parsedMap = getLocationMap(state, district, block);
        when(providerParser.parse(provider)).thenReturn(parsedMap);
        when(providerSyncMapper.map(Flw.class, parsedMap)).thenReturn(expectedFlw);
        when(careService.getLocation(state, district, block)).thenReturn(new LocationDimension(state, district, block));

        providerSyncProcessor.processProviderSync(new ArrayList<Provider>() {{
            add(provider);
        }});

        verify(providerSyncMapper).map(Flw.class, parsedMap);

        verify(careService).getLocation(state, district, block);
        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(Flw.class), flwArgumentCaptor.capture());
        Flw actualFlw = flwArgumentCaptor.getValue().get(0);
        assertEquals(expectedFlw, actualFlw);
        final LocationDimension locationDimension = actualFlw.getLocationDimension();
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
        Flw expectedFlw1 = createFlw(1, "firstName1", "lastName1");
        Flw expectedFlw2 = createFlw(2, "firstName2", "lastName2");

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
        HashMap<String, Object> parsedMap1 = new HashMap<String, Object>(){{
            put("firstName", "firstName1");
            put("lastName", "lastName1");
        }};
        HashMap<String, Object> parsedMap2 = new HashMap<String, Object>(){{
            put("firstName", "firstName2");
            put("lastName", "lastName2");
        }};
        when(providerParser.parse(provider1)).thenReturn(parsedMap1);
        when(providerParser.parse(provider2)).thenReturn(parsedMap2);

        when(providerSyncMapper.map(Flw.class, parsedMap1)).thenReturn(expectedFlw1);
        when(providerSyncMapper.map(Flw.class, parsedMap2)).thenReturn(expectedFlw2);

        when(careService.getOrCreateGroup(groupId1)).thenReturn(expectedFlwGroup1);
        when(careService.getOrCreateGroup(groupId2)).thenReturn(expectedFlwGroup2);

        providerSyncProcessor.processProviderSync(new ArrayList<Provider>() {{
            add(provider1);
            add(provider2);
        }});

        verify(providerSyncMapper).map(Flw.class, parsedMap1);
        verify(providerSyncMapper).map(Flw.class, parsedMap2);

        verify(careService).getOrCreateGroup(groupId1);
        verify(careService).getOrCreateGroup(groupId2);


        verify(careService).saveOrUpdateAllByExternalPrimaryKey(eq(Flw.class), flwArgumentCaptor.capture());
        Flw actualFlw1 = flwArgumentCaptor.getValue().get(0);
        assertEquals(expectedFlw1, actualFlw1);
        Set<FlwGroup> actualFlwGroupsForProvider1 =  actualFlw1.getFlwGroups();
        assertEquals(2, actualFlwGroupsForProvider1.size());
        assertTrue(actualFlwGroupsForProvider1.contains(expectedFlwGroup1));
        assertTrue(actualFlwGroupsForProvider1.contains(expectedFlwGroup2));

        Flw actualFlw2 = flwArgumentCaptor.getValue().get(1);
        assertEquals(expectedFlw2, actualFlw2);
        Set<FlwGroup> actualFlwGroupsForProvider2 = actualFlw2.getFlwGroups();
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

    private FlwGroup createFlwGroup(int id, String name) {
        FlwGroup flwGroup = new FlwGroup();
        flwGroup.setId(id);
        flwGroup.setName(name);
        return flwGroup;
    }

    private Flw createFlw(int id, String firstName, String lastName) {
        Flw flw = new Flw();
        flw.setId(id);
        flw.setFirstName(firstName);
        flw.setLastName(lastName);
        return flw;
    }
}
