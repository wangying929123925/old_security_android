package com.example.ananops_android.entity;

public class TroubleAddEntity {


    /**
     * code : string
     * dictId : 0
     * groupId : 0
     * id : 0
     * latitude : 0
     * longitude : 0
     * mark : string
     * name : string
     * sort : 0
     */

    private int code;
    private long dictId;
    private long groupId;
    private long id;
    private Double latitude;
    private Double longitude;
    private String mark;
    private String name;
    private int sort;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getDictId() {
        return dictId;
    }

    public void setDictId(long dictId) {
        this.dictId = dictId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
