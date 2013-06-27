package org.motechproject.care.reporting.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.motechproject.care.reporting.domain.SelfUpdatable;
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

import static org.motechproject.care.reporting.utils.AnnotationUtils.getExternalPrimaryKeyValue;
import static org.motechproject.care.reporting.utils.AnnotationUtils.getExternalPrimaryKeyField;

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
    public <T> void update(T entity) {
        dbRepository.update(entity);
    }

    @Override
    public <T extends SelfUpdatable<T>> void saveOrUpdateByExternalPrimaryKey(T entity) {
        T persistedObject = dbRepository.findByExternalPrimaryKey(((Class<T>) entity.getClass()), getExternalPrimaryKeyValue(entity));
        if(persistedObject == null)
            dbRepository.save(entity);
        else {
            persistedObject.updateToLatest(entity);
            dbRepository.save(persistedObject);
        }
    }

    @Override
    public <T extends SelfUpdatable<T>> void saveOrUpdateAllByExternalPrimaryKey(Class clazz, List<T> updatedEntities) {
        List<T> existingEntities = findAllByExternalPrimaryKey(clazz, updatedEntities);
        List<T> toBeSavedEntities = processToBeSavedEntities(updatedEntities, existingEntities);
        dbRepository.saveOrUpdateAll(toBeSavedEntities);
    }

    private <T extends SelfUpdatable> List<T> processToBeSavedEntities(List<T> updatedEntities, List<T> existingEntities) {
        List<T> toBeSavedEntities = new ArrayList<>();
        for (final T updatedEntity : updatedEntities) {
            T existing = (T) CollectionUtils.find(existingEntities, new Predicate() {
                @Override
                public boolean evaluate(Object object) {
                    return getExternalPrimaryKeyValue(object).equals(getExternalPrimaryKeyValue(updatedEntity));
                }
            });
            if (existing != null) {
                existing.updateToLatest(updatedEntity);
                toBeSavedEntities.add(existing);
            } else {
                toBeSavedEntities.add(updatedEntity);
            }
        }
        return toBeSavedEntities;
    }

    private <T> List<T> findAllByExternalPrimaryKey(Class clazz, List<T> entities) {
        List<String> externalPrimaryKeyValues = new ArrayList<>();
        CollectionUtils.collect(entities, new Transformer() {
            @Override
            public Object transform(Object input) {
                return getExternalPrimaryKeyValue(input);
            }
        }, externalPrimaryKeyValues);
        return dbRepository.findAllByField(clazz, externalPrimaryKeyValues, getExternalPrimaryKeyField(clazz).getName());
    }

    @Override
    public FlwGroup getOrCreateGroup(String groupId) {
        return getOrCreateNew(FlwGroup.class, "groupId", groupId);
    }

    @Override
    public MotherCase getOrCreateMotherCase(String caseId) {
        return getOrCreateNew(MotherCase.class, "caseId", caseId);
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
    public ChildCase getOrCreateChildCase(String caseId) {
        return getOrCreateNew(ChildCase.class, "caseId", caseId);
    }

    @Override
    public Flw getOrCreateFlw(String flwId) {
        return getOrCreateNew(Flw.class, "flwId", flwId);
    }

    @Override
    public <T> T getOrCreateNew(Class<T> type, String fieldName, String value) {
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

    @Override
    public <T> T get(Class<T> type, String fieldName, String value) {
        return dbRepository.get(type, fieldName, value);
    }

}
