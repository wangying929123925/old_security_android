package com.example.ananops_android.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.InspectionCommentRequest;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionCommentActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private ImageView back_img;
    private Button repair_commit_button;
    private RatingBar general_comment_rat;
    private RatingBar attitude_comment_rat;
    private RatingBar quality_comment_rat;
    private EditText et_comment;
    private int general_comment_mum;
    private static String ORDER_ID;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
      //  ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_repair_comment);
        initView();
        setOnListener();
    }
    private void initView(){
        title=findViewById(R.id.txt_title);//标题
        title.setText("评价");
        back_img = findViewById(R.id.img_back);
        repair_commit_button = findViewById(R.id.comment_submit_button);
        general_comment_rat = findViewById(R.id.general_comment_rat);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //  Log.v("", "initView: ");
        if (bundle != null) {
            ORDER_ID = bundle.getString("inspectionId");
        }
        else {
            Toast.makeText(mContext, "no_inspectionId", Toast.LENGTH_SHORT).show();
            // Log.v("result",System.currentTimeMillis() + "");
        }
        //Log.v("commentOrderId",ORDER_ID);
        //  Toast.makeText(mContext, "orderId"+ORDER_ID, Toast.LENGTH_SHORT).show();
    }
    private void setOnListener() {

        back_img.setOnClickListener(this);
        repair_commit_button.setOnClickListener(this);
        general_comment_rat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(mContext,"总体评分="+rating,Toast.LENGTH_LONG).show();
                general_comment_mum = (int)rating;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.img_back:
        showExitAlertDialog(v);
        break;
        case R.id.comment_submit_button:
            InspectionCommentRequest inspectionCommentRequest = new InspectionCommentRequest();
            inspectionCommentRequest.setContents(et_comment.getText().toString().trim());
            inspectionCommentRequest.setScore(general_comment_mum);
            inspectionCommentRequest.setStatus(5);
            inspectionCommentRequest.setPrincipalId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id"," ")));
            inspectionCommentRequest.setInspectionTaskId(Long.valueOf(ORDER_ID));
            Net.instance.InspectionCommentAdd(inspectionCommentRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CodeMessageResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("inspectionCommentTime", System.currentTimeMillis() + "");
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(CodeMessageResponse codeMessageResponse) {
                            if(TextUtils.equals(codeMessageResponse.getCode(),"200")){
                                Log.v("评价操作成功", System.currentTimeMillis() + "");
                                Toast.makeText(mContext,"操作成功！",Toast.LENGTH_SHORT).show();
                                BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                            }
                            else{
                                Toast.makeText(mContext,"操作失败！",Toast.LENGTH_SHORT).show();
                                Log.v("评价操作失败", codeMessageResponse.getMessage());
                            }
                        }
                    });
        break;
        default:
            break;
        }
    }
    public void showExitAlertDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("评价未提交，退出将不保存编辑，确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
