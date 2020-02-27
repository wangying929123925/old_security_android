package com.example.ananops_android.db;

public class InspectionItemLogsRequest {

    /**
     * itemId : 831220856213475328
     * orderBy : string
     * pageNum : 0
     * pageSize : 0
     */

    private Long itemId;
    private String orderBy;
    private int pageNum;
    private int pageSize;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

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
        this.pageSize = 0;
    }
}
