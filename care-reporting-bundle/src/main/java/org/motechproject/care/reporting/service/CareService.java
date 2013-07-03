package org.motechproject.care.reporting.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.motechproject.care.reporting.domain.SelfUpdatable;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.enums.CaseType;
import org.motechproject.care.reporting.factory.FormFactory;
import org.motechproject.care.reporting.mapper.CareReportingMapper;
import org.motechproject.care.reporting.processors.CaseNotFoundException;
import org.motechproject.care.reporting.repository.Repository;
import org.motechproject.care.reporting.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.motechproject.care.reporting.utils.AnnotationUtils.getExternalPrimaryKeyField;
import static org.motechproject.care.reporting.utils.AnnotationUtils.getExternalPrimaryKeyValue;

@Service
@Transactional
public class CareService implements org.motechproject.care.reporting.service.Service {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    private Repository dbRepository;
    private CareReportingMapper careReportingMapper;

    @Autowired
    public CareService(Repository dbRepository) {
        this.dbRepository = dbRepository;
        this.careReportingMapper = new CareReportingMapper(this);
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
        if (persistedObject == null)
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
    public <T> T get(Class<T> type, String fieldName, Object value) {
        return dbRepository.get(type, fieldName, value);
    }

    @Override
    public <T> T get(Class<T> type, Map<String, Object> fieldMap, Map<String, String> aliasMapping) {
        return dbRepository.get(type, fieldMap, aliasMapping);
    }

    @Override
    public void processAndSaveForms(Map<String, String> motherFormValues, List<Map<String, String>> childFormValues) {
        if(motherFormValues != null)
            saveMotherForm(motherFormValues);

        List<Serializable> childForms = new ArrayList<>();
        for (Map<String, String> childFormValue : childFormValues) {
            String xmlns = childFormValue.get("xmlns");
            String instanceId = childFormValue.get("instanceId");
            Class<?> childFormClass = FormFactory.getForm(xmlns, CaseType.CHILD);
            if(childFormClass == null){
                logger.warn(String.format("No form found for xmlns:%s, instanceId:%s", xmlns, instanceId));
                continue;
            }

            if (formExists(childFormClass, instanceId, childFormValue.get("caseId")))
                continue;

            childForms.add((Serializable) careReportingMapper.map(childFormValue, childFormClass));
        }

        for (Serializable childForm : childForms) {
            dbRepository.save(childForm);
        }
    }

    private boolean formExists(Class<?> type, String instanceId, String caseId) {
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("instanceId", instanceId);
        fieldMap.put("cc.caseId", caseId);

        Map<String, String> aliasMap = new HashMap<>();
        aliasMap.put("childCase", "cc");

        Object existingForm = get(type, fieldMap, aliasMap);
        if (existingForm != null) {
            logger.warn(format("Cannot save Form: %s. Form with same instanceId (%s) and caseId %s already exists: %s", type, instanceId, caseId, existingForm));
            return true;
        }
        return false;
    }


    private void saveMotherForm(Map<String, String> motherFormValues) {
        String xmlns = motherFormValues.get("xmlns");
        Class<?> motherFormClass = FormFactory.getForm(xmlns, CaseType.MOTHER);
        String instanceId = motherFormValues.get("instanceId");
        if(motherFormClass == null) {
            logger.warn(String.format("No form found for xmlns:%s, instanceId:%s", xmlns, instanceId));
            return;
        }

        Object existingForm = get(motherFormClass, "instanceId", instanceId);
        if (existingForm != null) {
            logger.warn(format("Cannot save Form: %s. Form with same instanceId (%s) already exists: %s", motherFormClass, instanceId, existingForm));
            return;
        }
        Object motherForm = careReportingMapper.map(motherFormValues, motherFormClass);
        dbRepository.save(motherForm);
    }

    @Override
    public <T extends SelfUpdatable<T>> T saveByExternalPrimaryKey(Class<T> entityClass, Map<String, String> values) {
        T entity = careReportingMapper.map(values, entityClass);
        saveOrUpdateByExternalPrimaryKey(entity);
        return entity;
    }

    @Override
    public void updateCase(String caseId, Map<String, String> updatedValues) {
        MotherCase motherCase = getMotherCase(caseId);
        if (motherCase != null) {
            careReportingMapper.map(motherCase, updatedValues);
            update(motherCase);
        } else {
            ChildCase childCase = getChildCase(caseId);
            if (childCase == null) {
                logger.warn(format("Cannot find case %s to update", caseId));
                throw new CaseNotFoundException(caseId);
            }
            careReportingMapper.map(childCase, updatedValues);
            update(childCase);
        }
    }
}
