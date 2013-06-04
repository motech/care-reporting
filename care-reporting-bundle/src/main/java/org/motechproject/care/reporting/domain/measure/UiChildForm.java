package org.motechproject.care.reporting.domain.measure;

// Generated Jun 4, 2013 4:50:32 PM by Hibernate Tools 3.4.0.CR1

import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;

import javax.persistence.*;
import java.util.Date;

/**
 * UiChildForm generated by hbm2java
 */
@Entity
@Table(name = "ui_child_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class UiChildForm implements java.io.Serializable {

	private int id;
	private Flw flw;
	private ChildCase childCase;
	private String instanceId;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Boolean addVaccinations;
	private Boolean babyBcg;
	private Boolean babyDpt1;
	private Boolean babyDpt2;
	private Boolean babyDpt3;
	private Boolean babyHepB0;
	private Boolean babyHepB1;
	private Boolean babyHepB2;
	private Boolean babyHepB3;
	private Boolean babyMeasles;
	private Boolean babyOpv0;
	private Boolean babyOpv1;
	private Boolean babyOpv2;
	private Boolean babyOpv3;
	private Boolean babyVita1;
	private Date bcgDate;
	private Date dpt1Date;
	private Date dpt2Date;
	private Date dpt3Date;
	private Date dptBoosterDate;
	private Date hepB0Date;
	private Date hepB1Date;
	private Date hepB2Date;
	private Date hepB3Date;
	private Date measlesDate;
	private Date opv0Date;
	private Date opv1Date;
	private Date opv2Date;
	private Date opv3Date;
	private Date opvBoosterDate;
	private Date vitA1Date;
	private Boolean babyDptBooster;
	private Boolean babyJe;
	private Boolean babyMeaslesBooster;
	private Boolean babyOpvBooster;
	private Boolean babyVita2;
	private Boolean babyVita3;
	private Date dateJe;
	private Date dateMeaslesBooster;
	private Date vitA2Date;
	private Date vitA3Date;

	public UiChildForm() {
	}

	public UiChildForm(int id) {
		this.id = id;
	}

	public UiChildForm(int id, Flw flw, ChildCase childCase, String instanceId,
			Date timeEnd, Date timeStart, Date dateModified,
			Boolean addVaccinations, Boolean babyBcg, Boolean babyDpt1,
			Boolean babyDpt2, Boolean babyDpt3, Boolean babyHepB0,
			Boolean babyHepB1, Boolean babyHepB2, Boolean babyHepB3,
			Boolean babyMeasles, Boolean babyOpv0, Boolean babyOpv1,
			Boolean babyOpv2, Boolean babyOpv3, Boolean babyVita1,
			Date bcgDate, Date dpt1Date, Date dpt2Date, Date dpt3Date,
			Date dptBoosterDate, Date hepB0Date, Date hepB1Date,
			Date hepB2Date, Date hepB3Date, Date measlesDate, Date opv0Date,
			Date opv1Date, Date opv2Date, Date opv3Date, Date opvBoosterDate,
			Date vitA1Date, Boolean babyDptBooster, Boolean babyJe,
			Boolean babyMeaslesBooster, Boolean babyOpvBooster,
			Boolean babyVita2, Boolean babyVita3, Date dateJe,
			Date dateMeaslesBooster, Date vitA2Date, Date vitA3Date) {
		this.id = id;
		this.flw = flw;
		this.childCase = childCase;
		this.instanceId = instanceId;
		this.timeEnd = timeEnd;
		this.timeStart = timeStart;
		this.dateModified = dateModified;
		this.addVaccinations = addVaccinations;
		this.babyBcg = babyBcg;
		this.babyDpt1 = babyDpt1;
		this.babyDpt2 = babyDpt2;
		this.babyDpt3 = babyDpt3;
		this.babyHepB0 = babyHepB0;
		this.babyHepB1 = babyHepB1;
		this.babyHepB2 = babyHepB2;
		this.babyHepB3 = babyHepB3;
		this.babyMeasles = babyMeasles;
		this.babyOpv0 = babyOpv0;
		this.babyOpv1 = babyOpv1;
		this.babyOpv2 = babyOpv2;
		this.babyOpv3 = babyOpv3;
		this.babyVita1 = babyVita1;
		this.bcgDate = bcgDate;
		this.dpt1Date = dpt1Date;
		this.dpt2Date = dpt2Date;
		this.dpt3Date = dpt3Date;
		this.dptBoosterDate = dptBoosterDate;
		this.hepB0Date = hepB0Date;
		this.hepB1Date = hepB1Date;
		this.hepB2Date = hepB2Date;
		this.hepB3Date = hepB3Date;
		this.measlesDate = measlesDate;
		this.opv0Date = opv0Date;
		this.opv1Date = opv1Date;
		this.opv2Date = opv2Date;
		this.opv3Date = opv3Date;
		this.opvBoosterDate = opvBoosterDate;
		this.vitA1Date = vitA1Date;
		this.babyDptBooster = babyDptBooster;
		this.babyJe = babyJe;
		this.babyMeaslesBooster = babyMeaslesBooster;
		this.babyOpvBooster = babyOpvBooster;
		this.babyVita2 = babyVita2;
		this.babyVita3 = babyVita3;
		this.dateJe = dateJe;
		this.dateMeaslesBooster = dateMeaslesBooster;
		this.vitA2Date = vitA2Date;
		this.vitA3Date = vitA3Date;
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
	@JoinColumn(name = "case_id")
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

	@Column(name = "add_vaccinations")
	public Boolean getAddVaccinations() {
		return this.addVaccinations;
	}

	public void setAddVaccinations(Boolean addVaccinations) {
		this.addVaccinations = addVaccinations;
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

	@Column(name = "baby_measles")
	public Boolean getBabyMeasles() {
		return this.babyMeasles;
	}

	public void setBabyMeasles(Boolean babyMeasles) {
		this.babyMeasles = babyMeasles;
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

	@Column(name = "baby_vita1")
	public Boolean getBabyVita1() {
		return this.babyVita1;
	}

	public void setBabyVita1(Boolean babyVita1) {
		this.babyVita1 = babyVita1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "bcg_date", length = 13)
	public Date getBcgDate() {
		return this.bcgDate;
	}

	public void setBcgDate(Date bcgDate) {
		this.bcgDate = bcgDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dpt_1_date", length = 13)
	public Date getDpt1Date() {
		return this.dpt1Date;
	}

	public void setDpt1Date(Date dpt1Date) {
		this.dpt1Date = dpt1Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dpt_2_date", length = 13)
	public Date getDpt2Date() {
		return this.dpt2Date;
	}

	public void setDpt2Date(Date dpt2Date) {
		this.dpt2Date = dpt2Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dpt_3_date", length = 13)
	public Date getDpt3Date() {
		return this.dpt3Date;
	}

	public void setDpt3Date(Date dpt3Date) {
		this.dpt3Date = dpt3Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dpt_booster_date", length = 13)
	public Date getDptBoosterDate() {
		return this.dptBoosterDate;
	}

	public void setDptBoosterDate(Date dptBoosterDate) {
		this.dptBoosterDate = dptBoosterDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "hep_b_0_date", length = 13)
	public Date getHepB0Date() {
		return this.hepB0Date;
	}

	public void setHepB0Date(Date hepB0Date) {
		this.hepB0Date = hepB0Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "hep_b_1_date", length = 13)
	public Date getHepB1Date() {
		return this.hepB1Date;
	}

	public void setHepB1Date(Date hepB1Date) {
		this.hepB1Date = hepB1Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "hep_b_2_date", length = 13)
	public Date getHepB2Date() {
		return this.hepB2Date;
	}

	public void setHepB2Date(Date hepB2Date) {
		this.hepB2Date = hepB2Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "hep_b_3_date", length = 13)
	public Date getHepB3Date() {
		return this.hepB3Date;
	}

	public void setHepB3Date(Date hepB3Date) {
		this.hepB3Date = hepB3Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "measles_date", length = 13)
	public Date getMeaslesDate() {
		return this.measlesDate;
	}

	public void setMeaslesDate(Date measlesDate) {
		this.measlesDate = measlesDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opv_0_date", length = 13)
	public Date getOpv0Date() {
		return this.opv0Date;
	}

	public void setOpv0Date(Date opv0Date) {
		this.opv0Date = opv0Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opv_1_date", length = 13)
	public Date getOpv1Date() {
		return this.opv1Date;
	}

	public void setOpv1Date(Date opv1Date) {
		this.opv1Date = opv1Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opv_2_date", length = 13)
	public Date getOpv2Date() {
		return this.opv2Date;
	}

	public void setOpv2Date(Date opv2Date) {
		this.opv2Date = opv2Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opv_3_date", length = 13)
	public Date getOpv3Date() {
		return this.opv3Date;
	}

	public void setOpv3Date(Date opv3Date) {
		this.opv3Date = opv3Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opv_booster_date", length = 13)
	public Date getOpvBoosterDate() {
		return this.opvBoosterDate;
	}

	public void setOpvBoosterDate(Date opvBoosterDate) {
		this.opvBoosterDate = opvBoosterDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vit_a_1_date", length = 13)
	public Date getVitA1Date() {
		return this.vitA1Date;
	}

	public void setVitA1Date(Date vitA1Date) {
		this.vitA1Date = vitA1Date;
	}

	@Column(name = "baby_dpt_booster")
	public Boolean getBabyDptBooster() {
		return this.babyDptBooster;
	}

	public void setBabyDptBooster(Boolean babyDptBooster) {
		this.babyDptBooster = babyDptBooster;
	}

	@Column(name = "baby_je")
	public Boolean getBabyJe() {
		return this.babyJe;
	}

	public void setBabyJe(Boolean babyJe) {
		this.babyJe = babyJe;
	}

	@Column(name = "baby_measles_booster")
	public Boolean getBabyMeaslesBooster() {
		return this.babyMeaslesBooster;
	}

	public void setBabyMeaslesBooster(Boolean babyMeaslesBooster) {
		this.babyMeaslesBooster = babyMeaslesBooster;
	}

	@Column(name = "baby_opv_booster")
	public Boolean getBabyOpvBooster() {
		return this.babyOpvBooster;
	}

	public void setBabyOpvBooster(Boolean babyOpvBooster) {
		this.babyOpvBooster = babyOpvBooster;
	}

	@Column(name = "baby_vita2")
	public Boolean getBabyVita2() {
		return this.babyVita2;
	}

	public void setBabyVita2(Boolean babyVita2) {
		this.babyVita2 = babyVita2;
	}

	@Column(name = "baby_vita3")
	public Boolean getBabyVita3() {
		return this.babyVita3;
	}

	public void setBabyVita3(Boolean babyVita3) {
		this.babyVita3 = babyVita3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_je", length = 13)
	public Date getDateJe() {
		return this.dateJe;
	}

	public void setDateJe(Date dateJe) {
		this.dateJe = dateJe;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_measles_booster", length = 13)
	public Date getDateMeaslesBooster() {
		return this.dateMeaslesBooster;
	}

	public void setDateMeaslesBooster(Date dateMeaslesBooster) {
		this.dateMeaslesBooster = dateMeaslesBooster;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vit_a_2_date", length = 13)
	public Date getVitA2Date() {
		return this.vitA2Date;
	}

	public void setVitA2Date(Date vitA2Date) {
		this.vitA2Date = vitA2Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vit_a_3_date", length = 13)
	public Date getVitA3Date() {
		return this.vitA3Date;
	}

	public void setVitA3Date(Date vitA3Date) {
		this.vitA3Date = vitA3Date;
	}

}
