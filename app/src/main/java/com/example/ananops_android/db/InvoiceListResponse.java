package com.example.ananops_android.db;

import com.example.ananops_android.entity.InvoiceItemInfo;

import java.util.List;

public class InvoiceListResponse {
    private String code;
    private String message;
    private List<InvoiceItemInfo> result;

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

    public List<InvoiceItemInfo> getResult() {
        return result;
    }

    public void setResult(List<InvoiceItemInfo> result) {
        this.result = result;
    }
}
