package com.example.ananops_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.db.ReplacementListResponse;
import com.example.ananops_android.entity.Replacement;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.SPUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChooseReplacementActivity extends BaseActivity {
    private ListView lv_choose_replacement;
    private List<Replacement> replacements=new ArrayList<>();
    private TextView title;
    private ImageView imageBack;
    private Button replacement_choose_confirm;
    private ListCommonAdapter mAdapter;
    private Context mContext;
    private String repairId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_replacement_choose);
        mContext=this;
        initViews();
        //initData();
        setListener();
        getReplacementData(replacements);
    }
    private void initViews() {
        lv_choose_replacement=findViewById(R.id.lv_choose_replacement);
        title=findViewById(R.id.txt_title);
        imageBack=findViewById(R.id.img_back);
        replacement_choose_confirm=findViewById(R.id.replacement_choose_confirm);
        title.setText("选择备件");
    }
    private void initData() {
        Intent intent=getIntent();
        repairId=intent.getStringExtra("repairId");
    //    replacements=getReplacementData(replacements);
        mAdapter=new ListCommonAdapter<Replacement>(getApplicationContext(),R.layout.item_chooese_replacement,replacements) {
            @Override
            protected void convert(ListViewHolder viewHolder, Replacement replacement, int position) {
               viewHolder.setText(R.id.replacement_name,replacement.getName());//名称
               viewHolder.setText(R.id.replacement_id,replacement.getId());//id
               viewHolder.setText(R.id.replacement_type,replacement.getType());//类型
               viewHolder.setText(R.id.replacement_price,String.valueOf(replacement.getPrice()));//价格
            }
        };
    lv_choose_replacement.setAdapter(mAdapter);
    }

    public List<Replacement> getReplacementData(final List<Replacement> replacements) {
        Replacement replacement=new Replacement();
        replacement.setName("易美吉双头锯-继电器（30*5*1）");
        replacement.setId("1324");
        replacement.setType("30*50*1");
        replacement.setPrice((float) 20.00);
        Replacement replacement1=new Replacement();
        replacement1.setName("易美吉双头锯-继电器（20*5*1）");
        replacement1.setId("4321");
        replacement1.setType("20*50*1");
        replacement1.setPrice((float) 40.00);
        replacements.add(replacement);
        replacements.add(replacement1);
        replacements.add(replacement);
        replacements.add(replacement1);
        Net.instance.getReplacementList(SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReplacementListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ReplacementListResponse replacementListResult) {
                        if (TextUtils.equals(replacementListResult.getCode(), "200")) {
                            replacements.clear();
                            if (replacementListResult.getResult().size() > 0) {
                                replacements.addAll(replacementListResult.getResult());
                                Log.v("巡检子项列表1", replacementListResult.getResult().get(0).getId() + "");
                                initData();
                            } else {
                                Toast.makeText(mContext, "无巡检子项列表！", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(mContext, replacementListResult.getMessage(), Toast.LENGTH_LONG).show();
                            initData();
                        }
                    }
                });
        return replacements;
    }

    private void setListener() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        replacement_choose_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Replacement> replacementAdd=new ArrayList<>();
                SparseBooleanArray checkedArray =lv_choose_replacement.getCheckedItemPositions();
                for (int i = 0; i < checkedArray.size(); i++) {
                    if (checkedArray.valueAt(i)) {
                        replacements.get(i).setCount(1);
                       replacementAdd.add(replacements.get(i));
                    }
                    }
                Toast.makeText(getApplicationContext(),"选中了"+replacementAdd.size(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                intent.putExtra("dataList",(Serializable) replacementAdd);
                LocalBroadcastManager.getInstance(ChooseReplacementActivity.this).sendBroadcast(intent);
                sendBroadcast(intent);
                finish();
            }
        });
    }



}
