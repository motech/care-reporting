package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ebf_child_form", uniqueConstraints = @UniqueConstraint(columnNames = {"instance_id","case_id"}))
public class EbfChildForm extends Form {
	private int id;
	private Flw flw;
	private ChildCase childCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Boolean addVaccinations;
	private Boolean atNight;
	private Boolean babyBcg;
	private Boolean babyDpt1;
	private Boolean babyDpt2;
	private Boolean babyDpt3;
	private Boolean babyHepB0;
	private Boolean babyHepB1;
	private Boolean babyHepB2;
	private Boolean babyHepB3;
	private Boolean babyOpv0;
	private Boolean babyOpv1;
	private Boolean babyOpv2;
	private Boolean babyOpv3;
	private Date bcgDate;
	private Boolean breastfeeding;
	private String caseName;
	private String childName;
	private Boolean counselAdequateBf;
	private Boolean counselOnlyMilk;
	private Boolean counselStopBottle;
	private Date dpt1Date;
	private Date dpt2Date;
	private Date dpt3Date;
	private Boolean eating;
	private Boolean emptying;
	private Boolean feedingBottle;
	private Date hepB0Date;
	private Date hepB1Date;
	private Date hepB2Date;
	private Date hepB3Date;
	private Boolean moreFeedingLessSix;
	private Boolean nameUpdate;
	private String notBreasfeeding;
	private Boolean onDemand;
	private Date opv0Date;
	private Date opv1Date;
	private Date opv2Date;
	private Date opv3Date;
	private Boolean recentFever;
	private Boolean teaOther;
	private Boolean treatedLessSix;
	private Boolean waterOrMilk;
    private Date creationTime = new Date();

    public EbfChildForm() {
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

	@Column(name = "add_vaccinations")
	public Boolean getAddVaccinations() {
		return this.addVaccinations;
	}

	public void setAddVaccinations(Boolean addVaccinations) {
		this.addVaccinations = addVaccinations;
	}

	@Column(name = "at_night")
	public Boolean getAtNight() {
		return this.atNight;
	}

	public void setAtNight(Boolean atNight) {
		this.atNight = atNight;
	}

	@Column(name = "baby_bcg")
	public Boolean getBabyBcg() {
		return this.babyBcg;
	}

	public void setBabyBcg(Boolean babyBcg) {
		this.babyBcg = babyBcg;
	}

	@Column(name = "baby_dpt1")
	public Boolean getBabyDpt1() {
		return this.babyDpt1;
	}

	public void setBabyDpt1(Boolean babyDpt1) {
		this.babyDpt1 = babyDpt1;
	}

	@Column(name = "baby_dpt2")
	public Boolean getBabyDpt2() {
		return this.babyDpt2;
	}

	public void setBabyDpt2(Boolean babyDpt2) {
		this.babyDpt2 = babyDpt2;
	}

	@Column(name = "baby_dpt3")
	public Boolean getBabyDpt3() {
		return this.babyDpt3;
	}

	public void setBabyDpt3(Boolean babyDpt3) {
		this.babyDpt3 = babyDpt3;
	}

	@Column(name = "baby_hep_b_0")
	public Boolean getBabyHepB0() {
		return this.babyHepB0;
	}

	public void setBabyHepB0(Boolean babyHepB0) {
		this.babyHepB0 = babyHepB0;
	}

	@Column(name = "baby_hep_b_1")
	public Boolean getBabyHepB1() {
		return this.babyHepB1;
	}

	public void setBabyHepB1(Boolean babyHepB1) {
		this.babyHepB1 = babyHepB1;
	}

	@Column(name = "baby_hep_b_2")
	public Boolean getBabyHepB2() {
		return this.babyHepB2;
	}

	public void setBabyHepB2(Boolean babyHepB2) {
		this.babyHepB2 = babyHepB2;
	}

	@Column(name = "baby_hep_b_3")
	public Boolean getBabyHepB3() {
		return this.babyHepB3;
	}

	public void setBabyHepB3(Boolean babyHepB3) {
		this.babyHepB3 = babyHepB3;
	}

	@Column(name = "baby_opv0")
	public Boolean getBabyOpv0() {
		return this.babyOpv0;
	}

	public void setBabyOpv0(Boolean babyOpv0) {
		this.babyOpv0 = babyOpv0;
	}

	@Column(name = "baby_opv1")
	public Boolean getBabyOpv1() {
		return this.babyOpv1;
	}

	public void setBabyOpv1(Boolean babyOpv1) {
		this.babyOpv1 = babyOpv1;
	}

	@Column(name = "baby_opv2")
	public Boolean getBabyOpv2() {
		return this.babyOpv2;
	}

	public void setBabyOpv2(Boolean babyOpv2) {
		this.babyOpv2 = babyOpv2;
	}

	@Column(name = "baby_opv3")
	public Boolean getBabyOpv3() {
		return this.babyOpv3;
	}

	public void setBabyOpv3(Boolean babyOpv3) {
		this.babyOpv3 = babyOpv3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "bcg_date")
	public Date getBcgDate() {
		return this.bcgDate;
	}

	public void setBcgDate(Date bcgDate) {
		this.bcgDate = bcgDate;
	}

	@Column(name = "breastfeeding")
	public Boolean getBreastfeeding() {
		return this.breastfeeding;
	}

	public void setBreastfeeding(Boolean breastfeeding) {
		this.breastfeeding = breastfeeding;
	}

	@Column(name = "case_name")
	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	@Column(name = "child_name")
	public String getChildName() {
		return this.childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	@Column(name = "counsel_adequate_bf")
	public Boolean getCounselAdequateBf() {
		return this.counselAdequateBf;
	}

	public void setCounselAdequateBf(Boolean counselAdequateBf) {
		this.counselAdequateBf = counselAdequateBf;
	}

	@Column(name = "counsel_only_milk")
	public Boolean getCounselOnlyMilk() {
		return this.counselOnlyMilk;
	}

	public void setCounselOnlyMilk(Boolean counselOnlyMilk) {
		this.counselOnlyMilk = counselOnlyMilk;
	}

	@Column(name = "counsel_stop_bottle")
	public Boolean getCounselStopBottle() {
		return this.counselStopBottle;
	}

	public void setCounselStopBottle(Boolean counselStopBottle) {
		this.counselStopBottle = counselStopBottle;
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

	@Column(name = "eating")
	public Boolean getEating() {
		return this.eating;
	}

	public void setEating(Boolean eating) {
		this.eating = eating;
	}

	@Column(name = "emptying")
	public Boolean getEmptying() {
		return this.emptying;
	}

	public void setEmptying(Boolean emptying) {
		this.emptying = emptying;
	}

	@Column(name = "feeding_bottle")
	public Boolean getFeedingBottle() {
		return this.feedingBottle;
	}

	public void setFeedingBottle(Boolean feedingBottle) {
		this.feedingBottle = feedingBottle;
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

	@Column(name = "more_feeding_less_six")
	public Boolean getMoreFeedingLessSix() {
		return this.moreFeedingLessSix;
	}

	public void setMoreFeedingLessSix(Boolean moreFeedingLessSix) {
		this.moreFeedingLessSix = moreFeedingLessSix;
	}

	@Column(name = "name_update")
	public Boolean getNameUpdate() {
		return this.nameUpdate;
	}

	public void setNameUpdate(Boolean nameUpdate) {
		this.nameUpdate = nameUpdate;
	}

	@Column(name = "not_breasfeeding")
	public String getNotBreasfeeding() {
		return this.notBreasfeeding;
	}

	public void setNotBreasfeeding(String notBreasfeeding) {
		this.notBreasfeeding = notBreasfeeding;
	}

	@Column(name = "on_demand")
	public Boolean getOnDemand() {
		return this.onDemand;
	}

	public void setOnDemand(Boolean onDemand) {
		this.onDemand = onDemand;
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

	@Column(name = "recent_fever")
	public Boolean getRecentFever() {
		return this.recentFever;
	}

	public void setRecentFever(Boolean recentFever) {
		this.recentFever = recentFever;
	}

	@Column(name = "tea_other")
	public Boolean getTeaOther() {
		return this.teaOther;
	}

	public void setTeaOther(Boolean teaOther) {
		this.teaOther = teaOther;
	}

	@Column(name = "treated_less_six")
	public Boolean getTreatedLessSix() {
		return this.treatedLessSix;
	}

	public void setTreatedLessSix(Boolean treatedLessSix) {
		this.treatedLessSix = treatedLessSix;
	}

	@Column(name = "water_or_milk")
	public Boolean getWaterOrMilk() {
		return this.waterOrMilk;
	}

	public void setWaterOrMilk(Boolean waterOrMilk) {
		this.waterOrMilk = waterOrMilk;
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
