package com.example.ananops_android.db;

public class OrderDetailResponse {

    /**
     * code : 0
     * message : string
     * result : {"companyVo":{"email":"string","groupId":0,"groupName":"string","id":0,"lastLoginIp":"string","lastLoginTime":"2020-03-07T07:14:28.623Z","loginName":"string","mobileNo":"string","remark":"string","roleCode":"string","roleId":0,"roleName":"string","roleStatus":"string","salt":"string","status":"string","type":"string","userCode":"string","userId":"string","userName":"string","userSource":"string"},"engineerDto":{"email":"string","groupId":0,"groupName":"string","id":0,"lastLoginIp":"string","lastLoginTime":"2020-03-07T07:14:28.623Z","loginName":"string","mobileNo":"string","remark":"string","roleCode":"string","roleId":0,"roleName":"string","roleStatus":"string","salt":"string","status":"string","type":"string","userCode":"string","userId":"string","userName":"string","userSource":"string"},"mdmcTask":{"actualFinishTime":"2020-03-07T07:14:28.623Z","actualStartTime":"2020-03-07T07:14:28.623Z","addressName":"string","appointTime":"2020-03-07T07:14:28.623Z","call":"string","clearingForm":0,"contractId":0,"createdTime":"2020-03-07T07:14:28.623Z","creator":"string","creatorId":0,"deadline":"2020-03-07T07:14:28.623Z","facilitatorId":0,"id":0,"lastOperator":"string","lastOperatorId":0,"level":0,"maintainerId":0,"note":"string","objectId":0,"objectType":0,"orderBy":"string","pageNum":0,"pageSize":0,"principalId":0,"projectId":0,"requestLatitude":0,"requestLongitude":0,"result":0,"scheduledFinishTime":"2020-03-07T07:14:28.623Z","scheduledStartTime":"2020-03-07T07:14:28.623Z","status":0,"suggestion":"string","title":"string","totalCost":0,"updateTime":"2020-03-07T07:14:28.623Z","userId":0,"version":0},"pmcProjectDto":{"aleaderId":0,"aleaderName":"string","aleaderTel":"string","aoneName":"string","athreeName":"string","atwoName":"string","bleaderId":0,"bleaderName":"string","bleaderTel":"string","contractId":0,"contractName":"string","description":"string","endTime":"2019-12-01 12:18:48","id":0,"isContract":0,"isDestroy":0,"partyAId":0,"partyAName":"string","partyAOne":"string","partyAThree":"string","partyATwo":"string","partyBEmail":"string","partyBId":0,"partyBName":"string","partyBPhone":"string","partyBTel":"string","projectName":"string","projectType":"string","startTime":"2019-12-01 12:18:48"},"principalInfoDto":{"email":"string","groupId":0,"groupName":"string","id":0,"lastLoginIp":"string","lastLoginTime":"2020-03-07T07:14:28.623Z","loginName":"string","mobileNo":"string","remark":"string","roleCode":"string","roleId":0,"roleName":"string","roleStatus":"string","salt":"string","status":"string","type":"string","userCode":"string","userId":"string","userName":"string","userSource":"string"},"userInfoDto":{"email":"string","groupId":0,"groupName":"string","id":0,"lastLoginIp":"string","lastLoginTime":"2020-03-07T07:14:28.623Z","loginName":"string","mobileNo":"string","remark":"string","roleCode":"string","roleId":0,"roleName":"string","roleStatus":"string","salt":"string","status":"string","type":"string","userCode":"string","userId":"string","userName":"string","userSource":"string"}}
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
         * companyVo : {"email":"string","groupId":0,"groupName":"string","id":0,"lastLoginIp":"string","lastLoginTime":"2020-03-07T07:14:28.623Z","loginName":"string","mobileNo":"string","remark":"string","roleCode":"string","roleId":0,"roleName":"string","roleStatus":"string","salt":"string","status":"string","type":"string","userCode":"string","userId":"string","userName":"string","userSource":"string"}
         * engineerDto : {"email":"string","groupId":0,"groupName":"string","id":0,"lastLoginIp":"string","lastLoginTime":"2020-03-07T07:14:28.623Z","loginName":"string","mobileNo":"string","remark":"string","roleCode":"string","roleId":0,"roleName":"string","roleStatus":"string","salt":"string","status":"string","type":"string","userCode":"string","userId":"string","userName":"string","userSource":"string"}
         * mdmcTask : {"actualFinishTime":"2020-03-07T07:14:28.623Z","actualStartTime":"2020-03-07T07:14:28.623Z","addressName":"string","appointTime":"2020-03-07T07:14:28.623Z","call":"string","clearingForm":0,"contractId":0,"createdTime":"2020-03-07T07:14:28.623Z","creator":"string","creatorId":0,"deadline":"2020-03-07T07:14:28.623Z","facilitatorId":0,"id":0,"lastOperator":"string","lastOperatorId":0,"level":0,"maintainerId":0,"note":"string","objectId":0,"objectType":0,"orderBy":"string","pageNum":0,"pageSize":0,"principalId":0,"projectId":0,"requestLatitude":0,"requestLongitude":0,"result":0,"scheduledFinishTime":"2020-03-07T07:14:28.623Z","scheduledStartTime":"2020-03-07T07:14:28.623Z","status":0,"suggestion":"string","title":"string","totalCost":0,"updateTime":"2020-03-07T07:14:28.623Z","userId":0,"version":0}
         * pmcProjectDto : {"aleaderId":0,"aleaderName":"string","aleaderTel":"string","aoneName":"string","athreeName":"string","atwoName":"string","bleaderId":0,"bleaderName":"string","bleaderTel":"string","contractId":0,"contractName":"string","description":"string","endTime":"2019-12-01 12:18:48","id":0,"isContract":0,"isDestroy":0,"partyAId":0,"partyAName":"string","partyAOne":"string","partyAThree":"string","partyATwo":"string","partyBEmail":"string","partyBId":0,"partyBName":"string","partyBPhone":"string","partyBTel":"string","projectName":"string","projectType":"string","startTime":"2019-12-01 12:18:48"}
         * principalInfoDto : {"email":"string","groupId":0,"groupName":"string","id":0,"lastLoginIp":"string","lastLoginTime":"2020-03-07T07:14:28.623Z","loginName":"string","mobileNo":"string","remark":"string","roleCode":"string","roleId":0,"roleName":"string","roleStatus":"string","salt":"string","status":"string","type":"string","userCode":"string","userId":"string","userName":"string","userSource":"string"}
         * userInfoDto : {"email":"string","groupId":0,"groupName":"string","id":0,"lastLoginIp":"string","lastLoginTime":"2020-03-07T07:14:28.623Z","loginName":"string","mobileNo":"string","remark":"string","roleCode":"string","roleId":0,"roleName":"string","roleStatus":"string","salt":"string","status":"string","type":"string","userCode":"string","userId":"string","userName":"string","userSource":"string"}
         */

        private CompanyVoBean companyVo;
        private EngineerDtoBean engineerDto;
        private MdmcTaskBean mdmcTask;
        private PmcProjectDtoBean pmcProjectDto;
        private PrincipalInfoDtoBean principalInfoDto;
        private UserInfoDtoBean userInfoDto;

        public CompanyVoBean getCompanyVo() {
            return companyVo;
        }

        public void setCompanyVo(CompanyVoBean companyVo) {
            this.companyVo = companyVo;
        }

        public EngineerDtoBean getEngineerDto() {
            return engineerDto;
        }

        public void setEngineerDto(EngineerDtoBean engineerDto) {
            this.engineerDto = engineerDto;
        }

        public MdmcTaskBean getMdmcTask() {
            return mdmcTask;
        }

        public void setMdmcTask(MdmcTaskBean mdmcTask) {
            this.mdmcTask = mdmcTask;
        }

        public PmcProjectDtoBean getPmcProjectDto() {
            return pmcProjectDto;
        }

        public void setPmcProjectDto(PmcProjectDtoBean pmcProjectDto) {
            this.pmcProjectDto = pmcProjectDto;
        }

        public PrincipalInfoDtoBean getPrincipalInfoDto() {
            return principalInfoDto;
        }

        public void setPrincipalInfoDto(PrincipalInfoDtoBean principalInfoDto) {
            this.principalInfoDto = principalInfoDto;
        }

        public UserInfoDtoBean getUserInfoDto() {
            return userInfoDto;
        }

        public void setUserInfoDto(UserInfoDtoBean userInfoDto) {
            this.userInfoDto = userInfoDto;
        }

        public static class CompanyVoBean {
            /**
             * email : string
             * groupId : 0
             * groupName : string
             * id : 0
             * lastLoginIp : string
             * lastLoginTime : 2020-03-07T07:14:28.623Z
             * loginName : string
             * mobileNo : string
             * remark : string
             * roleCode : string
             * roleId : 0
             * roleName : string
             * roleStatus : string
             * salt : string
             * status : string
             * type : string
             * userCode : string
             * userId : string
             * userName : string
             * userSource : string
             */

            private String email;
            private String groupId;
            private String groupName;
            private String id;
            private String lastLoginIp;
            private String lastLoginTime;
            private String loginName;
            private String mobileNo;
            private String remark;
            private String roleCode;
            private String roleId;
            private String roleName;
            private String roleStatus;
            private String salt;
            private String status;
            private String type;
            private String userCode;
            private String userId;
            private String userName;
            private String userSource;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLastLoginIp() {
                return lastLoginIp;
            }

            public void setLastLoginIp(String lastLoginIp) {
                this.lastLoginIp = lastLoginIp;
            }

            public String getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(String lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getMobileNo() {
                return mobileNo;
            }

            public void setMobileNo(String mobileNo) {
                this.mobileNo = mobileNo;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getRoleCode() {
                return roleCode;
            }

            public void setRoleCode(String roleCode) {
                this.roleCode = roleCode;
            }

            public String getRoleId() {
                return roleId;
            }

            public void setRoleId(String roleId) {
                this.roleId = roleId;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public String getRoleStatus() {
                return roleStatus;
            }

            public void setRoleStatus(String roleStatus) {
                this.roleStatus = roleStatus;
            }

            public String getSalt() {
                return salt;
            }

            public void setSalt(String salt) {
                this.salt = salt;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSource() {
                return userSource;
            }

            public void setUserSource(String userSource) {
                this.userSource = userSource;
            }
        }

        public static class EngineerDtoBean {
            /**
             * email : string
             * groupId : 0
             * groupName : string
             * id : 0
             * lastLoginIp : string
             * lastLoginTime : 2020-03-07T07:14:28.623Z
             * loginName : string
             * mobileNo : string
             * remark : string
             * roleCode : string
             * roleId : 0
             * roleName : string
             * roleStatus : string
             * salt : string
             * status : string
             * type : string
             * userCode : string
             * userId : string
             * userName : string
             * userSource : string
             */

            private String email;
            private String groupId;
            private String groupName;
            private String id;
            private String lastLoginIp;
            private String lastLoginTime;
            private String loginName;
            private String mobileNo;
            private String remark;
            private String roleCode;
            private String roleId;
            private String roleName;
            private String roleStatus;
            private String salt;
            private String status;
            private String type;
            private String userCode;
            private String userId;
            private String userName;
            private String userSource;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLastLoginIp() {
                return lastLoginIp;
            }

            public void setLastLoginIp(String lastLoginIp) {
                this.lastLoginIp = lastLoginIp;
            }

            public String getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(String lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getMobileNo() {
                return mobileNo;
            }

            public void setMobileNo(String mobileNo) {
                this.mobileNo = mobileNo;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getRoleCode() {
                return roleCode;
            }

            public void setRoleCode(String roleCode) {
                this.roleCode = roleCode;
            }

            public String getRoleId() {
                return roleId;
            }

            public void setRoleId(String roleId) {
                this.roleId = roleId;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public String getRoleStatus() {
                return roleStatus;
            }

            public void setRoleStatus(String roleStatus) {
                this.roleStatus = roleStatus;
            }

            public String getSalt() {
                return salt;
            }

            public void setSalt(String salt) {
                this.salt = salt;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSource() {
                return userSource;
            }

            public void setUserSource(String userSource) {
                this.userSource = userSource;
            }
        }

        public static class MdmcTaskBean {
            /**
             * actualFinishTime : 2020-03-07T07:14:28.623Z
             * actualStartTime : 2020-03-07T07:14:28.623Z
             * addressName : string
             * appointTime : 2020-03-07T07:14:28.623Z
             * call : string
             * clearingForm : 0
             * contractId : 0
             * createdTime : 2020-03-07T07:14:28.623Z
             * creator : string
             * creatorId : 0
             * deadline : 2020-03-07T07:14:28.623Z
             * facilitatorId : 0
             * id : 0
             * lastOperator : string
             * lastOperatorId : 0
             * level : 0
             * maintainerId : 0
             * note : string
             * objectId : 0
             * objectType : 0
             * orderBy : string
             * pageNum : 0
             * pageSize : 0
             * principalId : 0
             * projectId : 0
             * requestLatitude : 0
             * requestLongitude : 0
             * result : 0
             * scheduledFinishTime : 2020-03-07T07:14:28.623Z
             * scheduledStartTime : 2020-03-07T07:14:28.623Z
             * status : 0
             * suggestion : string
             * title : string
             * totalCost : 0
             * updateTime : 2020-03-07T07:14:28.623Z
             * userId : 0
             * version : 0
             */

            private String actualFinishTime;
            private String actualStartTime;
            private String addressName;
            private String appointTime;
            private String call;
            private int clearingForm;
            private String contractId;
            private String createdTime;
            private String creator;
            private String creatorId;
            private String deadline;
            private String facilitatorId;
            private String id;
            private String lastOperator;
            private String lastOperatorId;
            private int level;
            private String maintainerId;
            private String note;
            private String objectId;
            private int objectType;
            private String orderBy;
            private int pageNum;
            private int pageSize;
            private String principalId;
            private String projectId;
            private Double requestLatitude;
            private Double requestLongitude;
            private String result;
            private String scheduledFinishTime;
            private String scheduledStartTime;
            private int status;
            private String suggestion;
            private String title;
            private int totalCost;
            private String updateTime;
            private String userId;
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

            public int getClearingForm() {
                return clearingForm;
            }

            public void setClearingForm(int clearingForm) {
                this.clearingForm = clearingForm;
            }

            public String getContractId() {
                return contractId;
            }

            public void setContractId(String contractId) {
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

            public String getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(String creatorId) {
                this.creatorId = creatorId;
            }

            public String getDeadline() {
                return deadline;
            }

            public void setDeadline(String deadline) {
                this.deadline = deadline;
            }

            public String getFacilitatorId() {
                return facilitatorId;
            }

            public void setFacilitatorId(String facilitatorId) {
                this.facilitatorId = facilitatorId;
            }

            public String getResult() {
                return result;
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

            public String getLastOperatorId() {
                return lastOperatorId;
            }

            public void setLastOperatorId(String lastOperatorId) {
                this.lastOperatorId = lastOperatorId;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getMaintainerId() {
                return maintainerId;
            }

            public void setMaintainerId(String maintainerId) {
                this.maintainerId = maintainerId;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public int getObjectType() {
                return objectType;
            }

            public void setObjectType(int objectType) {
                this.objectType = objectType;
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

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }
        }

        public static class PmcProjectDtoBean {
            /**
             * aleaderId : 0
             * aleaderName : string
             * aleaderTel : string
             * aoneName : string
             * athreeName : string
             * atwoName : string
             * bleaderId : 0
             * bleaderName : string
             * bleaderTel : string
             * contractId : 0
             * contractName : string
             * description : string
             * endTime : 2019-12-01 12:18:48
             * id : 0
             * isContract : 0
             * isDestroy : 0
             * partyAId : 0
             * partyAName : string
             * partyAOne : string
             * partyAThree : string
             * partyATwo : string
             * partyBEmail : string
             * partyBId : 0
             * partyBName : string
             * partyBPhone : string
             * partyBTel : string
             * projectName : string
             * projectType : string
             * startTime : 2019-12-01 12:18:48
             */

            private String aleaderId;
            private String aleaderName;
            private String aleaderTel;
            private String aoneName;
            private String athreeName;
            private String atwoName;
            private String bleaderId;
            private String bleaderName;
            private String bleaderTel;
            private String contractId;
            private String contractName;
            private String description;
            private String endTime;
            private String id;
            private int isContract;
            private int isDestroy;
            private String partyAId;
            private String partyAName;
            private String partyAOne;
            private String partyAThree;
            private String partyATwo;
            private String partyBEmail;
            private String partyBId;
            private String partyBName;
            private String partyBPhone;
            private String partyBTel;
            private String projectName;
            private String projectType;
            private String startTime;

            public String getAleaderId() {
                return aleaderId;
            }

            public void setAleaderId(String aleaderId) {
                this.aleaderId = aleaderId;
            }

            public String getAleaderName() {
                return aleaderName;
            }

            public void setAleaderName(String aleaderName) {
                this.aleaderName = aleaderName;
            }

            public String getAleaderTel() {
                return aleaderTel;
            }

            public void setAleaderTel(String aleaderTel) {
                this.aleaderTel = aleaderTel;
            }

            public String getAoneName() {
                return aoneName;
            }

            public void setAoneName(String aoneName) {
                this.aoneName = aoneName;
            }

            public String getAthreeName() {
                return athreeName;
            }

            public void setAthreeName(String athreeName) {
                this.athreeName = athreeName;
            }

            public String getAtwoName() {
                return atwoName;
            }

            public void setAtwoName(String atwoName) {
                this.atwoName = atwoName;
            }

            public String getBleaderId() {
                return bleaderId;
            }

            public void setBleaderId(String bleaderId) {
                this.bleaderId = bleaderId;
            }

            public String getBleaderName() {
                return bleaderName;
            }

            public void setBleaderName(String bleaderName) {
                this.bleaderName = bleaderName;
            }

            public String getBleaderTel() {
                return bleaderTel;
            }

            public void setBleaderTel(String bleaderTel) {
                this.bleaderTel = bleaderTel;
            }

            public String getContractId() {
                return contractId;
            }

            public void setContractId(String contractId) {
                this.contractId = contractId;
            }

            public String getContractName() {
                return contractName;
            }

            public void setContractName(String contractName) {
                this.contractName = contractName;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getIsContract() {
                return isContract;
            }

            public void setIsContract(int isContract) {
                this.isContract = isContract;
            }

            public int getIsDestroy() {
                return isDestroy;
            }

            public void setIsDestroy(int isDestroy) {
                this.isDestroy = isDestroy;
            }

            public String getPartyAId() {
                return partyAId;
            }

            public void setPartyAId(String partyAId) {
                this.partyAId = partyAId;
            }

            public String getPartyAName() {
                return partyAName;
            }

            public void setPartyAName(String partyAName) {
                this.partyAName = partyAName;
            }

            public String getPartyAOne() {
                return partyAOne;
            }

            public void setPartyAOne(String partyAOne) {
                this.partyAOne = partyAOne;
            }

            public String getPartyAThree() {
                return partyAThree;
            }

            public void setPartyAThree(String partyAThree) {
                this.partyAThree = partyAThree;
            }

            public String getPartyATwo() {
                return partyATwo;
            }

            public void setPartyATwo(String partyATwo) {
                this.partyATwo = partyATwo;
            }

            public String getPartyBEmail() {
                return partyBEmail;
            }

            public void setPartyBEmail(String partyBEmail) {
                this.partyBEmail = partyBEmail;
            }

            public String getPartyBId() {
                return partyBId;
            }

            public void setPartyBId(String partyBId) {
                this.partyBId = partyBId;
            }

            public String getPartyBName() {
                return partyBName;
            }

            public void setPartyBName(String partyBName) {
                this.partyBName = partyBName;
            }

            public String getPartyBPhone() {
                return partyBPhone;
            }

            public void setPartyBPhone(String partyBPhone) {
                this.partyBPhone = partyBPhone;
            }

            public String getPartyBTel() {
                return partyBTel;
            }

            public void setPartyBTel(String partyBTel) {
                this.partyBTel = partyBTel;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }
        }

        public static class PrincipalInfoDtoBean {
            /**
             * email : string
             * groupId : 0
             * groupName : string
             * id : 0
             * lastLoginIp : string
             * lastLoginTime : 2020-03-07T07:14:28.623Z
             * loginName : string
             * mobileNo : string
             * remark : string
             * roleCode : string
             * roleId : 0
             * roleName : string
             * roleStatus : string
             * salt : string
             * status : string
             * type : string
             * userCode : string
             * userId : string
             * userName : string
             * userSource : string
             */

            private String email;
            private String groupId;
            private String groupName;
            private String id;
            private String lastLoginIp;
            private String lastLoginTime;
            private String loginName;
            private String mobileNo;
            private String remark;
            private String roleCode;
            private String roleId;
            private String roleName;
            private String roleStatus;
            private String salt;
            private String status;
            private String type;
            private String userCode;
            private String userId;
            private String userName;
            private String userSource;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLastLoginIp() {
                return lastLoginIp;
            }

            public void setLastLoginIp(String lastLoginIp) {
                this.lastLoginIp = lastLoginIp;
            }

            public String getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(String lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getMobileNo() {
                return mobileNo;
            }

            public void setMobileNo(String mobileNo) {
                this.mobileNo = mobileNo;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getRoleCode() {
                return roleCode;
            }

            public void setRoleCode(String roleCode) {
                this.roleCode = roleCode;
            }

            public String getRoleId() {
                return roleId;
            }

            public void setRoleId(String roleId) {
                this.roleId = roleId;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public String getRoleStatus() {
                return roleStatus;
            }

            public void setRoleStatus(String roleStatus) {
                this.roleStatus = roleStatus;
            }

            public String getSalt() {
                return salt;
            }

            public void setSalt(String salt) {
                this.salt = salt;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSource() {
                return userSource;
            }

            public void setUserSource(String userSource) {
                this.userSource = userSource;
            }
        }

        public static class UserInfoDtoBean {
            /**
             * email : string
             * groupId : 0
             * groupName : string
             * id : 0
             * lastLoginIp : string
             * lastLoginTime : 2020-03-07T07:14:28.623Z
             * loginName : string
             * mobileNo : string
             * remark : string
             * roleCode : string
             * roleId : 0
             * roleName : string
             * roleStatus : string
             * salt : string
             * status : string
             * type : string
             * userCode : string
             * userId : string
             * userName : string
             * userSource : string
             */

            private String email;
            private String groupId;
            private String groupName;
            private String id;
            private String lastLoginIp;
            private String lastLoginTime;
            private String loginName;
            private String mobileNo;
            private String remark;
            private String roleCode;
            private String roleId;
            private String roleName;
            private String roleStatus;
            private String salt;
            private String status;
            private String type;
            private String userCode;
            private String userId;
            private String userName;
            private String userSource;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLastLoginIp() {
                return lastLoginIp;
            }

            public void setLastLoginIp(String lastLoginIp) {
                this.lastLoginIp = lastLoginIp;
            }

            public String getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(String lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getMobileNo() {
                return mobileNo;
            }

            public void setMobileNo(String mobileNo) {
                this.mobileNo = mobileNo;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getRoleCode() {
                return roleCode;
            }

            public void setRoleCode(String roleCode) {
                this.roleCode = roleCode;
            }

            public String getRoleId() {
                return roleId;
            }

            public void setRoleId(String roleId) {
                this.roleId = roleId;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public String getRoleStatus() {
                return roleStatus;
            }

            public void setRoleStatus(String roleStatus) {
                this.roleStatus = roleStatus;
            }

            public String getSalt() {
                return salt;
            }

            public void setSalt(String salt) {
                this.salt = salt;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSource() {
                return userSource;
            }

            public void setUserSource(String userSource) {
                this.userSource = userSource;
            }
        }
    }
}