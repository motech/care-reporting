package org.motechproject.care.reporting.parser;

public enum AwwCaseType {
    CHILD_ONLY("case"),
    MOTHER_ONLY("case"),
    MOTHER_AND_CHILD("subcase_", "case");

    private String childCaseRootNode;
    private String motherCaseRootNode;

    AwwCaseType(String childCaseRootNode) {
        this.childCaseRootNode = childCaseRootNode;
    }

    AwwCaseType(String childCaseRootNode, String motherCaseRootNode) {
        this.childCaseRootNode = childCaseRootNode;
        this.motherCaseRootNode = motherCaseRootNode;
    }

    public String getChildCaseRootNode() {
        return childCaseRootNode;
    }

    public String getMotherCaseRootNode() {
        return motherCaseRootNode;
    }
}
