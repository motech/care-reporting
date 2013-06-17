package org.motechproject.care.reporting.ft.couch.service;

import org.motechproject.care.reporting.ft.couch.domain.Provider;
import org.motechproject.couch.mrs.model.CouchProviderImpl;
import org.motechproject.couch.mrs.repository.impl.AllCouchProvidersImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MRSProviderService {

    private final AllCouchProvidersImpl allCouchProviders;
    private final MRSPersonService mrsPersionService;

    @Autowired
    public MRSProviderService(AllCouchProvidersImpl allCouchProviders, MRSPersonService mrsPersionService) {
        this.allCouchProviders = allCouchProviders;
        this.mrsPersionService = mrsPersionService;
    }

    public Provider getFor(String providerId) {
        if (providerId == null) {
            return null;
        }

        CouchProviderImpl couchProvider = allCouchProviders.findByProviderId(providerId).get(0);
        return new Provider(couchProvider.getId(), couchProvider.getProviderId(), mrsPersionService.getFor(couchProvider.getPersonId()));
    }

    public void delete(String providerId) {
        List<CouchProviderImpl> providers = allCouchProviders.findByProviderId(providerId);
        if(providers.size() == 0) {
            return;
        }

        CouchProviderImpl provider = providers.get(0);
        mrsPersionService.delete(provider.getPersonId());
        allCouchProviders.remove(provider);

    }
}
