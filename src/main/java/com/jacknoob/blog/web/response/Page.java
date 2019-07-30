package com.jacknoob.blog.web.response;

import java.util.List;

/**
 * @author JackJun
 * 2019/7/4 20:11
 * Life is not just about survival.
 */
public class Page<T> {
    private List<T> data;
    private int total;
    private int pageSize;
    private int page;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
