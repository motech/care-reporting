package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "aww_growth_monitoring_1_child_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class AwwGrowthMonitoringChildForm1 extends Form {

    private Integer id;
    private ChildCase childCase;
    private Flw flw;
    private Date dateModified;
    private Date timeStart;
    private Date timeEnd;
    private Date creationTime;
    private String currentGrowth;
    private String lastGrowth1;
    private String lastGrowth2;
    private String lastGrowth3;
    private Date currentWeightDate;
    private Date lastWeightDate;
    private BigDecimal currentWeight;
    private String lastWeight;
    private String takeWeight;
    private String childWeight;
    private String showGrade;
    private String requiresAttention;
    private String success;
    private Integer calcGrade;
    private String calcGrowth;
    private String childGender;
    private Integer childAge;
    private Date dob;
    private String gender;
    private String changeFromNormal;
    private String changeFromMuw;
    private String changeFromSuw;
    private Integer ageLastWeight;

    public AwwGrowthMonitoringChildForm1() {

    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id")
    @Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
    public ChildCase getChildCase() {
        return childCase;
    }

    public void setChildCase(ChildCase childCase) {
        this.childCase = childCase;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
    public Flw getFlw() {
        return this.flw;
    }

    public void setFlw(Flw flw) {
        this.flw = flw;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date_modified")
    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "time_start")
    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "time_end")
    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Column(name = "current_growth")
    public String getCurrentGrowth() {
        return currentGrowth;
    }

    public void setCurrentGrowth(String currentGrowth) {
        this.currentGrowth = currentGrowth;
    }

    @Column(name = "last_growth_1")
    public String getLastGrowth1() {
        return lastGrowth1;
    }

    public void setLastGrowth1(String lastGrowth1) {
        this.lastGrowth1 = lastGrowth1;
    }

    @Column(name = "last_growth_2")
    public String getLastGrowth2() {
        return lastGrowth2;
    }

    public void setLastGrowth2(String lastGrowth2) {
        this.lastGrowth2 = lastGrowth2;
    }

    @Column(name = "last_growth_3")
    public String getLastGrowth3() {
        return lastGrowth3;
    }

    public void setLastGrowth3(String lastGrowth3) {
        this.lastGrowth3 = lastGrowth3;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "current_weight_date")
    public Date getCurrentWeightDate() {
        return currentWeightDate;
    }

    public void setCurrentWeightDate(Date currentWeightDate) {
        this.currentWeightDate = currentWeightDate;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "last_weight_date")
    public Date getLastWeightDate() {
        return lastWeightDate;
    }

    public void setLastWeightDate(Date lastWeightDate) {
        this.lastWeightDate = lastWeightDate;
    }

    @Column(name = "current_weight")
    public BigDecimal getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(BigDecimal currentWeight) {
        this.currentWeight = currentWeight;
    }

    @Column(name = "last_weight")
    public String getLastWeight() {
        return lastWeight;
    }

    public void setLastWeight(String lastWeight) {
        this.lastWeight = lastWeight;
    }

    @Column(name = "take_weight")
    public String getTakeWeight() {
        return takeWeight;
    }

    public void setTakeWeight(String takeWeight) {
        this.takeWeight = takeWeight;
    }

    @Column(name = "child_weight")
    public String getChildWeight() {
        return childWeight;
    }

    public void setChildWeight(String childWeight) {
        this.childWeight = childWeight;
    }

    @Column(name = "show_grade")
    public String getShowGrade() {
        return showGrade;
    }

    public void setShowGrade(String showGrade) {
        this.showGrade = showGrade;
    }

    @Column(name = "requires_attention")
    public String getRequiresAttention() {
        return requiresAttention;
    }

    public void setRequiresAttention(String requiresAttention) {
        this.requiresAttention = requiresAttention;
    }

    @Column(name = "success")
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Column(name = "calc_grade")
    public Integer getCalcGrade() {
        return calcGrade;
    }

    public void setCalcGrade(Integer calcGrade) {
        this.calcGrade = calcGrade;
    }

    @Column(name = "calc_growth")
    public String getCalcGrowth() {
        return calcGrowth;
    }

    public void setCalcGrowth(String calcGrowth) {
        this.calcGrowth = calcGrowth;
    }

    @Column(name = "child_gender")
    public String getChildGender() {
        return childGender;
    }

    public void setChildGender(String childGender) {
        this.childGender = childGender;
    }

    @Column(name = "child_age")
    public Integer getChildAge() {
        return childAge;
    }

    public void setChildAge(Integer childAge) {
        this.childAge = childAge;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "dob")
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "change_from_normal")
    public String getChangeFromNormal() {
        return changeFromNormal;
    }

    public void setChangeFromNormal(String changeFromNormal) {
        this.changeFromNormal = changeFromNormal;
    }

    @Column(name = "change_from_muw")
    public String getChangeFromMuw() {
        return changeFromMuw;
    }

    public void setChangeFromMuw(String changeFromMuw) {
        this.changeFromMuw = changeFromMuw;
    }

    @Column(name = "change_from_suw")
    public String getChangeFromSuw() {
        return changeFromSuw;
    }

    public void setChangeFromSuw(String changeFromSuw) {
        this.changeFromSuw = changeFromSuw;
    }

    @Column(name = "age_last_weight")
    public Integer getAgeLastWeight() {
        return ageLastWeight;
    }

    public void setAgeLastWeight(Integer ageLastWeight) {
        this.ageLastWeight = ageLastWeight;
    }
}
