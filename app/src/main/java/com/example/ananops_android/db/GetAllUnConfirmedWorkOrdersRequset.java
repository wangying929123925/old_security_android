package com.example.ananops_android.db;

public class GetAllUnConfirmedWorkOrdersRequset {

    /**
     * pageNum : 0
     * pageSize : 0
     */

    private int pageNum;
    private int pageSize;
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
