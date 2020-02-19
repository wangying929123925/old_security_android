package com.example.ananops_android.entity;

import java.util.List;

public class ReplacementOrder {

    /**
     * id : 29
     * version : 1
     * creator : 维修工程师
     * creatorId : 782526720958801921
     * createdTime : 2020-01-16 12:45:55
     * lastOperator : 维修工程师
     * lastOperatorId : 782526720958801921
     * updateTime : 2020-01-16 12:45:55
     * pageNum : null
     * pageSize : null
     * orderBy : null
     * objectType : 1
     * objectId : 804850770494102528
     * type : null
     * status : 1
     * statusMsg : 审核中
     * totalPrice : null
     * discount : null
     * items : [{"count":2,"id":2,"name":"照明灯"}]
     * processResult : null
     * processMsg : null
     * quotationText : null
     * quotationUrl : null
     */

    private Long id;
    private int version;
    private String creator;
    private Long creatorId;
    private String createdTime;
    private String lastOperator;
    private Long lastOperatorId;
    private String updateTime;
    private int objectType;
    private Long objectId;
    private String type;
    private int status;
    private String statusMsg;
    private Float totalPrice;
    private Float discount;
    private List<Replacement> items;
    private String processResult;
    private String processMsg;
    private String quotationText;
    private String quotationUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public List<Replacement> getItems() {
        return items;
    }

    public void setItems(List<Replacement> items) {
        this.items = items;
    }

    public String getProcessResult() {
        return processResult;
    }

    public void setProcessResult(String processResult) {
        this.processResult = processResult;
    }

    public String getProcessMsg() {
        return processMsg;
    }

    public void setProcessMsg(String processMsg) {
        this.processMsg = processMsg;
    }

    public String getQuotationText() {
        return quotationText;
    }

    public void setQuotationText(String quotationText) {
        this.quotationText = quotationText;
    }

    public String getQuotationUrl() {
        return quotationUrl;
    }

    public void setQuotationUrl(String quotationUrl) {
        this.quotationUrl = quotationUrl;
    }
}
