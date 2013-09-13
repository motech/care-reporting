package org.motechproject.care.reporting.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.motechproject.care.reporting.parser.InfoParser;
import org.motechproject.care.reporting.parser.InfoParserImpl;

import static java.util.Arrays.asList;


public class AppVersionListEntity {

    @JsonProperty("isExclusionList")
    private boolean isExclusion;

    @JsonProperty("versions")
    private String[] appVersions;

    public AppVersionListEntity() {

    }

    public AppVersionListEntity(boolean isExclusion, String[] appVersions) {
        this.isExclusion = isExclusion;
        this.appVersions = appVersions;
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean isExclusion() {
        return isExclusion;
    }

    public boolean isInclusion() {
        return !isExclusion();
    }

    public boolean contains(String appversion){
        return asList(getAppVersions()).contains(appversion);
    }

    public void setIsExclusion(boolean exclusion) {
        isExclusion = exclusion;
    }

    public String[] getAppVersions() {
        return appVersions;
    }

    public void setAppVersions(String[] appVersions) {
        this.appVersions = appVersions;
    }
}
