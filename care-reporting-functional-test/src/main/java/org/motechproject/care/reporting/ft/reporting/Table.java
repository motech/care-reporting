package org.motechproject.care.reporting.ft.reporting;

import org.motechproject.care.reporting.ft.utils.TimedRunner;

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
        if(value instanceof String) {
            statement.setString(index, (String) value);
            return;
        }

        if(value instanceof Integer) {
            statement.setInt(1, (Integer) value);
            return;
        }

       throw new RuntimeException("Could not set value on statement. Do the instance check and add it here.'");

    }

    public Map<String, Object> find(Object businessId) {
        return findBy(businessIdColumn, businessId);

    }

    public Map<String, Object> findBy(String columnName, Object columnValue) {
        return fetch(columnName, columnValue, false);
    }

    public Map<String, Object> waitAndGet(Object businessId) {
        return waitAndGetBy(businessIdColumn, businessId);

    }

    public Map<String, Object> waitAndGetBy(String columnName, Object columnValue) {
        Map<String, Object> map = fetch(columnName, columnValue, true);

        if(map == null) {
            throw new RuntimeException(format("Did not find record in table: %s; id column: %s; id: %s", tableName, columnName, columnValue));
        }

        return map;
    }

    private Map<String, Object> fetch(String columnName, Object columnValue, boolean retry) {
        try {
            final PreparedStatement stmt = connection.prepareStatement(format("SELECT * FROM %s WHERE %s = ? LIMIT 1", tableName, columnName));
            setParameter(stmt, columnValue, 1);

            ResultSet resultSet;

            if(retry) {
                resultSet = fetchWithRetry(stmt);
            } else {
                resultSet = execute(stmt);
            }

            if(resultSet == null) {
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

    private static ResultSet execute(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next() ? resultSet : null;
    }

    private ResultSet fetchWithRetry(final PreparedStatement preparedStatement) {
        TimedRunner<ResultSet> timedRunner = new TimedRunner<ResultSet>(5, 100) {
            @Override
            protected ResultSet run() throws Exception {
                return execute(preparedStatement);
            }
        };

        try {
            return timedRunner.executeWithTimeout();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
