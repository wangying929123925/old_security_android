package com.example.ananops_android.db;

public class InspectionListByUserIdAndStatusRequest {

    /**
     * orderBy : string
     * pageNum : 0
     * pageSize : 0
     * projectId : 0
     * role : 1
     * status : 2
     * userId : 782516789266360321
     */

    private String orderBy;
    private int pageNum;
    private int pageSize;
    private Long projectId;
    private int role;
    private int status;
    private Long userId;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
