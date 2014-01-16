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
@Table(name = "aww_reg_child_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class AwwRegisterChildForm extends Form {

	private int id;
	private Flw flw;
	private ChildCase childCase;
	private Date timeStart;
	private Date timeEnd;
	private Date dateModified;
	private Date creationTime;
	private Date dob;
	private String childAlive;
	private String gender;
	private String childName;
	private Integer ageEstMonths;
	private Integer ageEstYears;
	private Integer childMctsId;
	private String invalidOwner;
	private Integer fullChildMctsId;
	private String success;
	private String ownerIdCalc;

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
	@Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
	public Flw getFlw() {
		return flw;
	}

	public void setFlw(Flw flw) {
		this.flw = flw;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_id")
	@Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
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

	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "child_alive")
	public String getChildAlive() {
		return childAlive;
	}

	public void setChildAlive(String childAlive) {
		this.childAlive = childAlive;
	}
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "child_name")
	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	@Column(name = "age_est_months")
	public Integer getAgeEstMonths() {
		return ageEstMonths;
	}

	public void setAgeEstMonths(Integer ageEstMonths) {
		this.ageEstMonths = ageEstMonths;
	}

	@Column(name = "age_est_years")
	public Integer getAgeEstYears() {
		return ageEstYears;
	}

	public void setAgeEstYears(Integer ageEstYears) {
		this.ageEstYears = ageEstYears;
	}

	@Column(name = "child_mcts_id")
	public Integer getChildMctsId() {
		return childMctsId;
	}

	public void setChildMctsId(Integer childMctsId) {
		this.childMctsId = childMctsId;
	}

	@Column(name = "invalid_owner")
	public String getInvalidOwner() {
		return invalidOwner;
	}

	public void setInvalidOwner(String invalidOwner) {
		this.invalidOwner = invalidOwner;
	}

	@Column(name = "full_child_mcts_id")
	public Integer getFullChildMctsId() {
		return fullChildMctsId;
	}

	public void setFullChildMctsId(Integer fullChildMctsId) {
		this.fullChildMctsId = fullChildMctsId;
	}

	@Column(name = "success")
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	@Column(name = "owner_id_calc")
	public String getOwnerIdCalc() {
		return ownerIdCalc;
	}

	public void setOwnerIdCalc(String ownerIdCalc) {
		this.ownerIdCalc = ownerIdCalc;
	}
}
