package com.example.ananops_android.entity;

public class UserLogin {
    private String useName;
    private String passWord;
    public static volatile int useCode=1;

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


}


