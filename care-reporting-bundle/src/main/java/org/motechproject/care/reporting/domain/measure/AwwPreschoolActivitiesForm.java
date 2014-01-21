package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
@Table(name = "aww_preschool_activities_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class AwwPreschoolActivitiesForm extends Form {

    private Integer id;
    private Flw flw;
    private Date dateModified;
    private Date timeStart;
    private Date timeEnd;
    private Date creationTime;
    private String menu;
    private String activity;
    private String success;
    private Integer numChildren;
    private String childIds;

    public AwwPreschoolActivitiesForm() {

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
    @JoinColumn(name = "user_id")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT})
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

    @Column(name = "menu")
    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    @Column(name = "activity")
    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Column(name = "success")
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Column(name = "num_children")
    public Integer getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(Integer numChildren) {
        this.numChildren = numChildren;
    }

    @Column(name = "child_ids")
    public String getChildIds() {
        return childIds;
    }

    public void setChildIds(String childIds) {
        this.childIds = childIds;
    }
}
