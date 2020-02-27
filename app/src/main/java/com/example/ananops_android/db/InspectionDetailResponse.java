package com.example.ananops_android.db;

import com.example.ananops_android.entity.InspectionInfo;
import com.example.ananops_android.entity.InspectionInfoResponse;

public class InspectionDetailResponse {
    private String code;
    private String message;
    private InspectionInfoResponse result;

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

    public InspectionInfoResponse getResult() {
        return result;
    }

    public void setResult(InspectionInfoResponse result) {
        this.result = result;
    }
}
