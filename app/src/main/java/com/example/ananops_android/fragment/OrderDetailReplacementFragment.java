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
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.ChooseReplacementActivity;
import com.example.ananops_android.activity.RepairAddActivity;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.entity.RepairContent;
import com.example.ananops_android.entity.Replacement;
import com.example.ananops_android.util.BaseUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailReplacementFragment extends Fragment {
    private static String STATUS_FLAG;
    private static String ORDER_ID;
    private TextView tv_whether_replace;
    private TextView tv_replacement_id;
    private TextView tv_choose_whether_replace;
    private TextView fragment_order_choose_replacement;
    private String whether_replace="否";
    private Context mContext;
    private List<Replacement> replacementList=new ArrayList<>();
    private ListView lv_replacement;
    private ListCommonAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order_detail_replacement,container,false);
        tv_whether_replace=view.findViewById(R.id.tv_whether_replace);
        tv_replacement_id=view.findViewById(R.id.tv_replacement_id);
        tv_choose_whether_replace=view.findViewById(R.id.tv_choose_whether_replace);
        fragment_order_choose_replacement=view.findViewById(R.id.fragment_order_choose_replacement);
        lv_replacement=view.findViewById(R.id.lv_replacement);
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
               // String msg = intent.getStringExtra("replacement");
               List<Replacement> replacementListTest=new ArrayList<>();
                     replacementListTest= (List<Replacement>) intent.getSerializableExtra("dataList");
                     replacementList.clear();
                for(Replacement replacement:replacementListTest){
                    replacementList.add(replacement);
                }
                refresh();
            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);

    }
    private void initView(){
    }
    
    private void initData() {
        if(!(getArguments()==null)){
        STATUS_FLAG=(String)getArguments().get("str");}
        if(STATUS_FLAG=="3-2"||STATUS_FLAG.equals("3-2")){
            initDiffDetail();
        }else {
            tv_whether_replace.setText("是");
            fragment_order_choose_replacement.setVisibility(View.INVISIBLE);
            Replacement replacement=new Replacement();
            replacement.setRepalcement_name("易美吉双头锯-继电器（30*5*1）");
            replacement.setRepalcement_id("1324");
            replacement.setReplacement_type("30*50*1");
            replacement.setReplacement_price((float) 20.00);
            replacement.setReplacement_num(1);
            Replacement replacement1=new Replacement();
            replacement1.setRepalcement_name("易美吉双头锯-继电器（20*5*1）");
            replacement1.setRepalcement_id("4321");
            replacement1.setReplacement_type("20*50*1");
            replacement1.setReplacement_price((float) 40.00);
            replacement.setReplacement_num(1);
            replacementList.add(replacement);
            // replacements.add(replacement);
            // replacements.add(replacement1);
            replacementList.add(replacement1);
        }
        mAdapter=new ListCommonAdapter<Replacement>(getContext(),R.layout.item_replacement_table,replacementList) {
            @Override
            protected void convert(final ListViewHolder viewHolder, final Replacement replacement, final int position) {
                viewHolder.setText(R.id.replacement_table_name,replacement.getRepalcement_name());
                viewHolder.setText(R.id.replacement_table_id,replacement.getRepalcement_id());
                viewHolder.setText(R.id.replacement_table_num,String.valueOf(replacement.getReplacement_num()));
                viewHolder.setText(R.id.replacement_table_price,String.valueOf(replacement.getReplacement_price()));
                replacementList.get(position).setReplacement_totalPricce(replacement.getReplacement_num()*replacement.getReplacement_price());
                viewHolder.setText(R.id.replacement_table_money,String.valueOf(replacement.getReplacement_num()*replacement.getReplacement_price()));
               // final int value1=viewHolder.getItemPosition();
                viewHolder.setOnClickListener(R.id.replacement_table_num, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int value=viewHolder.getItemPosition();
                        Toast.makeText(getContext(),"position"+value,Toast.LENGTH_SHORT).show();
                        final EditText et = new EditText(getContext());
                        et.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(v.getContext());
                        alertBuilder.setTitle("请选择备件数量")
                                .setView(et)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int num=1;
                                        try{
                                         num=(Integer.parseInt(et.getText().toString()));}
                                        catch (Exception e){e.printStackTrace();}
                                        replacementList.get(value).setReplacement_num(num);
                                        replacementList.get(value).setReplacement_totalPricce(replacementList.get(value).getReplacement_price()*num);
                                        notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("取消",null).show();
                    }
                });
            }
        };
        lv_replacement.setAdapter(mAdapter);

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
            //刷新
            private void refresh(){
               mAdapter.notifyDataSetChanged();
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
                          BaseUtils.getInstence().intent(getContext(), ChooseReplacementActivity.class);
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
