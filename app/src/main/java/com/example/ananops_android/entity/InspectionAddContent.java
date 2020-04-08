package com.example.ananops_android.entity;

import java.util.List;

public class InspectionAddContent {

    private List<InspectionTaskItem> imcAddInspectionItemDtoList;
    /**
     * actualFinishTime : 2019-8-24 11:11:11
     * days : 0
     * facilitatorGroupId : 0
     * facilitatorId : 0
     * facilitatorManagerId : 0
     * frequency : 0
     * id : 0
     * imcAddInspectionItemDtoList : [{"actualFinishTime":"2019-8-24 11:11:11","actualStartTime":"2019-8-24 11:11:11","count":0,"days":0,"description":"string","frequency":0,"id":0,"inspectionTaskId":0,"itemLatitude":0,"itemLongitude":0,"itemName":"string","maintainerId":0,"result":"string","scheduledStartTime":"2019-8-24 11:11:11","status":0,"userId":0}]
     * inspectionType : 0
     * location : string
     * loginAuthDto : {"groupId":0,"groupName":"string","loginName":"string","userId":0,"userName":"string"}
     * maintenanceCost : 0
     * principalId : 0
     * projectId : 0
     * remark : string
     * scheduledStartTime : 2019-8-24 11:11:11
     * status : 0
     * taskName : string
     * totalCost : 0
     * userId : 0
     */

    private String actualFinishTime;
    private Integer days;
    private Long facilitatorGroupId;
    private Long facilitatorId;
    private Long facilitatorManagerId;
    private Integer frequency;
    private Long id;
    private Integer inspectionType;
    private String location;
    private Float maintenanceCost;
    private Long principalId;
    private Long projectId;
    private String remark;
    private String scheduledStartTime;
    private Integer status;
    private String taskName;
    private Float totalCost;
    private Long userId;
    public List<InspectionTaskItem> getImcAddInspectionItemDtoList() {
        return imcAddInspectionItemDtoList;
    }

    public void setImcAddInspectionItemDtoList(List<InspectionTaskItem> imcAddInspectionItemDtoList) {
        this.imcAddInspectionItemDtoList = imcAddInspectionItemDtoList;
    }

    public String getActualFinishTime() {
        return actualFinishTime;
    }

    public void setActualFinishTime(String actualFinishTime) {
        this.actualFinishTime = actualFinishTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Long getFacilitatorGroupId() {
        return facilitatorGroupId;
    }

    public void setFacilitatorGroupId(Long facilitatorGroupId) {
        this.facilitatorGroupId = facilitatorGroupId;
    }

    public Long getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(Long facilitatorId) {
        this.facilitatorId = facilitatorId;
    }

    public Long getFacilitatorManagerId() {
        return facilitatorManagerId;
    }

    public void setFacilitatorManagerId(Long facilitatorManagerId) {
        this.facilitatorManagerId = facilitatorManagerId;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(Integer inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(Float maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public Long getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Long principalId) {
        this.principalId = principalId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getScheduledStartTime() {
        return scheduledStartTime;
    }

    public void setScheduledStartTime(String scheduledStartTime) {
        this.scheduledStartTime = scheduledStartTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static class LoginAuthDtoBean {
        /**
         * groupId : 0
         * groupName : string
         * loginName : string
         * userId : 0
         * userName : string
         */

        private Long groupId;
        private String groupName;
        private String loginName;
        private Long userId;
        private String userName;

        public Long getGroupId() {
            return groupId;
        }

        public void setGroupId(Long groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static class ImcAddInspectionItemDtoListBean {
    }
}
