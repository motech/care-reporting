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
@Table(name = "aww_edit_child_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class AwwEditChildForm extends Form {

	private int id;
	private Flw flw;
	private ChildCase childCase;
	private Date timeStart;
	private Date timeEnd;
	private Date dateModified;
	private Date creationTime;
	String updateChildName;
	String newChildName;
	String updateChildDob;
	Date newChildDob;
	String success;

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

	@Column(name = "update_child_name")
	public String getUpdateChildName() {
		return updateChildName;
	}

	public void setUpdateChildName(String updateChildName) {
		this.updateChildName = updateChildName;
	}

	@Column(name = "new_child_name")
	public String getNewChildName() {
		return newChildName;
	}

	public void setNewChildName(String newChildName) {
		this.newChildName = newChildName;
	}

	@Column(name = "update_child_dob")
	public String getUpdateChildDob() {
		return updateChildDob;
	}

	public void setUpdateChildDob(String updateChildDob) {
		this.updateChildDob = updateChildDob;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "new_child_dob")
	public Date getNewChildDob() {
		return newChildDob;
	}

	public void setNewChildDob(Date newChildDob) {
		this.newChildDob = newChildDob;
	}

	@Column(name = "success")
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
}
