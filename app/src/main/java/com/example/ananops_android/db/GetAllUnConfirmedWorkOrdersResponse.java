package com.example.ananops_android.db;

import com.example.ananops_android.entity.InspectionInfo;

import java.util.List;

public class GetAllUnConfirmedWorkOrdersResponse {

    /**
     * code : 200
     * message : 操作成功
     * result : {"pageNum":1,"pageSize":1,"size":1,"startRow":0,"endRow":0,"total":"1","pages":1,"list":[{"id":"800607631264714752","creator":"超级管理员","creatorId":"1","createdTime":"2020-01-10 15:04:27","lastOperator":"超级管理员","lastOperatorId":"1","updateTime":"2020-01-10 15:18:22","type":"inspection","status":2,"projectId":"0","projectName":"项目","principalId":"1","principalName":null,"facilitatorId":"1","facilitatorName":"paascloud","maintainerId":null,"maintainerName":null,"taskName":"string","scheduledStartTime":1579835471000,"description":null}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * pageSize : 1
         * size : 1
         * startRow : 0
         * endRow : 0
         * total : 1
         * pages : 1
         * list : [{"id":"800607631264714752","creator":"超级管理员","creatorId":"1","createdTime":"2020-01-10 15:04:27","lastOperator":"超级管理员","lastOperatorId":"1","updateTime":"2020-01-10 15:18:22","type":"inspection","status":2,"projectId":"0","projectName":"项目","principalId":"1","principalName":null,"facilitatorId":"1","facilitatorName":"paascloud","maintainerId":null,"maintainerName":null,"taskName":"string","scheduledStartTime":1579835471000,"description":null}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private String total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<InspectionInfo> list;
        private List<Integer> navigatepageNums;

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

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<InspectionInfo> getList() {
            return list;
        }

        public void setList(List<InspectionInfo> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }


            /**
             * id : 800607631264714752
             * creator : 超级管理员
             * creatorId : 1
             * createdTime : 2020-01-10 15:04:27
             * lastOperator : 超级管理员
             * lastOperatorId : 1
             * updateTime : 2020-01-10 15:18:22
             * type : inspection
             * status : 2
             * projectId : 0
             * projectName : 项目
             * principalId : 1
             * principalName : null
             * facilitatorId : 1
             * facilitatorName : paascloud
             * maintainerId : null
             * maintainerName : null
             * taskName : string
             * scheduledStartTime : 1579835471000
             * description : null
             */


    }
}
