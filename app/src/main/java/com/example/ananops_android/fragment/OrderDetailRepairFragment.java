package com.example.ananops_android.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.db.OrderDetailResponse;
import com.example.ananops_android.entity.RepairCommonDetail;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class OrderDetailRepairFragment extends Fragment {
    private TextView tv_repair_start_time;
    private TextView tv_repair_end_time;
    private TextView tv_repair_money;
    private TextView tv_repair_status;
    private TextView tv_repair_group;
    private TextView tv_repair_man;
    private TextView tv_repair_degree;
    private TextView tv_emergency_degree;
    private EditText tv_repair_description;
    private Button bt_order_start_time;
    private Button bt_order_end_time;
    private Button bt_repair_save;
    private static String STATUS_FLAG;
    private static String ORDER_ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order_detail_repair,container,false);
        tv_repair_start_time = view.findViewById(R.id.tv_repair_start_time);
        tv_repair_end_time = view.findViewById(R.id.tv_repair_end_time);
        tv_repair_money = view.findViewById(R.id.tv_repair_money);
        tv_repair_status = view.findViewById(R.id.tv_repair_status);
        tv_repair_group = view.findViewById(R.id.tv_repair_group);
        tv_repair_man = view.findViewById(R.id.tv_repair_man);
        tv_repair_degree = view.findViewById(R.id.tv_repair_degree);
        tv_emergency_degree = view.findViewById(R.id.tv_emergency_degree);
        tv_repair_description = view.findViewById(R.id.tv_repair_description);
        bt_order_start_time = view.findViewById(R.id.bt_order_start_time);
        bt_order_end_time = view.findViewById(R.id.bt_order_end_time);
        bt_repair_save = view.findViewById(R.id.bt_repair_save);
        initdata();

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initdata() {
        tv_repair_start_time.setText("test");
        tv_repair_end_time.setText("test");
        tv_repair_degree.setText("test");
        tv_emergency_degree.setText("test");
        tv_repair_description.setText("test");
        tv_repair_money.setText("test");
        tv_repair_status.setText("test");
        tv_repair_group.setText("test");
        tv_repair_man.setText("test");
        if((getArguments()!=null)){
            STATUS_FLAG=(String)getArguments().get("str");
            ORDER_ID=(String) getArguments().get("order_id");
            getRepairDetail();
            Toast.makeText(getContext(),"STATUS_FLAG"+STATUS_FLAG,Toast.LENGTH_SHORT).show();
        }
            if(STATUS_FLAG=="3-2"||STATUS_FLAG.equals("3-2")){

                initDiffDetailUnRepair();
            }
            else if(STATUS_FLAG=="3-3"||STATUS_FLAG.equals("3-3")){
                initDiffDetailRepairing();
            } else {
//                tv_repair_start_time.setText("test");
//                tv_repair_end_time.setText("test");
//                tv_repair_degree.setText("test");
//                tv_emergency_degree.setText("test");
//                tv_repair_description.setText("test");
                bt_repair_save.setVisibility(View.INVISIBLE);
                bt_order_start_time.setVisibility(View.INVISIBLE);
                bt_order_end_time.setVisibility(View.INVISIBLE);
            }

    }
    private void getRepairDetail(){
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
                            tv_repair_start_time.setText(String.valueOf(orderDetailResponse.getResult().getActualStartTime()));
                            tv_repair_end_time.setText(String.valueOf(orderDetailResponse.getResult().getActualFinishTime()));
                            tv_repair_degree.setText(String.valueOf(orderDetailResponse.getResult().getLevel()));
                            tv_repair_description.setText(String.valueOf(orderDetailResponse.getResult().getSuggestion()));
                            tv_repair_money.setText(String.valueOf(orderDetailResponse.getResult().getTotalCost()));
                            tv_repair_status.setText(BaseUtils.getInstence().statusNumConvertString((orderDetailResponse.getResult().getStatus())));
                            tv_repair_group.setText("一组");
                            tv_repair_man.setText("782526720958801921");
                            tv_emergency_degree.setText(String.valueOf(orderDetailResponse.getResult().getLevel()));
                            RepairCommonDetail.level=orderDetailResponse.getResult().getLevel();
                            RepairCommonDetail.title=orderDetailResponse.getResult().getTitle();
                            RepairCommonDetail.totalCost=orderDetailResponse.getResult().getTotalCost();
                        } else {
                            Toast.makeText(getContext(),orderDetailResponse.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //待维修，填写方案
     private void initDiffDetailUnRepair(){
         bt_order_start_time.setVisibility(View.INVISIBLE);
         bt_order_end_time.setVisibility(View.INVISIBLE);
         tv_repair_degree.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final String[] items = {"轻微","一般","严重"};
                 showDialog(v,items,tv_repair_degree);
             }
         });
         tv_emergency_degree.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final String[] items = {"不急","一般","紧急"};
                 showDialog(v,items,tv_emergency_degree);
             }
         });
         tv_repair_description.setEnabled(true);
         bt_repair_save.setVisibility(View.VISIBLE);
         bt_repair_save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 RepairCommonDetail.id=Long.valueOf(ORDER_ID);
                 RepairCommonDetail.suggestion=tv_repair_description.getText().toString().trim();
             }
         });
     }
     //维修中，维修工维修
    private void initDiffDetailRepairing(){
        bt_order_start_time.setVisibility(View.VISIBLE);
        bt_order_end_time.setVisibility(View.VISIBLE);
        bt_repair_save.setVisibility(View.INVISIBLE);
        bt_order_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_repair_start_time.setText(BaseUtils.getInstence().getTime());
            }
        });
        bt_order_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_repair_end_time.setText(BaseUtils.getInstence().getTime());
            }
        });
     //   tv_repair_description.setFocusable(true);
    }
    public void showDialog(View view, final String[] items, final TextView textView)
    {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(view.getContext());
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(),items[i],Toast.LENGTH_SHORT).show();
                textView.setText(items[i]);
                dialogInterface.dismiss();
                //  tmp=items[i];
            }
        });
        alertBuilder.create().show();
    }
    }
