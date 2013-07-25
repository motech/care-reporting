package org.motechproject.care.reporting.ft.couch.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;
import org.motechproject.couch.mrs.model.CouchObservation;
import org.motechproject.mrs.domain.MRSObservation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@XmlRootElement
public class Observation {

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
        this.observationId = observationId;
        this.conceptName = conceptName;
        this.value = value.toString();
        this.patientId = patientId;
        this.dependentObservations = dependentObservations;
        this.date = date;
    }

    public Observation(CouchObservation couchObservation) {
        this.observationId = couchObservation.getObservationId();
        this.conceptName = couchObservation.getConceptName();
        this.value = couchObservation.getValue().toString();
        this.patientId = couchObservation.getPatientId();
        this.dependentObservations = map(couchObservation.getDependantObservations());
        this.date = couchObservation.getDate();
    }

    public Observation(MRSObservation mrsObservation) {
        this.observationId = mrsObservation.getObservationId();
        this.conceptName = mrsObservation.getConceptName();
        this.value = mrsObservation.getValue().toString();
        this.patientId = mrsObservation.getPatientId();
        this.dependentObservations = map(mrsObservation.getDependantObservations());
        this.date = mrsObservation.getDate();
    }

    private List<Observation> map(Set<? extends MRSObservation> dependantObservations) {
        List<Observation> observations = new ArrayList<>();
        for (MRSObservation dependantObservation : dependantObservations) {
            observations.add(new Observation(dependantObservation));
        }
        return observations;
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
