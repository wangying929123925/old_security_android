package com.example.ananops_android.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.UserMainActivity;
import com.example.ananops_android.datePicker.CustomDatePicker;
import com.example.ananops_android.datePicker.DateFormatUtils;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.InspectionItemSubmitRequest;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionItemFinishWindow implements View.OnClickListener{
    private Context mContext;
    private CustomDatePicker mTimerPicker;//时间选择
    private boolean mCanDialogShow;
    private Dialog mPickerDialog;
    private TextView et_order_start_time,et_order_finish_time,tv_confirm;
    private Button button_finish;
    private String inspectionItemId;

    public InspectionItemFinishWindow(String inspectionItemId,Context mContext) {
        this.inspectionItemId = inspectionItemId;
        this.mContext = mContext;
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
        mPickerDialog.setContentView(R.layout.popwindow_inspection_item_finish);
        Window window = mPickerDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
        tv_confirm = mPickerDialog.findViewById(R.id.tv_confirm);
        et_order_start_time = mPickerDialog.findViewById(R.id.et_order_start_time);
        et_order_finish_time = mPickerDialog.findViewById(R.id.et_order_finish_time);
        button_finish = mPickerDialog.findViewById(R.id.button_finish);
        et_order_start_time.setOnClickListener(this);
        et_order_finish_time.setOnClickListener(this);
        button_finish.setOnClickListener(this);
    }
    private boolean judgeComplete(){
        if (TextUtils.isEmpty(et_order_start_time.getText().toString().trim())) {
            Toast.makeText(mContext,"请填写开始时间", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_order_finish_time.getText().toString().trim())) {
            Toast.makeText(mContext,"请填写结束时间", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                break;
            case R.id.et_order_start_time:
                showTime(et_order_start_time);
                break;
            case R.id.et_order_finish_time:
                showTime(et_order_finish_time);
                break;
            case R.id.button_finish:
                if (judgeComplete()) {
                    InspectionItemSubmitRequest inspectionItemSubmitRequest = new InspectionItemSubmitRequest();
                    inspectionItemSubmitRequest.setItemId(Long.valueOf(inspectionItemId));
                    inspectionItemSubmitRequest.setActualStartTime(et_order_start_time.getText().toString().trim());
                    inspectionItemSubmitRequest.setActualFinishTime(et_order_finish_time.getText().toString().trim());
                    inspectionItemSubmitRequest.setStatus(4);
                    Net.instance.putInspectionItemResult(inspectionItemSubmitRequest, SPUtils.getInstance(mContext).getString("Token",""))
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<CodeMessageResponse>(){
                                @Override
                                public void onCompleted() {
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.v("LoginAddTime", System.currentTimeMillis() + "");
                                      e.printStackTrace();
                                    Toast.makeText(mContext, "提交失败", Toast.LENGTH_SHORT).show();

                                }
                                @Override
                                public void onNext(CodeMessageResponse codeMessageResponse) {
                                    if(TextUtils.equals(codeMessageResponse.getCode(),"200")){
                                        Toast.makeText(mContext,"提交成功！",Toast.LENGTH_SHORT).show();
                                        BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                    }
                                    else{
                                        Toast.makeText(mContext,"提交失败！",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
                break;
            default:
                break;
        }
    }
    private void showTime(TextView textView) {
        String endTime = "2050-12-31 23:59:00";
        String beginTime = BaseUtils.getInstence().getTime();
        mTimerPicker = new CustomDatePicker(mContext, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                textView.setText(DateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker.setCancelable(true);
        // 显示时和分
        mTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mTimerPicker.setCanShowAnim(true);
        mTimerPicker.show(beginTime);
    }
    public void onDestroy() {
        if (mPickerDialog != null) {
            mPickerDialog.dismiss();
            mPickerDialog = null;
        }
        if(mTimerPicker!=null){
            mTimerPicker.onDestroy();
        }

    }
}
