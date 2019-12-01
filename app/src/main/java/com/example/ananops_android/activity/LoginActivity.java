package com.example.ananops_android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.entity.UserLogin;
import com.example.ananops_android.util.SPUtils;

public class LoginActivity extends AppCompatActivity {
    private EditText mAccount;                        //用户名编辑
    private EditText mPwd;                            //密码编辑
    private Button mLoginButton;                      //登录按钮
    private CheckBox mRememberCheck;
    private SharedPreferences login_sp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        SPUtils.getInstance().init(this);
        mAccount = (EditText) findViewById(R.id.login_edit_account);
        mPwd = (EditText) findViewById(R.id.login_edit_pwd);
        mLoginButton = (Button) findViewById(R.id.login_btn_login);
        mRememberCheck = (CheckBox) findViewById(R.id.Login_Remember);
        login_sp = getSharedPreferences("userInfo", 0);
        String name=login_sp.getString("USER_NAME", "");
        String pwd =login_sp.getString("PASSWORD", "");
        boolean choseRemember =login_sp.getBoolean("mRememberCheck", false);
        if(choseRemember){
            mAccount.setText(name);
            mPwd.setText(pwd);
            mRememberCheck.setChecked(true);
        }
        mLoginButton.setOnClickListener(mListener);
    }
    View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_btn_login:                              //登录界面的登录按钮
                    login();
                    break;
            }
    }};
    public void login()
    {
        if (isUserNameAndPwdValid()) {
            final String userName = mAccount.getText().toString().trim();    //获取当前输入的用户名和密码信息
            final String userPwd = mPwd.getText().toString().trim();
            final SharedPreferences.Editor editor =login_sp.edit();
            editor.putString("USER_NAME", userName);
            editor.putString("PASSWORD", userPwd);
            //是否记住密码
            if(mRememberCheck.isChecked()){
                editor.putBoolean("mRememberCheck", true);
            }else{
                editor.putBoolean("mRememberCheck", false);
            }
            editor.commit();
            if(userName.equals("2") && userPwd.equals("2")
                    ||(userName.equals("1")) && (userPwd.equals("1"))
                    ||(userName.equals("3")) && (userPwd.equals("3"))) {
                if(userName.equals("2") && userPwd.equals("2")){
                    UserLogin.useCode=2;
                }
                if(userName.equals("1") && userPwd.equals("1")){
                    UserLogin.useCode=1;
                }
                if(userName.equals("3") && userPwd.equals("3")){
                    UserLogin.useCode=3;
                }
                Intent intent1 = new Intent(LoginActivity.this, UserMainActivity.class);
                startActivity(intent1);
                finish();
            }
            else {
                Toast.makeText(LoginActivity.this, "用户名或密码错误！！！", Toast.LENGTH_SHORT).show();
                mAccount.setText("");
                mPwd.setText("");
            }
        }
    }
    public boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
