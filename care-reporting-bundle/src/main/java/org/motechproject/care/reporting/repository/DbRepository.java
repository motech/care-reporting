package org.motechproject.care.reporting.repository;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DbRepository implements org.motechproject.care.reporting.repository.Repository {
    @Autowired
    private DataAccessTemplate template;

    @Override
    public <T> Integer save(T instance) {
        return (Integer) template.save(instance);
    }

    @Override
    public <T> void saveOrUpdateAll(List<T> instances) {
        template.saveOrUpdateAll(instances);
    }

    @Override
    public <T> T get(Class<T> entityClass, int id) {
        return template.get(entityClass, id);
    }

    @Override
    public <T> T get(Class<T> entityClass, String fieldName, Object value) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        criteria.add(Restrictions.eq(fieldName, value));

        @SuppressWarnings("unchecked")
        List<T> byCriteria = template.findByCriteria(criteria);
        if (CollectionUtils.isEmpty(byCriteria))
            return null;
        else
            return byCriteria.get(0);
    }
}

