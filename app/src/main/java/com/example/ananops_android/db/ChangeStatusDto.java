package com.example.ananops_android.db;

public class ChangeStatusDto {

    /**
     * status : 1
     * statusMsg : string
     * taskId : 1
     */

    private int status;
    private String statusMsg;
    private String taskId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
