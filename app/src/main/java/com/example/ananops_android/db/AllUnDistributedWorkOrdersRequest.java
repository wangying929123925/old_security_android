package com.example.ananops_android.db;

public class AllUnDistributedWorkOrdersRequest {
    /**
     * orderBy : string
     * pageNum : 0
     * pageSize : 100
     */

    private String orderBy;
    private int pageNum;
    private int pageSize;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = "string";
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = 0;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = 100;
    }

}
