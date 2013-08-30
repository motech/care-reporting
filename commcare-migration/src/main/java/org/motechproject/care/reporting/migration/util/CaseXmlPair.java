package org.motechproject.care.reporting.migration.util;

public class CaseXmlPair {
    private String createUpdateAction;
    private String closeAction;

    public CaseXmlPair() {
    }

    public CaseXmlPair(String createUpdateAction, String closeAction) {
        this.createUpdateAction = createUpdateAction;
        this.closeAction = closeAction;
    }

    public String getCreateUpdateAction() {
        return createUpdateAction;
    }

    public String getCloseAction() {
        return closeAction;
    }

    public void setCreateUpdateAction(String createUpdate) {
        this.createUpdateAction = createUpdate;
    }

    public void setClosedAction(String close) {
        this.closeAction = close;
    }

    public boolean hasClosedAction() {
        return closeAction != null;
    }
}
