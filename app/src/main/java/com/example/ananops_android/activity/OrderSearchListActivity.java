package com.example.ananops_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ananops_android.R;
import com.example.ananops_android.adapter.RepairAdapter;
import com.example.ananops_android.db.OrderResponse;
import com.example.ananops_android.entity.RepairContent;
import com.example.ananops_android.entity.RepairListContent;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderSearchListActivity extends AppCompatActivity implements View.OnClickListener {
    private String searchType;
    private  String searchContent;
    private List<RepairContent> repairContents=new ArrayList<>();
    private RepairAdapter adapter;//适配器
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView title;
    private ImageView back_img;
    private EditText search_content;
    private TextView search_text;
    private static String TITLE;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_order_list);
        initDatas();
        initViews();
        setOnListener();
    }
    private void initViews() {
        title=findViewById(R.id.txt_title);//标题
        //search_img=findViewById(R.id.img_search);
        back_img=findViewById(R.id.img_back);
        search_content=findViewById(R.id.text_search);
        search_text=findViewById(R.id.search_title_txt);
        searchType="项目类型";
        Intent intent=getIntent();
        TITLE=intent.getStringExtra("title");
        title.setText(TITLE);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView=findViewById(R.id.contact_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter=new RepairAdapter(repairContents);
        mRecyclerView.setAdapter(adapter);
    }
    private void initDatas() {
      repairContents= BaseUtils.getInstence().initRepairContent(repairContents);
//        Net.instance.login1(4)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<OrderResponse>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.v("LoginTime", System.currentTimeMillis() + "");
//                        e.printStackTrace();
//                        Toast.makeText(OrderSearchListActivity.this, "网络异常，请检查网络状态后登陆", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(OrderResponse orderResponse) {
//                    if(TextUtils.equals(orderResponse.getCode(),"200")){
////                        List<RepairListContent> result = orderResponse.getResult();
////                        repairContents = result;
////                        adapter.notifyDataSetChanged();
//                        for (int i = 0; i < orderResponse.getResult().size(); i++) {
//                            repairContents.add(orderResponse.getResult().get(i));
//                        }
//                        Toast.makeText(OrderSearchListActivity.this,"repairContents"+repairContents.get(0).getUserId(), Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        Toast.makeText(OrderSearchListActivity.this, orderResponse.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                    }
//                });

    }

    private void setOnListener() {
        back_img.setOnClickListener(this);
        search_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.search_title_txt:
                searchContent=search_content.getText().toString().trim();
                showSearchResult(searchType,searchContent);
                break;
        }
    }
    private void changeButton(Button button){

    }
    private void showSearchResult(String searchType,String searchContent){

    }
}
