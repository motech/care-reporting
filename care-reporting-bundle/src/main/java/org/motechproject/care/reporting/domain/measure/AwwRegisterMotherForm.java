package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
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
@Table(name = "aww_reg_mother_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class AwwRegisterMotherForm extends Form {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private Date timeStart;
	private Date timeEnd;
	private Date dateModified;
	private Date creationTime;
	private Date dob;
	private String fatherName;
	private String motherName;
	private String hhNumber;
	private String wardNumber;
	private String familyNumber;
	private String aadharNumber;
	private Integer mctsId;
	private String mobileNumber;
	private String mobileNumberWhose;
	private String eatsMeat;
	private String invalidOwner;
	private String dobKnown;
	private Date dobEntered;
	private String showAge;
	private Integer ageCalc;
	private Integer ageEstMonths;
	private Integer ageEstYears;
	private String updateMctsId;
	private String updateAadharNumber;
	private Integer fullMctsId;
	private String caste;
	private String resident;
	private String success;
	private String ownerIdCalc;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
	public Flw getFlw() {
		return flw;
	}

	public void setFlw(Flw flw) {
		this.flw = flw;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_id")
	@Cascade({	CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.REPLICATE, CascadeType.LOCK, CascadeType.EVICT })
	public MotherCase getMotherCase() {
		return motherCase;
	}

	public void setMotherCase(MotherCase childCase) {
		this.motherCase = childCase;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_start")
	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_end")
	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modified")
	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_time")
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

    @Column(name = "father_name")
    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

	@Column(name = "mother_name")
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	@Column(name = "hh_number")
	public String getHhNumber() {
		return hhNumber;
	}

	public void setHhNumber(String hhNumber) {
		this.hhNumber = hhNumber;
	}

	@Column(name = "ward_number")
	public String getWardNumber() {
		return wardNumber;
	}

	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

	@Column(name = "family_number")
	public String getFamilyNumber() {
		return familyNumber;
	}

	public void setFamilyNumber(String familyNumber) {
		this.familyNumber = familyNumber;
	}

	@Column(name = "aadhar_number")
	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	@Column(name = "mcts_id")
	public Integer getMctsId() {
		return mctsId;
	}

	public void setMctsId(Integer mctsId) {
		this.mctsId = mctsId;
	}

	@Column(name = "mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "mobile_number_whose")
	public String getMobileNumberWhose() {
		return mobileNumberWhose;
	}

	public void setMobileNumberWhose(String mobileNumberWhose) {
		this.mobileNumberWhose = mobileNumberWhose;
	}

	@Column(name = "eats_meat")
	public String getEatsMeat() {
		return eatsMeat;
	}

	public void setEatsMeat(String eatsMeat) {
		this.eatsMeat = eatsMeat;
	}

	@Column(name = "invalid_owner")
	public String getInvalidOwner() {
		return invalidOwner;
	}

	public void setInvalidOwner(String invalidOwner) {
		this.invalidOwner = invalidOwner;
	}

	@Column(name = "dob_known")
	public String getDobKnown() {
		return dobKnown;
	}

	public void setDobKnown(String dobKnown) {
		this.dobKnown = dobKnown;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "dob_entered")
	public Date getDobEntered() {
		return dobEntered;
	}

	public void setDobEntered(Date dobEntered) {
		this.dobEntered = dobEntered;
	}

	@Column(name = "show_age")
	public String getShowAge() {
		return showAge;
	}

	public void setShowAge(String showAge) {
		this.showAge = showAge;
	}

	@Column(name = "age_calc")
	public Integer getAgeCalc() {
		return ageCalc;
	}

	public void setAgeCalc(Integer ageCalc) {
		this.ageCalc = ageCalc;
	}

	@Column(name = "age_est_months")
	public Integer getAgeEstMonths() {
		return ageEstMonths;
	}

	public void setAgeEstMonths(Integer ageEstMonths) {
		this.ageEstMonths = ageEstMonths;
	}

	@Column(name = "age_est_years")
	public Integer getAgeEstYears() {
		return ageEstYears;
	}

	public void setAgeEstYears(Integer ageEstYears) {
		this.ageEstYears = ageEstYears;
	}

	@Column(name = "update_mcts_id")
	public String getUpdateMctsId() {
		return updateMctsId;
	}

	public void setUpdateMctsId(String updateMctsId) {
		this.updateMctsId = updateMctsId;
	}

	@Column(name = "update_aadhar_number")
	public String getUpdateAadharNumber() {
		return updateAadharNumber;
	}

	public void setUpdateAadharNumber(String updateAadharNumber) {
		this.updateAadharNumber = updateAadharNumber;
	}

	@Column(name = "full_mcts_id")
	public Integer getFullMctsId() {
		return fullMctsId;
	}

	public void setFullMctsId(Integer fullMctsId) {
		this.fullMctsId = fullMctsId;
	}

	@Column(name = "caste")
	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	@Column(name = "resident")
	public String getResident() {
		return resident;
	}

	public void setResident(String resident) {
		this.resident = resident;
	}

	@Column(name = "success")
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	@Column(name = "owner_id_calc")
	public String getOwnerIdCalc() {
		return ownerIdCalc;
	}

	public void setOwnerIdCalc(String ownerIdCalc) {
		this.ownerIdCalc = ownerIdCalc;
	}
}
