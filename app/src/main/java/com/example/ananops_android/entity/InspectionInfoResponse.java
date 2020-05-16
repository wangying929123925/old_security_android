package com.example.ananops_android.entity;

public class InspectionInfoResponse {

        /**
         * actualFinishTime : 2020-05-08T07:00:44.463Z
         * alreadyPoint : 0
         * content : string
         * days : 0
         * facilitatorId : 0
         * facilitatorName : string
         * frequency : 0
         * id : 0
         * inspectionType : 0
         * location : string
         * maintenanceCost : 0
         * pointSum : 0
         * principalId : 0
         * principalName : string
         * projectId : 0
         * projectName : string
         * remark : string
         * scheduledStartTime : 2020-05-08T07:00:44.463Z
         * status : 0
         * taskName : string
         * totalCost : 0
         */

        private String actualFinishTime;
        private int alreadyPoint;
        private String content;
        private int days;
        private Long facilitatorId;
        private String facilitatorName;
        private int frequency;
        private Long id;
        private int inspectionType;
        private String location;
        private Double maintenanceCost;
        private int pointSum;
        private Long principalId;
        private String principalName;
        private Long projectId;
        private String projectName;
        private String remark;
        private String scheduledStartTime;
        private int status;
        private String taskName;
        private Double totalCost;

        public String getActualFinishTime() {
            return actualFinishTime;
        }

        public void setActualFinishTime(String actualFinishTime) {
            this.actualFinishTime = actualFinishTime;
        }

        public int getAlreadyPoint() {
            return alreadyPoint;
        }

        public void setAlreadyPoint(int alreadyPoint) {
            this.alreadyPoint = alreadyPoint;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public Long getFacilitatorId() {
            return facilitatorId;
        }

        public void setFacilitatorId(Long facilitatorId) {
            this.facilitatorId = facilitatorId;
        }

        public String getFacilitatorName() {
            return facilitatorName;
        }

        public void setFacilitatorName(String facilitatorName) {
            this.facilitatorName = facilitatorName;
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

        public Double getMaintenanceCost() {
            return maintenanceCost;
        }

        public void setMaintenanceCost(Double maintenanceCost) {
            this.maintenanceCost = maintenanceCost;
        }

        public int getPointSum() {
            return pointSum;
        }

        public void setPointSum(int pointSum) {
            this.pointSum = pointSum;
        }

        public Long getPrincipalId() {
            return principalId;
        }

        public void setPrincipalId(Long principalId) {
            this.principalId = principalId;
        }

        public String getPrincipalName() {
            return principalName;
        }

        public void setPrincipalName(String principalName) {
            this.principalName = principalName;
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
    }
