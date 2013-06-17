package org.motechproject.care.reporting.ft.couch.domain;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Patient extends CouchDocument {

    @JsonProperty
    @XmlElement
    private String couchId;

    @JsonProperty
    @XmlElement
    private String patientId;

    @JsonProperty
    @XmlElement
    private String motechId;

    @JsonProperty
    @XmlElement
    private Person person;

    @JsonProperty
    @XmlElement
    private Facility facility;

    @JsonProperty
    @XmlElement
    @XmlElementWrapper(name = "encounters")
    private List<Encounter> encounters = new ArrayList<Encounter>();

    public Patient() {

    }

    public Patient(String couchId, String patientId, String motechId, Person person, Facility facility, List<Encounter> encounters) {
        super(couchId, "motech-patient-repository");
        this.couchId = couchId;
        this.patientId = patientId;
        this.motechId = motechId;
        this.person = person;
        this.facility = facility;
        this.encounters = encounters;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getMotechId() {
        return motechId;
    }

    public Person getPerson() {
        return person;
    }

    public Facility getFacility() {
        return facility;
    }

    public List<Encounter> getEncounters() {
        return encounters;
    }
}
