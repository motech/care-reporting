package org.motechproject.care.reporting.domain.dimension;

// Generated Jun 4, 2013 10:01:13 AM by Hibernate Tools 3.4.0.CR1

import org.motechproject.care.reporting.domain.SelfUpdatable;
import org.motechproject.care.reporting.domain.annotations.ExternalPrimaryKey;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FlwGroup generated by hbm2java
 */
@Entity
@Table(name = "flw_group", uniqueConstraints = @UniqueConstraint(columnNames = "group_id"))
public class FlwGroup implements java.io.Serializable, SelfUpdatable<FlwGroup> {

	private int id;
    @ExternalPrimaryKey
	private String groupId;
	private Boolean caseSharing;
	private String domain;
	private String awcCode;
	private String name;
	private Boolean reporting;
    private Date creationTime = new Date();
    private Date lastModifiedTime;
    private Set<Flw> flws = new HashSet<>(0);
	private Set<ChildCase> childCases = new HashSet<ChildCase>(0);
	private Set<MotherCase> motherCases = new HashSet<MotherCase>(0);

	public FlwGroup() {
	}

	public FlwGroup(int id) {
		this.id = id;
	}

	public FlwGroup(int id, String groupId, Boolean caseSharing, String domain,
                    String awcCode, String name, Boolean reporting,
                    Date creationTime, Date lastModifiedTime, Set<Flw> flws, Set<ChildCase> childCases,
                    Set<MotherCase> motherCases) {
		this.id = id;
		this.groupId = groupId;
		this.caseSharing = caseSharing;
		this.domain = domain;
		this.awcCode = awcCode;
		this.name = name;
		this.reporting = reporting;
        this.creationTime = creationTime;
        this.lastModifiedTime = lastModifiedTime;
        this.flws = flws;
        this.childCases = childCases;
		this.motherCases = motherCases;
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

	@Column(name = "group_id", unique = true, length = 36)
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "case_sharing")
	public Boolean getCaseSharing() {
		return this.caseSharing;
	}

	public void setCaseSharing(Boolean caseSharing) {
		this.caseSharing = caseSharing;
	}

	@Column(name = "domain")
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "awc_code")
	public String getAwcCode() {
		return this.awcCode;
	}

	public void setAwcCode(String awcCode) {
		this.awcCode = awcCode;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "reporting")
	public Boolean getReporting() {
		return this.reporting;
	}

	public void setReporting(Boolean reporting) {
		this.reporting = reporting;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flwGroup")
	public Set<ChildCase> getChildCases() {
		return this.childCases;
	}

    @ManyToMany(mappedBy="flwGroups")
    public Set<Flw> getFlws() {
        return flws;
    }

    public void setFlws(Set<Flw> flws) {
        this.flws = flws;
    }

    public void setChildCases(Set<ChildCase> childCases) {
		this.childCases = childCases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flwGroup")
	public Set<MotherCase> getMotherCases() {
		return this.motherCases;
	}

	public void setMotherCases(Set<MotherCase> motherCases) {
		this.motherCases = motherCases;
	}

    public void updateToLatest(FlwGroup other) {
        this.name = other.name;
        this.domain = other.domain;
        this.awcCode = other.awcCode;
        this.caseSharing = other.caseSharing;
        this.reporting = other.reporting;
    }

}
