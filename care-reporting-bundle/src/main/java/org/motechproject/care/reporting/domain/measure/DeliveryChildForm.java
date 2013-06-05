package org.motechproject.care.reporting.domain.measure;

// Generated Jun 4, 2013 4:50:32 PM by Hibernate Tools 3.4.0.CR1

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DeliveryChildForm generated by hbm2java
 */
@Entity
@Table(name = "delivery_child_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class DeliveryChildForm implements java.io.Serializable {

	private int id;
	private Flw flw;
	private ChildCase childCase;
	private String instanceId;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Boolean abnormalities;
	private Boolean addVaccinations;
	private Boolean babyBcg;
	private Boolean babyHepB0;
	private Boolean babyOpv0;
	private Boolean breastfedHour;
	private String close;
	private String caseName;
	private String caseType;
	private String ownerId;
	private Boolean babyWeight;
	private Date bcgDate;
	private String birthStatus;
	private Date dob;
	private String gender;
	private Date hepB0Date;
	private Date opv0Date;
	private String term;
	private Date timeOfBirth;
	private Boolean childAlive;
	private String childBreathing;
	private Boolean childCried;
	private Boolean childDiedVillage;
	private Boolean childHaveAName;
	private String childHeartbeats;
	private Boolean childMovement;
	private String childName;
	private String childPlaceDeath;
	private String childSiteDeath;
	private Date chldDateDeath;
	private Boolean cordApplied;
	private Boolean cordCut;
	private Boolean cordTied;
	private Date dateFirstWeight;
	private Date dateTimeFeed;
	private BigDecimal firstWeight;
	private Boolean skinCare;
	private String whatApplied;
	private Boolean wrappedDried;

	public DeliveryChildForm() {
	}

	public DeliveryChildForm(int id) {
		this.id = id;
	}

	public DeliveryChildForm(int id, Flw flw, ChildCase childCase,
			String instanceId, Date timeEnd, Date timeStart, Date dateModified,
			Boolean abnormalities, Boolean addVaccinations, Boolean babyBcg,
			Boolean babyHepB0, Boolean babyOpv0, Boolean breastfedHour,
			String close, String caseName, String caseType, String ownerId,
			Boolean babyWeight, Date bcgDate, String birthStatus, Date dob,
			String gender, Date hepB0Date, Date opv0Date, String term,
			Date timeOfBirth, Boolean childAlive, String childBreathing,
			Boolean childCried, Boolean childDiedVillage,
			Boolean childHaveAName, String childHeartbeats,
			Boolean childMovement, String childName, String childPlaceDeath,
			String childSiteDeath, Date chldDateDeath, Boolean cordApplied,
			Boolean cordCut, Boolean cordTied, Date dateFirstWeight,
			Date dateTimeFeed, BigDecimal firstWeight, Boolean skinCare,
			String whatApplied, Boolean wrappedDried) {
		this.id = id;
		this.flw = flw;
		this.childCase = childCase;
		this.instanceId = instanceId;
		this.timeEnd = timeEnd;
		this.timeStart = timeStart;
		this.dateModified = dateModified;
		this.abnormalities = abnormalities;
		this.addVaccinations = addVaccinations;
		this.babyBcg = babyBcg;
		this.babyHepB0 = babyHepB0;
		this.babyOpv0 = babyOpv0;
		this.breastfedHour = breastfedHour;
		this.close = close;
		this.caseName = caseName;
		this.caseType = caseType;
		this.ownerId = ownerId;
		this.babyWeight = babyWeight;
		this.bcgDate = bcgDate;
		this.birthStatus = birthStatus;
		this.dob = dob;
		this.gender = gender;
		this.hepB0Date = hepB0Date;
		this.opv0Date = opv0Date;
		this.term = term;
		this.timeOfBirth = timeOfBirth;
		this.childAlive = childAlive;
		this.childBreathing = childBreathing;
		this.childCried = childCried;
		this.childDiedVillage = childDiedVillage;
		this.childHaveAName = childHaveAName;
		this.childHeartbeats = childHeartbeats;
		this.childMovement = childMovement;
		this.childName = childName;
		this.childPlaceDeath = childPlaceDeath;
		this.childSiteDeath = childSiteDeath;
		this.chldDateDeath = chldDateDeath;
		this.cordApplied = cordApplied;
		this.cordCut = cordCut;
		this.cordTied = cordTied;
		this.dateFirstWeight = dateFirstWeight;
		this.dateTimeFeed = dateTimeFeed;
		this.firstWeight = firstWeight;
		this.skinCare = skinCare;
		this.whatApplied = whatApplied;
		this.wrappedDried = wrappedDried;
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
	public ChildCase getChildCase() {
		return this.childCase;
	}

	public void setChildCase(ChildCase childCase) {
		this.childCase = childCase;
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

	@Column(name = "abnormalities")
	public Boolean getAbnormalities() {
		return this.abnormalities;
	}

	public void setAbnormalities(Boolean abnormalities) {
		this.abnormalities = abnormalities;
	}

	@Column(name = "add_vaccinations")
	public Boolean getAddVaccinations() {
		return this.addVaccinations;
	}

	public void setAddVaccinations(Boolean addVaccinations) {
		this.addVaccinations = addVaccinations;
	}

	@Column(name = "baby_bcg")
	public Boolean getBabyBcg() {
		return this.babyBcg;
	}

	public void setBabyBcg(Boolean babyBcg) {
		this.babyBcg = babyBcg;
	}

	@Column(name = "baby_hep_b_0")
	public Boolean getBabyHepB0() {
		return this.babyHepB0;
	}

	public void setBabyHepB0(Boolean babyHepB0) {
		this.babyHepB0 = babyHepB0;
	}

	@Column(name = "baby_opv0")
	public Boolean getBabyOpv0() {
		return this.babyOpv0;
	}

	public void setBabyOpv0(Boolean babyOpv0) {
		this.babyOpv0 = babyOpv0;
	}

	@Column(name = "breastfed_hour")
	public Boolean getBreastfedHour() {
		return this.breastfedHour;
	}

	public void setBreastfedHour(Boolean breastfedHour) {
		this.breastfedHour = breastfedHour;
	}

	@Column(name = "close")
	public String getClose() {
		return this.close;
	}

	public void setClose(String close) {
		this.close = close;
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

	@Column(name = "owner_id")
	public String getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	@Column(name = "baby_weight")
	public Boolean getBabyWeight() {
		return this.babyWeight;
	}

	public void setBabyWeight(Boolean babyWeight) {
		this.babyWeight = babyWeight;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "bcg_date", length = 13)
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
	@Column(name = "dob", length = 13)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "gender", length = 25)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "hep_b_0_date", length = 13)
	public Date getHepB0Date() {
		return this.hepB0Date;
	}

	public void setHepB0Date(Date hepB0Date) {
		this.hepB0Date = hepB0Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opv_0_date", length = 13)
	public Date getOpv0Date() {
		return this.opv0Date;
	}

	public void setOpv0Date(Date opv0Date) {
		this.opv0Date = opv0Date;
	}

	@Column(name = "term", length = 50)
	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_of_birth", length = 35)
	public Date getTimeOfBirth() {
		return this.timeOfBirth;
	}

	public void setTimeOfBirth(Date timeOfBirth) {
		this.timeOfBirth = timeOfBirth;
	}

	@Column(name = "child_alive")
	public Boolean getChildAlive() {
		return this.childAlive;
	}

	public void setChildAlive(Boolean childAlive) {
		this.childAlive = childAlive;
	}

	@Column(name = "child_breathing", length = 25)
	public String getChildBreathing() {
		return this.childBreathing;
	}

	public void setChildBreathing(String childBreathing) {
		this.childBreathing = childBreathing;
	}

	@Column(name = "child_cried")
	public Boolean getChildCried() {
		return this.childCried;
	}

	public void setChildCried(Boolean childCried) {
		this.childCried = childCried;
	}

	@Column(name = "child_died_village")
	public Boolean getChildDiedVillage() {
		return this.childDiedVillage;
	}

	public void setChildDiedVillage(Boolean childDiedVillage) {
		this.childDiedVillage = childDiedVillage;
	}

	@Column(name = "child_have_a_name")
	public Boolean getChildHaveAName() {
		return this.childHaveAName;
	}

	public void setChildHaveAName(Boolean childHaveAName) {
		this.childHaveAName = childHaveAName;
	}

	@Column(name = "child_heartbeats", length = 25)
	public String getChildHeartbeats() {
		return this.childHeartbeats;
	}

	public void setChildHeartbeats(String childHeartbeats) {
		this.childHeartbeats = childHeartbeats;
	}

	@Column(name = "child_movement")
	public Boolean getChildMovement() {
		return this.childMovement;
	}

	public void setChildMovement(Boolean childMovement) {
		this.childMovement = childMovement;
	}

	@Column(name = "child_name", length = 25)
	public String getChildName() {
		return this.childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	@Column(name = "child_place_death", length = 25)
	public String getChildPlaceDeath() {
		return this.childPlaceDeath;
	}

	public void setChildPlaceDeath(String childPlaceDeath) {
		this.childPlaceDeath = childPlaceDeath;
	}

	@Column(name = "child_site_death", length = 50)
	public String getChildSiteDeath() {
		return this.childSiteDeath;
	}

	public void setChildSiteDeath(String childSiteDeath) {
		this.childSiteDeath = childSiteDeath;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "chld_date_death", length = 13)
	public Date getChldDateDeath() {
		return this.chldDateDeath;
	}

	public void setChldDateDeath(Date chldDateDeath) {
		this.chldDateDeath = chldDateDeath;
	}

	@Column(name = "cord_applied")
	public Boolean getCordApplied() {
		return this.cordApplied;
	}

	public void setCordApplied(Boolean cordApplied) {
		this.cordApplied = cordApplied;
	}

	@Column(name = "cord_cut")
	public Boolean getCordCut() {
		return this.cordCut;
	}

	public void setCordCut(Boolean cordCut) {
		this.cordCut = cordCut;
	}

	@Column(name = "cord_tied")
	public Boolean getCordTied() {
		return this.cordTied;
	}

	public void setCordTied(Boolean cordTied) {
		this.cordTied = cordTied;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_first_weight", length = 13)
	public Date getDateFirstWeight() {
		return this.dateFirstWeight;
	}

	public void setDateFirstWeight(Date dateFirstWeight) {
		this.dateFirstWeight = dateFirstWeight;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_time_feed", length = 13)
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
	public Boolean getSkinCare() {
		return this.skinCare;
	}

	public void setSkinCare(Boolean skinCare) {
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
	public Boolean getWrappedDried() {
		return this.wrappedDried;
	}

	public void setWrappedDried(Boolean wrappedDried) {
		this.wrappedDried = wrappedDried;
	}

}
