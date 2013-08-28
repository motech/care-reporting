package org.motechproject.care.reporting.migration.common;

public class PaginatedResponseMeta {
    private final Page currentPage;
    private final Page nextPage;
    private final Page previousPage;
    private int totalCount;

    public PaginatedResponseMeta(Page currentPage, Page nextPage, Page previousPage, int totalCount) {
        this.currentPage = currentPage;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PaginatedResponseMeta{" +
                "currentPage=" + currentPage +
                ", nextPage=" + nextPage +
                ", previousPage=" + previousPage +
                ", totalCount=" + totalCount +
                '}';
    }

    public Page getNextPage() {
        return nextPage;
    }

    public Page getPreviousPage() {
        return previousPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public Page getCurrentPage() {
        return currentPage;
    }
}
