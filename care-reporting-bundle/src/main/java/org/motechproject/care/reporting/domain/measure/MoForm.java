package org.motechproject.care.reporting.domain.measure;

// Generated Jun 4, 2013 4:50:32 PM by Hibernate Tools 3.4.0.CR1

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;

/**
 * MoForm generated by hbm2java
 */
@Entity
@Table(name = "mo_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class MoForm implements java.io.Serializable {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private String instanceId;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Date migrateOutDate;
	private String migratedStatus;
	private String status;
	private Date dateLearned;
	private Date dateLeft;
	private String name;
	private Boolean noteGiven;
    private Date creationTime = new Date();

    public MoForm() {
	}

	public MoForm(int id) {
		this.id = id;
	}

	public MoForm(int id, Flw flw, MotherCase motherCase, String instanceId,
                  Date timeEnd, Date timeStart, Date dateModified,
                  Date migrateOutDate, String migratedStatus, String status,
                  Date dateLearned, Date dateLeft, String name, Boolean noteGiven, Date creationTime) {
		this.id = id;
		this.flw = flw;
		this.motherCase = motherCase;
		this.instanceId = instanceId;
		this.timeEnd = timeEnd;
		this.timeStart = timeStart;
		this.dateModified = dateModified;
		this.migrateOutDate = migrateOutDate;
		this.migratedStatus = migratedStatus;
		this.status = status;
		this.dateLearned = dateLearned;
		this.dateLeft = dateLeft;
		this.name = name;
		this.noteGiven = noteGiven;
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
	@Column(name = "migrate_out_date", length = 13)
	public Date getMigrateOutDate() {
		return this.migrateOutDate;
	}

	public void setMigrateOutDate(Date migrateOutDate) {
		this.migrateOutDate = migrateOutDate;
	}

	@Column(name = "migrated_status")
	public String getMigratedStatus() {
		return this.migratedStatus;
	}

	public void setMigratedStatus(String migratedStatus) {
		this.migratedStatus = migratedStatus;
	}

	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_learned", length = 13)
	public Date getDateLearned() {
		return this.dateLearned;
	}

	public void setDateLearned(Date dateLearned) {
		this.dateLearned = dateLearned;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_left", length = 13)
	public Date getDateLeft() {
		return this.dateLeft;
	}

	public void setDateLeft(Date dateLeft) {
		this.dateLeft = dateLeft;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "note_given")
	public Boolean getNoteGiven() {
		return this.noteGiven;
	}

	public void setNoteGiven(Boolean noteGiven) {
		this.noteGiven = noteGiven;
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
