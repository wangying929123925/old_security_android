package com.example.ananops_android.db;

import com.example.ananops_android.entity.RepairContent;
import com.example.ananops_android.entity.TimeLine;

import java.util.List;

public class OrderTimelineResponse {
    private String code;
    private List<TimeLine> result;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TimeLine> getResult() {
        return result;
    }

    public void setResult(List<TimeLine> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
