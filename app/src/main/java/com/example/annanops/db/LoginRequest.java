package com.example.annanops.db;

public class LoginRequest {
    /**
     * userName : 15611237123
     * password : 123456
     */
    public static final String RESPONSE_OK = "0000";
    public static final String TOKEN_INVALID = "1001";
    public static final String TOKEN_INVALID2 = "1002";
    public static final String VIDEO_INVALID = "1003";
    public static final String PASSWORD_ERROR = "1005";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String userName;
    private String password;


}
