package org.motechproject.care.reporting.mapper;


import org.motechproject.care.reporting.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CareReportingMapper extends Mapper {

    private static CareReportingMapper _instance;

    private CareReportingMapper(Service careService) {
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
        });
        allDataTypeConverters.registerDomainConverters(beanUtils.getConvertUtils(), careService);
    }

    public static CareReportingMapper getInstance(Service careService) {
        if (_instance != null) {
            return _instance;
        }
        return createInstance(careService);
    }

    private synchronized static CareReportingMapper createInstance(Service careService) {
        if (_instance != null) {
            return _instance;
        }
        _instance = new CareReportingMapper(careService);
        return _instance;
    }
}