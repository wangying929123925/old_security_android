package com.example.ananops_android.db;

public class RelacementOrderOperationRequest {

    /**
     * discount : 0
     * id : 0
     * nextApprover : string
     * nextApproverId : 0
     * objectId : 0
     * objectType : 0
     * result : string
     * status : 0
     * suggestion : string
     * totalPrice : 0
     */

    private Float discount;
    private Long id;
    private String nextApprover;
    private Long nextApproverId;
    private Long objectId;
    private int objectType;
    private String result;
    private int status;
    private String suggestion;
    private Float totalPrice;

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNextApprover() {
        return nextApprover;
    }

    public void setNextApprover(String nextApprover) {
        this.nextApprover = nextApprover;
    }

    public Long getNextApproverId() {
        return nextApproverId;
    }

    public void setNextApproverId(Long nextApproverId) {
        this.nextApproverId = nextApproverId;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
