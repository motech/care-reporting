package org.motechproject.care.reporting.ft.couch.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.joda.time.DateTime;
import org.motechproject.care.reporting.ft.couch.domain.Encounter;
import org.motechproject.care.reporting.ft.couch.domain.Observation;
import org.motechproject.couch.mrs.model.CouchEncounterImpl;
import org.motechproject.couch.mrs.model.CouchObservation;
import org.motechproject.couch.mrs.repository.impl.AllCouchEncountersImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MRSEncounterService {

    private final MRSFacilityService mrsFacilityService;
    private final MRSProviderService mrsProviderService;
    private final AllCouchEncountersImpl allCouchEncounters;

    @Autowired
    public MRSEncounterService(MRSFacilityService mrsFacilityService,
                               MRSProviderService mrsProviderService, AllCouchEncountersImpl allCouchEncounters) {
        this.mrsFacilityService = mrsFacilityService;
        this.mrsProviderService = mrsProviderService;
        this.allCouchEncounters = allCouchEncounters;

    }

    public List<Encounter> getForMotechId(String motechId) {

        List<Encounter> encounters = new ArrayList<Encounter>();
        List<CouchEncounterImpl> couchEncounters = allCouchEncounters.getAll();
        for (CouchEncounterImpl couchEncounter : couchEncounters) {
            if (!motechId.equals(couchEncounter.getPatientId())) {
                continue;
            }
            encounters.add(map(couchEncounter));
        }

        Collections.sort(encounters, Collections.reverseOrder(new Comparator<Encounter>() {
            private DateTime getEncounterDate(Encounter encounter) {
                return encounter == null ? null : encounter.getDate();
            }

            @Override
            public int compare(Encounter o1, Encounter o2) {
                return new CompareToBuilder()
                        .append(getEncounterDate(o1), getEncounterDate(o2))
                        .toComparison();
            }
        }));

        return encounters;
    }

    private Encounter map(CouchEncounterImpl couchEncounter) {
        List<Observation> observations = new ArrayList<>();
        for (CouchObservation couchObservation : couchEncounter.getObservations()) {
            observations.add(new Observation(couchObservation));
        }

        Collections.sort(observations, new Comparator<Observation>() {
            private String getConceptName(Observation observation) {
                return observation == null ? null : StringUtils.lowerCase(observation.getConceptName());
            }

            @Override
            public int compare(Observation o1, Observation o2) {
                return new CompareToBuilder()
                        .append(getConceptName(o1), getConceptName(o2))
                        .toComparison();
            }
        });

        Encounter encounter = new Encounter(couchEncounter.getId(), couchEncounter.getEncounterId(), couchEncounter.getEncounterType(), mrsFacilityService.getFor(couchEncounter.getFacilityId()), mrsProviderService.getFor(couchEncounter.getProviderId()), couchEncounter.getPatientId(), observations, couchEncounter.getCreatorId(), couchEncounter.getDate());
        return encounter;
    }


    public void delete(String encounterId, boolean deleteProviders) {
        CouchEncounterImpl encounter = allCouchEncounters.findEncounterById(encounterId);

        if(encounter == null) {
            return;
        }

        if(deleteProviders) {
            mrsProviderService.delete(encounter.getProviderId());
        }

        allCouchEncounters.remove(encounter);

    }
}
