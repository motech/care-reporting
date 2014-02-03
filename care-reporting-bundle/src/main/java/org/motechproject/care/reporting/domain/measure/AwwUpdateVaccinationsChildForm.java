package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;


@Entity
@Table(name = "aww_update_vaccinations_child_form", uniqueConstraints = @UniqueConstraint(columnNames = {"instance_id", "case_id"}))
public class AwwUpdateVaccinationsChildForm extends Form {

    private int id;
    private ChildCase childCase;
    private Flw flw;
    private Date dateModified;
    private Date timeStart;
    private Date timeEnd;
    private Date creationTime = new Date();
    private String detailsAvailable;
    private String detailsNeeded;
    private String addVaccinations;
    private Date bcgDate;
    private Date opv0Date;
    private Date opv1Date;
    private Date opv2Date;
    private Date opv3Date;
    private Date opvBoosterDate;
    private Date dpt1Date;
    private Date dpt2Date;
    private Date dpt3Date;
    private Date dptBoosterDate;
    private Date hepB0Date;
    private Date hepB1Date;
    private Date hepB2Date;
    private Date hepB3Date;
    private Date measlesDate;
    private Date dateMeaslesBooster;
    private Date vitA1Date;
    private Date vitA2Date;
    private Date vitA3Date;
    private Date dateJe;
    private String upToDate;
    private String babyBcg;
    private String babyOpv0;
    private String babyOpv1;
    private String babyOpv2;
    private String babyOpv3;
    private String babyOpvBooster;
    private String babyDpt1;
    private String babyDpt2;
    private String babyDpt3;
    private String babyDptBooster;
    private String babyHepB0;
    private String babyHepB1;
    private String babyHepB2;
    private String babyHepB3;
    private String babyMeasles;
    private String babyMeaslesBooster;
    private String babyVita1;
    private String babyVita2;
    private String babyVita3;
    private String babyJe;
    private String upToDateSixWeeks;
    private String upToDateTenWeeks;
    private String upToDate14Weeks;
    private String upToDateOneYear;
    private String upToDateTwoYear;
    private String success;
    private String immunsUpToDate;
    private String childName;
    private Date dob;

    public AwwUpdateVaccinationsChildForm() {

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
    @JoinColumn(name = "case_id")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT})
    public ChildCase getChildCase() {
        return this.childCase;
    }

    public void setChildCase(ChildCase childCase) {
        this.childCase = childCase;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT})
    public Flw getFlw() {
        return this.flw;
    }

    public void setFlw(Flw flw) {
        this.flw = flw;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_modified")
    public Date getDateModified() {
        return this.dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
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

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Column(name = "details_available")
    public String getDetailsAvailable() {
        return detailsAvailable;
    }

    public void setDetailsAvailable(String detailsAvailable) {
        this.detailsAvailable = detailsAvailable;
    }

    @Column(name = "details_needed")
    public String getDetailsNeeded() {
        return detailsNeeded;
    }

    public void setDetailsNeeded(String detailsNeeded) {
        this.detailsNeeded = detailsNeeded;
    }

    @Column(name = "add_vaccinations")
    public String getAddVaccinations() {
        return addVaccinations;
    }

    public void setAddVaccinations(String addVaccinations) {
        this.addVaccinations = addVaccinations;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "bcg_date")
    public Date getBcgDate() {
        return bcgDate;
    }

    public void setBcgDate(Date bcgDate) {
        this.bcgDate = bcgDate;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "opv_0_date")
    public Date getOpv0Date() {
        return opv0Date;
    }

    public void setOpv0Date(Date opv0Date) {
        this.opv0Date = opv0Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "opv_1_date")
    public Date getOpv1Date() {
        return opv1Date;
    }

    public void setOpv1Date(Date opv1Date) {
        this.opv1Date = opv1Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "opv_2_date")
    public Date getOpv2Date() {
        return opv2Date;
    }

    public void setOpv2Date(Date opv2Date) {
        this.opv2Date = opv2Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "opv_3_date")
    public Date getOpv3Date() {
        return opv3Date;
    }

    public void setOpv3Date(Date opv3Date) {
        this.opv3Date = opv3Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "opv_booster_date")
    public Date getOpvBoosterDate() {
        return opvBoosterDate;
    }

    public void setOpvBoosterDate(Date opvBoosterDate) {
        this.opvBoosterDate = opvBoosterDate;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "dpt_1_date")
    public Date getDpt1Date() {
        return dpt1Date;
    }

    public void setDpt1Date(Date dpt1Date) {
        this.dpt1Date = dpt1Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "dpt_2_date")
    public Date getDpt2Date() {
        return dpt2Date;
    }

    public void setDpt2Date(Date dpt2Date) {
        this.dpt2Date = dpt2Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "dpt_3_date")
    public Date getDpt3Date() {
        return dpt3Date;
    }

    public void setDpt3Date(Date dpt3Date) {
        this.dpt3Date = dpt3Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "dpt_booster_date")
    public Date getDptBoosterDate() {
        return dptBoosterDate;
    }

    public void setDptBoosterDate(Date dptBoosterDate) {
        this.dptBoosterDate = dptBoosterDate;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "hep_b_0_date")
    public Date getHepB0Date() {
        return hepB0Date;
    }

    public void setHepB0Date(Date hepB0Date) {
        this.hepB0Date = hepB0Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "hep_b_1_date")
    public Date getHepB1Date() {
        return hepB1Date;
    }

    public void setHepB1Date(Date hepB1Date) {
        this.hepB1Date = hepB1Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "hep_b_2_date")
    public Date getHepB2Date() {
        return hepB2Date;
    }

    public void setHepB2Date(Date hepB2Date) {
        this.hepB2Date = hepB2Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "hep_b_3_date")
    public Date getHepB3Date() {
        return hepB3Date;
    }

    public void setHepB3Date(Date hepB3Date) {
        this.hepB3Date = hepB3Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "measles_date")
    public Date getMeaslesDate() {
        return measlesDate;
    }

    public void setMeaslesDate(Date measlesDate) {
        this.measlesDate = measlesDate;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date_measles_booster")
    public Date getDateMeaslesBooster() {
        return dateMeaslesBooster;
    }

    public void setDateMeaslesBooster(Date dateMeaslesBooster) {
        this.dateMeaslesBooster = dateMeaslesBooster;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "vit_a_1_date")
    public Date getVitA1Date() {
        return vitA1Date;
    }

    public void setVitA1Date(Date vitA1Date) {
        this.vitA1Date = vitA1Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "vit_a_2_date")
    public Date getVitA2Date() {
        return vitA2Date;
    }

    public void setVitA2Date(Date vitA2Date) {
        this.vitA2Date = vitA2Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "vit_a_3_date")
    public Date getVitA3Date() {
        return vitA3Date;
    }

    public void setVitA3Date(Date vitA3Date) {
        this.vitA3Date = vitA3Date;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date_je")
    public Date getDateJe() {
        return dateJe;
    }

    public void setDateJe(Date dateJe) {
        this.dateJe = dateJe;
    }

    @Column(name = "up_to_date")
    public String getUpToDate() {
        return upToDate;
    }

    public void setUpToDate(String upToDate) {
        this.upToDate = upToDate;
    }

    @Column(name = "baby_bcg")
    public String getBabyBcg() {
        return babyBcg;
    }

    public void setBabyBcg(String babyBcg) {
        this.babyBcg = babyBcg;
    }

    @Column(name = "baby_opv_0")
    public String getBabyOpv0() {
        return babyOpv0;
    }

    public void setBabyOpv0(String babyOpv0) {
        this.babyOpv0 = babyOpv0;
    }

    @Column(name = "baby_opv_1")
    public String getBabyOpv1() {
        return babyOpv1;
    }

    public void setBabyOpv1(String babyOpv1) {
        this.babyOpv1 = babyOpv1;
    }

    @Column(name = "baby_opv_2")
    public String getBabyOpv2() {
        return babyOpv2;
    }

    public void setBabyOpv2(String babyOpv2) {
        this.babyOpv2 = babyOpv2;
    }

    @Column(name = "baby_opv_3")
    public String getBabyOpv3() {
        return babyOpv3;
    }

    public void setBabyOpv3(String babyOpv3) {
        this.babyOpv3 = babyOpv3;
    }

    @Column(name = "baby_opv_booster")
    public String getBabyOpvBooster() {
        return babyOpvBooster;
    }

    public void setBabyOpvBooster(String babyOpvBooster) {
        this.babyOpvBooster = babyOpvBooster;
    }

    @Column(name = "baby_dpt_1")
    public String getBabyDpt1() {
        return babyDpt1;
    }

    public void setBabyDpt1(String babyDpt1) {
        this.babyDpt1 = babyDpt1;
    }

    @Column(name = "baby_dpt_2")
    public String getBabyDpt2() {
        return babyDpt2;
    }

    public void setBabyDpt2(String babyDpt2) {
        this.babyDpt2 = babyDpt2;
    }

    @Column(name = "baby_dpt_3")
    public String getBabyDpt3() {
        return babyDpt3;
    }

    public void setBabyDpt3(String babyDpt3) {
        this.babyDpt3 = babyDpt3;
    }

    @Column(name = "baby_dpt_booster")
    public String getBabyDptBooster() {
        return babyDptBooster;
    }

    public void setBabyDptBooster(String babyDptBooster) {
        this.babyDptBooster = babyDptBooster;
    }

    @Column(name = "baby_hep_b_0")
    public String getBabyHepB0() {
        return babyHepB0;
    }

    public void setBabyHepB0(String babyHepB0) {
        this.babyHepB0 = babyHepB0;
    }

    @Column(name = "baby_hep_b_1")
    public String getBabyHepB1() {
        return babyHepB1;
    }

    public void setBabyHepB1(String babyHepB1) {
        this.babyHepB1 = babyHepB1;
    }

    @Column(name = "baby_hep_b_2")
    public String getBabyHepB2() {
        return babyHepB2;
    }

    public void setBabyHepB2(String babyHepB2) {
        this.babyHepB2 = babyHepB2;
    }

    @Column(name = "baby_hep_b_3")
    public String getBabyHepB3() {
        return babyHepB3;
    }

    public void setBabyHepB3(String babyHepB3) {
        this.babyHepB3 = babyHepB3;
    }

    @Column(name = "baby_measles")
    public String getBabyMeasles() {
        return babyMeasles;
    }

    public void setBabyMeasles(String babyMeasles) {
        this.babyMeasles = babyMeasles;
    }

    @Column(name = "baby_measles_booster")
    public String getBabyMeaslesBooster() {
        return babyMeaslesBooster;
    }

    public void setBabyMeaslesBooster(String babyMeaslesBooster) {
        this.babyMeaslesBooster = babyMeaslesBooster;
    }

    @Column(name = "baby_vita_1")
    public String getBabyVita1() {
        return babyVita1;
    }

    public void setBabyVita1(String babyVita1) {
        this.babyVita1 = babyVita1;
    }

    @Column(name = "baby_vita_2")
    public String getBabyVita2() {
        return babyVita2;
    }

    public void setBabyVita2(String babyVita2) {
        this.babyVita2 = babyVita2;
    }

    @Column(name = "baby_vita_3")
    public String getBabyVita3() {
        return babyVita3;
    }

    public void setBabyVita3(String babyVita3) {
        this.babyVita3 = babyVita3;
    }

    @Column(name = "baby_je")
    public String getBabyJe() {
        return babyJe;
    }

    public void setBabyJe(String babyJe) {
        this.babyJe = babyJe;
    }

    @Column(name = "up_to_date_six_weeks")
    public String getUpToDateSixWeeks() {
        return upToDateSixWeeks;
    }

    public void setUpToDateSixWeeks(String upToDateSixWeeks) {
        this.upToDateSixWeeks = upToDateSixWeeks;
    }

    @Column(name = "up_to_date_ten_weeks")
    public String getUpToDateTenWeeks() {
        return upToDateTenWeeks;
    }

    public void setUpToDateTenWeeks(String upToDateTenWeeks) {
        this.upToDateTenWeeks = upToDateTenWeeks;
    }

    @Column(name = "up_to_date_14_weeks")
    public String getUpToDate14Weeks() {
        return upToDate14Weeks;
    }

    public void setUpToDate14Weeks(String upToDate14Weeks) {
        this.upToDate14Weeks = upToDate14Weeks;
    }

    @Column(name = "up_to_date_one_year")
    public String getUpToDateOneYear() {
        return upToDateOneYear;
    }

    public void setUpToDateOneYear(String upToDateOneYear) {
        this.upToDateOneYear = upToDateOneYear;
    }

    @Column(name = "up_to_date_two_year")
    public String getUpToDateTwoYear() {
        return upToDateTwoYear;
    }

    public void setUpToDateTwoYear(String upToDateTwoYear) {
        this.upToDateTwoYear = upToDateTwoYear;
    }

    @Column(name = "success")
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Column(name = "immuns_up_to_date")
    public String getImmunsUpToDate() {
        return immunsUpToDate;
    }

    public void setImmunsUpToDate(String immunsUpToDate) {
        this.immunsUpToDate = immunsUpToDate;
    }

    @Column(name = "child_name")
    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "dob")
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
