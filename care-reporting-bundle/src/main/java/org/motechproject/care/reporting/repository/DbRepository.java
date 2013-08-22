package org.motechproject.care.reporting.repository;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.motechproject.care.reporting.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.motechproject.care.reporting.utils.AnnotationUtils.getExternalPrimaryKeyField;

@Repository
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
    public <T> void update(T instance) {
        template.update(instance);
    }

    @Override
    public <T> void delete(T instance) {
        template.delete(instance);
        template.flush();
    }

    @Override
    public Object execute(final String query) {
        final ResultSet[] resultSet = new ResultSet[1];
        return template.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                session.doWork(new Work(){
                    @Override
                    public void execute(Connection connection) throws SQLException {
                        resultSet[0] = connection.prepareCall(query).executeQuery();
                    }
                });
                return resultSet[0] == null  || !resultSet[0].next() ? null : resultSet[0].getObject(1);
            }
        });
    }

    @Override
    public <T> List<T> findAllByField(Class<T> clazz, List<String> values, String fieldName) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        criteria.add(Restrictions.in(fieldName, values));
        return template.findByCriteria(criteria);
    }

    @Override
    public <T> T findByExternalPrimaryKey(Class<T> clazz, Object value) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        criteria.add(Restrictions.eq(getExternalPrimaryKeyField(clazz).getName(), value));
        List<T> results = template.findByCriteria(criteria);
        return ListUtils.safeGet(results, 0);
    }

    @Override
    public <T> T get(Class<T> entityClass, String fieldName, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(fieldName, value);

        return get(entityClass, map, new HashMap<String, String>());
    }

    @Override
    public <T> T get(Class<T> entityClass, Map<String, Object> fieldMap, Map<String, String> aliasMapping) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);

        if(MapUtils.isNotEmpty(aliasMapping)) {
            for (Map.Entry<String, String> alias : aliasMapping.entrySet()) {
                criteria.createAlias(alias.getKey(), alias.getValue());
            }
        }

        for (Map.Entry<String, Object> entry : fieldMap.entrySet()) {
            criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }

        @SuppressWarnings("unchecked")
        List<T> resultFromDb = template.findByCriteria(criteria);
        return CollectionUtils.isEmpty(resultFromDb) ? null : resultFromDb.get(0);
    }
}

