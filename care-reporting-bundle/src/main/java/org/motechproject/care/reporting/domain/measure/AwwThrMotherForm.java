package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;

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
import java.util.Date;

@Entity
@Table(name = "aww_mother_thr_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class AwwThrMotherForm extends Form {

    private Integer id;
    private MotherCase motherCase;
    private Flw flw;
    private Date dateModified;
    private Date timeStart;
    private Date timeEnd;
    private Date creationTime;
    private String collectRation;
    private String distributeRation;
    private Integer daysRationGiven;
    private String causeNotGiven;
    private String success;
    private String addval;
    private String motherRations;
    private String motherName;

    public AwwThrMotherForm() {

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
    @Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
    public MotherCase getMotherCase() {
        return this.motherCase;
    }

    public void setMotherCase(MotherCase motherCase) {
        this.motherCase = motherCase;
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

    @Column(name = "collect_ration")
    public String getCollectRation() {
        return collectRation;
    }

    public void setCollectRation(String collectRation) {
        this.collectRation = collectRation;
    }

    @Column(name = "distribute_ration")
    public String getDistributeRation() {
        return distributeRation;
    }

    public void setDistributeRation(String distributeRation) {
        this.distributeRation = distributeRation;
    }

    @Column(name = "days_ration_given")
    public Integer getDaysRationGiven() {
        return daysRationGiven;
    }

    public void setDaysRationGiven(Integer daysRationGiven) {
        this.daysRationGiven = daysRationGiven;
    }

    @Column(name = "cause_not_given")
    public String getCauseNotGiven() {
        return causeNotGiven;
    }

    public void setCauseNotGiven(String causeNotGiven) {
        this.causeNotGiven = causeNotGiven;
    }

    @Column(name = "success")
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Column(name = "addval")
    public String getAddval() {
        return addval;
    }

    public void setAddval(String addval) {
        this.addval = addval;
    }

    @Column(name = "mother_rations")
    public String getMotherRations() {
        return motherRations;
    }

    public void setMotherRations(String motherRations) {
        this.motherRations = motherRations;
    }

    @Column(name = "mother_name")
    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
}
