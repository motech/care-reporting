package org.motechproject.care.reporting.ft.reporting;

import org.motechproject.care.reporting.ft.utils.TestEnvironment;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
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
    private final Table registrationChildForm;
    private final Table abortForm;
    private final Table bpForm;
    private final Table cfChildForm;
    private final Table cfMotherForm;
    private final Table closeChildForm;
    private final Table closeMotherForm;
    private final Table deathChildForm;
    private final Table deathMotherForm;
    private final Table deliveryChildForm;
    private final Table deliveryMotherForm;
    private final Table ebfChildForm;
    private final Table ebfMotherForm;
    private final Table miForm;
    private final Table uiMotherForm;
    private final Table uiChildForm;
    private final Table referMotherForm;
    private final Table referChildForm;
    private final Table pncMotherForm;
    private final Table pncChildForm;
    private final Table moveBeneficiaryForm;
    private final Table motherEditForm;
    private final Table moForm;

    private Map<String, Table> tableMapper = new HashMap<>();

    public ReportingDatabase() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.postgresql.Driver");
        environment = new TestEnvironment();
        connection = DriverManager.getConnection(environment.getDbConnectionString(), environment.getProperty("postgres.user"), environment.getProperty("postgres.password"));

        newForm = getFormTable("new_form");
        registrationMotherForm = getFormTable("registration_mother_form");
        registrationChildForm = getFormTable("registration_child_form");
        abortForm = getFormTable("abort_form");
        bpForm = getFormTable("bp_form");
        cfChildForm = getFormTable("cf_child_form");
        cfMotherForm = getFormTable("cf_mother_form");
        closeChildForm = getFormTable("close_child_form");
        closeMotherForm = getFormTable("close_mother_form");
        deathChildForm = getFormTable("death_child_form");
        deathMotherForm = getFormTable("death_mother_form");
        deliveryChildForm = getFormTable("delivery_child_form");
        deliveryMotherForm = getFormTable("delivery_mother_form");
        ebfChildForm = getFormTable("ebf_child_form");
        ebfMotherForm = getFormTable("ebf_mother_form");
        miForm = getFormTable("mi_form");
        moForm = getFormTable("mo_form");
        motherEditForm = getFormTable("mother_edit_form");
        moveBeneficiaryForm = getFormTable("move_beneficiary_form");
        pncChildForm = getFormTable("pnc_child_form");
        pncMotherForm = getFormTable("pnc_mother_form");
        referChildForm = getFormTable("refer_child_form");
        referMotherForm = getFormTable("refer_mother_form");
        uiChildForm = getFormTable("ui_child_form");
        uiMotherForm = getFormTable("ui_mother_form");

        motherCase = new Table("report.mother_case", "case_id", connection);
        childCase = new Table("report.child_case", "case_id", connection);
        flw = new Table("report.flw", "flw_id", connection);
        flwGroup = new Table("report.flw_group", "group_id", connection);
        flwGroupMap = new Table("report.flw_group_map", "flw_id", connection);

        motherFormTables = Arrays.asList(newForm, registrationMotherForm);
        childFormTables = Arrays.asList();
    }

    private Table getFormTable(String tableName) {
        final Table table = new Table("report." + tableName, "instance_id", connection);
        tableMapper.put(tableName, table);
        return table;
    }

    public Table getTable(String tableName) {
        return tableMapper.get(tableName);
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
