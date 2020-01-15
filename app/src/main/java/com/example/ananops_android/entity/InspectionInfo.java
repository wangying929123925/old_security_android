package com.example.ananops_android.entity;

public class InspectionInfo {

    /**
     * createdTime : 2019-12-28T01:42:04.527Z
     * creator : string
     * creatorId : 0
     * cycleTime : 0
     * deadlineTime : 2019-12-01 12:18:48
     * dealResult : string
     * description : string
     * id : 0
     * inspectionCondition : string
     * inspectionContent : string
     * isNow : 0
     * lastOperator : string
     * lastOperatorId : 0
     * orderBy : string
     * pageNum : 0
     * pageSize : 0
     * projectId : 0
     * projectName : string
     * scheduledFinishTime : 0
     * scheduledStartTime : 2019-12-01 12:18:48
     * taskName : string
     * taskType : string
     * updateTime : 2019-12-28T01:42:04.527Z
     * version : 0
     */

    private String createdTime;
    private String creator;
    private Long creatorId;
    private Integer cycleTime;
    private String deadlineTime;
    private String dealResult;
    private String description;
    private Long id;
    private String inspectionCondition;
    private String inspectionContent;
    private int isNow;
    private String lastOperator;
    private Long lastOperatorId;
    private String orderBy;
    private Long projectId;
    private String projectName;
    private String scheduledFinishTime;
    private String scheduledStartTime;
    private String taskName;
    private String taskType;
    private String updateTime;
    /**
     * id : 799909594993266688
     * version : null
     * creatorId : 782515618032134145
     * lastOperatorId : 782524300140750849
     * pageNum : null
     * pageSize : null
     * orderBy : null
     * principalId : 1
     * facilitatorId : 4
     * projectId : 1
     * location : null
     * status : 3
     * totalCost : 100
     * maintenanceCost : null
     * actualFinishTime : null
     * days : 90
     * inspectionType : 1
     * remark : string
     * frequency : 30
     */

    private Long principalId;
    private Long facilitatorId;
    private Object location;
    private Integer status;
    private Float totalCost;
    private Object maintenanceCost;
    private Object actualFinishTime;
    private Integer days;
    private Integer inspectionType;
    private String remark;
    private Integer frequency;


    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
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

    public Integer getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(Integer cycleTime) {
        this.cycleTime = cycleTime;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInspectionCondition() {
        return inspectionCondition;
    }

    public void setInspectionCondition(String inspectionCondition) {
        this.inspectionCondition = inspectionCondition;
    }

    public String getInspectionContent() {
        return inspectionContent;
    }

    public void setInspectionContent(String inspectionContent) {
        this.inspectionContent = inspectionContent;
    }

    public int getIsNow() {
        return isNow;
    }

    public void setIsNow(int isNow) {
        this.isNow = isNow;
    }

    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

    public Long getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Long lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getScheduledFinishTime() {
        return scheduledFinishTime;
    }

    public void setScheduledFinishTime(String scheduledFinishTime) {
        this.scheduledFinishTime = scheduledFinishTime;
    }

    public String getScheduledStartTime() {
        return scheduledStartTime;
    }

    public void setScheduledStartTime(String scheduledStartTime) {
        this.scheduledStartTime = scheduledStartTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Long principalId) {
        this.principalId = principalId;
    }

    public Long getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(Long facilitatorId) {
        this.facilitatorId = facilitatorId;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public Object getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(Object maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public Object getActualFinishTime() {
        return actualFinishTime;
    }

    public void setActualFinishTime(Object actualFinishTime) {
        this.actualFinishTime = actualFinishTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(Integer inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}
