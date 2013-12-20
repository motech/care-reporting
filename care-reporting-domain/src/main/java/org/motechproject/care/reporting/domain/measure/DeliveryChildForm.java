package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "delivery_child_form", uniqueConstraints = @UniqueConstraint(columnNames = {"instance_id","case_id"}))
public class DeliveryChildForm extends Form {

	private int id;
	private Flw flw;
	private ChildCase childCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private String abnormalities;
	private String addVaccinations;
	private String babyBcg;
	private String babyHepB0;
	private String babyOpv0;
	private String breastfedHour;
	private String caseName;
	private String caseType;
	private String babyWeight;
	private Date bcgDate;
	private String birthStatus;
	private Date dob;
	private String gender;
	private Date hepB0Date;
	private Date opv0Date;
	private String term;
	private String timeOfBirth;
	private String childAlive;
	private String childBreathing;
	private String childCried;
	private String childDiedVillage;
	private String childHaveAName;
	private String childHeartbeats;
	private String childMovement;
	private String childName;
	private String childPlaceDeath;
	private String childSiteDeath;
	private Date chldDateDeath;
	private String cordApplied;
	private String cordCut;
	private String cordTied;
	private Date dateFirstWeight;
	private Date dateTimeFeed;
	private BigDecimal firstWeight;
	private String skinCare;
	private String whatApplied;
	private String wrappedDried;
    private Boolean close;
    private Date creationTime = new Date();

    public DeliveryChildForm() {
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
	public ChildCase getChildCase() {
		return this.childCase;
	}

	public void setChildCase(ChildCase childCase) {
		this.childCase = childCase;
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

	@Column(name = "abnormalities")
	public String getAbnormalities() {
		return this.abnormalities;
	}

	public void setAbnormalities(String abnormalities) {
		this.abnormalities = abnormalities;
	}

	@Column(name = "add_vaccinations")
	public String getAddVaccinations() {
		return this.addVaccinations;
	}

	public void setAddVaccinations(String addVaccinations) {
		this.addVaccinations = addVaccinations;
	}

	@Column(name = "baby_bcg")
	public String getBabyBcg() {
		return this.babyBcg;
	}

	public void setBabyBcg(String babyBcg) {
		this.babyBcg = babyBcg;
	}

	@Column(name = "baby_hep_b_0")
	public String getBabyHepB0() {
		return this.babyHepB0;
	}

	public void setBabyHepB0(String babyHepB0) {
		this.babyHepB0 = babyHepB0;
	}

	@Column(name = "baby_opv0")
	public String getBabyOpv0() {
		return this.babyOpv0;
	}

	public void setBabyOpv0(String babyOpv0) {
		this.babyOpv0 = babyOpv0;
	}

	@Column(name = "breastfed_hour")
	public String getBreastfedHour() {
		return this.breastfedHour;
	}

	public void setBreastfedHour(String breastfedHour) {
		this.breastfedHour = breastfedHour;
	}

	@Column(name = "case_name")
	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	@Column(name = "case_type")
	public String getCaseType() {
		return this.caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	@Column(name = "baby_weight")
	public String getBabyWeight() {
		return this.babyWeight;
	}

	public void setBabyWeight(String babyWeight) {
		this.babyWeight = babyWeight;
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
	@Column(name = "opv_0_date")
	public Date getOpv0Date() {
		return this.opv0Date;
	}

	public void setOpv0Date(Date opv0Date) {
		this.opv0Date = opv0Date;
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

	@Column(name = "child_alive")
	public String getChildAlive() {
		return this.childAlive;
	}

	public void setChildAlive(String childAlive) {
		this.childAlive = childAlive;
	}

	@Column(name = "child_breathing")
	public String getChildBreathing() {
		return this.childBreathing;
	}

	public void setChildBreathing(String childBreathing) {
		this.childBreathing = childBreathing;
	}

	@Column(name = "child_cried")
	public String getChildCried() {
		return this.childCried;
	}

	public void setChildCried(String childCried) {
		this.childCried = childCried;
	}

	@Column(name = "child_died_village")
	public String getChildDiedVillage() {
		return this.childDiedVillage;
	}

	public void setChildDiedVillage(String childDiedVillage) {
		this.childDiedVillage = childDiedVillage;
	}

	@Column(name = "child_have_a_name")
	public String getChildHaveAName() {
		return this.childHaveAName;
	}

	public void setChildHaveAName(String childHaveAName) {
		this.childHaveAName = childHaveAName;
	}

	@Column(name = "child_heartbeats")
	public String getChildHeartbeats() {
		return this.childHeartbeats;
	}

	public void setChildHeartbeats(String childHeartbeats) {
		this.childHeartbeats = childHeartbeats;
	}

	@Column(name = "child_movement")
	public String getChildMovement() {
		return this.childMovement;
	}

	public void setChildMovement(String childMovement) {
		this.childMovement = childMovement;
	}

	@Column(name = "child_name")
	public String getChildName() {
		return this.childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	@Column(name = "child_place_death")
	public String getChildPlaceDeath() {
		return this.childPlaceDeath;
	}

	public void setChildPlaceDeath(String childPlaceDeath) {
		this.childPlaceDeath = childPlaceDeath;
	}

	@Column(name = "child_site_death")
	public String getChildSiteDeath() {
		return this.childSiteDeath;
	}

	public void setChildSiteDeath(String childSiteDeath) {
		this.childSiteDeath = childSiteDeath;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "chld_date_death")
	public Date getChldDateDeath() {
		return this.chldDateDeath;
	}

	public void setChldDateDeath(Date chldDateDeath) {
		this.chldDateDeath = chldDateDeath;
	}

	@Column(name = "cord_applied")
	public String getCordApplied() {
		return this.cordApplied;
	}

	public void setCordApplied(String cordApplied) {
		this.cordApplied = cordApplied;
	}

	@Column(name = "cord_cut")
	public String getCordCut() {
		return this.cordCut;
	}

	public void setCordCut(String cordCut) {
		this.cordCut = cordCut;
	}

	@Column(name = "cord_tied")
	public String getCordTied() {
		return this.cordTied;
	}

	public void setCordTied(String cordTied) {
		this.cordTied = cordTied;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_first_weight")
	public Date getDateFirstWeight() {
		return this.dateFirstWeight;
	}

	public void setDateFirstWeight(Date dateFirstWeight) {
		this.dateFirstWeight = dateFirstWeight;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_time_feed")
	public Date getDateTimeFeed() {
		return this.dateTimeFeed;
	}

	public void setDateTimeFeed(Date dateTimeFeed) {
		this.dateTimeFeed = dateTimeFeed;
	}

	@Column(name = "first_weight", precision = 131089, scale = 0)
	public BigDecimal getFirstWeight() {
		return this.firstWeight;
	}

	public void setFirstWeight(BigDecimal firstWeight) {
		this.firstWeight = firstWeight;
	}

	@Column(name = "skin_care")
	public String getSkinCare() {
		return this.skinCare;
	}

	public void setSkinCare(String skinCare) {
		this.skinCare = skinCare;
	}

	@Column(name = "what_applied")
	public String getWhatApplied() {
		return this.whatApplied;
	}

	public void setWhatApplied(String whatApplied) {
		this.whatApplied = whatApplied;
	}

	@Column(name = "wrapped_dried")
	public String getWrappedDried() {
		return this.wrappedDried;
	}

	public void setWrappedDried(String wrappedDried) {
		this.wrappedDried = wrappedDried;
	}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Column(name = "close")
    public Boolean getClose() {
        return this.close;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return FormToString.toString(this);
    }
}
