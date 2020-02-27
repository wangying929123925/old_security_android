package com.example.ananops_android.db;

import com.example.ananops_android.entity.TroubleAddress;

import java.util.List;

public class TroubleTypeAndAddressListResponse {

    /**
     * code : 200
     * message : 操作成功
     * result : {"userId":"782517846944000001","troubleTypeList":["投影仪","摄像机","发电机","收款机","报警器","stjg"],"troubleAddressList":[{"troubleAddress":"主楼三楼楼梯口","troubleLongitude":null,"troubleLatitude":null},{"troubleAddress":"银行北门","troubleLongitude":null,"troubleLatitude":null},{"troubleAddress":"银行东门","troubleLongitude":null,"troubleLatitude":null},{"troubleAddress":"主楼二楼门口","troubleLongitude":null,"troubleLatitude":null},{"troubleAddress":"大门口","troubleLongitude":null,"troubleLatitude":null},{"troubleAddress":"string","troubleLongitude":null,"troubleLatitude":null}]}
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
         * userId : 782517846944000001
         * troubleTypeList : ["投影仪","摄像机","发电机","收款机","报警器","stjg"]
         * troubleAddressList : [{"troubleAddress":"主楼三楼楼梯口","troubleLongitude":null,"troubleLatitude":null},{"troubleAddress":"银行北门","troubleLongitude":null,"troubleLatitude":null},{"troubleAddress":"银行东门","troubleLongitude":null,"troubleLatitude":null},{"troubleAddress":"主楼二楼门口","troubleLongitude":null,"troubleLatitude":null},{"troubleAddress":"大门口","troubleLongitude":null,"troubleLatitude":null},{"troubleAddress":"string","troubleLongitude":null,"troubleLatitude":null}]
         */

        private Long userId;
        private List<String> troubleTypeList;
        private List<TroubleAddress> troubleAddressList;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public List<String> getTroubleTypeList() {
            return troubleTypeList;
        }

        public void setTroubleTypeList(List<String> troubleTypeList) {
            this.troubleTypeList = troubleTypeList;
        }

        public List<TroubleAddress> getTroubleAddressList() {
            return troubleAddressList;
        }

        public void setTroubleAddressList(List<TroubleAddress> troubleAddressList) {
            this.troubleAddressList = troubleAddressList;
        }

    }
}
