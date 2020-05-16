package com.example.ananops_android.db;

import java.util.List;

public class InspectionItemAddRequest {

    /**
     * actualFinishTime : 2019-8-24 11:11:11
     * actualStartTime : 2019-8-24 11:11:11
     * attachmentIds : [0]
     * count : 0
     * days : 0
     * description : string
     * frequency : 0
     * id : 0
     * inspectionTaskId : 0
     * itemLatitude : 0
     * itemLongitude : 0
     * itemName : string
     * location : string
     * maintainerId : 0
     * result : string
     * scheduledStartTime : 2019-8-24 11:11:11
     * status : 0
     * userId : 0
     */

    private String actualFinishTime;
    private String actualStartTime;
    private int count;
    private int days;
    private String description;
    private int frequency;
    private Long id;
    private Long inspectionTaskId;
    private Double itemLatitude;
    private Double itemLongitude;
    private String itemName;
    private String location;
    private Long maintainerId;
    private String result;
    private String scheduledStartTime;
    private int status;
    private Long userId;
    private List<Integer> attachmentIds;

    public String getActualFinishTime() {
        return actualFinishTime;
    }

    public void setActualFinishTime(String actualFinishTime) {
        this.actualFinishTime = actualFinishTime;
    }

    public String getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(String actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInspectionTaskId() {
        return inspectionTaskId;
    }

    public void setInspectionTaskId(Long inspectionTaskId) {
        this.inspectionTaskId = inspectionTaskId;
    }

    public Double getItemLatitude() {
        return itemLatitude;
    }

    public void setItemLatitude(Double itemLatitude) {
        this.itemLatitude = itemLatitude;
    }

    public Double getItemLongitude() {
        return itemLongitude;
    }

    public void setItemLongitude(Double itemLongitude) {
        this.itemLongitude = itemLongitude;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getMaintainerId() {
        return maintainerId;
    }

    public void setMaintainerId(Long  maintainerId) {
        this.maintainerId = maintainerId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getScheduledStartTime() {
        return scheduledStartTime;
    }

    public void setScheduledStartTime(String scheduledStartTime) {
        this.scheduledStartTime = scheduledStartTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Integer> getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(List<Integer> attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}
