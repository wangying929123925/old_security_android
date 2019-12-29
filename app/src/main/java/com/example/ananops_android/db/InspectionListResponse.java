package com.example.ananops_android.db;

import com.example.ananops_android.entity.InspectionInfo;

import java.util.List;

public class InspectionListResponse {

    /**
     * code : 200
     * message : 操作成功
     * result : []
     */

    private String code;
    private String message;
    private List<InspectionInfo> result;

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

    public List<InspectionInfo> getResult() {
        return result;
    }

    public void setResult(List<InspectionInfo> result) {
        this.result = result;
    }
}
