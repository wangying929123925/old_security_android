package com.example.ananops_android.entity;

public class TroubleAddress {

    /**
     * troubleAddress : 银行北门
     * troubleLongitude : null
     * troubleLatitude : null
     */

    private String troubleAddress;
    private Double troubleLongitude;
    private Double troubleLatitude;

    public String getTroubleAddress() {
        return troubleAddress;
    }

    public void setTroubleAddress(String troubleAddress) {
        this.troubleAddress = troubleAddress;
    }

    public Double getTroubleLongitude() {
        return troubleLongitude;
    }

    public void setTroubleLongitude(Double troubleLongitude) {
        this.troubleLongitude = troubleLongitude;
    }

    public Double getTroubleLatitude() {
        return troubleLatitude;
    }

    public void setTroubleLatitude(Double troubleLatitude) {
        this.troubleLatitude = troubleLatitude;
    }
}
