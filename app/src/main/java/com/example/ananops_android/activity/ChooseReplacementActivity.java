package com.example.ananops_android.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.annotation.ViewInject;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.OrderDetailResponse;
import com.example.ananops_android.db.ReplacementListResponse;
import com.example.ananops_android.db.ReplacementOrderCreateRequest;
import com.example.ananops_android.entity.Replacement;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.AnnotationUtils;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChooseReplacementActivity extends BaseActivity {
    @ViewInject(R.id.lv_choose_replacement)
    private ListView lv_choose_replacement;

    @ViewInject(R.id.txt_title)
    private TextView title;

    @ViewInject(R.id.img_back)
    private ImageView imageBack;

    @ViewInject(R.id.replacement_choose_confirm)
    private Button replacement_choose_confirm;

    @ViewInject(R.id.replacement_add_tv1)
    private TextView replacement_tv1;

    @ViewInject(R.id.replacement_add_tv2)
    private TextView replacement_tv2;

    @ViewInject(R.id.replacement_add_tv3)
    private TextView replacement_tv3;

    @ViewInject(R.id.replacement_add_tv4)
    private TextView replacement_tv4;

    @ViewInject(R.id.replacement_add_tv5)
    private TextView replacement_tv5;

    @ViewInject(R.id.apply_device)
    private LinearLayout apply_device;

    private ListView itemList;
    private List<Replacement> replacements=new ArrayList<>();
    private List<Replacement> replacementsadd=new ArrayList<>();
    private ListCommonAdapter mAdapter;
    private Context mContext;
    private String repairId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_replacement_choose);
        mContext=this;
        AnnotationUtils.injectViews(this);
        initViews();
        initData();
        setListener();
        getReplacementData();
    }
    private void initViews() {
        title.setText("添加备品备件");
    }
    private void initData() {
        Intent intent=getIntent();
        repairId=intent.getStringExtra("repairId");
        replacement_tv1.setText(repairId);
        replacement_tv3.setText(SPUtils.getInstance(mContext).getString("user_id", "0"));
        replacement_tv2.setText(SPUtils.getInstance(mContext).getString("role_name", "0"));
        getOrderInfo();
    //    replacements=getReplacementData(replacements);
        mAdapter=new ListCommonAdapter<Replacement>(mContext,R.layout.item_replacemet_add,replacementsadd) {
            @Override
            protected void convert(ListViewHolder viewHolder, Replacement replacement, int position) {
               viewHolder.setText(R.id.replacement_add_item_tv1,replacement.getDeviceId());//名称
               viewHolder.setText(R.id.replacement_add_item_tv2,replacement.getManufacture());//id
               viewHolder.setText(R.id.replacement_add_item_tv3,replacement.getModel());//类型
               viewHolder.setText(R.id.replacement_add_item_tv4,replacement.getName());//价格
                viewHolder.setText(R.id.replacement_add_item_tv5,replacement.getType());//价格
                    viewHolder.setText(R.id.replacement_add_item_tv6, String.valueOf(replacement.getCount()));
                viewHolder.setOnClickListener(R.id.replacement_add_item_tv6, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int value = viewHolder.getItemPosition();
                      //  Toast.makeText(getContext(), "position" + value, Toast.LENGTH_SHORT).show();
                        final EditText et = new EditText(mContext);
                        et.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(v.getContext());
                        alertBuilder.setTitle("请选择备件数量")
                                .setView(et)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int num = 1;
                                        try {
                                            num = (Integer.parseInt(et.getText().toString()));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        replacementsadd.get(value).setCount(num);
                                   //     replacementsadd.get(value).setReplacement_totalPricce(replacementList.get(value).getPrice() * num);
                                        notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("取消", null).show();
                    }
                });
            }
        };
    lv_choose_replacement.setAdapter(mAdapter);
    }

    private void getOrderInfo() {
        Net.instance.getOrderDetail(Long.valueOf(repairId), SPUtils.getInstance(mContext).getString("Token"," "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderDetailResponse>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("getRepairDetail", System.currentTimeMillis() + "");
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(OrderDetailResponse orderDetailResponse) {
                        if (TextUtils.equals(orderDetailResponse.getCode(), "200")) {
                            if(orderDetailResponse.getResult().getPrincipalInfoDto()!=null){
                                replacement_tv4.setText(orderDetailResponse.getResult().getPrincipalInfoDto().getUserName());//服务商
                                replacement_tv5.setText(String.valueOf(orderDetailResponse.getResult().getPrincipalInfoDto().getId()));//
                            }
                            if (orderDetailResponse.getResult().getEngineerDto() != null) {
                                replacement_tv2.setText(orderDetailResponse.getResult().getEngineerDto().getUserName());//工程师
                            }
                        }
                    }
                });
    }
    private void getReplacementData() {
        Net.instance.getReplacementList(SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReplacementListResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                     e.printStackTrace();
                    }

                    @Override
                    public void onNext(ReplacementListResponse replacementListResult) {
                        if (TextUtils.equals(replacementListResult.getCode(), "200")) {
                            replacements.clear();
                            if (replacementListResult.getResult().size() > 0) {
                                replacements.addAll(replacementListResult.getResult());
                            }
                        }
                    }
                });
    }

    private void setListener() {
        imageBack.setOnClickListener(v -> finish());
        apply_device.setOnClickListener(v -> {
            //subDeviceOrder();
            chooseReplacement();
        });
        replacement_choose_confirm.setOnClickListener(v -> {
           subDeviceOrder();
          //  chooseReplacement();
//            List<Replacement> replacementAdd=new ArrayList<>();
//            SparseBooleanArray checkedArray =lv_choose_replacement.getCheckedItemPositions();
//            for (int i = 0; i < checkedArray.size(); i++) {
//                if (checkedArray.valueAt(i)) {
//                    replacements.get(i).setCount(1);
//                   replacementAdd.add(replacements.get(i));
//                }
//                }
//            Toast.makeText(getApplicationContext(),"选中了"+replacementAdd.size(),Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent("android.intent.action.CART_BROADCAST");
//            intent.putExtra("dataList",(Serializable) replacementAdd);
//            LocalBroadcastManager.getInstance(ChooseReplacementActivity.this).sendBroadcast(intent);
//            sendBroadcast(intent);
//            finish();
        });
    }

    private void chooseReplacement() {
                         ListCommonAdapter mListAdapter = new ListCommonAdapter<Replacement>(mContext, R.layout.item_chooese_replacement, replacements) {
                    @Override
                    protected void convert(ListViewHolder viewHolder, Replacement replacement, int position) {
                        viewHolder.setText(R.id.replacement_name, replacement.getName());//名称
                        viewHolder.setText(R.id.replacement_id, String.valueOf(replacement.getId()));//id
                        viewHolder.setText(R.id.replacement_type, replacement.getModel());//类型
                        viewHolder.setText(R.id.replacement_price, replacement.getManufacture());//价格
                    }
                };
                itemList =new ListView(mContext);
                itemList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//                itemList.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_multiple_choice, inspectionTaskItems));
                itemList.setAdapter(mListAdapter);
                new AlertDialog.Builder(mContext)
                        .setTitle("请选择巡检子项")
                        .setView(itemList)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SparseBooleanArray checkedArray = itemList.getCheckedItemPositions();
                                replacementsadd.clear();
                                for (int i = 0; i < checkedArray.size(); i++) {
                                    if (checkedArray.valueAt(i)) {
                                        replacements.get(i).setCount(1);
                                        replacementsadd.add(replacements.get(i));
                                    }
                                }
                                mAdapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
    }
    private void subDeviceOrder() {
        ReplacementOrderCreateRequest replacementOrderCreateRequest = new ReplacementOrderCreateRequest();
        replacementOrderCreateRequest.setObjectId(Long.valueOf(repairId));
        replacementOrderCreateRequest.setCurrentApproverId(Long.valueOf(replacement_tv5.getText().toString().trim()));
        replacementOrderCreateRequest.setCurrentApprover(replacement_tv4.getText().toString().trim());
        replacementOrderCreateRequest.setApplicantId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", " ")));
        replacementOrderCreateRequest.setApplicant(SPUtils.getInstance(mContext).getString("role_name", " "));
        replacementOrderCreateRequest.setItems(replacementsadd);
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
                           // BaseUtils.getInstence().changeStatus(7, repairId, "提交备件申请", mContext);
                              BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                        } else {
                            Toast.makeText(mContext, "提交失败！", Toast.LENGTH_SHORT).show();
                            //    BaseUtils.getInstence().intent(getActivity(),UserMainActivity.class);
                        }
                    }
                });
    }

}
