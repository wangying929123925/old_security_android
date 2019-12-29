package com.example.ananops_android.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ananops_android.R;
import com.example.ananops_android.adapter.RepairAdapter;
import com.example.ananops_android.db.OrderDetailResponse;
import com.example.ananops_android.entity.RepairContent;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderDetailContentFragment extends Fragment {
    private static String ORDER_ID;
   private TextView tv_project_name;
    private TextView tv_repair_listid;
    private TextView tv_repair_person;
    private TextView tv_repair_tel;
    private TextView tv_repair_time;
    private TextView tv_fault_type;
    private TextView tv_fault_addr;
    private TextView tv_repair_address;
    private TextView tv_fault_name;
    private TextView tv_fault_degree;
    private TextView tv_emergency_degree;
    private TextView tv_fault_description;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order_detail_content,container,false);
        tv_project_name=view.findViewById(R.id.tv_project_name);
        tv_repair_listid=view.findViewById( R.id.tv_repair_listid);
       tv_repair_person=view.findViewById(R.id.tv_repair_person);
        tv_repair_tel=view.findViewById(R.id.tv_repair_tel);
        tv_repair_time=view.findViewById(R.id. tv_repair_time);
        tv_fault_type=view.findViewById(R.id.tv_fault_type);
        tv_fault_addr=view.findViewById(R.id.tv_fault_addr);
        tv_repair_address=view.findViewById(R.id.tv_repair_address);
        tv_fault_name=view.findViewById(R.id. tv_fault_name);
       tv_fault_degree=view.findViewById(R.id.tv_fault_degree);
        tv_emergency_degree=view.findViewById(R.id.tv_emergency_degree);
        tv_fault_description=view.findViewById(R.id.tv_fault_description);
        initdata();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initdata() {
        if(!(getArguments()==null)){
            ORDER_ID=(String) getArguments().get("order_id");
            Net.instance.getOrderDetail(ORDER_ID, SPUtils.getInstance().getString("Token"," "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<OrderDetailResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("LoginTime", System.currentTimeMillis() + "");
                            e.printStackTrace();
                            Toast.makeText(getContext(), "网络异常，请检查网络状态fragmentgetDetail", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(OrderDetailResponse orderDetailResponse) {
                            if (TextUtils.equals(orderDetailResponse.getCode(), "200")) {
                                tv_project_name.setText(String.valueOf(orderDetailResponse.getResult().getProjectId()));
                                tv_repair_listid.setText(ORDER_ID);
                                tv_repair_person.setText(String.valueOf(orderDetailResponse.getResult().getUserId()));
                                tv_repair_tel.setText(String.valueOf(orderDetailResponse.getResult().getCall()));
                                tv_repair_time.setText(String.valueOf(orderDetailResponse.getResult().getAppointTime()));
                                tv_fault_type.setText(String.valueOf(orderDetailResponse.getResult().getAddress_name()));
                                tv_fault_addr.setText(String.valueOf(orderDetailResponse.getResult().getAddress_name()));
                                tv_repair_address.setText("北京西站");
                                tv_fault_name.setText("测试");
                                tv_fault_degree.setText(String.valueOf(orderDetailResponse.getResult().getLevel()));
                                tv_emergency_degree.setText(String.valueOf(orderDetailResponse.getResult().getLevel()));
                                tv_fault_description.setText(String.valueOf(orderDetailResponse.getResult().getSuggestion()));
                            } else {
                                Toast.makeText(getContext(),orderDetailResponse.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}
