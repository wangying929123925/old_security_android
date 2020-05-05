package com.example.ananops_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.RelacementOrderOperationRequest;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReplacementOrderOperationActivity extends BaseActivity {
    private ImageView back_img;
    private TextView et_replacement_order_id;
    private TextView order_type;
    private TextView order_id;
    private EditText replacement_discount;
    private EditText replacement_result;
    private EditText replacement_suggestion;
    private EditText replacement_amount;
    private Button replacement_operation_button;
    private Context mContext;
    private String orderId;
    private String replacementOrderId;
    private boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
      //  ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_replacement_order_operation);
        initViews();
        setListeners();
    }
    private void initViews(){
        back_img = findViewById(R.id.basicinfo_back);
        et_replacement_order_id = findViewById(R.id.et_replacement_order_id);
        order_type = findViewById(R.id.order_type);
        order_id = findViewById(R.id.order_id);
        replacement_discount = findViewById(R.id.replacement_discount);
        replacement_result = findViewById(R.id.replacement_result);
        replacement_suggestion = findViewById(R.id.replacement_suggestion);
        replacement_amount = findViewById(R.id.replacement_amount);
        replacement_operation_button = findViewById(R.id.replacement_operation_button);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            replacementOrderId = intent.getStringExtra("replacementOrderId");
            orderId = intent.getStringExtra("orderId");
        }
        order_type.setText(String.valueOf(1));
        order_id.setText(orderId);
        et_replacement_order_id.setText(replacementOrderId);
    }

    private void setListeners() {
        //返回
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //提交
        replacement_operation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           if(checkSubmit()){
                submit();
            }}
        });
    }
    private boolean checkSubmit() {
    if(replacement_discount.getText().toString().trim().equals("")){
        Toast.makeText(mContext, "请输入折扣数量！", Toast.LENGTH_SHORT).show();
        return false;
    } else if (Float.valueOf(replacement_discount.getText().toString().trim()) < 0.01 || Float.valueOf(replacement_discount.getText().toString().trim()) > 10.00) {
        Toast.makeText(mContext, "请输入正确的折扣范围！", Toast.LENGTH_SHORT).show();
        return false;
    } else if (replacement_result.getText().toString().trim().equals("")) {
        Toast.makeText(mContext, "请输入处理结果！", Toast.LENGTH_SHORT).show();
        return false;
    } return true;
   }
    private void submit() {
        RelacementOrderOperationRequest relacementOrderOperationRequest = new RelacementOrderOperationRequest();
        if (replacementOrderId != null && orderId != null) {
            relacementOrderOperationRequest.setId(Long.valueOf(replacementOrderId));
            relacementOrderOperationRequest.setObjectId(Long.valueOf(orderId));
            relacementOrderOperationRequest.setObjectType(1);
            relacementOrderOperationRequest.setDiscount(Float.valueOf(replacement_discount.getText().toString().trim()));
            relacementOrderOperationRequest.setResult(replacement_result.getText().toString().trim());
            relacementOrderOperationRequest.setSuggestion(replacement_suggestion.getText().toString().trim());
            relacementOrderOperationRequest.setStatus(8);
            relacementOrderOperationRequest.setTotalPrice(10.0f);
            Net.instance.ReplacementOrderOperation(relacementOrderOperationRequest, SPUtils.getInstance(mContext).getString("Token"," "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CodeMessageResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("replaceOrderOperation", System.currentTimeMillis() + "");
                            e.printStackTrace();
                            Toast.makeText(mContext,"提交失败！",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(CodeMessageResponse codeMessageResponse) {
                            if(TextUtils.equals(codeMessageResponse.getCode(),"200")){
                                Toast.makeText(mContext,"提交成功！",Toast.LENGTH_SHORT).show();
                                BaseUtils.getInstence().changeStatus(8,orderId,"审核备品备件",mContext);
                              //  BaseUtils.getInstence().intent(mContext,UserMainActivity.class);
                            }
                            else{
                                Toast.makeText(mContext,"服务器故障！",Toast.LENGTH_SHORT).show();
                                Log.v("replaceOrderOperation", codeMessageResponse.getMessage());
                                BaseUtils.getInstence().intent(mContext,UserMainActivity.class);
                            }
                        }
                    });
        }

    }
}
