package com.example.ananops_android.db;

public class OrderDetailResponse {

    /**
     * code : 0
     * message : string
     * result : {"actualFinishTime":"2019-12-24T14:40:37.810Z","actualStartTime":"2019-12-24T14:40:37.810Z","address_name":"string","appointTime":"2019-12-24T14:40:37.810Z","call":"string","clearingForm":0,"contractId":0,"createdTime":"2019-12-24T14:40:37.810Z","creator":"string","creatorId":0,"deadline":"2019-12-24T14:40:37.810Z","facilitatorId":0,"id":0,"lastOperator":"string","lastOperatorId":0,"level":0,"maintainerId":0,"orderBy":"string","pageNum":0,"pageSize":0,"principalId":0,"projectId":0,"requestLatitude":0,"requestLongitude":0,"result":0,"scheduledFinishTime":"2019-12-24T14:40:37.810Z","scheduledStartTime":"2019-12-24T14:40:37.810Z","status":0,"suggestion":"string","title":"string","totalCost":0,"updateTime":"2019-12-24T14:40:37.810Z","userId":0,"version":0}
     */

    private String code;
    private String message;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * actualFinishTime : 2019-12-24T14:40:37.810Z
         * actualStartTime : 2019-12-24T14:40:37.810Z
         * address_name : string
         * appointTime : 2019-12-24T14:40:37.810Z
         * call : string
         * clearingForm : 0
         * contractId : 0
         * createdTime : 2019-12-24T14:40:37.810Z
         * creator : string
         * creatorId : 0
         * deadline : 2019-12-24T14:40:37.810Z
         * facilitatorId : 0
         * id : 0
         * lastOperator : string
         * lastOperatorId : 0
         * level : 0
         * maintainerId : 0
         * orderBy : string
         * pageNum : 0
         * pageSize : 0
         * principalId : 0
         * projectId : 0
         * requestLatitude : 0
         * requestLongitude : 0
         * result : 0
         * scheduledFinishTime : 2019-12-24T14:40:37.810Z
         * scheduledStartTime : 2019-12-24T14:40:37.810Z
         * status : 0
         * suggestion : string
         * title : string
         * totalCost : 0
         * updateTime : 2019-12-24T14:40:37.810Z
         * userId : 0
         * version : 0
         */

        private String actualFinishTime;
        private String actualStartTime;
        private String address_name;
        private String appointTime;
        private String call;
        private Integer clearingForm;
        private Long contractId;
        private String createdTime;
        private String creator;
        private Long creatorId;
        private String deadline;
        private Long facilitatorId;
        private String id;
        private String lastOperator;
        private Long lastOperatorId;
        private Integer level;
        private Long maintainerId;
        private String orderBy;
        private int pageNum;
        private int pageSize;
        private Long principalId;
        private Long projectId;
        private Double requestLatitude;
        private Double requestLongitude;
        private String result;
        private String scheduledFinishTime;
        private String scheduledStartTime;
        private Integer status;
        private String suggestion;
        private String title;
        private Double totalCost;
        private String updateTime;
        private Long userId;
        private int version;

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

        public String getAddress_name() {
            return address_name;
        }

        public void setAddress_name(String address_name) {
            this.address_name = address_name;
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

        public Integer getClearingForm() {
            return clearingForm;
        }

        public void setClearingForm(Integer clearingForm) {
            this.clearingForm = clearingForm;
        }

        public Long getContractId() {
            return contractId;
        }

        public void setContractId(Long contractId) {
            this.contractId = contractId;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Long getMaintainerId() {
            return maintainerId;
        }

        public void setMaintainerId(Long maintainerId) {
            this.maintainerId = maintainerId;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
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

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
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

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
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

        public Double getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(Double totalCost) {
            this.totalCost = totalCost;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
