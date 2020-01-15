package com.example.ananops_android.db;

/**
 * @author
 * @date 2020/1/6.
 * GitHub：Cuixiaoyang123
 * email：1227687610@qq.com
 * description：
 */
public class TestResponse {

    /**
     * code : 0
     * message : string
     * result : {"actualFinishTime":"2020-01-06T10:41:12.475Z","createdTime":"2020-01-06T10:41:12.475Z","creator":"string","creatorId":0,"days":0,"facilitatorId":0,"frequency":0,"id":0,"inspectionType":0,"lastOperator":"string","lastOperatorId":0,"location":"string","maintenanceCost":0,"orderBy":"string","pageNum":0,"pageSize":0,"principalId":0,"projectId":0,"remark":"string","scheduledStartTime":"2020-01-06T10:41:12.475Z","status":0,"taskName":"string","totalCost":0,"updateTime":"2020-01-06T10:41:12.475Z","version":0}
     */

    //使用虚假数据，用Retrofit时，将不用虚拟赋值
    private long code = 45478641;
    private String message = "国税局二楼办公室";
    private ResultBean result = new ResultBean();

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
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
         * actualFinishTime : 2020-01-06T10:41:12.475Z
         * createdTime : 2020-01-06T10:41:12.475Z
         * creator : string
         * creatorId : 0
         * days : 0
         * facilitatorId : 0
         * frequency : 0
         * id : 0
         * inspectionType : 0
         * lastOperator : string
         * lastOperatorId : 0
         * location : string
         * maintenanceCost : 0
         * orderBy : string
         * pageNum : 0
         * pageSize : 0
         * principalId : 0
         * projectId : 0
         * remark : string
         * scheduledStartTime : 2020-01-06T10:41:12.475Z
         * status : 0
         * taskName : string
         * totalCost : 0
         * updateTime : 2020-01-06T10:41:12.475Z
         * version : 0
         */

        //使用虚假数据，用Retrofit时，将不用虚拟赋值
        private String actualFinishTime = "2020-01-06T10:41:12.475Z";
        private String createdTime = "2020-01-06T10:41:12.475Z";
        private String creator = "王二";
        private long creatorId = 0;
        private int days = 0;
        private long facilitatorId = 0;
        private int frequency = 0;
        private long id = 0;
        private int inspectionType = 0;
        private String lastOperator = "string";
        private long lastOperatorId = 0;
        private String location = "string";
        private int maintenanceCost = 0;
        private String orderBy = "string";
        private int pageNum = 8232223;
        private int pageSize = 0;
        private long principalId = 0;
        private long projectId = 0;
        private String remark = "string";
        private String scheduledStartTime = "2020-01-06T10:41:12.475Z";
        private int status = 0;
        private String taskName = "string";
        private int totalCost = 0;
        private String updateTime = "2020-01-06T10:41:12.475Z";
        private int version = 0;

        public String getActualFinishTime() {
            return actualFinishTime;
        }

        public void setActualFinishTime(String actualFinishTime) {
            this.actualFinishTime = actualFinishTime;
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

        public long getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(long creatorId) {
            this.creatorId = creatorId;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public long getFacilitatorId() {
            return facilitatorId;
        }

        public void setFacilitatorId(long facilitatorId) {
            this.facilitatorId = facilitatorId;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getInspectionType() {
            return inspectionType;
        }

        public void setInspectionType(int inspectionType) {
            this.inspectionType = inspectionType;
        }

        public String getLastOperator() {
            return lastOperator;
        }

        public void setLastOperator(String lastOperator) {
            this.lastOperator = lastOperator;
        }

        public long getLastOperatorId() {
            return lastOperatorId;
        }

        public void setLastOperatorId(long lastOperatorId) {
            this.lastOperatorId = lastOperatorId;
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

        public long getPrincipalId() {
            return principalId;
        }

        public void setPrincipalId(long principalId) {
            this.principalId = principalId;
        }

        public long getProjectId() {
            return projectId;
        }

        public void setProjectId(long projectId) {
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

        public int getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(int totalCost) {
            this.totalCost = totalCost;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}

