package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.*;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "registration_mother_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class RegistrationMotherForm extends Form {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private String ppiud;
	private String pptl;
	private String abdPain;
	private Short ageCalc;
	private Short ageCalcAdj;
	private Short ageEst;
	private String ageEstTrigger;
	private Date add;
	private Short age;
	private String birthPlace;
	private String complications;
	private Date dateLastVisit;
	private Date dateNextBp;
	private Date dateNextCf;
	private Date dateNextEb;
	private Date dateNextPnc;
	private String eatsMeat;
	private Date edd;
	private String enrolledInKilkari;
	private String familyPlanningType;
	private Short howManyChildren;
	private String interestInKilkari;
	private String lastPregTt;
	private String lastVisitType;
	private Date lmp;
	private String mobileNumber;
	private Date motherDob;
	private Short numBoys;
	private String status;
	private Date childDob;
    private String clientNoRegister;
	private String clientNotPregnant;
	private String clinicalExam;
	private String condoms;
	private String continuePreg;
	private String deliveryNature;
	private String dobEst;
	private Date eddCalc;
	private String eddKnown;
	private String education;
	private String fever;
	private String firstPregnancy;
	private Short gestAge;
	private String goodToRegister;
	private String inDistrict;
	private String injectible;
	private String isPregnant;
	private String iudUsed;
	private String jsyBeneficiary;
	private String jsyMoney;
	private Integer lastPreg;
	private String lastPregCSection;
	private String lastPregFullTerm;
	private Date lmpCalc;
	private String lmpKnown;
	private String missedPeriod;
	private String mobileNumberWhose;
	private String nextvisit;
	private String nextvisitBp;
	private String nextvisittype;
	private Short numChildren;
	private Short numGirls;
	private String ocpUsed;
	private String otherConditions;
	private String otherDistrict;
	private String otherVillage;
	private String painUrine;
	private String postPostpartumFp;
	private String pregDesired;
	private String recentlyDelivered;
	private String referralPrompt;
	private String resident;
	private String urineTest;
	private String usedFp;
	private String vaginalDischarge;
	private String vegetarian;
	private String whereBorn;
	private String whichHospital;
	private String whichVillage;
	private String children;
    private Boolean close;

    private Date creationTime = new Date();

    public RegistrationMotherForm() {
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

	@Column(name = "ppiud")
	public String getPpiud() {
		return this.ppiud;
	}

	public void setPpiud(String ppiud) {
		this.ppiud = ppiud;
	}

	@Column(name = "pptl")
	public String getPptl() {
		return this.pptl;
	}

	public void setPptl(String pptl) {
		this.pptl = pptl;
	}

	@Column(name = "abd_pain")
	public String getAbdPain() {
		return this.abdPain;
	}

	public void setAbdPain(String abdPain) {
		this.abdPain = abdPain;
	}

	@Column(name = "age_calc")
	public Short getAgeCalc() {
		return this.ageCalc;
	}

	public void setAgeCalc(Short ageCalc) {
		this.ageCalc = ageCalc;
	}

	@Column(name = "age_calc_adj")
	public Short getAgeCalcAdj() {
		return this.ageCalcAdj;
	}

	public void setAgeCalcAdj(Short ageCalcAdj) {
		this.ageCalcAdj = ageCalcAdj;
	}

	@Column(name = "age_est")
	public Short getAgeEst() {
		return this.ageEst;
	}

	public void setAgeEst(Short ageEst) {
		this.ageEst = ageEst;
	}

	@Column(name = "age_est_trigger")
	public String getAgeEstTrigger() {
		return this.ageEstTrigger;
	}

	public void setAgeEstTrigger(String ageEstTrigger) {
		this.ageEstTrigger = ageEstTrigger;
	}

    @Temporal(TemporalType.DATE)
	@Column(name = "add")
	public Date getAdd() {
		return this.add;
	}

	public void setAdd(Date add) {
		this.add = add;
	}

	@Column(name = "age")
	public Short getAge() {
		return this.age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	@Column(name = "birth_place")
	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	@Column(name = "complications")
	public String getComplications() {
		return this.complications;
	}

	public void setComplications(String complications) {
		this.complications = complications;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_last_visit")
	public Date getDateLastVisit() {
		return this.dateLastVisit;
	}

	public void setDateLastVisit(Date dateLastVisit) {
		this.dateLastVisit = dateLastVisit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_bp")
	public Date getDateNextBp() {
		return this.dateNextBp;
	}

	public void setDateNextBp(Date dateNextBp) {
		this.dateNextBp = dateNextBp;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_cf")
	public Date getDateNextCf() {
		return this.dateNextCf;
	}

	public void setDateNextCf(Date dateNextCf) {
		this.dateNextCf = dateNextCf;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_eb")
	public Date getDateNextEb() {
		return this.dateNextEb;
	}

	public void setDateNextEb(Date dateNextEb) {
		this.dateNextEb = dateNextEb;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_pnc")
	public Date getDateNextPnc() {
		return this.dateNextPnc;
	}

	public void setDateNextPnc(Date dateNextPnc) {
		this.dateNextPnc = dateNextPnc;
	}

	@Column(name = "eats_meat")
	public String getEatsMeat() {
		return this.eatsMeat;
	}

	public void setEatsMeat(String eatsMeat) {
		this.eatsMeat = eatsMeat;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "edd")
	public Date getEdd() {
		return this.edd;
	}

	public void setEdd(Date edd) {
		this.edd = edd;
	}

	@Column(name = "enrolled_in_kilkari")
	public String getEnrolledInKilkari() {
		return this.enrolledInKilkari;
	}

	public void setEnrolledInKilkari(String enrolledInKilkari) {
		this.enrolledInKilkari = enrolledInKilkari;
	}

	@Column(name = "family_planning_type")
	public String getFamilyPlanningType() {
		return this.familyPlanningType;
	}

	public void setFamilyPlanningType(String familyPlanningType) {
		this.familyPlanningType = familyPlanningType;
	}

	@Column(name = "how_many_children")
	public Short getHowManyChildren() {
		return this.howManyChildren;
	}

	public void setHowManyChildren(Short howManyChildren) {
		this.howManyChildren = howManyChildren;
	}

	@Column(name = "interest_in_kilkari")
	public String getInterestInKilkari() {
		return this.interestInKilkari;
	}

	public void setInterestInKilkari(String interestInKilkari) {
		this.interestInKilkari = interestInKilkari;
	}

	@Column(name = "last_preg_tt")
	public String getLastPregTt() {
		return this.lastPregTt;
	}

	public void setLastPregTt(String lastPregTt) {
		this.lastPregTt = lastPregTt;
	}

	@Column(name = "last_visit_type")
	public String getLastVisitType() {
		return this.lastVisitType;
	}

	public void setLastVisitType(String lastVisitType) {
		this.lastVisitType = lastVisitType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "lmp")
	public Date getLmp() {
		return this.lmp;
	}

	public void setLmp(Date lmp) {
		this.lmp = lmp;
	}

	@Column(name = "mobile_number")
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "mother_dob")
	public Date getMotherDob() {
		return this.motherDob;
	}

	public void setMotherDob(Date motherDob) {
		this.motherDob = motherDob;
	}

	@Column(name = "num_boys")
	public Short getNumBoys() {
		return this.numBoys;
	}

	public void setNumBoys(Short numBoys) {
		this.numBoys = numBoys;
	}

	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "child_dob")
	public Date getChildDob() {
		return this.childDob;
	}

	public void setChildDob(Date childDob) {
		this.childDob = childDob;
	}

    @Column(name = "client_no_register")
	public String getClientNoRegister() {
		return this.clientNoRegister;
	}

	public void setClientNoRegister(String clientNoRegister) {
		this.clientNoRegister = clientNoRegister;
	}

	@Column(name = "client_not_pregnant")
	public String getClientNotPregnant() {
		return this.clientNotPregnant;
	}

	public void setClientNotPregnant(String clientNotPregnant) {
		this.clientNotPregnant = clientNotPregnant;
	}

	@Column(name = "clinical_exam")
	public String getClinicalExam() {
		return this.clinicalExam;
	}

	public void setClinicalExam(String clinicalExam) {
		this.clinicalExam = clinicalExam;
	}

	@Column(name = "condoms")
	public String getCondoms() {
		return this.condoms;
	}

	public void setCondoms(String condoms) {
		this.condoms = condoms;
	}

	@Column(name = "continue_preg")
	public String getContinuePreg() {
		return this.continuePreg;
	}

	public void setContinuePreg(String continuePreg) {
		this.continuePreg = continuePreg;
	}

	@Column(name = "delivery_nature")
	public String getDeliveryNature() {
		return this.deliveryNature;
	}

	public void setDeliveryNature(String deliveryNature) {
		this.deliveryNature = deliveryNature;
	}

	@Column(name = "dob_est")
	public String getDobEst() {
		return this.dobEst;
	}

	public void setDobEst(String dobEst) {
		this.dobEst = dobEst;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "edd_calc")
	public Date getEddCalc() {
		return this.eddCalc;
	}

	public void setEddCalc(Date eddCalc) {
		this.eddCalc = eddCalc;
	}

	@Column(name = "edd_known")
	public String getEddKnown() {
		return this.eddKnown;
	}

	public void setEddKnown(String eddKnown) {
		this.eddKnown = eddKnown;
	}

	@Column(name = "education")
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "fever")
	public String getFever() {
		return this.fever;
	}

	public void setFever(String fever) {
		this.fever = fever;
	}

	@Column(name = "first_pregnancy")
	public String getFirstPregnancy() {
		return this.firstPregnancy;
	}

	public void setFirstPregnancy(String firstPregnancy) {
		this.firstPregnancy = firstPregnancy;
	}

	@Column(name = "gest_age")
	public Short getGestAge() {
		return this.gestAge;
	}

	public void setGestAge(Short gestAge) {
		this.gestAge = gestAge;
	}

	@Column(name = "good_to_register")
	public String getGoodToRegister() {
		return this.goodToRegister;
	}

	public void setGoodToRegister(String goodToRegister) {
		this.goodToRegister = goodToRegister;
	}

	@Column(name = "in_district")
	public String getInDistrict() {
		return this.inDistrict;
	}

	public void setInDistrict(String inDistrict) {
		this.inDistrict = inDistrict;
	}

	@Column(name = "injectible")
	public String getInjectible() {
		return this.injectible;
	}

	public void setInjectible(String injectible) {
		this.injectible = injectible;
	}

	@Column(name = "is_pregnant")
	public String getIsPregnant() {
		return this.isPregnant;
	}

	public void setIsPregnant(String isPregnant) {
		this.isPregnant = isPregnant;
	}

	@Column(name = "iud_used")
	public String getIudUsed() {
		return this.iudUsed;
	}

	public void setIudUsed(String iudUsed) {
		this.iudUsed = iudUsed;
	}

	@Column(name = "jsy_beneficiary")
	public String getJsyBeneficiary() {
		return this.jsyBeneficiary;
	}

	public void setJsyBeneficiary(String jsyBeneficiary) {
		this.jsyBeneficiary = jsyBeneficiary;
	}

	@Column(name = "jsy_money")
	public String getJsyMoney() {
		return this.jsyMoney;
	}

	public void setJsyMoney(String jsyMoney) {
		this.jsyMoney = jsyMoney;
	}

	@Column(name = "last_preg")
	public Integer getLastPreg() {
		return this.lastPreg;
	}

	public void setLastPreg(Integer lastPreg) {
		this.lastPreg = lastPreg;
	}

	@Column(name = "last_preg_c_section")
	public String getLastPregCSection() {
		return this.lastPregCSection;
	}

	public void setLastPregCSection(String lastPregCSection) {
		this.lastPregCSection = lastPregCSection;
	}

	@Column(name = "last_preg_full_term")
	public String getLastPregFullTerm() {
		return this.lastPregFullTerm;
	}

	public void setLastPregFullTerm(String lastPregFullTerm) {
		this.lastPregFullTerm = lastPregFullTerm;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "lmp_calc")
	public Date getLmpCalc() {
		return this.lmpCalc;
	}

	public void setLmpCalc(Date lmpCalc) {
		this.lmpCalc = lmpCalc;
	}

	@Column(name = "lmp_known")
	public String getLmpKnown() {
		return this.lmpKnown;
	}

	public void setLmpKnown(String lmpKnown) {
		this.lmpKnown = lmpKnown;
	}

	@Column(name = "missed_period")
	public String getMissedPeriod() {
		return this.missedPeriod;
	}

	public void setMissedPeriod(String missedPeriod) {
		this.missedPeriod = missedPeriod;
	}

	@Column(name = "mobile_number_whose")
	public String getMobileNumberWhose() {
		return this.mobileNumberWhose;
	}

	public void setMobileNumberWhose(String mobileNumberWhose) {
		this.mobileNumberWhose = mobileNumberWhose;
	}

	@Column(name = "nextvisit")
	public String getNextvisit() {
		return this.nextvisit;
	}

	public void setNextvisit(String nextvisit) {
		this.nextvisit = nextvisit;
	}

	@Column(name = "nextvisit_bp")
	public String getNextvisitBp() {
		return this.nextvisitBp;
	}

	public void setNextvisitBp(String nextvisitBp) {
		this.nextvisitBp = nextvisitBp;
	}

	@Column(name = "nextvisittype")
	public String getNextvisittype() {
		return this.nextvisittype;
	}

	public void setNextvisittype(String nextvisittype) {
		this.nextvisittype = nextvisittype;
	}

	@Column(name = "num_children")
	public Short getNumChildren() {
		return this.numChildren;
	}

	public void setNumChildren(Short numChildren) {
		this.numChildren = numChildren;
	}

	@Column(name = "num_girls")
	public Short getNumGirls() {
		return this.numGirls;
	}

	public void setNumGirls(Short numGirls) {
		this.numGirls = numGirls;
	}

	@Column(name = "ocp_used")
	public String getOcpUsed() {
		return this.ocpUsed;
	}

	public void setOcpUsed(String ocpUsed) {
		this.ocpUsed = ocpUsed;
	}

	@Column(name = "other_conditions")
	public String getOtherConditions() {
		return this.otherConditions;
	}

	public void setOtherConditions(String otherConditions) {
		this.otherConditions = otherConditions;
	}

	@Column(name = "other_district")
	public String getOtherDistrict() {
		return this.otherDistrict;
	}

	public void setOtherDistrict(String otherDistrict) {
		this.otherDistrict = otherDistrict;
	}

	@Column(name = "other_village")
	public String getOtherVillage() {
		return this.otherVillage;
	}

	public void setOtherVillage(String otherVillage) {
		this.otherVillage = otherVillage;
	}

	@Column(name = "pain_urine")
	public String getPainUrine() {
		return this.painUrine;
	}

	public void setPainUrine(String painUrine) {
		this.painUrine = painUrine;
	}

	@Column(name = "post_postpartum_fp")
	public String getPostPostpartumFp() {
		return this.postPostpartumFp;
	}

	public void setPostPostpartumFp(String postPostpartumFp) {
		this.postPostpartumFp = postPostpartumFp;
	}

	@Column(name = "preg_desired")
	public String getPregDesired() {
		return this.pregDesired;
	}

	public void setPregDesired(String pregDesired) {
		this.pregDesired = pregDesired;
	}

	@Column(name = "recently_delivered")
	public String getRecentlyDelivered() {
		return this.recentlyDelivered;
	}

	public void setRecentlyDelivered(String recentlyDelivered) {
		this.recentlyDelivered = recentlyDelivered;
	}

	@Column(name = "referral_prompt")
	public String getReferralPrompt() {
		return this.referralPrompt;
	}

	public void setReferralPrompt(String referralPrompt) {
		this.referralPrompt = referralPrompt;
	}

	@Column(name = "resident")
	public String getResident() {
		return this.resident;
	}

	public void setResident(String resident) {
		this.resident = resident;
	}

	@Column(name = "urine_test")
	public String getUrineTest() {
		return this.urineTest;
	}

	public void setUrineTest(String urineTest) {
		this.urineTest = urineTest;
	}

	@Column(name = "used_fp")
	public String getUsedFp() {
		return this.usedFp;
	}

	public void setUsedFp(String usedFp) {
		this.usedFp = usedFp;
	}

	@Column(name = "vaginal_discharge")
	public String getVaginalDischarge() {
		return this.vaginalDischarge;
	}

	public void setVaginalDischarge(String vaginalDischarge) {
		this.vaginalDischarge = vaginalDischarge;
	}

	@Column(name = "vegetarian")
	public String getVegetarian() {
		return this.vegetarian;
	}

	public void setVegetarian(String vegetarian) {
		this.vegetarian = vegetarian;
	}

	@Column(name = "where_born")
	public String getWhereBorn() {
		return this.whereBorn;
	}

	public void setWhereBorn(String whereBorn) {
		this.whereBorn = whereBorn;
	}

	@Column(name = "which_hospital")
	public String getWhichHospital() {
		return this.whichHospital;
	}

	public void setWhichHospital(String whichHospital) {
		this.whichHospital = whichHospital;
	}

	@Column(name = "which_village")
	public String getWhichVillage() {
		return this.whichVillage;
	}

	public void setWhichVillage(String whichVillage) {
		this.whichVillage = whichVillage;
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

    @Column(name = "children")
    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    @Column(name = "close")
    public Boolean getClose() {
        return close;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }

}
