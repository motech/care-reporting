package org.motechproject.care.reporting.domain.measure;

// Generated Jun 4, 2013 4:50:32 PM by Hibernate Tools 3.4.0.CR1

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;

/**
 * CfMotherForm generated by hbm2java
 */
@Entity
@Table(name = "cf_mother_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class CfMotherForm implements java.io.Serializable {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private String instanceId;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Date dateCf1;
	private Date dateCf2;
	private Date dateCf3;
	private Date dateCf4;
	private Date dateCf5;
	private Date dateCf6;
	private Date dateLastVisit;
	private Date dateNextCf;
	private String lastVisitType;
	private Short cfVisitNum;
    private Short numChildren;
	private Boolean playCompFeedingVid;
	private Boolean lastvisit;
	private Date dateCf7;
	private Boolean confirmClose;
	private Boolean close;
    private Date creationTime = new Date();

    public CfMotherForm() {
	}

	public CfMotherForm(int id) {
		this.id = id;
	}

	public CfMotherForm(int id, Flw flw, MotherCase motherCase,
                        String instanceId, Date timeEnd, Date timeStart, Date dateModified,
                        Date dateCf1, Date dateCf2, Date dateCf3, Date dateCf4,
                        Date dateCf5, Date dateCf6, Date dateLastVisit, Date dateNextCf,
                        String lastVisitType, Short cfVisitNum,
                        Short numChildren, Boolean playCompFeedingVid, Boolean lastvisit,
                        Date dateCf7, Boolean confirmClose, Boolean close, Date creationTime) {
		this.id = id;
		this.flw = flw;
		this.motherCase = motherCase;
		this.instanceId = instanceId;
		this.timeEnd = timeEnd;
		this.timeStart = timeStart;
		this.dateModified = dateModified;
		this.dateCf1 = dateCf1;
		this.dateCf2 = dateCf2;
		this.dateCf3 = dateCf3;
		this.dateCf4 = dateCf4;
		this.dateCf5 = dateCf5;
		this.dateCf6 = dateCf6;
		this.dateLastVisit = dateLastVisit;
		this.dateNextCf = dateNextCf;
		this.lastVisitType = lastVisitType;
		this.cfVisitNum = cfVisitNum;
        this.numChildren = numChildren;
		this.playCompFeedingVid = playCompFeedingVid;
		this.lastvisit = lastvisit;
		this.dateCf7 = dateCf7;
		this.confirmClose = confirmClose;
		this.close = close;
        this.creationTime = creationTime;
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
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	public Flw getFlw() {
		return this.flw;
	}

	public void setFlw(Flw flw) {
		this.flw = flw;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	public MotherCase getMotherCase() {
		return this.motherCase;
	}

	public void setMotherCase(MotherCase motherCase) {
		this.motherCase = motherCase;
	}

	@Column(name = "instance_id", unique = true, length = 36)
	public String getInstanceId() {
		return this.instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_end", length = 35)
	public Date getTimeEnd() {
		return this.timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_start", length = 35)
	public Date getTimeStart() {
		return this.timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modified", length = 35)
	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_1", length = 13)
	public Date getDateCf1() {
		return this.dateCf1;
	}

	public void setDateCf1(Date dateCf1) {
		this.dateCf1 = dateCf1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_2", length = 13)
	public Date getDateCf2() {
		return this.dateCf2;
	}

	public void setDateCf2(Date dateCf2) {
		this.dateCf2 = dateCf2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_3", length = 13)
	public Date getDateCf3() {
		return this.dateCf3;
	}

	public void setDateCf3(Date dateCf3) {
		this.dateCf3 = dateCf3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_4", length = 13)
	public Date getDateCf4() {
		return this.dateCf4;
	}

	public void setDateCf4(Date dateCf4) {
		this.dateCf4 = dateCf4;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_5", length = 13)
	public Date getDateCf5() {
		return this.dateCf5;
	}

	public void setDateCf5(Date dateCf5) {
		this.dateCf5 = dateCf5;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_6", length = 13)
	public Date getDateCf6() {
		return this.dateCf6;
	}

	public void setDateCf6(Date dateCf6) {
		this.dateCf6 = dateCf6;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_last_visit", length = 13)
	public Date getDateLastVisit() {
		return this.dateLastVisit;
	}

	public void setDateLastVisit(Date dateLastVisit) {
		this.dateLastVisit = dateLastVisit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_cf", length = 13)
	public Date getDateNextCf() {
		return this.dateNextCf;
	}

	public void setDateNextCf(Date dateNextCf) {
		this.dateNextCf = dateNextCf;
	}

	@Column(name = "last_visit_type", length = 20)
	public String getLastVisitType() {
		return this.lastVisitType;
	}

	public void setLastVisitType(String lastVisitType) {
		this.lastVisitType = lastVisitType;
	}

	@Column(name = "cf_visit_num")
	public Short getCfVisitNum() {
		return this.cfVisitNum;
	}

	public void setCfVisitNum(Short cfVisitNum) {
		this.cfVisitNum = cfVisitNum;
	}

    @Column(name = "num_children")
	public Short getNumChildren() {
		return this.numChildren;
	}

	public void setNumChildren(Short numChildren) {
		this.numChildren = numChildren;
	}

	@Column(name = "play_comp_feeding_vid")
	public Boolean getPlayCompFeedingVid() {
		return this.playCompFeedingVid;
	}

	public void setPlayCompFeedingVid(Boolean playCompFeedingVid) {
		this.playCompFeedingVid = playCompFeedingVid;
	}

	@Column(name = "lastvisit")
	public Boolean getLastvisit() {
		return this.lastvisit;
	}

	public void setLastvisit(Boolean lastvisit) {
		this.lastvisit = lastvisit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_7", length = 13)
	public Date getDateCf7() {
		return this.dateCf7;
	}

	public void setDateCf7(Date dateCf7) {
		this.dateCf7 = dateCf7;
	}

	@Column(name = "confirm_close")
	public Boolean getConfirmClose() {
		return this.confirmClose;
	}

	public void setConfirmClose(Boolean confirmClose) {
		this.confirmClose = confirmClose;
	}

	@Column(name = "close")
	public Boolean getClose() {
		return this.close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return FormToString.toString(this);
    }
}
