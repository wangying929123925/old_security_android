package com.example.ananops_android.db;

import com.example.ananops_android.entity.Replacement;

import java.util.List;

public class ReplacementListResponse {
    private String code;
    private String message;
    private List<Replacement> result;

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

    public List<Replacement> getResult() {
        return result;
    }

    public void setResult(List<Replacement> result) {
        this.result = result;
    }
}
