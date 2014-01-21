package org.motechproject.care.reporting.parser;

public enum FormCaseType {
    CHILD_ONLY("case"),
    MOTHER_ONLY("case"),
    AWW_MOTHER_AND_CHILD("subcase_", "case"),
    CCS_MOTHER_AND_CHILD("child_info", "case"),
    CHILD_MANY_TO_MANY("child");

    private String childCaseRootNode;
    private String motherCaseRootNode;

    FormCaseType(String childCaseRootNode) {
        this.childCaseRootNode = childCaseRootNode;
    }

    FormCaseType(String childCaseRootNode, String motherCaseRootNode) {
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
