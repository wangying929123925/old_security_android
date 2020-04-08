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
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.ChooseReplacementActivity;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.ReplacementOrderCreateRequest;
import com.example.ananops_android.entity.Replacement;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderDetailReplacementFragment extends Fragment {
    private static String STATUS_FLAG;
    private static String ORDER_ID;
    private TextView tv_whether_replace;
    private TextView tv_replacement_id;
    private TextView tv_choose_whether_replace;
    private TextView fragment_order_choose_replacement;
    private Button replacement_submit;
    private String whether_replace = "否";
    private Context mContext;
    private ArrayList<Replacement> replacementList = new ArrayList<>();
    private ListView lv_replacement;
    private ListCommonAdapter mAdapter;
    private ArrayList<String> datas = new ArrayList<>();
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    public static OrderDetailReplacementFragment getInstance(ArrayList<String> datas,String orderId,String statusDo) {
        OrderDetailReplacementFragment orderDetailReplacementFragment = new OrderDetailReplacementFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("data", datas);
        bundle.putString("order_id",orderId);
        bundle.putString("statusDo",statusDo);
        orderDetailReplacementFragment.setArguments(bundle);
        return orderDetailReplacementFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail_replacement, container, false);
        tv_whether_replace = view.findViewById(R.id.tv_whether_replace);
        tv_replacement_id = view.findViewById(R.id.tv_replacement_id);
        tv_choose_whether_replace = view.findViewById(R.id.tv_choose_whether_replace);
        fragment_order_choose_replacement = view.findViewById(R.id.fragment_order_choose_replacement);
        lv_replacement = view.findViewById(R.id.lv_replacement);
        replacement_submit = view.findViewById(R.id.choose_replacement_button);
        tv_choose_whether_replace.setVisibility(View.INVISIBLE);
        fragment_order_choose_replacement.setVisibility(View.INVISIBLE);
        tv1 = view.findViewById(R.id.tv_facilities_name);
        tv2 = view.findViewById(R.id.tv_repairer_name);
        tv3 = view.findViewById(R.id.tv_repair_result);
        tv4 = view.findViewById(R.id.tv_fault_suggestion);
        initView();
        initData();
        mContext=getContext();
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
        if (!(getArguments() == null)) {
            ORDER_ID = (String) getArguments().get("order_id");
            STATUS_FLAG = (String) getArguments().get("statusDo");
            datas = getArguments().getStringArrayList("data");
        }
        tv1.setText(datas.get(0));
        tv2.setText(datas.get(1));
        tv3.setText(datas.get(2));
        tv4.setText(datas.get(3));
        if (STATUS_FLAG == "3-2" || STATUS_FLAG.equals("3-2")) {
            initDiffDetail();
        } else {
            //从后台拉取数据
            tv_whether_replace.setText("是");
            fragment_order_choose_replacement.setVisibility(View.INVISIBLE);
            replacement_submit.setVisibility(View.GONE);
            Replacement replacement = new Replacement();
            replacement.setName("易美吉双头锯-继电器（30*5*1）");
            replacement.setId("1324");
            replacement.setType("30*50*1");
            replacement.setPrice((float) 20.00);
            replacement.setCount(1);
            Replacement replacement1 = new Replacement();
            replacement1.setName("易美吉双头锯-继电器（20*5*1）");
            replacement1.setId("4321");
            replacement1.setType("20*50*1");
            replacement1.setPrice((float) 40.00);
            replacement.setCount(1);
            replacementList.add(replacement);
            replacementList.add(replacement1);
        }
        mAdapter=new ListCommonAdapter<Replacement>(getContext(),R.layout.item_replacement_table,replacementList) {
            @Override
            protected void convert(final ListViewHolder viewHolder, final Replacement replacement, final int position) {
                viewHolder.setText(R.id.replacement_table_name,replacement.getName());
                viewHolder.setText(R.id.replacement_table_id,replacement.getId());
                viewHolder.setText(R.id.replacement_table_num,String.valueOf(replacement.getCount()));
                viewHolder.setText(R.id.replacement_table_price,String.valueOf(replacement.getPrice()));
                replacementList.get(position).setReplacement_totalPricce(replacement.getCount()*replacement.getPrice());
                viewHolder.setText(R.id.replacement_table_money,String.valueOf(replacement.getCount()*replacement.getPrice()));
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
                                        replacementList.get(value).setCount(num);
                                        replacementList.get(value).setReplacement_totalPricce(replacementList.get(value).getPrice()*num);
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
        String s = tv_whether_replace.getText().toString().trim();
        tv_choose_whether_replace.setVisibility(View.VISIBLE);
        replacement_submit.setVisibility(View.VISIBLE);
        replacement_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交备品备件申请
                Log.v("是否选择备品备件",s);
                if (s.equals("是")) {
                    ReplacementOrderCreateRequest replacementOrderCreateRequest = new ReplacementOrderCreateRequest();
                    replacementOrderCreateRequest.setObjectId(Long.valueOf(ORDER_ID));
                    replacementOrderCreateRequest.setCurrentApproverId(782517846944000001L);
                    replacementOrderCreateRequest.setCurrentApprover("值机员");
                    replacementOrderCreateRequest.setApplicantId(782525013398923265L);
                    replacementOrderCreateRequest.setApplicant("服务商业务员");
                    replacementOrderCreateRequest.setItems(replacementList);
                    Net.instance.ReplacementOrderCreate(replacementOrderCreateRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<CodeMessageResponse>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.v("replacementAddTime", System.currentTimeMillis() + "");
                                    e.printStackTrace();
                                    Toast.makeText(mContext, "提交失败", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onNext(CodeMessageResponse codeMessageResponse) {
                                    if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                        Toast.makeText(mContext, "提交成功！", Toast.LENGTH_SHORT).show();
                                        BaseUtils.getInstence().changeStatus(7, ORDER_ID, "提交备件申请", mContext);
                                        //  BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                    } else {
                                        Toast.makeText(mContext, "服务器故障！", Toast.LENGTH_SHORT).show();
                                        //    BaseUtils.getInstence().intent(getActivity(),UserMainActivity.class);
                                    }
                                }
                            });
                } else {
                    BaseUtils.getInstence().changeStatus(9,ORDER_ID,"不添加备件",mContext);
                }
            }
        });
        tv_whether_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v);
            }});
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
