package org.motechproject.care.reporting.domain.dimension;

import org.motechproject.care.reporting.domain.SelfUpdatable;
import org.motechproject.care.reporting.domain.annotations.ExternalPrimaryKey;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "flw_group", uniqueConstraints = @UniqueConstraint(columnNames = "group_id"))
public class FlwGroup extends SelfUpdatable<FlwGroup> implements java.io.Serializable {

	private int id;
    @ExternalPrimaryKey
	private String groupId;
	private Boolean caseSharing;
	private String domain;
	private String awcCode;
	private String name;
	private Boolean reporting;
    private Date creationTime;
    private Date lastModifiedTime;
    private Set<Flw> flws;

	public FlwGroup() {
        Date date = new Date();
        creationTime = date;
        lastModifiedTime = date;
        flws = new HashSet<>(0);
	}

    public FlwGroup(int id, String groupId, Boolean caseSharing, String domain,
                    String awcCode, String name, Boolean reporting,
                    Date creationTime, Date lastModifiedTime, Set<Flw> flws) {
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

	@Column(name = "group_id", unique = true)
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

    @ManyToMany(mappedBy="flwGroups")
    public Set<Flw> getFlws() {
        return flws;
    }

    public void setFlws(Set<Flw> flws) {
        this.flws = flws;
    }

    @Override
    public void updateToLatest(FlwGroup other) {
        validateIfUpdatable(this.groupId, other.groupId);

        updateFields(other, Arrays.asList("id", "groupId", "creationTime", "flws"));
    }

    @Override
    protected void updateLastModifiedTime() {
        this.lastModifiedTime = new Date();
    }
}
