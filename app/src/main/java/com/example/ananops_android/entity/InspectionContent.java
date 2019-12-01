package com.example.ananops_android.entity;

public class InspectionContent {
    private String inspection_name;//名称
    private String inspection_id;  //单号
    private String device_name;    //设备
    private String inspection_time;   //报修时间
    private String inspection_status;//状态
    private int repair_image_id;//图标

    public String getInspection_name() {
        return inspection_name;
    }

    public void setInspection_name(String inspection_name) {
        this.inspection_name = inspection_name;
    }

    public String getInspection_id() {
        return inspection_id;
    }

    public void setInspection_id(String inspection_id) {
        this.inspection_id = inspection_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getInspection_time() {
        return inspection_time;
    }

    public void setInspection_time(String inspection_time) {
        this.inspection_time = inspection_time;
    }

    public String getInspection_status() {
        return inspection_status;
    }

    public void setInspection_status(String inspection_status) {
        this.inspection_status = inspection_status;
    }

    public int getRepair_image_id() {
        return repair_image_id;
    }

    public void setRepair_image_id(int repair_image_id) {
        this.repair_image_id = repair_image_id;
    }
}
