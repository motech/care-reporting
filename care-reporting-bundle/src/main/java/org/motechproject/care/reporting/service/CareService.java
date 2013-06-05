package org.motechproject.care.reporting.service;

import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.repository.Repository;
import org.motechproject.care.reporting.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public <T> T get(Class<T> type, String fieldName, String value){
        T instance = dbRepository.get(type, fieldName, value);
        if(null != instance)
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
