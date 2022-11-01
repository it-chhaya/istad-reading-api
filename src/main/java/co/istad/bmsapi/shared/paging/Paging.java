package co.istad.bmsapi.shared.paging;

import lombok.ToString;

@ToString
public class Paging {

    private Long page;
    private Long limit;
    private Long offset;
    private Long nextPage;
    private Long previousPage;
    private Long totalCount;
    private Long totalPages;
    private Long pagesToShow;
    private Long startPage;
    private Long endPage;

    public Paging() {
        this(1L, 20L, 0L, 0L, 8L);
    }

    public Paging(Long page, Long limit, Long totalCount, Long totalPages, Long pagesToShow) {
        this.page = page;
        this.limit = limit;
        this.totalCount = totalCount;
        this.totalPages = totalPages;
        this.pagesToShow = pagesToShow;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long currentPage) {
        this.page = currentPage <= 1 ? 1 : currentPage;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getTotalPages() {
        return (long) Math.ceil((double) this.totalCount / limit);
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getOffset() {
        return (this.page - 1) * this.limit;
    }

    public long getNextPage() {
        return page >= getTotalPages() ? getTotalPages() : page + 1;
    }

    public long getPreviousPage() {
        return (page <= 1) ? 1 : page - 1;
    }

    public long getStartPage() {
        return startPage;
    }

    public long getEndPage() {
        return endPage;
    }

    public long getPagesToShow() {
        return pagesToShow;
    }

    public void setPagesToShow(Long pagesToShow) {
        this.pagesToShow = pagesToShow;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
        this.setStartPageEndPage(getTotalPages());
    }

    private void setStartPageEndPage(Long totalPages) {

        Long halfPagesToShow = pagesToShow / 2;

        if (totalPages <= pagesToShow) {
            startPage = 1L;
            endPage = totalPages;
        } else if (page - halfPagesToShow <= 0) {
            startPage = 1L;
            endPage = pagesToShow;
        } else if (page + halfPagesToShow == totalPages) {
            startPage = page - halfPagesToShow;
            endPage = totalPages;
        } else if (page + halfPagesToShow > totalPages) {
            startPage = totalPages - pagesToShow + 1;
            endPage = totalPages;
        } else {
            startPage = page - halfPagesToShow;
            endPage = page + halfPagesToShow;
        }
    }

}
