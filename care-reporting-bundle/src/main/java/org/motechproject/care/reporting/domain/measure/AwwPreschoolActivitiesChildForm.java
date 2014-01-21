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
@Table(name = "aww_preschool_activities_child_form", uniqueConstraints = @UniqueConstraint(
        columnNames = {"instance_id","case_id"}))
public class AwwPreschoolActivitiesChildForm extends Form {

    private Integer id;
    private AwwPreschoolActivitiesForm form;
    private ChildCase childCase;
    private Flw flw;
    private Date dateModified;
    private Date timeStart;
    private Date timeEnd;
    private Date creationTime;
    private String caseId;
    private String childAttend;
    private String breakfast;
    private String participated;
    private String lunch;

    public AwwPreschoolActivitiesChildForm() {

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
    @JoinColumn(name = "form_id")
    @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
    public AwwPreschoolActivitiesForm getForm() {
        return form;
    }

    public void setForm(AwwPreschoolActivitiesForm form) {
        this.form = form;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id")
    @Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
    public ChildCase getChildCase() {
        return this.childCase;
    }

    public void setChildCase(ChildCase childCase) {
        this.childCase = childCase;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
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

    @Column(name = "caseid")
    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    @Column(name = "child_attend")
    public String getChildAttend() {
        return childAttend;
    }

    public void setChildAttend(String childAttend) {
        this.childAttend = childAttend;
    }

    @Column(name = "breakfast")
    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    @Column(name = "participated")
    public String getParticipated() {
        return participated;
    }

    public void setParticipated(String participated) {
        this.participated = participated;
    }

    @Column(name = "lunch")
    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }
}
