package com.example.ananops_android.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.RepairAddActivity;
import com.example.ananops_android.entity.RepairContent;

public class OrderDetailReplacementFragment extends Fragment {
    private static String STATUS_FLAG;
    private static String ORDER_ID;
    private TextView tv_whether_replace;
    private TextView tv_replacement_id;
    private TextView tv_replacement_name;
    private TextView tv_replacement_price;
    private TextView tv_replacement_number;
    private TextView tv_replacement_amount;
    private TextView tv_choose_whether_replace;
    private TextView fragment_order_choose_replacement;
    private String whether_replace="否";
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order_detail_replacement,container,false);
        tv_whether_replace=view.findViewById(R.id.tv_whether_replace);
        tv_replacement_id=view.findViewById(R.id.tv_replacement_id);
        tv_replacement_name=view.findViewById(R.id.tv_replacement_name);
        tv_replacement_price=view.findViewById(R.id.tv_replacement_price);
        tv_replacement_number=view.findViewById(R.id.tv_replacement_number);
        tv_replacement_amount=view.findViewById(R.id.tv_replacement_amount);
        tv_choose_whether_replace=view.findViewById(R.id.tv_choose_whether_replace);
        fragment_order_choose_replacement=view.findViewById(R.id.fragment_order_choose_replacement);
        tv_choose_whether_replace.setVisibility(View.INVISIBLE);
        fragment_order_choose_replacement.setVisibility(View.INVISIBLE);
        initView();
        initData();
        mContext=getContext();
       // setListener();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CART_BROADCAST");
        BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent){
               // RepairContent repairContent=intent.getParcelableExtra("tip");
                String msg = intent.getStringExtra("replacement");
                if("refresh".equals(msg)){

                }
            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);

    }
    private void initView(){
        tv_replacement_name.setText("无");
        tv_replacement_price.setText("无");
        tv_replacement_number.setText("无");
        tv_replacement_amount.setText("无");
    }
    
    private void initData() {
        if(!(getArguments()==null)){
        STATUS_FLAG=(String)getArguments().get("str");}
        if(STATUS_FLAG=="3-2"||STATUS_FLAG.equals("3-2")){
            initDiffDetail();
        }
    }
    //维修工待接单,填写方案，提交备件选择
    private void initDiffDetail(){
        tv_choose_whether_replace.setVisibility(View.VISIBLE);
        tv_whether_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v);
            }});
          //  switch (whether_replace){

            }

  public void showDialog(View view)
  {
      final String[] items = {"是","否"};
      AlertDialog.Builder alertBuilder = new AlertDialog.Builder(view.getContext());
      alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
              whether_replace=items[i];
              Toast.makeText(getContext(),whether_replace,Toast.LENGTH_SHORT).show();
              tv_whether_replace.setText(items[i]);
              if(whether_replace=="是"|whether_replace.equals("是")) {
                  fragment_order_choose_replacement.setVisibility(View.VISIBLE);
                  tv_replacement_id.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Toast.makeText(getContext(),"点击",Toast.LENGTH_SHORT).show();
                      }
                  });
              }else{
                  fragment_order_choose_replacement.setVisibility(View.INVISIBLE);
              }
              dialogInterface.dismiss();
            //  tmp=items[i];
          }
      });
      alertBuilder.create().show();
  }
}
