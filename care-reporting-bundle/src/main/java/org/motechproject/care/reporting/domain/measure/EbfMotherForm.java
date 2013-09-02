package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ebf_mother_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class EbfMotherForm extends Form {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Date addval;
	private String adoptImmediately;
	private String askPpiud;
	private String awareOfFailure;
	private String bleeding;
    private String complications;
	private String condoms;
	private String counselFollowUpPpiud;
	private String counselFollowUpPptl;
	private String counselMenstrualCycle;
	private String counselMethods;
	private String counselPpfp;
	private String counselTimeIud;
	private Date dateEb1;
	private Date dateEb2;
	private Date dateEb3;
	private Date dateEb4;
	private Date dateEb5;
	private Date dateEb6;
	private Date dateIudAdopted;
	private Date dateLastInj;
	private Date dateLastVisit;
	private Date dateNextCf;
	private Date dateNextEb;
	private String discharge;
	private String distension;
	private Short ebVisitNum;
	private String familyPlanningType;
	private String fever;
	private String haveCondoms;
	private String headaches;
	private String highBp;
	private String injMenstrualIrregularity;
	private String injectable;
	private String intendToContinue;
	private String intervalPpfpInterest;
	private String iud;
	private String iudAdopted;
	private String iudCounselDuration;
	private String iudCounselFollowUp;
	private String iudCounselHospital;
	private String iudCounselPlacement;
	private String iudCounselScreening;
	private String iudCounselSideEffects;
	private String lastVisitType;
	private String menstrualIrregularity;
	private Date nextInjCalc;
	private String nextvisittype;
	private Short numChildren;
	private String ocp;
	private String ocpContinue;
	private String ocpCounselRegularity;
	private String painSwelling;
	private Boolean ppfpInterest;
	private String ppiudAbdominalPain;
	private String ppiudProblems;
	private String pptlAbdominalPain;
	private String pptlPainSurgery;
	private String pptlProblems;
	private String regularPeriods;
	private String tabletsReceived;
	private String takenAsPrescribed;
	private String tl;
	private String tlAdopted;
	private String tlConselIncentives;
	private String tlCounselFollowUp;
	private String tlCounselHospital;
	private String tlCounselIrreversible;
	private String tlCounselScreening;
	private String tlCounselSideEffects;
	private String tlCounselTiming;
	private String understandTablets;
	private String usingCorrectly;
	private String whereReplace;
	private String whyNoPpffp;
	private String within42;
	private Date dateTlAdopted;
	private String abdominalPain;
	private String painUrination;
	private String ppiudBleeding;
	private String ppiudDischarge;
	private String ppiudFever;
    private Date creationTime = new Date();

    public EbfMotherForm() {
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
	@Column(name = "addval")
	public Date getAddval() {
		return this.addval;
	}

	public void setAddval(Date addval) {
		this.addval = addval;
	}

	@Column(name = "adopt_immediately")
	public String getAdoptImmediately() {
		return this.adoptImmediately;
	}

	public void setAdoptImmediately(String adoptImmediately) {
		this.adoptImmediately = adoptImmediately;
	}

	@Column(name = "ask_ppiud")
	public String getAskPpiud() {
		return this.askPpiud;
	}

	public void setAskPpiud(String askPpiud) {
		this.askPpiud = askPpiud;
	}

	@Column(name = "aware_of_failure")
	public String getAwareOfFailure() {
		return this.awareOfFailure;
	}

	public void setAwareOfFailure(String awareOfFailure) {
		this.awareOfFailure = awareOfFailure;
	}

	@Column(name = "bleeding")
	public String getBleeding() {
		return this.bleeding;
	}

	public void setBleeding(String bleeding) {
		this.bleeding = bleeding;
	}

    @Column(name = "complications")
	public String getComplications() {
		return this.complications;
	}

	public void setComplications(String complications) {
		this.complications = complications;
	}

	@Column(name = "condoms")
	public String getCondoms() {
		return this.condoms;
	}

	public void setCondoms(String condoms) {
		this.condoms = condoms;
	}

	@Column(name = "counsel_follow_up_ppiud")
	public String getCounselFollowUpPpiud() {
		return this.counselFollowUpPpiud;
	}

	public void setCounselFollowUpPpiud(String counselFollowUpPpiud) {
		this.counselFollowUpPpiud = counselFollowUpPpiud;
	}

	@Column(name = "counsel_follow_up_pptl")
	public String getCounselFollowUpPptl() {
		return this.counselFollowUpPptl;
	}

	public void setCounselFollowUpPptl(String counselFollowUpPptl) {
		this.counselFollowUpPptl = counselFollowUpPptl;
	}

	@Column(name = "counsel_menstrual_cycle")
	public String getCounselMenstrualCycle() {
		return this.counselMenstrualCycle;
	}

	public void setCounselMenstrualCycle(String counselMenstrualCycle) {
		this.counselMenstrualCycle = counselMenstrualCycle;
	}

	@Column(name = "counsel_methods")
	public String getCounselMethods() {
		return this.counselMethods;
	}

	public void setCounselMethods(String counselMethods) {
		this.counselMethods = counselMethods;
	}

	@Column(name = "counsel_ppfp")
	public String getCounselPpfp() {
		return this.counselPpfp;
	}

	public void setCounselPpfp(String counselPpfp) {
		this.counselPpfp = counselPpfp;
	}

	@Column(name = "counsel_time_iud")
	public String getCounselTimeIud() {
		return this.counselTimeIud;
	}

	public void setCounselTimeIud(String counselTimeIud) {
		this.counselTimeIud = counselTimeIud;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_1")
	public Date getDateEb1() {
		return this.dateEb1;
	}

	public void setDateEb1(Date dateEb1) {
		this.dateEb1 = dateEb1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_2")
	public Date getDateEb2() {
		return this.dateEb2;
	}

	public void setDateEb2(Date dateEb2) {
		this.dateEb2 = dateEb2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_3")
	public Date getDateEb3() {
		return this.dateEb3;
	}

	public void setDateEb3(Date dateEb3) {
		this.dateEb3 = dateEb3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_4")
	public Date getDateEb4() {
		return this.dateEb4;
	}

	public void setDateEb4(Date dateEb4) {
		this.dateEb4 = dateEb4;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_5")
	public Date getDateEb5() {
		return this.dateEb5;
	}

	public void setDateEb5(Date dateEb5) {
		this.dateEb5 = dateEb5;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_6")
	public Date getDateEb6() {
		return this.dateEb6;
	}

	public void setDateEb6(Date dateEb6) {
		this.dateEb6 = dateEb6;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_iud_adopted")
	public Date getDateIudAdopted() {
		return this.dateIudAdopted;
	}

	public void setDateIudAdopted(Date dateIudAdopted) {
		this.dateIudAdopted = dateIudAdopted;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_last_inj")
	public Date getDateLastInj() {
		return this.dateLastInj;
	}

	public void setDateLastInj(Date dateLastInj) {
		this.dateLastInj = dateLastInj;
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

	@Column(name = "discharge")
	public String getDischarge() {
		return this.discharge;
	}

	public void setDischarge(String discharge) {
		this.discharge = discharge;
	}

	@Column(name = "distension")
	public String getDistension() {
		return this.distension;
	}

	public void setDistension(String distension) {
		this.distension = distension;
	}

	@Column(name = "eb_visit_num")
	public Short getEbVisitNum() {
		return this.ebVisitNum;
	}

	public void setEbVisitNum(Short ebVisitNum) {
		this.ebVisitNum = ebVisitNum;
	}

	@Column(name = "family_planning_type")
	public String getFamilyPlanningType() {
		return this.familyPlanningType;
	}

	public void setFamilyPlanningType(String familyPlanningType) {
		this.familyPlanningType = familyPlanningType;
	}

	@Column(name = "fever")
	public String getFever() {
		return this.fever;
	}

	public void setFever(String fever) {
		this.fever = fever;
	}

	@Column(name = "have_condoms")
	public String getHaveCondoms() {
		return this.haveCondoms;
	}

	public void setHaveCondoms(String haveCondoms) {
		this.haveCondoms = haveCondoms;
	}

	@Column(name = "headaches")
	public String getHeadaches() {
		return this.headaches;
	}

	public void setHeadaches(String headaches) {
		this.headaches = headaches;
	}

	@Column(name = "high_bp")
	public String getHighBp() {
		return this.highBp;
	}

	public void setHighBp(String highBp) {
		this.highBp = highBp;
	}

	@Column(name = "inj_menstrual_irregularity")
	public String getInjMenstrualIrregularity() {
		return this.injMenstrualIrregularity;
	}

	public void setInjMenstrualIrregularity(String injMenstrualIrregularity) {
		this.injMenstrualIrregularity = injMenstrualIrregularity;
	}

	@Column(name = "injectable")
	public String getInjectable() {
		return this.injectable;
	}

	public void setInjectable(String injectable) {
		this.injectable = injectable;
	}

	@Column(name = "intend_to_continue")
	public String getIntendToContinue() {
		return this.intendToContinue;
	}

	public void setIntendToContinue(String intendToContinue) {
		this.intendToContinue = intendToContinue;
	}

	@Column(name = "interval_ppfp_interest")
	public String getIntervalPpfpInterest() {
		return this.intervalPpfpInterest;
	}

	public void setIntervalPpfpInterest(String intervalPpfpInterest) {
		this.intervalPpfpInterest = intervalPpfpInterest;
	}

	@Column(name = "iud")
	public String getIud() {
		return this.iud;
	}

	public void setIud(String iud) {
		this.iud = iud;
	}

	@Column(name = "iud_adopted")
	public String getIudAdopted() {
		return this.iudAdopted;
	}

	public void setIudAdopted(String iudAdopted) {
		this.iudAdopted = iudAdopted;
	}

	@Column(name = "iud_counsel_duration")
	public String getIudCounselDuration() {
		return this.iudCounselDuration;
	}

	public void setIudCounselDuration(String iudCounselDuration) {
		this.iudCounselDuration = iudCounselDuration;
	}

	@Column(name = "iud_counsel_follow_up")
	public String getIudCounselFollowUp() {
		return this.iudCounselFollowUp;
	}

	public void setIudCounselFollowUp(String iudCounselFollowUp) {
		this.iudCounselFollowUp = iudCounselFollowUp;
	}

	@Column(name = "iud_counsel_hospital")
	public String getIudCounselHospital() {
		return this.iudCounselHospital;
	}

	public void setIudCounselHospital(String iudCounselHospital) {
		this.iudCounselHospital = iudCounselHospital;
	}

	@Column(name = "iud_counsel_placement")
	public String getIudCounselPlacement() {
		return this.iudCounselPlacement;
	}

	public void setIudCounselPlacement(String iudCounselPlacement) {
		this.iudCounselPlacement = iudCounselPlacement;
	}

	@Column(name = "iud_counsel_screening")
	public String getIudCounselScreening() {
		return this.iudCounselScreening;
	}

	public void setIudCounselScreening(String iudCounselScreening) {
		this.iudCounselScreening = iudCounselScreening;
	}

	@Column(name = "iud_counsel_side_effects")
	public String getIudCounselSideEffects() {
		return this.iudCounselSideEffects;
	}

	public void setIudCounselSideEffects(String iudCounselSideEffects) {
		this.iudCounselSideEffects = iudCounselSideEffects;
	}

	@Column(name = "last_visit_type")
	public String getLastVisitType() {
		return this.lastVisitType;
	}

	public void setLastVisitType(String lastVisitType) {
		this.lastVisitType = lastVisitType;
	}

	@Column(name = "menstrual_irregularity")
	public String getMenstrualIrregularity() {
		return this.menstrualIrregularity;
	}

	public void setMenstrualIrregularity(String menstrualIrregularity) {
		this.menstrualIrregularity = menstrualIrregularity;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "next_inj_calc")
	public Date getNextInjCalc() {
		return this.nextInjCalc;
	}

	public void setNextInjCalc(Date nextInjCalc) {
		this.nextInjCalc = nextInjCalc;
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

	@Column(name = "ocp")
	public String getOcp() {
		return this.ocp;
	}

	public void setOcp(String ocp) {
		this.ocp = ocp;
	}

	@Column(name = "ocp_continue")
	public String getOcpContinue() {
		return this.ocpContinue;
	}

	public void setOcpContinue(String ocpContinue) {
		this.ocpContinue = ocpContinue;
	}

	@Column(name = "ocp_counsel_regularity")
	public String getOcpCounselRegularity() {
		return this.ocpCounselRegularity;
	}

	public void setOcpCounselRegularity(String ocpCounselRegularity) {
		this.ocpCounselRegularity = ocpCounselRegularity;
	}

	@Column(name = "pain_swelling")
	public String getPainSwelling() {
		return this.painSwelling;
	}

	public void setPainSwelling(String painSwelling) {
		this.painSwelling = painSwelling;
	}

	@Column(name = "ppfp_interest")
	public Boolean getPpfpInterest() {
		return this.ppfpInterest;
	}

	public void setPpfpInterest(Boolean ppfpInterest) {
		this.ppfpInterest = ppfpInterest;
	}

	@Column(name = "ppiud_abdominal_pain")
	public String getPpiudAbdominalPain() {
		return this.ppiudAbdominalPain;
	}

	public void setPpiudAbdominalPain(String ppiudAbdominalPain) {
		this.ppiudAbdominalPain = ppiudAbdominalPain;
	}

	@Column(name = "ppiud_problems")
	public String getPpiudProblems() {
		return this.ppiudProblems;
	}

	public void setPpiudProblems(String ppiudProblems) {
		this.ppiudProblems = ppiudProblems;
	}

	@Column(name = "pptl_abdominal_pain")
	public String getPptlAbdominalPain() {
		return this.pptlAbdominalPain;
	}

	public void setPptlAbdominalPain(String pptlAbdominalPain) {
		this.pptlAbdominalPain = pptlAbdominalPain;
	}

	@Column(name = "pptl_pain_surgery")
	public String getPptlPainSurgery() {
		return this.pptlPainSurgery;
	}

	public void setPptlPainSurgery(String pptlPainSurgery) {
		this.pptlPainSurgery = pptlPainSurgery;
	}

	@Column(name = "pptl_problems")
	public String getPptlProblems() {
		return this.pptlProblems;
	}

	public void setPptlProblems(String pptlProblems) {
		this.pptlProblems = pptlProblems;
	}

	@Column(name = "regular_periods")
	public String getRegularPeriods() {
		return this.regularPeriods;
	}

	public void setRegularPeriods(String regularPeriods) {
		this.regularPeriods = regularPeriods;
	}

	@Column(name = "tablets_received")
	public String getTabletsReceived() {
		return this.tabletsReceived;
	}

	public void setTabletsReceived(String tabletsReceived) {
		this.tabletsReceived = tabletsReceived;
	}

	@Column(name = "taken_as_prescribed")
	public String getTakenAsPrescribed() {
		return this.takenAsPrescribed;
	}

	public void setTakenAsPrescribed(String takenAsPrescribed) {
		this.takenAsPrescribed = takenAsPrescribed;
	}

	@Column(name = "tl")
	public String getTl() {
		return this.tl;
	}

	public void setTl(String tl) {
		this.tl = tl;
	}

	@Column(name = "tl_adopted")
	public String getTlAdopted() {
		return this.tlAdopted;
	}

	public void setTlAdopted(String tlAdopted) {
		this.tlAdopted = tlAdopted;
	}

	@Column(name = "tl_consel_incentives")
	public String getTlConselIncentives() {
		return this.tlConselIncentives;
	}

	public void setTlConselIncentives(String tlConselIncentives) {
		this.tlConselIncentives = tlConselIncentives;
	}

	@Column(name = "tl_counsel_follow_up")
	public String getTlCounselFollowUp() {
		return this.tlCounselFollowUp;
	}

	public void setTlCounselFollowUp(String tlCounselFollowUp) {
		this.tlCounselFollowUp = tlCounselFollowUp;
	}

	@Column(name = "tl_counsel_hospital")
	public String getTlCounselHospital() {
		return this.tlCounselHospital;
	}

	public void setTlCounselHospital(String tlCounselHospital) {
		this.tlCounselHospital = tlCounselHospital;
	}

	@Column(name = "tl_counsel_irreversible")
	public String getTlCounselIrreversible() {
		return this.tlCounselIrreversible;
	}

	public void setTlCounselIrreversible(String tlCounselIrreversible) {
		this.tlCounselIrreversible = tlCounselIrreversible;
	}

	@Column(name = "tl_counsel_screening")
	public String getTlCounselScreening() {
		return this.tlCounselScreening;
	}

	public void setTlCounselScreening(String tlCounselScreening) {
		this.tlCounselScreening = tlCounselScreening;
	}

	@Column(name = "tl_counsel_side_effects")
	public String getTlCounselSideEffects() {
		return this.tlCounselSideEffects;
	}

	public void setTlCounselSideEffects(String tlCounselSideEffects) {
		this.tlCounselSideEffects = tlCounselSideEffects;
	}

	@Column(name = "tl_counsel_timing")
	public String getTlCounselTiming() {
		return this.tlCounselTiming;
	}

	public void setTlCounselTiming(String tlCounselTiming) {
		this.tlCounselTiming = tlCounselTiming;
	}

	@Column(name = "understand_tablets")
	public String getUnderstandTablets() {
		return this.understandTablets;
	}

	public void setUnderstandTablets(String understandTablets) {
		this.understandTablets = understandTablets;
	}

	@Column(name = "using_correctly")
	public String getUsingCorrectly() {
		return this.usingCorrectly;
	}

	public void setUsingCorrectly(String usingCorrectly) {
		this.usingCorrectly = usingCorrectly;
	}

	@Column(name = "where_replace")
	public String getWhereReplace() {
		return this.whereReplace;
	}

	public void setWhereReplace(String whereReplace) {
		this.whereReplace = whereReplace;
	}

	@Column(name = "why_no_ppffp")
	public String getWhyNoPpffp() {
		return this.whyNoPpffp;
	}

	public void setWhyNoPpffp(String whyNoPpffp) {
		this.whyNoPpffp = whyNoPpffp;
	}

	@Column(name = "within_42")
	public String getWithin42() {
		return this.within42;
	}

	public void setWithin42(String within42) {
		this.within42 = within42;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_tl_adopted")
	public Date getDateTlAdopted() {
		return this.dateTlAdopted;
	}

	public void setDateTlAdopted(Date dateTlAdopted) {
		this.dateTlAdopted = dateTlAdopted;
	}

	@Column(name = "abdominal_pain")
	public String getAbdominalPain() {
		return this.abdominalPain;
	}

	public void setAbdominalPain(String abdominalPain) {
		this.abdominalPain = abdominalPain;
	}

	@Column(name = "pain_urination")
	public String getPainUrination() {
		return this.painUrination;
	}

	public void setPainUrination(String painUrination) {
		this.painUrination = painUrination;
	}

	@Column(name = "ppiud_bleeding")
	public String getPpiudBleeding() {
		return this.ppiudBleeding;
	}

	public void setPpiudBleeding(String ppiudBleeding) {
		this.ppiudBleeding = ppiudBleeding;
	}

	@Column(name = "ppiud_discharge")
	public String getPpiudDischarge() {
		return this.ppiudDischarge;
	}

	public void setPpiudDischarge(String ppiudDischarge) {
		this.ppiudDischarge = ppiudDischarge;
	}

	@Column(name = "ppiud_fever")
	public String getPpiudFever() {
		return this.ppiudFever;
	}

	public void setPpiudFever(String ppiudFever) {
		this.ppiudFever = ppiudFever;
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
