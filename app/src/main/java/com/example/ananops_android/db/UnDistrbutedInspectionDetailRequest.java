package com.example.ananops_android.db;

public class UnDistrbutedInspectionDetailRequest {

    /**
     * id : 0
     * orderBy : string
     * pageNum : 0
     * pageSize : 0
     * status : 0
     * type : string
     */

    private Integer id;
    private String orderBy;
    private Integer pageNum;
    private Integer pageSize;
    private int status;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
