package com.example.ananops_android.entity;

import com.google.gson.annotations.SerializedName;

public class InspectionTaskItem {
    /**
     * createdTime : 2019-12-28T01:42:04.513Z
     * creator : string
     * creatorId : 0
     * description : string
     * id : 0
     * inspectionTaskId : 0
     * inspectionTaskName : string
     * itemName : string
     * lastOperator : string
     * lastOperatorId : 0
     * maintainerId : 0
     * maintainerName : string
     * name : string
     * orderBy : string
     * pageNum : 0
     * pageSize : 0
     * remark : string
     * result : string
     * status : 0
     * updateTime : 2019-12-28T01:42:04.513Z
     * version : 0
     */

    private String createdTime;
    private String creator;
    private Long creatorId;
    private String description;
    private Long id;
    private Long inspectionTaskId;
    private String inspectionTaskName;
    private String itemName;
    private String lastOperator;
    private Long lastOperatorId;
    private Long maintainerId;
    private String maintainerName;
    private String name;
    private String orderBy;
    private int pageNum;
    private int pageSize;
    private String remark;
    private String result;
    private int status;
    private String updateTime;
    private int version;
    /**
     * id : 799909602912112640
     * version : null
     * creatorId : 782515618032134145
     * lastOperatorId : 782515618032134145
     * pageNum : null
     * pageSize : null
     * orderBy : null
     * inspectionTaskId : 799909594993266688
     * scheduledStartTime : 2019-12-19 08:18:48
     * actualStartTime : null
     * actualFinishTime : null
     * days : 90
     * frequency : 30
     * itemLatitude : 1
     * itemLongitude : 1
     * result : null
     * maintainerId : 232423423
     * count : 0
     */

    private String scheduledStartTime;
    private String actualStartTime;
    private String actualFinishTime;
    private Integer days;
    private Integer frequency;
    private Double itemLatitude;
    private Double itemLongitude;
    private Float count;

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

    public Long getInspectionTaskId() {
        return inspectionTaskId;
    }

    public void setInspectionTaskId(Long inspectionTaskId) {
        this.inspectionTaskId = inspectionTaskId;
    }

    public String getInspectionTaskName() {
        return inspectionTaskName;
    }

    public void setInspectionTaskName(String inspectionTaskName) {
        this.inspectionTaskName = inspectionTaskName;
    }

    public String getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(String actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public String getActualFinishTime() {
        return actualFinishTime;
    }

    public void setActualFinishTime(String actualFinishTime) {
        this.actualFinishTime = actualFinishTime;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public Long getMaintainerId() {
        return maintainerId;
    }

    public void setMaintainerId(Long maintainerId) {
        this.maintainerId = maintainerId;
    }

    public String getMaintainerName() {
        return maintainerName;
    }

    public void setMaintainerName(String maintainerName) {
        this.maintainerName = maintainerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getScheduledStartTime() {
        return scheduledStartTime;
    }

    public void setScheduledStartTime(String scheduledStartTime) {
        this.scheduledStartTime = scheduledStartTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Double getItemLatitude() {
        return itemLatitude;
    }

    public void setItemLatitude(Double itemLatitude) {
        this.itemLatitude = itemLatitude;
    }

    public Double getItemLongitude() {
        return itemLongitude;
    }

    public void setItemLongitude(Double itemLongitude) {
        this.itemLongitude = itemLongitude;
    }

    public Float getCount() {
        return count;
    }

    public void setCount(Float count) {
        this.count = count;
    }
}
