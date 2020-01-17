package com.example.ananops_android.db;

public class InspectionQueryByStatusAndIdRequest {

    /**
     * status : 2
     * userId : 1
     */

    private Integer status;
    private Long userId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
