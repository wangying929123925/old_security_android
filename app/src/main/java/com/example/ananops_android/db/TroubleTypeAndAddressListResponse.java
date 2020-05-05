package com.example.ananops_android.db;

import com.example.ananops_android.entity.TroubleAddEntity;

import java.util.List;

public class TroubleTypeAndAddressListResponse {


    /**
     * code : 0
     * message : string
     * result : {"deviceTypeList":[{"code":"string","dictId":0,"groupId":0,"id":0,"latitude":0,"longitude":0,"mark":"string","name":"string","sort":0}],"emergencyLevelList":[{"code":"string","dictId":0,"groupId":0,"id":0,"latitude":0,"longitude":0,"mark":"string","name":"string","sort":0}],"troubleAddressList":[{"code":"string","dictId":0,"groupId":0,"id":0,"latitude":0,"longitude":0,"mark":"string","name":"string","sort":0}],"troubleLevelList":[{"code":"string","dictId":0,"groupId":0,"id":0,"latitude":0,"longitude":0,"mark":"string","name":"string","sort":0}],"troubleTypeList":[{"code":"string","dictId":0,"groupId":0,"id":0,"latitude":0,"longitude":0,"mark":"string","name":"string","sort":0}]}
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
        private List<TroubleAddEntity> deviceTypeList;
        private List<TroubleAddEntity> emergencyLevelList;
        private List<TroubleAddEntity> troubleAddressList;
        private List<TroubleAddEntity> troubleLevelList;
        private List<TroubleAddEntity> troubleTypeList;

        public List<TroubleAddEntity> getDeviceTypeList() {
            return deviceTypeList;
        }

        public void setDeviceTypeList(List<TroubleAddEntity> deviceTypeList) {
            this.deviceTypeList = deviceTypeList;
        }

        public List<TroubleAddEntity> getEmergencyLevelList() {
            return emergencyLevelList;
        }

        public void setEmergencyLevelList(List<TroubleAddEntity> emergencyLevelList) {
            this.emergencyLevelList = emergencyLevelList;
        }

        public List<TroubleAddEntity> getTroubleAddressList() {
            return troubleAddressList;
        }

        public void setTroubleAddressList(List<TroubleAddEntity> troubleAddressList) {
            this.troubleAddressList = troubleAddressList;
        }

        public List<TroubleAddEntity> getTroubleLevelList() {
            return troubleLevelList;
        }

        public void setTroubleLevelList(List<TroubleAddEntity> troubleLevelList) {
            this.troubleLevelList = troubleLevelList;
        }

        public List<TroubleAddEntity> getTroubleTypeList() {
            return troubleTypeList;
        }

        public void setTroubleTypeList(List<TroubleAddEntity> troubleTypeList) {
            this.troubleTypeList = troubleTypeList;
        }
    }
}
