package org.motechproject.care.reporting.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.repository.Repository;
import org.motechproject.care.reporting.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CareService implements org.motechproject.care.reporting.service.Service {
    @Autowired
    private Repository dbRepository;

    public CareService() {
    }

    public CareService(Repository dbRepository) {
        this.dbRepository = dbRepository;
    }

    @Override
    public <T> Integer save(T instance) {
        return dbRepository.save(instance);
    }

    @Override
    public <T> void saveOrUpdateAll(List<T> instances) {
        dbRepository.saveOrUpdateAll(instances);
    }

    @Override
    public void saveOrUpdateGroups(List<FlwGroup> updatedGroups) {
        List<FlwGroup> existingGroups = findGroupsInDb(updatedGroups);
        List<FlwGroup> toBeSavedGroups = processToBeSavedGroups(updatedGroups, existingGroups);
        dbRepository.saveOrUpdateAll(toBeSavedGroups);
    }

    private List<FlwGroup> processToBeSavedGroups(List<FlwGroup> updatedGroups, List<FlwGroup> existingGroups) {
        List<FlwGroup> toBeSavedGroups = new ArrayList<>();
        for (final FlwGroup updatedGroup : updatedGroups) {
            FlwGroup existingGroup = (FlwGroup) CollectionUtils.find(existingGroups, new Predicate() {
                @Override
                public boolean evaluate(Object object) {
                    return ((FlwGroup) object).getGroupId().equals(updatedGroup.getGroupId());
                }
            });
            if (existingGroup != null) {
                existingGroup.updateFrom(updatedGroup);
                toBeSavedGroups.add(existingGroup);
            } else {
                toBeSavedGroups.add(updatedGroup);
            }
        }
        return toBeSavedGroups;
    }

    private List<FlwGroup> findGroupsInDb(List<FlwGroup> groups) {
        List<String> groupIds = new ArrayList<>();
        CollectionUtils.collect(groups, new Transformer() {
            @Override
            public Object transform(Object input) {
                return ((FlwGroup) input).getGroupId();
            }
        }, groupIds);
        return dbRepository.findAllByGroupId(groupIds);
    }

    @Override
    public FlwGroup getGroup(String groupId) {
        return get(FlwGroup.class, "groupId", groupId);
    }

    @Override
    public MotherCase getMotherCase(String caseId) {
        return get(MotherCase.class, "caseId", caseId);
    }

    @Override
    public ChildCase getChildCase(String caseId) {
        return get(ChildCase.class, "caseId", caseId);
    }

    @Override
    public Flw getFlw(String flwId) {
        return get(Flw.class, "flwId", flwId);
    }

    @Override
    public <T> T get(Class<T> type, String fieldName, String value) {
        T instance = dbRepository.get(type, fieldName, value);
        if (null != instance)
            return instance;

        T newInstance;
        try {
            newInstance = type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        ObjectUtils.set(newInstance, fieldName, value);

        return newInstance;
    }
}
