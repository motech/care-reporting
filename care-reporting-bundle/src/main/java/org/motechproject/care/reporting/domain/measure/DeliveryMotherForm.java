package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Time;
import java.util.Date;


@Entity
@Table(name = "delivery_mother_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class DeliveryMotherForm extends Form {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private String ppiud;
	private String pptl;
	private String abdPain;
	private Date add;
	private String birthPlace;
	private Date dateDelFu;
	private Date dateLastVisit;
	private Date dateNextCf;
	private Date dateNextEb;
	private Date dateNextPnc;
	private String familyPlanningType;
	private String lastVisitType;
	private String motherAlive;
	private String term;
	private Short castNumChildren;
	private String complications;
	private Date dateDeath;
	private String deathVillage;
	private String deliveryNature;
	private String fever;
	private String hasDelivered;
	private Short howManyChildren;
	private String ifaTabletsGiven;
	private String inDistrict;
	private String jsyMoney;
	private String nextvisittype;
	private Date notified;
	private Short numChildren;
	private String otherConditions;
	private String otherDistrict;
	private String otherVillage;
	private String painUrine;
	private String placeDeath;
	private String postPostpartumFp;
	private String safe;
	private String siteDeath;
	private String vaginalDischarge;
	private String whereBorn;
	private String whichHospital;
	private String whichVillage;
    private Boolean close;
    private Date creationTime = new Date();
    private Date jsyMoneyDate;
    private String deliveryComplications;
    private Date dischargeDate;
    private Date dischargeTime;
    private String whoAssisted;
    private String bleeding;
    private String homeSbaAssist;
    private Integer ageCurrentWeight;
    private Integer ageLastWeight;

    public DeliveryMotherForm() {
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

	@Temporal(TemporalType.DATE)
	@Column(name = "add")
	public Date getAdd() {
		return this.add;
	}

	public void setAdd(Date add) {
		this.add = add;
	}

	@Column(name = "birth_place")
	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_del_fu")
	public Date getDateDelFu() {
		return this.dateDelFu;
	}

	public void setDateDelFu(Date dateDelFu) {
		this.dateDelFu = dateDelFu;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_pnc")
	public Date getDateNextPnc() {
		return this.dateNextPnc;
	}

	public void setDateNextPnc(Date dateNextPnc) {
		this.dateNextPnc = dateNextPnc;
	}

	@Column(name = "family_planning_type")
	public String getFamilyPlanningType() {
		return this.familyPlanningType;
	}

	public void setFamilyPlanningType(String familyPlanningType) {
		this.familyPlanningType = familyPlanningType;
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

	@Column(name = "term")
	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Column(name = "cast_num_children")
	public Short getCastNumChildren() {
		return this.castNumChildren;
	}

	public void setCastNumChildren(Short castNumChildren) {
		this.castNumChildren = castNumChildren;
	}

	@Column(name = "complications")
	public String getComplications() {
		return this.complications;
	}

	public void setComplications(String complications) {
		this.complications = complications;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_death")
	public Date getDateDeath() {
		return this.dateDeath;
	}

	public void setDateDeath(Date dateDeath) {
		this.dateDeath = dateDeath;
	}

	@Column(name = "death_village")
	public String getDeathVillage() {
		return this.deathVillage;
	}

	public void setDeathVillage(String deathVillage) {
		this.deathVillage = deathVillage;
	}

	@Column(name = "delivery_nature")
	public String getDeliveryNature() {
		return this.deliveryNature;
	}

	public void setDeliveryNature(String deliveryNature) {
		this.deliveryNature = deliveryNature;
	}

	@Column(name = "fever")
	public String getFever() {
		return this.fever;
	}

	public void setFever(String fever) {
		this.fever = fever;
	}

	@Column(name = "has_delivered")
	public String getHasDelivered() {
		return this.hasDelivered;
	}

	public void setHasDelivered(String hasDelivered) {
		this.hasDelivered = hasDelivered;
	}

	@Column(name = "how_many_children")
	public Short getHowManyChildren() {
		return this.howManyChildren;
	}

	public void setHowManyChildren(Short howManyChildren) {
		this.howManyChildren = howManyChildren;
	}

	@Column(name = "ifa_tablets_given")
	public String getIfaTabletsGiven() {
		return this.ifaTabletsGiven;
	}

	public void setIfaTabletsGiven(String ifaTabletsGiven) {
		this.ifaTabletsGiven = ifaTabletsGiven;
	}

	@Column(name = "in_district")
	public String getInDistrict() {
		return this.inDistrict;
	}

	public void setInDistrict(String inDistrict) {
		this.inDistrict = inDistrict;
	}

	@Column(name = "jsy_money")
	public String getJsyMoney() {
		return this.jsyMoney;
	}

	public void setJsyMoney(String jsyMoney) {
		this.jsyMoney = jsyMoney;
	}

	@Column(name = "nextvisittype")
	public String getNextvisittype() {
		return this.nextvisittype;
	}

	public void setNextvisittype(String nextvisittype) {
		this.nextvisittype = nextvisittype;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "notified")
	public Date getNotified() {
		return this.notified;
	}

	public void setNotified(Date notified) {
		this.notified = notified;
	}

	@Column(name = "num_children")
	public Short getNumChildren() {
		return this.numChildren;
	}

	public void setNumChildren(Short numChildren) {
		this.numChildren = numChildren;
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

	@Column(name = "place_death")
	public String getPlaceDeath() {
		return this.placeDeath;
	}

	public void setPlaceDeath(String placeDeath) {
		this.placeDeath = placeDeath;
	}

	@Column(name = "post_postpartum_fp")
	public String getPostPostpartumFp() {
		return this.postPostpartumFp;
	}

	public void setPostPostpartumFp(String postPostpartumFp) {
		this.postPostpartumFp = postPostpartumFp;
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

	@Column(name = "vaginal_discharge")
	public String getVaginalDischarge() {
		return this.vaginalDischarge;
	}

	public void setVaginalDischarge(String vaginalDischarge) {
		this.vaginalDischarge = vaginalDischarge;
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

    @Column(name = "jsy_money_date")
    public Date getJsyMoneyDate() {
        return jsyMoneyDate;
    }

    public void setJsyMoneyDate(Date jsyMoneyDate) {
        this.jsyMoneyDate = jsyMoneyDate;
    }

    @Column(name = "delivery_complications")
    public String getDeliveryComplications() {
        return deliveryComplications;
    }

    public void setDeliveryComplications(String deliveryComplications) {
        this.deliveryComplications = deliveryComplications;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "discharge_date")
    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "discharge_time")
    public Date getDischargeTime() {
        return dischargeTime;
    }

    public void setDischargeTime(Date dischargeTime) {
        this.dischargeTime = dischargeTime;
    }

    @Column(name = "who_assisted")
    public String getWhoAssisted() {
        return whoAssisted;
    }

    public void setWhoAssisted(String whoAssisted) {
        this.whoAssisted = whoAssisted;
    }

    @Column(name = "bleeding")
    public String getBleeding() {
        return bleeding;
    }

    public void setBleeding(String bleeding) {
        this.bleeding = bleeding;
    }

    @Column(name = "home_sba_assist")
    public String getHomeSbaAssist() {
        return homeSbaAssist;
    }

    public void setHomeSbaAssist(String homeSbaAssist) {
        this.homeSbaAssist = homeSbaAssist;
    }

    @Column(name = "age_current_weight")
    public Integer getAgeCurrentWeight() {
        return ageCurrentWeight;
    }

    public void setAgeCurrentWeight(Integer ageCurrentWeight) {
        this.ageCurrentWeight = ageCurrentWeight;
    }

    @Column(name = "age_last_weight")
    public Integer getAgeLastWeight() {
        return ageLastWeight;
    }

    public void setAgeLastWeight(Integer ageLastWeight) {
        this.ageLastWeight = ageLastWeight;
    }
}
