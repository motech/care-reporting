package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "mo_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class MoForm extends Form {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Date migrateOutDate;
	private String migratedStatus;
	private String status;
	private Date dateLearned;
	private Date dateLeft;
	private String name;
	private String noteGiven;
    private Date creationTime = new Date();

    public MoForm() {
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
   @Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
	public Flw getFlw() {
		return this.flw;
	}

	public void setFlw(Flw flw) {
		this.flw = flw;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "migrate_out_date")
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
	@Column(name = "date_learned")
	public Date getDateLearned() {
		return this.dateLearned;
	}

	public void setDateLearned(Date dateLearned) {
		this.dateLearned = dateLearned;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_left")
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
	public String getNoteGiven() {
		return this.noteGiven;
	}

	public void setNoteGiven(String noteGiven) {
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
