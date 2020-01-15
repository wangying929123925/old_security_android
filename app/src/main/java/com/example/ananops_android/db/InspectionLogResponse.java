package com.example.ananops_android.db;

import com.example.ananops_android.entity.InspectionTaskItem;
import com.example.ananops_android.entity.InspectionTaskLog;

import java.util.List;

public class InspectionLogResponse {
    private String code;
    private String message;
    private List<InspectionTaskLog> result;

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

    public List<InspectionTaskLog> getResult() {
        return result;
    }

    public void setResult(List<InspectionTaskLog> result) {
        this.result = result;
    }
}
