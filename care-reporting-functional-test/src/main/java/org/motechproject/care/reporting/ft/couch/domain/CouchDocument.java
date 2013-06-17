package org.motechproject.care.reporting.ft.couch.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;

public abstract class CouchDocument {

    @JsonProperty
    @XmlElement
    private String couchId;

    private String couchDBName;

    public CouchDocument() {
    }

    public CouchDocument(String couchId, String couchDBName) {
        this.couchId = couchId;
        this.couchDBName = couchDBName;
    }

    public String getCouchId() {
        return couchId;
    }

    @JsonIgnore
    public String getCouchDocumentUrl(String couchBaseUrl) {
        return String.format("%s/_utils/document.html?%s_%s/%s", couchBaseUrl, TenantIdentity.getTenantId(), couchDBName, couchId);
    }
}
