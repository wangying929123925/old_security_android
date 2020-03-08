package com.example.ananops_android.db;

import org.json.JSONArray;

public class AllUnauthorizedTaskRequest {


    /**
     * pageNum : 0
     * pageSize : 0
     * userId : 1
     */

    private int pageNum;
    private int pageSize;
    private long userId;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
