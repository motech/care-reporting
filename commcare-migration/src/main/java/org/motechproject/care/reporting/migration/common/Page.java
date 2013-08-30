package org.motechproject.care.reporting.migration.common;

public class Page {

    private int offset;
    private int limit;

    public Page() {
    }

    public Page(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "Page{" +
                "offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
