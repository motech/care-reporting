package org.motechproject.care.reporting.domain.dimension;

// Generated Jun 4, 2013 4:50:32 PM by Hibernate Tools 3.4.0.CR1

import org.motechproject.care.reporting.domain.measure.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * MotherCase generated by hbm2java
 */
@Entity
@Table(name = "mother_case", uniqueConstraints = @UniqueConstraint(columnNames = "case_id"))
public class MotherCase implements java.io.Serializable {

	private int id;
	private Flw flw;
	private FlwGroup flwGroup;
	private String caseId;
	private String caseName;
	private String caseType;
	private Date dateModified;
	private Date serverDateModified;
	private Date serverDateOpened;
	private Integer familyNumber;
	private Integer hhNumber;
	private String husbandName;
	private Integer lastVisitType;
	private Boolean motherAlive;
	private Date motherDob;
	private String motherName;
	private Boolean close;
	private Boolean caseClosed;
	private Date closedOn;
	private Date add;
	private Short age;
	private String birthPlace;
	private Boolean complications;
	private Date dateNextBp;
	private Date dateNextCf;
	private Date dateNextEb;
	private Date dateNextPnc;
	private Boolean eatsMeat;
	private Date edd;
	private Boolean enrolledInKilkari;
	private String familyPlanningType;
	private Short howManyChildren;
	private Boolean interestInKilkari;
	private Boolean lastPregTt;
	private Date lmp;
	private String mobileNumber;
	private Short numBoys;
	private Date dateCf1;
	private Date dateCf2;
	private Date dateCf3;
	private Date dateCf4;
	private Date dateCf5;
	private Date dateCf6;
	private Date dateEb1;
	private Date dateEb2;
	private Date dateEb3;
	private Date dateEb4;
	private Date dateEb5;
	private Date dateEb6;
	private Boolean allPncOnTime;
	private Date datePnc1;
	private Date datePnc2;
	private Date datePnc3;
	private String firstPncTime;
	private Integer pnc1DaysLate;
	private Integer pnc2DaysLate;
	private Integer pnc3DaysLate;
	private Date ttBoosterDate;
	private Boolean sba;
	private Boolean sbaPhone;
	private Boolean accompany;
	private Date anc1Date;
	private Date anc2Date;
	private Date anc3Date;
	private Date anc4Date;
	private Boolean cleanCloth;
	private String coupleInterested;
	private Date dateBp1;
	private Date dateBp2;
	private Date dateBp3;
	private Date dateLastVisit;
	private String deliveryType;
	private Short ifaTablets;
	private Date ifaTablets100;
	private Boolean materials;
	private Boolean maternalEmergency;
	private Boolean maternalEmergencyNumber;
	private Boolean phoneVehicle;
	private Boolean savingMoney;
	private Date tt1Date;
	private Date tt2Date;
	private Boolean vehicle;
	private String birthStatus;
	private Date migrateOutDate;
	private String migratedStatus;
	private String status;
	private String term;
	private Date dateCf7;
	private Date dateDelFu;
	private Date dateNextReg;
	private Boolean institutional;
	private Date dob;
	private Boolean closed;
	private Date dateClosed;
	private Set<MiForm> miForms = new HashSet<MiForm>(0);
	private Set<BpForm> bpForms = new HashSet<BpForm>(0);
	private Set<PncMotherForm> pncMotherForms = new HashSet<PncMotherForm>(0);
	private Set<CfMotherForm> cfMotherForms = new HashSet<CfMotherForm>(0);
	private Set<RegistrationMotherForm> registrationMotherForms = new HashSet<RegistrationMotherForm>(
			0);
	private Set<UiMotherForm> uiMotherForms = new HashSet<UiMotherForm>(0);
	private Set<ReferMotherForm> referMotherForms = new HashSet<ReferMotherForm>(
			0);
	private Set<RegistrationChildForm> registrationChildForms = new HashSet<RegistrationChildForm>(
			0);
	private Set<ChildCase> childCases = new HashSet<ChildCase>(0);
	private Set<CloseMotherForm> closeMotherForms = new HashSet<CloseMotherForm>(
			0);
	private Set<MoForm> moForms = new HashSet<MoForm>(0);
	private Set<AbortForm> abortForms = new HashSet<AbortForm>(0);
	private Set<DeliveryMotherForm> deliveryMotherForms = new HashSet<DeliveryMotherForm>(
			0);
	private Set<DeathMotherForm> deathMotherForms = new HashSet<DeathMotherForm>(
			0);
	private Set<EbfMotherForm> ebfMotherForms = new HashSet<EbfMotherForm>(0);
	private Set<NewForm> newForms = new HashSet<NewForm>(0);

	public MotherCase() {
	}

	public MotherCase(int id) {
		this.id = id;
	}

	public MotherCase(int id, Flw flw, FlwGroup flwGroup, String caseId,
			String caseName, String caseType, Date dateModified,
			Date serverDateModified, Date serverDateOpened,
			Integer familyNumber, Integer hhNumber, String husbandName,
			Integer lastVisitType, Boolean motherAlive, Date motherDob,
			String motherName, Boolean close, Boolean caseClosed,
			Date closedOn, Date add, Short age, String birthPlace,
			Boolean complications, Date dateNextBp, Date dateNextCf,
			Date dateNextEb, Date dateNextPnc, Boolean eatsMeat, Date edd,
			Boolean enrolledInKilkari, String familyPlanningType,
			Short howManyChildren, Boolean interestInKilkari,
			Boolean lastPregTt, Date lmp, String mobileNumber, Short numBoys,
			Date dateCf1, Date dateCf2, Date dateCf3, Date dateCf4,
			Date dateCf5, Date dateCf6, Date dateEb1, Date dateEb2,
			Date dateEb3, Date dateEb4, Date dateEb5, Date dateEb6,
			Boolean allPncOnTime, Date datePnc1, Date datePnc2, Date datePnc3,
			String firstPncTime, Integer pnc1DaysLate, Integer pnc2DaysLate,
			Integer pnc3DaysLate, Date ttBoosterDate, Boolean sba,
			Boolean sbaPhone, Boolean accompany, Date anc1Date, Date anc2Date,
			Date anc3Date, Date anc4Date, Boolean cleanCloth,
			String coupleInterested, Date dateBp1, Date dateBp2, Date dateBp3,
			Date dateLastVisit, String deliveryType, Short ifaTablets,
			Date ifaTablets100, Boolean materials, Boolean maternalEmergency,
			Boolean maternalEmergencyNumber, Boolean phoneVehicle,
			Boolean savingMoney, Date tt1Date, Date tt2Date, Boolean vehicle,
			String birthStatus, Date migrateOutDate, String migratedStatus,
			String status, String term, Date dateCf7, Date dateDelFu,
			Date dateNextReg, Boolean institutional, Date dob, Boolean closed,
			Date dateClosed, Set<MiForm> miForms, Set<BpForm> bpForms,
			Set<PncMotherForm> pncMotherForms, Set<CfMotherForm> cfMotherForms,
			Set<RegistrationMotherForm> registrationMotherForms,
			Set<UiMotherForm> uiMotherForms,
			Set<ReferMotherForm> referMotherForms,
			Set<RegistrationChildForm> registrationChildForms,
			Set<ChildCase> childCases, Set<CloseMotherForm> closeMotherForms,
			Set<MoForm> moForms, Set<AbortForm> abortForms,
			Set<DeliveryMotherForm> deliveryMotherForms,
			Set<DeathMotherForm> deathMotherForms,
			Set<EbfMotherForm> ebfMotherForms, Set<NewForm> newForms) {
		this.id = id;
		this.flw = flw;
		this.flwGroup = flwGroup;
		this.caseId = caseId;
		this.caseName = caseName;
		this.caseType = caseType;
		this.dateModified = dateModified;
		this.serverDateModified = serverDateModified;
		this.serverDateOpened = serverDateOpened;
		this.familyNumber = familyNumber;
		this.hhNumber = hhNumber;
		this.husbandName = husbandName;
		this.lastVisitType = lastVisitType;
		this.motherAlive = motherAlive;
		this.motherDob = motherDob;
		this.motherName = motherName;
		this.close = close;
		this.caseClosed = caseClosed;
		this.closedOn = closedOn;
		this.add = add;
		this.age = age;
		this.birthPlace = birthPlace;
		this.complications = complications;
		this.dateNextBp = dateNextBp;
		this.dateNextCf = dateNextCf;
		this.dateNextEb = dateNextEb;
		this.dateNextPnc = dateNextPnc;
		this.eatsMeat = eatsMeat;
		this.edd = edd;
		this.enrolledInKilkari = enrolledInKilkari;
		this.familyPlanningType = familyPlanningType;
		this.howManyChildren = howManyChildren;
		this.interestInKilkari = interestInKilkari;
		this.lastPregTt = lastPregTt;
		this.lmp = lmp;
		this.mobileNumber = mobileNumber;
		this.numBoys = numBoys;
		this.dateCf1 = dateCf1;
		this.dateCf2 = dateCf2;
		this.dateCf3 = dateCf3;
		this.dateCf4 = dateCf4;
		this.dateCf5 = dateCf5;
		this.dateCf6 = dateCf6;
		this.dateEb1 = dateEb1;
		this.dateEb2 = dateEb2;
		this.dateEb3 = dateEb3;
		this.dateEb4 = dateEb4;
		this.dateEb5 = dateEb5;
		this.dateEb6 = dateEb6;
		this.allPncOnTime = allPncOnTime;
		this.datePnc1 = datePnc1;
		this.datePnc2 = datePnc2;
		this.datePnc3 = datePnc3;
		this.firstPncTime = firstPncTime;
		this.pnc1DaysLate = pnc1DaysLate;
		this.pnc2DaysLate = pnc2DaysLate;
		this.pnc3DaysLate = pnc3DaysLate;
		this.ttBoosterDate = ttBoosterDate;
		this.sba = sba;
		this.sbaPhone = sbaPhone;
		this.accompany = accompany;
		this.anc1Date = anc1Date;
		this.anc2Date = anc2Date;
		this.anc3Date = anc3Date;
		this.anc4Date = anc4Date;
		this.cleanCloth = cleanCloth;
		this.coupleInterested = coupleInterested;
		this.dateBp1 = dateBp1;
		this.dateBp2 = dateBp2;
		this.dateBp3 = dateBp3;
		this.dateLastVisit = dateLastVisit;
		this.deliveryType = deliveryType;
		this.ifaTablets = ifaTablets;
		this.ifaTablets100 = ifaTablets100;
		this.materials = materials;
		this.maternalEmergency = maternalEmergency;
		this.maternalEmergencyNumber = maternalEmergencyNumber;
		this.phoneVehicle = phoneVehicle;
		this.savingMoney = savingMoney;
		this.tt1Date = tt1Date;
		this.tt2Date = tt2Date;
		this.vehicle = vehicle;
		this.birthStatus = birthStatus;
		this.migrateOutDate = migrateOutDate;
		this.migratedStatus = migratedStatus;
		this.status = status;
		this.term = term;
		this.dateCf7 = dateCf7;
		this.dateDelFu = dateDelFu;
		this.dateNextReg = dateNextReg;
		this.institutional = institutional;
		this.dob = dob;
		this.closed = closed;
		this.dateClosed = dateClosed;
		this.miForms = miForms;
		this.bpForms = bpForms;
		this.pncMotherForms = pncMotherForms;
		this.cfMotherForms = cfMotherForms;
		this.registrationMotherForms = registrationMotherForms;
		this.uiMotherForms = uiMotherForms;
		this.referMotherForms = referMotherForms;
		this.registrationChildForms = registrationChildForms;
		this.childCases = childCases;
		this.closeMotherForms = closeMotherForms;
		this.moForms = moForms;
		this.abortForms = abortForms;
		this.deliveryMotherForms = deliveryMotherForms;
		this.deathMotherForms = deathMotherForms;
		this.ebfMotherForms = ebfMotherForms;
		this.newForms = newForms;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Flw getFlw() {
		return this.flw;
	}

	public void setFlw(Flw flw) {
		this.flw = flw;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	public FlwGroup getFlwGroup() {
		return this.flwGroup;
	}

	public void setFlwGroup(FlwGroup flwGroup) {
		this.flwGroup = flwGroup;
	}

	@Column(name = "case_id", unique = true, length = 36)
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

	@Column(name = "case_type")
	public String getCaseType() {
		return this.caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modified", length = 35)
	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "server_date_modified", length = 35)
	public Date getServerDateModified() {
		return this.serverDateModified;
	}

	public void setServerDateModified(Date serverDateModified) {
		this.serverDateModified = serverDateModified;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "server_date_opened", length = 35)
	public Date getServerDateOpened() {
		return this.serverDateOpened;
	}

	public void setServerDateOpened(Date serverDateOpened) {
		this.serverDateOpened = serverDateOpened;
	}

	@Column(name = "family_number")
	public Integer getFamilyNumber() {
		return this.familyNumber;
	}

	public void setFamilyNumber(Integer familyNumber) {
		this.familyNumber = familyNumber;
	}

	@Column(name = "hh_number")
	public Integer getHhNumber() {
		return this.hhNumber;
	}

	public void setHhNumber(Integer hhNumber) {
		this.hhNumber = hhNumber;
	}

	@Column(name = "husband_name")
	public String getHusbandName() {
		return this.husbandName;
	}

	public void setHusbandName(String husbandName) {
		this.husbandName = husbandName;
	}

	@Column(name = "last_visit_type")
	public Integer getLastVisitType() {
		return this.lastVisitType;
	}

	public void setLastVisitType(Integer lastVisitType) {
		this.lastVisitType = lastVisitType;
	}

	@Column(name = "mother_alive")
	public Boolean getMotherAlive() {
		return this.motherAlive;
	}

	public void setMotherAlive(Boolean motherAlive) {
		this.motherAlive = motherAlive;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "mother_dob", length = 13)
	public Date getMotherDob() {
		return this.motherDob;
	}

	public void setMotherDob(Date motherDob) {
		this.motherDob = motherDob;
	}

	@Column(name = "mother_name")
	public String getMotherName() {
		return this.motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	@Column(name = "close")
	public Boolean getClose() {
		return this.close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

	@Column(name = "case_closed")
	public Boolean getCaseClosed() {
		return this.caseClosed;
	}

	public void setCaseClosed(Boolean caseClosed) {
		this.caseClosed = caseClosed;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "closed_on", length = 13)
	public Date getClosedOn() {
		return this.closedOn;
	}

	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "add", length = 13)
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
	public Boolean getComplications() {
		return this.complications;
	}

	public void setComplications(Boolean complications) {
		this.complications = complications;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_bp", length = 13)
	public Date getDateNextBp() {
		return this.dateNextBp;
	}

	public void setDateNextBp(Date dateNextBp) {
		this.dateNextBp = dateNextBp;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_cf", length = 13)
	public Date getDateNextCf() {
		return this.dateNextCf;
	}

	public void setDateNextCf(Date dateNextCf) {
		this.dateNextCf = dateNextCf;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_eb", length = 13)
	public Date getDateNextEb() {
		return this.dateNextEb;
	}

	public void setDateNextEb(Date dateNextEb) {
		this.dateNextEb = dateNextEb;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_pnc", length = 13)
	public Date getDateNextPnc() {
		return this.dateNextPnc;
	}

	public void setDateNextPnc(Date dateNextPnc) {
		this.dateNextPnc = dateNextPnc;
	}

	@Column(name = "eats_meat")
	public Boolean getEatsMeat() {
		return this.eatsMeat;
	}

	public void setEatsMeat(Boolean eatsMeat) {
		this.eatsMeat = eatsMeat;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "edd", length = 13)
	public Date getEdd() {
		return this.edd;
	}

	public void setEdd(Date edd) {
		this.edd = edd;
	}

	@Column(name = "enrolled_in_kilkari")
	public Boolean getEnrolledInKilkari() {
		return this.enrolledInKilkari;
	}

	public void setEnrolledInKilkari(Boolean enrolledInKilkari) {
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
	public Boolean getInterestInKilkari() {
		return this.interestInKilkari;
	}

	public void setInterestInKilkari(Boolean interestInKilkari) {
		this.interestInKilkari = interestInKilkari;
	}

	@Column(name = "last_preg_tt")
	public Boolean getLastPregTt() {
		return this.lastPregTt;
	}

	public void setLastPregTt(Boolean lastPregTt) {
		this.lastPregTt = lastPregTt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "lmp", length = 13)
	public Date getLmp() {
		return this.lmp;
	}

	public void setLmp(Date lmp) {
		this.lmp = lmp;
	}

	@Column(name = "mobile_number", length = 20)
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "num_boys")
	public Short getNumBoys() {
		return this.numBoys;
	}

	public void setNumBoys(Short numBoys) {
		this.numBoys = numBoys;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_1", length = 13)
	public Date getDateCf1() {
		return this.dateCf1;
	}

	public void setDateCf1(Date dateCf1) {
		this.dateCf1 = dateCf1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_2", length = 13)
	public Date getDateCf2() {
		return this.dateCf2;
	}

	public void setDateCf2(Date dateCf2) {
		this.dateCf2 = dateCf2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_3", length = 13)
	public Date getDateCf3() {
		return this.dateCf3;
	}

	public void setDateCf3(Date dateCf3) {
		this.dateCf3 = dateCf3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_4", length = 13)
	public Date getDateCf4() {
		return this.dateCf4;
	}

	public void setDateCf4(Date dateCf4) {
		this.dateCf4 = dateCf4;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_5", length = 13)
	public Date getDateCf5() {
		return this.dateCf5;
	}

	public void setDateCf5(Date dateCf5) {
		this.dateCf5 = dateCf5;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_6", length = 13)
	public Date getDateCf6() {
		return this.dateCf6;
	}

	public void setDateCf6(Date dateCf6) {
		this.dateCf6 = dateCf6;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_1", length = 13)
	public Date getDateEb1() {
		return this.dateEb1;
	}

	public void setDateEb1(Date dateEb1) {
		this.dateEb1 = dateEb1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_2", length = 13)
	public Date getDateEb2() {
		return this.dateEb2;
	}

	public void setDateEb2(Date dateEb2) {
		this.dateEb2 = dateEb2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_3", length = 13)
	public Date getDateEb3() {
		return this.dateEb3;
	}

	public void setDateEb3(Date dateEb3) {
		this.dateEb3 = dateEb3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_4", length = 13)
	public Date getDateEb4() {
		return this.dateEb4;
	}

	public void setDateEb4(Date dateEb4) {
		this.dateEb4 = dateEb4;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_5", length = 13)
	public Date getDateEb5() {
		return this.dateEb5;
	}

	public void setDateEb5(Date dateEb5) {
		this.dateEb5 = dateEb5;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_6", length = 13)
	public Date getDateEb6() {
		return this.dateEb6;
	}

	public void setDateEb6(Date dateEb6) {
		this.dateEb6 = dateEb6;
	}

	@Column(name = "all_pnc_on_time")
	public Boolean getAllPncOnTime() {
		return this.allPncOnTime;
	}

	public void setAllPncOnTime(Boolean allPncOnTime) {
		this.allPncOnTime = allPncOnTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_pnc_1", length = 13)
	public Date getDatePnc1() {
		return this.datePnc1;
	}

	public void setDatePnc1(Date datePnc1) {
		this.datePnc1 = datePnc1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_pnc_2", length = 13)
	public Date getDatePnc2() {
		return this.datePnc2;
	}

	public void setDatePnc2(Date datePnc2) {
		this.datePnc2 = datePnc2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_pnc_3", length = 13)
	public Date getDatePnc3() {
		return this.datePnc3;
	}

	public void setDatePnc3(Date datePnc3) {
		this.datePnc3 = datePnc3;
	}

	@Column(name = "first_pnc_time")
	public String getFirstPncTime() {
		return this.firstPncTime;
	}

	public void setFirstPncTime(String firstPncTime) {
		this.firstPncTime = firstPncTime;
	}

	@Column(name = "pnc_1_days_late")
	public Integer getPnc1DaysLate() {
		return this.pnc1DaysLate;
	}

	public void setPnc1DaysLate(Integer pnc1DaysLate) {
		this.pnc1DaysLate = pnc1DaysLate;
	}

	@Column(name = "pnc_2_days_late")
	public Integer getPnc2DaysLate() {
		return this.pnc2DaysLate;
	}

	public void setPnc2DaysLate(Integer pnc2DaysLate) {
		this.pnc2DaysLate = pnc2DaysLate;
	}

	@Column(name = "pnc_3_days_late")
	public Integer getPnc3DaysLate() {
		return this.pnc3DaysLate;
	}

	public void setPnc3DaysLate(Integer pnc3DaysLate) {
		this.pnc3DaysLate = pnc3DaysLate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_booster_date", length = 13)
	public Date getTtBoosterDate() {
		return this.ttBoosterDate;
	}

	public void setTtBoosterDate(Date ttBoosterDate) {
		this.ttBoosterDate = ttBoosterDate;
	}

	@Column(name = "sba")
	public Boolean getSba() {
		return this.sba;
	}

	public void setSba(Boolean sba) {
		this.sba = sba;
	}

	@Column(name = "sba_phone")
	public Boolean getSbaPhone() {
		return this.sbaPhone;
	}

	public void setSbaPhone(Boolean sbaPhone) {
		this.sbaPhone = sbaPhone;
	}

	@Column(name = "accompany")
	public Boolean getAccompany() {
		return this.accompany;
	}

	public void setAccompany(Boolean accompany) {
		this.accompany = accompany;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_1_date", length = 13)
	public Date getAnc1Date() {
		return this.anc1Date;
	}

	public void setAnc1Date(Date anc1Date) {
		this.anc1Date = anc1Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_2_date", length = 13)
	public Date getAnc2Date() {
		return this.anc2Date;
	}

	public void setAnc2Date(Date anc2Date) {
		this.anc2Date = anc2Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_3_date", length = 13)
	public Date getAnc3Date() {
		return this.anc3Date;
	}

	public void setAnc3Date(Date anc3Date) {
		this.anc3Date = anc3Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_4_date", length = 13)
	public Date getAnc4Date() {
		return this.anc4Date;
	}

	public void setAnc4Date(Date anc4Date) {
		this.anc4Date = anc4Date;
	}

	@Column(name = "clean_cloth")
	public Boolean getCleanCloth() {
		return this.cleanCloth;
	}

	public void setCleanCloth(Boolean cleanCloth) {
		this.cleanCloth = cleanCloth;
	}

	@Column(name = "couple_interested", length = 15)
	public String getCoupleInterested() {
		return this.coupleInterested;
	}

	public void setCoupleInterested(String coupleInterested) {
		this.coupleInterested = coupleInterested;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_bp_1", length = 13)
	public Date getDateBp1() {
		return this.dateBp1;
	}

	public void setDateBp1(Date dateBp1) {
		this.dateBp1 = dateBp1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_bp_2", length = 13)
	public Date getDateBp2() {
		return this.dateBp2;
	}

	public void setDateBp2(Date dateBp2) {
		this.dateBp2 = dateBp2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_bp_3", length = 13)
	public Date getDateBp3() {
		return this.dateBp3;
	}

	public void setDateBp3(Date dateBp3) {
		this.dateBp3 = dateBp3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_last_visit", length = 13)
	public Date getDateLastVisit() {
		return this.dateLastVisit;
	}

	public void setDateLastVisit(Date dateLastVisit) {
		this.dateLastVisit = dateLastVisit;
	}

	@Column(name = "delivery_type")
	public String getDeliveryType() {
		return this.deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	@Column(name = "ifa_tablets")
	public Short getIfaTablets() {
		return this.ifaTablets;
	}

	public void setIfaTablets(Short ifaTablets) {
		this.ifaTablets = ifaTablets;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ifa_tablets_100", length = 13)
	public Date getIfaTablets100() {
		return this.ifaTablets100;
	}

	public void setIfaTablets100(Date ifaTablets100) {
		this.ifaTablets100 = ifaTablets100;
	}

	@Column(name = "materials")
	public Boolean getMaterials() {
		return this.materials;
	}

	public void setMaterials(Boolean materials) {
		this.materials = materials;
	}

	@Column(name = "maternal_emergency")
	public Boolean getMaternalEmergency() {
		return this.maternalEmergency;
	}

	public void setMaternalEmergency(Boolean maternalEmergency) {
		this.maternalEmergency = maternalEmergency;
	}

	@Column(name = "maternal_emergency_number")
	public Boolean getMaternalEmergencyNumber() {
		return this.maternalEmergencyNumber;
	}

	public void setMaternalEmergencyNumber(Boolean maternalEmergencyNumber) {
		this.maternalEmergencyNumber = maternalEmergencyNumber;
	}

	@Column(name = "phone_vehicle")
	public Boolean getPhoneVehicle() {
		return this.phoneVehicle;
	}

	public void setPhoneVehicle(Boolean phoneVehicle) {
		this.phoneVehicle = phoneVehicle;
	}

	@Column(name = "saving_money")
	public Boolean getSavingMoney() {
		return this.savingMoney;
	}

	public void setSavingMoney(Boolean savingMoney) {
		this.savingMoney = savingMoney;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_1_date", length = 13)
	public Date getTt1Date() {
		return this.tt1Date;
	}

	public void setTt1Date(Date tt1Date) {
		this.tt1Date = tt1Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_2_date", length = 13)
	public Date getTt2Date() {
		return this.tt2Date;
	}

	public void setTt2Date(Date tt2Date) {
		this.tt2Date = tt2Date;
	}

	@Column(name = "vehicle")
	public Boolean getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Boolean vehicle) {
		this.vehicle = vehicle;
	}

	@Column(name = "birth_status")
	public String getBirthStatus() {
		return this.birthStatus;
	}

	public void setBirthStatus(String birthStatus) {
		this.birthStatus = birthStatus;
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

	@Column(name = "term", length = 25)
	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cf_7", length = 13)
	public Date getDateCf7() {
		return this.dateCf7;
	}

	public void setDateCf7(Date dateCf7) {
		this.dateCf7 = dateCf7;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_del_fu", length = 13)
	public Date getDateDelFu() {
		return this.dateDelFu;
	}

	public void setDateDelFu(Date dateDelFu) {
		this.dateDelFu = dateDelFu;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_reg", length = 13)
	public Date getDateNextReg() {
		return this.dateNextReg;
	}

	public void setDateNextReg(Date dateNextReg) {
		this.dateNextReg = dateNextReg;
	}

	@Column(name = "institutional")
	public Boolean getInstitutional() {
		return this.institutional;
	}

	public void setInstitutional(Boolean institutional) {
		this.institutional = institutional;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dob", length = 13)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "closed")
	public Boolean getClosed() {
		return this.closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_closed", length = 13)
	public Date getDateClosed() {
		return this.dateClosed;
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<MiForm> getMiForms() {
		return this.miForms;
	}

	public void setMiForms(Set<MiForm> miForms) {
		this.miForms = miForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<BpForm> getBpForms() {
		return this.bpForms;
	}

	public void setBpForms(Set<BpForm> bpForms) {
		this.bpForms = bpForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<PncMotherForm> getPncMotherForms() {
		return this.pncMotherForms;
	}

	public void setPncMotherForms(Set<PncMotherForm> pncMotherForms) {
		this.pncMotherForms = pncMotherForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<CfMotherForm> getCfMotherForms() {
		return this.cfMotherForms;
	}

	public void setCfMotherForms(Set<CfMotherForm> cfMotherForms) {
		this.cfMotherForms = cfMotherForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<RegistrationMotherForm> getRegistrationMotherForms() {
		return this.registrationMotherForms;
	}

	public void setRegistrationMotherForms(
			Set<RegistrationMotherForm> registrationMotherForms) {
		this.registrationMotherForms = registrationMotherForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<UiMotherForm> getUiMotherForms() {
		return this.uiMotherForms;
	}

	public void setUiMotherForms(Set<UiMotherForm> uiMotherForms) {
		this.uiMotherForms = uiMotherForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<ReferMotherForm> getReferMotherForms() {
		return this.referMotherForms;
	}

	public void setReferMotherForms(Set<ReferMotherForm> referMotherForms) {
		this.referMotherForms = referMotherForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<RegistrationChildForm> getRegistrationChildForms() {
		return this.registrationChildForms;
	}

	public void setRegistrationChildForms(
			Set<RegistrationChildForm> registrationChildForms) {
		this.registrationChildForms = registrationChildForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<ChildCase> getChildCases() {
		return this.childCases;
	}

	public void setChildCases(Set<ChildCase> childCases) {
		this.childCases = childCases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<CloseMotherForm> getCloseMotherForms() {
		return this.closeMotherForms;
	}

	public void setCloseMotherForms(Set<CloseMotherForm> closeMotherForms) {
		this.closeMotherForms = closeMotherForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<MoForm> getMoForms() {
		return this.moForms;
	}

	public void setMoForms(Set<MoForm> moForms) {
		this.moForms = moForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<AbortForm> getAbortForms() {
		return this.abortForms;
	}

	public void setAbortForms(Set<AbortForm> abortForms) {
		this.abortForms = abortForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<DeliveryMotherForm> getDeliveryMotherForms() {
		return this.deliveryMotherForms;
	}

	public void setDeliveryMotherForms(
			Set<DeliveryMotherForm> deliveryMotherForms) {
		this.deliveryMotherForms = deliveryMotherForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<DeathMotherForm> getDeathMotherForms() {
		return this.deathMotherForms;
	}

	public void setDeathMotherForms(Set<DeathMotherForm> deathMotherForms) {
		this.deathMotherForms = deathMotherForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<EbfMotherForm> getEbfMotherForms() {
		return this.ebfMotherForms;
	}

	public void setEbfMotherForms(Set<EbfMotherForm> ebfMotherForms) {
		this.ebfMotherForms = ebfMotherForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motherCase")
	public Set<NewForm> getNewForms() {
		return this.newForms;
	}

	public void setNewForms(Set<NewForm> newForms) {
		this.newForms = newForms;
	}

}
