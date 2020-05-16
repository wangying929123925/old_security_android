package com.example.ananops_android.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.datePicker.CustomDatePicker;
import com.example.ananops_android.datePicker.DateFormatUtils;
import com.example.ananops_android.entity.RepairAddContent;
import com.example.ananops_android.util.BaseUtils;

public class RepairFinishWindow implements View.OnClickListener {
    private Context mContext;
    private CustomDatePicker mTimerPicker;//时间选择
    private boolean mCanDialogShow;
    private Dialog mPickerDialog;
    private TextView et_order_start_time,et_order_finish_time,tv_confirm;
    private EditText et_order_fault_reason, et_order_repair_result,
            et_order_repair_suggestion,et_order_repair_overtime;
    private Button button_finish;
    private String orderId;
    final private RepairAddContent repairAddContent = new RepairAddContent();//save

    public RepairFinishWindow(String orderId,Context mContext) {
        this.orderId = orderId;
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
        mPickerDialog.setContentView(R.layout.popwindow_finish_repair);
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
        et_order_fault_reason = mPickerDialog.findViewById(R.id.et_order_fault_reason);
        et_order_repair_result = mPickerDialog.findViewById(R.id.et_order_repair_result);
        et_order_repair_suggestion = mPickerDialog.findViewById(R.id.et_order_repair_suggestion);
        et_order_repair_overtime = mPickerDialog.findViewById(R.id.et_order_repair_overtime);
        button_finish = mPickerDialog.findViewById(R.id.button_finish);
        et_order_start_time.setOnClickListener(this);
        et_order_finish_time.setOnClickListener(this);
        button_finish.setOnClickListener(this);
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
                    repairAddContent.setId(Long.valueOf(orderId));
                    repairAddContent.setStatus(10);
                    repairAddContent.setActualStartTime(et_order_start_time.getText().toString().trim());
                    repairAddContent.setActualFinishTime(et_order_finish_time.getText().toString().trim());
                    repairAddContent.setTroubleReason(et_order_fault_reason.getText().toString().trim());
                    repairAddContent.setDelayReason(et_order_repair_overtime.getText().toString().trim());
                    repairAddContent.setResult(et_order_fault_reason.getText().toString().trim());
                    repairAddContent.setSuggestion(et_order_repair_suggestion.getText().toString().trim());
                    BaseUtils.getInstence().repairAdd(repairAddContent,mContext);
                }break;
                default:
                    break;
        }
       // if (mPickerDialog != null && mPickerDialog.isShowing()) {
           // mPickerDialog.dismiss();
       // }
    }
    private boolean judgeComplete(){
        if (TextUtils.isEmpty(et_order_start_time.getText().toString().trim())) {
            Toast.makeText(mContext,"请填写开始时间", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_order_finish_time.getText().toString().trim())) {
            Toast.makeText(mContext,"请填写结束时间", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_order_fault_reason.getText().toString().trim())) {
            Toast.makeText(mContext,"请填写故障原因", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_order_repair_result.getText().toString().trim())) {
            Toast.makeText(mContext,"请填写维修结果", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_order_repair_suggestion.getText().toString().trim())) {
            Toast.makeText(mContext,"请选择维修建议", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    /**
     * 销毁弹窗
     */
    private void showTime(TextView textView) {
        String endTime = "2023-12-31 23:59:00";
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
