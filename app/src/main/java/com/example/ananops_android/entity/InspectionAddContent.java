package com.example.ananops_android.entity;

import java.util.List;

public class InspectionAddContent {

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
    private int frequency;
    private Long id;
    private int inspectionType;
    private String location;
    private int maintenanceCost;
    private Long principalId;
    private Long projectId;
    private String remark;
    private String scheduledStartTime;
    private int status;
    private String taskName;
    private Double totalCost;
    private Long userId;
    private List<InspectionTaskItem> imcAddInspectionItemDtoList;

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

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(int inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(int maintenanceCost) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<InspectionTaskItem> getImcAddInspectionItemDtoList() {
        return imcAddInspectionItemDtoList;
    }

    public void setImcAddInspectionItemDtoList(List<InspectionTaskItem> imcAddInspectionItemDtoList) {
        this.imcAddInspectionItemDtoList = imcAddInspectionItemDtoList;
    }

    public static class ImcAddInspectionItemDtoListBean {
        /**
         * actualFinishTime : 2019-8-24 11:11:11
         * actualStartTime : 2019-8-24 11:11:11
         * count : 0
         * days : 0
         * description : string
         * frequency : 0
         * id : 0
         * inspectionTaskId : 0
         * itemLatitude : 0
         * itemLongitude : 0
         * itemName : string
         * maintainerId : 0
         * result : string
         * scheduledStartTime : 2019-8-24 11:11:11
         * status : 0
         * userId : 0
         */

        private String actualFinishTime;
        private String actualStartTime;
        private int count;
        private Integer days;
        private String description;
        private int frequency;
        private Long id;
        private Long inspectionTaskId;
        private Double itemLatitude;
        private Double itemLongitude;
        private String itemName;
        private Long maintainerId;
        private String result;
        private String scheduledStartTime;
        private int status;
        private Long userId;

        public String getActualFinishTime() {
            return actualFinishTime;
        }

        public void setActualFinishTime(String actualFinishTime) {
            this.actualFinishTime = actualFinishTime;
        }

        public String getActualStartTime() {
            return actualStartTime;
        }

        public void setActualStartTime(String actualStartTime) {
            this.actualStartTime = actualStartTime;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Integer getDays() {
            return days;
        }

        public void setDays(Integer days) {
            this.days = days;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
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

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public Long getMaintainerId() {
            return maintainerId;
        }

        public void setMaintainerId(Long maintainerId) {
            this.maintainerId = maintainerId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getScheduledStartTime() {
            return scheduledStartTime;
        }

        public void setScheduledStartTime(String scheduledStartTime) {
            this.scheduledStartTime = scheduledStartTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }
}
