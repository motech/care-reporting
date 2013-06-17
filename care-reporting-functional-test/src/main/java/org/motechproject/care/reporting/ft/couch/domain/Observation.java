package org.motechproject.care.reporting.ft.couch.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Observation extends CouchDocument {

    @JsonProperty
    @XmlElement
    private String observationId;
    @JsonProperty
    @XmlElement
    private String conceptName;

    @JsonProperty
    @XmlElement
    private String value;

    @JsonProperty
    @XmlElement
    private String patientId;

    @JsonProperty
    @XmlElement
    @XmlElementWrapper(name = "dependentObservations")
    private List<Observation> dependentObservations = new ArrayList<Observation>();

    @JsonProperty
    @XmlElement
    private DateTime date;

    public Observation() {

    }

    public Observation(String couchId, String observationId, String conceptName, String value, String patientId, List<Observation> dependentObservations, DateTime date) {
        super(couchId, "motech-observation-repository");
        this.observationId = observationId;
        this.conceptName = conceptName;
        this.value = value.toString();
        this.patientId = patientId;
        this.dependentObservations = dependentObservations;
        this.date = date;
    }

    public String getObservationId() {
        return observationId;
    }

    public String getConceptName() {
        return conceptName;
    }

    public String getValue() {
        return value;
    }

    public String getPatientId() {
        return patientId;
    }

    public List<Observation> getDependentObservations() {
        return dependentObservations;
    }

    public DateTime getDate() {
        return date;
    }
}
