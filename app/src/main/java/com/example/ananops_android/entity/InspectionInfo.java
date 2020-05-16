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
    private int pointSum;
    private int alreadyPoint;


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

    public int getPointSum() {
        return pointSum;
    }

    public void setPointSum(int pointSum) {
        this.pointSum = pointSum;
    }

    public int getAlreadyPoint() {
        return alreadyPoint;
    }

    public void setAlreadyPoint(int alreadyPoint) {
        this.alreadyPoint = alreadyPoint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createdTime);
        dest.writeString(this.creator);
        dest.writeLong(this.creatorId);
        dest.writeInt(this.cycleTime);
        dest.writeString(this.deadlineTime);
        dest.writeString(this.dealResult);
        dest.writeString(this.description);
        dest.writeValue(this.id);
        dest.writeString(this.inspectionCondition);
        dest.writeString(this.inspectionContent);
        dest.writeInt(this.isNow);
        dest.writeString(this.lastOperator);
        dest.writeLong(this.lastOperatorId);
        dest.writeString(this.orderBy);
        dest.writeLong(this.projectId);
        dest.writeString(this.projectName);
        dest.writeString(this.scheduledFinishTime);
        dest.writeString(this.scheduledStartTime);
        dest.writeString(this.taskName);
        dest.writeString(this.taskType);
        dest.writeString(this.updateTime);
        dest.writeLong(this.principalId);
        dest.writeLong(this.facilitatorId);
        dest.writeString(this.location);
        dest.writeInt(this.status);
        dest.writeFloat(this.totalCost);
        dest.writeFloat(this.maintenanceCost);
        dest.writeString(this.actualFinishTime);
        dest.writeInt(this.days);
        dest.writeInt(this.inspectionType);
        dest.writeString(this.remark);
        dest.writeInt(this.frequency);
        dest.writeInt(this.pointSum);
        dest.writeInt(this.alreadyPoint);
    }

    public InspectionInfo() {
    }

    protected InspectionInfo(Parcel in) {
        this.createdTime = in.readString();
        this.creator = in.readString();
        this.creatorId = in.readLong();
        this.cycleTime = in.readInt();
        this.deadlineTime = in.readString();
        this.dealResult = in.readString();
        this.description = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.inspectionCondition = in.readString();
        this.inspectionContent = in.readString();
        this.isNow = in.readInt();
        this.lastOperator = in.readString();
        this.lastOperatorId = in.readLong();
        this.orderBy = in.readString();
        this.projectId = in.readLong();
        this.projectName = in.readString();
        this.scheduledFinishTime = in.readString();
        this.scheduledStartTime = in.readString();
        this.taskName = in.readString();
        this.taskType = in.readString();
        this.updateTime = in.readString();
        this.principalId = in.readLong();
        this.facilitatorId = in.readLong();
        this.location = in.readString();
        this.status = in.readInt();
        this.totalCost = in.readFloat();
        this.maintenanceCost = in.readFloat();
        this.actualFinishTime = in.readString();
        this.days = in.readInt();
        this.inspectionType = in.readInt();
        this.remark = in.readString();
        this.frequency = in.readInt();
        this.pointSum = in.readInt();
        this.alreadyPoint = in.readInt();
    }

    public static final Creator<InspectionInfo> CREATOR = new Creator<InspectionInfo>() {
        @Override
        public InspectionInfo createFromParcel(Parcel source) {
            return new InspectionInfo(source);
        }

        @Override
        public InspectionInfo[] newArray(int size) {
            return new InspectionInfo[size];
        }
    };
}


