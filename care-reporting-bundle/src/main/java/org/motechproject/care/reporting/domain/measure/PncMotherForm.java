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
@Table(name = "pnc_mother_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class PncMotherForm extends Form {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Boolean abdominalPain;
	private Date addval;
	private Boolean adoptImmediately;
	private Boolean allPncOnTime;
	private Boolean bleeding;
    private Boolean complications;
	private Boolean congested;
	private Boolean counselBreast;
	private Boolean counselFollowUpPpiud;
	private Boolean counselFollowUpPptl;
	private Boolean counselIncreaseFoodBf;
	private Boolean counselMateralComp;
	private Boolean counselMethods;
	private Boolean counselNeonatalComp;
	private Boolean counselPpfp;
	private Boolean counselTimeIud;
	private Date dateDeath;
	private Date dateIudAdopted;
	private Date dateLastVisit;
	private Date dateNextEb;
	private Date dateNextPnc;
	private Date dateNextCf;
	private Date datePnc1;
	private Date datePnc2;
	private Date datePnc3;
	private Date dateTlAdopted;
	private Boolean deathVillage;
	private Boolean discharge;
	private Boolean distension;
	private Boolean eatingWell;
	private String familyPlanningType;
	private Boolean fever;
	private String firstPncTime;
	private Boolean intervalPpfpInterest;
	private Boolean iud;
	private Boolean iudAdopted;
	private Boolean iudCounselDuration;
	private Boolean iudCounselFollowUp;
	private Boolean iudCounselHospital;
	private Boolean iudCounselPlacement;
	private Boolean iudCounselScreening;
	private Boolean iudCounselSideEffects;
	private String lastVisitType;
	private Boolean motherAlive;
	private Boolean motherChildAlive;
	private String nextvisittype;
	private Short numChildren;
	private Boolean otherIssues;
	private Boolean painUrination;
	private Boolean painfulNipples;
	private String placeDeath;
	private Integer pnc1DaysLate;
	private Integer pnc2DaysLate;
	private Integer pnc3DaysLate;
	private Short pncVisitNum;
	private Boolean ppfpInterest;
	private Boolean ppiudAbdominalPain;
	private Boolean ppiudBleeding;
	private Boolean ppiudDischarge;
	private Boolean ppiudFever;
	private Boolean ppiudProblems;
	private Boolean pptlAbdominalPain;
	private Boolean pptlExcessiveBleeding;
	private Boolean pptlPainSurgery;
	private Boolean pptlProblems;
	private Boolean problemsBreast;
	private Boolean safe;
	private String siteDeath;
	private Boolean tl;
	private Boolean tlAdopted;
	private Boolean tlConselIncentives;
	private Boolean tlCounselFollowUp;
	private Boolean tlCounselHospital;
	private Boolean tlCounselIrreversible;
	private Boolean tlCounselScreening;
	private Boolean tlCounselSideEffects;
	private Boolean tlCounselTiming;
	private String whyNoPpffp;
    private Date creationTime = new Date();

    public PncMotherForm() {
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

	@Column(name = "abdominal_pain")
	public Boolean getAbdominalPain() {
		return this.abdominalPain;
	}

	public void setAbdominalPain(Boolean abdominalPain) {
		this.abdominalPain = abdominalPain;
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
	public Boolean getAdoptImmediately() {
		return this.adoptImmediately;
	}

	public void setAdoptImmediately(Boolean adoptImmediately) {
		this.adoptImmediately = adoptImmediately;
	}

	@Column(name = "all_pnc_on_time")
	public Boolean getAllPncOnTime() {
		return this.allPncOnTime;
	}

	public void setAllPncOnTime(Boolean allPncOnTime) {
		this.allPncOnTime = allPncOnTime;
	}

	@Column(name = "bleeding")
	public Boolean getBleeding() {
		return this.bleeding;
	}

	public void setBleeding(Boolean bleeding) {
		this.bleeding = bleeding;
	}

    @Column(name = "complications")
	public Boolean getComplications() {
		return this.complications;
	}

	public void setComplications(Boolean complications) {
		this.complications = complications;
	}

	@Column(name = "congested")
	public Boolean getCongested() {
		return this.congested;
	}

	public void setCongested(Boolean congested) {
		this.congested = congested;
	}

	@Column(name = "counsel_breast")
	public Boolean getCounselBreast() {
		return this.counselBreast;
	}

	public void setCounselBreast(Boolean counselBreast) {
		this.counselBreast = counselBreast;
	}

	@Column(name = "counsel_follow_up_ppiud")
	public Boolean getCounselFollowUpPpiud() {
		return this.counselFollowUpPpiud;
	}

	public void setCounselFollowUpPpiud(Boolean counselFollowUpPpiud) {
		this.counselFollowUpPpiud = counselFollowUpPpiud;
	}

	@Column(name = "counsel_follow_up_pptl")
	public Boolean getCounselFollowUpPptl() {
		return this.counselFollowUpPptl;
	}

	public void setCounselFollowUpPptl(Boolean counselFollowUpPptl) {
		this.counselFollowUpPptl = counselFollowUpPptl;
	}

	@Column(name = "counsel_increase_food_bf")
	public Boolean getCounselIncreaseFoodBf() {
		return this.counselIncreaseFoodBf;
	}

	public void setCounselIncreaseFoodBf(Boolean counselIncreaseFoodBf) {
		this.counselIncreaseFoodBf = counselIncreaseFoodBf;
	}

	@Column(name = "counsel_materal_comp")
	public Boolean getCounselMateralComp() {
		return this.counselMateralComp;
	}

	public void setCounselMateralComp(Boolean counselMateralComp) {
		this.counselMateralComp = counselMateralComp;
	}

	@Column(name = "counsel_methods")
	public Boolean getCounselMethods() {
		return this.counselMethods;
	}

	public void setCounselMethods(Boolean counselMethods) {
		this.counselMethods = counselMethods;
	}

	@Column(name = "counsel_neonatal_comp")
	public Boolean getCounselNeonatalComp() {
		return this.counselNeonatalComp;
	}

	public void setCounselNeonatalComp(Boolean counselNeonatalComp) {
		this.counselNeonatalComp = counselNeonatalComp;
	}

	@Column(name = "counsel_ppfp")
	public Boolean getCounselPpfp() {
		return this.counselPpfp;
	}

	public void setCounselPpfp(Boolean counselPpfp) {
		this.counselPpfp = counselPpfp;
	}

	@Column(name = "counsel_time_iud")
	public Boolean getCounselTimeIud() {
		return this.counselTimeIud;
	}

	public void setCounselTimeIud(Boolean counselTimeIud) {
		this.counselTimeIud = counselTimeIud;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_death")
	public Date getDateDeath() {
		return this.dateDeath;
	}

	public void setDateDeath(Date dateDeath) {
		this.dateDeath = dateDeath;
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
	@Column(name = "date_last_visit")
	public Date getDateLastVisit() {
		return this.dateLastVisit;
	}

	public void setDateLastVisit(Date dateLastVisit) {
		this.dateLastVisit = dateLastVisit;
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
    @Column(name = "date_next_cf")
    public Date getDateNextCf() {
        return this.dateNextCf;
    }

    public void setDateNextCf(Date dateNextCf) {
        this.dateNextCf = dateNextCf;
    }
    
	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_pnc")
	public Date getDateNextPnc() {
		return this.dateNextPnc;
	}

	public void setDateNextPnc(Date dateNextPnc) {
		this.dateNextPnc = dateNextPnc;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_pnc_1")
	public Date getDatePnc1() {
		return this.datePnc1;
	}

	public void setDatePnc1(Date datePnc1) {
		this.datePnc1 = datePnc1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_pnc_2")
	public Date getDatePnc2() {
		return this.datePnc2;
	}

	public void setDatePnc2(Date datePnc2) {
		this.datePnc2 = datePnc2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_pnc_3")
	public Date getDatePnc3() {
		return this.datePnc3;
	}

	public void setDatePnc3(Date datePnc3) {
		this.datePnc3 = datePnc3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_tl_adopted")
	public Date getDateTlAdopted() {
		return this.dateTlAdopted;
	}

	public void setDateTlAdopted(Date dateTlAdopted) {
		this.dateTlAdopted = dateTlAdopted;
	}

	@Column(name = "death_village")
	public Boolean getDeathVillage() {
		return this.deathVillage;
	}

	public void setDeathVillage(Boolean deathVillage) {
		this.deathVillage = deathVillage;
	}

	@Column(name = "discharge")
	public Boolean getDischarge() {
		return this.discharge;
	}

	public void setDischarge(Boolean discharge) {
		this.discharge = discharge;
	}

	@Column(name = "distension")
	public Boolean getDistension() {
		return this.distension;
	}

	public void setDistension(Boolean distension) {
		this.distension = distension;
	}

	@Column(name = "eating_well")
	public Boolean getEatingWell() {
		return this.eatingWell;
	}

	public void setEatingWell(Boolean eatingWell) {
		this.eatingWell = eatingWell;
	}

	@Column(name = "family_planning_type")
	public String getFamilyPlanningType() {
		return this.familyPlanningType;
	}

	public void setFamilyPlanningType(String familyPlanningType) {
		this.familyPlanningType = familyPlanningType;
	}

	@Column(name = "fever")
	public Boolean getFever() {
		return this.fever;
	}

	public void setFever(Boolean fever) {
		this.fever = fever;
	}

	@Column(name = "first_pnc_time")
	public String getFirstPncTime() {
		return this.firstPncTime;
	}

	public void setFirstPncTime(String firstPncTime) {
		this.firstPncTime = firstPncTime;
	}

	@Column(name = "interval_ppfp_interest")
	public Boolean getIntervalPpfpInterest() {
		return this.intervalPpfpInterest;
	}

	public void setIntervalPpfpInterest(Boolean intervalPpfpInterest) {
		this.intervalPpfpInterest = intervalPpfpInterest;
	}

	@Column(name = "iud")
	public Boolean getIud() {
		return this.iud;
	}

	public void setIud(Boolean iud) {
		this.iud = iud;
	}

	@Column(name = "iud_adopted")
	public Boolean getIudAdopted() {
		return this.iudAdopted;
	}

	public void setIudAdopted(Boolean iudAdopted) {
		this.iudAdopted = iudAdopted;
	}

	@Column(name = "iud_counsel_duration")
	public Boolean getIudCounselDuration() {
		return this.iudCounselDuration;
	}

	public void setIudCounselDuration(Boolean iudCounselDuration) {
		this.iudCounselDuration = iudCounselDuration;
	}

	@Column(name = "iud_counsel_follow_up")
	public Boolean getIudCounselFollowUp() {
		return this.iudCounselFollowUp;
	}

	public void setIudCounselFollowUp(Boolean iudCounselFollowUp) {
		this.iudCounselFollowUp = iudCounselFollowUp;
	}

	@Column(name = "iud_counsel_hospital")
	public Boolean getIudCounselHospital() {
		return this.iudCounselHospital;
	}

	public void setIudCounselHospital(Boolean iudCounselHospital) {
		this.iudCounselHospital = iudCounselHospital;
	}

	@Column(name = "iud_counsel_placement")
	public Boolean getIudCounselPlacement() {
		return this.iudCounselPlacement;
	}

	public void setIudCounselPlacement(Boolean iudCounselPlacement) {
		this.iudCounselPlacement = iudCounselPlacement;
	}

	@Column(name = "iud_counsel_screening")
	public Boolean getIudCounselScreening() {
		return this.iudCounselScreening;
	}

	public void setIudCounselScreening(Boolean iudCounselScreening) {
		this.iudCounselScreening = iudCounselScreening;
	}

	@Column(name = "iud_counsel_side_effects")
	public Boolean getIudCounselSideEffects() {
		return this.iudCounselSideEffects;
	}

	public void setIudCounselSideEffects(Boolean iudCounselSideEffects) {
		this.iudCounselSideEffects = iudCounselSideEffects;
	}

	@Column(name = "last_visit_type")
	public String getLastVisitType() {
		return this.lastVisitType;
	}

	public void setLastVisitType(String lastVisitType) {
		this.lastVisitType = lastVisitType;
	}

	@Column(name = "mother_alive")
	public Boolean getMotherAlive() {
		return this.motherAlive;
	}

	public void setMotherAlive(Boolean motherAlive) {
		this.motherAlive = motherAlive;
	}

	@Column(name = "mother_child_alive")
	public Boolean getMotherChildAlive() {
		return this.motherChildAlive;
	}

	public void setMotherChildAlive(Boolean motherChildAlive) {
		this.motherChildAlive = motherChildAlive;
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

	@Column(name = "other_issues")
	public Boolean getOtherIssues() {
		return this.otherIssues;
	}

	public void setOtherIssues(Boolean otherIssues) {
		this.otherIssues = otherIssues;
	}

	@Column(name = "pain_urination")
	public Boolean getPainUrination() {
		return this.painUrination;
	}

	public void setPainUrination(Boolean painUrination) {
		this.painUrination = painUrination;
	}

	@Column(name = "painful_nipples")
	public Boolean getPainfulNipples() {
		return this.painfulNipples;
	}

	public void setPainfulNipples(Boolean painfulNipples) {
		this.painfulNipples = painfulNipples;
	}

	@Column(name = "place_death")
	public String getPlaceDeath() {
		return this.placeDeath;
	}

	public void setPlaceDeath(String placeDeath) {
		this.placeDeath = placeDeath;
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

	@Column(name = "pnc_visit_num")
	public Short getPncVisitNum() {
		return this.pncVisitNum;
	}

	public void setPncVisitNum(Short pncVisitNum) {
		this.pncVisitNum = pncVisitNum;
	}

	@Column(name = "ppfp_interest")
	public Boolean getPpfpInterest() {
		return this.ppfpInterest;
	}

	public void setPpfpInterest(Boolean ppfpInterest) {
		this.ppfpInterest = ppfpInterest;
	}

	@Column(name = "ppiud_abdominal_pain")
	public Boolean getPpiudAbdominalPain() {
		return this.ppiudAbdominalPain;
	}

	public void setPpiudAbdominalPain(Boolean ppiudAbdominalPain) {
		this.ppiudAbdominalPain = ppiudAbdominalPain;
	}

	@Column(name = "ppiud_bleeding")
	public Boolean getPpiudBleeding() {
		return this.ppiudBleeding;
	}

	public void setPpiudBleeding(Boolean ppiudBleeding) {
		this.ppiudBleeding = ppiudBleeding;
	}

	@Column(name = "ppiud_discharge")
	public Boolean getPpiudDischarge() {
		return this.ppiudDischarge;
	}

	public void setPpiudDischarge(Boolean ppiudDischarge) {
		this.ppiudDischarge = ppiudDischarge;
	}

	@Column(name = "ppiud_fever")
	public Boolean getPpiudFever() {
		return this.ppiudFever;
	}

	public void setPpiudFever(Boolean ppiudFever) {
		this.ppiudFever = ppiudFever;
	}

	@Column(name = "ppiud_problems")
	public Boolean getPpiudProblems() {
		return this.ppiudProblems;
	}

	public void setPpiudProblems(Boolean ppiudProblems) {
		this.ppiudProblems = ppiudProblems;
	}

	@Column(name = "pptl_abdominal_pain")
	public Boolean getPptlAbdominalPain() {
		return this.pptlAbdominalPain;
	}

	public void setPptlAbdominalPain(Boolean pptlAbdominalPain) {
		this.pptlAbdominalPain = pptlAbdominalPain;
	}

	@Column(name = "pptl_excessive_bleeding")
	public Boolean getPptlExcessiveBleeding() {
		return this.pptlExcessiveBleeding;
	}

	public void setPptlExcessiveBleeding(Boolean pptlExcessiveBleeding) {
		this.pptlExcessiveBleeding = pptlExcessiveBleeding;
	}

	@Column(name = "pptl_pain_surgery")
	public Boolean getPptlPainSurgery() {
		return this.pptlPainSurgery;
	}

	public void setPptlPainSurgery(Boolean pptlPainSurgery) {
		this.pptlPainSurgery = pptlPainSurgery;
	}

	@Column(name = "pptl_problems")
	public Boolean getPptlProblems() {
		return this.pptlProblems;
	}

	public void setPptlProblems(Boolean pptlProblems) {
		this.pptlProblems = pptlProblems;
	}

	@Column(name = "problems_breast")
	public Boolean getProblemsBreast() {
		return this.problemsBreast;
	}

	public void setProblemsBreast(Boolean problemsBreast) {
		this.problemsBreast = problemsBreast;
	}

	@Column(name = "safe")
	public Boolean getSafe() {
		return this.safe;
	}

	public void setSafe(Boolean safe) {
		this.safe = safe;
	}

	@Column(name = "site_death")
	public String getSiteDeath() {
		return this.siteDeath;
	}

	public void setSiteDeath(String siteDeath) {
		this.siteDeath = siteDeath;
	}

	@Column(name = "tl")
	public Boolean getTl() {
		return this.tl;
	}

	public void setTl(Boolean tl) {
		this.tl = tl;
	}

	@Column(name = "tl_adopted")
	public Boolean getTlAdopted() {
		return this.tlAdopted;
	}

	public void setTlAdopted(Boolean tlAdopted) {
		this.tlAdopted = tlAdopted;
	}

	@Column(name = "tl_consel_incentives")
	public Boolean getTlConselIncentives() {
		return this.tlConselIncentives;
	}

	public void setTlConselIncentives(Boolean tlConselIncentives) {
		this.tlConselIncentives = tlConselIncentives;
	}

	@Column(name = "tl_counsel_follow_up")
	public Boolean getTlCounselFollowUp() {
		return this.tlCounselFollowUp;
	}

	public void setTlCounselFollowUp(Boolean tlCounselFollowUp) {
		this.tlCounselFollowUp = tlCounselFollowUp;
	}

	@Column(name = "tl_counsel_hospital")
	public Boolean getTlCounselHospital() {
		return this.tlCounselHospital;
	}

	public void setTlCounselHospital(Boolean tlCounselHospital) {
		this.tlCounselHospital = tlCounselHospital;
	}

	@Column(name = "tl_counsel_irreversible")
	public Boolean getTlCounselIrreversible() {
		return this.tlCounselIrreversible;
	}

	public void setTlCounselIrreversible(Boolean tlCounselIrreversible) {
		this.tlCounselIrreversible = tlCounselIrreversible;
	}

	@Column(name = "tl_counsel_screening")
	public Boolean getTlCounselScreening() {
		return this.tlCounselScreening;
	}

	public void setTlCounselScreening(Boolean tlCounselScreening) {
		this.tlCounselScreening = tlCounselScreening;
	}

	@Column(name = "tl_counsel_side_effects")
	public Boolean getTlCounselSideEffects() {
		return this.tlCounselSideEffects;
	}

	public void setTlCounselSideEffects(Boolean tlCounselSideEffects) {
		this.tlCounselSideEffects = tlCounselSideEffects;
	}

	@Column(name = "tl_counsel_timing")
	public Boolean getTlCounselTiming() {
		return this.tlCounselTiming;
	}

	public void setTlCounselTiming(Boolean tlCounselTiming) {
		this.tlCounselTiming = tlCounselTiming;
	}

	@Column(name = "why_no_ppffp")
	public String getWhyNoPpffp() {
		return this.whyNoPpffp;
	}

	public void setWhyNoPpffp(String whyNoPpffp) {
		this.whyNoPpffp = whyNoPpffp;
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
