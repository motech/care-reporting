package org.motechproject.care.reporting.migration.common;

public class PaginationOption {

    private int limit = 100;
    private int offset = 0;

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
}
