package com.example.ananops_android.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.db.LoginRequest;
import com.example.ananops_android.db.UserDataManager;

public class LoginActivity1 extends AppCompatActivity {
    public int pwdresetFlag=0;
    private EditText mAccount;                        //用户名编辑
    private EditText mPwd;                            //密码编辑
    private Button mRegisterButton;                   //注册按钮
    private Button mLoginButton;                      //登录按钮
    private Button mCancleButton;                     //注销按钮
    private CheckBox mRememberCheck;
    private SharedPreferences login_sp;
    private String userNameValue,passwordValue;

    private View loginView;                           //登录
    private View loginSuccessView;
    private TextView loginSuccessShow;
    private TextView mChangepwdText;
   private UserDataManager mUserDataManager;         //用户数据管理类
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
      //  SPUtils.getInstance().init(this);
        mAccount = (EditText) findViewById(R.id.login_edit_account);
        mPwd = (EditText) findViewById(R.id.login_edit_pwd);
        mRegisterButton = (Button) findViewById(R.id.login_btn_register);
        mLoginButton = (Button) findViewById(R.id.login_btn_login);
      //  mCancleButton = (Button) findViewById(R.id.login_btn_cancle);
        loginView = findViewById(R.id.login_view);
        loginSuccessView = findViewById(R.id.login_success_view);
        loginSuccessShow = (TextView) findViewById(R.id.login_success_show);
        mChangepwdText = (TextView) findViewById(R.id.login_text_change_pwd);
        mRememberCheck = (CheckBox) findViewById(R.id.Login_Remember);
        login_sp = getSharedPreferences("userInfo", 0);

        String name=login_sp.getString("USER_NAME", "");
        String pwd =login_sp.getString("PASSWORD", "");
        boolean choseRemember =login_sp.getBoolean("mRememberCheck", false);
        boolean choseAutoLogin =login_sp.getBoolean("mAutologinCheck", false);
        if(choseRemember){
            mAccount.setText(name);
            mPwd.setText(pwd);
            mRememberCheck.setChecked(true);
        }
        mRegisterButton.setOnClickListener(mListener);                      //采用OnClickListener方法设置不同按钮按下之后的监听事件
        mLoginButton.setOnClickListener(mListener);
        mCancleButton.setOnClickListener(mListener);
        mChangepwdText.setOnClickListener(mListener);

    //    ImageView image = (ImageView) findViewById(R.id.logo);             //使用ImageView显示logo
      //  image.setImageResource(R.drawable.logo);

       if (mUserDataManager == null) {
          mUserDataManager = new UserDataManager(this);
          mUserDataManager.openDataBase();                              //建立本地数据库
       }
       intentFilter=new IntentFilter();
       intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//系统要添加什么样的广播，添加对应的action
        networkChangeReceiver=new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }
   OnClickListener mListener = new OnClickListener() {                  //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
              //  case R.id.login_btn_register:                            //登录界面的注册按钮
               //   Intent intent_Login_to_Register = new Intent(LoginActivity1.this,Register.class) ;    //切换Login Activity至User Activity
                //  startActivity(intent_Login_to_Register);
                //    finish();
                 //   break;
                case R.id.login_btn_login:                              //登录界面的登录按钮
                    login();
                    break;
           //     case R.id.login_btn_cancle:                             //登录界面的注销按钮
              //      cancel();
            //        break;
               // case R.id.login_text_change_pwd:                             //登录界面的修改密码按钮
                //   Intent intent_Login_to_reset = new Intent(LoginActivity1.this,Resetpwd.class) ;    //切换Login Activity至User Activity
                 //  startActivity(intent_Login_to_reset);
               //     finish();
                //    break;
            }
        }
    };
    public void login() {                                              //登录按钮监听事件
        if (isUserNameAndPwdValid()) {
            final String userName = mAccount.getText().toString().trim();    //获取当前输入的用户名和密码信息
            final String userPwd = mPwd.getText().toString().trim();
            final SharedPreferences.Editor editor =login_sp.edit();
                //??
                final LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUsername(userName);
                loginRequest.setPassword(userPwd);
//                Net.instance.login(loginRequest)
//                Net.instance.login(userName,userPwd,"1","password","ananops-client-gateway","ananopsClientSecret","*")
//                    .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<LoginResponse>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.v("LoginTime", System.currentTimeMillis() + "");
//                                e.printStackTrace();
//                                Toast.makeText(LoginActivity1.this, "网络异常，请检查网络状态后登陆", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onNext(LoginResponse loginResponse) {
//                                Log.v("LoginTime", System.currentTimeMillis() + "");
//                                if (TextUtils.equals(loginResponse.getCode(),"200")) {
//                                  //  String token = loginResponse.getToken();
//                                   // SPUtils.getInstance().putString("TOKEN", token);
//                                   // SPUtils.getInstance().putString("phoneNumber", userName);
//                                  //  SPUtils.getInstance().putString("password",userPwd);
//                                 //   SPUtils.getInstance().putString("userId", loginResponse.getUserIndex());
//                                    //保存用户名和密码
//                                    editor.putString("USER_NAME", userName);
//                                    editor.putString("PASSWORD", userPwd);
//                                    //是否记住密码
//                                    if(mRememberCheck.isChecked()){
//                                        editor.putBoolean("mRememberCheck", true);
//                                    }else{
//                                        editor.putBoolean("mRememberCheck", false);
//                                    }
//                                    editor.commit();
//                                    Intent intent1 = new Intent(LoginActivity1.this, UserMainActivity.class);
//                                    startActivity(intent1);
//                                    finish();
//                                } //else if (TextUtils.equals(loginResponse.getRetn(), LoginRequest.PASSWORD_ERROR)) {
                                    Toast.makeText(LoginActivity1.this, "密码错误", Toast.LENGTH_SHORT).show();
                                }// else {
                               //     Toast.makeText(LoginActivity1.this, loginResponse.getDesc(), Toast.LENGTH_SHORT).show();
                              //  }
                          //  }
//                        });
                //??

     //   }
    }
    public void cancel() {           //注销
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();    //获取当前输入的用户名和密码信息
            String userPwd = mPwd.getText().toString().trim();
            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if(result==1){                                             //返回1说明用户名和密码均正确
               Toast.makeText(this, getString(R.string.cancel_success),Toast.LENGTH_SHORT).show();//登录成功提示
                mPwd.setText("");
                mAccount.setText("");
                mUserDataManager.deleteUserDatabyname(userName);
            }else if(result==0){
            Toast.makeText(this, getString(R.string.cancel_fail),Toast.LENGTH_SHORT).show();  //登录失败提示
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

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //系统服务类，用于管理网络连接
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is aviliable", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unaviliable", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onResume() {
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }
    @Override
    protected void onPause() {
        if (mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }
}
