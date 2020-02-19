package com.example.ananops_android.db;

import com.example.ananops_android.entity.ReplacementOrder;

import java.util.List;

public class RelacementOrderListUndoResult {
    private String code;
    private String message;
    private List<ReplacementOrder> result;

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

    public List<ReplacementOrder> getResult() {
        return result;
    }

    public void setResult(List<ReplacementOrder> result) {
        this.result = result;
    }
}
