package com.example.ananops_android.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.InspectionCommentRequest;
import com.example.ananops_android.db.RepairCommentRequest;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.ActivityManager;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CommentWindow {
    private Context mContext;
    private TextView et_tv1,et_tv2,tv_confirm;
    private EditText et_tv3,et_tv4;
    private RatingBar general_comment_rat;
    private int general_comment_mum = 5;
    private Button button_finish;
    private String orderId;
    private String userId;
    private int orderType;
    private boolean mCanDialogShow;
    private Dialog mPickerDialog;

    public CommentWindow(String orderId,String userId,int orderType,Context mContext) {
        this.orderId = orderId;
        this.mContext = mContext;
        this.orderType = orderType;
        this.userId = userId;
        initView();
        mCanDialogShow = true;
    }
    public void show() {
        if (!canShow() ) return;
        // 弹窗时，考虑用户体验，不展示滚动动画
        mPickerDialog.show();
    }
    private boolean canShow() {
        return mCanDialogShow && mPickerDialog != null;
    }
    private void initView() {
        mPickerDialog = new Dialog(mContext, R.style.date_picker_dialog);
        mPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mPickerDialog.setContentView(R.layout.activity_repair_comment);
        Window window = mPickerDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
       tv_confirm = mPickerDialog.findViewById(R.id.tv_confirm);
        et_tv1 = mPickerDialog.findViewById(R.id.comment_tv1);
        et_tv2 = mPickerDialog.findViewById(R.id.comment_tv2);
        et_tv3 = mPickerDialog.findViewById(R.id.comment_tv3);
        et_tv4 = mPickerDialog.findViewById(R.id.comment_tv4);
        et_tv1.setText(orderId);
        et_tv2.setText(userId);
        general_comment_rat = mPickerDialog.findViewById(R.id.general_comment_rat);
        button_finish = mPickerDialog.findViewById(R.id.comment_submit_button);
        tv_confirm.setOnClickListener(v -> {

        });
        general_comment_rat.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            general_comment_mum = (int)rating;
        });
        button_finish.setOnClickListener(v -> {
            if (orderType == 1) {
              repairCommentSubmit();
            } else if (orderType==2) {
               inspectionCommentSubmit();
            }
        });
    }
    private boolean judgeComplete(){
        if (TextUtils.isEmpty(et_tv1.getText().toString().trim())) {
            Toast.makeText(mContext,"任务唯一编号获取失败", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_tv2.getText().toString().trim())) {
            Toast.makeText(mContext,"评价用户编号获取失败", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_tv3.getText().toString().trim())) {
            Toast.makeText(mContext,"请填写验收服务内容", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_tv4.getText().toString().trim())) {
            Toast.makeText(mContext,"请填写服务评价", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void repairCommentSubmit() {
        if (judgeComplete()) {
            RepairCommentRequest repairCommentRequest = new RepairCommentRequest();
            repairCommentRequest.setTaskId(Long.valueOf(orderId));
            repairCommentRequest.setContents(et_tv4.getText().toString().trim());
            repairCommentRequest.setCheckContens(et_tv3.getText().toString().trim());
            repairCommentRequest.setScore(general_comment_mum);
            repairCommentRequest.setUserId(Long.valueOf(userId));
            //   repairCommentRequest.setPrincipalId(782525013398923265L);
            Net.instance.RepairCommentAdd(repairCommentRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CodeMessageResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("repairCommentTime", System.currentTimeMillis() + "");
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(CodeMessageResponse codeMessageResponse) {
                            if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                Log.v("评价操作成功", System.currentTimeMillis() + "");
                                //   Toast.makeText(mContext,"提交成功！",Toast.LENGTH_SHORT).show();
                                BaseUtils.getInstence().changeStatus(11, orderId, "确认完成并提交评价", mContext);
                                // BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                            } else {
                                Toast.makeText(mContext, "提交失败！", Toast.LENGTH_SHORT).show();
                                Log.v("评价操作失败", codeMessageResponse.getMessage());
                            }
                        }
                    });
        }
    }

    private void inspectionCommentSubmit() {
        if(judgeComplete()) {
            InspectionCommentRequest inspectionCommentRequest = new InspectionCommentRequest();
            inspectionCommentRequest.setContents(et_tv4.getText().toString().trim());
            inspectionCommentRequest.setScore(general_comment_mum);
            inspectionCommentRequest.setStatus(5);
            inspectionCommentRequest.setPrincipalId(Long.valueOf(userId));
            inspectionCommentRequest.setInspectionTaskId(Long.valueOf(orderId));
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
                            Toast.makeText(mContext, "操作失败！", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(CodeMessageResponse codeMessageResponse) {
                            if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                Log.v("评价操作成功", System.currentTimeMillis() + "");
                                Toast.makeText(mContext, "操作成功！", Toast.LENGTH_SHORT).show();
                                ActivityManager.getInstance().finishCurrentActivity();
                            } else {
                                Toast.makeText(mContext, "操作失败！", Toast.LENGTH_SHORT).show();
                                Log.v("评价操作失败", codeMessageResponse.getMessage());
                            }
                        }
                    });
        }
    }
    public void onDestroy() {
        if (mPickerDialog != null) {
            mPickerDialog.dismiss();
            mPickerDialog = null;
        }
    }


}
