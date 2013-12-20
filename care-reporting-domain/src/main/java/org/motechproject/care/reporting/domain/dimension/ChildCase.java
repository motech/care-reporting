package org.motechproject.care.reporting.domain.dimension;

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.SelfUpdatable;
import org.motechproject.care.reporting.domain.annotations.ExternalPrimaryKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "child_case")
public class ChildCase extends SelfUpdatable<ChildCase> implements java.io.Serializable {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private FlwGroup flwGroup;
	@ExternalPrimaryKey
    private String caseId;
	private String caseName;
	private Date dateModified;
	private Date serverDateModified;
    private String caseType;
	private String babyMeasles;
	private Date bcgDate;
	private String birthStatus;
	private Date dob;
	private Date dpt1Date;
	private Date dpt2Date;
	private Date dpt3Date;
	private String gender;
	private Date hepB0Date;
	private Date hepB1Date;
	private Date hepB2Date;
	private Date hepB3Date;
	private Date measlesDate;
	private Date opv0Date;
	private Date opv1Date;
	private Date opv2Date;
	private Date opv3Date;
	private Date vitA1Date;
	private String childAlive;
	private Date dptBoosterDate;
	private Date opvBoosterDate;
	private Date dateJe;
	private Date dateMeaslesBooster;
	private String babyWeight;
	private String name;
	private String term;
	private String timeOfBirth;
	private Date vitA2Date;
	private Date vitA3Date;
    private String cordFallen;
	private Boolean closed;
	private Date closedOn;
    private Flw closedBy;
    private Date creationTime;
    private Date lastModifiedTime;

    public ChildCase() {
        Date date = new Date();
        creationTime = date;
        lastModifiedTime = date;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mother_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	public MotherCase getMotherCase() {
		return this.motherCase;
	}

	public void setMotherCase(MotherCase motherCase) {
		this.motherCase = motherCase;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	public FlwGroup getFlwGroup() {
		return this.flwGroup;
	}

	public void setFlwGroup(FlwGroup flwGroup) {
		this.flwGroup = flwGroup;
	}

	@Column(name = "case_id")
	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	@Column(name = "case_name")
	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modified")
	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "server_date_modified")
	public Date getServerDateModified() {
		return this.serverDateModified;
	}

	public void setServerDateModified(Date serverDateModified) {
		this.serverDateModified = serverDateModified;
	}

    @Column(name = "case_type")
	public String getCaseType() {
		return this.caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	@Column(name = "baby_measles")
	public String getBabyMeasles() {
		return this.babyMeasles;
	}

	public void setBabyMeasles(String babyMeasles) {
		this.babyMeasles = babyMeasles;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "bcg_date")
	public Date getBcgDate() {
		return this.bcgDate;
	}

	public void setBcgDate(Date bcgDate) {
		this.bcgDate = bcgDate;
	}

	@Column(name = "birth_status")
	public String getBirthStatus() {
		return this.birthStatus;
	}

	public void setBirthStatus(String birthStatus) {
		this.birthStatus = birthStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dpt_1_date")
	public Date getDpt1Date() {
		return this.dpt1Date;
	}

	public void setDpt1Date(Date dpt1Date) {
		this.dpt1Date = dpt1Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dpt_2_date")
	public Date getDpt2Date() {
		return this.dpt2Date;
	}

	public void setDpt2Date(Date dpt2Date) {
		this.dpt2Date = dpt2Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dpt_3_date")
	public Date getDpt3Date() {
		return this.dpt3Date;
	}

	public void setDpt3Date(Date dpt3Date) {
		this.dpt3Date = dpt3Date;
	}

	@Column(name = "gender")
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "hep_b_0_date")
	public Date getHepB0Date() {
		return this.hepB0Date;
	}

	public void setHepB0Date(Date hepB0Date) {
		this.hepB0Date = hepB0Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "hep_b_1_date")
	public Date getHepB1Date() {
		return this.hepB1Date;
	}

	public void setHepB1Date(Date hepB1Date) {
		this.hepB1Date = hepB1Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "hep_b_2_date")
	public Date getHepB2Date() {
		return this.hepB2Date;
	}

	public void setHepB2Date(Date hepB2Date) {
		this.hepB2Date = hepB2Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "hep_b_3_date")
	public Date getHepB3Date() {
		return this.hepB3Date;
	}

	public void setHepB3Date(Date hepB3Date) {
		this.hepB3Date = hepB3Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "measles_date")
	public Date getMeaslesDate() {
		return this.measlesDate;
	}

	public void setMeaslesDate(Date measlesDate) {
		this.measlesDate = measlesDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opv_0_date")
	public Date getOpv0Date() {
		return this.opv0Date;
	}

	public void setOpv0Date(Date opv0Date) {
		this.opv0Date = opv0Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opv_1_date")
	public Date getOpv1Date() {
		return this.opv1Date;
	}

	public void setOpv1Date(Date opv1Date) {
		this.opv1Date = opv1Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opv_2_date")
	public Date getOpv2Date() {
		return this.opv2Date;
	}

	public void setOpv2Date(Date opv2Date) {
		this.opv2Date = opv2Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opv_3_date")
	public Date getOpv3Date() {
		return this.opv3Date;
	}

	public void setOpv3Date(Date opv3Date) {
		this.opv3Date = opv3Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vit_a_1_date")
	public Date getVitA1Date() {
		return this.vitA1Date;
	}

	public void setVitA1Date(Date vitA1Date) {
		this.vitA1Date = vitA1Date;
	}

	@Column(name = "child_alive")
	public String getChildAlive() {
		return this.childAlive;
	}

	public void setChildAlive(String childAlive) {
		this.childAlive = childAlive;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dpt_booster_date")
	public Date getDptBoosterDate() {
		return this.dptBoosterDate;
	}

	public void setDptBoosterDate(Date dptBoosterDate) {
		this.dptBoosterDate = dptBoosterDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opv_booster_date")
	public Date getOpvBoosterDate() {
		return this.opvBoosterDate;
	}

	public void setOpvBoosterDate(Date opvBoosterDate) {
		this.opvBoosterDate = opvBoosterDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_je")
	public Date getDateJe() {
		return this.dateJe;
	}

	public void setDateJe(Date dateJe) {
		this.dateJe = dateJe;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_measles_booster")
	public Date getDateMeaslesBooster() {
		return this.dateMeaslesBooster;
	}

	public void setDateMeaslesBooster(Date dateMeaslesBooster) {
		this.dateMeaslesBooster = dateMeaslesBooster;
	}

    @Column(name = "baby_weight")
    public String getBabyWeight() {
        return babyWeight;
    }

    public void setBabyWeight(String babyWeight) {
        this.babyWeight = babyWeight;
    }

    @Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "term")
	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Column(name = "time_of_birth")
	public String getTimeOfBirth() {
		return this.timeOfBirth;
	}

	public void setTimeOfBirth(String timeOfBirth) {
		this.timeOfBirth = timeOfBirth;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vit_a_2_date")
	public Date getVitA2Date() {
		return this.vitA2Date;
	}

	public void setVitA2Date(Date vitA2Date) {
		this.vitA2Date = vitA2Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vit_a_3_date")
	public Date getVitA3Date() {
		return this.vitA3Date;
	}

	public void setVitA3Date(Date vitA3Date) {
		this.vitA3Date = vitA3Date;
	}

    @Column(name = "cord_fallen")
    public String getCordFallen() {
        return cordFallen;
    }

    public void setCordFallen(String cordFallen) {
        this.cordFallen = cordFallen;
    }


    @Column(name = "closed")
	public Boolean getClosed() {
		return this.closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "closed_on")
	public Date getClosedOn() {
		return this.closedOn;
	}

	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closed_by")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    public Flw getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(Flw closedBy) {
        this.closedBy = closedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_time")
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    public void updateToLatest(ChildCase updated) {
        validateIfUpdatable(this.caseId, updated.caseId);

        if (!isLatest(updated)) {
            logger.warn(String.format("Ignoring mother case update with case id: %s since current date modified is %s and given date modified is %s", this.caseId, this.dateModified, updated.dateModified));
            return;
        }

        List<String> fieldsToIgnore = Arrays.asList("id", "caseId", "creationTime", "closedOn", "closedBy", "closed");
        updateFields(updated, fieldsToIgnore);
    }

    @Override
    public void updateLastModifiedTime() {
        this.lastModifiedTime = new Date();
    }

    private boolean isLatest(ChildCase updatedObject) {
        if (this.serverDateModified == null)
            return true;
        else if (updatedObject.serverDateModified == null)
            return false;
        return this.serverDateModified.compareTo(updatedObject.serverDateModified) <= 0;
    }
}
