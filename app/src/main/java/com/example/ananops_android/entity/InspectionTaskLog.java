package com.example.ananops_android.entity;

public class InspectionTaskLog {

    /**
     * browser : string
     * createdTime : 2020-01-09T14:45:09.324Z
     * creator : string
     * creatorId : 0
     * id : 0
     * ipAddress : string
     * lastOperator : string
     * lastOperatorId : 0
     * movement : string
     * os : string
     * statusTimestamp : 2020-01-09T14:45:09.324Z
     * taskName : string
     * updateTime : 2020-01-09T14:45:09.324Z
     */

    private String browser;
    private String createdTime;
    private String creator;
    private Long creatorId;
    private Long id;
    private String ipAddress;
    private String lastOperator;
    private Long lastOperatorId;
    private String movement;
    private String os;
    private String statusTimestamp;
    private String taskName;
    private String updateTime;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getStatusTimestamp() {
        return statusTimestamp;
    }

    public void setStatusTimestamp(String statusTimestamp) {
        this.statusTimestamp = statusTimestamp;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
