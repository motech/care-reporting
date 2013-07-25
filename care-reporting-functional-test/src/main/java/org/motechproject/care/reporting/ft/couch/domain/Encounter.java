package org.motechproject.care.reporting.ft.couch.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Encounter extends CouchDocument {

    @JsonProperty
    @XmlElement
    private String encounterId;

    @JsonProperty
    @XmlElement
    private String type;

    @JsonProperty
    @XmlElement
    private Facility facility;

    @JsonProperty("encounterDate")
    @XmlElement
    private Provider provider;

    @JsonProperty("encounterPatientId")
    @XmlElement
    private String patientId;

    @JsonProperty
    @XmlElement
    @XmlElementWrapper(name = "observations")
    private List<Observation> observations = new ArrayList<Observation>();

    @JsonProperty
    @XmlElement
    private String creatorId;

    @JsonProperty
    @XmlElement
    private DateTime date;

    public Encounter() {

    }

    public Encounter(String couchId, String encounterId, String type, Facility facility, Provider provider, String patientId, List<Observation> observations, String creatorId, DateTime date) {
        super(couchId, "motech-encounter-repository");
        this.encounterId = encounterId;
        this.type = type;
        this.facility = facility;
        this.provider = provider;
        this.patientId = patientId;
        this.observations = observations;
        this.creatorId = creatorId;
        this.date = date;
    }

    public String getEncounterId() {
        return encounterId;
    }

    public String getType() {
        return type;
    }

    public Facility getFacility() {
        return facility;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getPatientId() {
        return patientId;
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public DateTime getDate() {
        return date;
    }
}
