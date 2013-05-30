package org.motechproject.care.reporting.service;

import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.repository.Repository;
import org.motechproject.care.reporting.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CareService implements Service {

    private Repository dbRepository;

    @Autowired
    public CareService(Repository dbRepository){
        this.dbRepository = dbRepository;
    }

    @Override
    public <T> Integer save(T instance) {
        return dbRepository.save(instance);
    }


    @Override
    public MotherCase getMotherCase(String caseId){
        return get("caseId", caseId, MotherCase.class);
    }

    @Override
    public Flw getFlw(String flwId) {
        return get("name", flwId, Flw.class);
    }

    private <T> T get(String fieldName, String value, Class<T> type){
        T instance = dbRepository.get(fieldName, value, type);
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
