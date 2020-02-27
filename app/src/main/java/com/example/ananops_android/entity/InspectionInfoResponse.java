package com.example.ananops_android.entity;

public class InspectionInfoResponse {

    /**
     * id : 805555020551426048
     * version : null
     * creator : 杨克样
     * creatorId : 782516789266360321
     * createdTime : 2020-01-17 10:54:02
     * lastOperator : 杨克样
     * lastOperatorId : 782516789266360321
     * updateTime : 2020-02-25 15:47:54
     * pageNum : null
     * pageSize : null
     * orderBy : null
     * principalId : 148202157630162810
     * facilitatorId : 4
     * projectId : 805494383683049472
     * location : null
     * status : 2
     * totalCost : null
     * maintenanceCost : null
     * scheduledStartTime : 2020-01-18 00:00:00
     * actualFinishTime : 2020-02-23 13:25:09
     * days : 90
     * inspectionType : 1
     * remark : null
     * taskName : 安安运维巡检任务测试2020-1-17
     * frequency : 30
     */

    private Long id;
    private String version;
    private String creator;
    private Long creatorId;
    private String createdTime;
    private String lastOperator;
    private String lastOperatorId;
    private String updateTime;
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private Long principalId;
    private String facilitatorId;
    private Long projectId;
    private String location;
    private int status;
    private Float totalCost;
    private Float maintenanceCost;
    private String scheduledStartTime;
    private String actualFinishTime;
    private int days;
    private int inspectionType;
    private String remark;
    private String taskName;
    private int frequency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

    public String getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(String lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Long getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Long principalId) {
        this.principalId = principalId;
    }

    public String getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(String facilitatorId) {
        this.facilitatorId = facilitatorId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public Float getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(Float maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public String getScheduledStartTime() {
        return scheduledStartTime;
    }

    public void setScheduledStartTime(String scheduledStartTime) {
        this.scheduledStartTime = scheduledStartTime;
    }

    public String getActualFinishTime() {
        return actualFinishTime;
    }

    public void setActualFinishTime(String actualFinishTime) {
        this.actualFinishTime = actualFinishTime;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(int inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
