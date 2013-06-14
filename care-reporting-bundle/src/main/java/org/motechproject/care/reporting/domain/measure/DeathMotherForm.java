package org.motechproject.care.reporting.domain.measure;

// Generated Jun 4, 2013 4:50:32 PM by Hibernate Tools 3.4.0.CR1

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;

/**
 * DeathMotherForm generated by hbm2java
 */
@Entity
@Table(name = "death_mother_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class DeathMotherForm implements java.io.Serializable {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private String instanceId;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private String close;
	private Boolean motherAlive;
	private String status;
	private Short castNumChildren;
    private Date dateDeath;
	private Boolean deathVillage;
	private Short numChildren;
	private String placeDeath;
	private String siteDeath;
    private Date creationTime = new Date();

    public DeathMotherForm() {
	}

	public DeathMotherForm(int id) {
		this.id = id;
	}

	public DeathMotherForm(int id, Flw flw, MotherCase motherCase,
                           String instanceId, Date timeEnd, Date timeStart, Date dateModified,
                           String close, Boolean motherAlive, String status,
                           Short castNumChildren, Date dateDeath,
                           Boolean deathVillage, Short numChildren, String placeDeath,
                           String siteDeath, Date creationTime) {
		this.id = id;
		this.flw = flw;
		this.motherCase = motherCase;
		this.instanceId = instanceId;
		this.timeEnd = timeEnd;
		this.timeStart = timeStart;
		this.dateModified = dateModified;
		this.close = close;
		this.motherAlive = motherAlive;
		this.status = status;
		this.castNumChildren = castNumChildren;
        this.dateDeath = dateDeath;
		this.deathVillage = deathVillage;
		this.numChildren = numChildren;
		this.placeDeath = placeDeath;
		this.siteDeath = siteDeath;
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

	@Column(name = "close", length = 20)
	public String getClose() {
		return this.close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	@Column(name = "mother_alive")
	public Boolean getMotherAlive() {
		return this.motherAlive;
	}

	public void setMotherAlive(Boolean motherAlive) {
		this.motherAlive = motherAlive;
	}

	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "cast_num_children")
	public Short getCastNumChildren() {
		return this.castNumChildren;
	}

	public void setCastNumChildren(Short castNumChildren) {
		this.castNumChildren = castNumChildren;
	}

    @Temporal(TemporalType.DATE)
	@Column(name = "date_death", length = 13)
	public Date getDateDeath() {
		return this.dateDeath;
	}

	public void setDateDeath(Date dateDeath) {
		this.dateDeath = dateDeath;
	}

	@Column(name = "death_village")
	public Boolean getDeathVillage() {
		return this.deathVillage;
	}

	public void setDeathVillage(Boolean deathVillage) {
		this.deathVillage = deathVillage;
	}

	@Column(name = "num_children")
	public Short getNumChildren() {
		return this.numChildren;
	}

	public void setNumChildren(Short numChildren) {
		this.numChildren = numChildren;
	}

	@Column(name = "place_death")
	public String getPlaceDeath() {
		return this.placeDeath;
	}

	public void setPlaceDeath(String placeDeath) {
		this.placeDeath = placeDeath;
	}

	@Column(name = "site_death")
	public String getSiteDeath() {
		return this.siteDeath;
	}

	public void setSiteDeath(String siteDeath) {
		this.siteDeath = siteDeath;
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
