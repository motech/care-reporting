package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "new_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class NewForm extends Form {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Short ageCalc;
	private String caseName;
	private String caseType;
	private Date dateLastVisit;
	private Date dateNextReg;
	private Integer familyNumber;
	private Integer hhNumber;
	private String husbandName;
	private String lastVisitType;
	private String motherAlive;
	private Date motherDob;
	private String motherName;
	private String caste;
	private Date dob;
	private String dobKnown;
	private String fullName;
    private String manualGroup;
    private Date creationTime = new Date();

    public NewForm() {
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

	@Column(name = "manual_group")
	public String getManualGroup() {
		return this.manualGroup;
	}

	public void setManualGroup(String manualGroup) {
		this.manualGroup = manualGroup;
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

	@Column(name = "age_calc")
	public Short getAgeCalc() {
		return this.ageCalc;
	}

	public void setAgeCalc(Short ageCalc) {
		this.ageCalc = ageCalc;
	}

	@Column(name = "case_name")
	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	@Column(name = "case_type")
	public String getCaseType() {
		return this.caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
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
	@Column(name = "date_next_reg")
	public Date getDateNextReg() {
		return this.dateNextReg;
	}

	public void setDateNextReg(Date dateNextReg) {
		this.dateNextReg = dateNextReg;
	}

	@Column(name = "family_number")
	public Integer getFamilyNumber() {
		return this.familyNumber;
	}

	public void setFamilyNumber(Integer familyNumber) {
		this.familyNumber = familyNumber;
	}

	@Column(name = "hh_number")
	public Integer getHhNumber() {
		return this.hhNumber;
	}

	public void setHhNumber(Integer hhNumber) {
		this.hhNumber = hhNumber;
	}

	@Column(name = "husband_name")
	public String getHusbandName() {
		return this.husbandName;
	}

	public void setHusbandName(String husbandName) {
		this.husbandName = husbandName;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "mother_dob")
	public Date getMotherDob() {
		return this.motherDob;
	}

	public void setMotherDob(Date motherDob) {
		this.motherDob = motherDob;
	}

	@Column(name = "mother_name")
	public String getMotherName() {
		return this.motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	@Column(name = "caste")
	public String getCaste() {
		return this.caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "dob_known")
	public String getDobKnown() {
		return this.dobKnown;
	}

	public void setDobKnown(String dobKnown) {
		this.dobKnown = dobKnown;
	}

	@Column(name = "full_name")
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
