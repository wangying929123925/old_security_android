package com.example.ananops_android.db;

import com.example.ananops_android.entity.Contacts;
import java.util.List;

public class RepairerListResponse {
    private String code;
    private String message;
    private List<Contacts> result;

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

    public List<Contacts> getResult() {
        return result;
    }

    public void setResult(List<Contacts> result) {
        this.result = result;
    }
}
