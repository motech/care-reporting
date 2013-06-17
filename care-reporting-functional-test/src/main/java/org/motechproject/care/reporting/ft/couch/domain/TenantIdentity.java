package org.motechproject.care.reporting.ft.couch.domain;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

final public class TenantIdentity {
    private static String tenantId = System.getProperty("user.name");

    static {
        Resource resource = new ClassPathResource("/tenant.properties");
        Properties props = null;
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String value = props.getProperty("tenant.id");

        if(!StringUtils.isBlank(value)) {
            tenantId = value;
        }
    }

    private TenantIdentity() throws IOException {

    }

    public static String getTenantId() {
         return tenantId;
    }
}
