package com.example.ananops_android.db;

import java.util.List;

public class DeviceOrderResult {

    /**
     * code : 200
     * message : 操作成功
     * result : {"deviceOrderCount":1,"deviceOrderList":[{"deviceOrder":{"id":"42","objectType":1,"objectId":"838560332241705984","type":null,"status":1,"statusMsg":"审核中","totalPrice":null,"discount":null,"items":"[{\"count\":2,\"id\":1,\"name\":\"摄像头\"}]","processResult":null,"processMsg":null},"approveCount":1,"approves":[{"id":"41","version":1,"previousApproverId":null,"previousApprover":null,"currentApproverId":"838497501970433024","currentApprover":"朴叙俊","nextApproverId":null,"nextApprover":null,"objectType":1,"objectId":"42","applicantId":"838495957392819200","applicant":"朴宝剑","result":null,"suggestion":null}]}]}
     */

    private String code;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * deviceOrderCount : 1
         * deviceOrderList : [{"deviceOrder":{"id":"42","objectType":1,"objectId":"838560332241705984","type":null,"status":1,"statusMsg":"审核中","totalPrice":null,"discount":null,"items":"[{\"count\":2,\"id\":1,\"name\":\"摄像头\"}]","processResult":null,"processMsg":null},"approveCount":1,"approves":[{"id":"41","version":1,"previousApproverId":null,"previousApprover":null,"currentApproverId":"838497501970433024","currentApprover":"朴叙俊","nextApproverId":null,"nextApprover":null,"objectType":1,"objectId":"42","applicantId":"838495957392819200","applicant":"朴宝剑","result":null,"suggestion":null}]}]
         */

        private int deviceOrderCount;
        private List<DeviceOrderListBean> deviceOrderList;

        public int getDeviceOrderCount() {
            return deviceOrderCount;
        }

        public void setDeviceOrderCount(int deviceOrderCount) {
            this.deviceOrderCount = deviceOrderCount;
        }

        public List<DeviceOrderListBean> getDeviceOrderList() {
            return deviceOrderList;
        }

        public void setDeviceOrderList(List<DeviceOrderListBean> deviceOrderList) {
            this.deviceOrderList = deviceOrderList;
        }

        public static class DeviceOrderListBean {
            /**
             * deviceOrder : {"id":"42","objectType":1,"objectId":"838560332241705984","type":null,"status":1,"statusMsg":"审核中","totalPrice":null,"discount":null,"items":"[{\"count\":2,\"id\":1,\"name\":\"摄像头\"}]","processResult":null,"processMsg":null}
             * approveCount : 1
             * approves : [{"id":"41","version":1,"previousApproverId":null,"previousApprover":null,"currentApproverId":"838497501970433024","currentApprover":"朴叙俊","nextApproverId":null,"nextApprover":null,"objectType":1,"objectId":"42","applicantId":"838495957392819200","applicant":"朴宝剑","result":null,"suggestion":null}]
             */

            private DeviceOrderBean deviceOrder;
            private int approveCount;
            private List<ApprovesBean> approves;

            public DeviceOrderBean getDeviceOrder() {
                return deviceOrder;
            }

            public void setDeviceOrder(DeviceOrderBean deviceOrder) {
                this.deviceOrder = deviceOrder;
            }

            public int getApproveCount() {
                return approveCount;
            }

            public void setApproveCount(int approveCount) {
                this.approveCount = approveCount;
            }

            public List<ApprovesBean> getApproves() {
                return approves;
            }

            public void setApproves(List<ApprovesBean> approves) {
                this.approves = approves;
            }

            public static class DeviceOrderBean {
                /**
                 * id : 42
                 * objectType : 1
                 * objectId : 838560332241705984
                 * type : null
                 * status : 1
                 * statusMsg : 审核中
                 * totalPrice : null
                 * discount : null
                 * items : [{"count":2,"id":1,"name":"摄像头"}]
                 * processResult : null
                 * processMsg : null
                 */

                private String id;
                private int objectType;
                private String objectId;
                private Object type;
                private int status;
                private String statusMsg;
                private Object totalPrice;
                private Object discount;
                private String items;
                private Object processResult;
                private Object processMsg;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public int getObjectType() {
                    return objectType;
                }

                public void setObjectType(int objectType) {
                    this.objectType = objectType;
                }

                public String getObjectId() {
                    return objectId;
                }

                public void setObjectId(String objectId) {
                    this.objectId = objectId;
                }

                public Object getType() {
                    return type;
                }

                public void setType(Object type) {
                    this.type = type;
                }

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

                public Object getTotalPrice() {
                    return totalPrice;
                }

                public void setTotalPrice(Object totalPrice) {
                    this.totalPrice = totalPrice;
                }

                public Object getDiscount() {
                    return discount;
                }

                public void setDiscount(Object discount) {
                    this.discount = discount;
                }

                public String getItems() {
                    return items;
                }

                public void setItems(String items) {
                    this.items = items;
                }

                public Object getProcessResult() {
                    return processResult;
                }

                public void setProcessResult(Object processResult) {
                    this.processResult = processResult;
                }

                public Object getProcessMsg() {
                    return processMsg;
                }

                public void setProcessMsg(Object processMsg) {
                    this.processMsg = processMsg;
                }
            }

            public static class ApprovesBean {
                /**
                 * id : 41
                 * version : 1
                 * previousApproverId : null
                 * previousApprover : null
                 * currentApproverId : 838497501970433024
                 * currentApprover : 朴叙俊
                 * nextApproverId : null
                 * nextApprover : null
                 * objectType : 1
                 * objectId : 42
                 * applicantId : 838495957392819200
                 * applicant : 朴宝剑
                 * result : null
                 * suggestion : null
                 */

                private String id;
                private int version;
                private Object previousApproverId;
                private Object previousApprover;
                private String currentApproverId;
                private String currentApprover;
                private Object nextApproverId;
                private Object nextApprover;
                private int objectType;
                private String objectId;
                private String applicantId;
                private String applicant;
                private Object result;
                private Object suggestion;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public int getVersion() {
                    return version;
                }

                public void setVersion(int version) {
                    this.version = version;
                }

                public Object getPreviousApproverId() {
                    return previousApproverId;
                }

                public void setPreviousApproverId(Object previousApproverId) {
                    this.previousApproverId = previousApproverId;
                }

                public Object getPreviousApprover() {
                    return previousApprover;
                }

                public void setPreviousApprover(Object previousApprover) {
                    this.previousApprover = previousApprover;
                }

                public String getCurrentApproverId() {
                    return currentApproverId;
                }

                public void setCurrentApproverId(String currentApproverId) {
                    this.currentApproverId = currentApproverId;
                }

                public String getCurrentApprover() {
                    return currentApprover;
                }

                public void setCurrentApprover(String currentApprover) {
                    this.currentApprover = currentApprover;
                }

                public Object getNextApproverId() {
                    return nextApproverId;
                }

                public void setNextApproverId(Object nextApproverId) {
                    this.nextApproverId = nextApproverId;
                }

                public Object getNextApprover() {
                    return nextApprover;
                }

                public void setNextApprover(Object nextApprover) {
                    this.nextApprover = nextApprover;
                }

                public int getObjectType() {
                    return objectType;
                }

                public void setObjectType(int objectType) {
                    this.objectType = objectType;
                }

                public String getObjectId() {
                    return objectId;
                }

                public void setObjectId(String objectId) {
                    this.objectId = objectId;
                }

                public String getApplicantId() {
                    return applicantId;
                }

                public void setApplicantId(String applicantId) {
                    this.applicantId = applicantId;
                }

                public String getApplicant() {
                    return applicant;
                }

                public void setApplicant(String applicant) {
                    this.applicant = applicant;
                }

                public Object getResult() {
                    return result;
                }

                public void setResult(Object result) {
                    this.result = result;
                }

                public Object getSuggestion() {
                    return suggestion;
                }

                public void setSuggestion(Object suggestion) {
                    this.suggestion = suggestion;
                }
            }
        }
    }
}
