package org.motechproject.care.reporting.domain.measure;

import org.motechproject.care.reporting.domain.annotations.ExternalPrimaryKey;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class Form implements java.io.Serializable {
    @ExternalPrimaryKey
    private String instanceId;

    private String appVersion;

    private Date serverDateModified;

    private Integer deliveryOffsetDays;

    public Form() {
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "server_date_modified", length = 35)
    public Date getServerDateModified() {
        return serverDateModified;
    }

    public void setServerDateModified(Date serverDateModified) {
        this.serverDateModified = serverDateModified;
    }


    @Column(name = "delivery_offset_days")
    public Integer getDeliveryOffsetDays() {
        return this.deliveryOffsetDays;
    }

    public void setDeliveryOffsetDays(Integer deliveryOffsetDays) {
        this.deliveryOffsetDays = deliveryOffsetDays;
    }

}
