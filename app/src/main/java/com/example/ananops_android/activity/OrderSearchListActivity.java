package com.example.ananops_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ananops_android.R;
import com.example.ananops_android.adapter.RepairAdapter;
import com.example.ananops_android.entity.RepairContent;

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

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_order_list);
        initViews();
        initDatas();
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
        RepairContent repairContent=new RepairContent();
        repairContent.setRepair_id("2019140282");
        repairContent.setCheck_group("第一组");
        repairContent.setRepair_address("科研楼一楼");
        repairContent.setRepair_man("李民浩");
        repairContent.setRepair_time("2019-11-11 18:39");
        repairContent.setRepair_content("笔记本电脑");
        repairContent.setRepair_status("待维修");
        RepairContent repairContent1=new RepairContent();
        repairContent1.setRepair_id("2019140282");
        repairContent1.setCheck_group("第一组");
        repairContent1.setRepair_address("科研楼一楼");
        repairContent1.setRepair_man("李民浩");
        repairContent1.setRepair_time("2019-11-11 18:39");
        repairContent1.setRepair_content("笔记本电脑");
        repairContent1.setRepair_status("维修中");
        RepairContent repairContent2=new RepairContent();
        repairContent2.setRepair_id("2019140282");
        repairContent2.setCheck_group("第一组");
        repairContent2.setRepair_address("科研楼一楼");
        repairContent2.setRepair_man("李民浩");
        repairContent1.setRepair_time("2019-11-11 18:39");
        repairContent2.setRepair_content("笔记本电脑");
        repairContent2.setRepair_status("待审核");

        RepairContent repairContent3=new RepairContent();
        repairContent3.setRepair_id("2019140282");
        repairContent3.setCheck_group("第一组");
        repairContent3.setRepair_address("科研楼一楼");
        repairContent3.setRepair_man("李民浩");
        repairContent3.setRepair_time("2019-11-11 18:39");
        repairContent3.setRepair_content("笔记本电脑");
        repairContent3.setRepair_status("待接单");

        RepairContent repairContent4=new RepairContent();
        repairContent4.setRepair_id("2019140282");
        repairContent4.setCheck_group("第一组");
        repairContent4.setRepair_address("科研楼一楼");
        repairContent4.setRepair_man("李民浩");
        repairContent4.setRepair_time("2019-11-11 18:39");
        repairContent4.setRepair_content("笔记本电脑");
        repairContent4.setRepair_status("待验收");

        RepairContent repairContent5=new RepairContent();
        repairContent5.setRepair_id("2019140282");
        repairContent5.setCheck_group("第一组");
        repairContent5.setRepair_address("科研楼一楼");
        repairContent5.setRepair_man("李民浩");
        repairContent5.setRepair_time("2019-11-11 18:39");
        repairContent5.setRepair_content("笔记本电脑");
        repairContent5.setRepair_status("已完成");

        RepairContent repairContent6=new RepairContent();
        repairContent6.setRepair_id("2019140282");
        repairContent6.setCheck_group("第一组");
        repairContent6.setRepair_address("科研楼一楼");
        repairContent6.setRepair_man("李民浩");
        repairContent6.setRepair_time("2019-11-11 18:39");
        repairContent6.setRepair_content("笔记本电脑");
        repairContent6.setRepair_status("已完成");

        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent2);
        repairContents.add(repairContent2);
        repairContents.add(repairContent3);
        repairContents.add(repairContent3);
        repairContents.add(repairContent4);
        repairContents.add(repairContent4);
        repairContents.add(repairContent5);
        repairContents.add(repairContent5);
        repairContents.add(repairContent6);
        repairContents.add(repairContent6);

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
