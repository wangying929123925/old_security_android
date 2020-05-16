package com.example.ananops_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.annotation.ViewInject;
import com.example.ananops_android.datePicker.CustomDatePicker;
import com.example.ananops_android.datePicker.DateFormatUtils;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.InvoiceDetailResponse;
import com.example.ananops_android.entity.InvoiceDetail;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.AnnotationUtils;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InvoiceDetailActivity extends BaseActivity {
    private Context mContext;
    @ViewInject(R.id.invoice_detail_tv1)
    private EditText invoice_detail_tv1;

    @ViewInject(R.id.invoice_detail_tv2)
    private EditText invoice_detail_tv2;

    @ViewInject(R.id.invoice_detail_tv3)
    private TextView invoice_detail_tv3;

    @ViewInject(R.id.asset_list)
    private LinearLayout asset_list;

    @ViewInject(R.id.inspection_detail_list)
    private LinearLayout inspection_detail_list;

    @ViewInject(R.id.invoice_detail_confirm1)
    private EditText invoice_detail_confirm1;

    @ViewInject(R.id.invoice_detail_confirm2)
    private TextView invoice_detail_confirm2;

    @ViewInject(R.id.invoice_detail_confirm3)
    private TextView invoice_detail_confirm3;

    @ViewInject(R.id.invoice_detail_confirm4)
    private TextView invoice_detail_confirm4;

    @ViewInject(R.id.basicinfo_back)
    private ImageView img_back;

    @ViewInject(R.id.invoice_detail_bt)
    private Button invoice_submit_bt;
    private CustomDatePicker mTimerPicker;//时间选择
    private String invoiceId="883835153284075520";
    private InvoiceDetail invoiceDetail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_invoice_detail);
        AnnotationUtils.injectViews(this);
        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            invoiceId = bundle.getString("invoiceId");
        }
       // invoice_detail_tv3.setText("11111111");

        img_back.setOnClickListener(v -> finish());
        invoice_detail_confirm2.setOnClickListener(v -> {
            String endTime = "2023-12-31 23:59:00";
            String beginTime = BaseUtils.getInstence().getTime();
            mTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
                @Override
                public void onTimeSelected(long timestamp) {
                    invoice_detail_confirm2.setText(DateFormatUtils.long2Str(timestamp, true));
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
        });
        invoice_submit_bt.setOnClickListener(v -> invoiceSubmit());
        getInvoiceDetail();
     //   addViewDynamic();
    }

    private void getInvoiceDetail() {
        if (invoiceId != null) {
            //Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiLph5HmnZDmmLEiLCJzY29wZSI6WyIqIl0sImxvZ2luTmFtZSI6IumHkeadkOaYsSIsImV4cCI6MTU4OTE4MTk3NSwiYXV0aG9yaXRpZXMiOlsiL3VzZXIvYXV0aFVzZXJNb2RpZnlQd2QiXSwianRpIjoiMDc1NGEwNWQtNmFjNy00NzlkLTlkMTItMmFkNzFmNDRhNWM2IiwiY2xpZW50X2lkIjoiYW5hbm9wcy1jbGllbnQtdWFjIiwidGltZXN0YW1wIjoxNTg5MTc0Nzc1MTE3fQ.DU6m6w7VAx1fAK4SMdeqpmglUy2M5hRk9oDQq_VB698
                Net.instance.getInvoiceDetail(Long.valueOf(invoiceId), SPUtils.getInstance(mContext).getString("Token", " "))
             //  Net.instance.getInvoiceDetail(Long.valueOf(invoiceId),"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiLph5HmnZDmmLEiLCJzY29wZSI6WyIqIl0sImxvZ2luTmFtZSI6IumHkeadkOaYsSIsImV4cCI6MTU4OTE4MTk3NSwiYXV0aG9yaXRpZXMiOlsiL3VzZXIvYXV0aFVzZXJNb2RpZnlQd2QiXSwianRpIjoiMDc1NGEwNWQtNmFjNy00NzlkLTlkMTItMmFkNzFmNDRhNWM2IiwiY2xpZW50X2lkIjoiYW5hbm9wcy1jbGllbnQtdWFjIiwidGltZXN0YW1wIjoxNTg5MTc0Nzc1MTE3fQ.DU6m6w7VAx1fAK4SMdeqpmglUy2M5hRk9oDQq_VB698")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<InvoiceDetailResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(InvoiceDetailResponse invoiceDetailResponse) {
                            if (TextUtils.equals(invoiceDetailResponse.getCode(), "200")) {
                                invoiceDetail = invoiceDetailResponse.getResult();
                                invoice_detail_tv1.setText(invoiceDetail.getPointName());
                                invoice_detail_tv2.setText(invoiceDetail.getPointAddress());
                                invoice_detail_tv3.setText(invoiceDetail.getInspcCompany());
                                invoice_detail_confirm1.setText(invoiceDetail.getFeedback().getInspcResult());
                                invoice_detail_confirm2.setText(invoiceDetail.getFeedback().getInspcDate());
                                invoice_detail_confirm4.setText(invoiceDetail.getFeedback().getEngineer());
                            addViewDynamic();
                            }
                        }
                    });
        }
    }
    private void addViewDynamic() {
        int assetSize = invoiceDetail.getAssetList().size();
        for (int i = 0; i < assetSize; i++) {
            LayoutInflater inflater3 = LayoutInflater.from(mContext);
            View view = inflater3.inflate(R.layout.item_invoice_asset, null).findViewById(R.id.asset_liner_layout);
            asset_list.addView(view,i);
            //   asset_list.addView(view2,2);
            EditText tv = asset_list.getChildAt(i).findViewById(R.id.invoice_detail_device_tv1);
            tv.setText(invoiceDetail.getAssetList().get(i).getDevice());
            TextView assetNum = asset_list.getChildAt(i).findViewById(R.id.asset_num);
            assetNum.setText(String.valueOf(i+1));
        }
        int itemSize = invoiceDetail.getInspcDetailList().size();
        for (int i = 0; i < itemSize; i++) {
            LayoutInflater inflater2 = LayoutInflater.from(mContext);
            View view = inflater2.inflate(R.layout.item_invoice_inspection_detail, null).findViewById(R.id.invoice_detail_liner_layout);
            inspection_detail_list.addView(view,i);
            //   asset_list.addView(view2,2);
            TextView tv = inspection_detail_list.getChildAt(i).findViewById(R.id.invoice_ins_tv1);
            EditText tv1 = inspection_detail_list.getChildAt(i).findViewById(R.id.invoice_ins_tv2);
            TextView ins_num = inspection_detail_list.getChildAt(i).findViewById(R.id.ins_num);
            tv.setText(invoiceDetail.getInspcDetailList().get(i).getItemContent());
            tv1.setText(invoiceDetail.getInspcDetailList().get(i).getItemResult());
            ins_num.setText(String.valueOf(i+1));
            RadioButton normal = inspection_detail_list.getChildAt(i).findViewById(R.id.normal);
            RadioButton abnormal = inspection_detail_list.getChildAt(i).findViewById(R.id.abnormal);
            if (invoiceDetail.getInspcDetailList().get(i).getItemState().equals("Y")) {
                normal.setChecked(true);
                abnormal.setChecked(false);
                tv1.setText("--");
                tv1.setFocusable(false);
            } else {
                normal.setChecked(false);
                abnormal.setChecked(true);
                tv1.setFocusable(true);
            }
        }
    }

    private void invoiceSubmit() {
        if (checkInvoice()) {
           //  Net.instance.invoiceDetailSave(invoiceDetail,"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiLph5HmnZDmmLEiLCJzY29wZSI6WyIqIl0sImxvZ2luTmFtZSI6IumHkeadkOaYsSIsImV4cCI6MTU4OTE4MTk3NSwiYXV0aG9yaXRpZXMiOlsiL3VzZXIvYXV0aFVzZXJNb2RpZnlQd2QiXSwianRpIjoiMDc1NGEwNWQtNmFjNy00NzlkLTlkMTItMmFkNzFmNDRhNWM2IiwiY2xpZW50X2lkIjoiYW5hbm9wcy1jbGllbnQtdWFjIiwidGltZXN0YW1wIjoxNTg5MTc0Nzc1MTE3fQ.DU6m6w7VAx1fAK4SMdeqpmglUy2M5hRk9oDQq_VB698")
            Net.instance.invoiceDetailSave(invoiceDetail, SPUtils.getInstance(mContext).getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CodeMessageResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(mContext,"提交失败！", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(CodeMessageResponse codeMessageResponse) {
                            if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                Toast.makeText(mContext,"提交成功！", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
        }
    }
    private boolean checkInvoice() {
        invoiceDetail.setPointName(invoice_detail_tv1.getText().toString().trim());
        invoiceDetail.setPointAddress(invoice_detail_tv2.getText().toString().trim());
        invoiceDetail.getFeedback().setInspcResult(invoice_detail_confirm1.getText().toString().trim());
        invoiceDetail.getFeedback().setInspcDate(invoice_detail_confirm2.getText().toString().trim());
        int assetSize = invoiceDetail.getAssetList().size();
        for (int i = 0; i < assetSize; i++) {
            EditText tv = asset_list.getChildAt(i).findViewById(R.id.invoice_detail_device_tv1);
            invoiceDetail.getAssetList().get(i).setDevice(tv.getText().toString().trim());
        }
        int itemSize = invoiceDetail.getInspcDetailList().size();
        for (int i = 0; i < itemSize; i++) {
            EditText tv1 = inspection_detail_list.getChildAt(i).findViewById(R.id.invoice_ins_tv2);
            invoiceDetail.getInspcDetailList().get(i).setItemResult(tv1.getText().toString().trim());
            RadioButton normal = inspection_detail_list.getChildAt(i).findViewById(R.id.normal);
            if (normal.isChecked()) {
                invoiceDetail.getInspcDetailList().get(i).setItemState("Y");
            } else {
                invoiceDetail.getInspcDetailList().get(i).setItemState("N");
            }
        }
        if (TextUtils.isEmpty(invoice_detail_tv1.getText().toString().trim())) {
            Toast.makeText(this,"请填写点位名称", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(invoice_detail_tv2.getText().toString().trim())) {
            Toast.makeText(this,"请填写点位地址", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(invoice_detail_confirm1.getText().toString().trim())) {
            Toast.makeText(this,"请填写巡检结果", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(invoice_detail_confirm2.getText().toString().trim())) {
            Toast.makeText(this,"请填写巡检日期", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTimerPicker!=null) {
            mTimerPicker.onDestroy();
        }
    }
}
