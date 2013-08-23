package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mother_edit_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class MotherEditForm extends Form {

    private int id;
    private Flw flw;
    private MotherCase motherCase;
    private Date timeEnd;
    private Date timeStart;
    private Date dateModified;
    private Date creationTime = new Date();
    private Integer age;
    private String caseName;
    private String motherName;
    private String updateMotherName;
    private Integer hhNumber;
    private String updateHhNumber;
    private Date motherDob;
    private String updateMotherDob;
    private Integer familyNumber;
    private String updateFamilyNumber;
    private String mobileNumber;
    private String updateMobileNumber;
    private Integer wardNumber;
    private String updateWardNumber;
    private String mobileNumberWhose;
    private String updateMobileNumberWhose;
    private String husbandName;
    private String updateHusbandName;


    public MotherEditForm() {

    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public Flw getFlw() {
        return this.flw;
    }

    public void setFlw(Flw flw) {
        this.flw = flw;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public MotherCase getMotherCase() {
        return this.motherCase;
    }

    public void setMotherCase(MotherCase motherCase) {
        this.motherCase = motherCase;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_end")
    public Date getTimeEnd() {
        return this.timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_start")
    public Date getTimeStart() {
        return this.timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_modified")
    public Date getDateModified() {
        return this.dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Column(name = "update_husband_name")
    public String getUpdateHusbandName() {
        return updateHusbandName;
    }

    public void setUpdateHusbandName(String updateHusbandName) {
        this.updateHusbandName = updateHusbandName;
    }

    @Column(name = "husband_name")
    public String getHusbandName() {
        return husbandName;
    }

    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName;
    }

    @Column(name = "update_mobile_number_whose")
    public String getUpdateMobileNumberWhose() {
        return updateMobileNumberWhose;
    }

    public void setUpdateMobileNumberWhose(String updateMobileNumberWhose) {
        this.updateMobileNumberWhose = updateMobileNumberWhose;
    }

    @Column(name = "mobile_number_whose")
    public String getMobileNumberWhose() {
        return mobileNumberWhose;
    }

    public void setMobileNumberWhose(String mobileNumberWhose) {
        this.mobileNumberWhose = mobileNumberWhose;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Column(name = "update_ward_number")
    public String getUpdateWardNumber() {
        return updateWardNumber;
    }

    public void setUpdateWardNumber(String updateWardNumber) {
        this.updateWardNumber = updateWardNumber;
    }

    @Column(name = "ward_number")
    public Integer getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(Integer wardNumber) {
        this.wardNumber = wardNumber;
    }

    @Column(name = "update_mobile_number")
    public String getUpdateMobileNumber() {
        return updateMobileNumber;
    }

    public void setUpdateMobileNumber(String updateMobileNumber) {
        this.updateMobileNumber = updateMobileNumber;
    }

    @Column(name = "mobile_number")
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Column(name = "update_family_number")
    public String getUpdateFamilyNumber() {
        return updateFamilyNumber;
    }

    public void setUpdateFamilyNumber(String updateFamilyNumber) {
        this.updateFamilyNumber = updateFamilyNumber;
    }

    @Column(name = "family_number")
    public Integer getFamilyNumber() {
        return familyNumber;
    }

    public void setFamilyNumber(Integer familyNumber) {
        this.familyNumber = familyNumber;
    }

    @Column(name = "update_mother_dob")
    public String getUpdateMotherDob() {
        return updateMotherDob;
    }

    public void setUpdateMotherDob(String updateMotherDob) {
        this.updateMotherDob = updateMotherDob;
    }

    @Column(name = "mother_dob")
    public Date getMotherDob() {
        return motherDob;
    }

    public void setMotherDob(Date motherDob) {
        this.motherDob = motherDob;
    }

    @Column(name = "update_hh_number")
    public String getUpdateHhNumber() {
        return updateHhNumber;
    }

    public void setUpdateHhNumber(String updateHhNumber) {
        this.updateHhNumber = updateHhNumber;
    }

    @Column(name = "hh_number")
    public Integer getHhNumber() {
        return hhNumber;
    }

    public void setHhNumber(Integer hhNumber) {
        this.hhNumber = hhNumber;
    }

    @Column(name = "update_mother_name")
    public String getUpdateMotherName() {
        return updateMotherName;
    }

    public void setUpdateMotherName(String updateMotherName) {
        this.updateMotherName = updateMotherName;
    }

    @Column(name = "mother_name")
    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    @Column(name = "case_name")
    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
}