package com.example.ananops_android.db;

public class ChangePasswordRequest {

    /**
     * oldPassword : 123456
     * newPassword : 123456
     * confirmPwd : 123456
     * loginName : 乐享服务商管理员
     */

    private String oldPassword;
    private String newPassword;
    private String confirmPwd;
    private String loginName;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
