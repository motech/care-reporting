package org.motechproject.care.reporting.ft.couch.domain;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Attribute {

    @JsonProperty
    @XmlElement
    private String name;

    @JsonProperty
    @XmlElement
    private String value;

    public Attribute() {

    }

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
