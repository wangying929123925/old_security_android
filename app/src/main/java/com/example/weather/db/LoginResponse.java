package com.example.weather.db;

public class LoginResponse {

    /**
     * Retn : 0000
     * Token : d86a7247347bc08175cdf736144f77cf
     * Desc : login success
     * UserIndex : 79
     */

    private String Retn;
    private String Token;
    private String Desc;
    private String UserIndex;

    public String getRetn() {
        return Retn;
    }

    public void setRetn(String Retn) {
        this.Retn = Retn;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public String getUserIndex() {
        return UserIndex;
    }

    public void setUserIndex(String UserIndex) {
        this.UserIndex = UserIndex;
    }
}
