package org.motechproject.care.reporting.ft.reporting;

import org.motechproject.care.reporting.ft.utils.TestEnvironment;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class ReportingDatabase {
    private TestEnvironment environment;
    private Connection connection;
    public final Table newForm;
    public final Table registrationMotherForm;
    public final Table motherCase;
    public final Table childCase;
    public final Table flw;
    public final Table flwGroup;
    public final Table flwGroupMap;

    private List<Table> motherFormTables;
    private List<Table> childFormTables;

    public ReportingDatabase() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.postgresql.Driver");
        environment = new TestEnvironment();
        connection = DriverManager.getConnection(environment.getDbConnectionString(), environment.getProperty("postgres.user"), environment.getProperty("postgres.password"));
        newForm = new Table("report.new_form", "instance_id", connection);
        registrationMotherForm = new Table("report.registration_mother_form", "instance_id", connection);
        motherCase = new Table("report.mother_case", "case_id", connection);
        childCase = new Table("report.child_case", "case_id", connection);
        flw = new Table("report.flw", "flw_id", connection);
        flwGroup = new Table("report.flw_group", "group_id", connection);
        flwGroupMap = new Table("report.flw_group_map", "flw_id", connection);

        motherFormTables = Arrays.asList(newForm, registrationMotherForm);
        childFormTables = Arrays.asList();
    }

    @PreDestroy
    public void close() {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteMother(String caseId) {
        deletePatient(caseId, motherCase, motherFormTables);
    }

    public void deleteChild(String caseId) {
        deletePatient(caseId, childCase, childFormTables);
    }

    private void deletePatient(String caseId, Table caseTable, List<Table> formTables)  {
        Map<String,Object> mother = caseTable.find(caseId);
        if(mother == null) {
            return;
        }

        Integer motherId = (Integer) mother.get("id");

        for(Table formTable: formTables) {
            formTable.deleteBy("case_id", motherId);
        }

        caseTable.delete(caseId);
    }

    public void deleteFLW(String flwId) {
        Map<String, Object> flwMap = flw.find(flwId);
        if(flwMap == null) {
            return;
        }

        Integer flwDbId = (Integer) flwMap.get("id");
        flwGroupMap.delete(flwDbId);

        flw.delete(flwId);
    }

    public void deleteGroup(String groupId) {
        Map<String, Object> flwGroupMap = flwGroup.find(groupId);
        if(flwGroupMap == null) {
            return;
        }

        Integer flwGroupDbId = (Integer) flwGroupMap.get("id");
        this.flwGroupMap.deleteBy("group_id", flwGroupDbId);

        flwGroup.delete(groupId);
    }
}
