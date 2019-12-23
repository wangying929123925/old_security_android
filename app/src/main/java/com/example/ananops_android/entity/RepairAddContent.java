package com.example.ananops_android.entity;

import java.util.List;

public class RepairAddContent {
    private String addressName;//故障位置
    private String appoint_time;//预约时间
    private String contractId;//
    private String created_time;//创建时间
    private String creator;//创建人
    private String creator_call;//电话
    private String  facilitatorId;//服务商ID
    private String level;//等级
    private int payMode;
    private int principalId;
    private String projectId;//项目ID
    private int status;
    private String title;
    private float totalCost;
    private String userId;//用户ID
    private String uid;

    private List<TaskItemDtoListBean> taskItemDtoList;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAppoint_time() {
        return appoint_time;
    }

    public void setAppoint_time(String appoint_time) {
        this.appoint_time = appoint_time;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator_call() {
        return creator_call;
    }

    public void setCreator_call(String creator_call) {
        this.creator_call = creator_call;
    }

    public String getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(String facilitatorId) {
        this.facilitatorId = facilitatorId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getPayMode() {
        return payMode;
    }

    public void setPayMode(int payMode) {
        this.payMode = payMode;
    }

    public int getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(int principalId) {
        this.principalId = principalId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<TaskItemDtoListBean> getTaskItemDtoList() {
        return taskItemDtoList;
    }

    public void setTaskItemDtoList(List<TaskItemDtoListBean> taskItemDtoList) {
        this.taskItemDtoList = taskItemDtoList;
    }

    public static class TaskItemDtoListBean {
        /**
         * description : string
         * deviceId : 0
         * deviceLatitude : 0
         * deviceLongitude : 0
         * deviceName : string
         * level : string
         * photo_url : string
         * taskId : 0
         * trouble_type : 0
         */

        private String description;//描述
        private String deviceId;//设备id
        private Double deviceLatitude;//j
        private Double deviceLongitude;//
        private String deviceName;//
        private String level;//故障等级
        private String photo_url;//
        private String trouble_type;//故障类型
        private String taskId;

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }

        public String getTrouble_type() {
            return trouble_type;
        }

        public void setTrouble_type(String trouble_type) {
            this.trouble_type = trouble_type;
        }
    }

}
