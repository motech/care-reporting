package org.motechproject.care.reporting.ft.couch.domain;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Provider extends CouchDocument {

    @JsonProperty
    @XmlElement
    private String providerId;

    @JsonProperty
    @XmlElement
    private Person person;

    public Provider() {

    }

    public Provider(String couchId, String providerId, Person person) {
        super(couchId, "motech-provider-repository");
        this.providerId = providerId;
        this.person = person;
    }

    public String getProviderId() {
        return providerId;
    }

    public Person getPerson() {
        return person;
    }
}
