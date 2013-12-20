package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "pnc_child_form", uniqueConstraints = @UniqueConstraint(columnNames = {"instance_id","case_id"}))
public class PncChildForm extends Form {

	private int id;
	private Flw flw;
	private ChildCase childCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private String ableExpressedMilk;
	private String adequateSupport;
	private String appliedToStump;
	private String babyActive;
	private String breastfeedingWell;
	private String childAlive;
	private String childDiedVillage;
	private String childPlaceDeath;
	private String childSiteDeath;
	private Date chldDateDeath;
	private String cordFallen;
	private String correctPosition;
	private String counselCordCare;
	private String counselExclusiveBf;
	private String counselExpressMilk;
	private String counselSkin;
	private String couselBfCorrect;
	private String demonstrateExpressed;
	private String demonstrateSkin;
	private String easyAwake;
	private String feedVigour;
	private String goodLatch;
	private String improvementsBf;
	private String observedBf;
	private String otherMilkToChild;
	private String secondObservation;
	private String skinToSkin;
	private String warmToTouch;
	private String whatApplied;
	private String wrapped;
    private Boolean close;
    private Date creationTime = new Date();

    public PncChildForm() {
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

	@Column(name = "able_expressed_milk")
	public String getAbleExpressedMilk() {
		return this.ableExpressedMilk;
	}

	public void setAbleExpressedMilk(String ableExpressedMilk) {
		this.ableExpressedMilk = ableExpressedMilk;
	}

	@Column(name = "adequate_support")
	public String getAdequateSupport() {
		return this.adequateSupport;
	}

	public void setAdequateSupport(String adequateSupport) {
		this.adequateSupport = adequateSupport;
	}

	@Column(name = "applied_to_stump")
	public String getAppliedToStump() {
		return this.appliedToStump;
	}

	public void setAppliedToStump(String appliedToStump) {
		this.appliedToStump = appliedToStump;
	}

	@Column(name = "baby_active")
	public String getBabyActive() {
		return this.babyActive;
	}

	public void setBabyActive(String babyActive) {
		this.babyActive = babyActive;
	}

	@Column(name = "breastfeeding_well")
	public String getBreastfeedingWell() {
		return this.breastfeedingWell;
	}

	public void setBreastfeedingWell(String breastfeedingWell) {
		this.breastfeedingWell = breastfeedingWell;
	}

	@Column(name = "child_alive")
	public String getChildAlive() {
		return this.childAlive;
	}

	public void setChildAlive(String childAlive) {
		this.childAlive = childAlive;
	}

	@Column(name = "child_died_village")
	public String getChildDiedVillage() {
		return this.childDiedVillage;
	}

	public void setChildDiedVillage(String childDiedVillage) {
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
	@Column(name = "chld_date_death")
	public Date getChldDateDeath() {
		return this.chldDateDeath;
	}

	public void setChldDateDeath(Date chldDateDeath) {
		this.chldDateDeath = chldDateDeath;
	}

	@Column(name = "cord_fallen")
	public String getCordFallen() {
		return this.cordFallen;
	}

	public void setCordFallen(String cordFallen) {
		this.cordFallen = cordFallen;
	}

	@Column(name = "correct_position")
	public String getCorrectPosition() {
		return this.correctPosition;
	}

	public void setCorrectPosition(String correctPosition) {
		this.correctPosition = correctPosition;
	}

	@Column(name = "counsel_cord_care")
	public String getCounselCordCare() {
		return this.counselCordCare;
	}

	public void setCounselCordCare(String counselCordCare) {
		this.counselCordCare = counselCordCare;
	}

	@Column(name = "counsel_exclusive_bf")
	public String getCounselExclusiveBf() {
		return this.counselExclusiveBf;
	}

	public void setCounselExclusiveBf(String counselExclusiveBf) {
		this.counselExclusiveBf = counselExclusiveBf;
	}

	@Column(name = "counsel_express_milk")
	public String getCounselExpressMilk() {
		return this.counselExpressMilk;
	}

	public void setCounselExpressMilk(String counselExpressMilk) {
		this.counselExpressMilk = counselExpressMilk;
	}

	@Column(name = "counsel_skin")
	public String getCounselSkin() {
		return this.counselSkin;
	}

	public void setCounselSkin(String counselSkin) {
		this.counselSkin = counselSkin;
	}

	@Column(name = "cousel_bf_correct")
	public String getCouselBfCorrect() {
		return this.couselBfCorrect;
	}

	public void setCouselBfCorrect(String couselBfCorrect) {
		this.couselBfCorrect = couselBfCorrect;
	}

	@Column(name = "demonstrate_expressed")
	public String getDemonstrateExpressed() {
		return this.demonstrateExpressed;
	}

	public void setDemonstrateExpressed(String demonstrateExpressed) {
		this.demonstrateExpressed = demonstrateExpressed;
	}

	@Column(name = "demonstrate_skin")
	public String getDemonstrateSkin() {
		return this.demonstrateSkin;
	}

	public void setDemonstrateSkin(String demonstrateSkin) {
		this.demonstrateSkin = demonstrateSkin;
	}

	@Column(name = "easy_awake")
	public String getEasyAwake() {
		return this.easyAwake;
	}

	public void setEasyAwake(String easyAwake) {
		this.easyAwake = easyAwake;
	}

	@Column(name = "feed_vigour")
	public String getFeedVigour() {
		return this.feedVigour;
	}

	public void setFeedVigour(String feedVigour) {
		this.feedVigour = feedVigour;
	}

	@Column(name = "good_latch")
	public String getGoodLatch() {
		return this.goodLatch;
	}

	public void setGoodLatch(String goodLatch) {
		this.goodLatch = goodLatch;
	}

	@Column(name = "improvements_bf")
	public String getImprovementsBf() {
		return this.improvementsBf;
	}

	public void setImprovementsBf(String improvementsBf) {
		this.improvementsBf = improvementsBf;
	}

	@Column(name = "observed_bf")
	public String getObservedBf() {
		return this.observedBf;
	}

	public void setObservedBf(String observedBf) {
		this.observedBf = observedBf;
	}

	@Column(name = "other_milk_to_child")
	public String getOtherMilkToChild() {
		return this.otherMilkToChild;
	}

	public void setOtherMilkToChild(String otherMilkToChild) {
		this.otherMilkToChild = otherMilkToChild;
	}

	@Column(name = "second_observation")
	public String getSecondObservation() {
		return this.secondObservation;
	}

	public void setSecondObservation(String secondObservation) {
		this.secondObservation = secondObservation;
	}

	@Column(name = "skin_to_skin")
	public String getSkinToSkin() {
		return this.skinToSkin;
	}

	public void setSkinToSkin(String skinToSkin) {
		this.skinToSkin = skinToSkin;
	}

	@Column(name = "warm_to_touch")
	public String getWarmToTouch() {
		return this.warmToTouch;
	}

	public void setWarmToTouch(String warmToTouch) {
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
	public String getWrapped() {
		return this.wrapped;
	}

	public void setWrapped(String wrapped) {
		this.wrapped = wrapped;
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
        return close;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return FormToString.toString(this);
    }
}
