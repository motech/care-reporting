package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
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
	private String abdominalPain;
	private Date addval;
	private String adoptImmediately;
	private String allPncOnTime;
	private String bleeding;
    private String complications;
	private String congested;
	private String counselBreast;
	private String counselFollowUpPpiud;
	private String counselFollowUpPptl;
	private String counselIncreaseFoodBf;
	private String counselMateralComp;
	private String counselMethods;
	private String counselNeonatalComp;
	private String counselPpfp;
	private String counselTimeIud;
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
	private String deathVillage;
	private String discharge;
	private String distension;
	private String eatingWell;
	private String familyPlanningType;
	private String fever;
	private String firstPncTime;
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
	private String motherAlive;
	private String motherChildAlive;
	private String nextvisittype;
	private Short numChildren;
	private String otherIssues;
	private String painUrination;
	private String painfulNipples;
	private String placeDeath;
	private Integer pnc1DaysLate;
	private Integer pnc2DaysLate;
	private Integer pnc3DaysLate;
	private Short pncVisitNum;
	private Boolean ppfpInterest;
	private String ppiudAbdominalPain;
	private String ppiudBleeding;
	private String ppiudDischarge;
	private String ppiudFever;
	private String ppiudProblems;
	private String pptlAbdominalPain;
	private String pptlExcessiveBleeding;
	private String pptlPainSurgery;
	private String pptlProblems;
	private String problemsBreast;
	private String safe;
	private String siteDeath;
	private String tl;
	private String tlAdopted;
	private String tlConselIncentives;
	private String tlCounselFollowUp;
	private String tlCounselHospital;
	private String tlCounselIrreversible;
	private String tlCounselScreening;
	private String tlCounselSideEffects;
	private String tlCounselTiming;
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

	@Column(name = "abdominal_pain")
	public String getAbdominalPain() {
		return this.abdominalPain;
	}

	public void setAbdominalPain(String abdominalPain) {
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
	public String getAdoptImmediately() {
		return this.adoptImmediately;
	}

	public void setAdoptImmediately(String adoptImmediately) {
		this.adoptImmediately = adoptImmediately;
	}

	@Column(name = "all_pnc_on_time")
	public String getAllPncOnTime() {
		return this.allPncOnTime;
	}

	public void setAllPncOnTime(String allPncOnTime) {
		this.allPncOnTime = allPncOnTime;
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

	@Column(name = "congested")
	public String getCongested() {
		return this.congested;
	}

	public void setCongested(String congested) {
		this.congested = congested;
	}

	@Column(name = "counsel_breast")
	public String getCounselBreast() {
		return this.counselBreast;
	}

	public void setCounselBreast(String counselBreast) {
		this.counselBreast = counselBreast;
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

	@Column(name = "counsel_increase_food_bf")
	public String getCounselIncreaseFoodBf() {
		return this.counselIncreaseFoodBf;
	}

	public void setCounselIncreaseFoodBf(String counselIncreaseFoodBf) {
		this.counselIncreaseFoodBf = counselIncreaseFoodBf;
	}

	@Column(name = "counsel_materal_comp")
	public String getCounselMateralComp() {
		return this.counselMateralComp;
	}

	public void setCounselMateralComp(String counselMateralComp) {
		this.counselMateralComp = counselMateralComp;
	}

	@Column(name = "counsel_methods")
	public String getCounselMethods() {
		return this.counselMethods;
	}

	public void setCounselMethods(String counselMethods) {
		this.counselMethods = counselMethods;
	}

	@Column(name = "counsel_neonatal_comp")
	public String getCounselNeonatalComp() {
		return this.counselNeonatalComp;
	}

	public void setCounselNeonatalComp(String counselNeonatalComp) {
		this.counselNeonatalComp = counselNeonatalComp;
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
	public String getDeathVillage() {
		return this.deathVillage;
	}

	public void setDeathVillage(String deathVillage) {
		this.deathVillage = deathVillage;
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

	@Column(name = "eating_well")
	public String getEatingWell() {
		return this.eatingWell;
	}

	public void setEatingWell(String eatingWell) {
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
	public String getFever() {
		return this.fever;
	}

	public void setFever(String fever) {
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

	@Column(name = "mother_alive")
	public String getMotherAlive() {
		return this.motherAlive;
	}

	public void setMotherAlive(String motherAlive) {
		this.motherAlive = motherAlive;
	}

	@Column(name = "mother_child_alive")
	public String getMotherChildAlive() {
		return this.motherChildAlive;
	}

	public void setMotherChildAlive(String motherChildAlive) {
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
	public String getOtherIssues() {
		return this.otherIssues;
	}

	public void setOtherIssues(String otherIssues) {
		this.otherIssues = otherIssues;
	}

	@Column(name = "pain_urination")
	public String getPainUrination() {
		return this.painUrination;
	}

	public void setPainUrination(String painUrination) {
		this.painUrination = painUrination;
	}

	@Column(name = "painful_nipples")
	public String getPainfulNipples() {
		return this.painfulNipples;
	}

	public void setPainfulNipples(String painfulNipples) {
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
	public String getPpiudAbdominalPain() {
		return this.ppiudAbdominalPain;
	}

	public void setPpiudAbdominalPain(String ppiudAbdominalPain) {
		this.ppiudAbdominalPain = ppiudAbdominalPain;
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

	@Column(name = "pptl_excessive_bleeding")
	public String getPptlExcessiveBleeding() {
		return this.pptlExcessiveBleeding;
	}

	public void setPptlExcessiveBleeding(String pptlExcessiveBleeding) {
		this.pptlExcessiveBleeding = pptlExcessiveBleeding;
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

	@Column(name = "problems_breast")
	public String getProblemsBreast() {
		return this.problemsBreast;
	}

	public void setProblemsBreast(String problemsBreast) {
		this.problemsBreast = problemsBreast;
	}

	@Column(name = "safe")
	public String getSafe() {
		return this.safe;
	}

	public void setSafe(String safe) {
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
