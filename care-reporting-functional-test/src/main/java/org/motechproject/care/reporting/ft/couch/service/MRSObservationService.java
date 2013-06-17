package org.motechproject.care.reporting.ft.couch.service;

import org.motechproject.care.reporting.ft.couch.domain.Observation;
import org.motechproject.couch.mrs.model.CouchObservationImpl;
import org.motechproject.couch.mrs.repository.impl.AllCouchObservationsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class MRSObservationService {

    private final AllCouchObservationsImpl allCouchObservations;


    @Autowired
    public MRSObservationService(AllCouchObservationsImpl allCouchObservations) {
        this.allCouchObservations = allCouchObservations;
    }

    public Observation getFor(String observationId) {
        if (observationId == null) {
            return null;
        }

        List<CouchObservationImpl> observations = allCouchObservations.findByObservationId(observationId);
        if (observations.size() == 0) {
            throw new SaveOperationStillInProgressException();
        }
        CouchObservationImpl couchObservation = observations.get(0);

        List<Observation> dependentObservations = new ArrayList<Observation>();
        Set<String> dependentObservationIds = couchObservation.getDependantObservationIds();
        if (dependentObservationIds != null && dependentObservationIds.size() > 0) {
            for (String dependentObservationId : dependentObservationIds) {
                dependentObservations.add(this.getFor(dependentObservationId));
            }
        }

        return new Observation(couchObservation.getId(), couchObservation.getObservationId(), couchObservation.getConceptName(), getObservationValue(couchObservation), couchObservation.getPatientId(), dependentObservations, couchObservation.getDate());
    }

    private String getObservationValue(CouchObservationImpl couchObservation) {
        Object value = couchObservation.getValue();
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public void delete(String observationId) {
        List<CouchObservationImpl> observations = allCouchObservations.findByObservationId(observationId);
        if (observations.size() == 0) {
            return;
        }

        CouchObservationImpl observation = observations.get(0);

        Set dependantObservationIds = observation.getDependantObservationIds();
        if (dependantObservationIds != null) {
            for (Object dependentObservationId : dependantObservationIds) {
                delete((String) dependentObservationId);
            }
        }

        allCouchObservations.remove(observation);
    }
}
