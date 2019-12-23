package com.example.ananops_android.entity;

public class RepairTimeLine {
    private String last_operator;
    private String movement;
    private String status;
    private String status_timestamp;

    public String getLast_operator() {
        return last_operator;
    }

    public void setLast_operator(String last_operator) {
        this.last_operator = last_operator;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_timestamp() {
        return status_timestamp;
    }

    public void setStatus_timestamp(String status_timestamp) {
        this.status_timestamp = status_timestamp;
    }
}
