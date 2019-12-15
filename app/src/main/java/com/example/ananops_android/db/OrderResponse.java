package com.example.ananops_android.db;


import com.example.ananops_android.entity.RepairListContent;

import java.util.List;

public class OrderResponse {
    private String code;
    private List<RepairListContent> result;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<RepairListContent> getResult() {
        return result;
    }

    public void setResult(List<RepairListContent> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
