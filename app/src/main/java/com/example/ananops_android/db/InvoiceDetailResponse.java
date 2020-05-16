package com.example.ananops_android.db;

import com.example.ananops_android.entity.InvoiceDetail;

public class InvoiceDetailResponse {
    private String code;
    private String message;
    private InvoiceDetail result;

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

    public InvoiceDetail getResult() {
        return result;
    }

    public void setResult(InvoiceDetail result) {
        this.result = result;
    }
}
