package com.example.ananops_android.db;

public class InvoiceListRequest {

    /**
     * itemId : 0
     * status : string
     */

    private Long itemId;
    private String status;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
