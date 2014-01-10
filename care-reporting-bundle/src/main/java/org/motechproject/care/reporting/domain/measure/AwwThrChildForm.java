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
import java.util.Date;

@Entity
@Table(name = "aww_child_thr", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class AwwThrChildForm extends Form {

    private int id;
    private Flw flw;
    private ChildCase childCase;
    private Date timeStart;
    private Date timeEnd;
    private Date dateModified;
    private Date creationTime;
    private String collectRation;
    private String childDistributeRation;
    private Integer childDaysRationGiven;
    private String childAmountGiven;
    private String childCauseNotGiven;
    private String success;
    private String childName;
    private String motherName;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
    public Flw getFlw() {
        return flw;
    }

    public void setFlw(Flw flw) {
        this.flw = flw;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id")
    @Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
    public ChildCase getChildCase() {
        return childCase;
    }

    public void setChildCase(ChildCase childCase) {
        this.childCase = childCase;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_start")
    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_end")
    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_modified")
    public Date getDateModified() {
        return dateModified;
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

    @Column(name = "collect_ration")
    public String getCollectRation() {
        return collectRation;
    }

    public void setCollectRation(String collectRation) {
        this.collectRation = collectRation;
    }

    @Column(name = "child_distribute_ration")
    public String getChildDistributeRation() {
        return childDistributeRation;
    }

    public void setChildDistributeRation(String childDistributeRation) {
        this.childDistributeRation = childDistributeRation;
    }

    @Column(name = "child_days_ration_given")
    public Integer getChildDaysRationGiven() {
        return childDaysRationGiven;
    }

    public void setChildDaysRationGiven(Integer childDaysRationGiven) {
        this.childDaysRationGiven = childDaysRationGiven;
    }

    @Column(name = "child_amount_given")
    public String getChildAmountGiven() {
        return childAmountGiven;
    }

    public void setChildAmountGiven(String childAmountGiven) {
        this.childAmountGiven = childAmountGiven;
    }

    @Column(name = "child_cause_not_given")
    public String getChildCauseNotGiven() {
        return childCauseNotGiven;
    }

    public void setChildCauseNotGiven(String childCauseNotGiven) {
        this.childCauseNotGiven = childCauseNotGiven;
    }

    @Column(name = "success")
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Column(name = "child_name")
    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    @Column(name = "mother_name")
    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
}
