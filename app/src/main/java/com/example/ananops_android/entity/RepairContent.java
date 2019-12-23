package com.example.ananops_android.entity;

public class RepairContent {
    private String order_depart;//单位
    private String repair_id;//单号
    private String check_group;//组别
    private String repair_address;//地址
    private String repair_content;//物件
    private String repair_man;//维修人
    private String repair_time;//报修时间
    private String repair_status;//状态
    private int repair_image_id;//图标




    public String getOrder_depart() {
        return order_depart;
    }
    public void setOrder_depart(String order_depart) {
        this.order_depart = order_depart;
    }

    public String getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(String repair_id) {
        this.repair_id = repair_id;
    }

    public String getCheck_group() {
        return check_group;
    }

    public String getRepair_content() {
        return repair_content;
    }

    public void setRepair_content(String repair_content) {
        this.repair_content = repair_content;
    }

    public void setCheck_group(String check_group) {
        this.check_group = check_group;
    }

    public String getRepair_address() {
        return repair_address;
    }

    public void setRepair_address(String repair_address) {
        this.repair_address = repair_address;
    }

    public String getRepair_man() {
        return repair_man;
    }

    public void setRepair_man(String repair_man) {
        this.repair_man = repair_man;
    }

    public String getRepair_time() {
        return repair_time;
    }

    public void setRepair_time(String repair_time) {
        this.repair_time = repair_time;
    }

    public String getRepair_status() {
        return repair_status;
    }

    public void setRepair_status(String repair_status) {
        this.repair_status = repair_status;
    }

    public int getRepair_image_id() {
        return repair_image_id;
    }

    public void setRepair_image_id(int repair_image_id) {
        this.repair_image_id = repair_image_id;
    }


}
