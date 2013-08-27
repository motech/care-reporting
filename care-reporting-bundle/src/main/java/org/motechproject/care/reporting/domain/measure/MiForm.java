package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "mi_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class MiForm extends Form {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Date dateArrived;
	private Date dateLearned;
	private Date dateOfDelivery;
	private String name;
	private String pregStatus;
	private String referralInfo;
	private String abortionType;
	private Date dateAborted;
	private String migratedStatus;
    private String status;
    private Date creationTime = new Date();

    public MiForm() {
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
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	public Flw getFlw() {
		return this.flw;
	}

	public void setFlw(Flw flw) {
		this.flw = flw;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
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
	@Column(name = "date_arrived")
	public Date getDateArrived() {
		return this.dateArrived;
	}

	public void setDateArrived(Date dateArrived) {
		this.dateArrived = dateArrived;
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
	@Column(name = "date_of_delivery")
	public Date getDateOfDelivery() {
		return this.dateOfDelivery;
	}

	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "preg_status")
	public String getPregStatus() {
		return this.pregStatus;
	}

	public void setPregStatus(String pregStatus) {
		this.pregStatus = pregStatus;
	}

	@Column(name = "referral_info")
	public String getReferralInfo() {
		return this.referralInfo;
	}

	public void setReferralInfo(String referralInfo) {
		this.referralInfo = referralInfo;
	}

	@Column(name = "abortion_type")
	public String getAbortionType() {
		return this.abortionType;
	}

	public void setAbortionType(String abortionType) {
		this.abortionType = abortionType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_aborted")
	public Date getDateAborted() {
		return this.dateAborted;
	}

	public void setDateAborted(Date dateAborted) {
		this.dateAborted = dateAborted;
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
