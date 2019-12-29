package com.example.ananops_android.db;

import com.example.ananops_android.entity.ProjectInfo;

import java.util.List;

public class ProjectListResponse {

    /**
     * code : 200
     * message : 操作成功
     * result : []
     */

    private String code;
    private String message;
    private List<ProjectInfo> result;

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

    public List<ProjectInfo> getResult() {
        return result;
    }

    public void setResult(List<ProjectInfo> result) {
        this.result = result;
    }
}
