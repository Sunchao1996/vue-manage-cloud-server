package com.sc.util.page;


import com.sc.util.global.GlobalConst;

/**
 * 分页查询类的父类
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class PageSearchVO {
    private int pageIndex = 1;//当前页，默认第一页
    private int pageSize = GlobalConst.PAGESIZE;//每页记录数，默认全局变量
    private String dataRange;//数据访问权限

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getDataRange() {
        return dataRange;
    }

    public void setDataRange(String dataRange) {
        this.dataRange = dataRange;
    }
}
