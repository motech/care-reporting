package org.motechproject.care.reporting.ft.couch.service;

import org.motechproject.care.reporting.ft.couch.domain.Encounter;
import org.motechproject.care.reporting.ft.couch.domain.Patient;
import org.motechproject.care.reporting.ft.utils.TimedRunner;
import org.motechproject.care.reporting.ft.utils.TimedRunnerBreakCondition;
import org.motechproject.couch.mrs.model.CouchPatientImpl;
import org.motechproject.couch.mrs.repository.AllCouchPatients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MRSPatientService {

    private final AllCouchPatients allCouchPatients;
    private final MRSPersonService mrsPersonService;
    private final MRSFacilityService mrsFacilityService;
    private final MRSEncounterService mrsEncounterService;

    @Autowired
    public MRSPatientService(AllCouchPatients allCouchPatients, MRSPersonService mrsPersonService, MRSFacilityService mrsFacilityService, MRSEncounterService mrsEncounterService) {
        this.allCouchPatients = allCouchPatients;
        this.mrsPersonService = mrsPersonService;
        this.mrsFacilityService = mrsFacilityService;
        this.mrsEncounterService = mrsEncounterService;
    }


    public Patient waitAndFindByMotechId(final String motechId)  {
        TimedRunner<Patient> timedRunner = new TimedRunner<Patient>(10, 1000) {
            @Override
            protected Patient run() {
                return findByMotechId(motechId);
            }
        };

        try {
            return timedRunner.executeWithTimeout();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Patient waitAndFindByMotechId(final String motechId, TimedRunnerBreakCondition breakCondition)  {
        TimedRunner<Patient> timedRunner = new TimedRunner<Patient>(100, 100, breakCondition) {
            @Override
            protected Patient run() {
                return findByMotechId(motechId);
            }
        };

        try {
            return timedRunner.executeWithTimeout();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Patient findByMotechId(String motechId) {
        if (motechId == null) {
            return null;
        }
        return getSinglePatient(allCouchPatients.findByMotechId(motechId));
    }

    private Patient getSinglePatient(List<CouchPatientImpl> couchPatients) {
        if (couchPatients.size() == 0) {
            return null;
        }

        CouchPatientImpl couchPatient = couchPatients.get(0);

        return new Patient(couchPatient.getId(), couchPatient.getPatientId(), couchPatient.getMotechId(), mrsPersonService.getFor(couchPatient.getPersonId()), mrsFacilityService.getFor(couchPatient.getFacilityId()), mrsEncounterService.getForMotechId(couchPatient.getMotechId()));
    }

    public Patient findByPatientId(String patientId) {
        if (patientId == null) {
            return null;
        }
        return getSinglePatient(allCouchPatients.findByPatientId(patientId));
    }

    public void delete(String motechId, boolean deleteProviders) {
        List<CouchPatientImpl> patients = allCouchPatients.findByMotechId(motechId);
        if(patients.size() == 0) {
            return;
        }
        CouchPatientImpl patient = patients.get(0);
        mrsPersonService.delete(patient.getPersonId());

        List<Encounter> encounters = mrsEncounterService.getForMotechId(motechId);
        for (Encounter encounter : encounters) {
            mrsEncounterService.delete(encounter.getEncounterId(), deleteProviders);
        }

        allCouchPatients.remove(patient);
    }
}
