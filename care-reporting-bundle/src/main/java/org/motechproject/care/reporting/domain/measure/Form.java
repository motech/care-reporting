package org.motechproject.care.reporting.domain.measure;

import org.motechproject.care.reporting.domain.annotations.ExternalPrimaryKey;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Form implements java.io.Serializable {
    @ExternalPrimaryKey
    private String instanceId;

    private String appVersion;

    public Form() {
    }

    public Form(String instanceId) {
        this.instanceId = instanceId;
    }

    @Column(name = "instance_id", unique = true)
    public String getInstanceId() {
        return this.instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Column(name = "app_version")
    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
