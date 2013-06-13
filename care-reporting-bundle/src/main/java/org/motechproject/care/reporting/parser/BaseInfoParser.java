package org.motechproject.care.reporting.parser;

public abstract class BaseInfoParser {

    protected InfoParser infoParser;

    public BaseInfoParser(InfoParser infoParser){
        this.infoParser = infoParser;
    }
}
