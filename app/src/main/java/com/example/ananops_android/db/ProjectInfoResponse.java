package com.example.ananops_android.db;

import com.example.ananops_android.entity.ProjectInfo;

public class ProjectInfoResponse {
    private String code;
    private String message;
    private ProjectInfo result;

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

    public ProjectInfo getResult() {
        return result;
    }

    public void setResult(ProjectInfo result) {
        this.result = result;
    }
}
