package org.motechproject.care.reporting.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.motechproject.care.reporting.parser.InfoParser;
import org.motechproject.care.reporting.parser.InfoParserImpl;


public class MappingEntity {

    public MappingEntity() {

    }

    public MappingEntity(String identifier, String version, String segment) {
        this.identifier = identifier;
        this.version = version;
        this.segment = segment;
    }

    private String identifier;
    private String version;
    private String segment;

    @JsonProperty("parser")
    @JsonDeserialize(as = InfoParserImpl.class)
    private InfoParser infoParser = new InfoParserImpl();


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public InfoParser getInfoParser() {
        return infoParser;
    }

    public void setInfoParser(InfoParser infoParser) {
        this.infoParser = infoParser;
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
