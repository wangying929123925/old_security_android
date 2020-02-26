package com.example.ananops_android.db;

import com.example.ananops_android.entity.InspectionInfo;
import com.example.ananops_android.entity.InspectionTaskItem;

import java.util.ArrayList;

public class InspectionItemDetailResponse {
    private String code;
    private String message;
    private InspectionTaskItem result;

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

    public InspectionTaskItem getResult() {
        return result;
    }

    public void setResult(InspectionTaskItem result) {
        this.result = result;
    }
}
