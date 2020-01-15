package com.example.ananops_android.db;

public class InspectionItemListImcRequest {

    /**
     * itemName : string
     * maintainerId : 0
     * orderBy : string
     * pageNum : 0
     * pageSize : 0
     * status : 0
     * taskId : 799909594993266688
     * userId : 0
     */

    private String itemName;
    private Long maintainerId;
    private String orderBy;
    private int pageNum;
    private int pageSize;
    private int status;
    private long taskId;
    private int userId;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = "string";
    }

    public Long getMaintainerId() {
        return maintainerId;
    }

    public void setMaintainerId(Long maintainerId) {
        this.maintainerId = 0L;
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
        this.pageNum = 0;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = 0;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = 0;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
