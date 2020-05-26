package com.example.ananops_android.db;

import java.util.List;

public class InspectionItemSubmitRequest {

    /**
     * actualFinishTime : 2019-8-24 11:11:11
     * actualStartTime : 2019-8-24 11:11:11
     * attachmentIds : [0]
     * itemId : 0
     * status : 0
     */

    private String actualFinishTime;
    private String actualStartTime;
    private Long itemId;
    private int status;
    private List<Integer> attachmentIds;

    public String getActualFinishTime() {
        return actualFinishTime;
    }

    public void setActualFinishTime(String actualFinishTime) {
        this.actualFinishTime = actualFinishTime;
    }

    public String getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(String actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Integer> getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(List<Integer> attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}
