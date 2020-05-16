package com.example.ananops_android.db;

import java.util.List;

public class EngineerListByGroupIdResponse {

    /**
     * code : 0
     * message : string
     * result : {"endRow":0,"firstPage":0,"hasNextPage":true,"hasPreviousPage":true,"isFirstPage":true,"isLastPage":true,"lastPage":0,"list":[{"education":"string","email":"string","employmentStartTime":"2020-05-14T15:32:01.768Z","id":0,"identityExpirationDate":"2020-05-14T15:32:01.768Z","identityNumber":"string","identityPhoto":"string","location":"string","loginName":"string","mobileNo":"string","position":"string","roleCode":"string","roleId":0,"roleName":"string","roleStatus":"string","sex":"string","status":"string","title":"string","titleCeNumber":"string","titleCePhoto":"string","userCode":"string","userId":0,"userName":"string"}],"navigateFirstPage":0,"navigateLastPage":0,"navigatePages":0,"navigatepageNums":[0],"nextPage":0,"pageNum":0,"pageSize":0,"pages":0,"prePage":0,"size":0,"startRow":0,"total":0}
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
         * endRow : 0
         * firstPage : 0
         * hasNextPage : true
         * hasPreviousPage : true
         * isFirstPage : true
         * isLastPage : true
         * lastPage : 0
         * list : [{"education":"string","email":"string","employmentStartTime":"2020-05-14T15:32:01.768Z","id":0,"identityExpirationDate":"2020-05-14T15:32:01.768Z","identityNumber":"string","identityPhoto":"string","location":"string","loginName":"string","mobileNo":"string","position":"string","roleCode":"string","roleId":0,"roleName":"string","roleStatus":"string","sex":"string","status":"string","title":"string","titleCeNumber":"string","titleCePhoto":"string","userCode":"string","userId":0,"userName":"string"}]
         * navigateFirstPage : 0
         * navigateLastPage : 0
         * navigatePages : 0
         * navigatepageNums : [0]
         * nextPage : 0
         * pageNum : 0
         * pageSize : 0
         * pages : 0
         * prePage : 0
         * size : 0
         * startRow : 0
         * total : 0
         */

        private int endRow;
        private int firstPage;
        private boolean hasNextPage;
        private boolean hasPreviousPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private int lastPage;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int navigatePages;
        private int nextPage;
        private int pageNum;
        private int pageSize;
        private int pages;
        private int prePage;
        private int size;
        private int startRow;
        private int total;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
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

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
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

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

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

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * education : string
             * email : string
             * employmentStartTime : 2020-05-14T15:32:01.768Z
             * id : 0
             * identityExpirationDate : 2020-05-14T15:32:01.768Z
             * identityNumber : string
             * identityPhoto : string
             * location : string
             * loginName : string
             * mobileNo : string
             * position : string
             * roleCode : string
             * roleId : 0
             * roleName : string
             * roleStatus : string
             * sex : string
             * status : string
             * title : string
             * titleCeNumber : string
             * titleCePhoto : string
             * userCode : string
             * userId : 0
             * userName : string
             */

            private String education;
            private String email;
            private String employmentStartTime;
            private Long id;
            private String identityExpirationDate;
            private String identityNumber;
            private String identityPhoto;
            private String location;
            private String loginName;
            private String mobileNo;
            private String position;
            private String roleCode;
            private Long roleId;
            private String roleName;
            private String roleStatus;
            private String sex;
            private String status;
            private String title;
            private String titleCeNumber;
            private String titleCePhoto;
            private String userCode;
            private String userId;
            private String userName;

            public String getEducation() {
                return education;
            }

            public void setEducation(String education) {
                this.education = education;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getEmploymentStartTime() {
                return employmentStartTime;
            }

            public void setEmploymentStartTime(String employmentStartTime) {
                this.employmentStartTime = employmentStartTime;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getIdentityExpirationDate() {
                return identityExpirationDate;
            }

            public void setIdentityExpirationDate(String identityExpirationDate) {
                this.identityExpirationDate = identityExpirationDate;
            }

            public String getIdentityNumber() {
                return identityNumber;
            }

            public void setIdentityNumber(String identityNumber) {
                this.identityNumber = identityNumber;
            }

            public String getIdentityPhoto() {
                return identityPhoto;
            }

            public void setIdentityPhoto(String identityPhoto) {
                this.identityPhoto = identityPhoto;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getMobileNo() {
                return mobileNo;
            }

            public void setMobileNo(String mobileNo) {
                this.mobileNo = mobileNo;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getRoleCode() {
                return roleCode;
            }

            public void setRoleCode(String roleCode) {
                this.roleCode = roleCode;
            }

            public Long getRoleId() {
                return roleId;
            }

            public void setRoleId(Long roleId) {
                this.roleId = roleId;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public String getRoleStatus() {
                return roleStatus;
            }

            public void setRoleStatus(String roleStatus) {
                this.roleStatus = roleStatus;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitleCeNumber() {
                return titleCeNumber;
            }

            public void setTitleCeNumber(String titleCeNumber) {
                this.titleCeNumber = titleCeNumber;
            }

            public String getTitleCePhoto() {
                return titleCePhoto;
            }

            public void setTitleCePhoto(String titleCePhoto) {
                this.titleCePhoto = titleCePhoto;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
