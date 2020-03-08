package com.example.ananops_android.entity;

import java.util.List;

public class RepairAddContent {

    /**
     * addressName : string
     * appointTime : 2020-01-16T22:38:17.128Z
     * call : string
     * contractId : 0
     * deadline : 2020-01-16T22:38:17.128Z
     * facilitatorId : 0
     * id : 0
     * level : 0
     * maintainerId : 0
     * mdmcAddTaskItemDtoList : [{"description":"string","deviceId":0,"deviceLatitude":0,"deviceLongitude":0,"deviceName":"string","deviceType":"string","id":0,"level":"string","taskId":0,"troubleType":0}]
     * note : string
     * objectId : 0
     * objectType : 0
     * principalId : 0
     * projectId : 0
     * requestLatitude : 0
     * requestLongitude : 0
     * result : 0
     * scheduledFinishTime : 2020-01-16T22:38:17.128Z
     * scheduledStartTime : 2020-01-16T22:38:17.128Z
     * status : 0
     * suggestion : string
     * title : string
     * totalCost : 0
     * userId : 0
     */

    private String addressName;
    private String appointTime;
    private String call;
    private Long contractId;
    private String deadline;
    private Long facilitatorId;
    private Long id;
    private int level;
    private Long maintainerId;
    private String note;
    private Long objectId;
    private int objectType;
    private Long principalId;
    private Long projectId;
    private Double requestLatitude;
    private Double requestLongitude;
    private int result;
    private String scheduledFinishTime;
    private String scheduledStartTime;
    private int status;
    private String suggestion;
    private String title;
    private int totalCost;
    private Long userId;
    private List<MdmcAddTaskItemDtoListBean> mdmcAddTaskItemDtoList;
    private List<Long> attachmentIdList;

    public List<Long> getAttachmentIdList() {
        return attachmentIdList;
    }

    public void setAttachmentIdList(List<Long> attachmentIdList) {
        this.attachmentIdList = attachmentIdList;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Long getMaintainerId() {
        return maintainerId;
    }

    public void setMaintainerId(Long maintainerId) {
        this.maintainerId = maintainerId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
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

    public Double getRequestLatitude() {
        return requestLatitude;
    }

    public void setRequestLatitude(Double requestLatitude) {
        this.requestLatitude = requestLatitude;
    }

    public Double getRequestLongitude() {
        return requestLongitude;
    }

    public void setRequestLongitude(Double requestLongitude) {
        this.requestLongitude = requestLongitude;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
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
         * deviceName : string
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
        private String deviceName;
        private String deviceType;
        private Long id;
        private String level;
        private Long taskId;
        private int troubleType;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Long getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(Long deviceId) {
            this.deviceId = deviceId;
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

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public Long getTaskId() {
            return taskId;
        }

        public void setTaskId(Long taskId) {
            this.taskId = taskId;
        }

        public int getTroubleType() {
            return troubleType;
        }

        public void setTroubleType(int troubleType) {
            this.troubleType = troubleType;
        }
    }
}
