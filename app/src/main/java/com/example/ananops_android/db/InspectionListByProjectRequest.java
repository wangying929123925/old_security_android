package com.example.ananops_android.db;

public class InspectionListByProjectRequest {

    /**
     * projectId : 1
     */

    private Long projectId;
    /**
     * orderBy : string
     * pageNum : 0
     * pageSize : 0
     * projectId : 0
     * role : 0
     * status : 0
     * taskName : string
     * userId : 0
     */

    private String orderBy;
    private int pageNum;
    private int pageSize;
    private int role;
    private String taskName;
    private Long userId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
