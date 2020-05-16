package com.example.ananops_android.entity;

import java.util.List;

public class InvoiceDetail {

    /**
     * assetList : [{"device":"string","id":0}]
     * feedback : {"engineer":"string","engineerId":0,"inspcDate":"2020-05-08T09:03:37.875Z","inspcResult":"string","userConfirm":"string"}
     * id : 0
     * inspcCompany : string
     * inspcDetailList : [{"id":0,"itemContent":"string","itemResult":"string","itemState":"string"}]
     * pointAddress : string
     * pointName : string
     * schemaId : 0
     * templateId : 0
     */

    private FeedbackBean feedback;
    private Long id;
    private String inspcCompany;
    private String pointAddress;
    private String pointName;
    private Long schemaId;
    private Long templateId;
    private List<AssetListBean> assetList;
    private List<InspcDetailListBean> inspcDetailList;

    public FeedbackBean getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedbackBean feedback) {
        this.feedback = feedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInspcCompany() {
        return inspcCompany;
    }

    public void setInspcCompany(String inspcCompany) {
        this.inspcCompany = inspcCompany;
    }

    public String getPointAddress() {
        return pointAddress;
    }

    public void setPointAddress(String pointAddress) {
        this.pointAddress = pointAddress;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public Long getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(Long schemaId) {
        this.schemaId = schemaId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public List<AssetListBean> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<AssetListBean> assetList) {
        this.assetList = assetList;
    }

    public List<InspcDetailListBean> getInspcDetailList() {
        return inspcDetailList;
    }

    public void setInspcDetailList(List<InspcDetailListBean> inspcDetailList) {
        this.inspcDetailList = inspcDetailList;
    }

    public static class FeedbackBean {
        /**
         * engineer : string
         * engineerId : 0
         * inspcDate : 2020-05-08T09:03:37.875Z
         * inspcResult : string
         * userConfirm : string
         */

        private String engineer;
        private Long engineerId;
        private String inspcDate;
        private String inspcResult;
        private String userConfirm;

        public String getEngineer() {
            return engineer;
        }

        public void setEngineer(String engineer) {
            this.engineer = engineer;
        }

        public Long getEngineerId() {
            return engineerId;
        }

        public void setEngineerId(Long engineerId) {
            this.engineerId = engineerId;
        }

        public String getInspcDate() {
            return inspcDate;
        }

        public void setInspcDate(String inspcDate) {
            this.inspcDate = inspcDate;
        }

        public String getInspcResult() {
            return inspcResult;
        }

        public void setInspcResult(String inspcResult) {
            this.inspcResult = inspcResult;
        }

        public String getUserConfirm() {
            return userConfirm;
        }

        public void setUserConfirm(String userConfirm) {
            this.userConfirm = userConfirm;
        }
    }

    public static class AssetListBean {
        /**
         * device : string
         * id : 0
         */

        private String device;
        private Long id;

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    public static class InspcDetailListBean {
        /**
         * id : 0
         * itemContent : string
         * itemResult : string
         * itemState : string
         */

        private Long id;
        private String itemContent;
        private String itemResult;
        private String itemState;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getItemContent() {
            return itemContent;
        }

        public void setItemContent(String itemContent) {
            this.itemContent = itemContent;
        }

        public String getItemResult() {
            return itemResult;
        }

        public void setItemResult(String itemResult) {
            this.itemResult = itemResult;
        }

        public String getItemState() {
            return itemState;
        }

        public void setItemState(String itemState) {
            this.itemState = itemState;
        }
    }
}
