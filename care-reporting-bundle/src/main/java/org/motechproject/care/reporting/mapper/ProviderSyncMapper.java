package org.motechproject.care.reporting.mapper;

import org.springframework.stereotype.Component;

@Component
public class ProviderSyncMapper extends Mapper {

    public ProviderSyncMapper() {
        super(new String[]{
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "yyyy-MM-dd'T'HH:mm:ssXXX",
                "yyyy-MM-dd'T'HH:mm:ssZ",
                "yyyy-MM-dd'T'HH:mm:ss",
                "yyyy-MM-dd HH:mm:ss.SSSXXX",
                "yyyy-MM-dd HH:mm:ss.SSSZ",
                "yyyy-MM-dd HH:mm:ssXXX",
                "yyyy-MM-dd HH:mm:ssZ",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd",
                "dd/MM/yyyy"
        });
    }
}
