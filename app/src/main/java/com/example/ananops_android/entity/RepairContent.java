package com.example.ananops_android.entity;

public class RepairContent {


    /**
     * id : 312
     * version : null
     * creator : null
     * creatorId : null
     * createdTime : null
     * lastOperator : null
     * lastOperatorId : null
     * updateTime : null
     * pageNum : null
     * pageSize : null
     * orderBy : null
     * userId : 222
     * title : faasg
     * principalId : 11
     * projectId : 4
     * facilitatorId : 444
     * maintainerId : 41421342
     * scheduledFinishTime : null
     * actualFinishTime : 1563398825000
     * scheduledStartTime : null
     * actualStartTime : 1576618036000
     * deadline : null
     * requestLatitude : null
     * requestLongitude : null
     * status : 4
     * totalCost : 324
     * clearingForm : 1
     * creator_call : 231244
     * address_name : faasgfas
     * contractId : 2314
     * level : 4
     * appointTime : 2019-12-18 05:28:48
     * suggestion : null
     * result : null
     */

    private String id;
    private String userId;
    private String title;
    private int status;
    private String address_name;
    private Long contractId;
    private int level;
    private String appointTime;
    private Long projectId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
