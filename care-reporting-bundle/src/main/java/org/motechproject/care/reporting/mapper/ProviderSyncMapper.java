package org.motechproject.care.reporting.mapper;

public class ProviderSyncMapper extends Mapper {
    private static ProviderSyncMapper _instance;

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

    public static ProviderSyncMapper getInstance() {
        if (_instance != null) {
            return _instance;
        }
        return createInstance();
    }

    private synchronized static ProviderSyncMapper createInstance() {
        if (_instance != null) {
            return _instance;
        }
        _instance = new ProviderSyncMapper();
        return _instance;
    }
}
