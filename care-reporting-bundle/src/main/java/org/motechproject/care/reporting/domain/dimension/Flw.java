package org.motechproject.care.reporting.domain.dimension;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.motechproject.care.reporting.domain.SelfUpdatable;
import org.motechproject.care.reporting.domain.annotations.ExternalPrimaryKey;

import javax.persistence.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "flw", uniqueConstraints = @UniqueConstraint(columnNames = "flw_id"))
public class Flw extends SelfUpdatable<Flw> implements java.io.Serializable {

	private int id;
    @ExternalPrimaryKey
    private String flwId;
	private String defaultPhoneNumber;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber1;
	private String phoneNumber2;
	private String assetId;
	private String awcCode;
    private String role;
	private String subcentre;
	private String userType;
	private String username;
	private String population;
	private String education;
	private String state;
	private String district;
	private String block;
	private String panchayat;
	private String village;
	private String ward;
	private String caste;
	private String ictcordinator;
	private String remarks;
    private Date dob;
    private Date creationTime;
    private Date lastModifiedTime;
    private Set<FlwGroup> flwGroups;
    private LocationDimension locationDimension;
    private String locationCode;

    @Column(name = "location_code")
	public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

	public Flw() {
        Date date = new Date();
        creationTime = date;
        lastModifiedTime = date;
        flwGroups = new HashSet<>();
    }

    public Flw(String flwId,
               String defaultPhoneNumber, String email, String firstName,
               String lastName, String phoneNumber1, String phoneNumber2, String assetId,
               String awcCode, String role, String subcentre,
               String userType, String username, String population,
               String education, String state, String district, String block, String panchayat, String village,
               String ward, String caste, String ictcordinator, String remarks, Date dob, Date creationTime, Date lastModifiedTime, LocationDimension locationDimension, String locationCode) {
        this.flwId = flwId;
        this.defaultPhoneNumber = defaultPhoneNumber;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.assetId = assetId;
        this.awcCode = awcCode;
        this.role = role;
        this.subcentre = subcentre;
        this.userType = userType;
        this.username = username;
        this.population = population;
        this.education = education;
        this.state = state;
        this.district = district;
        this.block = block;
        this.panchayat = panchayat;
        this.village = village;
        this.ward = ward;
        this.caste = caste;
        this.ictcordinator = ictcordinator;
        this.remarks = remarks;
        this.dob = dob;
        this.creationTime = creationTime;
        this.lastModifiedTime = lastModifiedTime;
        this.locationDimension = locationDimension;
        this.locationCode = locationCode;
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

    @Column(name = "flw_id", unique = true)
	public String getFlwId() {
		return this.flwId;
	}

	public void setFlwId(String flwId) {
		this.flwId = flwId;
	}

	@Column(name = "default_phone_number")
	public String getDefaultPhoneNumber() {
		return this.defaultPhoneNumber;
	}

	public void setDefaultPhoneNumber(String defaultPhoneNumber) {
		this.defaultPhoneNumber = defaultPhoneNumber;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "phone_number_1")
	public String getPhoneNumber1() {
		return this.phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	@Column(name = "phone_number_2")
	public String getPhoneNumber2() {
		return this.phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	@Column(name = "asset_id")
	public String getAssetId() {
		return this.assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	@Column(name = "awc_code")
	public String getAwcCode() {
		return this.awcCode;
	}

	public void setAwcCode(String awcCode) {
		this.awcCode = awcCode;
	}

	@Column(name = "role")
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "subcentre")
	public String getSubcentre() {
		return this.subcentre;
	}

	public void setSubcentre(String subcentre) {
		this.subcentre = subcentre;
	}

	@Column(name = "user_type")
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "username")
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    @ManyToMany
    @Cascade(value = {CascadeType.SAVE_UPDATE})
    @JoinTable(name="flw_group_map",
            joinColumns={@JoinColumn(name="flw_id")},
            inverseJoinColumns={@JoinColumn(name="group_id")})
    public Set<FlwGroup> getFlwGroups() {
        return flwGroups;
    }

    public void setFlwGroups(Set<FlwGroup> flwGroups) {
        this.flwGroups = flwGroups;
    }

    @Column(name = "population")
	public String getPopulation() {
		return this.population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	@Column(name = "education")
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}


    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "district")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(name = "block")
    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    @Column(name = "panchayat")
    public String getPanchayat() {
        return panchayat;
    }

    public void setPanchayat(String panchayat) {
        this.panchayat = panchayat;
    }

    @Column(name = "village")
    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    @Column
    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    @Column
    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    @Column
    public String getIctcordinator() {
        return ictcordinator;
    }

    public void setIctcordinator(String ictcordinator) {
        this.ictcordinator = ictcordinator;
    }

    @Column
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_time")
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    public void updateToLatest(Flw object) {
        validateIfUpdatable(this.flwId, object.flwId);

        updateFields(object, Arrays.asList("id", "flwId", "creationTime"));
    }

    @Override
    protected void updateLastModifiedTime() {
        this.lastModifiedTime = new Date();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    public LocationDimension getLocationDimension() {
        return this.locationDimension;
    }

    public void setLocationDimension(LocationDimension locationDimension) {
        this.locationDimension = locationDimension;
    }

}
