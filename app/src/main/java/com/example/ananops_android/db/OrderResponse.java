package com.example.ananops_android.db;

import com.example.ananops_android.entity.RepairContent;

import java.util.List;

public class OrderResponse {

    /**
     * code : 200
     * message : 操作成功
     * result : {"pageNum":2,"pageSize":10,"size":10,"startRow":11,"endRow":20,"total":"27","pages":3,"list":[{"id":"867285729607162880","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-11 15:01:56","lastOperator":"朴叙俊","lastOperatorId":"838497501970433024","updateTime":"2020-05-14 23:18:02","pageNum":null,"pageSize":null,"orderBy":null,"objectType":null,"objectId":null,"userId":"838245741926286336","title":"test2020","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-04-11 00:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":"2020-04-14 00:00:00","requestLatitude":null,"requestLongitude":null,"status":4,"totalCost":0,"clearingForm":null,"call":null,"addressName":"","contractId":null,"troubleReason":null,"delayReason":null,"level":1,"suggestion":null,"result":null,"note":null},{"id":"861655455343057920","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-03 20:35:35","lastOperator":"金信","lastOperatorId":"838542415148090368","updateTime":"2020-04-27 23:42:56","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"北邮南门","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-04-03 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":39.95869,"requestLongitude":39.95869,"status":3,"totalCost":0,"clearingForm":null,"call":null,"addressName":"金源新燕莎Mall","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"861653406199061504","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-03 20:31:30","lastOperator":"朴叙俊","lastOperatorId":"838497501970433024","updateTime":"2020-05-15 14:04:27","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"永外车站路12号北京南站F2层","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":"852357504124847104","appointTime":"2020-04-03 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":30.773612,"requestLongitude":30.773612,"status":8,"totalCost":0,"clearingForm":null,"call":null,"addressName":"武汉天河国际机场","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"861602833638628352","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-03 18:51:02","lastOperator":"凌凌漆","lastOperatorId":"838245741926286336","updateTime":"2020-04-03 18:51:02","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"北邮小二门","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-04-03 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":39.912413,"requestLongitude":39.912413,"status":2,"totalCost":0,"clearingForm":null,"call":null,"addressName":"首都医科大学附属北京儿","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"861568560286145536","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-03 17:42:56","lastOperator":"金信","lastOperatorId":"838542415148090368","updateTime":"2020-04-28 23:09:30","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"北邮小二门","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-04-03 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":39.961554,"requestLongitude":39.961554,"status":14,"totalCost":0,"clearingForm":null,"call":null,"addressName":"北京邮电大学","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"861519475931880448","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-03 16:05:25","lastOperator":"金信","lastOperatorId":"838542415148090368","updateTime":"2020-04-28 23:09:24","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"北邮小二门","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-04-03 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":39.89491,"requestLongitude":39.89491,"status":3,"totalCost":0,"clearingForm":null,"call":null,"addressName":"北京西站","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"861512290275433472","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-03 15:51:08","lastOperator":"凌凌漆","lastOperatorId":"838245741926286336","updateTime":"2020-04-03 15:51:08","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"北邮南门","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-04-03 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":39.992806,"requestLongitude":39.992806,"status":2,"totalCost":0,"clearingForm":null,"call":null,"addressName":"北京大学","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"851311239211523072","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-03-20 14:03:28","lastOperator":"朴叙俊","lastOperatorId":"838497501970433024","updateTime":"2020-03-21 19:44:49","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"北邮西门","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-03-20 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":39.865246,"requestLongitude":39.865246,"status":4,"totalCost":0,"clearingForm":null,"call":null,"addressName":"北京南站","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"841967452186221568","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-03-07 16:39:02","lastOperator":"朴叙俊","lastOperatorId":"838497501970433024","updateTime":"2020-03-10 21:32:55","pageNum":null,"pageSize":null,"orderBy":null,"objectType":null,"objectId":null,"userId":"838245741926286336","title":"湿度传感器维护","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-03-07 00:00:00","scheduledFinishTime":"2020-03-31 00:00:00","actualFinishTime":null,"scheduledStartTime":"2020-03-09 00:00:00","actualStartTime":null,"deadline":"2020-03-15 00:00:00","requestLatitude":116.358104,"requestLongitude":39.961554,"status":4,"totalCost":1000,"clearingForm":null,"call":null,"addressName":"西土城路10号","contractId":null,"troubleReason":null,"delayReason":null,"level":1,"suggestion":null,"result":null,"note":null},{"id":"841892706551602176","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-03-07 14:10:31","lastOperator":"朴叙俊","lastOperatorId":"838497501970433024","updateTime":"2020-03-20 21:01:08","pageNum":null,"pageSize":null,"orderBy":null,"objectType":0,"objectId":null,"userId":"838245741926286336","title":"北京邮电大学图书馆","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":"838470611079529472","appointTime":"2020-03-07 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":"2020-03-20 01:00:00","requestLatitude":39.89491,"requestLongitude":39.89491,"status":5,"totalCost":0,"clearingForm":null,"call":null,"addressName":"北京西站","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null}],"prePage":1,"nextPage":3,"isFirstPage":false,"isLastPage":false,"hasPreviousPage":true,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3],"navigateFirstPage":1,"navigateLastPage":3,"firstPage":1,"lastPage":3}
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
         * pageNum : 2
         * pageSize : 10
         * size : 10
         * startRow : 11
         * endRow : 20
         * total : 27
         * pages : 3
         * list : [{"id":"867285729607162880","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-11 15:01:56","lastOperator":"朴叙俊","lastOperatorId":"838497501970433024","updateTime":"2020-05-14 23:18:02","pageNum":null,"pageSize":null,"orderBy":null,"objectType":null,"objectId":null,"userId":"838245741926286336","title":"test2020","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-04-11 00:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":"2020-04-14 00:00:00","requestLatitude":null,"requestLongitude":null,"status":4,"totalCost":0,"clearingForm":null,"call":null,"addressName":"","contractId":null,"troubleReason":null,"delayReason":null,"level":1,"suggestion":null,"result":null,"note":null},{"id":"861655455343057920","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-03 20:35:35","lastOperator":"金信","lastOperatorId":"838542415148090368","updateTime":"2020-04-27 23:42:56","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"北邮南门","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-04-03 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":39.95869,"requestLongitude":39.95869,"status":3,"totalCost":0,"clearingForm":null,"call":null,"addressName":"金源新燕莎Mall","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"861653406199061504","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-03 20:31:30","lastOperator":"朴叙俊","lastOperatorId":"838497501970433024","updateTime":"2020-05-15 14:04:27","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"永外车站路12号北京南站F2层","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":"852357504124847104","appointTime":"2020-04-03 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":30.773612,"requestLongitude":30.773612,"status":8,"totalCost":0,"clearingForm":null,"call":null,"addressName":"武汉天河国际机场","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"861602833638628352","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-03 18:51:02","lastOperator":"凌凌漆","lastOperatorId":"838245741926286336","updateTime":"2020-04-03 18:51:02","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"北邮小二门","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-04-03 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":39.912413,"requestLongitude":39.912413,"status":2,"totalCost":0,"clearingForm":null,"call":null,"addressName":"首都医科大学附属北京儿","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"861568560286145536","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-03 17:42:56","lastOperator":"金信","lastOperatorId":"838542415148090368","updateTime":"2020-04-28 23:09:30","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"北邮小二门","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-04-03 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":39.961554,"requestLongitude":39.961554,"status":14,"totalCost":0,"clearingForm":null,"call":null,"addressName":"北京邮电大学","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"861519475931880448","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-03 16:05:25","lastOperator":"金信","lastOperatorId":"838542415148090368","updateTime":"2020-04-28 23:09:24","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"北邮小二门","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-04-03 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":39.89491,"requestLongitude":39.89491,"status":3,"totalCost":0,"clearingForm":null,"call":null,"addressName":"北京西站","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"861512290275433472","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-04-03 15:51:08","lastOperator":"凌凌漆","lastOperatorId":"838245741926286336","updateTime":"2020-04-03 15:51:08","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"北邮南门","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-04-03 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":39.992806,"requestLongitude":39.992806,"status":2,"totalCost":0,"clearingForm":null,"call":null,"addressName":"北京大学","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"851311239211523072","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-03-20 14:03:28","lastOperator":"朴叙俊","lastOperatorId":"838497501970433024","updateTime":"2020-03-21 19:44:49","pageNum":null,"pageSize":null,"orderBy":null,"objectType":1,"objectId":null,"userId":"838245741926286336","title":"北邮西门","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-03-20 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":null,"requestLatitude":39.865246,"requestLongitude":39.865246,"status":4,"totalCost":0,"clearingForm":null,"call":null,"addressName":"北京南站","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null},{"id":"841967452186221568","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-03-07 16:39:02","lastOperator":"朴叙俊","lastOperatorId":"838497501970433024","updateTime":"2020-03-10 21:32:55","pageNum":null,"pageSize":null,"orderBy":null,"objectType":null,"objectId":null,"userId":"838245741926286336","title":"湿度传感器维护","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":null,"appointTime":"2020-03-07 00:00:00","scheduledFinishTime":"2020-03-31 00:00:00","actualFinishTime":null,"scheduledStartTime":"2020-03-09 00:00:00","actualStartTime":null,"deadline":"2020-03-15 00:00:00","requestLatitude":116.358104,"requestLongitude":39.961554,"status":4,"totalCost":1000,"clearingForm":null,"call":null,"addressName":"西土城路10号","contractId":null,"troubleReason":null,"delayReason":null,"level":1,"suggestion":null,"result":null,"note":null},{"id":"841892706551602176","version":null,"creator":"凌凌漆","creatorId":"838245741926286336","createdTime":"2020-03-07 14:10:31","lastOperator":"朴叙俊","lastOperatorId":"838497501970433024","updateTime":"2020-03-20 21:01:08","pageNum":null,"pageSize":null,"orderBy":null,"objectType":0,"objectId":null,"userId":"838245741926286336","title":"北京邮电大学图书馆","principalId":"838542415148090368","projectId":"838547714164660224","facilitatorId":"838497501970433024","maintainerId":"838470611079529472","appointTime":"2020-03-07 01:00:00","scheduledFinishTime":null,"actualFinishTime":null,"scheduledStartTime":null,"actualStartTime":null,"deadline":"2020-03-20 01:00:00","requestLatitude":39.89491,"requestLongitude":39.89491,"status":5,"totalCost":0,"clearingForm":null,"call":null,"addressName":"北京西站","contractId":"1","troubleReason":null,"delayReason":null,"level":1,"suggestion":"","result":"0","note":null}]
         * prePage : 1
         * nextPage : 3
         * isFirstPage : false
         * isLastPage : false
         * hasPreviousPage : true
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3]
         * navigateFirstPage : 1
         * navigateLastPage : 3
         * firstPage : 1
         * lastPage : 3
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
        private List<RepairContent> list;
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

        public List<RepairContent> getList() {
            return list;
        }

        public void setList(List<RepairContent> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

    }
}
