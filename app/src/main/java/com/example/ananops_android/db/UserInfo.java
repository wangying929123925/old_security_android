package com.example.ananops_android.db;

import java.util.List;

public class UserInfo {

    private String code;
    private String message;
    private UserInfoResult result;
    public static String TOKEN="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbIioiXSwibG9naW5OYW1lIjoiYWRtaW4iLCJleHAiOjE1Nzk0MjI4MzYsImF1dGhvcml0aWVzIjpbIi9hY3Rpb24vYmF0Y2hEZWxldGVCeUlkTGlzdCIsIi9hY3Rpb24vY2hlY2tBY3Rpb25Db2RlIiwiL21lbnUvc2F2ZSIsIi9yb2xlL21vZGlmeVJvbGVTdGF0dXNCeUlkIiwiL3JvbGUvYmluZEFjdGlvbiIsIi9hY3Rpb24vZGVsZXRlQWN0aW9uQnlJZC8qIiwiL21lbnUvbW9kaWZ5U3RhdHVzIiwiL2dyb3VwL21vZGlmeVN0YXR1cyIsIi9yb2xlL2JpbmRVc2VyIiwiL3VhYy9yb2xlL3F1ZXJ5TGlzdCIsIi9yb2xlL2RlbGV0ZVJvbGVCeUlkLyoiLCIvZGljdC9tb2RpZnlTdGF0dXMiLCIvZGljdC9kZWxldGVCeUlkLyoiLCIvdXNlci9zYXZlIiwiL3VzZXIvcmVzZXRMb2dpblB3ZCIsIi9tZW51L2RlbGV0ZUJ5SWQvKiIsIi9ncm91cC9kZWxldGVCeUlkLyoiLCIvdXNlci9iaW5kUm9sZSIsIi9hY3Rpb24vcXVlcnlMaXN0V2l0aFBhZ2UiLCIvYWN0aW9uL21vZGlmeVN0YXR1cyIsIi9ncm91cC9zYXZlIiwiL3JvbGUvc2F2ZSIsIi9hY3Rpb24vc2F2ZSIsIi91c2VyL21vZGlmeVVzZXJTdGF0dXNCeUlkIiwiL2dyb3VwL2JpbmRVc2VyIiwiL2RpY3Qvc2F2ZSIsIi9hY3Rpb24vY2hlY2tVcmwiLCIvcm9sZS9iaW5kTWVudSIsIi9yb2xlL2JhdGNoRGVsZXRlQnlJZExpc3QiXSwianRpIjoiZjI2YjNmMWEtZTk4Ni00MjFkLTk0YmMtNzkxMWZkODFkMjYxIiwiY2xpZW50X2lkIjoiYW5hbm9wcy1jbGllbnQtdWFjIiwidGltZXN0YW1wIjoxNTc2ODMwODM2NDM4fQ.Jk9Yy96oRTzYxsMLkQrLu44Odn4OV1OEv7p9L_TobW0";
  //  public String ContentType="application/json";
    public volatile static int user_code=1;
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

    public UserInfoResult getResult() {
        return result;
    }

    public void setResult(UserInfoResult result) {
        this.result = result;
    }



    public class UserInfoResult {
        private String userId;
        private List<Role> roles;

        public String getId() {
            return userId;
        }

        public void setId(String userId) {
            this.userId = userId;
        }

        public List<Role> getRoles() {
            return roles;
        }

        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }

        public class Role {
         private String roleName;

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }
        }
    }


}
