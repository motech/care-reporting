package org.motechproject.care.reporting.repository;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DbRepository {

    @Autowired
    private DataAccessTemplate template;

    public <T> Integer save(T instance) {
        return (Integer) template.save(instance);
    }

    public <T> T get(int id, Class<T> entityClass) {
        return template.get(entityClass, id);
    }

    public <T> T get(String fieldName, Object value, Class<T> entityClass) {
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

