package com.example.ananops_android.entity;

import java.util.Date;
import java.util.List;

public class RepairAddContent {
    /**
     * appointTime : 2019-12-23T09:13:51.064Z
     * contractId : 0
     * facilitatorId : 0
     * id : null
     * level : 0
     * mdmcAddTaskItemDtoList : [{"description":"string","deviceId":0,"deviceLatitude":0,"deviceLongitude":0,"deviceType":"string","id":0,"level":"string","taskId":0,"troubleType":0}]
     * principalId : 0
     * projectId : 0
     * result : 0
     * suggestion : string
     * title : string
     * totalCost : 0
     * userId : 0
     */

    private String appointTime;
    private Long contractId;
    private Long facilitatorId;
    private Long id;
    private Integer level;
    private Long principalId;
    private Long projectId;
    private Integer result;
    private String suggestion;
    private String title;
    private Double totalCost;
    private Long userId;
    private List<MdmcAddTaskItemDtoListBean> mdmcAddTaskItemDtoList;

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(Long facilitatorId) {
        this.facilitatorId = facilitatorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<MdmcAddTaskItemDtoListBean> getMdmcAddTaskItemDtoList() {
        return mdmcAddTaskItemDtoList;
    }

    public void setMdmcAddTaskItemDtoList(List<MdmcAddTaskItemDtoListBean> mdmcAddTaskItemDtoList) {
        this.mdmcAddTaskItemDtoList = mdmcAddTaskItemDtoList;
    }

    public static class MdmcAddTaskItemDtoListBean {
        /**
         * description : string
         * deviceId : 0
         * deviceLatitude : 0
         * deviceLongitude : 0
         * deviceType : string
         * id : 0
         * level : string
         * taskId : 0
         * troubleType : 0
         */

        private String description;
        private Long deviceId;
        private Double deviceLatitude;
        private Double deviceLongitude;
        private String deviceType;
        private Long id;
        private String level;
        private Long taskId;
        private Integer troubleType;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Double getDeviceLatitude() {
            return deviceLatitude;
        }

        public void setDeviceLatitude(Double deviceLatitude) {
            this.deviceLatitude = deviceLatitude;
        }

        public Double getDeviceLongitude() {
            return deviceLongitude;
        }

        public void setDeviceLongitude(Double deviceLongitude) {
            this.deviceLongitude = deviceLongitude;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }


        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public Long getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(Long deviceId) {
            this.deviceId = deviceId;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getTaskId() {
            return taskId;
        }

        public void setTaskId(Long taskId) {
            this.taskId = taskId;
        }

        public Integer getTroubleType() {
            return troubleType;
        }

        public void setTroubleType(Integer troubleType) {
            this.troubleType = troubleType;
        }
    }

    /**
     * appointTime : 2019-12-23T09:13:51.064Z
     * contractId : 0
     * facilitatorId : 0
     * id : 0
     * level : 0
     * mdmcAddTaskItemDtoList : [{"description":"string","deviceId":0,"deviceLatitude":0,"deviceLongitude":0,"deviceType":"string","id":0,"level":"string","taskId":0,"troubleType":0}]
     * principalId : 0
     * projectId : 0
     * result : 0
     * suggestion : string
     * title : string
     * totalCost : 0
     * userId : 0
     */

}
