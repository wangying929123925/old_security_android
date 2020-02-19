package com.example.ananops_android.db;

import com.example.ananops_android.entity.Replacement;

import java.util.List;

public class ReplacementOrderCreateRequest {

    /**
     * applicant : string
     * applicantId : 0
     * currentApprover : string
     * currentApproverId : 0
     * items : [{"count":0,"id":0,"manufacture":"string","model":"string","name":"string","type":"string"}]
     * objectId : 0
     * objectType : 0
     */

    private String applicant;
    private Long applicantId;
    private String currentApprover;
    private Long currentApproverId;
    private Long objectId;
    private int objectType;
    private List<Replacement> items;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public String getCurrentApprover() {
        return currentApprover;
    }

    public void setCurrentApprover(String currentApprover) {
        this.currentApprover = currentApprover;
    }

    public Long getCurrentApproverId() {
        return currentApproverId;
    }

    public void setCurrentApproverId(Long currentApproverId) {
        this.currentApproverId = currentApproverId;
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

    public List<Replacement> getItems() {
        return items;
    }

    public void setItems(List<Replacement> items) {
        this.items = items;
    }
}
