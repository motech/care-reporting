package org.motechproject.care.reporting.domain.measure;

// Generated Jun 4, 2013 4:50:32 PM by Hibernate Tools 3.4.0.CR1

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;

import javax.persistence.*;
import java.util.Date;

/**
 * BpForm generated by hbm2java
 */
@Entity
@Table(name = "bp_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class BpForm implements java.io.Serializable {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private String instanceId;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Date ancLatestDate;
	private Integer ancLatestNum;
	private String anc1AbdominalExam;
	private Boolean anc1Abnormalities;
	private String anc1BloodPressure;
	private Date anc1Date;
	private String anc1Facility;
	private Boolean anc1Details;
	private String anc2AbdominalExam;
	private Boolean anc2Abnormalities;
	private String anc2BloodPressure;
	private Date anc2Date;
	private String anc2Facility;
	private Boolean anc2Details;
	private String anc3AbdominalExam;
	private Boolean anc3Abnormalities;
	private String anc3BloodPressure;
	private Date anc3Date;
	private String anc3Facility;
	private Boolean anc3Details;
	private String anc4AbdominalExam;
	private Boolean anc4Abnormalities;
	private String anc4BloodPressure;
	private Date anc4Date;
	private String anc4Facility;
	private Boolean anc4Details;
	private Boolean counselIfa;
	private Boolean counselTt;
	private Boolean eatingExtra;
	private Short ifaTabletsIssued;
	private String reasonNoIfa;
	private Boolean receivedTt1;
	private Boolean receivedTt2;
	private Boolean resting;
	private Date tt1Date;
	private Date tt2Date;
	private Boolean ttBooster;
	private Date ttBoosterDate;
	private Boolean usingIfa;
	private Boolean sba;
	private Boolean sbaPhone;
	private Boolean accompany;
	private Boolean careOfHome;
	private Boolean cleanCloth;
	private Boolean cordCare;
	private Boolean counselHomeDelivery;
	private Boolean counselInstitutional;
	private Boolean counselPreparation;
	private Boolean dangerInstitution;
	private Boolean dangerNumber;
	private Boolean hasDangerSigns;
	private Boolean immediateBreastfeeding;
	private Boolean informDangerSigns;
	private Boolean materials;
	private Boolean maternalDangerSigns;
	private Boolean nowInstitutional;
	private Boolean phoneVehicle;
	private Boolean playBirthPreparednessVid;
	private Boolean playCordCareVid;
	private Boolean savingMoney;
	private Boolean skinToSkin;
	private Boolean vehicle;
	private Boolean wrapping;
	private Short bpVisitNum;
	private Date anc1Date_1;
	private Date anc2Date_1;
	private Date anc3Date_1;
	private Date anc4Date_1;
	private String coupleInterested;
	private Date dateBp1;
	private Date dateBp2;
	private Date dateBp3;
	private Date dateLastVisit;
	private Date dateNextBp;
	private String deliveryType;
	private Short ifaTablets;
	private Date ifaTablets100;
	private String lastVisitType;
	private Boolean maternalEmergency;
	private Boolean maternalEmergencyNumber;
	private Date tt1Date_1;
	private Date tt2Date_1;
	private Boolean conceive;
	private Integer delFup;
	private Boolean availImmediate;
	private Boolean counselAccessible;
	private Boolean counselBenefits;
	private Boolean counselDisqualification;
	private Boolean counselInstitution;
	private Boolean counselMethods;
	private Boolean counselNearest;
	private Boolean counselOptions;
	private Boolean counselStay;
	private Boolean immediateAppropriate;
	private Boolean institutionImmediate;
	private Boolean postponeConception;
	private Boolean riskOfPreg;
	private Boolean spacingMethods;
	private String stopChildren;
	private Short ifaTabletsTotal;
	private String nextvisittype;
	private Boolean playFamilyPlanningVid;
	private String postponing;

	public BpForm() {
	}

	public BpForm(int id) {
		this.id = id;
	}

	public BpForm(int id, Flw flw, MotherCase motherCase, String instanceId,
			Date timeEnd, Date timeStart, Date dateModified,
			Date ancLatestDate, Integer ancLatestNum, String anc1AbdominalExam,
			Boolean anc1Abnormalities, String anc1BloodPressure, Date anc1Date,
			String anc1Facility, Boolean anc1Details, String anc2AbdominalExam,
			Boolean anc2Abnormalities, String anc2BloodPressure, Date anc2Date,
			String anc2Facility, Boolean anc2Details, String anc3AbdominalExam,
			Boolean anc3Abnormalities, String anc3BloodPressure, Date anc3Date,
			String anc3Facility, Boolean anc3Details, String anc4AbdominalExam,
			Boolean anc4Abnormalities, String anc4BloodPressure, Date anc4Date,
			String anc4Facility, Boolean anc4Details, Boolean counselIfa,
			Boolean counselTt, Boolean eatingExtra, Short ifaTabletsIssued,
			String reasonNoIfa, Boolean receivedTt1, Boolean receivedTt2,
			Boolean resting, Date tt1Date, Date tt2Date, Boolean ttBooster,
			Date ttBoosterDate, Boolean usingIfa, Boolean sba,
			Boolean sbaPhone, Boolean accompany, Boolean careOfHome,
			Boolean cleanCloth, Boolean cordCare, Boolean counselHomeDelivery,
			Boolean counselInstitutional, Boolean counselPreparation,
			Boolean dangerInstitution, Boolean dangerNumber,
			Boolean hasDangerSigns, Boolean immediateBreastfeeding,
			Boolean informDangerSigns, Boolean materials,
			Boolean maternalDangerSigns, Boolean nowInstitutional,
			Boolean phoneVehicle, Boolean playBirthPreparednessVid,
			Boolean playCordCareVid, Boolean savingMoney, Boolean skinToSkin,
			Boolean vehicle, Boolean wrapping, Short bpVisitNum,
			Date anc1Date_1, Date anc2Date_1, Date anc3Date_1, Date anc4Date_1,
			String coupleInterested, Date dateBp1, Date dateBp2, Date dateBp3,
			Date dateLastVisit, Date dateNextBp, String deliveryType,
			Short ifaTablets, Date ifaTablets100, String lastVisitType,
			Boolean maternalEmergency, Boolean maternalEmergencyNumber,
			Date tt1Date_1, Date tt2Date_1, Boolean conceive, Integer delFup,
			Boolean availImmediate, Boolean counselAccessible,
			Boolean counselBenefits, Boolean counselDisqualification,
			Boolean counselInstitution, Boolean counselMethods,
			Boolean counselNearest, Boolean counselOptions,
			Boolean counselStay, Boolean immediateAppropriate,
			Boolean institutionImmediate, Boolean postponeConception,
			Boolean riskOfPreg, Boolean spacingMethods, String stopChildren,
			Short ifaTabletsTotal, String nextvisittype,
			Boolean playFamilyPlanningVid, String postponing) {
		this.id = id;
		this.flw = flw;
		this.motherCase = motherCase;
		this.instanceId = instanceId;
		this.timeEnd = timeEnd;
		this.timeStart = timeStart;
		this.dateModified = dateModified;
		this.ancLatestDate = ancLatestDate;
		this.ancLatestNum = ancLatestNum;
		this.anc1AbdominalExam = anc1AbdominalExam;
		this.anc1Abnormalities = anc1Abnormalities;
		this.anc1BloodPressure = anc1BloodPressure;
		this.anc1Date = anc1Date;
		this.anc1Facility = anc1Facility;
		this.anc1Details = anc1Details;
		this.anc2AbdominalExam = anc2AbdominalExam;
		this.anc2Abnormalities = anc2Abnormalities;
		this.anc2BloodPressure = anc2BloodPressure;
		this.anc2Date = anc2Date;
		this.anc2Facility = anc2Facility;
		this.anc2Details = anc2Details;
		this.anc3AbdominalExam = anc3AbdominalExam;
		this.anc3Abnormalities = anc3Abnormalities;
		this.anc3BloodPressure = anc3BloodPressure;
		this.anc3Date = anc3Date;
		this.anc3Facility = anc3Facility;
		this.anc3Details = anc3Details;
		this.anc4AbdominalExam = anc4AbdominalExam;
		this.anc4Abnormalities = anc4Abnormalities;
		this.anc4BloodPressure = anc4BloodPressure;
		this.anc4Date = anc4Date;
		this.anc4Facility = anc4Facility;
		this.anc4Details = anc4Details;
		this.counselIfa = counselIfa;
		this.counselTt = counselTt;
		this.eatingExtra = eatingExtra;
		this.ifaTabletsIssued = ifaTabletsIssued;
		this.reasonNoIfa = reasonNoIfa;
		this.receivedTt1 = receivedTt1;
		this.receivedTt2 = receivedTt2;
		this.resting = resting;
		this.tt1Date = tt1Date;
		this.tt2Date = tt2Date;
		this.ttBooster = ttBooster;
		this.ttBoosterDate = ttBoosterDate;
		this.usingIfa = usingIfa;
		this.sba = sba;
		this.sbaPhone = sbaPhone;
		this.accompany = accompany;
		this.careOfHome = careOfHome;
		this.cleanCloth = cleanCloth;
		this.cordCare = cordCare;
		this.counselHomeDelivery = counselHomeDelivery;
		this.counselInstitutional = counselInstitutional;
		this.counselPreparation = counselPreparation;
		this.dangerInstitution = dangerInstitution;
		this.dangerNumber = dangerNumber;
		this.hasDangerSigns = hasDangerSigns;
		this.immediateBreastfeeding = immediateBreastfeeding;
		this.informDangerSigns = informDangerSigns;
		this.materials = materials;
		this.maternalDangerSigns = maternalDangerSigns;
		this.nowInstitutional = nowInstitutional;
		this.phoneVehicle = phoneVehicle;
		this.playBirthPreparednessVid = playBirthPreparednessVid;
		this.playCordCareVid = playCordCareVid;
		this.savingMoney = savingMoney;
		this.skinToSkin = skinToSkin;
		this.vehicle = vehicle;
		this.wrapping = wrapping;
		this.bpVisitNum = bpVisitNum;
		this.anc1Date_1 = anc1Date_1;
		this.anc2Date_1 = anc2Date_1;
		this.anc3Date_1 = anc3Date_1;
		this.anc4Date_1 = anc4Date_1;
		this.coupleInterested = coupleInterested;
		this.dateBp1 = dateBp1;
		this.dateBp2 = dateBp2;
		this.dateBp3 = dateBp3;
		this.dateLastVisit = dateLastVisit;
		this.dateNextBp = dateNextBp;
		this.deliveryType = deliveryType;
		this.ifaTablets = ifaTablets;
		this.ifaTablets100 = ifaTablets100;
		this.lastVisitType = lastVisitType;
		this.maternalEmergency = maternalEmergency;
		this.maternalEmergencyNumber = maternalEmergencyNumber;
		this.tt1Date_1 = tt1Date_1;
		this.tt2Date_1 = tt2Date_1;
		this.conceive = conceive;
		this.delFup = delFup;
		this.availImmediate = availImmediate;
		this.counselAccessible = counselAccessible;
		this.counselBenefits = counselBenefits;
		this.counselDisqualification = counselDisqualification;
		this.counselInstitution = counselInstitution;
		this.counselMethods = counselMethods;
		this.counselNearest = counselNearest;
		this.counselOptions = counselOptions;
		this.counselStay = counselStay;
		this.immediateAppropriate = immediateAppropriate;
		this.institutionImmediate = institutionImmediate;
		this.postponeConception = postponeConception;
		this.riskOfPreg = riskOfPreg;
		this.spacingMethods = spacingMethods;
		this.stopChildren = stopChildren;
		this.ifaTabletsTotal = ifaTabletsTotal;
		this.nextvisittype = nextvisittype;
		this.playFamilyPlanningVid = playFamilyPlanningVid;
		this.postponing = postponing;
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
	@Column(name = "anc_latest_date", length = 13)
	public Date getAncLatestDate() {
		return this.ancLatestDate;
	}

	public void setAncLatestDate(Date ancLatestDate) {
		this.ancLatestDate = ancLatestDate;
	}

	@Column(name = "anc_latest_num")
	public Integer getAncLatestNum() {
		return this.ancLatestNum;
	}

	public void setAncLatestNum(Integer ancLatestNum) {
		this.ancLatestNum = ancLatestNum;
	}

	@Column(name = "anc1_abdominal_exam")
	public String getAnc1AbdominalExam() {
		return this.anc1AbdominalExam;
	}

	public void setAnc1AbdominalExam(String anc1AbdominalExam) {
		this.anc1AbdominalExam = anc1AbdominalExam;
	}

	@Column(name = "anc1_abnormalities")
	public Boolean getAnc1Abnormalities() {
		return this.anc1Abnormalities;
	}

	public void setAnc1Abnormalities(Boolean anc1Abnormalities) {
		this.anc1Abnormalities = anc1Abnormalities;
	}

	@Column(name = "anc1_blood_pressure")
	public String getAnc1BloodPressure() {
		return this.anc1BloodPressure;
	}

	public void setAnc1BloodPressure(String anc1BloodPressure) {
		this.anc1BloodPressure = anc1BloodPressure;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc1_date", length = 13)
	public Date getAnc1Date() {
		return this.anc1Date;
	}

	public void setAnc1Date(Date anc1Date) {
		this.anc1Date = anc1Date;
	}

	@Column(name = "anc1_facility")
	public String getAnc1Facility() {
		return this.anc1Facility;
	}

	public void setAnc1Facility(String anc1Facility) {
		this.anc1Facility = anc1Facility;
	}

	@Column(name = "anc1_details")
	public Boolean getAnc1Details() {
		return this.anc1Details;
	}

	public void setAnc1Details(Boolean anc1Details) {
		this.anc1Details = anc1Details;
	}

	@Column(name = "anc2_abdominal_exam")
	public String getAnc2AbdominalExam() {
		return this.anc2AbdominalExam;
	}

	public void setAnc2AbdominalExam(String anc2AbdominalExam) {
		this.anc2AbdominalExam = anc2AbdominalExam;
	}

	@Column(name = "anc2_abnormalities")
	public Boolean getAnc2Abnormalities() {
		return this.anc2Abnormalities;
	}

	public void setAnc2Abnormalities(Boolean anc2Abnormalities) {
		this.anc2Abnormalities = anc2Abnormalities;
	}

	@Column(name = "anc2_blood_pressure")
	public String getAnc2BloodPressure() {
		return this.anc2BloodPressure;
	}

	public void setAnc2BloodPressure(String anc2BloodPressure) {
		this.anc2BloodPressure = anc2BloodPressure;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc2_date", length = 13)
	public Date getAnc2Date() {
		return this.anc2Date;
	}

	public void setAnc2Date(Date anc2Date) {
		this.anc2Date = anc2Date;
	}

	@Column(name = "anc2_facility")
	public String getAnc2Facility() {
		return this.anc2Facility;
	}

	public void setAnc2Facility(String anc2Facility) {
		this.anc2Facility = anc2Facility;
	}

	@Column(name = "anc2_details")
	public Boolean getAnc2Details() {
		return this.anc2Details;
	}

	public void setAnc2Details(Boolean anc2Details) {
		this.anc2Details = anc2Details;
	}

	@Column(name = "anc3_abdominal_exam")
	public String getAnc3AbdominalExam() {
		return this.anc3AbdominalExam;
	}

	public void setAnc3AbdominalExam(String anc3AbdominalExam) {
		this.anc3AbdominalExam = anc3AbdominalExam;
	}

	@Column(name = "anc3_abnormalities")
	public Boolean getAnc3Abnormalities() {
		return this.anc3Abnormalities;
	}

	public void setAnc3Abnormalities(Boolean anc3Abnormalities) {
		this.anc3Abnormalities = anc3Abnormalities;
	}

	@Column(name = "anc3_blood_pressure")
	public String getAnc3BloodPressure() {
		return this.anc3BloodPressure;
	}

	public void setAnc3BloodPressure(String anc3BloodPressure) {
		this.anc3BloodPressure = anc3BloodPressure;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc3_date", length = 13)
	public Date getAnc3Date() {
		return this.anc3Date;
	}

	public void setAnc3Date(Date anc3Date) {
		this.anc3Date = anc3Date;
	}

	@Column(name = "anc3_facility")
	public String getAnc3Facility() {
		return this.anc3Facility;
	}

	public void setAnc3Facility(String anc3Facility) {
		this.anc3Facility = anc3Facility;
	}

	@Column(name = "anc3_details")
	public Boolean getAnc3Details() {
		return this.anc3Details;
	}

	public void setAnc3Details(Boolean anc3Details) {
		this.anc3Details = anc3Details;
	}

	@Column(name = "anc4_abdominal_exam")
	public String getAnc4AbdominalExam() {
		return this.anc4AbdominalExam;
	}

	public void setAnc4AbdominalExam(String anc4AbdominalExam) {
		this.anc4AbdominalExam = anc4AbdominalExam;
	}

	@Column(name = "anc4_abnormalities")
	public Boolean getAnc4Abnormalities() {
		return this.anc4Abnormalities;
	}

	public void setAnc4Abnormalities(Boolean anc4Abnormalities) {
		this.anc4Abnormalities = anc4Abnormalities;
	}

	@Column(name = "anc4_blood_pressure")
	public String getAnc4BloodPressure() {
		return this.anc4BloodPressure;
	}

	public void setAnc4BloodPressure(String anc4BloodPressure) {
		this.anc4BloodPressure = anc4BloodPressure;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc4_date", length = 13)
	public Date getAnc4Date() {
		return this.anc4Date;
	}

	public void setAnc4Date(Date anc4Date) {
		this.anc4Date = anc4Date;
	}

	@Column(name = "anc4_facility")
	public String getAnc4Facility() {
		return this.anc4Facility;
	}

	public void setAnc4Facility(String anc4Facility) {
		this.anc4Facility = anc4Facility;
	}

	@Column(name = "anc4_details")
	public Boolean getAnc4Details() {
		return this.anc4Details;
	}

	public void setAnc4Details(Boolean anc4Details) {
		this.anc4Details = anc4Details;
	}

	@Column(name = "counsel_ifa")
	public Boolean getCounselIfa() {
		return this.counselIfa;
	}

	public void setCounselIfa(Boolean counselIfa) {
		this.counselIfa = counselIfa;
	}

	@Column(name = "counsel_tt")
	public Boolean getCounselTt() {
		return this.counselTt;
	}

	public void setCounselTt(Boolean counselTt) {
		this.counselTt = counselTt;
	}

	@Column(name = "eating_extra")
	public Boolean getEatingExtra() {
		return this.eatingExtra;
	}

	public void setEatingExtra(Boolean eatingExtra) {
		this.eatingExtra = eatingExtra;
	}

	@Column(name = "ifa_tablets_issued")
	public Short getIfaTabletsIssued() {
		return this.ifaTabletsIssued;
	}

	public void setIfaTabletsIssued(Short ifaTabletsIssued) {
		this.ifaTabletsIssued = ifaTabletsIssued;
	}

	@Column(name = "reason_no_ifa")
	public String getReasonNoIfa() {
		return this.reasonNoIfa;
	}

	public void setReasonNoIfa(String reasonNoIfa) {
		this.reasonNoIfa = reasonNoIfa;
	}

	@Column(name = "received_tt1")
	public Boolean getReceivedTt1() {
		return this.receivedTt1;
	}

	public void setReceivedTt1(Boolean receivedTt1) {
		this.receivedTt1 = receivedTt1;
	}

	@Column(name = "received_tt2")
	public Boolean getReceivedTt2() {
		return this.receivedTt2;
	}

	public void setReceivedTt2(Boolean receivedTt2) {
		this.receivedTt2 = receivedTt2;
	}

	@Column(name = "resting")
	public Boolean getResting() {
		return this.resting;
	}

	public void setResting(Boolean resting) {
		this.resting = resting;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt1_date", length = 13)
	public Date getTt1Date() {
		return this.tt1Date;
	}

	public void setTt1Date(Date tt1Date) {
		this.tt1Date = tt1Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt2_date", length = 13)
	public Date getTt2Date() {
		return this.tt2Date;
	}

	public void setTt2Date(Date tt2Date) {
		this.tt2Date = tt2Date;
	}

	@Column(name = "tt_booster")
	public Boolean getTtBooster() {
		return this.ttBooster;
	}

	public void setTtBooster(Boolean ttBooster) {
		this.ttBooster = ttBooster;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_booster_date", length = 13)
	public Date getTtBoosterDate() {
		return this.ttBoosterDate;
	}

	public void setTtBoosterDate(Date ttBoosterDate) {
		this.ttBoosterDate = ttBoosterDate;
	}

	@Column(name = "using_ifa")
	public Boolean getUsingIfa() {
		return this.usingIfa;
	}

	public void setUsingIfa(Boolean usingIfa) {
		this.usingIfa = usingIfa;
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

	@Column(name = "care_of_home")
	public Boolean getCareOfHome() {
		return this.careOfHome;
	}

	public void setCareOfHome(Boolean careOfHome) {
		this.careOfHome = careOfHome;
	}

	@Column(name = "clean_cloth")
	public Boolean getCleanCloth() {
		return this.cleanCloth;
	}

	public void setCleanCloth(Boolean cleanCloth) {
		this.cleanCloth = cleanCloth;
	}

	@Column(name = "cord_care")
	public Boolean getCordCare() {
		return this.cordCare;
	}

	public void setCordCare(Boolean cordCare) {
		this.cordCare = cordCare;
	}

	@Column(name = "counsel_home_delivery")
	public Boolean getCounselHomeDelivery() {
		return this.counselHomeDelivery;
	}

	public void setCounselHomeDelivery(Boolean counselHomeDelivery) {
		this.counselHomeDelivery = counselHomeDelivery;
	}

	@Column(name = "counsel_institutional")
	public Boolean getCounselInstitutional() {
		return this.counselInstitutional;
	}

	public void setCounselInstitutional(Boolean counselInstitutional) {
		this.counselInstitutional = counselInstitutional;
	}

	@Column(name = "counsel_preparation")
	public Boolean getCounselPreparation() {
		return this.counselPreparation;
	}

	public void setCounselPreparation(Boolean counselPreparation) {
		this.counselPreparation = counselPreparation;
	}

	@Column(name = "danger_institution")
	public Boolean getDangerInstitution() {
		return this.dangerInstitution;
	}

	public void setDangerInstitution(Boolean dangerInstitution) {
		this.dangerInstitution = dangerInstitution;
	}

	@Column(name = "danger_number")
	public Boolean getDangerNumber() {
		return this.dangerNumber;
	}

	public void setDangerNumber(Boolean dangerNumber) {
		this.dangerNumber = dangerNumber;
	}

	@Column(name = "has_danger_signs")
	public Boolean getHasDangerSigns() {
		return this.hasDangerSigns;
	}

	public void setHasDangerSigns(Boolean hasDangerSigns) {
		this.hasDangerSigns = hasDangerSigns;
	}

	@Column(name = "immediate_breastfeeding")
	public Boolean getImmediateBreastfeeding() {
		return this.immediateBreastfeeding;
	}

	public void setImmediateBreastfeeding(Boolean immediateBreastfeeding) {
		this.immediateBreastfeeding = immediateBreastfeeding;
	}

	@Column(name = "inform_danger_signs")
	public Boolean getInformDangerSigns() {
		return this.informDangerSigns;
	}

	public void setInformDangerSigns(Boolean informDangerSigns) {
		this.informDangerSigns = informDangerSigns;
	}

	@Column(name = "materials")
	public Boolean getMaterials() {
		return this.materials;
	}

	public void setMaterials(Boolean materials) {
		this.materials = materials;
	}

	@Column(name = "maternal_danger_signs")
	public Boolean getMaternalDangerSigns() {
		return this.maternalDangerSigns;
	}

	public void setMaternalDangerSigns(Boolean maternalDangerSigns) {
		this.maternalDangerSigns = maternalDangerSigns;
	}

	@Column(name = "now_institutional")
	public Boolean getNowInstitutional() {
		return this.nowInstitutional;
	}

	public void setNowInstitutional(Boolean nowInstitutional) {
		this.nowInstitutional = nowInstitutional;
	}

	@Column(name = "phone_vehicle")
	public Boolean getPhoneVehicle() {
		return this.phoneVehicle;
	}

	public void setPhoneVehicle(Boolean phoneVehicle) {
		this.phoneVehicle = phoneVehicle;
	}

	@Column(name = "play_birth_preparedness_vid")
	public Boolean getPlayBirthPreparednessVid() {
		return this.playBirthPreparednessVid;
	}

	public void setPlayBirthPreparednessVid(Boolean playBirthPreparednessVid) {
		this.playBirthPreparednessVid = playBirthPreparednessVid;
	}

	@Column(name = "play_cord_care_vid")
	public Boolean getPlayCordCareVid() {
		return this.playCordCareVid;
	}

	public void setPlayCordCareVid(Boolean playCordCareVid) {
		this.playCordCareVid = playCordCareVid;
	}

	@Column(name = "saving_money")
	public Boolean getSavingMoney() {
		return this.savingMoney;
	}

	public void setSavingMoney(Boolean savingMoney) {
		this.savingMoney = savingMoney;
	}

	@Column(name = "skin_to_skin")
	public Boolean getSkinToSkin() {
		return this.skinToSkin;
	}

	public void setSkinToSkin(Boolean skinToSkin) {
		this.skinToSkin = skinToSkin;
	}

	@Column(name = "vehicle")
	public Boolean getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Boolean vehicle) {
		this.vehicle = vehicle;
	}

	@Column(name = "wrapping")
	public Boolean getWrapping() {
		return this.wrapping;
	}

	public void setWrapping(Boolean wrapping) {
		this.wrapping = wrapping;
	}

	@Column(name = "bp_visit_num")
	public Short getBpVisitNum() {
		return this.bpVisitNum;
	}

	public void setBpVisitNum(Short bpVisitNum) {
		this.bpVisitNum = bpVisitNum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_1_date", length = 13)
	public Date getAnc1Date_1() {
		return this.anc1Date_1;
	}

	public void setAnc1Date_1(Date anc1Date_1) {
		this.anc1Date_1 = anc1Date_1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_2_date", length = 13)
	public Date getAnc2Date_1() {
		return this.anc2Date_1;
	}

	public void setAnc2Date_1(Date anc2Date_1) {
		this.anc2Date_1 = anc2Date_1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_3_date", length = 13)
	public Date getAnc3Date_1() {
		return this.anc3Date_1;
	}

	public void setAnc3Date_1(Date anc3Date_1) {
		this.anc3Date_1 = anc3Date_1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_4_date", length = 13)
	public Date getAnc4Date_1() {
		return this.anc4Date_1;
	}

	public void setAnc4Date_1(Date anc4Date_1) {
		this.anc4Date_1 = anc4Date_1;
	}

	@Column(name = "couple_interested")
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

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_bp", length = 13)
	public Date getDateNextBp() {
		return this.dateNextBp;
	}

	public void setDateNextBp(Date dateNextBp) {
		this.dateNextBp = dateNextBp;
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

	@Column(name = "last_visit_type", length = 20)
	public String getLastVisitType() {
		return this.lastVisitType;
	}

	public void setLastVisitType(String lastVisitType) {
		this.lastVisitType = lastVisitType;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_1_date", length = 13)
	public Date getTt1Date_1() {
		return this.tt1Date_1;
	}

	public void setTt1Date_1(Date tt1Date_1) {
		this.tt1Date_1 = tt1Date_1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_2_date", length = 13)
	public Date getTt2Date_1() {
		return this.tt2Date_1;
	}

	public void setTt2Date_1(Date tt2Date_1) {
		this.tt2Date_1 = tt2Date_1;
	}

	@Column(name = "conceive")
	public Boolean getConceive() {
		return this.conceive;
	}

	public void setConceive(Boolean conceive) {
		this.conceive = conceive;
	}

	@Column(name = "del_fup")
	public Integer getDelFup() {
		return this.delFup;
	}

	public void setDelFup(Integer delFup) {
		this.delFup = delFup;
	}

	@Column(name = "avail_immediate")
	public Boolean getAvailImmediate() {
		return this.availImmediate;
	}

	public void setAvailImmediate(Boolean availImmediate) {
		this.availImmediate = availImmediate;
	}

	@Column(name = "counsel_accessible")
	public Boolean getCounselAccessible() {
		return this.counselAccessible;
	}

	public void setCounselAccessible(Boolean counselAccessible) {
		this.counselAccessible = counselAccessible;
	}

	@Column(name = "counsel_benefits")
	public Boolean getCounselBenefits() {
		return this.counselBenefits;
	}

	public void setCounselBenefits(Boolean counselBenefits) {
		this.counselBenefits = counselBenefits;
	}

	@Column(name = "counsel_disqualification")
	public Boolean getCounselDisqualification() {
		return this.counselDisqualification;
	}

	public void setCounselDisqualification(Boolean counselDisqualification) {
		this.counselDisqualification = counselDisqualification;
	}

	@Column(name = "counsel_institution")
	public Boolean getCounselInstitution() {
		return this.counselInstitution;
	}

	public void setCounselInstitution(Boolean counselInstitution) {
		this.counselInstitution = counselInstitution;
	}

	@Column(name = "counsel_methods")
	public Boolean getCounselMethods() {
		return this.counselMethods;
	}

	public void setCounselMethods(Boolean counselMethods) {
		this.counselMethods = counselMethods;
	}

	@Column(name = "counsel_nearest")
	public Boolean getCounselNearest() {
		return this.counselNearest;
	}

	public void setCounselNearest(Boolean counselNearest) {
		this.counselNearest = counselNearest;
	}

	@Column(name = "counsel_options")
	public Boolean getCounselOptions() {
		return this.counselOptions;
	}

	public void setCounselOptions(Boolean counselOptions) {
		this.counselOptions = counselOptions;
	}

	@Column(name = "counsel_stay")
	public Boolean getCounselStay() {
		return this.counselStay;
	}

	public void setCounselStay(Boolean counselStay) {
		this.counselStay = counselStay;
	}

	@Column(name = "immediate_appropriate")
	public Boolean getImmediateAppropriate() {
		return this.immediateAppropriate;
	}

	public void setImmediateAppropriate(Boolean immediateAppropriate) {
		this.immediateAppropriate = immediateAppropriate;
	}

	@Column(name = "institution_immediate")
	public Boolean getInstitutionImmediate() {
		return this.institutionImmediate;
	}

	public void setInstitutionImmediate(Boolean institutionImmediate) {
		this.institutionImmediate = institutionImmediate;
	}

	@Column(name = "postpone_conception")
	public Boolean getPostponeConception() {
		return this.postponeConception;
	}

	public void setPostponeConception(Boolean postponeConception) {
		this.postponeConception = postponeConception;
	}

	@Column(name = "risk_of_preg")
	public Boolean getRiskOfPreg() {
		return this.riskOfPreg;
	}

	public void setRiskOfPreg(Boolean riskOfPreg) {
		this.riskOfPreg = riskOfPreg;
	}

	@Column(name = "spacing_methods")
	public Boolean getSpacingMethods() {
		return this.spacingMethods;
	}

	public void setSpacingMethods(Boolean spacingMethods) {
		this.spacingMethods = spacingMethods;
	}

	@Column(name = "stop_children", length = 15)
	public String getStopChildren() {
		return this.stopChildren;
	}

	public void setStopChildren(String stopChildren) {
		this.stopChildren = stopChildren;
	}

	@Column(name = "ifa_tablets_total")
	public Short getIfaTabletsTotal() {
		return this.ifaTabletsTotal;
	}

	public void setIfaTabletsTotal(Short ifaTabletsTotal) {
		this.ifaTabletsTotal = ifaTabletsTotal;
	}

	@Column(name = "nextvisittype", length = 20)
	public String getNextvisittype() {
		return this.nextvisittype;
	}

	public void setNextvisittype(String nextvisittype) {
		this.nextvisittype = nextvisittype;
	}

	@Column(name = "play_family_planning_vid")
	public Boolean getPlayFamilyPlanningVid() {
		return this.playFamilyPlanningVid;
	}

	public void setPlayFamilyPlanningVid(Boolean playFamilyPlanningVid) {
		this.playFamilyPlanningVid = playFamilyPlanningVid;
	}

	@Column(name = "postponing", length = 15)
	public String getPostponing() {
		return this.postponing;
	}

	public void setPostponing(String postponing) {
		this.postponing = postponing;
	}

}
