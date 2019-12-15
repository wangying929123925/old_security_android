package com.example.ananops_android.entity;


public class RepairListContent {
    private String title;
    private String principalId;
    private String projectId;
    private String facilitatorId;
    private String userId;
    private String totalCost;
    private String payMode;
    private String taskItems;
    private String uid;
   private String status;

   /*       "title": "kk",
            "principalId": 111,
            "projectId": 1,
            "facilitatorId": 333,
            "userId": null,
            "totalCost": 3.000,
            "payMode": 1,
            "taskItems": null,
            "status": null,
            "uid": null*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(String facilitatorId) {
        this.facilitatorId = facilitatorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getTaskItems() {
        return taskItems;
    }

    public void setTaskItems(String taskItems) {
        this.taskItems = taskItems;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return "维修中";
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
