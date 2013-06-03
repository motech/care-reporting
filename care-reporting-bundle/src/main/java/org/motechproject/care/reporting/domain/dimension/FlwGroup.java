package org.motechproject.care.reporting.domain.dimension;

// Generated Jun 3, 2013 2:41:26 PM by Hibernate Tools 3.4.0.CR1

import org.motechproject.care.reporting.domain.measure.NewForm;
import org.motechproject.care.reporting.domain.measure.RegistrationChildForm;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * FlwGroup generated by hbm2java
 */
@Entity
@Table(name = "flw_group", uniqueConstraints = @UniqueConstraint(columnNames = "group_id"))
public class FlwGroup implements Serializable {

	private int id;
	private String groupId;
	private Boolean caseSharing;
	private String groupDomain;
	private String awcCode;
	private String groupName;
	private Serializable path;
	private Boolean reporting;
	private String resourceUri;
	private Set<FlwGroupMap> flwGroupMaps = new HashSet<FlwGroupMap>(0);
	private Set<ChildCase> childCases = new HashSet<ChildCase>(0);
	private Set<RegistrationChildForm> registrationChildForms = new HashSet<RegistrationChildForm>(
			0);
	private Set<MotherCase> motherCases = new HashSet<MotherCase>(0);
	private Set<NewForm> newForms = new HashSet<NewForm>(0);

	public FlwGroup() {
	}

	public FlwGroup(int id) {
		this.id = id;
	}

	public FlwGroup(int id, String groupId, Boolean caseSharing,
			String groupDomain, String awcCode, String groupName,
			Serializable path, Boolean reporting, String resourceUri,
			Set<FlwGroupMap> flwGroupMaps, Set<ChildCase> childCases,
			Set<RegistrationChildForm> registrationChildForms,
			Set<MotherCase> motherCases, Set<NewForm> newForms) {
		this.id = id;
		this.groupId = groupId;
		this.caseSharing = caseSharing;
		this.groupDomain = groupDomain;
		this.awcCode = awcCode;
		this.groupName = groupName;
		this.path = path;
		this.reporting = reporting;
		this.resourceUri = resourceUri;
		this.flwGroupMaps = flwGroupMaps;
		this.childCases = childCases;
		this.registrationChildForms = registrationChildForms;
		this.motherCases = motherCases;
		this.newForms = newForms;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
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

	@Column(name = "group_domain")
	public String getGroupDomain() {
		return this.groupDomain;
	}

	public void setGroupDomain(String groupDomain) {
		this.groupDomain = groupDomain;
	}

	@Column(name = "awc_code")
	public String getAwcCode() {
		return this.awcCode;
	}

	public void setAwcCode(String awcCode) {
		this.awcCode = awcCode;
	}

	@Column(name = "group_name")
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "path")
	public Serializable getPath() {
		return this.path;
	}

	public void setPath(Serializable path) {
		this.path = path;
	}

	@Column(name = "reporting")
	public Boolean getReporting() {
		return this.reporting;
	}

	public void setReporting(Boolean reporting) {
		this.reporting = reporting;
	}

	@Column(name = "resource_uri")
	public String getResourceUri() {
		return this.resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flwGroup")
	public Set<FlwGroupMap> getFlwGroupMaps() {
		return this.flwGroupMaps;
	}

	public void setFlwGroupMaps(Set<FlwGroupMap> flwGroupMaps) {
		this.flwGroupMaps = flwGroupMaps;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flwGroup")
	public Set<ChildCase> getChildCases() {
		return this.childCases;
	}

	public void setChildCases(Set<ChildCase> childCases) {
		this.childCases = childCases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flwGroup")
	public Set<RegistrationChildForm> getRegistrationChildForms() {
		return this.registrationChildForms;
	}

	public void setRegistrationChildForms(
			Set<RegistrationChildForm> registrationChildForms) {
		this.registrationChildForms = registrationChildForms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flwGroup")
	public Set<MotherCase> getMotherCases() {
		return this.motherCases;
	}

	public void setMotherCases(Set<MotherCase> motherCases) {
		this.motherCases = motherCases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flwGroup")
	public Set<NewForm> getNewForms() {
		return this.newForms;
	}

	public void setNewForms(Set<NewForm> newForms) {
		this.newForms = newForms;
	}

}