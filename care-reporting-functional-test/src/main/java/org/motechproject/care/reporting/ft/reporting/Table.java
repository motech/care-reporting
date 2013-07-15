package org.motechproject.care.reporting.ft.reporting;

import org.motechproject.care.reporting.ft.utils.TimedRunner;
import org.motechproject.care.reporting.ft.utils.TimedRunnerBreakCondition;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.String.format;

public class Table {

    private final String tableName;
    private final String businessIdColumn;
    private final Connection connection;

    public Table(String tableName, String businessIdColumn, Connection connection) {
        this.tableName = tableName;
        this.businessIdColumn = businessIdColumn;
        this.connection = connection;
    }

    public void delete(Object businessId) {
        deleteBy(businessIdColumn, businessId);
    }

    public void deleteBy(String columnName, Object columnValue) {
        try {
            PreparedStatement statement = connection.prepareStatement(format("DELETE FROM %s WHERE %s=?", tableName, columnName));
            setParameter(statement, columnValue, 1);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setParameter(PreparedStatement statement, Object value, int index) throws SQLException {
        if (value instanceof String) {
            statement.setString(index, (String) value);
            return;
        }

        if (value instanceof Integer) {
            statement.setInt(1, (Integer) value);
            return;
        }

        throw new RuntimeException("Could not set value on statement. Do the instance check and add it here.'");

    }

    public Map<String, Object> find(Object businessId) {
        return findBy(businessIdColumn, businessId);
    }

    public Map<String, Object> findBy(String columnName, Object columnValue) {
        return fetch(columnName, columnValue);
    }

    public Map<String, Object> waitAndGet(Object businessId) {
        return waitAndGetBy(businessIdColumn, businessId, 100, 100, null);
    }

    public Map<String, Object> waitAndGet(Object businessId, TimedRunnerBreakCondition<Map<String, Object>> timedRunnerBreakCondition) {
        return waitAndGetBy(businessIdColumn, businessId, 100, 100, timedRunnerBreakCondition);
    }

    public Map<String, Object> waitAndGet(Object businessId, int tries, int intervalSleep, TimedRunnerBreakCondition<Map<String, Object>> timedRunnerBreakCondition) {
        return waitAndGetBy(businessIdColumn, businessId, tries, intervalSleep, timedRunnerBreakCondition);
    }

    public Map<String, Object> waitAndGetBy(final String columnName, final Object columnValue, int tries, int intervalSleep, TimedRunnerBreakCondition<Map<String, Object>> timedRunnerBreakCondition) {
        Map<String, Object> map = new TimedRunner<Map<String, Object>>(tries, intervalSleep, timedRunnerBreakCondition) {
            @Override
            protected Map<String, Object> run() {
                return fetch(columnName, columnValue);
            }
        }.executeWithTimeout();

        if (map == null) {
            throw new RecordNotFoundException(format("Did not find record in table: %s; id column: %s; id: %s", tableName, columnName, columnValue));
        }
        return map;
    }

    private Map<String, Object> fetch(String columnName, Object columnValue) {
        try {
            final PreparedStatement stmt = connection.prepareStatement(format("SELECT * FROM %s WHERE %s = ? LIMIT 1", tableName, columnName));
            setParameter(stmt, columnValue, 1);

            ResultSet resultSet = execute(stmt);

            if (resultSet == null) {
                return null;
            }

            Map<String, Object> record = new LinkedHashMap<String, Object>();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int totalColumns = metaData.getColumnCount();

            for (int i = 1; i <= totalColumns; i++) {
                Object value = resultSet.getObject(i);
                record.put(metaData.getColumnName(i), value);
            }
            return record;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ResultSet execute(PreparedStatement preparedStatement) {
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
