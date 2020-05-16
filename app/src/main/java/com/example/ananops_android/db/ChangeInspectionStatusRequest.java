package com.example.ananops_android.db;

public class ChangeInspectionStatusRequest {

    /**
     * loginAuthDto : {"groupId":0,"groupName":"string","loginName":"string","userId":0,"userName":"string"}
     * status : 0
     * statusMsg : string
     * taskId : 0
     */

    private LoginAuthDtoBean loginAuthDto;
    private int status;
    private String statusMsg;
    private Long taskId;

    public LoginAuthDtoBean getLoginAuthDto() {
        return loginAuthDto;
    }

    public void setLoginAuthDto(LoginAuthDtoBean loginAuthDto) {
        this.loginAuthDto = loginAuthDto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public static class LoginAuthDtoBean {
        /**
         * groupId : 0
         * groupName : string
         * loginName : string
         * userId : 0
         * userName : string
         */

        private int groupId;
        private String groupName;
        private String loginName;
        private int userId;
        private String userName;

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
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
