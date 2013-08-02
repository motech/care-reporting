package org.motechproject.care.reporting.migration.task;

import com.google.gson.JsonArray;
import org.apache.commons.httpclient.NameValuePair;
import org.motechproject.care.reporting.migration.MigratorArguments;
import org.motechproject.care.reporting.migration.common.PaginatedResult;
import org.motechproject.care.reporting.migration.service.Paginator;
import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class MigrationTask {

    protected final CommcareAPIHttpClient commcareAPIHttpClient;
    protected final MotechAPIHttpClient motechAPIHttpClient;

    public MigrationTask(CommcareAPIHttpClient commcareAPIHttpClient, MotechAPIHttpClient motechAPIHttpClient) {
        this.commcareAPIHttpClient = commcareAPIHttpClient;
        this.motechAPIHttpClient = motechAPIHttpClient;
    }

    public void migrate(MigratorArguments migratorArguments) {
        NameValuePair[] pairs = getNameValuePair(migratorArguments);
        Paginator paginator = getPaginator(pairs);
        PaginatedResult paginatedResult;
        while ((paginatedResult = paginator.nextPage()) != null) {
            postToMotech(paginatedResult.getResponse());
        }
    }

    private NameValuePair[] getNameValuePair(MigratorArguments migratorArguments) {
        Map<String, String> optionsToUrlMapper = getOptionsToUrlMapper();

        List<NameValuePair> pairs = new ArrayList<>();
        for (Map.Entry<String, Object> entry : migratorArguments.getMap().entrySet()) {
            String optionKey = entry.getKey();

            if (optionsToUrlMapper.containsKey(optionKey)) {
                pairs.add(new NameValuePair(optionsToUrlMapper.get(optionKey), entry.getValue().toString()));
            }
        }
        return pairs.toArray(new NameValuePair[pairs.size()]);
    }

    protected abstract Map<String, String> getOptionsToUrlMapper();

    protected abstract Paginator getPaginator(NameValuePair[] pairs);

    protected abstract void postToMotech(JsonArray request);

}
