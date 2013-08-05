package org.motechproject.care.reporting.migration.common;

public class PaginationOption {

    private int limit = getDefaultLimit();
    private int offset = getDefaultOffset();

    public PaginationOption() {
    }

    public PaginationOption(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public static int getDefaultOffset(){
        return 0;
    }
    public static int getDefaultLimit() {
        return 100;
    }
}
