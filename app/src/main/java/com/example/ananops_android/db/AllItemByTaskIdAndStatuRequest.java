package com.example.ananops_android.db;

public class AllItemByTaskIdAndStatuRequest {

    /**
     * itemName : string
     * maintainerId : 0
     * orderBy : string
     * pageNum : 0
     * pageSize : 0
     * status : 1
     * taskId : 831220856171532288
     * userId : 0
     */

    private String orderBy;
    private int pageNum;
    private int pageSize;
    private String status;
    private String taskId;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
