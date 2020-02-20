package com.example.ananops_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ananops_android.R;
import com.example.ananops_android.adapter.RepairAdapter;
import com.example.ananops_android.db.OrderRequest;
import com.example.ananops_android.entity.RepairContent;
import com.example.ananops_android.db.UserInfo;
import com.example.ananops_android.util.ActivityManager;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

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
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mContext=this;
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_research_order_list);
        initDatas();
        initViews();
        setOnListener();
    }
    private void initViews() {
        title = findViewById(R.id.txt_title);//标题
        //search_img=findViewById(R.id.img_search);
        back_img = findViewById(R.id.img_back);
        search_content = findViewById(R.id.text_search);
        search_text = findViewById(R.id.search_title_txt);
        searchType = "项目类型";
        Intent intent = getIntent();
        TITLE = intent.getStringExtra("title");
        title.setText("维修列表");
        int status = Integer.parseInt(TITLE);
        Log.v("Status",String.valueOf(status));
        OrderRequest orderRequest=new OrderRequest();
        orderRequest.setId(SPUtils.getInstance().getString("user_id",""));
        orderRequest.setStatus(status);
        orderRequest.setRoleCode(SPUtils.getInstance().getString("role_code",""));
        repairContents = BaseUtils.getInstence().getRepairList(repairContents,orderRequest,mContext);
        adapter = new RepairAdapter(repairContents);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.contact_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);

    }
    private void initDatas() {


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
