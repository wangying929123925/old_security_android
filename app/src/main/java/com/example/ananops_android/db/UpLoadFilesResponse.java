package com.example.ananops_android.db;

public class UpLoadFilesResponse {

    /**
     * attachmentId : 839664019257168896
     * attachmentUrl : http://file.ananops.com/ananops%2Fmdmc%2F838245741926286336%2FmaintainOrder%2F839663992631727104.png?e=1583299351&token=9BaGusa9xE_kgQpuHRqjU7-wVyhnVVyL14dUvG1B:xvJ_iL-CGyi8zsZOKkh-RGDJ97Y=
     * attachmentName : 839663992631727104.png
     * attachmentPath : ananops/mdmc/838245741926286336/maintainOrder/
     * fileType : png
     * refNo : null
     */

    private Long attachmentId;
    private String attachmentUrl;
    private String attachmentName;
    private String attachmentPath;
    private String fileType;
    private Object refNo;

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Object getRefNo() {
        return refNo;
    }

    public void setRefNo(Object refNo) {
        this.refNo = refNo;
    }
}
