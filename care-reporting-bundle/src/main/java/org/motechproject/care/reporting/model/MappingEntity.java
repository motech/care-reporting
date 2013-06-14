package org.motechproject.care.reporting.model;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.motechproject.care.reporting.parser.*;


public class MappingEntity {

    private String identifier;

    private String version;

    private String segment;

    @JsonDeserialize(as=InfoParserImpl.class)
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
}
