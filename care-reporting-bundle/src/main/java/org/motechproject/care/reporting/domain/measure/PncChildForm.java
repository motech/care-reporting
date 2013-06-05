package org.motechproject.care.reporting.domain.measure;

// Generated Jun 4, 2013 4:50:32 PM by Hibernate Tools 3.4.0.CR1

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;

import javax.persistence.*;
import java.util.Date;

/**
 * PncChildForm generated by hbm2java
 */
@Entity
@Table(name = "pnc_child_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class PncChildForm implements java.io.Serializable {

	private int id;
	private Flw flw;
	private ChildCase childCase;
	private String instanceId;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Boolean ableExpressedMilk;
	private Boolean adequateSupport;
	private Boolean appliedToStump;
	private Boolean babyActive;
	private Boolean breastfeedingWell;
	private Boolean childAlive;
	private Boolean childDiedVillage;
	private String childPlaceDeath;
	private String childSiteDeath;
	private Date chldDateDeath;
	private String close;
	private Boolean cordFallen;
	private Boolean correctPosition;
	private Boolean counselCordCare;
	private Boolean counselExclusiveBf;
	private Boolean counselExpressMilk;
	private Boolean counselSkin;
	private Boolean couselBfCorrect;
	private Boolean demonstrateExpressed;
	private Boolean demonstrateSkin;
	private Boolean easyAwake;
	private Boolean feedVigour;
	private Boolean goodLatch;
	private Boolean improvementsBf;
	private Boolean observedBf;
	private Boolean otherMilkToChild;
	private Boolean secondObservation;
	private Boolean skinToSkin;
	private Boolean warmToTouch;
	private String whatApplied;
	private Boolean wrapped;

	public PncChildForm() {
	}

	public PncChildForm(int id) {
		this.id = id;
	}

	public PncChildForm(int id, Flw flw, ChildCase childCase,
			String instanceId, Date timeEnd, Date timeStart, Date dateModified,
			Boolean ableExpressedMilk, Boolean adequateSupport,
			Boolean appliedToStump, Boolean babyActive,
			Boolean breastfeedingWell, Boolean childAlive,
			Boolean childDiedVillage, String childPlaceDeath,
			String childSiteDeath, Date chldDateDeath, String close,
			Boolean cordFallen, Boolean correctPosition,
			Boolean counselCordCare, Boolean counselExclusiveBf,
			Boolean counselExpressMilk, Boolean counselSkin,
			Boolean couselBfCorrect, Boolean demonstrateExpressed,
			Boolean demonstrateSkin, Boolean easyAwake, Boolean feedVigour,
			Boolean goodLatch, Boolean improvementsBf, Boolean observedBf,
			Boolean otherMilkToChild, Boolean secondObservation,
			Boolean skinToSkin, Boolean warmToTouch, String whatApplied,
			Boolean wrapped) {
		this.id = id;
		this.flw = flw;
		this.childCase = childCase;
		this.instanceId = instanceId;
		this.timeEnd = timeEnd;
		this.timeStart = timeStart;
		this.dateModified = dateModified;
		this.ableExpressedMilk = ableExpressedMilk;
		this.adequateSupport = adequateSupport;
		this.appliedToStump = appliedToStump;
		this.babyActive = babyActive;
		this.breastfeedingWell = breastfeedingWell;
		this.childAlive = childAlive;
		this.childDiedVillage = childDiedVillage;
		this.childPlaceDeath = childPlaceDeath;
		this.childSiteDeath = childSiteDeath;
		this.chldDateDeath = chldDateDeath;
		this.close = close;
		this.cordFallen = cordFallen;
		this.correctPosition = correctPosition;
		this.counselCordCare = counselCordCare;
		this.counselExclusiveBf = counselExclusiveBf;
		this.counselExpressMilk = counselExpressMilk;
		this.counselSkin = counselSkin;
		this.couselBfCorrect = couselBfCorrect;
		this.demonstrateExpressed = demonstrateExpressed;
		this.demonstrateSkin = demonstrateSkin;
		this.easyAwake = easyAwake;
		this.feedVigour = feedVigour;
		this.goodLatch = goodLatch;
		this.improvementsBf = improvementsBf;
		this.observedBf = observedBf;
		this.otherMilkToChild = otherMilkToChild;
		this.secondObservation = secondObservation;
		this.skinToSkin = skinToSkin;
		this.warmToTouch = warmToTouch;
		this.whatApplied = whatApplied;
		this.wrapped = wrapped;
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

	@Column(name = "able_expressed_milk")
	public Boolean getAbleExpressedMilk() {
		return this.ableExpressedMilk;
	}

	public void setAbleExpressedMilk(Boolean ableExpressedMilk) {
		this.ableExpressedMilk = ableExpressedMilk;
	}

	@Column(name = "adequate_support")
	public Boolean getAdequateSupport() {
		return this.adequateSupport;
	}

	public void setAdequateSupport(Boolean adequateSupport) {
		this.adequateSupport = adequateSupport;
	}

	@Column(name = "applied_to_stump")
	public Boolean getAppliedToStump() {
		return this.appliedToStump;
	}

	public void setAppliedToStump(Boolean appliedToStump) {
		this.appliedToStump = appliedToStump;
	}

	@Column(name = "baby_active")
	public Boolean getBabyActive() {
		return this.babyActive;
	}

	public void setBabyActive(Boolean babyActive) {
		this.babyActive = babyActive;
	}

	@Column(name = "breastfeeding_well")
	public Boolean getBreastfeedingWell() {
		return this.breastfeedingWell;
	}

	public void setBreastfeedingWell(Boolean breastfeedingWell) {
		this.breastfeedingWell = breastfeedingWell;
	}

	@Column(name = "child_alive")
	public Boolean getChildAlive() {
		return this.childAlive;
	}

	public void setChildAlive(Boolean childAlive) {
		this.childAlive = childAlive;
	}

	@Column(name = "child_died_village")
	public Boolean getChildDiedVillage() {
		return this.childDiedVillage;
	}

	public void setChildDiedVillage(Boolean childDiedVillage) {
		this.childDiedVillage = childDiedVillage;
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
	@Column(name = "chld_date_death", length = 13)
	public Date getChldDateDeath() {
		return this.chldDateDeath;
	}

	public void setChldDateDeath(Date chldDateDeath) {
		this.chldDateDeath = chldDateDeath;
	}

	@Column(name = "close")
	public String getClose() {
		return this.close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	@Column(name = "cord_fallen")
	public Boolean getCordFallen() {
		return this.cordFallen;
	}

	public void setCordFallen(Boolean cordFallen) {
		this.cordFallen = cordFallen;
	}

	@Column(name = "correct_position")
	public Boolean getCorrectPosition() {
		return this.correctPosition;
	}

	public void setCorrectPosition(Boolean correctPosition) {
		this.correctPosition = correctPosition;
	}

	@Column(name = "counsel_cord_care")
	public Boolean getCounselCordCare() {
		return this.counselCordCare;
	}

	public void setCounselCordCare(Boolean counselCordCare) {
		this.counselCordCare = counselCordCare;
	}

	@Column(name = "counsel_exclusive_bf")
	public Boolean getCounselExclusiveBf() {
		return this.counselExclusiveBf;
	}

	public void setCounselExclusiveBf(Boolean counselExclusiveBf) {
		this.counselExclusiveBf = counselExclusiveBf;
	}

	@Column(name = "counsel_express_milk")
	public Boolean getCounselExpressMilk() {
		return this.counselExpressMilk;
	}

	public void setCounselExpressMilk(Boolean counselExpressMilk) {
		this.counselExpressMilk = counselExpressMilk;
	}

	@Column(name = "counsel_skin")
	public Boolean getCounselSkin() {
		return this.counselSkin;
	}

	public void setCounselSkin(Boolean counselSkin) {
		this.counselSkin = counselSkin;
	}

	@Column(name = "cousel_bf_correct")
	public Boolean getCouselBfCorrect() {
		return this.couselBfCorrect;
	}

	public void setCouselBfCorrect(Boolean couselBfCorrect) {
		this.couselBfCorrect = couselBfCorrect;
	}

	@Column(name = "demonstrate_expressed")
	public Boolean getDemonstrateExpressed() {
		return this.demonstrateExpressed;
	}

	public void setDemonstrateExpressed(Boolean demonstrateExpressed) {
		this.demonstrateExpressed = demonstrateExpressed;
	}

	@Column(name = "demonstrate_skin")
	public Boolean getDemonstrateSkin() {
		return this.demonstrateSkin;
	}

	public void setDemonstrateSkin(Boolean demonstrateSkin) {
		this.demonstrateSkin = demonstrateSkin;
	}

	@Column(name = "easy_awake")
	public Boolean getEasyAwake() {
		return this.easyAwake;
	}

	public void setEasyAwake(Boolean easyAwake) {
		this.easyAwake = easyAwake;
	}

	@Column(name = "feed_vigour")
	public Boolean getFeedVigour() {
		return this.feedVigour;
	}

	public void setFeedVigour(Boolean feedVigour) {
		this.feedVigour = feedVigour;
	}

	@Column(name = "good_latch")
	public Boolean getGoodLatch() {
		return this.goodLatch;
	}

	public void setGoodLatch(Boolean goodLatch) {
		this.goodLatch = goodLatch;
	}

	@Column(name = "improvements_bf")
	public Boolean getImprovementsBf() {
		return this.improvementsBf;
	}

	public void setImprovementsBf(Boolean improvementsBf) {
		this.improvementsBf = improvementsBf;
	}

	@Column(name = "observed_bf")
	public Boolean getObservedBf() {
		return this.observedBf;
	}

	public void setObservedBf(Boolean observedBf) {
		this.observedBf = observedBf;
	}

	@Column(name = "other_milk_to_child")
	public Boolean getOtherMilkToChild() {
		return this.otherMilkToChild;
	}

	public void setOtherMilkToChild(Boolean otherMilkToChild) {
		this.otherMilkToChild = otherMilkToChild;
	}

	@Column(name = "second_observation")
	public Boolean getSecondObservation() {
		return this.secondObservation;
	}

	public void setSecondObservation(Boolean secondObservation) {
		this.secondObservation = secondObservation;
	}

	@Column(name = "skin_to_skin")
	public Boolean getSkinToSkin() {
		return this.skinToSkin;
	}

	public void setSkinToSkin(Boolean skinToSkin) {
		this.skinToSkin = skinToSkin;
	}

	@Column(name = "warm_to_touch")
	public Boolean getWarmToTouch() {
		return this.warmToTouch;
	}

	public void setWarmToTouch(Boolean warmToTouch) {
		this.warmToTouch = warmToTouch;
	}

	@Column(name = "what_applied")
	public String getWhatApplied() {
		return this.whatApplied;
	}

	public void setWhatApplied(String whatApplied) {
		this.whatApplied = whatApplied;
	}

	@Column(name = "wrapped")
	public Boolean getWrapped() {
		return this.wrapped;
	}

	public void setWrapped(Boolean wrapped) {
		this.wrapped = wrapped;
	}

}
