package com.example.ananops_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.annotation.ViewInject;
import com.example.ananops_android.db.ChangePasswordRequest;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.AnnotationUtils;
import com.example.ananops_android.util.SPUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChangePasswordActivity extends BaseActivity {
    @ViewInject(R.id.img_back)
    private ImageView img_back;
    @ViewInject(R.id.txt_title)
    private TextView txt_title;
    @ViewInject(R.id.change_password_old)
    private EditText change_password_old;
    @ViewInject(R.id.change_password_new)
    private EditText change_password_new;
    @ViewInject(R.id.change_password_new1)
    private EditText change_password_new1;
    @ViewInject(R.id.change_password_btn)
    private Button change_password_btn;
    private Context mContext;
    private String originalPsw;
    private String newPsw;
    private String newPswAgain;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_change_password);
        AnnotationUtils.injectViews(this);
        initView();

    }
    private void initView() {
        txt_title.setText("修改密码");
        img_back.setOnClickListener(v -> {
            finish();
        });
        change_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getText();
                String pwd = SPUtils.getInstance(mContext).getString("PASSWORD", "");
                if (TextUtils.isEmpty(originalPsw)){
                    Toast.makeText(mContext,"请输入原始密码",Toast.LENGTH_SHORT).show();
                }else if (!(originalPsw).equals(pwd)){
                    Toast.makeText(mContext,"输入的密码与原始密码不一致",Toast.LENGTH_SHORT).show();
                }else if (pwd.equals(newPsw)){
                    Toast.makeText(mContext,"输入的新密码与原始密码不能一致",Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(newPsw)){
                    Toast.makeText(mContext,"请输入新密码",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(newPswAgain)){
                    Toast.makeText(mContext,"请再次输入新密码",Toast.LENGTH_SHORT).show();
                }else if (!newPsw.equals(newPswAgain)){
                    Toast.makeText(mContext,"两次输入的新密码不一致",Toast.LENGTH_SHORT).show();
                }else{
                    //  Toast.makeText(ModifyPswActivity.this,"新密码设置成功",Toast.LENGTH_SHORT).show();
                    //修改登录成功后保存在SharedPreferences中的密码
                    modifyPsw();

                }
            }
        });
    }

    private void getText() {
        originalPsw = change_password_old.getText().toString().trim();
        newPsw = change_password_new.getText().toString().trim();
        newPswAgain = change_password_new1.getText().toString().trim();
    }

    private void modifyPsw() {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setLoginName(SPUtils.getInstance(mContext).getString("USER_NAME", ""));
        changePasswordRequest.setOldPassword(originalPsw);
        changePasswordRequest.setNewPassword(newPsw);
        changePasswordRequest.setConfirmPwd(newPswAgain);
        Net.instance.changePassword(changePasswordRequest,SPUtils.getInstance(mContext).getString("Token"," "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CodeMessageResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CodeMessageResponse codeMessageResponse) {
                        if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                            Toast.makeText(mContext,"两次输入的新密码不一致",Toast.LENGTH_SHORT).show();
                            SPUtils.getInstance(mContext).putString("PASSWORD",newPsw);
                            finish();
                        }else {
                            Toast.makeText(mContext,"提交失败  "+codeMessageResponse.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
