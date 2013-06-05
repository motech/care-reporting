package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.mapper.GenericMapper;
import org.motechproject.care.reporting.parser.GroupParser;
import org.motechproject.care.reporting.parser.ProviderParser;
import org.motechproject.care.reporting.service.CareService;
import org.motechproject.commcare.provider.sync.response.Group;
import org.motechproject.commcare.provider.sync.response.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component
public class ProviderSyncProcessor {
    private GroupParser groupParser;
    private CareService careService;
    private GenericMapper genericMapper;
    private final ProviderParser providerParser;

    @Autowired
    public ProviderSyncProcessor(GroupParser groupParser, ProviderParser providerParser, CareService careService) {
        this.groupParser = groupParser;
        this.careService = careService;
        this.providerParser = providerParser;
        this.genericMapper = new GenericMapper();
    }

    public void processGroupSync(List<Group> groups) {
        List<FlwGroup> flwGroups = new ArrayList<>();
        for (Group group : groups) {
            Map<String, String> parsedGroups = groupParser.parse(group);
            flwGroups.add(genericMapper.map(parsedGroups, FlwGroup.class));
        }
        careService.saveOrUpdateAll(flwGroups);
    }

    public void processProviderSync(List<Provider> providers) {
        List<Flw> flws = new ArrayList<>();
        for (Provider provider : providers) {
            Map<String, String> parsedProvider = providerParser.parse(provider);
            Flw flw = genericMapper.map(parsedProvider, Flw.class);
            flw.setFlwGroups(new HashSet<>(getAssociatedFlwGroups(provider.getGroups())));
            flws.add(flw);
        }
        careService.saveOrUpdateAll(flws);
    }

    private List<FlwGroup> getAssociatedFlwGroups(List<String> groups) {
        ArrayList<FlwGroup> flwGroups = new ArrayList<>();
        if (groups == null)
            return flwGroups;
        for (String groupId : groups) {
            FlwGroup group = careService.getGroup(groupId);
            flwGroups.add(group);
        }
        return flwGroups;
    }
}
