package com.sc.common.page;

import com.sc.util.global.GlobalConst;

/**
 * what:  返回的分页数据
 *
 * @author 孙超 created on 2018/11/11
 */
public class PageDto {
    private Integer pageIndex;
    private Integer total;
    private Integer pageSize;
    private Object data;

    public PageDto(Integer pageIndex, Integer total, Integer pageSize, Object data) {
        this.pageIndex = pageIndex;
        this.total = total;
        this.pageSize = pageSize;
        this.data = data;
    }

    public PageDto(Integer pageIndex, Integer total, Object data) {
        this.pageIndex = pageIndex;
        this.total = total;
        this.pageSize = GlobalConst.PAGESIZE;
        this.data = data;
    }

    @Override
    public String
    toString() {
        return "PageDto{" +
                "pageIndex=" + pageIndex +
                ", total=" + total +
                ", pageSize=" + pageSize +
                ", data=" + data +
                '}';
    }

    public Object getData() {
        return data;
    }

    public PageDto setData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public PageDto setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public PageDto setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public PageDto setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
