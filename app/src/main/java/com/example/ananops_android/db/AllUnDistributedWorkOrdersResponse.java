package com.example.ananops_android.db;

import com.example.ananops_android.entity.InspectionInfo;

import java.util.ArrayList;
import java.util.List;

public class AllUnDistributedWorkOrdersResponse {

    /**
     * code : 200
     * message : 操作成功
     * result : {"pageNum":1,"pageSize":0,"size":0,"startRow":0,"endRow":0,"total":"0","pages":0,"list":[],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[],"navigateFirstPage":0,"navigateLastPage":0,"firstPage":0,"lastPage":0}
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
         * pageNum : 1
         * pageSize : 0
         * size : 0
         * startRow : 0
         * endRow : 0
         * total : 0
         * pages : 0
         * list : []
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : []
         * navigateFirstPage : 0
         * navigateLastPage : 0
         * firstPage : 0
         * lastPage : 0
         */

        private Integer pageNum;
        private Integer pageSize;
        private Integer size;
        private Integer startRow;
        private Integer endRow;
        private Float total;
        private ArrayList<InspectionInfo> list;

        public Integer getPageNum() {
            return pageNum;
        }

        public void setPageNum(Integer pageNum) {
            this.pageNum = pageNum;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Integer getStartRow() {
            return startRow;
        }

        public void setStartRow(Integer startRow) {
            this.startRow = startRow;
        }

        public Integer getEndRow() {
            return endRow;
        }

        public void setEndRow(Integer endRow) {
            this.endRow = endRow;
        }

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
            this.total = total;
        }

       public ArrayList<InspectionInfo> getList() {
            return list;
        }

        public void setList(ArrayList<InspectionInfo> list) {
            this.list = list;
        }

    }
}
