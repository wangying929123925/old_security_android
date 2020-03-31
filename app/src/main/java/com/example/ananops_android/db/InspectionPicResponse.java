package com.example.ananops_android.db;

import java.util.List;

public class InspectionPicResponse {

    /**
     * code : 0
     * message : string
     * result : [{"attachmentId":0,"name":"string","url":"string"}]
     */

    private String code;
    private String message;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * attachmentId : 0
         * name : string
         * url : string
         */

        private Long attachmentId;
        private String name;
        private String url;

        public Long getAttachmentId() {
            return attachmentId;
        }

        public void setAttachmentId(Long attachmentId) {
            this.attachmentId = attachmentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
