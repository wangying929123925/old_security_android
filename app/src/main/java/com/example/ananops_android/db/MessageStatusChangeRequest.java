package com.example.ananops_android.db;

public class MessageStatusChangeRequest {

    /**
     * messageId : 0
     * status : 0
     */

    private Long messageId;
    private int status;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
