package com.example.ananops_android.db;

public class LoginRequest {
    /**
     * userName : 15611237123
     * password : 123456
     */

    private String username;
    private String password;
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String scope;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = "password";
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = "ananops-client-gateway";
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = "ananopsClientSecret";
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = "*";
    }
}
