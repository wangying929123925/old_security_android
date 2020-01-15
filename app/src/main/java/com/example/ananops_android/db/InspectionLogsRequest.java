package com.example.ananops_android.db;

public class InspectionLogsRequest {

    /**
     * orderBy : string
     * pageNum : 0
     * pageSize : 0
     * taskId : 123456789
     */

    private String orderBy;
    private Integer pageNum;
    private Integer pageSize;
    private Long taskId;

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
        this.pageNum = 0;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = 0;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
