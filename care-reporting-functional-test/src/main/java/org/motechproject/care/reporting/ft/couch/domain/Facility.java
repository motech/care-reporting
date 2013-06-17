package org.motechproject.care.reporting.ft.couch.domain;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Facility extends CouchDocument {

    @JsonProperty
    @XmlElement
    private String facilityId;

    @JsonProperty
    @XmlElement
    private String name;

    @JsonProperty
    @XmlElement
    private String region;

    @JsonProperty
    @XmlElement
    private String countyDistrict;

    @JsonProperty
    @XmlElement
    private String stateProvince;

    @JsonProperty
    @XmlElement
    private String country;

    public Facility() {

    }

    public Facility(String couchId, String facilityId, String name, String region, String countyDistrict, String stateProvince, String country) {
        super(couchId, "motech-facility-repository");
        this.facilityId = facilityId;
        this.name = name;
        this.region = region;
        this.countyDistrict = countyDistrict;
        this.stateProvince = stateProvince;
        this.country = country;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getCountyDistrict() {
        return countyDistrict;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public String getCountry() {
        return country;
    }
}
