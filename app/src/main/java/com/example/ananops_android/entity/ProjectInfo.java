package com.example.ananops_android.entity;

public class ProjectInfo {

    /**
     * id : 1
     * version : null
     * creator : admin
     * creatorId : 1
     * createdTime : 2019-12-16 16:32:11
     * lastOperator : 超级管理员
     * lastOperatorId : 1
     * updateTime : 2019-12-19 20:37:30
     * pageNum : null
     * pageSize : null
     * orderBy : null
     * contractId : 1
     * contractName :
     * projectName : ATM维修项目
     * projectType : 维修维护
     * isContract : 1
     * partyAId : 2
     * partyAName : xxxx股份有限公司北京分行
     * partyBId : 4
     * partyBName : xxxxxx安防科技有限公司
     * partyAOne : 18111111111
     * partyATwo :
     * partyAThree :
     * partyBOne : 17866666666
     * partyBTel :
     * partyBPhone :
     * partyBEmail :
     * startTime : 2019-12-01 12:18:48
     * endTime : 2019-12-31 12:18:48
     * isDestory : 0
     * description :
     * aoneName : 王刚
     * atwoName :
     * athreeName :
     * bname : 李四
     */

    private Long id;
    private String creator;
    private Long creatorId;
    private String createdTime;
    private String lastOperator;
    private Long lastOperatorId;
    private String updateTime;
    private Long contractId;
    private String contractName;
    private String projectName;
    private String projectType;
    private Integer isContract;
    private Long partyAId;
    private String partyAName;
    private Long partyBId;
    private String partyBName;
    private String partyAOne;
    private String partyATwo;
    private String description;
    private String athreeName;
    private String bname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
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

    public Integer getIsContract() {
        return isContract;
    }

    public void setIsContract(Integer isContract) {
        this.isContract = isContract;
    }

    public Long getPartyAId() {
        return partyAId;
    }

    public void setPartyAId(Long partyAId) {
        this.partyAId = partyAId;
    }

    public String getPartyAName() {
        return partyAName;
    }

    public void setPartyAName(String partyAName) {
        this.partyAName = partyAName;
    }

    public Long getPartyBId() {
        return partyBId;
    }

    public void setPartyBId(Long partyBId) {
        this.partyBId = partyBId;
    }

    public String getPartyBName() {
        return partyBName;
    }

    public void setPartyBName(String partyBName) {
        this.partyBName = partyBName;
    }

    public String getPartyAOne() {
        return partyAOne;
    }

    public void setPartyAOne(String partyAOne) {
        this.partyAOne = partyAOne;
    }

    public String getPartyATwo() {
        return partyATwo;
    }

    public void setPartyATwo(String partyATwo) {
        this.partyATwo = partyATwo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAthreeName() {
        return athreeName;
    }

    public void setAthreeName(String athreeName) {
        this.athreeName = athreeName;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
}
