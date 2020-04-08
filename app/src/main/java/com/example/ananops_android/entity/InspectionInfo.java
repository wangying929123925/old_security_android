package com.example.ananops_android.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class InspectionInfo implements Parcelable {

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
    private long creatorId;
    private int cycleTime;
    private String deadlineTime;
    private String dealResult;
    private String description;
    private Long id;
    private String inspectionCondition;
    private String inspectionContent;
    private int isNow;
    private String lastOperator;
    private long lastOperatorId;
    private String orderBy;
    private long projectId;
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

    private long principalId;
    private long facilitatorId;
    private String location;
    private int status;
    private float totalCost;
    private float maintenanceCost;
    private String actualFinishTime;
    private int days;
    private int inspectionType;
    private String remark;
    private int frequency;


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

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public int getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(int cycleTime) {
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

    public long getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(long lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
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

    public long getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(long principalId) {
        this.principalId = principalId;
    }

    public long getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(long facilitatorId) {
        this.facilitatorId = facilitatorId;
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

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(float maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
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

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createdTime);
        dest.writeString(creator);
        dest.writeLong(creatorId);
        dest.writeInt(cycleTime);
        dest.writeString(deadlineTime);
        dest.writeString(dealResult);
        dest.writeString(description);
        dest.writeLong(id);
        dest.writeString(inspectionCondition);
        dest.writeString(inspectionContent);
        dest.writeInt(isNow);
        dest.writeString(lastOperator);
        dest.writeLong(lastOperatorId);
        dest.writeString(orderBy);
        dest.writeLong(projectId);
        dest.writeString(projectName) ;
        dest.writeString(scheduledFinishTime);
        dest.writeString(scheduledStartTime);
        dest.writeString(taskName);
        dest.writeString(taskType);
        dest.writeString(updateTime);
        dest.writeLong(principalId);
        dest.writeLong(facilitatorId);
        dest.writeString(location);
        dest.writeInt( status);
        dest.writeFloat(totalCost);
        dest.writeFloat(maintenanceCost);
        dest.writeString(actualFinishTime);
        dest.writeInt(days);
        dest.writeInt(inspectionType);
        dest.writeString(remark);
        dest.writeInt(frequency);
    }

    public static final Parcelable.Creator<InspectionInfo> CREATOR = new Creator<InspectionInfo>() {
        @Override
        public InspectionInfo createFromParcel(Parcel source) {
            InspectionInfo inspectionInfo = new InspectionInfo();
            inspectionInfo.setCreatedTime(source.readString());
            inspectionInfo.setCreator(source.readString());
            inspectionInfo.setCreatorId(source.readLong());
            inspectionInfo.setCycleTime(source.readInt());
            inspectionInfo.setDeadlineTime(source.readString());
            inspectionInfo.setDealResult(source.readString());
            inspectionInfo.setDescription(source.readString());
            inspectionInfo.setId(source.readLong());
            inspectionInfo.setInspectionCondition(source.readString());
            inspectionInfo.setInspectionContent(source.readString());
            inspectionInfo.setIsNow(source.readInt());
            inspectionInfo.setLastOperator(source.readString());
            inspectionInfo.setLastOperatorId(source.readLong());
            inspectionInfo.setOrderBy(source.readString());
            inspectionInfo.setProjectId(source.readLong());
            inspectionInfo.setProjectName(source.readString()) ;
            inspectionInfo.setScheduledFinishTime(source.readString());
            inspectionInfo.setScheduledStartTime(source.readString());
            inspectionInfo.setTaskName(source.readString());
            inspectionInfo.setTaskType(source.readString());
            inspectionInfo.setUpdateTime(source.readString());
            inspectionInfo.setPrincipalId(source.readLong());
            inspectionInfo.setFacilitatorId(source.readLong());
            inspectionInfo.setLocation(source.readString());
            inspectionInfo.setStatus(source.readInt());
            inspectionInfo.setTotalCost(source.readFloat());
            inspectionInfo.setMaintenanceCost(source.readFloat());
            inspectionInfo.setActualFinishTime(source.readString());
            inspectionInfo.setDays(source.readInt());
            inspectionInfo.setInspectionType(source.readInt());
            inspectionInfo.setRemark(source.readString());
            inspectionInfo.setFrequency(source.readInt());
            return inspectionInfo;
        }

        @Override
        public InspectionInfo[] newArray(int size) {
            return new InspectionInfo[size];
        }
    };
}


