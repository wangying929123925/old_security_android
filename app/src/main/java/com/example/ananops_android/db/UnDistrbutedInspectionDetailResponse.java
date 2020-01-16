package com.example.ananops_android.db;

import com.example.ananops_android.entity.InspectionTaskItem;

import java.util.List;

public class UnDistrbutedInspectionDetailResponse {

    /**
     * code : 0
     * message : string
     * result : {"companyVo":{"accountName":"string","accountNumber":"string","accountOpeningLicense":"string","addressList":[0],"areaId":0,"areaName":"string","businessLicensePhoto":"string","businessScope":"string","cityId":0,"cityName":"string","contact":"string","contactPhone":"string","country":"string","createdTime":"2020-01-15T08:59:31.871Z","creator":"string","creatorId":0,"detailAddress":"string","expirationDate":"2020-01-15T08:59:31.871Z","groupAddress":"string","groupCode":"string","groupId":0,"groupName":"string","id":0,"lastOperator":"string","lastOperatorId":0,"legalPersonName":"string","legalPersonNumber":"string","legalPersonPhone":"string","legalPersonidPhoto":"string","licenseType":"string","mainWork":"string","productCategory":"string","provinceId":0,"provinceName":"string","registeredCaptial":"string","status":0,"streetId":0,"streetName":"string","type":"string","updateTime":"2020-01-15T08:59:31.871Z","userId":0,"zipCode":"string"},"createdTime":"2020-01-15T08:59:31.871Z","creator":"string","creatorId":0,"engineerVos":[{"createdTime":"2020-01-15T08:59:31.871Z","creator":"string","creatorId":0,"education":"string","email":"string","employmentStartTime":"2020-01-15T08:59:31.871Z","groupId":0,"groupName":"string","id":0,"identityExpirationDate":"2020-01-15T08:59:31.871Z","identityNumber":"string","identityPhoto":"string","lastOperator":"string","lastOperatorId":0,"location":"string","loginName":"string","mobileNo":"string","position":"string","remark":"string","sex":"string","status":"string","title":"string","titleCeAuthority":"string","titleCeNumber":"string","titleCePhoto":"string","titleCeTime":"2020-01-15T08:59:31.871Z","updateTime":"2020-01-15T08:59:31.871Z","userCode":"string","userId":0,"userName":"string"}],"id":0,"inspectionTask":{"actualFinishTime":"2020-01-15T08:59:31.871Z","createdTime":"2020-01-15T08:59:31.871Z","creator":"string","creatorId":0,"days":0,"facilitatorId":0,"frequency":0,"id":0,"inspectionType":0,"itemDtoList":[{"actualFinishTime":"2020-01-15T08:59:31.871Z","actualStartTime":"2020-01-15T08:59:31.871Z","count":0,"days":0,"description":"string","frequency":0,"id":0,"inspectionTaskId":0,"itemLatitude":0,"itemLongitude":0,"itemName":"string","maintainerId":0,"result":"string","scheduledStartTime":"2020-01-15T08:59:31.871Z","status":0}],"lastOperator":"string","lastOperatorId":0,"location":"string","maintenanceCost":0,"principalId":0,"projectId":0,"remark":"string","scheduledStartTime":"2020-01-15T08:59:31.871Z","status":0,"taskName":"string","totalCost":0,"updateTime":"2020-01-15T08:59:31.871Z"},"lastOperator":"string","lastOperatorId":0,"maintainTask":{"actualFinishTime":"2020-01-15T08:59:31.871Z","actualStartTime":"2020-01-15T08:59:31.871Z","address_name":"string","appointTime":"2020-01-15T08:59:31.871Z","call":"string","clearingForm":0,"contractId":0,"createdTime":"2020-01-15T08:59:31.871Z","creator":"string","creatorId":0,"deadline":"2020-01-15T08:59:31.871Z","facilitatorId":0,"id":0,"lastOperator":"string","lastOperatorId":0,"level":0,"maintainerId":0,"note":"string","objectId":0,"objectType":0,"orderBy":"string","pageNum":0,"pageSize":0,"principalId":0,"projectId":0,"requestLatitude":0,"requestLongitude":0,"result":0,"scheduledFinishTime":"2020-01-15T08:59:31.871Z","scheduledStartTime":"2020-01-15T08:59:31.871Z","status":0,"suggestion":"string","title":"string","totalCost":0,"updateTime":"2020-01-15T08:59:31.871Z","userId":0,"version":0},"pmcProjectDto":{"aleaderId":0,"aleaderName":"string","aleaderTel":"string","aoneName":"string","athreeName":"string","atwoName":"string","bleaderId":0,"bleaderName":"string","bleaderTel":"string","contractId":0,"contractName":"string","description":"string","endTime":"2019-12-01 12:18:48","id":0,"isContract":0,"isDestroy":0,"partyAId":0,"partyAName":"string","partyAOne":"string","partyAThree":"string","partyATwo":"string","partyBEmail":"string","partyBId":0,"partyBName":"string","partyBPhone":"string","partyBTel":"string","projectName":"string","projectType":"string","startTime":"2019-12-01 12:18:48"},"type":"string","updateTime":"2020-01-15T08:59:31.871Z"}
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
         * companyVo : {"accountName":"string","accountNumber":"string","accountOpeningLicense":"string","addressList":[0],"areaId":0,"areaName":"string","businessLicensePhoto":"string","businessScope":"string","cityId":0,"cityName":"string","contact":"string","contactPhone":"string","country":"string","createdTime":"2020-01-15T08:59:31.871Z","creator":"string","creatorId":0,"detailAddress":"string","expirationDate":"2020-01-15T08:59:31.871Z","groupAddress":"string","groupCode":"string","groupId":0,"groupName":"string","id":0,"lastOperator":"string","lastOperatorId":0,"legalPersonName":"string","legalPersonNumber":"string","legalPersonPhone":"string","legalPersonidPhoto":"string","licenseType":"string","mainWork":"string","productCategory":"string","provinceId":0,"provinceName":"string","registeredCaptial":"string","status":0,"streetId":0,"streetName":"string","type":"string","updateTime":"2020-01-15T08:59:31.871Z","userId":0,"zipCode":"string"}
         * createdTime : 2020-01-15T08:59:31.871Z
         * creator : string
         * creatorId : 0
         * engineerVos : [{"createdTime":"2020-01-15T08:59:31.871Z","creator":"string","creatorId":0,"education":"string","email":"string","employmentStartTime":"2020-01-15T08:59:31.871Z","groupId":0,"groupName":"string","id":0,"identityExpirationDate":"2020-01-15T08:59:31.871Z","identityNumber":"string","identityPhoto":"string","lastOperator":"string","lastOperatorId":0,"location":"string","loginName":"string","mobileNo":"string","position":"string","remark":"string","sex":"string","status":"string","title":"string","titleCeAuthority":"string","titleCeNumber":"string","titleCePhoto":"string","titleCeTime":"2020-01-15T08:59:31.871Z","updateTime":"2020-01-15T08:59:31.871Z","userCode":"string","userId":0,"userName":"string"}]
         * id : 0
         * inspectionTask : {"actualFinishTime":"2020-01-15T08:59:31.871Z","createdTime":"2020-01-15T08:59:31.871Z","creator":"string","creatorId":0,"days":0,"facilitatorId":0,"frequency":0,"id":0,"inspectionType":0,"itemDtoList":[{"actualFinishTime":"2020-01-15T08:59:31.871Z","actualStartTime":"2020-01-15T08:59:31.871Z","count":0,"days":0,"description":"string","frequency":0,"id":0,"inspectionTaskId":0,"itemLatitude":0,"itemLongitude":0,"itemName":"string","maintainerId":0,"result":"string","scheduledStartTime":"2020-01-15T08:59:31.871Z","status":0}],"lastOperator":"string","lastOperatorId":0,"location":"string","maintenanceCost":0,"principalId":0,"projectId":0,"remark":"string","scheduledStartTime":"2020-01-15T08:59:31.871Z","status":0,"taskName":"string","totalCost":0,"updateTime":"2020-01-15T08:59:31.871Z"}
         * lastOperator : string
         * lastOperatorId : 0
         * maintainTask : {"actualFinishTime":"2020-01-15T08:59:31.871Z","actualStartTime":"2020-01-15T08:59:31.871Z","address_name":"string","appointTime":"2020-01-15T08:59:31.871Z","call":"string","clearingForm":0,"contractId":0,"createdTime":"2020-01-15T08:59:31.871Z","creator":"string","creatorId":0,"deadline":"2020-01-15T08:59:31.871Z","facilitatorId":0,"id":0,"lastOperator":"string","lastOperatorId":0,"level":0,"maintainerId":0,"note":"string","objectId":0,"objectType":0,"orderBy":"string","pageNum":0,"pageSize":0,"principalId":0,"projectId":0,"requestLatitude":0,"requestLongitude":0,"result":0,"scheduledFinishTime":"2020-01-15T08:59:31.871Z","scheduledStartTime":"2020-01-15T08:59:31.871Z","status":0,"suggestion":"string","title":"string","totalCost":0,"updateTime":"2020-01-15T08:59:31.871Z","userId":0,"version":0}
         * pmcProjectDto : {"aleaderId":0,"aleaderName":"string","aleaderTel":"string","aoneName":"string","athreeName":"string","atwoName":"string","bleaderId":0,"bleaderName":"string","bleaderTel":"string","contractId":0,"contractName":"string","description":"string","endTime":"2019-12-01 12:18:48","id":0,"isContract":0,"isDestroy":0,"partyAId":0,"partyAName":"string","partyAOne":"string","partyAThree":"string","partyATwo":"string","partyBEmail":"string","partyBId":0,"partyBName":"string","partyBPhone":"string","partyBTel":"string","projectName":"string","projectType":"string","startTime":"2019-12-01 12:18:48"}
         * type : string
         * updateTime : 2020-01-15T08:59:31.871Z
         */

        private String createdTime;
        private String creator;
        private int creatorId;
        private int id;
        private InspectionTaskBean inspectionTask;
        private String lastOperator;
        private int lastOperatorId;
        private String type;
        private String updateTime;


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

        public int getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public InspectionTaskBean getInspectionTask() {
            return inspectionTask;
        }

        public void setInspectionTask(InspectionTaskBean inspectionTask) {
            this.inspectionTask = inspectionTask;
        }

        public String getLastOperator() {
            return lastOperator;
        }

        public void setLastOperator(String lastOperator) {
            this.lastOperator = lastOperator;
        }

        public int getLastOperatorId() {
            return lastOperatorId;
        }

        public void setLastOperatorId(int lastOperatorId) {
            this.lastOperatorId = lastOperatorId;
        }


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public static class InspectionTaskBean {
            /**
             * actualFinishTime : 2020-01-15T08:59:31.871Z
             * createdTime : 2020-01-15T08:59:31.871Z
             * creator : string
             * creatorId : 0
             * days : 0
             * facilitatorId : 0
             * frequency : 0
             * id : 0
             * inspectionType : 0
             * itemDtoList : [{"actualFinishTime":"2020-01-15T08:59:31.871Z","actualStartTime":"2020-01-15T08:59:31.871Z","count":0,"days":0,"description":"string","frequency":0,"id":0,"inspectionTaskId":0,"itemLatitude":0,"itemLongitude":0,"itemName":"string","maintainerId":0,"result":"string","scheduledStartTime":"2020-01-15T08:59:31.871Z","status":0}]
             * lastOperator : string
             * lastOperatorId : 0
             * location : string
             * maintenanceCost : 0
             * principalId : 0
             * projectId : 0
             * remark : string
             * scheduledStartTime : 2020-01-15T08:59:31.871Z
             * status : 0
             * taskName : string
             * totalCost : 0
             * updateTime : 2020-01-15T08:59:31.871Z
             */

            private String actualFinishTime;
            private String createdTime;
            private String creator;
            private int creatorId;
            private int days;
            private int facilitatorId;
            private int frequency;
            private int id;
            private int inspectionType;
            private String lastOperator;
            private int lastOperatorId;
            private String location;
            private int maintenanceCost;
            private int principalId;
            private int projectId;
            private String remark;
            private String scheduledStartTime;
            private int status;
            private String taskName;
            private int totalCost;
            private String updateTime;
            private List<InspectionTaskItem> itemDtoList;

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

            public int getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(int creatorId) {
                this.creatorId = creatorId;
            }

            public int getDays() {
                return days;
            }

            public void setDays(int days) {
                this.days = days;
            }

            public int getFacilitatorId() {
                return facilitatorId;
            }

            public void setFacilitatorId(int facilitatorId) {
                this.facilitatorId = facilitatorId;
            }

            public int getFrequency() {
                return frequency;
            }

            public void setFrequency(int frequency) {
                this.frequency = frequency;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public int getLastOperatorId() {
                return lastOperatorId;
            }

            public void setLastOperatorId(int lastOperatorId) {
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

            public int getPrincipalId() {
                return principalId;
            }

            public void setPrincipalId(int principalId) {
                this.principalId = principalId;
            }

            public int getProjectId() {
                return projectId;
            }

            public void setProjectId(int projectId) {
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

            public List<InspectionTaskItem> getItemDtoList() {
                return itemDtoList;
            }

            public void setItemDtoList(List<InspectionTaskItem> itemDtoList) {
                this.itemDtoList = itemDtoList;
            }

        }
    }
}
