package com.example.ananops_android.db;

public class AllUnDistributedWorkOrdersRequest {

    /**
     * pageNum : 0
     * pageSize : 0
     * type : inspection
     */

    private Integer pageNum;
    private Integer pageSize;
    private String type;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = 0;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = 0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
