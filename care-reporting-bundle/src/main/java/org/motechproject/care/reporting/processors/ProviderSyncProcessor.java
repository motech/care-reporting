package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.mapper.GenericMapper;
import org.motechproject.care.reporting.parser.GroupParser;
import org.motechproject.care.reporting.service.CareService;
import org.motechproject.commcare.provider.sync.response.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProviderSyncProcessor {
    private GroupParser groupParser;
    private CareService careService;
    private GenericMapper genericMapper;

    @Autowired
    public ProviderSyncProcessor(GroupParser groupParser, CareService careService) {
        this.groupParser = groupParser;
        this.careService = careService;
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
}
