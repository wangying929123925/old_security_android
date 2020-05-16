package com.example.ananops_android.db;

public class AllAcceptedItemByMaintainerRequest {

    /**
     * orderBy : string
     * pageSize : 100
     * pageNum : 0
     * maintainerId : 782526720958801921
     */

    private String orderBy;
    private int pageSize;
    private int pageNum;
    private Long maintainerId;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = "string";
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = 100;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = 0;
    }

    public Long getMaintainerId() {
        return maintainerId;
    }

    public void setMaintainerId(Long maintainerId) {
        this.maintainerId = maintainerId;
    }

}
