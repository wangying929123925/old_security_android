package com.example.ananops_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.db.InspectionDetailResponse;
import com.example.ananops_android.db.OrderDetailResponse;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import java.io.IOException;

import retrofit2.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MessageDetailActivity extends BaseActivity {
    private TextView text_message_title1;
    private TextView text_message_value1;
    private TextView text_message_title2;
    private TextView text_message_value2;
    private TextView text_message_title3;
    private TextView text_message_value3;
    private TextView title;
    private ImageView back_img;
    private String orderId;
    private String messageType;
    private String messageContent;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_message_details);
        initView();
    }

    private void initView() {
        text_message_title1 = findViewById(R.id.text_message_title1);
        text_message_title2 = findViewById(R.id.text_message_title2);
        text_message_title3 = findViewById(R.id.text_message_title3);
        text_message_title1.setText("消息类型");
        text_message_title2.setText("消息内容");
        text_message_title3.setText("任务名称");
        text_message_value1 = findViewById(R.id.text_message_value1);
        text_message_value2 = findViewById(R.id.text_message_value2);
        text_message_value3 = findViewById(R.id.text_message_value3);
        title = findViewById(R.id.txt_title);//标题
        back_img=findViewById(R.id.img_back);//返回
        title.setText("消息详情");
        back_img.setOnClickListener(v -> {
            Bundle bundle1 = new Bundle();
            bundle1.putInt("fragmentIndex",1);
            BaseUtils.getInstence().intent(mContext,UserMainActivity.class,bundle1);
        });
        getData();
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            orderId = bundle.getString("orderId");
            messageType = bundle.getString("messageType");
            messageContent = bundle.getString("messageContent");
        }
        if (messageType != null) {
            text_message_value2.setText(messageContent);
            if (messageType.equals("MDMC_TOPIC")) {//获取维修维护信息
                text_message_value1.setText("维修消息");
                Net.instance.getOrderDetail(Long.valueOf(orderId), SPUtils.getInstance(mContext).getString("Token"," "))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<OrderDetailResponse>() {
                            @Override
                            public void onCompleted() {

                            }
                            @Override
                            public void onError(Throwable e) {
                                Log.e("getRepairDetail", System.currentTimeMillis() + "");
                                if (e instanceof HttpException) {
                                    HttpException httpException = (HttpException) e;
                                    try {
                                        String error = httpException.response().errorBody().string();
                                        Log.e("RepairDetail", error);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                } else {
                                    //ToastUtil.showLongToast("请求失败");
                                }
                                Toast.makeText(mContext, "获取维修信息失败！", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNext(OrderDetailResponse orderDetailResponse) {
                                if (TextUtils.equals(orderDetailResponse.getCode(), "200")) {
                                    String title = orderDetailResponse.getResult().getMdmcTask().getTitle();
                                    text_message_value3.setText(title);
                                } else {
                                    //Toast.makeText(mContext,orderDetailResponse.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else if (messageType.equals("IMC_TOPIC")) {//获取巡检信息
                text_message_value1.setText("巡检消息");
                Net.instance.getInspectionDetails(Long.valueOf(orderId), SPUtils.getInstance(mContext).getString("Token", " "))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<InspectionDetailResponse>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.v("ErrorGetInspectionInfo", System.currentTimeMillis() + "");
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(InspectionDetailResponse inspectionDetailResponse) {
                                if (TextUtils.equals(inspectionDetailResponse.getCode(), "200")) {
                                    String title = inspectionDetailResponse.getResult().getTaskName();
                                    text_message_value3.setText(title);
                                } else {
                                  //  Toast.makeText(mContext, inspectionDetailResponse.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        }

    }
}
