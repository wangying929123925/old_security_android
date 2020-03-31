package com.example.ananops_android.db;

public class InspectionPicRequest {

    /**
     * itemId : 0
     * itemStatus : 0
     * taskId : 0
     */

    private Long itemId;
    private int itemStatus;
    private Long taskId;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(int itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
