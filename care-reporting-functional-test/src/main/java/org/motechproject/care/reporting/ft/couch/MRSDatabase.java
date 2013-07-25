package org.motechproject.care.reporting.ft.couch;

import org.motechproject.care.reporting.ft.couch.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MRSDatabase {

    @Autowired
    private MRSEncounterService mrsEncounterService;

    @Autowired
    private MRSFacilityService mrsFacilityService;

    @Autowired
    private MRSPatientService mrsPatientService;

    @Autowired
    private MRSPersonService mrsPersonService;

    @Autowired
    private MRSProviderService mrsProviderService;

    public MRSEncounterService encounters() {
        return mrsEncounterService;
    }

    public MRSFacilityService facilities() {
        return mrsFacilityService;
    }

    public MRSPatientService patients() {
        return mrsPatientService;
    }

    public MRSPersonService persons() {
        return mrsPersonService;
    }

    public MRSProviderService providers() {
        return mrsProviderService;
    }
}
