package org.motechproject.care.reporting.migration.task;

import com.google.gson.JsonArray;
import org.motechproject.care.reporting.migration.MigratorArguments;
import org.motechproject.care.reporting.migration.common.CommcareResponseWrapper;
import org.motechproject.care.reporting.migration.common.PaginatedResult;
import org.motechproject.care.reporting.migration.common.PaginationOption;
import org.motechproject.care.reporting.migration.common.ResponseParser;
import org.motechproject.care.reporting.migration.service.PaginationScheme;
import org.motechproject.care.reporting.migration.service.Paginator;
import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MigrationTask {

    private static final Logger logger = LoggerFactory.getLogger(CaseMigrationTask.class);
    private static final Logger progressLogger = LoggerFactory.getLogger("migration-progress-logger");

    protected final CommcareAPIHttpClient commcareAPIHttpClient;
    protected final MotechAPIHttpClient motechAPIHttpClient;
    private ResponseParser responseParser;

    public MigrationTask(CommcareAPIHttpClient commcareAPIHttpClient, MotechAPIHttpClient motechAPIHttpClient, ResponseParser responseParser) {
        this.commcareAPIHttpClient = commcareAPIHttpClient;
        this.motechAPIHttpClient = motechAPIHttpClient;
        this.responseParser = responseParser;
    }

    public void migrate(MigratorArguments migratorArguments) {
        Map<String, String> pairs = getNameValuePair(migratorArguments);
        Paginator paginator = getPaginator(pairs);
        PaginatedResult paginatedResult;
        while ((paginatedResult = paginator.nextPage()) != null) {
            postToMotech(paginatedResult.getResponse());
        }
    }

    private void postToMotech(JsonArray request) {
        List<CommcareResponseWrapper> commcareResponseWrappers = convertToEntity(request);
        int recordsCount = commcareResponseWrappers.size();
        String log = String.format("Started posting %d request(s) to motech", recordsCount);
        logger.info(log);
        progressLogger.info(log);
        int successCount = 0;
        try {
            for (CommcareResponseWrapper commcareResponseWrapper : commcareResponseWrappers) {
                postToMotech(commcareResponseWrapper);
                successCount++;
            }
        } finally {
            if(successCount != recordsCount) {
                log = String.format("Error posting posting request(s) to motech. Successful: %s, Failed: %s", successCount, recordsCount - successCount);
                logger.error(log);
                progressLogger.error(log);
            } else {
                log = String.format("Completed successfully posting %d request(s) to motech", recordsCount);
                logger.info(log);
                progressLogger.info(log);
            }
        }
    }


    private Map<String,String> getNameValuePair(MigratorArguments migratorArguments) {
        Map<String, String> optionsToUrlMapper = getOptionsToUrlMapper();

        Map<String,String> pairs = new HashMap<>();
        for (Map.Entry<String, Object> entry : migratorArguments.getOptions().entrySet()) {
            String optionKey = entry.getKey();

            if (optionsToUrlMapper.containsKey(optionKey)) {
                pairs.put(optionsToUrlMapper.get(optionKey), entry.getValue().toString());
            }
            else
                pairs.put(optionKey,entry.getValue().toString());
        }
        return pairs;
    }

    protected Paginator getPaginator(Map<String, String> pairs) {
        PaginationScheme paginationScheme = new PaginationScheme() {
            @Override
            public String nextPage(Map<String, String> parameters, PaginationOption paginationOption) {
                String log = String.format("Fetching cases from commcare with offset: %s, limit: %s", paginationOption.getLimit(), paginationOption.getOffset());
                progressLogger.info(log);
                logger.info(log);
                return fetchCommcareRecords(parameters, paginationOption);
            }
        };

        return new Paginator(pairs, paginationScheme, responseParser);
    }

    protected abstract Map<String, String> getOptionsToUrlMapper();

    protected abstract List<CommcareResponseWrapper> convertToEntity(JsonArray request);

    protected abstract void postToMotech(CommcareResponseWrapper commcareResponseWrapper);

    protected abstract String fetchCommcareRecords(Map<String, String> parameters, PaginationOption paginationOption);

}
